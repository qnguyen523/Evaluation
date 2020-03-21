import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class SMI_Listener implements ActionListener {
	JFrame frame;
	JTabbedPane tabPane;
	SavingList saving_list;
	SaveItemListener saveItem;
	DefaultTableModel model;
	JTable table;
	JPanel panel;
	JScrollPane sp;
	Button addRow;
    Button computeIndex;
    ProjectInfoModel projectInfo;
    SaveModel saveObject;
 // exit
 	public void setTable(JTable table,DefaultTableModel model){
 		this.table=table;this.model=model;
 	}
 	// set last saveObject 
 	public void setSaveObject(SaveModel saveObject) {
 		this.saveObject=saveObject;
 	}
    // set fields
	public void setFields(JFrame frame,JTabbedPane tabPane,SavingList saving_list,
			SaveItemListener saveItem,DefaultTableModel model,JTable table,
			JPanel panel,JScrollPane sp,Button addRow,Button computeIndex,
			ProjectInfoModel projectInfo) {
		this.frame=frame;this.tabPane=tabPane;this.saving_list=saving_list;
		this.saveItem=saveItem;this.model=model;this.table=table;
		this.panel=panel;this.sp=sp;this.addRow=addRow;
		this.computeIndex=computeIndex;this.projectInfo=projectInfo;
	}
	public void actionPerformed(ActionEvent e) {
    	// a project name must be input before proceeding
    	String hold = projectInfo.newProjectText.getText();

//    	 testing
    	if(hold.equals("") || hold.equals("Project Name cannot be empty")) {
    		System.err.println("Error. Project Name cannot be empty");
    		JOptionPane.showMessageDialog(null, "Please enter your project name", "Error", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	// validate before opening smi tab
    	System.out.println("In SMI_Listener: "+saveObject);
		if (saveObject!=null && saveObject.CodeSizeField.getText().equals("")) {
			System.err.println("Fields cannot be empty before "
					+ "opening a new SMI tab. "
					+ "Please compute before proceeding");
			JOptionPane.showMessageDialog(null, "Fields cannot be empty before "
					+ "opening a new SMI tab. "
					+ "Please compute size before proceeding", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
    	
    	
		// validate if the panel has been opened
		if (model.getRowCount()!=0) {
			JOptionPane.showMessageDialog(null, "A SMI panel has been opened", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		panel = new JPanel();
		tabPane.addTab("SMI", panel);
		frame.getContentPane().add(tabPane, BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Software Maturity Index"));
		sp.setPreferredSize( new Dimension(700,400) );
		table.setGridColor(Color.BLACK);
	    panel.add(sp);
	    panel.add(addRow);
	    panel.add(computeIndex);
	    
	    // finalize frame
	    frame.setVisible(true);
	}
}
