package SMI;
import java.io.Serializable;

import javax.swing.*;

public class ProjectInfoModel implements Serializable {
	JTextField newProjectText = new JTextField("Project Name cannot be empty", 5);
	JTextField productNameText = new JTextField(5);
	JTextField creatorText = new JTextField(5);
	JTextArea commentTextArea = new JTextArea();

	// constructor
	public ProjectInfoModel() {

	}
}