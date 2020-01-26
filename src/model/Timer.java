package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.TimerTask;

public class Timer {

	public enum TimeComponent {
		ALL, HOUR, MINUTE, SECOND, NONE
	}

	private long totalGameTimeSeconds;
	private long remainingGameTimeSeconds;
	private String remainingGameTimeAsFormattedString;
	private String totalGameTimeAsFormattedString;
	private boolean isRunning;
	private PropertyChangeSupport propertyChangeSupport;

	public Timer() {
		this(3);
	}

	public Timer(long totalGameTimeSeconds) {
		this.totalGameTimeSeconds = totalGameTimeSeconds;
		this.remainingGameTimeSeconds = totalGameTimeSeconds;
		this.isRunning = false;
		this.propertyChangeSupport = new PropertyChangeSupport(this);
		setupTimer();
	}

	public long getTotalGameTimeSeconds() {
		return totalGameTimeSeconds;
	}

	public void setTotalGameTimeSeconds(long totalGameTimeSeconds) {
		this.totalGameTimeSeconds = totalGameTimeSeconds;
	}

	public void incrementTime() {
		if (!this.isRunning) {
			this.totalGameTimeSeconds++;
			this.remainingGameTimeSeconds++;
			this.updateRemainingGameTimeAsFormattedString();
			this.updateTotalGameTimeAsFormattedString();
		}
	}

	public void decrementTime() {
		if (!this.isRunning && totalGameTimeSeconds > 0) {
			this.remainingGameTimeSeconds--;
			this.totalGameTimeSeconds--;
			this.updateRemainingGameTimeAsFormattedString();
			this.updateTotalGameTimeAsFormattedString();
		}
	}

	public long getRemainingGameTimeSeconds() {
		return remainingGameTimeSeconds;
	}

	private void decreaseRemainingTime() {
		this.remainingGameTimeSeconds--;
		this.updateRemainingGameTimeAsFormattedString();
	}

	public void increaseTotalGameTime(TimeComponent timeComponent) {
		if (timeComponent == TimeComponent.HOUR) {
			totalGameTimeSeconds += 3600;
		} else if (timeComponent == TimeComponent.MINUTE) {
			totalGameTimeSeconds += 60;
		} else if (timeComponent == TimeComponent.SECOND) {
			totalGameTimeSeconds += 1;
		}
		updateTotalGameTimeAsFormattedString();
	}

	public void decreaseTotalGameTime(TimeComponent timeComponent) {
		if (timeComponent == TimeComponent.HOUR && totalGameTimeSeconds > 3600) {
			totalGameTimeSeconds -= 3600;
		} else if (timeComponent == TimeComponent.MINUTE && totalGameTimeSeconds > 60) {
			totalGameTimeSeconds -= 60;
		} else if (timeComponent == TimeComponent.SECOND && totalGameTimeSeconds > 0) {
			totalGameTimeSeconds -= 1;
		}
		updateTotalGameTimeAsFormattedString();
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
		this.propertyChangeSupport.firePropertyChange("isRunning", !isRunning, isRunning);
	}

	private void updateRemainingGameTimeAsFormattedString() {
		this.remainingGameTimeAsFormattedString = timeInSecondsToString(remainingGameTimeSeconds);
		this.propertyChangeSupport.firePropertyChange("remainingGameTimeAsFormattedString", null,
				remainingGameTimeAsFormattedString);
	}
	
	private void updateTotalGameTimeAsFormattedString() {
		this.totalGameTimeAsFormattedString = timeInSecondsToString(totalGameTimeSeconds);
		this.propertyChangeSupport.firePropertyChange("totalGameTimeAsFormattedString", null,
				totalGameTimeAsFormattedString);
	}
	
	private String timeInSecondsToString(long timeInSeconds) {
		int hours = Math.toIntExact(timeInSeconds / 3600);
		int minutes = Math.toIntExact((timeInSeconds - (3600 * hours)) / 60);
		int seconds = Math.toIntExact((timeInSeconds - (3600 * hours) - (minutes * 60)));
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	public void addObserver(PropertyChangeListener propertyChangeListener) {
		this.propertyChangeSupport.addPropertyChangeListener("isRunning", propertyChangeListener);
		this.propertyChangeSupport.addPropertyChangeListener("remainingGameTimeAsFormattedString",
				propertyChangeListener);
		this.propertyChangeSupport.addPropertyChangeListener("totalGameTimeAsFormattedString",
				propertyChangeListener);
		this.updateRemainingGameTimeAsFormattedString();
	}

	public void run() {
		this.isRunning = true;
	}

	public void stop() {
		this.isRunning = false;
	}

	public void reset() {
		this.stop();
		this.remainingGameTimeSeconds = totalGameTimeSeconds;
		this.updateRemainingGameTimeAsFormattedString();
	}

	private void setupTimer() {

		new java.util.Timer().scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				if (isRunning && remainingGameTimeSeconds > 0) {
					decreaseRemainingTime();
				} else {
					stop();
				}
			}
		}, 0, 1000);
	}

}
