import java.io.*;
import javax.swing.*;
public class ModelToBeSaved implements Serializable {
	JTextField languageField = new JTextField(2);
	infomationDomain[] id = new infomationDomain[5];
	JTextField total = new JTextField(2);
	JTextField VAFField = new JTextField("0", 2);
	JTextField CodeSizeField = new JTextField(2);
}
