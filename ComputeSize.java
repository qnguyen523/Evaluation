import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
/*
 * This class is to compute code size
 */
public class ComputeSize implements ActionListener {
	FPModel fp;
	JTextField CodeSizeField;
	// set fields
	public void setFields(FPModel fp, JTextField CodeSizeField) {
		this.fp=fp;
		this.CodeSizeField=CodeSizeField;
	}
	// when Compute Code Size button is clicked
	public void actionPerformed(ActionEvent e) {
		// compute code size
		DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
		CodeSizeField.setText(format.format(fp.computeCodeSize())+"");
	}
}
