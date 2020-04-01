package SMI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/*
 * This class is to compute function point
 */
public class FunctionPointItemListener implements ActionListener {
	final private int SIMPLE = 0, AVERAGE = 1, COMPLEX = 2;
	final int EI = 0, EO = 1, EInq = 2, ILF = 3, EIF = 4;

	JTabbedPane tabPane;
	JTextField VAFField;// = new JTextField(2);
	VafValue vaf_total_value;// = new VafValue();
	LanguageItemListener lanItem;
	JFrame frame;
	FPModel fp;
	infomationDomain[] id;
	JTextField languageField = new JTextField(2);
	SaveModel saveObject;
	ArrayList<SaveModel> saveObjectArray;
	JPanel panel;// = new JPanel();
	SMI_Listener sl;

	int tab_index = 1;
	String tabTitle = "";
	JTable table;

	// set panel
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	// set fields
	public void setFields(LanguageItemListener lanItem, JFrame frame, FPModel fp, JTextField languageField,
			JTabbedPane tabPane, ArrayList<SaveModel> saveObjectArray, SMI_Listener sl, JTable table) {
		this.lanItem = lanItem;
		this.frame = frame;
		// this.fp=fp;
		// this.saveObject.id=this.id;
		// this.saveObject=saveObject;
		this.tabPane = tabPane;
		this.saveObjectArray = saveObjectArray;
		this.sl = sl;
		this.table = table;
	}

	// when Function Points button is clicked
	public void actionPerformed(ActionEvent e) {
		// validate before opening new fp tab
		if (saveObject != null && saveObject.CodeSizeField.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					"Fields cannot be empty before " + "opening a new FP tab. " + "Please compute before proceeding",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		vaf_total_value = new VafValue();
		fp = new FPModel();
		panel = new JPanel();
		saveObject = new SaveModel();

		languageField = new JTextField(2);
		String hold = lanItem.text;
		// System.out.println(hold);

		// testing
		if (hold.equals("")) {
			JOptionPane.showMessageDialog(null, "Please choose a language", "Alert", JOptionPane.ERROR_MESSAGE);
			System.err.println("Error");
			return;
		}

		// do not update language when change language button is clicked
		languageField.setText(hold);

		// no language is selected when opening a new tab
		if (hold.equals("ASSEMBLER")) {
			fp.currentLanguage = FPModel.LANGUAGE.ASSEMBLER;
		} else if (hold.equals("ADA 95")) {
			fp.currentLanguage = FPModel.LANGUAGE.ADA_95;
		} else if (hold.equals("C")) {
			fp.currentLanguage = FPModel.LANGUAGE.CL;
		} else if (hold.equals("C++")) {
			fp.currentLanguage = FPModel.LANGUAGE.CLPLUS;
		} else if (hold.equals("C#")) {
			fp.currentLanguage = FPModel.LANGUAGE.CSHARP;
		} else if (hold.equals("COBOL")) {
			fp.currentLanguage = FPModel.LANGUAGE.COBOL;
		} else if (hold.equals("FORTRAN")) {
			fp.currentLanguage = FPModel.LANGUAGE.FORTRAN;
		} else if (hold.equals("HTML")) {
			fp.currentLanguage = FPModel.LANGUAGE.HTML;
		} else if (hold.equals("JAVA")) {
			fp.currentLanguage = FPModel.LANGUAGE.JAVA;
		} else if (hold.equals("JAVASCRIPT")) {
			fp.currentLanguage = FPModel.LANGUAGE.JAVASCRIPT;
		} else if (hold.equals("VBSCRIPT")) {
			fp.currentLanguage = FPModel.LANGUAGE.VBSCRIPT;
		} else if (hold.equals("VISUAL BASIC")) {
			fp.currentLanguage = FPModel.LANGUAGE.VISUAL_BASIC;
		} else {
			fp.currentLanguage = FPModel.LANGUAGE.DEFAULT;
		}

		tabTitle = "Function Points " + tab_index;

		// add to last position of saveObjectArray
		tabPane.insertTab(tabTitle, null, panel, "", saveObjectArray.size());
		tabPane.setSelectedIndex(saveObjectArray.size());
		tab_index++;

		frame.getContentPane().add(tabPane, BorderLayout.CENTER);
		frame.setVisible(true);
		panel.setLayout(null);

		// int index=tabPane.getSelectedIndex();
		// System.out.println("Tab's name:" + tabPane.getTitleAt(index));

		// labels
		JLabel weightingFactors = new JLabel("Weighting Factors", JLabel.CENTER);
		JLabel simple = new JLabel("Simple   Average   Complex", JLabel.CENTER);

		// set bounds
		weightingFactors.setBounds(0, 10, 800, 20);
		simple.setBounds(0, 30, 800, 20);

		// add to panel
		panel.add(weightingFactors);
		panel.add(simple);

		functionPoint(panel);
	}

	public void functionPoint(JPanel panel) {
		id = new infomationDomain[5];
		// objects
		id[EI] = new infomationDomain(4);
		id[EO] = new infomationDomain(5);
		id[EInq] = new infomationDomain(4);
		id[ILF] = new infomationDomain(10);
		id[EIF] = new infomationDomain(7);
		// labels
		id[EI].label = new JLabel("External Inputs");
		id[EO].label = new JLabel("External Outputs");
		id[EInq].label = new JLabel("External Inquiries");
		id[ILF].label = new JLabel("Internal Logical Files");
		id[EIF].label = new JLabel("External Interface Files");
		// JTextFields
		id[EI].input = new JTextField(2);
		id[EO].input = new JTextField(2);
		id[EInq].input = new JTextField(2);
		id[ILF].input = new JTextField(2);
		id[EIF].input = new JTextField(2);
		// JRadioButton
		id[EI].radioButtons[SIMPLE] = new JRadioButton("3");
		id[EI].radioButtons[AVERAGE] = new JRadioButton("4", true);
		id[EI].radioButtons[COMPLEX] = new JRadioButton("6");

		id[EO].radioButtons[SIMPLE] = new JRadioButton("4");
		id[EO].radioButtons[AVERAGE] = new JRadioButton("5", true);
		id[EO].radioButtons[COMPLEX] = new JRadioButton("7");

		id[EInq].radioButtons[SIMPLE] = new JRadioButton("3");
		id[EInq].radioButtons[AVERAGE] = new JRadioButton("4", true);
		id[EInq].radioButtons[COMPLEX] = new JRadioButton("6");

		id[ILF].radioButtons[SIMPLE] = new JRadioButton("7");
		id[ILF].radioButtons[AVERAGE] = new JRadioButton("10", true);
		id[ILF].radioButtons[COMPLEX] = new JRadioButton("15");

		id[EIF].radioButtons[SIMPLE] = new JRadioButton("5");
		id[EIF].radioButtons[AVERAGE] = new JRadioButton("7", true);
		id[EIF].radioButtons[COMPLEX] = new JRadioButton("10");

		// group the radio button
		id[EI].group = new ButtonGroup();
		id[EI].addToGroup(id[EI].group, id[EI].radioButtons);

		id[EO].group = new ButtonGroup();
		id[EO].addToGroup(id[EO].group, id[EO].radioButtons);

		id[EInq].group = new ButtonGroup();
		id[EInq].addToGroup(id[EInq].group, id[EInq].radioButtons);

		id[ILF].group = new ButtonGroup();
		id[ILF].addToGroup(id[ILF].group, id[ILF].radioButtons);

		id[EIF].group = new ButtonGroup();
		id[EIF].addToGroup(id[EIF].group, id[EIF].radioButtons);

		// output
		id[EI].output = new JTextField(2);
		id[EI].output.setEditable(false);
		id[EO].output = new JTextField(2);
		id[EO].output.setEditable(false);
		id[EInq].output = new JTextField(2);
		id[EInq].output.setEditable(false);
		id[ILF].output = new JTextField(2);
		id[ILF].output.setEditable(false);
		id[EIF].output = new JTextField(2);
		id[EIF].output.setEditable(false);

		// the second half of panel
		Button compute_FP_button = new Button("Compute FP");
		Button VAF_button = new Button("Value Adjustment");
		Button compute_code_size_button = new Button("Compute Code Size");
		Button change_language_button = new Button("Change Language");
		JLabel currentLanguage = new JLabel("Current Language");
		JLabel totalCount = new JLabel("Total Count");
		JTextField total = new JTextField(2);
		total.setEditable(false);
		languageField.setEditable(false);
		JTextField FPField = new JTextField(2);
		FPField.setEditable(false);
		// reset vaf value to 0 when opening new tab
		VAFField = new JTextField("0", 2);
		// vaf_total_value.value = 0; // not necessary

		VAFField.setEditable(false);
		JTextField CodeSizeField = new JTextField(2);
		CodeSizeField.setEditable(false);

		// setbounds
		id[EI].label.setBounds(10, 50, 180, 20);
		id[EO].label.setBounds(10, 70, 180, 20);
		id[EInq].label.setBounds(10, 90, 180, 20);
		id[ILF].label.setBounds(10, 110, 180, 20);
		id[EIF].label.setBounds(10, 130, 180, 20);
		id[EI].input.setBounds(190, 50, 50, 20);
		id[EO].input.setBounds(190, 70, 50, 20);
		id[EInq].input.setBounds(190, 90, 50, 20);
		id[ILF].input.setBounds(190, 110, 50, 20);
		id[EIF].input.setBounds(190, 130, 50, 20);
		id[EI].radioButtons[SIMPLE].setBounds(280, 50, 90, 20);
		id[EI].radioButtons[AVERAGE].setBounds(370, 50, 90, 20);
		id[EI].radioButtons[COMPLEX].setBounds(460, 50, 90, 20);
		id[EO].radioButtons[SIMPLE].setBounds(280, 70, 90, 20);
		id[EO].radioButtons[AVERAGE].setBounds(370, 70, 90, 20);
		id[EO].radioButtons[COMPLEX].setBounds(460, 70, 90, 20);
		id[EInq].radioButtons[SIMPLE].setBounds(280, 90, 90, 20);
		id[EInq].radioButtons[AVERAGE].setBounds(370, 90, 90, 20);
		id[EInq].radioButtons[COMPLEX].setBounds(460, 90, 90, 20);
		id[ILF].radioButtons[SIMPLE].setBounds(280, 110, 90, 20);
		id[ILF].radioButtons[AVERAGE].setBounds(370, 110, 90, 20);
		id[ILF].radioButtons[COMPLEX].setBounds(460, 110, 90, 20);
		id[EIF].radioButtons[SIMPLE].setBounds(280, 130, 90, 20);
		id[EIF].radioButtons[AVERAGE].setBounds(370, 130, 90, 20);
		id[EIF].radioButtons[COMPLEX].setBounds(460, 130, 90, 20);
		id[EI].output.setBounds(550, 50, 80, 20);
		id[EO].output.setBounds(550, 70, 80, 20);
		id[EInq].output.setBounds(550, 90, 80, 20);
		id[ILF].output.setBounds(550, 110, 80, 20);
		id[EIF].output.setBounds(550, 130, 80, 20);
		totalCount.setBounds(10, 160, 180, 20);
		total.setBounds(550, 160, 80, 20);
		compute_FP_button.setBounds(10, 200, 180, 20);
		VAF_button.setBounds(10, 240, 180, 20);
		compute_code_size_button.setBounds(10, 280, 180, 20);
		change_language_button.setBounds(10, 320, 180, 20);
		currentLanguage.setBounds(240, 280, 140, 20);
		languageField.setBounds(380, 280, 100, 20);
		FPField.setBounds(550, 200, 120, 20);
		VAFField.setBounds(550, 240, 80, 20);
		CodeSizeField.setBounds(550, 280, 120, 20);

		// add to panel
		panel.add(id[EI].label);
		panel.add(id[EO].label);
		panel.add(id[EInq].label);
		panel.add(id[ILF].label);
		panel.add(id[EIF].label);
		panel.add(id[EI].input);
		panel.add(id[EO].input);
		panel.add(id[EInq].input);
		panel.add(id[ILF].input);
		panel.add(id[EIF].input);
		panel.add(id[EI].radioButtons[SIMPLE]);
		panel.add(id[EI].radioButtons[AVERAGE]);
		panel.add(id[EI].radioButtons[COMPLEX]);
		panel.add(id[EO].radioButtons[SIMPLE]);
		panel.add(id[EO].radioButtons[AVERAGE]);
		panel.add(id[EO].radioButtons[COMPLEX]);
		panel.add(id[EInq].radioButtons[SIMPLE]);
		panel.add(id[EInq].radioButtons[AVERAGE]);
		panel.add(id[EInq].radioButtons[COMPLEX]);
		panel.add(id[ILF].radioButtons[SIMPLE]);
		panel.add(id[ILF].radioButtons[AVERAGE]);
		panel.add(id[ILF].radioButtons[COMPLEX]);
		panel.add(id[EIF].radioButtons[SIMPLE]);
		panel.add(id[EIF].radioButtons[AVERAGE]);
		panel.add(id[EIF].radioButtons[COMPLEX]);
		panel.add(id[EI].output);
		panel.add(id[EO].output);
		panel.add(id[EInq].output);
		panel.add(id[ILF].output);
		panel.add(id[EIF].output);
		panel.add(currentLanguage);
		panel.add(compute_FP_button);
		panel.add(VAF_button);
		panel.add(compute_code_size_button);
		panel.add(change_language_button);
		panel.add(languageField);
		panel.add(FPField);
		panel.add(VAFField);
		panel.add(CodeSizeField);
		panel.add(totalCount);
		panel.add(total);

		// change language
		ChangeLanguageItemListener changeLanItem = new ChangeLanguageItemListener();
		changeLanItem.setFields(fp, languageField);
		change_language_button.addActionListener(changeLanItem);

		// vaf frame
		int[] vaf_array = new int[14];
		for (int i : vaf_array)
			i = 0;
		vafActionListener vafItem = new vafActionListener();
		vafItem.setFields(vaf_total_value, VAFField, vaf_array);
		VAF_button.addActionListener(vafItem);

		// compute fp
		ComputeFP fpItem = new ComputeFP();
		fpItem.setFields(fp, id, total, VAFField, vaf_total_value, FPField);
		compute_FP_button.addActionListener(fpItem);

		// compute size
		System.out.println("New Computse Size in FPItemListener");
		ComputeSize sizeItem = new ComputeSize();
		sizeItem.setFields(fp, CodeSizeField, saveObject, saveObjectArray);
		compute_code_size_button.addActionListener(sizeItem);

		// save fields
		saveObject.id = id;
		saveObject.languageField = languageField;
		saveObject.total = total;
		saveObject.FPField = FPField;
		saveObject.VAFField = VAFField;
		saveObject.CodeSizeField = CodeSizeField;
		saveObject.vaf_array = vaf_array;
		saveObject.fp = fp;
		saveObject.tabTitle = tabTitle;

		// validate before opening smi tab
		sl.setSaveObject(saveObject);
	}
}