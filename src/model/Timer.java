package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.TimerTask;

public class Timer {

	private long totalGameTimeSeconds;
	private long remainingGameTimeSeconds;
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
		this.propertyChangeSupport.addPropertyChangeListener("isRunning", propertyChangeListener);
		this.propertyChangeSupport.addPropertyChangeListener("totalGameTimeSeconds", propertyChangeListener);
		this.propertyChangeSupport.addPropertyChangeListener("remainingGameTimeSeconds", propertyChangeListener);
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
				if (isRunning && remainingGameTimeSeconds >= 0) {
					System.out.println(remainingGameTimeSeconds);
					decreaseRemainingTime();
				}
			}
		}, 0, 1000);

		/*
		 * this.thread = new Thread() { public void run() { while (isRunning &&
		 * remainingGameTimeSeconds >= 0) {
		 * System.out.println(remainingGameTimeSeconds); decreaseRemainingTime(); try {
		 * sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); } } } };
		 */
	}
}
