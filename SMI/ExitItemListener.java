package SMI;
import java.awt.event.*;

import javax.swing.*;

/*
 * This class is to close the frame and exit
 */
public class ExitItemListener implements ActionListener {
	JFrame frame;
	IsOpen open;

	// set fields
	public void setFields(JFrame frame, IsOpen open) {
		this.frame = frame;
		this.open = open;
	}

	// when exit button is clicked, close the frame
	public void actionPerformed(ActionEvent e) {
		System.out.println("In ExitItemL: " + this.open.isOpen);
		WindowListener[] a = frame.getWindowListeners();
		WindowListener w = a[0];
		w.windowClosing(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		// frame.dispose();
	}
}
