import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class infomationDomain {
	final private int SIMPLE = 0, AVERAGE = 1, COMPLEX = 2;
	public JLabel label;
	public JTextField input;
	public JTextField output;
	public JRadioButton[] radioButtons;
	public ButtonGroup group;
	public int complexity_value = 0;
	public infomationDomain(int average) {
		radioButtons = new JRadioButton[3];
		complexity_value = average;
	}
	public void addToGroup(ButtonGroup x, JRadioButton[] y) {
		group = x;
		group.add(y[SIMPLE]);
		group.add(y[AVERAGE]);
		group.add(y[COMPLEX]);
		// add ActionListener
		y[SIMPLE].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				complexity_value = Integer.valueOf(y[SIMPLE].getText());
				System.out.println(complexity_value);
			}
		});
		y[AVERAGE].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				complexity_value = Integer.valueOf(y[AVERAGE].getText());
				System.out.println(y[AVERAGE].getText());
			}
		});
		y[COMPLEX].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				complexity_value = Integer.valueOf(y[COMPLEX].getText());
				System.out.println(y[COMPLEX].getText());
			}
		});
	}
}
