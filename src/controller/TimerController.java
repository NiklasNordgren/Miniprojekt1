package controller;

import java.beans.PropertyChangeListener;

import model.Timer;
import model.Timer.TimeComponent;

public class TimerController {

	public enum TimerPosition {
		LEFT, RIGHT, BOTH
	}
	
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
		else
			this.activeTimer.run();
	}

	public void reset() {
		this.leftTimer.reset();
		this.rightTimer.reset();
	}

	public void increaseTotalGameTime(TimerPosition timerPosition, TimeComponent timeComponent) {
		if (!this.leftTimer.isRunning() && !this.rightTimer.isRunning()) {
			switch (timerPosition) {
			case LEFT:
				leftTimer.increaseTotalGameTime(timeComponent);
				break;
			case RIGHT:
				rightTimer.increaseTotalGameTime(timeComponent);
				break;
			case BOTH:
				leftTimer.increaseTotalGameTime(timeComponent);
				rightTimer.increaseTotalGameTime(timeComponent);
				break;
			default:
				break;
			}
		}
	}

	public void decreaseTotalGameTime(TimerPosition timerPosition, TimeComponent timeComponent) {
		if (!this.leftTimer.isRunning() && !this.rightTimer.isRunning()) {
			switch (timerPosition) {
			case LEFT:
				leftTimer.decreaseTotalGameTime(timeComponent);
				break;
			case RIGHT:
				rightTimer.decreaseTotalGameTime(timeComponent);
				break;
			case BOTH:
				leftTimer.decreaseTotalGameTime(timeComponent);
				rightTimer.decreaseTotalGameTime(timeComponent);
				break;
			default:
				break;
			}
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

	public void setActiveTimer(Timer activeTimer) {
		this.activeTimer = activeTimer;
	}

	public Timer getLeftTimer() {
		return leftTimer;
	}

	public void setLeftTimer(Timer leftTimer) {
		this.leftTimer = leftTimer;
	}

	public Timer getRightTimer() {
		return rightTimer;
	}

	public void setRightTimer(Timer rightTimer) {
		this.rightTimer = rightTimer;
	}

}
