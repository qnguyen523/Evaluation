import java.awt.event.*;

import javax.swing.*;
/*
 * This class is to close the frame and exit
 */
public class ExitItemListener implements ActionListener {
	JFrame frame;
	// set fields
	public void setFields(JFrame frame) {
		this.frame=frame;
	}
	// when exit button is clicked, close the frame
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
	}
}
