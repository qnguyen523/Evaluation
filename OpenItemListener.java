import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.table.DefaultTableModel;

import java.util.*;
/*
 * This class is to open file to load a new tab
 */
public class OpenItemListener implements ActionListener{
	final private int SIMPLE = 0, AVERAGE = 1, COMPLEX = 2;
    final int EI = 0, EO = 1, EInq = 2, ILF = 3, EIF = 4;
//	SaveModel saveObject;
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
	// save
	public void set_SMI_Listener(SMI_Listener sl) {
		this.sl=sl;
	}
	// exit
	public void SetNumberOfRows
	(NumberOfRows number_of_rows_when_opening,NumberOfRows number_of_rows_when_saving) {
		this.number_of_rows_when_opening=number_of_rows_when_opening;
		this.number_of_rows_when_saving=number_of_rows_when_saving;
	}
	// set fields
	public void setFields(SavingList saving_list,JTabbedPane tabPane,JFrame frame,
			DefaultTableModel model,Button addRow,Button computeIndex,AddRow ar,
			ComputeIndex ci,SaveItemListener saveItem,JTable table,
			ProjectInfoModel projectInfo,IsOpen open,FunctionPointItemListener fpItem) {
//		this.saveObject=saveObject;
		this.tabPane=tabPane;
		this.frame=frame;
		this.saving_list=saving_list;
		this.model=model;
		this.addRow=addRow;
		this.computeIndex=computeIndex;
		this.ar=ar;
		this.ci=ci;
		this.saveItem=saveItem;
		this.table=table;
		this.projectInfo=projectInfo;
		this.open=open;
		this.fpItem=fpItem;
	}
	
	// create a row
	public void addRow(DefaultTableModel model,SMI x) {
		model.addRow(new String[] {x.smi+"",x.added+"",x.changed+"",x.deleted+"",x.currentTotal+""});

	}
	// when open button is clicked
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		open.isOpen=true;
		// for testing purpose
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
				
				/*
				 * testing to get title
				System.out.println(file.getName());
				String s = file.getName();
				System.out.println(file.getName().split("\\."));
				String title = frame.getTitle() + " - "+file.getName().split("\\.")[0];
				frame.setTitle(title);
				frame.setVisible(true);
				*/
				
				FileInputStream fileIn = new FileInputStream(file);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				SavingList temp_saving_list = (SavingList) in.readObject();
				saveItem.setSavingList(temp_saving_list);
				this.saving_list=temp_saving_list;
				
				fpItem.saveObjectArray=temp_saving_list.saveObjectArray;
				this.saving_list.saveObjectArray = fpItem.saveObjectArray;
				in.close();
				fileIn.close();
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
//			saving_list.saveObjectArray = new ArrayList<SaveModel>();
//			System.out.println(saveObjectArray);
			if (saveObjectArray==null) {
				System.out.println("Error with saveObjectArray");
				return;
			}
//			System.out.println(saveObjectArray);
			for (SaveModel i : saving_list.saveObjectArray) {
				functionPoint(i);
			}
			
//			while(!saving_list.saveObjectArray.isEmpty()) {
//				SaveModel saveObject = new SaveModel(); 
//				saveObject = saving_list.saveObjectArray.remove(0);
//				functionPoint(saveObject);
//			}
			
			
			// take care of table
			SMI_list = saving_list.SMI_list;
//			initialize for exit
			number_of_rows_when_opening.num=number_of_rows_when_saving.num=SMI_list.size();
					
//			System.out.println(SMI_list);
			if (SMI_list==null || SMI_list.isEmpty()) {
				System.out.println("Error with SMI_List");
				return;
			}
			JPanel panel = new JPanel();
			tabPane.addTab("SMI", panel);
			frame.getContentPane().add(tabPane, BorderLayout.CENTER);
			String[] header = {"SMI","SMI Added","SMI Changed","SMI Deleted","Total Modules"};
			String[][] rec = {

			};
			panel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Software Maturity Index"));
			model = new DefaultTableModel(rec,header) {
				@Override
				public boolean isCellEditable(int row, int column) {
					if (row==this.getRowCount()-1 && (column==1 || column==2 || column==3))
						return true;
					else return false;  
				}
			};
			JTable table = new JTable(model);
			// add to JTable
			for (SMI x : SMI_list) {
				addRow(model,x);
			}
			saveItem.setTable(table,model);
			sl.setTable(table,model);
			
			JScrollPane sp = new JScrollPane(table);
		    
		    ar.setField(model,table);
//		    addRow.addActionListener(ar);
		    
		    SMI lastSMI = new SMI();
		    ci.setFields(table,lastSMI,SMI_list);
		    int lastTotal = SMI_list.get(SMI_list.size()-1).currentTotal;
		    SMI last_smi = new SMI();
		    ci.setFields(table,last_smi,SMI_list);
		    ci.setNumberOfRows(number_of_rows_when_opening,number_of_rows_when_saving);
		    ci.setLastTotal(lastTotal);
//		    computeIndex.addActionListener(ci);

		    sp.setPreferredSize( new Dimension(700,400) );
		    table.setGridColor(Color.RED);
		    panel.add(sp);
		    panel.add(addRow);
		    panel.add(computeIndex);
			
		    // testing
//		    return;
//		    WindowListener[] a = frame.getWindowListeners();
//			WindowListener w = a[0];
//			w.windowClosing(new WindowEvent(frame,WindowEvent.WINDOW_CLOSING));
		}
		else {
			return;
		}
		 // testing: backup addWindowListener()
		/*
	    frame.addWindowListener(new WindowAdapter() {
	    	@Override
	    	public void windowClosing(WindowEvent e) {
	    		System.out.println("In OpenItemListener:");
//	    		System.out.println(saveObjectArray);
//	    		System.out.println(SMI_list);
	    		System.out.println(saving_list.saveObjectArray);
	    		System.out.println(saving_list.SMI_list);
	    		System.out.println(number_of_rows_when_opening+" "+number_of_rows_when_saving);
	    		if (number_of_rows_when_opening.equals(number_of_rows_when_saving)){
	    			System.out.println("Dispose and exit");
	    			frame.dispose();
	    			System.exit(1);
	    		} else {
	    			System.out.println("Not equal");
	    			// not equal
	    			// save
	    			String[] options = { "Save", "Discard" };
	    			String msg = "You must save before closing the application"
	    					+ "\nDo you want to save the changes made to the SMI panel?";
	    			int choice = JOptionPane.showOptionDialog(null, msg, "Warning",
	    					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
	    					null, options, options[1]);
	    			if (choice==0) {
	    				// save
	    				String projectName = projectInfo.newProjectText.getText();
	    				String fileName = projectName+".ms";
	    				if (fileName.equals("Project Name cannot be empty.ms")
	    						|| fileName.equals(".ms")) {
	    					System.err.println("Error in OpenItemListener. Frame.addActionLister()");
	    					JOptionPane.showMessageDialog(frame, "Nothing to be saved. You must have a project name", "Alert", JOptionPane.ERROR_MESSAGE);
	    					return;
	    				}
	    				// save
	    				try {
	    					FileOutputStream fileOut = new FileOutputStream(fileName);
	    					ObjectOutputStream out = new ObjectOutputStream(fileOut);
	    					out.writeObject(saving_list);
	    					out.close();
	    					fileOut.close();
	    					//       				  JOptionPane.showMessageDialog(frame, "Saved!","Save", JOptionPane.INFORMATION_MESSAGE);
	    					System.out.println("Serialized data is saved");
	    					System.out.println(saving_list.saveObjectArray);
	    					System.out.println(saving_list.SMI_list);
	    					
	    					// dispose and exit 
	    					frame.dispose();
	    					System.exit(1);
	    				} catch (IOException i) {
	    					i.printStackTrace();
	    				}
	    				
	    				// dispose and exit
//                	 frame.dispose();
//                	 System.exit(1);
	    			}
	    			else {
	    				// "Discard" or Close the dialog
	    			}
	    		}
	    	}
	    });
	    */
	}
	// take care of function point
	public void functionPoint(SaveModel saveObject) {
		// add a new tab to current panel
		JPanel panel = new JPanel();
		tabPane.addTab("Function Points", panel);
		frame.getContentPane().add(tabPane, BorderLayout.CENTER);
		frame.setVisible(true);
		panel.setLayout(null);

		// labels
		JLabel weightingFactors = new JLabel("Weighting Factors", JLabel.CENTER);
		JLabel simple = new JLabel("Simple   Average   Complex", JLabel.CENTER);

		// set bounds
		weightingFactors.setBounds(0,10,800,20);
		simple.setBounds(0,30,800,20);

		// add to panel
		panel.add(weightingFactors);
		panel.add(simple);

		// labels
		saveObject.id[EI].label = new JLabel("External Inputs");
		saveObject.id[EO].label = new JLabel("External Outputs");
		saveObject.id[EInq].label = new JLabel("External Inquiries");
		saveObject.id[ILF].label = new JLabel("Internal Logical Files");
		saveObject.id[EIF].label = new JLabel("External Interface Files");
		
		// complexity values or weighting factors 
		int hold = saveObject.id[EI].complexity_value;
		saveObject.id[EI].radioButtons[SIMPLE] = new JRadioButton("3",3==hold? true : false);
		saveObject.id[EI].radioButtons[AVERAGE] = new JRadioButton("4",4==hold? true : false);
		saveObject.id[EI].radioButtons[COMPLEX] = new JRadioButton("6",6==hold? true : false);
		hold = saveObject.id[EO].complexity_value;
		saveObject.id[EO].radioButtons[SIMPLE] = new JRadioButton("4",4==hold? true : false);
		saveObject.id[EO].radioButtons[AVERAGE] = new JRadioButton("5",5==hold? true : false);
		saveObject.id[EO].radioButtons[COMPLEX] = new JRadioButton("7",7==hold? true : false);
		hold = saveObject.id[EInq].complexity_value;
		saveObject.id[EInq].radioButtons[SIMPLE] = new JRadioButton("3",3==hold? true : false);
		saveObject.id[EInq].radioButtons[AVERAGE] = new JRadioButton("4", 4==hold? true : false);
		saveObject.id[EInq].radioButtons[COMPLEX] = new JRadioButton("6",6==hold? true : false);
		hold = saveObject.id[ILF].complexity_value;
		saveObject.id[ILF].radioButtons[SIMPLE] = new JRadioButton("7",7==hold? true : false);
		saveObject.id[ILF].radioButtons[AVERAGE] = new JRadioButton("10",10==hold? true : false);
		saveObject.id[ILF].radioButtons[COMPLEX] = new JRadioButton("15",15==hold? true : false);
		hold = saveObject.id[EIF].complexity_value;
		saveObject.id[EIF].radioButtons[SIMPLE] = new JRadioButton("5",5==hold? true : false);
		saveObject.id[EIF].radioButtons[AVERAGE] = new JRadioButton("7",7==hold? true : false);
		saveObject.id[EIF].radioButtons[COMPLEX] = new JRadioButton("10",10==hold? true : false);
		
		// group the radio buttons
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
		
		// compute fp
		FPModel fp = new FPModel();
		ComputeFP fpItem = new ComputeFP(); 
		VafValue vaf_total_value = new VafValue();
		fpItem.setFields(fp,saveObject.id,saveObject.total,saveObject.VAFField,
				vaf_total_value,saveObject.FPField);
		compute_FP_button.addActionListener(fpItem);
		
		// vaf frame
		vafActionListener vafItem = new vafActionListener();
		vafItem.setFields(vaf_total_value, saveObject.VAFField,saveObject.vaf_array);
		VAF_button.addActionListener(vafItem);
		
		// change language
		ChangeLanguageItemListener changeLanItem = new ChangeLanguageItemListener();
		changeLanItem.setFields(fp,saveObject.languageField);
		change_language_button.addActionListener(changeLanItem);
		
		// compute size
		ComputeSizeFromSave sizeItem = new ComputeSizeFromSave(); 
		sizeItem.setFields(fp,saveObject.CodeSizeField,saveObject.languageField);
		compute_code_size_button.addActionListener(sizeItem);
	}
}
