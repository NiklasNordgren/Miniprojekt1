package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DigitalClockPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField timeField;
	private JButton endTurnButton;

	public DigitalClockPanel() {
		init();
	}

	private void init() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createTitledBorder("DigitalClockPanel"));
		this.timeField = new JTextField();
		this.timeField.setText("HH:mm:ss");
		this.timeField.setEditable(false);
		this.add(timeField, c);

		c.gridy++;

		this.endTurnButton = new JButton("End turn");
		this.add(endTurnButton, c);
	}

}
