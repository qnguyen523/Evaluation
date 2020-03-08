import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.*;
public class OpenItemListener implements ActionListener{
	final private int SIMPLE = 0, AVERAGE = 1, COMPLEX = 2;
    final int EI = 0, EO = 1, EInq = 2, ILF = 3, EIF = 4;
	SaveModel saveObject;
	JTabbedPane tabPane;
	JFrame frame;
	public void setFields(SaveModel saveObject,JTabbedPane tabPane,JFrame frame) {
		this.saveObject=saveObject;
		this.tabPane=tabPane;
		this.frame=frame;
	}
	public void actionPerformed(ActionEvent e) {
		File f = new File("/Users/Peter/Documents/workspace2/Metrics-Suite/");
//		File f = new File("/");
		JFileChooser inputFile=new JFileChooser(f);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".ms", "ms");
		inputFile.setFileFilter(filter);
		inputFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		if(inputFile.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			File file = inputFile.getSelectedFile();
			try {
				FileInputStream fileIn = new FileInputStream(file);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				saveObject = (SaveModel) in.readObject();
				in.close();
				fileIn.close();
			} catch (IOException i) {
				i.printStackTrace();
				return;
			} catch (ClassNotFoundException c) {
				System.out.println("Saved data not found");
				c.printStackTrace();
				return;
			}
			
		    functionPoint();
		}
		else {
			return;
		}
		
	}
	// take care of function point
	public void functionPoint() {
		// add a new tab to current panel
		JPanel panel = new JPanel();
		tabPane.addTab("Function Points", panel);
		frame.getContentPane().add(tabPane, BorderLayout.CENTER);
		frame.setVisible(true);
		panel.setLayout(null);
		// labels
		JLabel weightingFactors = new JLabel("Weighting Factors", JLabel.CENTER);
		JLabel simple = new JLabel("Simple   Average   Complex", JLabel.CENTER);

		// setbounds
		weightingFactors.setBounds(0,10,800,20);
		simple.setBounds(0,30,800,20);

		// add to panel
		panel.add(weightingFactors);
		panel.add(simple);
		
		// test
		System.out.println(saveObject.id[EI].complexity_value);
		
		saveObject.id[EI].label = new JLabel("External Inputs");
		saveObject.id[EO].label = new JLabel("External Outputs");
		saveObject.id[EInq].label = new JLabel("External Inquiries");
		saveObject.id[ILF].label = new JLabel("Internal Logical Files");
		saveObject.id[EIF].label = new JLabel("External Interface Files");
		
		int hold = saveObject.id[EI].complexity_value;
		saveObject.id[EI].radioButtons[SIMPLE] = new JRadioButton("3",3==hold? true : false);
		saveObject.id[EI].radioButtons[AVERAGE] = new JRadioButton("4", 4==hold? true : false);
		saveObject.id[EI].radioButtons[COMPLEX] = new JRadioButton("6",6==hold? true : false);
		
		hold = saveObject.id[EO].complexity_value;
		saveObject.id[EO].radioButtons[SIMPLE] = new JRadioButton("4",4==hold? true : false);
		saveObject.id[EO].radioButtons[AVERAGE] = new JRadioButton("5", 5==hold? true : false);
		saveObject.id[EO].radioButtons[COMPLEX] = new JRadioButton("7",7==hold? true : false);
		
		hold = saveObject.id[EInq].complexity_value;
		saveObject.id[EInq].radioButtons[SIMPLE] = new JRadioButton("3",3==hold? true : false);
		saveObject.id[EInq].radioButtons[AVERAGE] = new JRadioButton("4", 4==hold? true : false);
		saveObject.id[EInq].radioButtons[COMPLEX] = new JRadioButton("6",6==hold? true : false);
		
		hold = saveObject.id[ILF].complexity_value;
		saveObject.id[ILF].radioButtons[SIMPLE] = new JRadioButton("7",7==hold? true : false);
		saveObject.id[ILF].radioButtons[AVERAGE] = new JRadioButton("10", 10==hold? true : false);
		saveObject.id[ILF].radioButtons[COMPLEX] = new JRadioButton("15",15==hold? true : false);
		
		hold = saveObject.id[EIF].complexity_value;
		saveObject.id[EIF].radioButtons[SIMPLE] = new JRadioButton("5",5==hold? true : false);
		saveObject.id[EIF].radioButtons[AVERAGE] = new JRadioButton("7", 7==hold? true : false);
		saveObject.id[EIF].radioButtons[COMPLEX] = new JRadioButton("10",10==hold? true : false);
		
		// group the radio button
		saveObject.id[EI].group = new ButtonGroup();
		saveObject.id[EI].addToGroup(saveObject.id[EI].group, saveObject.id[EI].radioButtons);
		saveObject.id[EO].group = new ButtonGroup();
		saveObject.id[EO].addToGroup(saveObject.id[EO].group, saveObject.id[EO].radioButtons);
		saveObject.id[EInq].group = new ButtonGroup();
		saveObject.id[EInq].addToGroup(saveObject.id[EInq].group, saveObject.id[EInq].radioButtons);
		saveObject.id[ILF].group = new ButtonGroup();
		saveObject.id[ILF].addToGroup(saveObject.id[ILF].group, saveObject.id[ILF].radioButtons);
		saveObject.id[EIF].group = new ButtonGroup();
		saveObject.id[EIF].addToGroup(saveObject.id[EIF].group, saveObject.id[EIF].radioButtons);

		// take care of the second half of panel
		Button compute_FP_button = new Button("Compute FP");
		Button VAF_button = new Button("Value Adjustment");
		Button compute_code_size_button = new Button("Compute Code Size");
		Button change_language_button = new Button("Change Language");
		JLabel currentLanguage = new JLabel("Current Language");
		JLabel totalCount = new JLabel("Total Count");
		
		// disable editing input
		saveObject.id[EI].input.setEditable(false);
		saveObject.id[EO].input.setEditable(false);
		saveObject.id[EInq].input.setEditable(false);
		saveObject.id[ILF].input.setEditable(false);
		saveObject.id[EIF].input.setEditable(false);
		
		// setbounds
		saveObject.id[EI].label.setBounds(10,50,180,20);
		saveObject.id[EO].label.setBounds(10,70,180,20);
		saveObject.id[EInq].label.setBounds(10,90,180,20);
		saveObject.id[ILF].label.setBounds(10,110,180,20);
		saveObject.id[EIF].label.setBounds(10,130,180,20);
		saveObject.id[EI].input.setBounds(190,50,50,20);
		saveObject.id[EO].input.setBounds(190,70,50,20);
		saveObject.id[EInq].input.setBounds(190,90,50,20);
		saveObject.id[ILF].input.setBounds(190,110,50,20);
		saveObject.id[EIF].input.setBounds(190,130,50,20);
		saveObject.id[EI].radioButtons[SIMPLE].setBounds(280,50,90,20);
		saveObject.id[EI].radioButtons[AVERAGE].setBounds(370,50,90,20);
		saveObject.id[EI].radioButtons[COMPLEX].setBounds(460,50,90,20);
		saveObject.id[EO].radioButtons[SIMPLE].setBounds(280,70,90,20);
		saveObject.id[EO].radioButtons[AVERAGE].setBounds(370,70,90,20);
		saveObject.id[EO].radioButtons[COMPLEX].setBounds(460,70,90,20);
		saveObject.id[EInq].radioButtons[SIMPLE].setBounds(280,90,90,20);
		saveObject.id[EInq].radioButtons[AVERAGE].setBounds(370,90,90,20);
		saveObject.id[EInq].radioButtons[COMPLEX].setBounds(460,90,90,20);
		saveObject.id[ILF].radioButtons[SIMPLE].setBounds(280,110,90,20);
		saveObject.id[ILF].radioButtons[AVERAGE].setBounds(370,110,90,20);
		saveObject.id[ILF].radioButtons[COMPLEX].setBounds(460,110,90,20);
		saveObject.id[EIF].radioButtons[SIMPLE].setBounds(280,130,90,20);
		saveObject.id[EIF].radioButtons[AVERAGE].setBounds(370,130,90,20);
		saveObject.id[EIF].radioButtons[COMPLEX].setBounds(460,130,90,20);
		saveObject.id[EI].output.setBounds(550,50,80,20);
		saveObject.id[EO].output.setBounds(550,70,80,20);
		saveObject.id[EInq].output.setBounds(550,90,80,20);
		saveObject.id[ILF].output.setBounds(550,110,80,20);
		saveObject.id[EIF].output.setBounds(550,130,80,20);
		totalCount.setBounds(10,160,180,20);
		saveObject.total.setBounds(550,160,80,20);
		compute_FP_button.setBounds(10,200,180,20);
		VAF_button.setBounds(10,240,180,20);
		compute_code_size_button.setBounds(10,280,180,20);
		change_language_button.setBounds(10,320,180,20);
		currentLanguage.setBounds(240,280,140,20);
		saveObject.languageField.setBounds(380,280,100,20);
		saveObject.FPField.setBounds(550,200,120,20);
		saveObject.VAFField.setBounds(550,240,80,20);
		saveObject.CodeSizeField.setBounds(550,280,120,20);
		
		// add to panel
		panel.add(saveObject.id[EI].label);
		panel.add(saveObject.id[EO].label);
		panel.add(saveObject.id[EInq].label);
		panel.add(saveObject.id[ILF].label);
		panel.add(saveObject.id[EIF].label);
		panel.add(saveObject.id[EI].input);
		panel.add(saveObject.id[EO].input);
		panel.add(saveObject.id[EInq].input);
		panel.add(saveObject.id[ILF].input);
		panel.add(saveObject.id[EIF].input);
		panel.add(saveObject.id[EI].radioButtons[SIMPLE]);
		panel.add(saveObject.id[EI].radioButtons[AVERAGE]);
		panel.add(saveObject.id[EI].radioButtons[COMPLEX]);
		panel.add(saveObject.id[EO].radioButtons[SIMPLE]);
		panel.add(saveObject.id[EO].radioButtons[AVERAGE]);
		panel.add(saveObject.id[EO].radioButtons[COMPLEX]);
		panel.add(saveObject.id[EInq].radioButtons[SIMPLE]);
		panel.add(saveObject.id[EInq].radioButtons[AVERAGE]);
		panel.add(saveObject.id[EInq].radioButtons[COMPLEX]);
		panel.add(saveObject.id[ILF].radioButtons[SIMPLE]);
		panel.add(saveObject.id[ILF].radioButtons[AVERAGE]);
		panel.add(saveObject.id[ILF].radioButtons[COMPLEX]);
		panel.add(saveObject.id[EIF].radioButtons[SIMPLE]);
		panel.add(saveObject.id[EIF].radioButtons[AVERAGE]);
		panel.add(saveObject.id[EIF].radioButtons[COMPLEX]);
		panel.add(saveObject.id[EI].output);
		panel.add(saveObject.id[EO].output);
		panel.add(saveObject.id[EInq].output);
		panel.add(saveObject.id[ILF].output);
		panel.add(saveObject.id[EIF].output);
		panel.add(currentLanguage);
		panel.add(compute_FP_button);
		panel.add(VAF_button);
		panel.add(compute_code_size_button);
		panel.add(change_language_button);
		panel.add(saveObject.languageField);
		panel.add(saveObject.FPField);
		panel.add(saveObject.VAFField);
		panel.add(saveObject.CodeSizeField);
		panel.add(totalCount);
		panel.add(saveObject.total);
		
		compute_FP_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = "Sorry. This operation is not allowed because this is a saved file";
				JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		VAF_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = "Sorry. This operation is not allowed because this is a saved file";
				JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		compute_code_size_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = "Sorry. This operation is not allowed because this is a saved file";
				JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		change_language_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = "Sorry. This operation is not allowed because this is a saved file";
				JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
}
