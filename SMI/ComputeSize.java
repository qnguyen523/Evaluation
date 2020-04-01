package SMI;
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
	SaveModel saveObject;
	ArrayList<SaveModel> saveObjectArray;
	int currentCodeSize = -1;

	// set fields
	public void setFields(FPModel fp, JTextField CodeSizeField, SaveModel saveObject,
			ArrayList<SaveModel> saveObjectArray) {
		this.fp = fp;
		this.CodeSizeField = CodeSizeField;
		this.saveObject = saveObject;
		this.saveObjectArray = saveObjectArray;
	}

	// when Compute Code Size button is clicked
	public void actionPerformed(ActionEvent e) {
		// compute code size
		System.out.println(currentCodeSize + " in ComputeSize");
		// validate
		if (currentCodeSize != fp.computeCodeSize()) {
			// saving
			if (!saveObjectArray.isEmpty() && currentCodeSize != -1) {
				saveObjectArray.remove(saveObjectArray.size() - 1);
				System.err.println("Remove from ComputeSize");
			}
			currentCodeSize = fp.computeCodeSize();
			DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
			CodeSizeField.setText(format.format(currentCodeSize) + "");

			saveObjectArray.add(saveObject);
			System.out.println(saveObjectArray);
		} else {
			System.err.println("Error in ComputeSize. You already computed");
			JOptionPane.showMessageDialog(null, "You already computed. " + "You can change inputs and compute again",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
