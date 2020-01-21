package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private DigitalClockPanel leftDigitalClock;
	private DigitalClockPanel rightDigitalClock;
	private ButtonPanel buttonPanel;

	public GUI() {
		init();
	}

	private void init() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		this.setSize(new Dimension(700, 300));
		this.getContentPane().setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		this.leftDigitalClock = new DigitalClockPanel();
		this.rightDigitalClock = new DigitalClockPanel();
		this.buttonPanel = new ButtonPanel();
		this.add(leftDigitalClock);
		this.add(buttonPanel);
		this.add(rightDigitalClock);

		this.setVisible(true);
	}

}
