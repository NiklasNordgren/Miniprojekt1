package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;

	private JButton playAndPauseButton = new JButton("Play/Pause");
	private JButton incrementGameTimeButton = new JButton("+");
	private JButton decrementGameTimeButton = new JButton("-");
	private JButton moveCursorLeftButton = new JButton("<");
	private JButton moveCursorRightButton = new JButton(">");
	private JPanel mainPanel = new JPanel();

	public ButtonPanel() {
		init();
	}

	private void init() {
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createEmptyBorder());

		playAndPauseButton.setPreferredSize(new Dimension(100, 100));

		playAndPauseButton.setBackground(Color.lightGray);
		incrementGameTimeButton.setBackground(Color.lightGray);
		decrementGameTimeButton.setBackground(Color.lightGray);
		moveCursorLeftButton.setBackground(Color.lightGray);
		moveCursorRightButton.setBackground(Color.lightGray);

		this.mainPanel.setLayout(new BorderLayout());
		this.mainPanel.add(incrementGameTimeButton, BorderLayout.NORTH);
		this.mainPanel.add(decrementGameTimeButton, BorderLayout.SOUTH);
		this.mainPanel.add(playAndPauseButton, BorderLayout.CENTER);
		this.mainPanel.add(moveCursorLeftButton, BorderLayout.WEST);
		this.mainPanel.add(moveCursorRightButton, BorderLayout.EAST);
		this.add(mainPanel);
	}

	public void addPlayAndPauseButtonActionListener(ActionListener actionListener) {
		this.playAndPauseButton.addActionListener(actionListener);
	}

	public void addIncrementGameTimeButtonActionListener(ActionListener actionListener) {
		this.incrementGameTimeButton.addActionListener(actionListener);
	}

	public void addDecrementGameTimeButtonActionListener(ActionListener actionListener) {
		this.decrementGameTimeButton.addActionListener(actionListener);
	}

	public void addMoveCursorLeftButtonActionListener(ActionListener actionListener) {
		this.moveCursorLeftButton.addActionListener(actionListener);
	}

	public void addMoveCursorRightButtonActionListener(ActionListener actionListener) {
		this.moveCursorRightButton.addActionListener(actionListener);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt.getNewValue());
	}

}
