package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.TimerTask;

public class Timer {

	private long totalGameTimeSeconds;
	private long remainingGameTimeSeconds;
	private String remainingGameTimeAsFormattedString;
	private boolean isRunning;
	private PropertyChangeSupport propertyChangeSupport;

	public Timer() {
		this(5);
	}

	public Timer(long totalGameTimeSeconds) {
		this.totalGameTimeSeconds = totalGameTimeSeconds;
		this.remainingGameTimeSeconds = totalGameTimeSeconds;
		this.isRunning = false;
		this.propertyChangeSupport = new PropertyChangeSupport(this);
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
		}
	}

	public void decrementTime() {
		if (!this.isRunning && totalGameTimeSeconds > 0) {
			this.remainingGameTimeSeconds--;
			this.totalGameTimeSeconds--;
			this.updateRemainingGameTimeAsFormattedString();
		}
	}

	public long getRemainingGameTimeSeconds() {
		return remainingGameTimeSeconds;
	}

	public void decreaseRemainingTime() {
		this.remainingGameTimeSeconds--;
		this.updateRemainingGameTimeAsFormattedString();
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	private void updateRemainingGameTimeAsFormattedString() {
		int hours = Math.toIntExact(remainingGameTimeSeconds / 3600);
		int minutes = Math.toIntExact((remainingGameTimeSeconds - (3600 * hours)) / 60);
		int seconds = Math.toIntExact((remainingGameTimeSeconds - (3600 * hours) - (minutes * 60)));

		this.remainingGameTimeAsFormattedString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
		this.propertyChangeSupport.firePropertyChange("remainingGameTimeAsFormattedString", null,
				remainingGameTimeAsFormattedString);
	}

	public void addObserver(PropertyChangeListener propertyChangeListener) {
		this.propertyChangeSupport.addPropertyChangeListener("isRunning", propertyChangeListener);
		this.propertyChangeSupport.addPropertyChangeListener("remainingGameTimeAsFormattedString",
				propertyChangeListener);
		this.updateRemainingGameTimeAsFormattedString();
	}

	public void run() {
		this.isRunning = true;
		this.startTimer();
	}

	public void stop() {
		this.isRunning = false;
	}

	public void reset() {
		this.stop();
		this.remainingGameTimeSeconds = totalGameTimeSeconds;
	}

	private void startTimer() {

		new java.util.Timer().scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				if (isRunning && remainingGameTimeSeconds > 0) {
					System.out.println(remainingGameTimeSeconds);
					decreaseRemainingTime();
				}
			}
		}, 0, 1000);
	}

}
