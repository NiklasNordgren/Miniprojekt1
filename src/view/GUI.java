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
import model.Timer.TimeComponent;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private DigitalClockPanel leftDigitalClock;
	private DigitalClockPanel rightDigitalClock;
	private ButtonPanel buttonPanel;
	private TimerController timerController;
	private TimeComponent currentTimeComponent;
	
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
		selectTimeComponent(TimeComponent.SECOND);
	}

	private void initLeftDigitalClock() {
		leftDigitalClock = new DigitalClockPanel();
		timerController.registerPropertyChangeListenerLeft(leftDigitalClock);
		leftDigitalClock.setBorderTitle("Player 1");
		leftDigitalClock.setButtonText("End turn (alt-z)");
		leftDigitalClock.setButtonHotKey(KeyEvent.VK_Z);
		leftDigitalClock.setEndTurnButtonEnabled(true);
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
				buttonPanel.directionalButtonsEnabled(false);
				leftDigitalClock.stopBlinking();
				rightDigitalClock.stopBlinking();
			}
		});
		buttonPanel.addResetButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timerController.reset();
				selectTimeComponent(TimeComponent.SECOND);
				buttonPanel.directionalButtonsEnabled(true);
			}
		});
		buttonPanel.addIncrementGameTimeButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timerController.increaseTime(currentTimeComponent);
			}
		});
		buttonPanel.addDecrementGameTimeButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timerController.decreaseTime(currentTimeComponent);
			}
		});
		buttonPanel.addMoveCursorLeftButtonActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (currentTimeComponent) {
				case SECOND:
					selectTimeComponent(TimeComponent.MINUTE);
					break;
				case MINUTE:
					selectTimeComponent(TimeComponent.HOUR);
					break;
				default:
					break;
				}
			}
		});
		buttonPanel.addMoveCursorRightButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (currentTimeComponent) {
				case HOUR:
					selectTimeComponent(TimeComponent.MINUTE);
					break;
				case MINUTE:
					selectTimeComponent(TimeComponent.SECOND);
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
	
	private void selectTimeComponent(TimeComponent timeComponent) {
		currentTimeComponent = timeComponent;
		leftDigitalClock.setTimeComponent(timeComponent);
		rightDigitalClock.setTimeComponent(timeComponent);
	}

}
