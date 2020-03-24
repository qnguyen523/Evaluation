import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel; 
/*
 * This class is to create a menu bar and to control every operation
 */
public class MenuControl {
	// menubar 
    private JMenuBar menuBar; 
    // JMenu 
    private JMenu file, edit, preferences, metrics, help;
    // Menu items 
    private JMenuItem preferences_option1,
    					metrics_option1;
    private JMenuItem[] file_option;
    
    private JFrame frame;
    private String text;
    private JMenuItem smi_item;// = new JMenuItem("SMI"); 
    public FPModel fp;
    
    // back-end
    public JTextField languageField;

    // save and open operation
//    ProjectInfoModel projectInfo;
    JTabbedPane tabPane;
    SavingList saving_list;
    SaveItemListener saveItem;
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
	NumberOfRows number_of_rows_when_opening=new NumberOfRows(0);
	NumberOfRows number_of_rows_when_saving=new NumberOfRows(0);
	JPanel panel = new JPanel();
	IsOpen open = new IsOpen();
	
	LanguageItemListener lanItem;
	
    // constructor
    public MenuControl(String projectName) { 
    	saving_list = new SavingList();
    	// initialization
    	file_option = new JMenuItem[4];
    	text = new String();
    	fp = new FPModel();
    	languageField = new JTextField(2);
//    	projectInfo = new ProjectInfoModel();
    	tabPane = new JTabbedPane();
    	
    	frame = new JFrame(projectName);
    	menuBar = new JMenuBar();
    	// create JMenu objects
    	file = new JMenu("File");
    	edit = new JMenu("Edit");
    	preferences = new JMenu("Preferences");
    	metrics = new JMenu("Metrics");
    	help = new JMenu("Help");
    	
    	// add to menuBar
    	menuBar.add(file);
    	menuBar.add(edit);
    	menuBar.add(preferences);
    	menuBar.add(metrics);
    	menuBar.add(help);
    	
    	// create menu items for file
    	file_option[0] = new JMenuItem("New"); 
    	file_option[1] = new JMenuItem("Open"); 
    	file_option[2] = new JMenuItem("Save"); 
    	file_option[3] = new JMenuItem("Exit");
    	
    	// add ActionListener to JMenu file_option[0]: New operation
    	metrics.setEnabled(false);
    	newItem = new NewItemListener();
    	newItem.setFields(frame, saving_list.projectInfo,metrics);
    	file_option[0].addActionListener(newItem);
    	
    	// add ActionListener to JMenu file_option[1]: Exit operation
    	ExitItemListener exitItem = new ExitItemListener();
    	exitItem.setFields(frame,open);
    	file_option[3].addActionListener(exitItem);
    	
    	// add to JMenu file
    	file.add(file_option[0]);
    	file.add(file_option[1]);
    	file.add(file_option[2]);
    	file.add(file_option[3]);
    	
    	// create menu items for preferences
    	preferences_option1 = new JMenuItem("Language");
    	
    	// could add languageField here to change language
    	lanItem = new LanguageItemListener();
    	lanItem.setFields(fp, text, languageField,saving_list.projectInfo);
    	preferences_option1.addActionListener(lanItem);
    	
    	// add to preferences
    	preferences.add(preferences_option1);
    	
    	// create menu items for metrics
    	metrics_option1 = new JMenuItem("Function Points");
    	
    	// add table
    	String[] header = {"SMI","SMI Added","SMI Changed","SMI Deleted","Total Modules"};
    	String[][] rec = {

    	};
    	model = new DefaultTableModel(rec,header) {
    		@Override
    		public boolean isCellEditable(int row, int column) {
    			if (row==this.getRowCount()-1 && (column==1 || column==2 || column==3))
    				return true;
    			else return false;  
    		}
    	};
    	 table = new JTable(model);
    	 sp = new JScrollPane(table);
//	    save
	    SMI last_smi = new SMI();
    	// buttons
	    addRow = new Button("Add Row");
	    computeIndex = new Button("Compute Index");
	    
	    AddRow ar = new AddRow();
	    ar.setField(model,table);
	    addRow.addActionListener(ar);
	    
	    ci = new ComputeIndex();
	    ci.setFields(table,last_smi,saving_list.SMI_list);
	    ci.setNumberOfRows(number_of_rows_when_opening,number_of_rows_when_saving);
	    computeIndex.addActionListener(ci);
	    
		// add ActionListener for metrics_option1
    	fpItem = new FunctionPointItemListener();
    	sl = new SMI_Listener();
    	fpItem.setFields(lanItem,frame,fp,languageField,tabPane,saving_list.saveObjectArray,sl);
    	metrics_option1.addActionListener(fpItem);
	    
    	// add ActionListener to JMenu file_option[2]: Save operation
    	saveItem = new SaveItemListener();
    	saveItem.setFields(saving_list, saving_list.projectInfo,frame,table,tabPane);
    	saveItem.setNumberOfRows(number_of_rows_when_opening,number_of_rows_when_saving);
    	file_option[2].addActionListener(saveItem);

    	// add ActionListener to JMenu file_option[1]: Open operation
    	openItem = new OpenItemListener();
    	openItem.setFields(saving_list,tabPane,frame,model,addRow,
    			computeIndex,ar,ci,saveItem,table,saving_list.projectInfo,open,fpItem,metrics,lanItem);
    	openItem.SetNumberOfRows(number_of_rows_when_opening,number_of_rows_when_saving);
    	file_option[1].addActionListener(openItem);

    	smi_item = new JMenuItem("SMI"); 
    	// add ActionListener for smi
    	
    	sl.setFields(frame,tabPane,saving_list,saveItem,model,table,panel,
    			sp,addRow,computeIndex,saving_list.projectInfo);
    	smi_item.addActionListener(sl);
    	openItem.set_SMI_Listener(sl);
    	
    	
    	// add to metrics
    	metrics.add(metrics_option1);
    	metrics.add(smi_item);
    	
    	// finalize frame
    	frame.setLayout(new BorderLayout());
    	frame.setJMenuBar(menuBar);
    	frame.setSize(800, 600);
    	frame.setVisible(true);

    	// testing
//    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	
    	frame.addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			ArrayList<SMI> SMI_list = openItem.SMI_list;
    			// this is a clone of saveObjectArray right after reading from the file
    			ArrayList<SaveModel> saveObjectArray = openItem.saveObjectArray;
    			System.out.println(SMI_list);
    			System.out.println(saveObjectArray);
    			System.out.println("In MenuControl: "+open.isOpen);
    			
    			if (open.isOpen) {
    				saving_list.SMI_list = openItem.SMI_list;
    				saving_list.saveObjectArray = openItem.fpItem.saveObjectArray;
    				saving_list.projectInfo = openItem.projectInfo;
    				model = openItem.model;
    			}
    			
    			System.out.println("Closing in MenuControl:");
    			System.out.println(saving_list.SMI_list);
    			System.out.println(saving_list.saveObjectArray);
    			System.out.println(model.getRowCount());
    			System.out.println(number_of_rows_when_opening+" "+number_of_rows_when_saving);
    			// exit from MenuControl
    			if (model.getRowCount()==0 && number_of_rows_when_opening.num==0
    					&&number_of_rows_when_saving.num==0) {
    				System.out.println("Displose and exit in MenuControl");
    				frame.dispose();
    				System.exit(1);
    			}
    			// validate
    			if (number_of_rows_when_opening.num==number_of_rows_when_saving.num) {
    				System.out.println("Displose and exit");
    				frame.dispose();
    				System.exit(1);
    			}
    			// exit from OpenItemListener
    			if (model.getRowCount()==0) {
    				System.err.println("Error with model in MenuControl");
    				return;
    			}
    			// not equal
    			// save
    			String[] options = { "Save", "Discard" };
    			String msg = "You must save before closing the application"
    					+ "\nDo you want to save the changes made to the SMI panel?";
    			int choice = JOptionPane.showOptionDialog(frame, msg, "Warning",
    					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
    					null, options, options[1]);

    			if (choice==0) {
    				String projectName = saving_list.projectInfo.newProjectText.getText();
    				String fileName = projectName+".ms";
    				if (fileName.equals("Project Name cannot be empty.ms")
    						|| fileName.equals(".ms")) {
    					System.err.println("Error in MenuControl. Frame.addActionLister()");
    					JOptionPane.showMessageDialog(frame, "Nothing to be saved. You must have a project name", "Alert", JOptionPane.ERROR_MESSAGE);
    					return;
    				}
    				// save
    				try {
    					FileOutputStream fileOut = new FileOutputStream(fileName);
    					System.out.println(saving_list.saveObjectArray);
    					System.out.println(saving_list.SMI_list);
    					ObjectOutputStream out = new ObjectOutputStream(fileOut);
    					out.writeObject(saving_list);
    					out.close();
    					fileOut.close();
    					//       				  JOptionPane.showMessageDialog(frame, "Saved!","Save", JOptionPane.INFORMATION_MESSAGE);
    					System.out.println("Serialized data is saved");

    					// close the frame 
    					frame.dispose();
    					System.exit(1);
    				} catch (IOException i) {
    					i.printStackTrace();
    				}
    			}
    			else {
    				// "Discard" or Close the dialog
    			}

    		}
    	});
    }
}
