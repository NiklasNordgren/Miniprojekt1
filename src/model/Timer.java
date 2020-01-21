package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Timer {

	private long totalGameTimeSeconds;
	private long remainingGameTimeSeconds;
	private boolean isRunning;
	private PropertyChangeSupport propertyChangeSupport;
	private Thread thread;

	public Timer() {
		this(60);
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
		}
	}

	public void decreaseTime() {
		if (!this.isRunning) {
			this.remainingGameTimeSeconds--;
			this.totalGameTimeSeconds--;
		}
	}

	public long getRemainingGameTimeSeconds() {
		return remainingGameTimeSeconds;
	}

	public void decreaseRemainingTime() {
		this.remainingGameTimeSeconds--;
	}

	/*
	 * public void setRemainingGameTimeSeconds(long remainingGameTimeSeconds) {
	 * this.remainingGameTimeSeconds = remainingGameTimeSeconds; }
	 */

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public String getRemainingGameTimeAsFormattedString() {
		int hours = Math.toIntExact(remainingGameTimeSeconds / 3600);
		int minutes = Math.toIntExact((remainingGameTimeSeconds - (3600 * hours)) / 60);
		int seconds = Math.toIntExact((remainingGameTimeSeconds - (3600 * hours) - (minutes * 60)));

		return "hours: " + hours + ", minutes: " + minutes + ", seconds: " + seconds + "";
	}

	public void addObserver(PropertyChangeListener propertyChangeListener) {
		this.propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
	}

	public void run() {
		this.isRunning = true;
		this.thread.run();
	}

	public void stop() {
		this.isRunning = false;
	}

	public void reset() {
		this.stop();
		this.remainingGameTimeSeconds = totalGameTimeSeconds;
	}

	private void setupTimer() {
		this.thread = new Thread() {
			public void run() {
				while (isRunning && remainingGameTimeSeconds >= 0) {
					System.out.println(remainingGameTimeSeconds);
					decreaseRemainingTime();
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
	}
}
