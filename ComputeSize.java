import java.awt.event.*;
import javax.swing.*;
public class ComputeSize implements ActionListener {
	FPModel fp;
	JTextField CodeSizeField;
	public void setFields(FPModel fp, JTextField CodeSizeField) {
		this.fp=fp;
		this.CodeSizeField=CodeSizeField;
	}
	public void actionPerformed(ActionEvent e) {
		// compute code size
		CodeSizeField.setText(fp.computeCodeSize()+"");
	}
}
