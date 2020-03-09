import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
public class ComputeSizeFromSave implements ActionListener {
	FPModel fp;
	JTextField CodeSizeField;
	JTextField languageField;
	public void setFields(FPModel fp, JTextField CodeSizeField,JTextField languageField) {
		this.fp=fp;
		this.CodeSizeField=CodeSizeField;
		this.languageField=languageField;
	}
	public void actionPerformed(ActionEvent e) {
		// compute code size
		DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
		CodeSizeField.setText(format.format(fp.computeCodeSizeFromSave(languageField))+"");
	}
}
