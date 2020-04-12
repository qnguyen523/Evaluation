package SMI;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import SMI.MenuControl.TreePopup;

public class SMI_Listener implements ActionListener {
	JFrame frame;
	JTabbedPane tabPane;
	ArrayList<SMI> SMI_list;
	public Map<String, String> file_map;
	SaveItemListener saveItem;
	DefaultTableModel model;
	JTable table;
	JPanel panel;
	JScrollPane sp;
	Button addRow;
	Button computeIndex;
	ProjectInfoModel projectInfo;
	SaveModel lastObject;
	NumberOfRows number_of_rows_when_opening;
	NumberOfRows number_of_rows_when_saving;

	JTree jt;
	TreePopup treePopup;
	// exit
	public void setTable(JTable table, DefaultTableModel model) {
		this.table = table;
		this.model = model;
	}
	// exit
	public void SetNumberOfRows(NumberOfRows number_of_rows_when_opening, NumberOfRows number_of_rows_when_saving) {
		this.number_of_rows_when_opening = number_of_rows_when_opening;
		this.number_of_rows_when_saving = number_of_rows_when_saving;
	}
	// set last saveObject
	public void setSaveObject(SaveModel saveObject) {
		this.lastObject = saveObject;
	}

	// set fields
	public void setFields(JFrame frame, JTabbedPane tabPane, ArrayList<SMI> SMI_list,
			Map<String, String> file_map, SaveItemListener saveItem,
			DefaultTableModel model, JTable table, JPanel panel, JScrollPane sp,
			ProjectInfoModel projectInfo,JTree jt,TreePopup treePopup) {
		this.frame = frame;
		this.tabPane = tabPane;
		this.saveItem = saveItem;
		
		this.SMI_list = SMI_list;
		this.file_map=file_map;
		
		this.model = model;
		this.table = table;
		this.panel = panel;
		this.sp = sp;
//		this.addRow = addRow;
//		this.computeIndex = computeIndex;
		this.projectInfo = projectInfo;
		this.jt=jt;
		this.treePopup=treePopup;
	}

	public void actionPerformed(ActionEvent e) {
//		// add table
//				String[] header = { "SMI", "SMI Added", "SMI Changed", "SMI Deleted", "Total Modules" };
//				String[][] rec = {
//
//				};
//				model = new DefaultTableModel(rec, header) {
//					@Override
//					public boolean isCellEditable(int row, int column) {
//						if (row == this.getRowCount() - 1 && (column == 1 || column == 2 || column == 3))
//							return true;
//						else
//							return false;
//					}
//				};
//				table = new JTable(model);
//				sp = new JScrollPane(table);
//				saveItem.table=table;
//				saveItem.model=model;
//				menuControl.table=table;
//				menuControl.model=model;
		
		// a project name must be input before proceeding
		String hold = projectInfo.newProjectText.getText();
		// testing
		if (hold.equals("") || hold.equals("Project Name cannot be empty")) {
			System.err.println("Error. Project Name cannot be empty");
			JOptionPane.showMessageDialog(null, "Please enter your project name", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// validate before opening smi tab
		System.out.println("In SMI_Listener: " + lastObject);
		if (lastObject != null && lastObject.CodeSizeField.getText().equals("")) {
			System.err.println(
					"Fields cannot be empty before " + "opening a new SMI tab. " + "Please compute before proceeding");
			JOptionPane.showMessageDialog(null, "Fields cannot be empty before " + "opening a new SMI tab. "
					+ "Please compute size before proceeding", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

//		// validate if the panel has been opened
//		if (model.getRowCount() != 0) {
//			JOptionPane.showMessageDialog(null, "A SMI panel has been opened", "Error", JOptionPane.ERROR_MESSAGE);
//			return;
//		}
		// add tree node to the root
		DefaultMutableTreeNode node = new DefaultMutableTreeNode("SMI");
		DefaultTreeModel treeModel = (DefaultTreeModel)jt.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)treeModel.getRoot();
		TreePath path = null;
		int row = (path == null ? 0 : jt.getRowForPath(path));
		path = jt.getNextMatch("SMI", row, Position.Bias.Forward);
		if (path != null || file_map.containsKey("SMI")) {
			JOptionPane.showMessageDialog(frame, "Error. Only one SMI panel is allowed", 
					"Error", JOptionPane.ERROR_MESSAGE);
			System.err.println("Cannot add another SMI panel");
			return;
		}
		root.add(node);
		treeModel.reload();
		// smi panel
		panel = new JPanel();
		panel.setLayout(null);
		tabPane.addTab("SMI", panel);
//		frame.getContentPane().add(tabPane, BorderLayout.CENTER);
		panel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Software Maturity Index"));
		sp.setPreferredSize(new Dimension(600, 400));
		table.setGridColor(Color.BLACK);
		// buttons
		SMI last_smi = new SMI();
		addRow = new Button("Add Row");
		computeIndex = new Button("Compute Index");

		AddRow ar = new AddRow();
		ar.setField(model, table);
		addRow.addActionListener(ar);

		ComputeIndex ci = new ComputeIndex();
		ci.setFields(table, last_smi, SMI_list);
		ci.setNumberOfRows(number_of_rows_when_opening, number_of_rows_when_saving);
		computeIndex.addActionListener(ci);
		// set bounds
		sp.setBounds(50,20,600,400);
		addRow.setBounds(200,440,150,20);
		computeIndex.setBounds(400,440,150,20);
		// add to panel
		panel.add(sp);
		panel.add(addRow);
		panel.add(computeIndex);
		// put into map
		file_map.put("SMI", "SMI");
		
		// finalize frame
		SwingUtilities.updateComponentTreeUI(frame);
		// JTree
//		treePopup.popup_saving_list.SMI_list = SMI_list;
//		saveItem.saving_list = treePopup.popup_saving_list;
	}
}
