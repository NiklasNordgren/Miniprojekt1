package controller;

import java.beans.PropertyChangeListener;

import model.Time;
import model.Timer;

public class TimerController {

	public enum TimeComponent {
		HOUR, MINUTE, SECOND
	}

	public enum TimerPosition {
		LEFT, RIGHT, BOTH
	}

	private Timer activeTimer;
	private Timer leftTimer;
	private Timer rightTimer;

	public TimerController() {
		this(0, 2, 0);
	}

	public TimerController(int hours, int minutes, int seconds) {
		this.createTimers(hours, minutes, seconds);
		this.activeTimer = leftTimer;
	}

	private void createTimers(int hours, int minutes, int seconds) {
		this.leftTimer = new Timer(hours, minutes, seconds);
		this.rightTimer = new Timer(hours, minutes, seconds);
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

	public void incrementGameTime(TimerPosition timerPosition, TimeComponent timeComponent) {
		switch (timerPosition) {
		case LEFT:
			increase(timeComponent, leftTimer);
			break;
		case RIGHT:
			increase(timeComponent, rightTimer);
			break;
		case BOTH:
			increase(timeComponent, leftTimer, rightTimer);
			break;
		default:
			throw new RuntimeException(
					"Error in incrementGameTime(). Legal values are LEFT, RIGHT and BOTH. Supplied value was"
							+ timeComponent.toString());
			break;
		}

		if (!this.leftTimer.isRunning() && !this.rightTimer.isRunning()) {
			this.leftTimer.incrementCurrentTime();
			this.rightTimer.incrementCurrentTime();
		}
	}

	public void decrementGameTime(TimerPosition timerPosition, TimeComponent timeComponent) {
		if (!this.leftTimer.isRunning() && !this.rightTimer.isRunning()) {
			switch (timerPosition) {
			case LEFT:
				decrease(timeComponent, leftTimer);
				break;
			case RIGHT:
				decrease(timeComponent, rightTimer);
				break;
			case BOTH:
				decrease(timeComponent, leftTimer, rightTimer);
				break;
			default:
				throw new RuntimeException(
						"Error in incrementGameTime(). Legal values are LEFT, RIGHT and BOTH. Supplied value was"
								+ timeComponent.toString());
			}
		}
	}
	
	private void increase(TimeComponent timeComponent, Timer timer1, Timer timer2) {
		switch (timeComponent) {
		case HOUR:
			timer1.incrementRemainingHours();
			break;
		case MINUTE:
			timer.decrementRemainingMinutes();
			break;
		case SECOND:
			timer.decrementRemainingSeconds();
			break;
		default:
			break;
		}
	}

	private void decrease(TimeComponent timeComponent, Timer timer) {
		switch (timeComponent) {
		case HOUR:
			timer.decrementRemainingHours();
			break;
		case MINUTE:
			timer.decrementRemainingMinutes();
			break;
		case SECOND:
			timer.decrementRemainingSeconds();
			break;
		default:
			break;
		}
	}
	
	private void decrease(TimeComponent timeComponent, Timer timer1, Timer timer2) {
		switch (timeComponent) {
		case HOUR:
			timer1.decrementRemainingHours();
			timer2.decrementRemainingHours();
			break;
		case MINUTE:
			timer1.decrementRemainingMinutes();
			timer2.decrementRemainingMinutes();
			break;
		case SECOND:
			timer1.decrementRemainingSeconds();
			timer2.decrementRemainingSeconds();
			break;
		default:
			break;
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
