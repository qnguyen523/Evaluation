package SMI;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AddRow implements ActionListener {
	DefaultTableModel model;
	JTable table;

	public void setField(DefaultTableModel model, JTable table) {
		this.model = model;
		this.table = table;
	}

	public void actionPerformed(ActionEvent e) {
		int numOfRows = table.getRowCount();
		if (numOfRows < 0) {
			System.err.println("Error");
		} else if (numOfRows == 0) {
			model.addRow(new String[] { "", "", "", "", "" });
		} else {
			// > 0
			String hold = (String) table.getValueAt(numOfRows - 1, 0);
			if (!hold.isEmpty()) {
				model.addRow(new String[] { "", "", "", "", "" });
			} else {
				// error
				JOptionPane.showMessageDialog(null, "Please compute before proceed", "Alert",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
