import java.awt.event.*;
import java.io.Serializable;
import javax.swing.*;
// save operation
public class SaveModel implements Serializable {
	JTextField languageField = new JTextField("Null",2);
	infomationDomain[] id = new infomationDomain[5];
	JTextField total = new JTextField("0",2);
	JTextField VAFField = new JTextField("0",2);
	JTextField FPField = new JTextField("0",2);
	JTextField CodeSizeField = new JTextField("0",2);
	public SaveModel() {
		id[0] = new infomationDomain(0);
		id[1] = new infomationDomain(0);
		id[2] = new infomationDomain(0);
		id[3] = new infomationDomain(0);
		id[4] = new infomationDomain(0);
	}
}
