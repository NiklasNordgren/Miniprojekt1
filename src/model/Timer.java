package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.TimerTask;

public class Timer {

	private Time remainingTime;
	private Time totalGameTime;
	private String remainingGameTimeAsFormattedString;
	private boolean isRunning;
	private PropertyChangeSupport propertyChangeSupport;


	public Timer(int hours, int minutes, int seconds) {
		this.remainingTime = new Time(hours, minutes, seconds);
		this.totalGameTime = remainingTime;
		this.isRunning = false;
		this.propertyChangeSupport = new PropertyChangeSupport(this);
		this.setupTimer();
	}

	public Time getRemainingTime() {
		return remainingTime;
	}
	
	public void setRemainingTime(Time remainingTime) {
		this.remainingTime = remainingTime;
	}
	
	public Time getTotalGameTime() {
		return this.totalGameTime;
	}

	public void setTotalGameTime(Time totalGameTime) {
		this.totalGameTime = totalGameTime;
	}

	public void incrementCurrentTime() {
		if (!this.isRunning) {
			this.remainingTime.incrementTime();
			this.updateRemainingGameTimeAsFormattedString();
		}
	}
	
	public void incrementTotalTime() {
		this.totalGameTime.incrementTime();
	}

	public void decrementRemainingTime() {
		if (!this.isRunning && !totalGameTime.isTimeZero()) {
			this.decreaseRemainingTime();
		}
	}
	
//	public void decrementTotalGameTime() {
//		this.totalGameTime.decrementSeconds();
//	}

	public void decrementRemainingHours() {
		this.remainingTime.decrementHours();
	}
	
	public void decrementRemainingMinutes() {
		this.remainingTime.decrementMinutes();
	}
	
	public void decrementRemainingSeconds() {
		this.remainingTime.decrementSeconds();
	}

	public void decreaseRemainingTime() {
		this.remainingTime.decrementSeconds();;
		this.updateRemainingGameTimeAsFormattedString();
	}

	public boolean isRunning() {
		return this.isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
		this.propertyChangeSupport.firePropertyChange("isRunning", !isRunning, isRunning);
	}

	
	public void addObserver(PropertyChangeListener propertyChangeListener) {
		this.propertyChangeSupport.addPropertyChangeListener("isRunning", propertyChangeListener);
		this.propertyChangeSupport.addPropertyChangeListener("remainingGameTimeAsFormattedString",
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
		int hours = totalGameTime.getHours();
		int minutes = totalGameTime.getMinutes();
		int seconds = totalGameTime.getSeconds();
		this.remainingTime = new Time(hours, minutes, seconds);
		this.updateRemainingGameTimeAsFormattedString();
	}
	
	private void updateRemainingGameTimeAsFormattedString() {
		this.remainingGameTimeAsFormattedString = remainingTime.toString();
		this.propertyChangeSupport.firePropertyChange("remainingGameTimeAsFormattedString", null,
				remainingGameTimeAsFormattedString);
	}

	private void setupTimer() {

		new java.util.Timer().scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				if (isRunning && !remainingTime.isTimeZero()) {
					decreaseRemainingTime();
				} else {
					stop();
				}
			}
		}, 0, 1000);
	}

}
