import java.awt.event.*;

import javax.swing.*;

public class ExitItemListener implements ActionListener {
	JFrame frame;
	public void setFields(JFrame frame) {
		this.frame=frame;
	}
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
	}
}
