import javax.swing.*;

public class FPModel {
	final private int SIMPLE = 0, AVERAGE = 1, COMPLEX = 2;
	public JLabel label;
	public JTextField textField;
	public JTextField output;
	public JRadioButton[] radioButtons;
	public ButtonGroup group;
	public FPModel() {
		radioButtons = new JRadioButton[3];
	}
	public void addToGroup(ButtonGroup x, JRadioButton[] y) {
		group = x;
		group.add(y[SIMPLE]);
		group.add(y[AVERAGE]);
		group.add(y[COMPLEX]);
	}
}
