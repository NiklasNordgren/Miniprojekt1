package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.TimerController;
import controller.TimerController.TimerPosition;
import model.Timer.TimeComponent;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private DigitalClockPanel leftDigitalClock;
	private DigitalClockPanel rightDigitalClock;
	private ButtonPanel buttonPanel;
	private TimerController timerController;
	private TimerPosition selectedTimer = TimerPosition.BOTH;

	public GUI() {
		int gameTimeInSeconds = 120;
		this.timerController = new TimerController(gameTimeInSeconds);
		init();
	}

	private void init() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		setSize(new Dimension(700, 300));
		getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(10, 10, 10, 10);

		initLeftDigitalClock();
		initRightDigitalClock();
		initButtonPanel();
		setActivePlayerBackgroundColor();

		c.weightx = 2;
		c.weighty = 2;
		add(leftDigitalClock, c);

		c.weightx = 1;
		c.weighty = 1;
		add(buttonPanel, c);

		c.weightx = 2;
		c.weighty = 2;
		add(rightDigitalClock, c);

		pack();
		setVisible(true);
	}

	private void initLeftDigitalClock() {
		leftDigitalClock = new DigitalClockPanel();
		timerController.registerPropertyChangeListenerLeft(leftDigitalClock);
		leftDigitalClock.setBorderTitle("Player 1");
		leftDigitalClock.setButtonText("End turn (alt-z)");
		leftDigitalClock.setButtonHotKey(KeyEvent.VK_Z);
		leftDigitalClock.setEndTurnButtonEnabled(true);
		leftDigitalClock.setSelectedField(TimeComponent.ALL);
		leftDigitalClock.addEndTurnButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timerController.endTurn();
				toggleEndTurnButtons();
				setActivePlayerBackgroundColor();
				rightDigitalClock.setEndTurnButtonEnabled(true);
			}
		});

	}

	private void initRightDigitalClock() {
		rightDigitalClock = new DigitalClockPanel();
		timerController.registerPropertyChangeListenerRight(rightDigitalClock);
		rightDigitalClock.setBorderTitle("Player 2");
		rightDigitalClock.setButtonText("End turn (alt-m)");
		rightDigitalClock.setButtonHotKey(KeyEvent.VK_M);
		rightDigitalClock.setBackgroundColor(Color.white);
		rightDigitalClock.setEndTurnButtonEnabled(false);
		rightDigitalClock.setSelectedField(TimeComponent.ALL);
		rightDigitalClock.addEndTurnButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timerController.endTurn();
				toggleEndTurnButtons();
				setActivePlayerBackgroundColor();
			}
		});

	}

	private void initButtonPanel() {
		buttonPanel = new ButtonPanel();
		buttonPanel.addPlayAndPauseButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timerController.startAndStop();
				if (timerController.isRunning()) {
					leftDigitalClock.setSelectedField(TimeComponent.NONE);
					rightDigitalClock.setSelectedField(TimeComponent.NONE);
				} else {
					leftDigitalClock.setSelectedField(TimeComponent.ALL);
					rightDigitalClock.setSelectedField(TimeComponent.ALL);
				}
			}
		});
		buttonPanel.addResetButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timerController.reset();
			}
		});
		buttonPanel.addIncrementGameTimeButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (selectedTimer) {
				case BOTH:
					timerController.increaseTime(TimerPosition.BOTH, TimeComponent.SECOND);
					break;
				case LEFT:
					timerController.increaseTime(TimerPosition.LEFT, leftDigitalClock.getSelectedField());
					break;
				case RIGHT:
					timerController.increaseTime(TimerPosition.RIGHT, leftDigitalClock.getSelectedField());
					break;
				default:
					break;
				}
			}
		});
		buttonPanel.addDecrementGameTimeButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (selectedTimer) {
				case BOTH:
					timerController.decreaseTime(TimerPosition.BOTH, TimeComponent.SECOND);
					break;
				case LEFT:
					timerController.decreaseTime(TimerPosition.LEFT, leftDigitalClock.getSelectedField());
					break;
				case RIGHT:
					timerController.decreaseTime(TimerPosition.RIGHT, leftDigitalClock.getSelectedField());
					break;
				default:
					break;
				}
			}
		});
		buttonPanel.addMoveCursorLeftButtonActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timerController.isRunning()) {
					return;
				}
				switch (selectedTimer) {
				case BOTH:
					selectedTimer = TimerPosition.LEFT;
					leftDigitalClock.setSelectedField(TimeComponent.ALL);
					rightDigitalClock.setSelectedField(TimeComponent.NONE);
					break;
				case LEFT:
					switch (leftDigitalClock.getSelectedField()) {
					case ALL:
						leftDigitalClock.setSelectedField(TimeComponent.SECOND);
						break;
					case SECOND:
						leftDigitalClock.setSelectedField(TimeComponent.MINUTE);
						break;
					case MINUTE:
						leftDigitalClock.setSelectedField(TimeComponent.HOUR);
						break;
					case HOUR:
						break;
					default:
						break;
					}
					break;
				case RIGHT:
					switch (rightDigitalClock.getSelectedField()) {
					case ALL:
						rightDigitalClock.setSelectedField(TimeComponent.ALL);
						leftDigitalClock.setSelectedField(TimeComponent.ALL);
						selectedTimer = TimerPosition.BOTH;
						break;
					case SECOND:
						rightDigitalClock.setSelectedField(TimeComponent.MINUTE);
						break;
					case MINUTE:
						rightDigitalClock.setSelectedField(TimeComponent.HOUR);
						break;
					case HOUR:
						rightDigitalClock.setSelectedField(TimeComponent.ALL);
						break;
					default:
						break;
					}
					break;
				default:
					break;
				}
			}
		});
		buttonPanel.addMoveCursorRightButtonActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (selectedTimer) {
				case BOTH:
					selectedTimer = TimerPosition.RIGHT;
					rightDigitalClock.setSelectedField(TimeComponent.ALL);
					leftDigitalClock.setSelectedField(TimeComponent.NONE);
					break;
				case LEFT:
					switch (leftDigitalClock.getSelectedField()) {
					case ALL:
						leftDigitalClock.setSelectedField(TimeComponent.ALL);
						rightDigitalClock.setSelectedField(TimeComponent.ALL);
						selectedTimer = TimerPosition.BOTH;
						break;
					case SECOND:
						leftDigitalClock.setSelectedField(TimeComponent.ALL);
						break;
					case MINUTE:
						leftDigitalClock.setSelectedField(TimeComponent.SECOND);
						break;
					case HOUR:
						leftDigitalClock.setSelectedField(TimeComponent.MINUTE);
					default:
						break;
					}
					break;
				case RIGHT:
					switch (rightDigitalClock.getSelectedField()) {
					case ALL:
						rightDigitalClock.setSelectedField(TimeComponent.HOUR);
						break;
					case HOUR:
						rightDigitalClock.setSelectedField(TimeComponent.MINUTE);
						break;
					case MINUTE:
						rightDigitalClock.setSelectedField(TimeComponent.SECOND);
						break;
					case SECOND:
						break;
					default:
						break;
					}
					break;
				default:
					break;
				}
			}
		});
	}

	private void setActivePlayerBackgroundColor() {
		if (timerController.getActiveTimer().equals(timerController.getLeftTimer())) {
			leftDigitalClock.setBackgroundColor(Color.black);
			rightDigitalClock.setBackgroundColor(Color.white);
		} else if (timerController.getActiveTimer().equals(timerController.getRightTimer())) {
			leftDigitalClock.setBackgroundColor(Color.white);
			rightDigitalClock.setBackgroundColor(Color.black);
		}
	}

	private void toggleEndTurnButtons() {
		if (leftDigitalClock.isEndTurnButtonEnabled()) {
			leftDigitalClock.setEndTurnButtonEnabled(false);
			rightDigitalClock.setEndTurnButtonEnabled(true);
			rightDigitalClock.grabFocusOnEndTurnButton();
		} else if (rightDigitalClock.isEndTurnButtonEnabled()) {
			rightDigitalClock.setEndTurnButtonEnabled(false);
			leftDigitalClock.setEndTurnButtonEnabled(true);
			leftDigitalClock.grabFocusOnEndTurnButton();
		}
	}

}
