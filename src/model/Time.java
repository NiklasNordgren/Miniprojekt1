package model;

public class Time {

	private int hours, minutes, seconds;

	public Time(int hours, int minutes, int seconds) {
		if (hours < 0 || minutes < 0 || seconds < 0) {
			throw new IllegalArgumentException("Time must not be negative!");
		}
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public void incrementTime() {
		if (seconds == 59) {
			seconds = 0;
			if (minutes == 59) {
				minutes = 0;
				if (hours == 23) {
					hours = 0;
				}
			}
		} else {
			seconds++;
		}
	}
	
	public void decrementSeconds() {
		if (seconds == 0 && minutes == 0 && hours == 0) {
			// do nothing
		} else if (seconds == 0) {
			seconds = 59;
			if (minutes == 0) {
				minutes = 59;
				if (hours == 23) {
					hours = 0;
				}
			}
		} else {
			seconds--;
		}
	}
	
	public int getTimeInSeconds() {
		return hours*3600 + minutes*60 + seconds;
	}
	
	public boolean isTimeZero() {
		return hours == 0 && minutes == 0 && seconds == 0;
	}

	public int getHours() {
		return hours;
	}

	public void decrementHours() {
		if (hours < 1) {
			// do nothing
		} else {
			hours--;
		}
	}
	
	public void decrementMinutes() {
		if (minutes < 1) {
			// do nothing
		} else {
			minutes--;
		}
	}
	
//	public void setHours(int hours) {
//		if (hours < 0) {
//			throw new IllegalArgumentException("Hours must not be negative!");
//		}
//		this.hours = hours;
//	}

	public int getMinutes() {
		return minutes;
	}

//	public void setMinutes(int minutes) {
//		if (minutes < 0) {
//			throw new IllegalArgumentException("Minutes must not be negative!");
//		}
//		this.minutes = minutes;
//	}

	public int getSeconds() {
		return seconds;
	}

//	public void setSeconds(int seconds) {
//		if (seconds < 0) {
//			throw new IllegalArgumentException("Seconds must not be negative!");
//		}
//		this.seconds = seconds;
//	}
	
	@Override
	public String toString() {
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}
	
}
