import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LanguageItemListener implements ActionListener  {

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
    	checkBoxes[0] = new JCheckBox("Assembly");
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
    	doneButton.addActionListener(new ActionListener() {
    		@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
    			if (checkBoxes[0].isSelected())
    				System.out.println(checkBoxes[0].getText() + " is clicked");
    			else
    				System.out.println("Nothing is clicked");
    		}
    	});
		// add to JFrame newProjectFrame
    	newProjectFrame.add(selectLanguage);
    	for (JCheckBox c : checkBoxes) newProjectFrame.add(c);
    	newProjectFrame.add(doneButton);
    }

}
