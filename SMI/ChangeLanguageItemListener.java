package SMI;
import java.awt.Button;
import java.awt.event.*;
import javax.swing.*;

/*
 * This class allows users to change language
 */
public class ChangeLanguageItemListener implements ActionListener {
	FPModel fp;
	JTextField languageField;

	// set fields
	public void setFields(FPModel fp, JTextField languageField) {
		this.fp = fp;
		this.languageField = languageField;
	}

	// when Change Language button is clicked
	public void actionPerformed(ActionEvent e) {
		JFrame changeLanguageFrame = new JFrame("Choose a language");
		changeLanguageFrame.setLayout(null);
		changeLanguageFrame.setSize(300, 600);
		changeLanguageFrame.setVisible(true);
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

		// when doneButton button is clicked
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkBoxes[0].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.ASSEMBLER;
					fp.LOC = 209;
					languageField.setText("ASSEMBLER");
					changeLanguageFrame.dispose();
					return;
				} else if (checkBoxes[1].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.ADA_95;
					fp.LOC = 154;
					languageField.setText("ADA 95");
					changeLanguageFrame.dispose();
					return;
				} else if (checkBoxes[2].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.CL;
					fp.LOC = 148;
					languageField.setText("C");
					changeLanguageFrame.dispose();
					return;
				} else if (checkBoxes[3].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.CLPLUS;
					fp.LOC = 59;
					languageField.setText("C++");
					changeLanguageFrame.dispose();
					return;
				} else if (checkBoxes[4].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.CSHARP;
					fp.LOC = 58;
					languageField.setText("C#");
					changeLanguageFrame.dispose();
					return;
				} else if (checkBoxes[5].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.COBOL;
					fp.LOC = 80;
					languageField.setText("COBOL");
					changeLanguageFrame.dispose();
					return;
				} else if (checkBoxes[6].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.FORTRAN;
					fp.LOC = 90;
					languageField.setText("FORTRAN");
					changeLanguageFrame.dispose();
					return;
				} else if (checkBoxes[7].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.HTML;
					fp.LOC = 43;
					languageField.setText("HTML");
					changeLanguageFrame.dispose();
					return;
				} else if (checkBoxes[8].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.JAVA;
					languageField.setText("JAVA");
					fp.LOC = 55;
					changeLanguageFrame.dispose();
					return;
				} else if (checkBoxes[9].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.JAVASCRIPT;
					fp.LOC = 54;
					languageField.setText("JAVASCRIPT");
					changeLanguageFrame.dispose();
					return;
				} else if (checkBoxes[10].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.VBSCRIPT;
					fp.LOC = 38;
					languageField.setText("VBSCRIPT");
					changeLanguageFrame.dispose();
					return;
				} else if (checkBoxes[11].isSelected()) {
					fp.currentLanguage = FPModel.LANGUAGE.VISUAL_BASIC;
					fp.LOC = 50;
					languageField.setText("VISUAL_BASIC");
					changeLanguageFrame.dispose();
					return;
				} else {
					JOptionPane.showMessageDialog(null, "Please choose a language", "Alert", JOptionPane.ERROR_MESSAGE);
					languageField.setText("");
					fp.LOC = 0;
					System.err.println("Error");
				}

			}
		});
		// add to JFrame changeLanguageFrame
		changeLanguageFrame.add(selectLanguage);
		for (JCheckBox c : checkBoxes)
			changeLanguageFrame.add(c);
		changeLanguageFrame.add(doneButton);

	}
}