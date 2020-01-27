package controller;

import java.beans.PropertyChangeListener;

import model.Timer;
import model.Timer.TimeComponent;

public class TimerController {

	private Timer activeTimer;
	private Timer leftTimer;
	private Timer rightTimer;

	public TimerController() {
		this(180);
	}

	public TimerController(int gameTimeInSeconds) {
		this.createTimers(gameTimeInSeconds);
		this.activeTimer = leftTimer;
	}

	private void createTimers(int gameTimeInSeconds) {
		this.leftTimer = new Timer(gameTimeInSeconds);
		this.rightTimer = new Timer(gameTimeInSeconds);
	}

	public void startAndStop() {
		if (this.activeTimer.isRunning())
			this.activeTimer.stop();
		else {
			this.activeTimer.run();
		}
	}

	public void reset() {
		this.leftTimer.reset();
		this.rightTimer.reset();
	}

	public void increaseTime(TimeComponent timeComponent) {
		if (!this.leftTimer.isRunning() && !this.rightTimer.isRunning()) {
			leftTimer.increaseTotalGameTime(timeComponent);
			rightTimer.increaseTotalGameTime(timeComponent);
		}
	}

	public void decreaseTime(TimeComponent timeComponent) {
		if (!this.leftTimer.isRunning() && !this.rightTimer.isRunning()) {
			leftTimer.decreaseTotalGameTime(timeComponent);
			rightTimer.decreaseTotalGameTime(timeComponent);
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
		this.activeTimer.stop();
		if (this.activeTimer.equals(leftTimer)) {
			this.activeTimer = rightTimer;
		} else {
			this.activeTimer = leftTimer;
		}
		this.activeTimer.run();
	}

	public Timer getActiveTimer() {
		return activeTimer;
	}

	public Timer getLeftTimer() {
		return leftTimer;
	}

	public Timer getRightTimer() {
		return rightTimer;
	}

}
