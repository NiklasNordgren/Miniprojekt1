package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.Timer.TimeComponent;

public class DigitalClockPanel extends JPanel implements PropertyChangeListener {

//	public enum SelectedField {
//		ALL, HOUR, MINUTE, SECOND, NONE
//	}

	private static final long serialVersionUID = 1L;

	private JTextField timeField = new JTextField();
	private JLabel hourField = new JLabel();
	private JLabel minuteField = new JLabel();
	private JLabel secondField = new JLabel();
	private JButton endTurnButton = new JButton();
	private JPanel timerPanel = new JPanel();
	private TimeComponent selectedField = TimeComponent.ALL;
	private Timer blinkTimer = new Timer();

	public DigitalClockPanel() {
		init();
	}

	private void init() {
		setLayout(new GridBagLayout());
		setBackground(Color.white);

		GridBagConstraints c = new GridBagConstraints();
		Font font = new Font("Tahoma", Font.PLAIN, 40);

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;

		hourField.setFont(font);
		hourField.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		hourField.setBackground(Color.WHITE);
		hourField.setOpaque(true);
		minuteField.setFont(font);
		minuteField.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		minuteField.setBackground(Color.WHITE);
		minuteField.setOpaque(true);
		secondField.setFont(font);
		secondField.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		secondField.setBackground(Color.WHITE);
		secondField.setOpaque(true);

		timerPanel.setBackground(Color.white);
		timerPanel.setPreferredSize(new Dimension(300, 75));
		timerPanel.setLayout(new GridBagLayout());
		timerPanel.add(hourField, c);
		c.gridx++;
		timerPanel.add(minuteField, c);
		c.gridx++;
		timerPanel.add(secondField, c);
		c.gridx = 0;

		JPanel endTurnPanel = new JPanel();
		endTurnPanel.setBackground(Color.white);
		endTurnPanel.setLayout(new GridBagLayout());
		endTurnButton.setPreferredSize(new Dimension(150, 35));
		endTurnButton.setBackground(Color.lightGray);
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

	public void addEndTurnButtonActionListener(ActionListener actionListener) {
		endTurnButton.addActionListener(actionListener);
	}

	public void setBackgroundColor(Color color) {
		timerPanel.setBackground(color);
	}

	public void setEndTurnButtonEnabled(boolean tof) {
		endTurnButton.setEnabled(tof);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if (prop.equals("remainingGameTimeAsFormattedString") || prop.equals("totalGameTimeAsFormattedString")) {
			timeField.setText(evt.getNewValue().toString());
			String[] timeComponents = evt.getNewValue().toString().split(":");
			hourField.setText(timeComponents[0] + ":");
			minuteField.setText(timeComponents[1] + ":");
			secondField.setText(timeComponents[2]);
		}
	}

	public boolean isEndTurnButtonEnabled() {
		return this.endTurnButton.isEnabled();
	}

	public void grabFocusOnEndTurnButton() {
		this.endTurnButton.grabFocus();
	}

	public TimeComponent getSelectedField() {
		return selectedField;
	}

	/**
	 * The selected field starts blinking.
	 * 
	 * @param selectedField
	 */
	public void setSelectedField(TimeComponent selectedField) {
		this.selectedField = selectedField;
		stopBlinking();
		List<JLabel> fields = new ArrayList<JLabel>();
		switch (selectedField) {
		case NONE:
			fields = Arrays.asList(hourField, minuteField, secondField);
			stopBlinking();
			return;
		case ALL:
			fields = Arrays.asList(hourField, minuteField, secondField);
			break;
		case HOUR:
			fields.add(hourField);
			break;
		case MINUTE:
			fields.add(minuteField);
			break;
		case SECOND:
			fields.add(secondField);
			break;
		default:
			break;
		}
		blinkSelectedFields(fields);
	}

	private void blinkSelectedFields(List<JLabel> labels) {
		blinkTimer = new Timer();
		setAllFieldTextColor(Color.BLACK);
		blinkTimer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				labels.forEach(label -> {
					if (label.getForeground() == Color.BLACK) {
						label.setForeground(Color.WHITE);
					} else {
						label.setForeground(Color.BLACK);
					}
				});
			}
		}, 0, 700);
	}
	
	private void stopBlinking() {
		blinkTimer.cancel();
		setAllFieldTextColor(Color.BLACK);
	}

	private void setAllFieldTextColor(Color color) {
		hourField.setForeground(color);
		minuteField.setForeground(color);
		secondField.setForeground(color);
	}

}
