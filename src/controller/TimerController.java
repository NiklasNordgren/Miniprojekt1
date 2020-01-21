package controller;

import java.beans.PropertyChangeListener;

import model.Timer;

public class TimerController {

	private Timer activeTimer;
	private Timer leftTimer;
	private Timer rightTimer;

	public TimerController() {
		this.createTimers();
	}

	private void createTimers() {
		this.leftTimer = new Timer();
		this.rightTimer = new Timer();
	}

	public void start() {
		activeTimer.run();
	}

	public void stop() {
		this.activeTimer.stop();
	}

	public void reset() {
		this.leftTimer.reset();
		this.rightTimer.reset();
	}

	public void registerPropertyChangeListenerLeft(PropertyChangeListener propertyChangeListener) {
		leftTimer.addObserver(propertyChangeListener);
	}

	public void registerPropertyChangeListenerRight(PropertyChangeListener propertyChangeListener) {
		rightTimer.addObserver(propertyChangeListener);
	}

	public String getLeftTimerFomatedString() {
		return leftTimer.getRemainingGameTimeAsFormattedString();
	}

	public String getRightTimerFomatedString() {
		return rightTimer.getRemainingGameTimeAsFormattedString();
	}

}
