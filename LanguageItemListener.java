import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LanguageItemListener implements ActionListener  {
	FPModel fp;
	String text;
	public void setField(FPModel fp, String text) {
		this.fp=fp;this.text=text;
	}
    public void actionPerformed(ActionEvent e) { 
    	JFrame newProjectFrame=new JFrame("Choose a language");
    	newProjectFrame.setLayout(null);  
    	newProjectFrame.setSize(300,600);
    	newProjectFrame.setVisible(true);
    	// somebody needs to continue here
    	// select one language
    	JLabel selectLanguage = new JLabel("Select one language", JLabel.CENTER);        	
    	selectLanguage.setBounds(30,20,200,20);
    	// Check boxes
    	JCheckBox[] checkBoxes = new JCheckBox[12];
    	
    	// Assembly
    	checkBoxes[0] = new JCheckBox("Assembler");
    	checkBoxes[0].setBounds(50,50,200,20);
    	// Ada 95
    	checkBoxes[1] = new JCheckBox("Ada 95");
    	checkBoxes[1].setBounds(50,70,200,20);
		// C
    	checkBoxes[2] = new JCheckBox("C");
    	checkBoxes[2].setBounds(50,90,200,20);
    	// C++
    	checkBoxes[3] = new JCheckBox("C++");
    	checkBoxes[3].setBounds(50,110,200,20);
    	// C#
    	checkBoxes[4] = new JCheckBox("C#");
    	checkBoxes[4].setBounds(50,130,200,20);
    	// COBOL
    	checkBoxes[5] = new JCheckBox("COBOL");
    	checkBoxes[5].setBounds(50,150,200,20);
    	// Fortran
    	checkBoxes[6] = new JCheckBox("FORTRAN+");
    	checkBoxes[6].setBounds(50,170,200,20);
    	// HTML
    	checkBoxes[7] = new JCheckBox("HTML");
    	checkBoxes[7].setBounds(50,190,200,20);        	
    	// Java
    	checkBoxes[8] = new JCheckBox("Java");
    	checkBoxes[8].setBounds(50,210,200,20);
    	// JavaScript
    	checkBoxes[9] = new JCheckBox("JavaScript");
    	checkBoxes[9].setBounds(50,230,200,20);
    	// VBScript
    	checkBoxes[10] = new JCheckBox("VBScript");
    	checkBoxes[10].setBounds(50,250,200,20);
    	// Visual Basic
    	checkBoxes[11] = new JCheckBox("Visual Basic");
    	checkBoxes[11].setBounds(50,270,200,20);
    	// Done button
    	Button doneButton = new Button("DONE");
    	doneButton.setBounds(50,300,100,20);
    	
    	// group checkboxes
    	ButtonGroup checkBoxesGroup = new ButtonGroup();
    	for (JCheckBox c : checkBoxes) checkBoxesGroup.add(c); 
    	
    	doneButton.addActionListener(new ActionListener() {
    		@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
    			if (checkBoxes[0].isSelected()) {
    				fp.currentLanguge = FPModel.LANGUAGE.ASSEMBLER;
					text = "ASSEMBLER";
    	    		newProjectFrame.dispose();
    	    		return;
    			}
    			else if (checkBoxes[1].isSelected())  {
    				fp.currentLanguge = FPModel.LANGUAGE.ADA_95;
					text = "ADA 95";
    	    		newProjectFrame.dispose();
    	    		return;
    			}
    			else if (checkBoxes[2].isSelected())  {
    				fp.currentLanguge = FPModel.LANGUAGE.CL;
					text = "C";
    	    		newProjectFrame.dispose();
    	    		return;
    			}
    			else if (checkBoxes[3].isSelected())  {
    				fp.currentLanguge = FPModel.LANGUAGE.CLPLUS;
					text = "C++";
    	    		newProjectFrame.dispose();
    	    		return;
    			}
    			else if (checkBoxes[4].isSelected())  {
    				fp.currentLanguge = FPModel.LANGUAGE.CSHARP;
					text = "C#";
    	    		newProjectFrame.dispose();
    	    		return;
    			}
    			else if (checkBoxes[5].isSelected())  {
    				fp.currentLanguge = FPModel.LANGUAGE.COBOL;
					text = "COBOL";
    	    		newProjectFrame.dispose();
    	    		return;
    			}
    			else if (checkBoxes[6].isSelected())  {
    				fp.currentLanguge = FPModel.LANGUAGE.FORTRAN;
					text = "FORTRAN";
    	    		newProjectFrame.dispose();
    	    		return;
    			}
    			else if (checkBoxes[7].isSelected())  {
    				fp.currentLanguge = FPModel.LANGUAGE.HTML;
					text = "HTML";
    	    		newProjectFrame.dispose();
    	    		return;
    			}
    			else if (checkBoxes[8].isSelected())  {
    				fp.currentLanguge = FPModel.LANGUAGE.JAVA;
					text = "JAVA";
    	    		newProjectFrame.dispose();
    	    		return;
    			}
    			else if (checkBoxes[9].isSelected())  {
    				fp.currentLanguge = FPModel.LANGUAGE.JAVASCRIPT;
					text = "JAVASCRIPT";
    	    		newProjectFrame.dispose();
    	    		return;
    			}
    			else if (checkBoxes[10].isSelected())  {
    				fp.currentLanguge = FPModel.LANGUAGE.VBSCRIPT;
					text = "VBSCRIPT";
    	    		newProjectFrame.dispose();
    	    		return;
    			}
    			else if (checkBoxes[11].isSelected())  {
    				fp.currentLanguge = FPModel.LANGUAGE.VISUAL_BASIC;
					text = "VISUAL BASIC";
    	    		newProjectFrame.dispose();
    	    		return;
    			}
    			else {
    				System.err.println("Error");
    			}
    			
    		}
    	});
		// add to JFrame newProjectFrame
    	newProjectFrame.add(selectLanguage);
    	for (JCheckBox c : checkBoxes) newProjectFrame.add(c);
    	newProjectFrame.add(doneButton);
    }
}