import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

// create a menu bar
public class MenuControl {
	final private int SIMPLE = 0, AVERAGE = 1, COMPLEX = 2;
	// menubar 
    private JMenuBar menuBar; 
  
    // JMenu 
    private JMenu file, edit, preferences, metrics, help;
  
    // Menu items 
    // code factoring MenuItem extends JMenuItem
    private JMenuItem preferences_option1,
    					metrics_option1;
    
    private JMenuItem[] file_option = new JMenuItem[4];
    
    private JFrame frame;
    private String projectName;
    // constructor
    public MenuControl(String projectName) { 
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
    	
    	// add ActionListener to JMenu file_option[0]
    	file_option[0].addActionListener(new NewItemListener());
    	
    	// add to JMenu file
    	file.add(file_option[0]);
    	file.add(file_option[1]);
    	file.add(file_option[2]);
    	file.add(file_option[3]);
    	
    	
    	// create menu items for preferences
    	preferences_option1 = new JMenuItem("Language");
    	preferences_option1.addActionListener(new LanguageItemListener());
    	
    	// add to preferences
    	preferences.add(preferences_option1);
    	
    	// create menu items for metrics
    	metrics_option1 = new JMenuItem("Function Points");
    	// add ActionListener for metrics
    	metrics_option1.addActionListener(new FunctionPointItemListener());
    	// add to metrics
    	metrics.add(metrics_option1);
    	
    	// frame
    	frame = new JFrame(projectName);
		frame.setLayout(new BorderLayout());
    	frame.setJMenuBar(menuBar);
    	frame.setSize(800, 600);
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public String text = "";
    class LanguageItemListener implements ActionListener  {
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
        	    		// languageField.setText("ASSEMBLER");
						text = "ASSEMBLER";
        	    		newProjectFrame.dispose();
        	    		return;
        			}
        			else if (checkBoxes[1].isSelected())  {
        				fp.currentLanguge = FPModel.LANGUAGE.ADA_95;
        	    		// languageField.setText("ADA 95");
						text = "ADA 95";
        	    		newProjectFrame.dispose();
        	    		return;
        			}
        			else if (checkBoxes[2].isSelected())  {
        				fp.currentLanguge = FPModel.LANGUAGE.CL;
        	    		// languageField.setText("C");
						text = "C";
        	    		newProjectFrame.dispose();
        	    		return;
        			}
        			else if (checkBoxes[3].isSelected())  {
        				fp.currentLanguge = FPModel.LANGUAGE.CLPLUS;
        	    		// languageField.setText("C++");
						text = "C++";
        	    		newProjectFrame.dispose();
        	    		return;
        			}
        			else if (checkBoxes[4].isSelected())  {
        				fp.currentLanguge = FPModel.LANGUAGE.CSHARP;
        	    		// languageField.setText("C#");
						text = "C#";
        	    		newProjectFrame.dispose();
        	    		return;
        			}
        			else if (checkBoxes[5].isSelected())  {
        				fp.currentLanguge = FPModel.LANGUAGE.COBOL;
        	    		// languageField.setText("COBOL");
						text = "COBOL";
        	    		newProjectFrame.dispose();
        	    		return;
        			}
        			else if (checkBoxes[6].isSelected())  {
        				fp.currentLanguge = FPModel.LANGUAGE.FORTRAN;
        	    		// languageField.setText("FORTRAN");
						text = "FORTRAN";
        	    		newProjectFrame.dispose();
        	    		return;
        			}
        			else if (checkBoxes[7].isSelected())  {
        				fp.currentLanguge = FPModel.LANGUAGE.HTML;
        	    		// languageField.setText("HTML");
						text = "HTML";
        	    		newProjectFrame.dispose();
        	    		return;
        			}
        			else if (checkBoxes[8].isSelected())  {
        				fp.currentLanguge = FPModel.LANGUAGE.JAVA;
        	    		// languageField.setText("JAVA");
						text = "JAVA";
        	    		newProjectFrame.dispose();
        	    		return;
        			}
        			else if (checkBoxes[9].isSelected())  {
        				fp.currentLanguge = FPModel.LANGUAGE.JAVASCRIPT;
        	    		// languageField.setText("JAVASCRIPT");
						text = "JAVASCRIPT";
        	    		newProjectFrame.dispose();
        	    		return;
        			}
        			else if (checkBoxes[10].isSelected())  {
        				fp.currentLanguge = FPModel.LANGUAGE.VBSCRIPT;
        	    		// languageField.setText("VBSCRIPT");
						text = "VBSCRIPT";
        	    		newProjectFrame.dispose();
        	    		return;
        			}
        			else if (checkBoxes[11].isSelected())  {
        				fp.currentLanguge = FPModel.LANGUAGE.VISUAL_BASIC;
        	    		// languageField.setText("VISUAL_BASIC");
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

    
    public class NewItemListener implements ActionListener {
    	public String projectNameField;
    	public void actionPerformed(ActionEvent e) {       
    		projectNameField = "";
    		JFrame newProjectFrame=new JFrame("New Project");
    		newProjectFrame.setSize(600,500);
    		newProjectFrame.setLayout(null);  
    		newProjectFrame.setVisible(true);
    		// labels
    		JLabel newProject = new JLabel("CECS 543 Metrics Suite New Project");
    		JLabel projectName = new JLabel("*Project Name: ");
    		JLabel productName = new JLabel("Product Name: ");
    		JLabel creator = new JLabel("Creator: ");
    		JLabel comments = new JLabel("Comments: ");
    		// JTextField
    		JTextField newProjectText = new JTextField("Project Name cannot be empty", 5);
    		newProjectText.setForeground(Color.GRAY);
    		newProjectText.addFocusListener(new FocusListener() {
    			@Override
    			public void focusGained(FocusEvent e) {
    				if(newProjectText.getText().equals("Project Name cannot be empty")
    						&& projectNameField.isEmpty())
    				{
    					newProjectText.setText("");
    					newProjectText.setForeground(Color.BLACK);
    				}

    			}
    			@Override
    			public void focusLost(FocusEvent e) {
    				if (newProjectText.getText().isEmpty()) {
    					newProjectText.setForeground(Color.GRAY);
    					newProjectText.setText("Project Name cannot be empty");
    					projectNameField = "";
    				}
    				else projectNameField = newProjectText.getText();
    			}
    		});

    		JTextField productNameText = new JTextField(5);
    		JTextField creatorText = new JTextField(5);
    		JTextArea commentTextArea = new JTextArea();
    		// buttons
    		Button OKButton = new Button("OK");
    		Button CancelButton = new Button("Cancel");

    		// need to continue
    		// from here
    		OKButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				if (projectNameField.isEmpty()){
    					System.err.println("The project name cannot be empty");
    					JOptionPane.showMessageDialog(null, "The project name cannot be empty. Please input project name!", "Error", JOptionPane.ERROR_MESSAGE);
    				}
    				else {
    					String title = newProject.getText() + " - " + projectNameField;
    					frame.setTitle(title);
    					frame.setVisible(true);
    				}
    			}
    		});

    		CancelButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				System.out.println("Cancel Button clicked.");
    				newProjectFrame.dispose();
    			}
    		});
    		// to here

    		// setbounds
    		newProject.setBounds(50,20,250,20);

    		projectName.setBounds(10,60,250,20);
    		newProjectText.setBounds(110,60,250,20);

    		productName.setBounds(10,90,250,20);
    		productNameText.setBounds(110,90,250,20);

    		creator.setBounds(10,120,250,20);
    		creatorText.setBounds(110,120,250,20);

    		comments.setBounds(10,150,250,20);
    		commentTextArea.setBounds(10,170,500,200);

    		OKButton.setBounds(10,400,70,20);
    		CancelButton.setBounds(90,400,70,20);
    		// add to JFrame newProjectFrame
    		newProjectFrame.add(newProject);

    		newProjectFrame.add(projectName);
    		newProjectFrame.add(newProjectText);

    		newProjectFrame.add(productName);
    		newProjectFrame.add(productNameText);

    		newProjectFrame.add(creator);
    		newProjectFrame.add(creatorText);

    		newProjectFrame.add(comments);
    		newProjectFrame.add(commentTextArea);

    		newProjectFrame.add(OKButton);
    		newProjectFrame.add(CancelButton);

    		// testing
    		newProjectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	}    
    }
    // get the menuBar
    public JMenuBar getMenuBar() {
    	return menuBar;
    }
   
    protected JTabbedPane tabPane = new JTabbedPane();
	// back-end
    protected FPModel fp = new FPModel();
    protected JTextField languageField = new JTextField(2);
    class FunctionPointItemListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		languageField = new JTextField(2);
    		languageField.setText(text);
    		JPanel panel = new JPanel();
    		tabPane.addTab("Function Points", panel);
    		frame.getContentPane().add(tabPane, BorderLayout.CENTER);
    	    frame.setVisible(true);
    	    panel.setLayout(null);
    	    
    	    // labels
    	    JLabel weightingFactors = new JLabel("Weighting Factors", JLabel.CENTER);
    	    JLabel simple = new JLabel("Simple   Average   Complex", JLabel.CENTER);
    	    
    	    // setbounds
    	    weightingFactors.setBounds(0,10,800,20);
    	    simple.setBounds(0,30,800,20);

    	    // add to panel
    	    panel.add(weightingFactors);
    	    panel.add(simple);
    	    
    	    functionPoint(panel);
    	}
    	
    	final int EI = 0, EO = 1, EInq = 2, ILF = 3, EIF = 4;
    	public JTextField VAFField;
    	public void functionPoint(JPanel panel) {
    		
    		
    		infomationDomain[] id = new infomationDomain[5];
    		// objects
    		id[EI] = new infomationDomain(4);
    		id[EO] = new infomationDomain(5);
    		id[EInq] = new infomationDomain(4);
    		id[ILF] = new infomationDomain(10);
    		id[EIF] = new infomationDomain(7);
    		// labels
    		id[EI].label = new JLabel("External Inputs");
    		id[EO].label = new JLabel("External Outputs");
    		id[EInq].label = new JLabel("External Inquiries");
    		id[ILF].label = new JLabel("Internal Logical Files");
    		id[EIF].label = new JLabel("External Interface Files");
    		//JTextFields
    		id[EI].input = new JTextField(2);
    		id[EO].input = new JTextField(2);
    		id[EInq].input = new JTextField(2);
    		id[ILF].input = new JTextField(2);
    		id[EIF].input = new JTextField(2);
    		// JRadioButton
    		id[EI].radioButtons[SIMPLE] = new JRadioButton("3");
    		id[EI].radioButtons[AVERAGE] = new JRadioButton("4", true);
    		id[EI].radioButtons[COMPLEX] = new JRadioButton("6");

    		id[EO].radioButtons[SIMPLE] = new JRadioButton("4");
    		id[EO].radioButtons[AVERAGE] = new JRadioButton("5", true);
    		id[EO].radioButtons[COMPLEX] = new JRadioButton("7");

    		id[EInq].radioButtons[SIMPLE] = new JRadioButton("3");
    		id[EInq].radioButtons[AVERAGE] = new JRadioButton("4", true);
    		id[EInq].radioButtons[COMPLEX] = new JRadioButton("6");

    		id[ILF].radioButtons[SIMPLE] = new JRadioButton("7");
    		id[ILF].radioButtons[AVERAGE] = new JRadioButton("10", true);
    		id[ILF].radioButtons[COMPLEX] = new JRadioButton("15");

    		id[EIF].radioButtons[SIMPLE] = new JRadioButton("5");
    		id[EIF].radioButtons[AVERAGE] = new JRadioButton("7", true);
    		id[EIF].radioButtons[COMPLEX] = new JRadioButton("10");
    		// group the radio button
    		id[EI].group = new ButtonGroup();
    		id[EI].addToGroup(id[EI].group, id[EI].radioButtons);

    		id[EO].group = new ButtonGroup();
    		id[EO].addToGroup(id[EO].group, id[EO].radioButtons);

    		id[EInq].group = new ButtonGroup();
    		id[EInq].addToGroup(id[EInq].group, id[EInq].radioButtons);

    		id[ILF].group = new ButtonGroup();
    		id[ILF].addToGroup(id[ILF].group, id[ILF].radioButtons);

    		id[EIF].group = new ButtonGroup();
    		id[EIF].addToGroup(id[EIF].group, id[EIF].radioButtons);

    		// output
    		id[EI].output = new JTextField(2);
    		id[EI].output.setEditable(false);
    		id[EO].output = new JTextField(2);
    		id[EO].output.setEditable(false);
    		id[EInq].output = new JTextField(2);
    		id[EInq].output.setEditable(false);
    		id[ILF].output = new JTextField(2);
    		id[ILF].output.setEditable(false);
    		id[EIF].output = new JTextField(2);
    		id[EIF].output.setEditable(false);
    		
    		// the second half of panel
    		Button compute_FP_button = new Button("Compute FP");
    		Button VAF_button = new Button("Value Adjustment");
    		Button compute_code_size_button = new Button("Compute Code Size");
    		Button change_language_button = new Button("Change Language");
    		JLabel currentLanguage = new JLabel("Current Language");
    		JLabel totalCount = new JLabel("Total Count");
    		JTextField total = new JTextField(2);
    		total.setEditable(false);
    		
//    		JTextField languageField2 = new JTextField(2);
    		languageField.setEditable(false);
//    		languageField2.setEditable(false);
    		JTextField FPField = new JTextField(2);
    		FPField.setEditable(false);
    		VAFField = new JTextField("0", 2);
    		VAFField.setEditable(false);
    		JTextField CodeSizeField = new JTextField(2);
    		CodeSizeField.setEditable(false);
    	
    		// setbounds
    		id[EI].label.setBounds(10,50,180,20);
    		id[EO].label.setBounds(10,70,180,20);
    		id[EInq].label.setBounds(10,90,180,20);
    		id[ILF].label.setBounds(10,110,180,20);
    		id[EIF].label.setBounds(10,130,180,20);
    		id[EI].input.setBounds(190,50,50,20);
    		id[EO].input.setBounds(190,70,50,20);
    		id[EInq].input.setBounds(190,90,50,20);
    		id[ILF].input.setBounds(190,110,50,20);
    		id[EIF].input.setBounds(190,130,50,20);
    		id[EI].radioButtons[SIMPLE].setBounds(280,50,90,20);
    		id[EI].radioButtons[AVERAGE].setBounds(370,50,90,20);
    		id[EI].radioButtons[COMPLEX].setBounds(460,50,90,20);
    		id[EO].radioButtons[SIMPLE].setBounds(280,70,90,20);
    		id[EO].radioButtons[AVERAGE].setBounds(370,70,90,20);
    		id[EO].radioButtons[COMPLEX].setBounds(460,70,90,20);
    		id[EInq].radioButtons[SIMPLE].setBounds(280,90,90,20);
    		id[EInq].radioButtons[AVERAGE].setBounds(370,90,90,20);
    		id[EInq].radioButtons[COMPLEX].setBounds(460,90,90,20);
    		id[ILF].radioButtons[SIMPLE].setBounds(280,110,90,20);
    		id[ILF].radioButtons[AVERAGE].setBounds(370,110,90,20);
    		id[ILF].radioButtons[COMPLEX].setBounds(460,110,90,20);
    		id[EIF].radioButtons[SIMPLE].setBounds(280,130,90,20);
    		id[EIF].radioButtons[AVERAGE].setBounds(370,130,90,20);
    		id[EIF].radioButtons[COMPLEX].setBounds(460,130,90,20);
    		id[EI].output.setBounds(550,50,50,20);
    		id[EO].output.setBounds(550,70,50,20);
    		id[EInq].output.setBounds(550,90,50,20);
    		id[ILF].output.setBounds(550,110,50,20);
    		id[EIF].output.setBounds(550,130,50,20);
    		totalCount.setBounds(10,160,180,20);
    		total.setBounds(550,160,50,20);
    		compute_FP_button.setBounds(10,200,180,20);
    		VAF_button.setBounds(10,240,180,20);
    		compute_code_size_button.setBounds(10,280,180,20);
    		change_language_button.setBounds(10,320,180,20);
    		currentLanguage.setBounds(240,280,140,20);
    		languageField.setBounds(380,280,100,20);
    		// testing
//    		languageField2.setBounds(380,280,100,20);
    		
    		FPField.setBounds(550,200,50,20);
    		VAFField.setBounds(550,240,50,20);
    		CodeSizeField.setBounds(550,280,50,20);
    		
    		// add to panel
    		panel.add(id[EI].label);
    		panel.add(id[EO].label);
    		panel.add(id[EInq].label);
    		panel.add(id[ILF].label);
    		panel.add(id[EIF].label);
    		panel.add(id[EI].input);
    		panel.add(id[EO].input);
    		panel.add(id[EInq].input);
    		panel.add(id[ILF].input);
    		panel.add(id[EIF].input);
    		panel.add(id[EI].radioButtons[SIMPLE]);
    		panel.add(id[EI].radioButtons[AVERAGE]);
    		panel.add(id[EI].radioButtons[COMPLEX]);
    		panel.add(id[EO].radioButtons[SIMPLE]);
    		panel.add(id[EO].radioButtons[AVERAGE]);
    		panel.add(id[EO].radioButtons[COMPLEX]);
    		panel.add(id[EInq].radioButtons[SIMPLE]);
    		panel.add(id[EInq].radioButtons[AVERAGE]);
    		panel.add(id[EInq].radioButtons[COMPLEX]);
    		panel.add(id[ILF].radioButtons[SIMPLE]);
    		panel.add(id[ILF].radioButtons[AVERAGE]);
    		panel.add(id[ILF].radioButtons[COMPLEX]);
    		panel.add(id[EIF].radioButtons[SIMPLE]);
    		panel.add(id[EIF].radioButtons[AVERAGE]);
    		panel.add(id[EIF].radioButtons[COMPLEX]);
    		panel.add(id[EI].output);
    		panel.add(id[EO].output);
    		panel.add(id[EInq].output);
    		panel.add(id[ILF].output);
    		panel.add(id[EIF].output);
    		panel.add(currentLanguage);
    		panel.add(compute_FP_button);
    		panel.add(VAF_button);
    		panel.add(compute_code_size_button);
    		panel.add(change_language_button);
//    		languageField2 = languageField;
//    		panel.add(languageField2);
    		panel.add(languageField);
    		panel.add(FPField);
    		panel.add(VAFField);
    		panel.add(CodeSizeField);
    		panel.add(totalCount);
    		panel.add(total);
    		
//    		languageField = new JTextField(2);
//    		languageField.setText(languageField2.getText());
    		// vaf frame
    		VAF_button.addActionListener(new vafActionListener());
    		
    		//chamge language
    		change_language_button.addActionListener(new changeLanguageItemListener());

    		// testing    		
//    		fp.currentLanguge = FPModel.LANGUAGE.JAVA;
//    		languageField.setText("Java");
    		
    		compute_FP_button.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				// take care of information domain values
    				fp.EICount = id[EI].input.getText().equals("") ? 0 : 
    					Integer.valueOf(id[EI].input.getText()) * id[EI].complexity_value;
    				fp.EOCount = id[EO].input.getText().equals("") ? 0 : 
    					Integer.valueOf(id[EO].input.getText()) * id[EO].complexity_value;
    				fp.EInqCount = id[EInq].input.getText().equals("") ? 0 : 
    					Integer.valueOf(id[EInq].input.getText()) * id[EInq].complexity_value;
    				fp.ILFCount = id[ILF].input.getText().equals("") ? 0 : 
    					Integer.valueOf(id[ILF].input.getText()) * id[ILF].complexity_value;
    				fp.EIFCount = id[EIF].input.getText().equals("") ? 0 : 
    					Integer.valueOf(id[EIF].input.getText()) * id[EIF].complexity_value;
    				
    				id[EI].output.setText(String.valueOf(fp.EICount));
    	    		id[EO].output.setText(String.valueOf(fp.EOCount));
    	    		id[EInq].output.setText(String.valueOf(fp.EInqCount));
    	    		id[ILF].output.setText(String.valueOf(fp.ILFCount));
    	    		id[EIF].output.setText(String.valueOf(fp.ILFCount));
    				// compute fp
    				total.setText(String.valueOf(fp.computeTotal()));
    				FPField.setText(String.valueOf(fp.computeFP(vaf_total_value)));
    			}
    		});

    		compute_code_size_button.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				// compute code size
    				CodeSizeField.setText(fp.computeCodeSize()+"");
    			}
    		});
    	}
    	
    	public int vaf_total_value = 0;
    	public String current_language ="";
    	class vafActionListener implements ActionListener {
    		public void actionPerformed(ActionEvent e) {
    			JFrame f = new JFrame("Value Adjustment Factors");;
    			
    			final JLabel label = new JLabel();
    			final JLabel label1 = new JLabel();
    			final JLabel label2 = new JLabel();
    			final JLabel label3 = new JLabel();
    			final JLabel label4 = new JLabel();
    			final JLabel label5 = new JLabel();
    			final JLabel label6 = new JLabel();
    			final JLabel label7 = new JLabel();
    			final JLabel label8 = new JLabel();
    			final JLabel label9 = new JLabel();
    			final JLabel label10 = new JLabel();
    			final JLabel label11 = new JLabel();
    			final JLabel label12 = new JLabel();
    			final JLabel label13 = new JLabel();
    			final JLabel label14 = new JLabel();
    			String values[] = {"0","1","2","3","4","5"};
    			final JComboBox<String> box1 = new JComboBox<>(values);
    			final JComboBox<String> box2 = new JComboBox<>(values);
    			final JComboBox<String> box3 = new JComboBox<>(values);
    			final JComboBox<String> box4 = new JComboBox<>(values);
    			final JComboBox<String> box5 = new JComboBox<>(values);
    			final JComboBox<String> box6 = new JComboBox<>(values);
    			final JComboBox<String> box7 = new JComboBox<>(values);
    			final JComboBox<String> box8 = new JComboBox<>(values);
    			final JComboBox<String> box9 = new JComboBox<>(values);
    			final JComboBox<String> box10 = new JComboBox<>(values);
    			final JComboBox<String> box11 = new JComboBox<>(values);
    			final JComboBox<String> box12 = new JComboBox<>(values);
    			final JComboBox<String> box13 = new JComboBox<>(values);
    			final JComboBox<String> box14 = new JComboBox<>(values);
    			final JButton vafOkButton = new JButton("DONE");
    			final JButton vafCancelButton = new JButton("Cancel");
    			
    			label.setSize(263,14);
    			label.setText("Assign value from 0 to 5 for each Value Adjustment Factors:");
    			label.setBounds(10, 15, 500, 15);
    			label1.setText("Does the system require reliable backup and recovery?");
    			label1.setBounds(10,45,500,15);
    			label2.setText("Are specialized data communications required to transfer information to or from the application?");
    			label2.setBounds(10, 75, 800, 15);
    			label3.setText("Are there distributed processing functions? ");
    			label3.setBounds(10, 105, 800, 15);
    			label4.setText("Is performance critical? ");
    			label4.setBounds(10, 135, 800, 15);
    			label5.setText("Will the system run in an existing, heavily utilized operational environment? ");
    			label5.setBounds(10, 165, 800, 15);
    			label6.setText("Does the system require on-line data entry? ");
    			label6.setBounds(10, 195, 800, 15);
    			label7.setText("Does the on-line data entry require the input transaction to be built over multiple screens or operations? ");
    			label7.setBounds(10, 225, 800, 15);
    			label8.setText("Are the internal logical files updated on-line? ");
    			label8.setBounds(10, 255, 800, 15);
    			label9.setText("Are the inputs, outputs, files, or inquiries complex? ");
    			label9.setBounds(10, 285, 800, 15);
    			label10.setText("Is the internal processing complex? ");
    			label10.setBounds(10, 315, 800, 15);
    			label11.setText("Is the code designed to be reusable? ");
    			label11.setBounds(10, 345, 800, 15);
    			label12.setText("Are conversion and installation included in the design? ");
    			label12.setBounds(10, 375, 800, 15);
    			label13.setText("Is the system designed for multiple installations in different organizations? ");
    			label13.setBounds(10, 405, 800, 15);
    			label14.setText("Is the application designed to facilitate change and for ease of use by the user? ");
    			label14.setBounds(10, 435, 800, 15);
    			box1.setBounds(700,45,60,17);
    			box2.setBounds(700,75,60,17);
    			box3.setBounds(700,105,60,17);
    			box4.setBounds(700,135,60,17);
    			box5.setBounds(700,165,60,17);
    			box6.setBounds(700,195,60,17);
    			box7.setBounds(700,225,60,17);
    			box8.setBounds(700,255,60,17);
    			box9.setBounds(700,285,60,17);
    			box10.setBounds(700,315,60,17);
    			box11.setBounds(700,345,60,17);
    			box12.setBounds(700,375,60,17);
    			box13.setBounds(700,405,60,17);
    			box14.setBounds(700,435,60,17);
    			vafOkButton.setBounds(40, 490, 100, 30);
    			vafCancelButton.setBounds(150, 490, 100, 30);
    			
    			f.setSize(800,600);
    			f.add(label);
    			f.add(label1);
    			f.add(label2);
    			f.add(label3);
    			f.add(label4);
    			f.add(label5);
    			f.add(label6);
    			f.add(label7);
    			f.add(label8);
    			f.add(label9);
    			f.add(label10);
    			f.add(label11);
    			f.add(label12);
    			f.add(label13);
    			f.add(label14);
    			f.add(box1);
    			f.add(box2);
    			f.add(box3);
    			f.add(box4);
    			f.add(box5);
    			f.add(box6);
    			f.add(box7);
    			f.add(box8);
    			f.add(box9);
    			f.add(box10);
    			f.add(box11);
    			f.add(box12);
    			f.add(box13);
    			f.add(box14);
    			f.add(vafOkButton);
    			f.add(vafCancelButton);
    			f.setLayout(null);
    			f.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    			f.setLocationRelativeTo(null);
    			f.setVisible(true);
    			
    			vafOkButton.addActionListener(new ActionListener() {  
    		        public void actionPerformed(ActionEvent e) {    
    		        	//Add Code Here
    	    			int vaf1 =  Integer.parseInt((String) box1.getItemAt(box1.getSelectedIndex()));
    	    			int vaf2 =  Integer.parseInt((String) box2.getItemAt(box2.getSelectedIndex()));
    	    			int vaf3 =  Integer.parseInt((String) box3.getItemAt(box3.getSelectedIndex()));
    	    			int vaf4 =  Integer.parseInt((String) box4.getItemAt(box4.getSelectedIndex()));
    	    			int vaf5 =  Integer.parseInt((String) box5.getItemAt(box5.getSelectedIndex()));
    	    			int vaf6 =  Integer.parseInt((String) box6.getItemAt(box6.getSelectedIndex()));
    	    			int vaf7 =  Integer.parseInt((String) box7.getItemAt(box7.getSelectedIndex()));
    	    			int vaf8 =  Integer.parseInt((String) box8.getItemAt(box8.getSelectedIndex()));
    	    			int vaf9 =  Integer.parseInt((String) box9.getItemAt(box9.getSelectedIndex()));
    	    			int vaf10 =  Integer.parseInt((String) box10.getItemAt(box10.getSelectedIndex()));
    	    			int vaf11 =  Integer.parseInt((String) box11.getItemAt(box11.getSelectedIndex()));
    		        	int vaf12 =  Integer.parseInt((String) box12.getItemAt(box12.getSelectedIndex()));
    		        	int vaf13 =  Integer.parseInt((String) box13.getItemAt(box13.getSelectedIndex()));
    		        	int vaf14 =  Integer.parseInt((String) box14.getItemAt(box14.getSelectedIndex()));
    		        	
    		        	int 	final_ans = vaf1+vaf2+vaf3+vaf4+vaf5+vaf6+vaf7+vaf8+vaf9+vaf10+vaf11+vaf12+vaf13+vaf14; 
    		        	vaf_total_value = final_ans;
    		        	JOptionPane.showMessageDialog(f, final_ans, "VAF Total Value", JOptionPane.INFORMATION_MESSAGE);
    		        	VAFField.setText(vaf_total_value+"");
    		        	
    		        	f.dispose();
    		        }  
    			}); 
    			
    			vafCancelButton.addActionListener(new ActionListener() {  
    				public void actionPerformed(ActionEvent e) {       
    					f.dispose();
    				}  
    			}); 
    			
    			
    		}
    	}
    	class changeLanguageItemListener implements ActionListener  {
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
            	    		 languageField.setText("ASSEMBLER");
//    						text = "ASSEMBLER";
            	    		newProjectFrame.dispose();
            	    		return;
            			}
            			else if (checkBoxes[1].isSelected())  {
            				fp.currentLanguge = FPModel.LANGUAGE.ADA_95;
            	    		 languageField.setText("ADA 95");
//    						text = "ADA 95";
            	    		newProjectFrame.dispose();
            	    		return;
            			}
            			else if (checkBoxes[2].isSelected())  {
            				fp.currentLanguge = FPModel.LANGUAGE.CL;
            	    		 languageField.setText("C");
//    						text = "C";
            	    		newProjectFrame.dispose();
            	    		return;
            			}
            			else if (checkBoxes[3].isSelected())  {
            				fp.currentLanguge = FPModel.LANGUAGE.CLPLUS;
            	    		 languageField.setText("C++");
//    						text = "C++";
            	    		newProjectFrame.dispose();
            	    		return;
            			}
            			else if (checkBoxes[4].isSelected())  {
            				fp.currentLanguge = FPModel.LANGUAGE.CSHARP;
            	    		 languageField.setText("C#");
//    						text = "C#";
            	    		newProjectFrame.dispose();
            	    		return;
            			}
            			else if (checkBoxes[5].isSelected())  {
            				fp.currentLanguge = FPModel.LANGUAGE.COBOL;
            	    		 languageField.setText("COBOL");
//    						text = "COBOL";
            	    		newProjectFrame.dispose();
            	    		return;
            			}
            			else if (checkBoxes[6].isSelected())  {
            				fp.currentLanguge = FPModel.LANGUAGE.FORTRAN;
            	    		 languageField.setText("FORTRAN");
//    						text = "FORTRAN";
            	    		newProjectFrame.dispose();
            	    		return;
            			}
            			else if (checkBoxes[7].isSelected())  {
            				fp.currentLanguge = FPModel.LANGUAGE.HTML;
            	    		 languageField.setText("HTML");
//    						text = "HTML";
            	    		newProjectFrame.dispose();
            	    		return;
            			}
            			else if (checkBoxes[8].isSelected())  {
            				fp.currentLanguge = FPModel.LANGUAGE.JAVA;
            	    		 languageField.setText("JAVA");
//    						text = "JAVA";
            	    		newProjectFrame.dispose();
            	    		return;
            			}
            			else if (checkBoxes[9].isSelected())  {
            				fp.currentLanguge = FPModel.LANGUAGE.JAVASCRIPT;
            	    		 languageField.setText("JAVASCRIPT");
//    						text = "JAVASCRIPT";
            	    		newProjectFrame.dispose();
            	    		return;
            			}
            			else if (checkBoxes[10].isSelected())  {
            				fp.currentLanguge = FPModel.LANGUAGE.VBSCRIPT;
            	    		 languageField.setText("VBSCRIPT");
//    						text = "VBSCRIPT";
            	    		newProjectFrame.dispose();
            	    		return;
            			}
            			else if (checkBoxes[11].isSelected())  {
            				fp.currentLanguge = FPModel.LANGUAGE.VISUAL_BASIC;
            	    		 languageField.setText("VISUAL_BASIC");
//    						text = "VISUAL BASIC";
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
    }
}


















