package controller;

import java.beans.PropertyChangeListener;

import model.Timer;

public class TimerController {

	private Timer activeTimer;
	private Timer leftTimer;
	private Timer rightTimer;

	public TimerController() {
		this.createTimers();
		this.activeTimer = leftTimer;
	}

	private void createTimers() {
		this.leftTimer = new Timer();
		this.rightTimer = new Timer();
	}

	public void startAndStop() {
		if (this.activeTimer.isRunning())
			this.activeTimer.stop();
		else
			this.activeTimer.run();
	}

	public void reset() {
		this.leftTimer.reset();
		this.rightTimer.reset();
	}

	public void incrementGameTime() {

		// TODO: Needs two inparameters, which time unit & timer to increment

		if (!this.leftTimer.isRunning() && !this.rightTimer.isRunning()) {
			this.leftTimer.incrementTime();
			this.rightTimer.incrementTime();
		}
	}

	public void decrementGameTime() {

		// TODO: Needs two inparameters, which time unit & timer to decrement

		if (!this.leftTimer.isRunning() && !this.rightTimer.isRunning()) {
			this.leftTimer.decrementTime();
			this.rightTimer.decrementTime();
		}
	}

	public void registerPropertyChangeListenerLeft(PropertyChangeListener propertyChangeListener) {
		leftTimer.addObserver(propertyChangeListener);
	}

	public void registerPropertyChangeListenerRight(PropertyChangeListener propertyChangeListener) {
		rightTimer.addObserver(propertyChangeListener);
	}

	public boolean isRunning() {
		return this.activeTimer.isRunning();
	}

	public void toggleRunning() {
		if (this.activeTimer.isRunning()) {
			this.activeTimer.setRunning(false);
		} else {
			this.activeTimer.setRunning(true);
		}

	}

	public void endTurn() {
		this.toggleRunning();
		if (this.activeTimer.equals(leftTimer)) {
			this.activeTimer = rightTimer;
		} else {
			this.activeTimer = leftTimer;
		}
	}

}
