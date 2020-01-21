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
		setSize(new Dimension(700, 300));
		getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 10, 10, 10);

		leftDigitalClock = new DigitalClockPanel();
		leftDigitalClock.setBorderTitle("Player 1");
		leftDigitalClock.setButtonText("End button (alt-z)");
		leftDigitalClock.setButtonHotKey(KeyEvent.VK_Z);
		leftDigitalClock.setActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Player 1 end turn pressed");
			}
		});

		rightDigitalClock = new DigitalClockPanel();
		rightDigitalClock.setBorderTitle("Player 2");
		rightDigitalClock.setButtonText("End button (alt-m)");
		rightDigitalClock.setButtonHotKey(KeyEvent.VK_M);
		rightDigitalClock.setActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Player 2 end turn pressed");
			}
		});

		buttonPanel = new ButtonPanel();

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

}