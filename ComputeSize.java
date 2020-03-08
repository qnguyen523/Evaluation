import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
public class ComputeSize implements ActionListener {
	FPModel fp;
	JTextField CodeSizeField;
	public void setFields(FPModel fp, JTextField CodeSizeField) {
		this.fp=fp;
		this.CodeSizeField=CodeSizeField;
	}
	public void actionPerformed(ActionEvent e) {
		// compute code size
		DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
		CodeSizeField.setText(format.format(fp.computeCodeSize())+"");
	}
}
