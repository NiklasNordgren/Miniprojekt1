package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton playAndPauseButton = new JButton("Play/Pause");
	private JButton resetButton = new JButton("Reset");
	private JButton incrementGameTimeButton = new JButton("+");
	private JButton decrementGameTimeButton = new JButton("-");
	private JButton moveCursorLeftButton = new JButton("<");
	private JButton moveCursorRightButton = new JButton(">");

	public ButtonPanel() {
		init();
	}

	private void init() {
		setLayout(new GridBagLayout());
		setBackground(Color.white);
		setBorder(BorderFactory.createEmptyBorder());

		playAndPauseButton.setPreferredSize(new Dimension(100, 100));

		playAndPauseButton.setBackground(Color.lightGray);
		resetButton.setBackground(Color.lightGray);
		incrementGameTimeButton.setBackground(Color.lightGray);
		decrementGameTimeButton.setBackground(Color.lightGray);
		moveCursorLeftButton.setBackground(Color.lightGray);
		moveCursorRightButton.setBackground(Color.lightGray);

		JPanel mainPanel = new JPanel();
		JPanel southPanel = new JPanel();

		mainPanel.setLayout(new BorderLayout());
		southPanel.setLayout(new BorderLayout());

		mainPanel.add(incrementGameTimeButton, BorderLayout.NORTH);
		mainPanel.add(playAndPauseButton, BorderLayout.CENTER);
		mainPanel.add(moveCursorLeftButton, BorderLayout.WEST);
		mainPanel.add(moveCursorRightButton, BorderLayout.EAST);

		southPanel.add(decrementGameTimeButton, BorderLayout.NORTH);
		southPanel.add(resetButton, BorderLayout.SOUTH);

		mainPanel.add(southPanel, BorderLayout.SOUTH);

		add(mainPanel);
	}

	public void addPlayAndPauseButtonActionListener(ActionListener actionListener) {
		playAndPauseButton.addActionListener(actionListener);
	}

	public void addResetButtonActionListener(ActionListener actionListener) {
		resetButton.addActionListener(actionListener);
	}

	public void addIncrementGameTimeButtonActionListener(ActionListener actionListener) {
		incrementGameTimeButton.addActionListener(actionListener);
	}

	public void addDecrementGameTimeButtonActionListener(ActionListener actionListener) {
		decrementGameTimeButton.addActionListener(actionListener);
	}

	public void addMoveCursorLeftButtonActionListener(ActionListener actionListener) {
		moveCursorLeftButton.addActionListener(actionListener);
	}

	public void addMoveCursorRightButtonActionListener(ActionListener actionListener) {
		moveCursorRightButton.addActionListener(actionListener);
	}

}
