package SMI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * This class allows users to choose a language
 */
public class LanguageItemListener implements ActionListener {
	FPModel fp;
	String text;
	JTextField languageField;
	SaveModel saveObject;
	ProjectInfoModel projectInfo;

	// set fields
	public void setFields(FPModel fp, String text, JTextField languageField, ProjectInfoModel projectInfo) {
		this.fp = fp;
		this.text = text;
		this.languageField = languageField;
		// this.saveObject=saveObject;
		// this.saveObject.languageField=this.languageField;
		this.projectInfo = projectInfo;
	}

	// constructor
	public LanguageItemListener() {
		text = "";
		fp = null;
	}

	public void actionPerformed(ActionEvent e) {
		// a project name must be input before proceeding
		String hold = projectInfo.newProjectText.getText();

		// test
		System.out.println(hold);

		// testing
		if (hold.equals("") || hold.equals("Project Name cannot be empty")) {
			System.err.println("Error. Project Name cannot be empty");
			JOptionPane.showMessageDialog(null, "Please enter your project name", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		JFrame languageFrame = new JFrame("Choose a language");
		languageFrame.setLayout(null);
		languageFrame.setSize(300, 600);
		languageFrame.setVisible(true);

		// select one language
		JLabel selectLanguage = new JLabel("Select one language", JLabel.CENTER);
		selectLanguage.setBounds(30, 20, 200, 20);
		// Check boxes
		JCheckBox[] checkBoxes = new JCheckBox[12];
		// Assembly
		checkBoxes[0] = new JCheckBox("Assembler");
		checkBoxes[0].setBounds(50, 50, 200, 20);
		// Ada 95
		checkBoxes[1] = new JCheckBox("Ada 95");
		checkBoxes[1].setBounds(50, 70, 200, 20);
		// C
		checkBoxes[2] = new JCheckBox("C");
		checkBoxes[2].setBounds(50, 90, 200, 20);
		// C++
		checkBoxes[3] = new JCheckBox("C++");
		checkBoxes[3].setBounds(50, 110, 200, 20);
		// C#
		checkBoxes[4] = new JCheckBox("C#");
		checkBoxes[4].setBounds(50, 130, 200, 20);
		// COBOL
		checkBoxes[5] = new JCheckBox("COBOL");
		checkBoxes[5].setBounds(50, 150, 200, 20);
		// Fortran
		checkBoxes[6] = new JCheckBox("FORTRAN");
		checkBoxes[6].setBounds(50, 170, 200, 20);
		// HTML
		checkBoxes[7] = new JCheckBox("HTML");
		checkBoxes[7].setBounds(50, 190, 200, 20);
		// Java
		checkBoxes[8] = new JCheckBox("Java");
		checkBoxes[8].setBounds(50, 210, 200, 20);
		// JavaScript
		checkBoxes[9] = new JCheckBox("JavaScript");
		checkBoxes[9].setBounds(50, 230, 200, 20);
		// VBScript
		checkBoxes[10] = new JCheckBox("VBScript");
		checkBoxes[10].setBounds(50, 250, 200, 20);
		// Visual Basic
		checkBoxes[11] = new JCheckBox("Visual Basic");
		checkBoxes[11].setBounds(50, 270, 200, 20);

		// Done button
		Button doneButton = new Button("DONE");
		doneButton.setBounds(50, 300, 100, 20);

		// group check boxes
		ButtonGroup checkBoxesGroup = new ButtonGroup();
		for (JCheckBox c : checkBoxes)
			checkBoxesGroup.add(c);

		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkBoxes[0].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.ASSEMBLER;
					fp.LOC = 209;
					text = "ASSEMBLER";
					languageField.setText("ASSEMBLER");
					languageFrame.dispose();
					return;
				} else if (checkBoxes[1].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.ADA_95;
					fp.LOC = 154;
					text = "ADA 95";
					languageField.setText("ADA 95");
					languageFrame.dispose();
					return;
				} else if (checkBoxes[2].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.CL;
					fp.LOC = 148;
					text = "C";
					languageField.setText("C");
					languageFrame.dispose();
					return;
				} else if (checkBoxes[3].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.CLPLUS;
					fp.LOC = 59;
					text = "C++";
					languageField.setText("C++");
					languageFrame.dispose();
					return;
				} else if (checkBoxes[4].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.CSHARP;
					fp.LOC = 58;
					text = "C#";
					languageField.setText("C#");
					languageFrame.dispose();
					return;
				} else if (checkBoxes[5].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.COBOL;
					fp.LOC = 80;
					text = "COBOL";
					languageField.setText("COBOL");
					languageFrame.dispose();
					return;
				} else if (checkBoxes[6].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.FORTRAN;
					fp.LOC = 90;
					text = "FORTRAN";
					languageField.setText("FORTRAN");
					languageFrame.dispose();
					return;
				} else if (checkBoxes[7].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.HTML;
					fp.LOC = 43;
					text = "HTML";
					languageField.setText("HTML");
					languageFrame.dispose();
					return;
				} else if (checkBoxes[8].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.JAVA;
					fp.LOC = 55;
					text = "JAVA";
					languageField.setText("JAVA");
					languageFrame.dispose();
					return;
				} else if (checkBoxes[9].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.JAVASCRIPT;
					fp.LOC = 54;
					text = "JAVASCRIPT";
					languageField.setText("JAVASCRIPT");
					languageFrame.dispose();
					return;
				} else if (checkBoxes[10].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.VBSCRIPT;
					fp.LOC = 38;
					text = "VBSCRIPT";
					languageField.setText("VBSCRIPT");
					languageFrame.dispose();
					return;
				} else if (checkBoxes[11].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.VISUAL_BASIC;
					fp.LOC = 50;
					text = "VISUAL BASIC";
					languageField.setText("VISUAL BASIC");
					languageFrame.dispose();
					return;
				} else {
					JOptionPane.showMessageDialog(languageFrame, "Please choose a language", "Alert",
							JOptionPane.ERROR_MESSAGE);
					languageField.setText("");
					fp.LOC = 0;
					text = "";
					System.err.println("Error");
				}

			}
		});
		// add to JFrame languageFrame
		languageFrame.add(selectLanguage);
		for (JCheckBox c : checkBoxes)
			languageFrame.add(c);
		languageFrame.add(doneButton);
	}
}