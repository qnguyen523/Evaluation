package SMI;
import java.awt.*;
import ANTLR.*;
import ANTLR.AddCodeListener.Statistics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

/*
 * This class is to create a menu bar and to control every operation
 */
public class MenuControl {
	// menubar
	private JMenuBar menuBar;
	// JMenu
	public JMenu file, edit, preferences, metrics, project_code, help;
	// Menu items
	private JMenuItem preferences_option1, metrics_option1;
	private JMenuItem[] file_option;
	public JMenuItem add_code, statistics;
	private JFrame frame;
	private String text;
	private JMenuItem smi_item;// = new JMenuItem("SMI");
	public FPModel fp;
	// back-end
	public JTextField languageField;
	// save and open operation
	// ProjectInfoModel projectInfo;
	JTabbedPane tabPane;
	public SavingList saving_list;
	public SaveItemListener saveItem;
	DefaultTableModel model;
	JTable table;
	JScrollPane sp;
	OpenItemListener openItem;
	NewItemListener newItem;
	Button addRow;
	Button computeIndex;
	ComputeIndex ci;
	FunctionPointItemListener fpItem;
	SMI_Listener sl;
	// exit
	NumberOfRows number_of_rows_when_opening = new NumberOfRows(0);
	NumberOfRows number_of_rows_when_saving = new NumberOfRows(0);
	JPanel panel = new JPanel();
	IsOpen open = new IsOpen();
	LanguageItemListener lanItem;
	
	// JTree
	JTree jt;
	TreePopup treePopup;
	
	// constructor
	public MenuControl(String projectName) {
		saving_list = new SavingList();
		// JTree
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("...");
		jt=new JTree(root);  
		JScrollPane tree_sp = new JScrollPane(jt);
		treePopup = new TreePopup(jt);
		
		treePopup.popup_saving_list=saving_list;
		jt.addMouseListener(new MouseAdapter() {
			public void mouseReleased (MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				TreePath path = jt.getPathForLocation(x, y);
				int row = jt.getRowForLocation(x, y);
				if (SwingUtilities.isRightMouseButton(e)) {
					if (path == null || row == -1) {
						System.err.println("Nothing to pop up");
						return;
					}
					
					treePopup.tabPane = tabPane;
					treePopup.frame = frame;
					treePopup.tabTitle = path.getLastPathComponent().toString();
					treePopup.row = row;
					treePopup.path=path;
					treePopup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		
		// initialization
		file_option = new JMenuItem[4];
		text = new String();
		fp = new FPModel();
		languageField = new JTextField(2);
		// projectInfo = new ProjectInfoModel();
		tabPane = new JTabbedPane();

		frame = new JFrame(projectName);
		menuBar = new JMenuBar();
		// create JMenu objects
		file = new JMenu("File");
		edit = new JMenu("Edit");
		preferences = new JMenu("Preferences");
		metrics = new JMenu("Metrics");
		project_code = new JMenu("Project Code");
		help = new JMenu("Help");

		// add to menuBar
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(preferences);
		menuBar.add(metrics);
		menuBar.add(project_code);
		menuBar.add(help);

		// project_code
		saveItem = new SaveItemListener();
		add_code = new JMenuItem("Add code");
		statistics = new JMenuItem("Project code statistics");
		statistics.setEnabled(false);
		project_code.add(add_code);
		project_code.add(statistics);
		AddCodeListener add_code_listener = new AddCodeListener();
		add_code_listener.setFields(frame,tabPane,saving_list.file_names,saveItem,statistics,jt,saving_list.file_map,add_code);
		add_code.addActionListener(add_code_listener);
		
		// create menu items for file
		file_option[0] = new JMenuItem("New");
		file_option[1] = new JMenuItem("Open");
		file_option[2] = new JMenuItem("Save");
		file_option[3] = new JMenuItem("Exit");

		// add ActionListener to JMenu file_option[0]: New operation
		metrics.setEnabled(false);
		project_code.setEnabled(false);
		newItem = new NewItemListener();
		newItem.setFields(frame,saving_list.projectInfo,metrics,project_code,jt);
		file_option[0].addActionListener(newItem);

		// add ActionListener to JMenu file_option[1]: Exit operation
		ExitItemListener exitItem = new ExitItemListener();
		exitItem.setFields(frame, open);
		file_option[3].addActionListener(exitItem);

		// add to JMenu file
		file.add(file_option[0]);
		file.add(file_option[1]);
		file.add(file_option[2]);
		file.add(file_option[3]);

		// create menu items for metrics
				metrics_option1 = new JMenuItem("Function Points");
				metrics_option1.setEnabled(false);
		
		// create menu items for preferences
		preferences_option1 = new JMenuItem("Language");
//		preferences_option1.setEnabled(false);

		// could add languageField here to change language
		lanItem = new LanguageItemListener();
		lanItem.setFields(fp, text, languageField, saving_list.projectInfo);
		lanItem.metrics_option1 = metrics_option1;
		preferences_option1.addActionListener(lanItem);

		// add to preferences
		preferences.add(preferences_option1);

		// add table
		String[] header = { "SMI", "SMI Added", "SMI Changed", "SMI Deleted", "Total Modules" };
		String[][] rec = {

		};
		model = new DefaultTableModel(rec, header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (row == this.getRowCount() - 1 && (column == 1 || column == 2 || column == 3))
					return true;
				else
					return false;
			}
		};
		table = new JTable(model);
		sp = new JScrollPane(table);
		// save
//		SMI last_smi = new SMI();
//		// buttons
//		addRow = new Button("Add Row");
//		computeIndex = new Button("Compute Index");
//
//		AddRow ar = new AddRow();
//		ar.setField(model, table);
//		addRow.addActionListener(ar);
//
//		ci = new ComputeIndex();
//		ci.setFields(table, last_smi, saving_list.SMI_list);
//		ci.setNumberOfRows(number_of_rows_when_opening, number_of_rows_when_saving);
//		computeIndex.addActionListener(ci);

		// add ActionListener for metrics_option1
		fpItem = new FunctionPointItemListener();
		sl = new SMI_Listener();
		fpItem.setFields(lanItem, frame, fp, languageField, tabPane, saving_list.saveObjectArray, sl, table,
				jt,saving_list.file_map,treePopup);
		metrics_option1.addActionListener(fpItem);

		// add ActionListener to JMenu file_option[2]: Save operation
		
		saveItem.setFields(saving_list, saving_list.projectInfo, frame, table, tabPane);
		saveItem.setNumberOfRows(number_of_rows_when_opening, number_of_rows_when_saving);
		file_option[2].addActionListener(saveItem);

		// add ActionListener to JMenu file_option[1]: Open operation
		openItem = new OpenItemListener();
		openItem.setFields(saving_list, tabPane, frame, model, saveItem, table,
				saving_list.projectInfo, open, fpItem, metrics, lanItem,project_code,add_code_listener,
				jt,treePopup,this);
		openItem.SetNumberOfRows(number_of_rows_when_opening, number_of_rows_when_saving);
		file_option[1].addActionListener(openItem);

		smi_item = new JMenuItem("SMI");
		// add ActionListener for smi
		sl.setFields(frame, tabPane, saving_list.SMI_list,saving_list.file_map, 
				saveItem, model, table, panel, sp,
				saving_list.projectInfo,jt,treePopup,saving_list.saveObjectArray);
		sl.SetNumberOfRows(number_of_rows_when_opening, number_of_rows_when_saving);
		smi_item.addActionListener(sl);
		openItem.set_SMI_Listener(sl);

		// add to metrics
		metrics.add(metrics_option1);
		metrics.add(smi_item);

		// finalize frame
		frame.setLayout(null);
		tree_sp.setBounds(0, 0, 200, 550);
		tabPane.setBounds(200,0,700,550);
		frame.add(tree_sp);
		frame.add(tabPane);
		
//		 // create a splitpane 
//		JScrollPane tabPane_sp = new JScrollPane(tabPane);
//        JSplitPane splitPane = new JSplitPane(SwingConstants.VERTICAL, tree_sp, tabPane_sp); 
//        splitPane.setBounds(0,0,915,550);
//        splitPane.setDividerLocation(170);
//        splitPane.setOrientation(SwingConstants.VERTICAL); 
//        frame.add(splitPane); 
		
		frame.setJMenuBar(menuBar);
		frame.setSize(920, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ArrayList<SMI> SMI_list = openItem.SMI_list;
				// this is a clone of saveObjectArray right after reading from
				// the file
				ArrayList<SaveModel> saveObjectArray = openItem.saveObjectArray;
//				System.out.println(SMI_list);
//				System.out.println(saveObjectArray);
//				System.out.println("In MenuControl: " + open.isOpen);

				if (open.isOpen) {
					saving_list.SMI_list = openItem.SMI_list;
//					saving_list.saveObjectArray = openItem.fpItem.saveObjectArray;
					saving_list.saveObjectArray = openItem.saveObjectArray;
					saving_list.projectInfo = openItem.projectInfo;
					model = openItem.model;
					saving_list.file_names = openItem.file_names; //fpItem.file_map
					saving_list.file_map = openItem.saving_list.file_map;
				}

				System.out.println("Closing in MenuControl:");
				System.out.println(saving_list.SMI_list);
				System.out.println(saving_list.saveObjectArray);
				System.out.println(saving_list.file_names);
				System.err.println(saving_list.file_map);
				System.out.println(model.getRowCount());
				System.out.println(number_of_rows_when_opening + " " + number_of_rows_when_saving);
				// exit from MenuControl
				if (model.getRowCount() == 0 && number_of_rows_when_opening.num == 0
						&& number_of_rows_when_saving.num == 0) {
					System.out.println("Displose and exit in MenuControl");
					frame.dispose();
					System.exit(1);
				}
				// validate
				if (number_of_rows_when_opening.num == number_of_rows_when_saving.num) {
					System.out.println("Displose and exit");
					frame.dispose();
					System.exit(1);
				}
				// exit from OpenItemListener
				model = sl.model;
				System.out.println(model.getRowCount());
				if (model.getRowCount() == 0) {
					System.err.println("Error with model in MenuControl");
					return;
				}
				// not equal
				// save
				String[] options = { "Save", "Discard" };
				String msg = "You must save before closing the application"
						+ "\nDo you want to save the changes made to the SMI panel?";
				int choice = JOptionPane.showOptionDialog(frame, msg, "Warning", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[1]);

				if (choice == 0) {
					String projectName = saving_list.projectInfo.newProjectText.getText();
					String fileName = projectName + ".ms";
					if (fileName.equals("Project Name cannot be empty.ms") || fileName.equals(".ms")) {
						System.err.println("Error in MenuControl. Frame.addActionLister()");
						JOptionPane.showMessageDialog(frame, "Nothing to be saved. You must have a project name",
								"Alert", JOptionPane.ERROR_MESSAGE);
						return;
					}
					// save
					try {
						FileOutputStream fileOut = new FileOutputStream(fileName);
//						System.out.println(saving_list.saveObjectArray);
//						System.out.println(saving_list.SMI_list);
						ObjectOutputStream out = new ObjectOutputStream(fileOut);
						
						// save active panel's index
						int index = tabPane.getSelectedIndex();
						String title = tabPane.getTitleAt(index);
						saving_list.activeTabTitle = title;
						
						out.writeObject(saving_list);
						out.close();
						fileOut.close();
						// JOptionPane.showMessageDialog(frame, "Saved!","Save",
						// JOptionPane.INFORMATION_MESSAGE);
						System.out.println("Serialized data is saved");

						// close the frame
						frame.dispose();
						System.exit(1);
					} catch (IOException i) {
						i.printStackTrace();
					}
				} else {
					// "Discard" or Close the dialog
				}

			}
		});
		
	}
	// Pop-up class
	class TreePopup extends JPopupMenu {
			String tabTitle;
			JTabbedPane tabPane;
			JFrame frame;
			int row;
			TreePath path;
			public SavingList popup_saving_list;
			SaveModel lastObject;
		public TreePopup(JTree tree) {
				tabTitle = "";
				row = -1;
				
				JMenuItem open = new JMenuItem("Open");
				JMenuItem close = new JMenuItem("Close");
				JMenuItem delete = new JMenuItem("Delete");
				open.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						System.out.println("Open "+tabTitle);
						System.out.println("Open "+ popup_saving_list.file_map.get(tabTitle));
						// validate
						if (popup_saving_list.file_map.get(tabTitle) == null 
								|| !popup_saving_list.file_map.containsKey(tabTitle)) return;
						int i = tabPane.indexOfTab(tabTitle);
						// validate
						if (i != -1) {
							System.err.println("The tab is already opened");
							return;
						}
						// if the file is FP panel
						if(tabTitle.charAt(0)==' ') {
							int index = Integer.parseInt(popup_saving_list.file_map.get(tabTitle));
							if (popup_saving_list.saveObjectArray==null 
									|| popup_saving_list.saveObjectArray.isEmpty()) {
								System.err.println("Error with saveObjectArray");
								return;
							}
							System.err.println(popup_saving_list.saveObjectArray.get(index));
							// never out of range
							int atIndex = (tabPane.getTabCount() < row-1) ? tabPane.getTabCount() : row-1;
							System.err.println(atIndex);
							openItem.functionPoint(popup_saving_list.saveObjectArray.get(index),
									tabPane,atIndex);
							return;
						}
						// if the file is SMI panel
						if(tabTitle.equals("SMI")) {
//							System.err.println("Open SMI");
//							System.err.println(saving_list.SMI_list);
							// never out of range
							int atIndex = (tabPane.getTabCount() < row-1) ? 0 : row-1;
							System.err.println(atIndex);
							openItem.smi(popup_saving_list.SMI_list,tabPane,atIndex);
							SwingUtilities.updateComponentTreeUI(frame);
							return;
						}
						
						// parse
						AddCodeListener.MyPanel my_panel = new AddCodeListener().new MyPanel();
						my_panel.panel = new JPanel();
						StringBuilder sb = new StringBuilder();
						File file = new File(popup_saving_list.file_map.get(tabTitle));
//						System.err.println("Row: "+row);
						// never out of range
						int atIndex = (tabPane.getTabCount() < row-1) ? tabPane.getTabCount() : row-1;
						System.err.println(atIndex);
						new AddCodeListener().new Statistics().parse(file,sb,my_panel,tabPane,false,atIndex);
					}
				});
				close.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						// FP panel
						if (tabTitle.charAt(0)==' ') {
							// validate
							System.out.println("In Close TreePopup: " + lastObject);
							if (lastObject != null && lastObject.CodeSizeField.getText().equals("")) {
								String msg = "Cannot close. Please compute before closing";
								System.err.println(msg);
								JOptionPane.showMessageDialog(frame, msg, "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						
						int i = tabPane.indexOfTab(tabTitle);
						if (i == -1) {
							System.err.println("The tab is already closed");
							return;
						}
						System.out.println("Closing "+popup_saving_list.file_map.get(tabTitle));
						tabPane.remove(i);
					}
				});
				delete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						// validate
						if (!popup_saving_list.file_map.containsKey(tabTitle)) {
							System.err.println("You cannot delete the root");
							return;
						}
						
						String[] options = { "Cancel","No","Yes"};
						String msg = "Delete "+tabTitle;
						int choice = JOptionPane.showOptionDialog(frame, msg, "Select an Option", JOptionPane.DEFAULT_OPTION,
								JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						// validate
						if (choice != 2) return;
						// remove object, FP panel
						if (tabTitle.charAt(0)==' ') {
							int index = Integer.parseInt(popup_saving_list.file_map.get(tabTitle));
							System.err.println(index);
							System.err.println(popup_saving_list.saveObjectArray.size());
							lastObject = null;
							fpItem.lastObject = null;
							sl.lastObject = null;
							if (index < popup_saving_list.saveObjectArray.size())
								popup_saving_list.saveObjectArray.remove(index);
						}
						// reset rows, SMI panel
						if (tabTitle.equals("SMI")) {
							System.err.println("In Delete SMI: ");
							System.err.println(popup_saving_list.SMI_list);
							popup_saving_list.SMI_list.clear();
							System.err.println(popup_saving_list.SMI_list);
							
							// store
							sl.SMI_list = popup_saving_list.SMI_list;
							sl.file_map = popup_saving_list.file_map;
							sl.saveObjectArray = popup_saving_list.saveObjectArray;
							
							model.setRowCount(0);
							sl.model.setRowCount(0);
							sl.setTable(table, model);
							saveItem.table = table;
							saveItem.model = model;
							
							number_of_rows_when_opening.num=number_of_rows_when_saving.num=0;
						}
						
						int i = tabPane.indexOfTab(tabTitle);
						//				System.out.println("Delete "+tabTitle);
						// validate
						if (i != -1)
							tabPane.remove(i);
						
						DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
						model.removeNodeFromParent((MutableTreeNode)path.getLastPathComponent());
						// remove from map and array list
						System.out.println(popup_saving_list.file_names.remove(popup_saving_list.file_map.get(tabTitle)));
						System.out.println(popup_saving_list.file_map.remove(tabTitle));
					}
				});
				
				add(open);
				add(close);
				add(delete);
		}
	}
}


