package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

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
		playAndPauseButton.setOpaque(true);

		this.mainPanel.setLayout(new BorderLayout());
		this.mainPanel.add(incrementGameTimeButton, BorderLayout.NORTH);
		this.mainPanel.add(decrementGameTimeButton, BorderLayout.SOUTH);
		this.mainPanel.add(playAndPauseButton, BorderLayout.CENTER);
		this.mainPanel.add(moveCursorLeftButton, BorderLayout.WEST);
		this.mainPanel.add(moveCursorRightButton, BorderLayout.EAST);
		this.add(mainPanel);
	}

}
