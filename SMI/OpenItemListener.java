package SMI;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import ANTLR.JavaJavaLexer;
import ANTLR.JavaJavaParser;
import ANTLR.AddCodeListener;
import ANTLR.AddCodeListener.MyPanel;

import java.util.*;

/*
 * This class is to open file to load a new tab
 */
public class OpenItemListener implements ActionListener {
	final private int SIMPLE = 0, AVERAGE = 1, COMPLEX = 2;
	final int EI = 0, EO = 1, EInq = 2, ILF = 3, EIF = 4;
	// SaveModel saveObject;
	JTabbedPane tabPane;
	JFrame frame;
	ArrayList<SaveModel> saveObjectArray;
	SavingList saving_list;
	ArrayList<SMI> SMI_list;
	DefaultTableModel model;
	JTable table;
	Button addRow;// = new Button("Add Row");
	Button computeIndex;// = new Button("Compute Index");
	AddRow ar;// = new AddRow();
	ComputeIndex ci;// = new ComputeIndex();
	SaveItemListener saveItem;// = new SaveItemListener();
	NumberOfRows number_of_rows_when_opening;
	NumberOfRows number_of_rows_when_saving;
	ProjectInfoModel projectInfo;
	SMI_Listener sl;// = new SMI_Listener();
	IsOpen open;
	FunctionPointItemListener fpItem;
	ComputeSizeFromSave sizeItem;
	JMenu metrics;
	LanguageItemListener lanItem;
	ArrayList<String> file_names;
	public JMenu project_code;
	AddCodeListener add_code_listener;
	// open panels of statistics
	ANTLR.AddCodeListener.MyPanel[] my_panels;
	// JTree
	JTree jt;
	MenuControl menuControl;
	MenuControl.TreePopup treePopup;
	// save
	public void set_SMI_Listener(SMI_Listener sl) {
		this.sl = sl;
	}

	// exit
	public void SetNumberOfRows(NumberOfRows number_of_rows_when_opening, NumberOfRows number_of_rows_when_saving) {
		this.number_of_rows_when_opening = number_of_rows_when_opening;
		this.number_of_rows_when_saving = number_of_rows_when_saving;
	}

	// set fields
	public void setFields(SavingList saving_list, JTabbedPane tabPane, JFrame frame, DefaultTableModel model, 
			SaveItemListener saveItem, JTable table,
			ProjectInfoModel projectInfo, IsOpen open, FunctionPointItemListener fpItem, JMenu metrics,
			LanguageItemListener lanItem,JMenu project_code,AddCodeListener add_code_listener,JTree jt,
			MenuControl.TreePopup treePopup,MenuControl menuControl) {
		// this.saveObject=saveObject;
		this.tabPane = tabPane;
		this.frame = frame;
		this.saving_list = saving_list;
		this.model = model;
//		this.addRow = addRow;
//		this.computeIndex = computeIndex;
//		this.ar = ar;
//		this.ci = ci;
		ar = new AddRow();
		ci = new ComputeIndex();
		addRow = new Button("Add Row");
		computeIndex = new Button("Compute Index");
		
		this.saveItem = saveItem;
		this.table = table;
		this.projectInfo = projectInfo;
		this.open = open;
		this.fpItem = fpItem;
		this.metrics = metrics;
		this.lanItem = lanItem;
		this.project_code=project_code;
		this.add_code_listener=add_code_listener;
		this.jt=jt;
		this.treePopup=treePopup;
		this.menuControl=menuControl;
	}

	// create a row
	public void addRow(DefaultTableModel model, SMI x) {
		model.addRow(new String[] { x.smi + "", x.added + "", x.changed + "", x.deleted + "", x.currentTotal + "" });

	}

	// when open button is clicked
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {

		// for testing purpose
		File f = new File("/Users/Peter/Documents/workspace2/Metrics-Suite/");
		// File f = new File("/");

		JFileChooser inputFile = new JFileChooser(f);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".ms", "ms");
		inputFile.setFileFilter(filter);
		inputFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		if (inputFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			// validate
			// System.out.println(saving_list.SMI_list.size());
			if (!saving_list.SMI_list.isEmpty()) {
				System.err.println("You already opened a project. You cannot open two projects " + "at the same time.");
				JOptionPane.showMessageDialog(null,
						"You already opened a project. " + "You cannot open two projects " + "at the same time.",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			File file = inputFile.getSelectedFile();
			try {
				// testing to get title
				System.out.println(file.getName());
				String s = file.getName();
				String title = frame.getTitle() + " - " + file.getName().split("\\.")[0];
				frame.setTitle(title);
				frame.setVisible(true);
				// set name to root
				DefaultTreeModel treeModel = (DefaultTreeModel)jt.getModel();
				DefaultMutableTreeNode root = (DefaultMutableTreeNode)treeModel.getRoot();
				root.setUserObject(file.getName().split("\\.")[0]);
				treeModel.reload();
				// open file
				FileInputStream fileIn = new FileInputStream(file);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				SavingList temp_saving_list = (SavingList) in.readObject();
				saveItem.setSavingList(temp_saving_list);

				file_names = temp_saving_list.file_names;
//				System.out.println("file_names:" + file_names);
				
				this.saving_list = temp_saving_list;
				fpItem.saveObjectArray = temp_saving_list.saveObjectArray;
				fpItem.tab_index = fpItem.saveObjectArray.size() + 1;
				this.saving_list.saveObjectArray = fpItem.saveObjectArray;
				this.projectInfo = temp_saving_list.projectInfo;
				lanItem.projectInfo = this.projectInfo;
				sl.projectInfo = this.projectInfo;
				saveItem.projectInfo = this.projectInfo;
				ci.list = temp_saving_list.SMI_list;
				treePopup.popup_saving_list = temp_saving_list;
				fpItem.file_map = temp_saving_list.file_map;
				sl.file_map = temp_saving_list.file_map;
				add_code_listener.file_map = temp_saving_list.file_map;
				add_code_listener.file_names = temp_saving_list.file_names;
				
				in.close();
				fileIn.close();
				open.isOpen = true;
				// enable
				metrics.setEnabled(true);
				project_code.setEnabled(true);
				
//				System.err.println(saving_list.file_map);
			} catch (IOException i) {
				i.printStackTrace();
				return;
			} catch (ClassNotFoundException c) {
				System.out.println("Saved data is not found");
				c.printStackTrace();
				return;
			}

			// take care of fp panels
			saveObjectArray = new ArrayList<SaveModel>();
			saveObjectArray = (ArrayList<SaveModel>) saving_list.saveObjectArray.clone();
//			System.out.println("In OpenItem " + saveObjectArray);
//			System.out.println(this.saving_list.saveObjectArray);
			if (saveObjectArray == null) {
				System.out.println("Error with saveObjectArray");
				return;
			}
			for (SaveModel i : saving_list.saveObjectArray) {
				functionPoint(i,tabPane);
				// add node to root
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(i.tabTitle);
				DefaultTreeModel treeModel = (DefaultTreeModel)jt.getModel();
				DefaultMutableTreeNode root = (DefaultMutableTreeNode)treeModel.getRoot();
				root.add(node);
				treeModel.reload();
			}
			// take care of table
			SMI_list = saving_list.SMI_list;
			// initialize for exit
			number_of_rows_when_opening.num = number_of_rows_when_saving.num = SMI_list.size();
			// validate
			if (SMI_list == null) {
				System.err.println("Error with SMI_List");
				return;
			}
			if (!SMI_list.isEmpty()) {
				JPanel panel = new JPanel();
				panel.setLayout(null);
				tabPane.addTab("SMI", panel);
				// add node to root
				DefaultMutableTreeNode node = new DefaultMutableTreeNode("SMI");
				DefaultTreeModel treeModel = (DefaultTreeModel)jt.getModel();
				DefaultMutableTreeNode root = (DefaultMutableTreeNode)treeModel.getRoot();
				root.add(node);
				treeModel.reload();
				// update ui
				SwingUtilities.updateComponentTreeUI(frame);
				
				String[] header = { "SMI", "SMI Added", "SMI Changed", "SMI Deleted", "Total Modules" };
				String[][] rec = {};
				panel.setBorder(
						BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Software Maturity Index"));
				model = new DefaultTableModel(rec, header) {
					@Override
					public boolean isCellEditable(int row, int column) {
						if (row == this.getRowCount() - 1 && (column == 1 || column == 2 || column == 3))
							return true;
						else
							return false;
					}
				};
				JTable table = new JTable(model);
				// add to JTable
				for (SMI x : SMI_list) {
					addRow(model, x);
				}
				saveItem.setTable(table, model);
				sl.setTable(table, model);
				JScrollPane sp = new JScrollPane(table);
				// buttons
				SMI last_smi = new SMI();
				ar.setField(model, table);
				ci.setFields(table, last_smi, SMI_list);
				ci.setNumberOfRows(number_of_rows_when_opening, number_of_rows_when_saving);
				addRow.addActionListener(ar);
				computeIndex.addActionListener(ci);
				
				// ci.setLastTotal(lastTotal);
				if (!SMI_list.isEmpty()) {
					int lastTotal = SMI_list.get(SMI_list.size() - 1).currentTotal;
					ci.setLastTotal(lastTotal);
				}
				
				sp.setPreferredSize(new Dimension(600, 400));
				table.setGridColor(Color.RED);
				// set bounds
				sp.setBounds(50,20,600,400);
				addRow.setBounds(200,440,150,20);
				computeIndex.setBounds(400,440,150,20);
				// add to panel
				panel.add(sp);
				panel.add(addRow);
				panel.add(computeIndex);
			}
			
			// take care of code statistics
			if (!file_names.isEmpty()) {
				my_panels = new ANTLR.AddCodeListener.MyPanel[file_names.size()];
				for (int i=0; i<file_names.size(); i++) {
					File F = new File(file_names.get(i));
					my_panels[i] = add_code_listener.new MyPanel();
					my_panels[i].panel = new JPanel();
					my_panels[i].sb = new StringBuilder();
					add_code_listener.new Statistics().parse(F,my_panels[i].sb,my_panels[i],tabPane,false);
					// add node to root
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(F.getName());
					DefaultTreeModel treeModel = (DefaultTreeModel)jt.getModel();
					DefaultMutableTreeNode root = (DefaultMutableTreeNode)treeModel.getRoot();
					root.add(node);
					treeModel.reload();
				}
			}
			
			// update ui
			SwingUtilities.updateComponentTreeUI(frame);
			// save file_names
			saveItem.saving_list.file_names = file_names;
			add_code_listener.file_names = file_names;
			// open the active panel
			int index = tabPane.indexOfTab(saving_list.activeTabTitle);
			tabPane.setSelectedIndex(index);
				
		} else {
			return;
		}
	}
	// take care of SMI
	public void smi(ArrayList<SMI> SMI_list, JTabbedPane tabPane) {
		JPanel panel = new JPanel();
		tabPane.addTab("SMI", panel);
		String[] header = { "SMI", "SMI Added", "SMI Changed", "SMI Deleted", "Total Modules" };
		String[][] rec = {};
		panel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Software Maturity Index"));
		model = new DefaultTableModel(rec, header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (row == this.getRowCount() - 1 && (column == 1 || column == 2 || column == 3))
					return true;
				else
					return false;
			}
		};
		JTable table = new JTable(model);

		// add to JTable
		for (SMI x : SMI_list) {
			model.addRow(new String[] { x.smi + "", x.added + "", x.changed + "", x.deleted + "", x.currentTotal + "" });
		}
		JScrollPane sp = new JScrollPane(table);
		// buttons
		SMI last_smi = new SMI();
		Button addRow = new Button("Add Row");
		Button computeIndex = new Button("Compute Index");

		AddRow ar = new AddRow();
		ar.setField(model, table);
		addRow.addActionListener(ar);
		
		ComputeIndex ci = new ComputeIndex();
		ci.setFields(table, last_smi, SMI_list);
		ci.setNumberOfRows(number_of_rows_when_opening, number_of_rows_when_saving);
		computeIndex.addActionListener(ci);

		if (!SMI_list.isEmpty()) {
			int lastTotal = SMI_list.get(SMI_list.size() - 1).currentTotal;
			ci.setLastTotal(lastTotal);
		}
		
		sp.setPreferredSize(new Dimension(600, 400));
		table.setGridColor(Color.RED);
		// set bounds
		sp.setBounds(50,20,600,400);
		addRow.setBounds(200,440,150,20);
		computeIndex.setBounds(400,440,150,20);
		// set bounds
		sp.setBounds(50,20,600,400);
		addRow.setBounds(200,440,150,20);
		computeIndex.setBounds(400,440,150,20);
		// add to panel
		panel.add(sp);
		panel.add(addRow);
		panel.add(computeIndex);
	}

	// take care of function point
	public void functionPoint(SaveModel saveObject,JTabbedPane tabPane) {
		 
		
		// add a new tab to current panel
		JPanel panel = new JPanel();
		// have title
		tabPane.addTab(saveObject.tabTitle, panel);
//		frame.getContentPane().add(tabPane, BorderLayout.CENTER);
		panel.setLayout(null);
		// labels
		JLabel weightingFactors = new JLabel("Weighting Factors", JLabel.CENTER);
		JLabel simple = new JLabel("Simple   Average   Complex", JLabel.CENTER);
		// set bounds
		weightingFactors.setBounds(0, 10, 800, 20);
		simple.setBounds(0, 30, 800, 20);
		// add to panel
		panel.add(weightingFactors);
		panel.add(simple);
		// take care of the second half of panel
		Button compute_FP_button = new Button("Compute FP");
		Button VAF_button = new Button("Value Adjustment");
		Button compute_code_size_button = new Button("Compute Code Size");
		Button change_language_button = new Button("Change Language");
		JLabel currentLanguage = new JLabel("Current Language");
		JLabel totalCount = new JLabel("Total Count");

		// setbounds
		totalCount.setBounds(10, 160, 180, 20);
		compute_FP_button.setBounds(10, 200, 180, 20);
		VAF_button.setBounds(10, 240, 180, 20);
		compute_code_size_button.setBounds(10, 280, 180, 20);
		change_language_button.setBounds(10, 320, 180, 20);
		currentLanguage.setBounds(240, 280, 140, 20);

		// add to panel
		new FunctionPointItemListener().add_id_to_panel(panel, saveObject.id);
//		fpItem.add_id_to_panel(panel, saveObject.id);
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
		// compute fp
		ComputeFP compute_fp = new ComputeFP();
		VafValue vaf_total_value = new VafValue();
		compute_fp.setFields(saveObject.fp, saveObject.id, saveObject.total, saveObject.VAFField, vaf_total_value,
				saveObject.FPField);
		compute_FP_button.addActionListener(compute_fp);
		// vaf frame
		vafActionListener vafItem = new vafActionListener();
		vafItem.setFields(vaf_total_value, saveObject.VAFField, saveObject.vaf_array);
		VAF_button.addActionListener(vafItem);
		// change language
		ChangeLanguageItemListener changeLanItem = new ChangeLanguageItemListener();
		changeLanItem.setFields(saveObject.fp, saveObject.languageField);
		change_language_button.addActionListener(changeLanItem);
		// compute size
		sizeItem = new ComputeSizeFromSave();
		int currentCodeSize = Integer.parseInt(saveObject.CodeSizeField.getText().replace(",", ""));
		sizeItem.setFields(saveObject, currentCodeSize);
		sizeItem.setTabPane(tabPane);
		compute_code_size_button.addActionListener(sizeItem);
	}
}
