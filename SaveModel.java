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
	FPModel fp = new FPModel();
	@Override
    public boolean equals(Object o) {
		if (o == this) { 
            return true; 
        }
		if (!(o instanceof SMI)) { 
            return false; 
        } 
		// compare
		SaveModel c = (SaveModel) o;
		for (int i=0; i<5; i++) {
			if (!id[i].equals(c.id[i])) return false;
		}
		if (
				!languageField.getText().equals(c.languageField.getText())
				|| !total.getText().equals(c.total.getText())
				|| !VAFField.getText().equals(c.VAFField.getText())
				|| !CodeSizeField.getText().equals(c.CodeSizeField.getText())
				)
			return false;

		// all of fields are equal
		return true;	
	}
	public SaveModel() {
		id[0] = new infomationDomain(0);
		id[1] = new infomationDomain(0);
		id[2] = new infomationDomain(0);
		id[3] = new infomationDomain(0);
		id[4] = new infomationDomain(0);
		for (int i : vaf_array) i=0;
	}
	public String toString() {
		String s = languageField.getText()+" "+total.getText()+" "+VAFField.getText()
		+" "+FPField.getText()+" "+CodeSizeField.getText();
		return s;
	}
}
