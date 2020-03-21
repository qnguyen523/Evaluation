import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ComputeIndex implements ActionListener {
	int lastTotal = 0;
	JTable table;
	SMI smi;
	ArrayList<SMI> list;
	NumberOfRows number_of_rows_when_opening;
	NumberOfRows number_of_rows_when_saving;
	// exit
	public void setNumberOfRows
	(NumberOfRows number_of_rows_when_opening,NumberOfRows number_of_rows_when_saving) {
		this.number_of_rows_when_opening=number_of_rows_when_opening;
		this.number_of_rows_when_saving=number_of_rows_when_saving;
	}
	// set fields
	public void setFields(JTable table,SMI smi,ArrayList<SMI> list) {
		this.table=table;this.smi=smi;this.list=list;
	}
	// open
	public void setLastTotal(int lastTotal) {
		this.lastTotal=lastTotal;
	}
	public void actionPerformed(ActionEvent e) {
		SMI x = new SMI();
		int numOfRows = table.getRowCount();
		
		if (numOfRows < 1) {
			System.err.println("Error");
			JOptionPane.showMessageDialog(null, "There is nothing to compute", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (((String) table.getValueAt(numOfRows-1, 1)).isEmpty()
				|| ((String) table.getValueAt(numOfRows-1, 2)).isEmpty()
				|| ((String) table.getValueAt(numOfRows-1, 3)).isEmpty()) {
			System.err.println("Error");
			JOptionPane.showMessageDialog(null, "You must enter all fields", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (!((String) table.getValueAt(numOfRows-1, 0)).isEmpty()) {
			System.err.println("Error");
			JOptionPane.showMessageDialog(null, "You already computed", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// validate
		if (	!((String) table.getValueAt(numOfRows-1, 1)).matches("[0-9]+") 
			|| 	!((String) table.getValueAt(numOfRows-1, 2)).matches("[0-9]+") 
			||	!((String) table.getValueAt(numOfRows-1, 3)).matches("[0-9]+")	) {
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.err.println("Error from ComputeIndex. "
					+ "Users do not input non-negative numbers in the fields");
			JOptionPane.showMessageDialog(null, "Please input non-negative numbers in the fields", 
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		x.added=Integer.parseInt((String) table.getValueAt(numOfRows-1, 1));
		x.changed=Integer.parseInt((String) table.getValueAt(numOfRows-1, 2));
		x.deleted=Integer.parseInt((String) table.getValueAt(numOfRows-1, 3));
		
		// validate
		if (x.added<0 || x.changed<0 || x.deleted<0) {
			System.err.println("Error from ComputeIndex. "
					+ "You must enter non-negative number in the fields");
			JOptionPane.showMessageDialog(null, "You must enter non-negative number in the fields", 
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		table.setValueAt(x.total(x,lastTotal)+"", numOfRows-1, 4);
		table.setValueAt(x.smi(x)+"", numOfRows-1, 0);
		lastTotal=x.currentTotal;
		
		// save
		number_of_rows_when_saving.num++;
		smi = x;
		list.add(smi);
		System.out.println("In ComputeIndex:\n"+ list);
		System.out.println(number_of_rows_when_opening);
		System.out.println(number_of_rows_when_saving);
	}
}
