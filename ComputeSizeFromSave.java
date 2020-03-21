import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
public class ComputeSizeFromSave implements ActionListener {
	FPModel fp;
	SaveModel saveObject;
	int currentCodeSize=-1;
	JTabbedPane tabPane;
	public void setTabPane(JTabbedPane tabPane) {
		this.tabPane=tabPane;
	}
	public void setFields(SaveModel saveObject,int currentCodeSize) {
		this.saveObject=saveObject;
		this.currentCodeSize=currentCodeSize;
	}
	public void actionPerformed(ActionEvent e) {
		// compute code size
		System.out.println(currentCodeSize+ " from ComputeSizeFromSave");
		// validate
		if (currentCodeSize!=saveObject.fp.computeCodeSizeFromSave(saveObject.languageField)) {
			currentCodeSize=saveObject.fp.computeCodeSizeFromSave(saveObject.languageField);
			DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
			saveObject.CodeSizeField.setText(format.format(currentCodeSize)+"");
			System.out.println(saveObject);
		} else {
			System.err.println("Error in ComputeSizeFromSave. You already computed");
			JOptionPane.showMessageDialog(null, "You already computed. "
					+ "You can change inputs and compute again", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
