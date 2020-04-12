package SMI;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * This class is to save the object saveObject
 */
public class SaveItemListener implements ActionListener {
	SaveModel saveObject;
	ProjectInfoModel projectInfo;
	JFrame frame;
	// ArrayList<SaveModel> saveObjectArray;
	public SavingList saving_list;
	JTable table;
	DefaultTableModel model;
	NumberOfRows number_of_rows_when_opening;
	NumberOfRows number_of_rows_when_saving;
	JTabbedPane tabPane;
	public ArrayList<String> file_names;
	public void setFields(SavingList saving_list, ProjectInfoModel projectInfo, JFrame frame, JTable table,
			JTabbedPane tabPane) {
		// this.saveObject=saveObject;
		this.projectInfo = projectInfo;
		this.frame = frame;
		this.saving_list = saving_list;
		this.table = table;
		this.tabPane = tabPane;
	}

	// exit
	public void setNumberOfRows(NumberOfRows number_of_rows_when_opening, NumberOfRows number_of_rows_when_saving) {
		this.number_of_rows_when_opening = number_of_rows_when_opening;
		this.number_of_rows_when_saving = number_of_rows_when_saving;
	}

	// exit
	public void setSavingList(SavingList saving_list) {
		this.saving_list = saving_list;
	}

	// exit
	public void setTable(JTable table, DefaultTableModel model) {
		this.table = table;
		this.model = model;
	}

	// when the save button is clicked
	public void actionPerformed(ActionEvent e) {
		// save file based on projectName and creatorName
		String projectName = projectInfo.newProjectText.getText();
		String creatorName = projectInfo.creatorText.getText();
		creatorName = creatorName.equals("") ? "" : "_" + creatorName;
		String fileName = projectName + ".ms";
		if (fileName.equals("Project Name cannot be empty.ms")) {
			JOptionPane.showMessageDialog(frame, "Nothing to be saved. You must enter the project name", "Alert",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// validate
		if (table == null) {
			System.err.println("Table is not okay");
			System.err.println("Error with table");
			JOptionPane.showMessageDialog(null, "You must add SMI panel and save before closing", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// validate
		System.out.println("Table in Save: "+table.getRowCount());
		if (table.getRowCount() == 0) {
			System.out.println("the number of rows is 0");
			// return;
		} else {
			int numOfRows = table.getRowCount();
			// last smi cell
			if (((String) table.getValueAt(numOfRows - 1, 0)).isEmpty()) {
				System.err.println("Error");
				JOptionPane.showMessageDialog(null, "You must compute before saving", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		// save a file
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			// save active panel's index
			int index = tabPane.getSelectedIndex();
			String title = tabPane.getTitleAt(index);
			saving_list.activeTabTitle = title;

			System.out.println(saving_list.saveObjectArray);
			System.out.println(saving_list.SMI_list);
			System.out.println("In SaveItemListener: saving_list.activeTabTitle: " + saving_list.activeTabTitle);
			System.out.println(saving_list.projectInfo.newProjectText.getText());
			System.out.println(saving_list.file_names);
			System.out.println(saving_list.file_map);
			
			out.writeObject(saving_list);

			out.close();
			fileOut.close();
			JOptionPane.showMessageDialog(frame, "Saved!", "Save", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Serialized data is saved");
		} catch (IOException i) {
			i.printStackTrace();
		}
		// after saving, reset number of rows for exiting
		number_of_rows_when_opening.num = saving_list.SMI_list.size();
		number_of_rows_when_saving.num = saving_list.SMI_list.size();
	}
}
