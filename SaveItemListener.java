import java.awt.event.*;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class SaveItemListener implements ActionListener{
	SaveModel saveObject;
	ProjectInfoModel projectInfo;
	JFrame frame;
	public void setFields(SaveModel saveObject,ProjectInfoModel projectInfo, JFrame frame) {
		this.saveObject=saveObject;
		this.projectInfo=projectInfo;
		this.frame=frame;
	}
	public void actionPerformed(ActionEvent e) {
		String projectName = projectInfo.newProjectText.getText();
		String creatorName = projectInfo.creatorText.getText();
		creatorName = creatorName.equals("") ? "" : "_"+ creatorName;
		String fileName = projectName + creatorName+".ms";
		if (fileName.equals("Project Name cannot be empty.ms")) {
			JOptionPane.showMessageDialog(frame, "Nothing to be saved", "Alert", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (saveObject.CodeSizeField.getText().equals("") || saveObject.CodeSizeField.getText().equals("0")) {
			JOptionPane.showMessageDialog(null, "Fields cannot be empty before saving", "Alert", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// save a file
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(saveObject);
			out.close();
			fileOut.close();
			JOptionPane.showMessageDialog(frame, "Saved!","Save", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Serialized data is saved");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}
