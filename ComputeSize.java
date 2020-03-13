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
	// set fields
	public void setFields(FPModel fp, JTextField CodeSizeField,SaveModel saveObject,ArrayList<SaveModel> saveObjectArray) {
		this.fp=fp;
		this.CodeSizeField=CodeSizeField;
		this.saveObject=saveObject;
		this.saveObjectArray=saveObjectArray;
	}
	// when Compute Code Size button is clicked
	public void actionPerformed(ActionEvent e) {
		// compute code size
		DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
		CodeSizeField.setText(format.format(fp.computeCodeSize())+"");
		
		// saving
		System.out.println(saveObject);
		saveObjectArray.add(saveObject);
	}
}
