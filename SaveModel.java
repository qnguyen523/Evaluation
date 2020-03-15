import java.io.Serializable;
import javax.swing.*;
/*
 * This class is to group all of objects to be saved
 */
@SuppressWarnings("serial")
public class SaveModel implements Serializable {
	JTextField languageField = new JTextField("Null",2);
	infomationDomain[] id = new infomationDomain[5];
	JTextField total = new JTextField("0",2);
	JTextField VAFField = new JTextField("0",2);
	JTextField FPField = new JTextField("0",2);
	JTextField CodeSizeField = new JTextField("0",2);
	int[] vaf_array = new int[14];
	public SaveModel() {
		id[0] = new infomationDomain(0);
		id[1] = new infomationDomain(0);
		id[2] = new infomationDomain(0);
		id[3] = new infomationDomain(0);
		id[4] = new infomationDomain(0);
		for (int i : vaf_array) i=0;
	}
	public String toString() {
		return CodeSizeField.getText();
	}
}
