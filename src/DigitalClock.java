public class DigitalClock {

	private boolean isActive;
	private Thread timer;
	private long gameTimeInSeconds;
	private long remainingGameTimeInSeconds;

	public DigitalClock() {
		this(3 * 60); // Default gameTimeInSeconds set to 3 minutes
	}

	public DigitalClock(long gameTimeInSeconds) {
		this.setupTimer();
		this.isActive = false;
		this.gameTimeInSeconds = gameTimeInSeconds;
		this.remainingGameTimeInSeconds = gameTimeInSeconds;
	}

	private void setupTimer() {
		this.timer = new Thread() {
			public void run() {
				while (isActive && remainingGameTimeInSeconds > 0) {
					System.out.println(remainingGameTimeInSeconds);
					remainingGameTimeInSeconds--;
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
	}

	public void incrementTime() {
		if (!this.isActive) {
			this.gameTimeInSeconds++;
			this.remainingGameTimeInSeconds++;
		}
	}

	public void decrementTime() {
		if (!this.isActive) {
			this.gameTimeInSeconds--;
			this.remainingGameTimeInSeconds--;
		}
	}

	public void start() {
		this.isActive = true;
		this.timer.run();
	}

	public void stop() {
		this.isActive = false;
	}

	public void reset() {
		this.stop();
		this.remainingGameTimeInSeconds = gameTimeInSeconds;
	}

	public String getRemainingGameTimeAsFormattedString() {
		// return String.valueOf(remainingGameTimeInSeconds);
		int hours = Math.toIntExact(remainingGameTimeInSeconds / 3600);
		int minutes = Math.toIntExact((remainingGameTimeInSeconds - (3600 * hours)) / 60);
		int seconds = Math.toIntExact((remainingGameTimeInSeconds - (3600 * hours)) % 60);

		return "hours: " + hours + "minutes: " + minutes + "seconds: " + seconds + "";
	}

	public String getGameTimeAsFormattedString() {
		return String.valueOf(gameTimeInSeconds);
	}

}
