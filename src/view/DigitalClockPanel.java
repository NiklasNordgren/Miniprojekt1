package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class DigitalClockPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField timeField = new JTextField();
	private JButton endTurnButton = new JButton();
	private JPanel timerPanel = new JPanel();
	private JPanel endTurnPanel = new JPanel();
	private GridBagConstraints c = new GridBagConstraints();
	private Font font = new Font("Tahoma", Font.PLAIN, 40);

	public DigitalClockPanel() {
		init();
	}

	private void init() {
		setLayout(new GridBagLayout());

		c.insets = new Insets(50, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		setBackground(Color.white);

		timeField.setText("HH:mm:ss");
		timeField.setEditable(false);
		timeField.setPreferredSize(new Dimension(300, 75));
		timeField.setFont(font);
		timeField.setHorizontalAlignment((int) CENTER_ALIGNMENT);

		timerPanel.setBackground(Color.white);
		timerPanel.setLayout(new GridBagLayout());
		timerPanel.add(timeField, c);

		endTurnPanel.setBackground(Color.white);
		endTurnPanel.setLayout(new GridBagLayout());
		endTurnButton.setPreferredSize(new Dimension(150, 35));
		endTurnButton.setBackground(Color.white);
		endTurnPanel.add(endTurnButton, c);

		add(timerPanel, c);
		c.gridy++;
		add(endTurnPanel, c);
	}

	public void setBorderTitle(String borderTitle) {
		Border border = BorderFactory.createTitledBorder(borderTitle);
		setBorder(border);
	}

	public void setButtonText(String buttonText) {
		endTurnButton.setText(buttonText);
	}

	public void setButtonHotKey(int hotKey) {
		endTurnButton.setMnemonic(hotKey);
	}

	public void setActionListener(ActionListener actionListener) {
		this.endTurnButton.addActionListener(actionListener);
	}

}