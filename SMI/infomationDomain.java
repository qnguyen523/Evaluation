package SMI;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class infomationDomain implements Serializable {
	final private int SIMPLE = 0, AVERAGE = 1, COMPLEX = 2;
	public JLabel label;
	public JTextField input;
	public JTextField output;
	public JRadioButton[] radioButtons;
	public ButtonGroup group;
	public int complexity_value = 0;

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof SMI)) {
			return false;
		}
		infomationDomain c = (infomationDomain) o;
		int i = Integer.parseInt(c.output.getText());
		int j = Integer.parseInt(this.output.getText());

		return i == j;
	}

	public infomationDomain(int average) {
		radioButtons = new JRadioButton[3];
		complexity_value = average;
		// create objects
		input = new JTextField("0", 2);
		output = new JTextField("0", 2);
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
