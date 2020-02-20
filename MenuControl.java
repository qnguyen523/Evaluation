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
    	frame.setJMenuBar(menuBar);
    	frame.setSize(800, 600);
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    		newProjectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	}    
    }
    // get the menuBar
    public JMenuBar getMenuBar() {
    	return menuBar;
    }
   
    protected JTabbedPane tabPane;
    class FunctionPointItemListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		frame.setLayout(new BorderLayout());
    		tabPane = new JTabbedPane();
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
    	    {
    	    	// FPModel: Function Points model
    	    	FPModel EI = new FPModel();
    	    	FPModel EO = new FPModel();
    	    	FPModel EInq = new FPModel();
    	    	FPModel ILF = new FPModel();
    	    	FPModel EIF = new FPModel();
        	    // labels 
    	    	EI.label = new JLabel("External Inputs");
    	    	EO.label = new JLabel("External Outputs");
    	    	EInq.label = new JLabel("External Inquiries");
    	    	ILF.label = new JLabel("Internal Logical Files");
    	    	EIF.label = new JLabel("External Interface Files");
    		    //JTextFields
    	    	EI.textField = new JTextField(2);
    	    	EO.textField = new JTextField(2);
    	    	EInq.textField = new JTextField(2);
    	    	ILF.textField = new JTextField(2);
    	    	EIF.textField = new JTextField(2);
    		    // JRadioButton
    	    	EI.radioButtons[SIMPLE] = new JRadioButton("3");
    	    	EI.radioButtons[AVERAGE] = new JRadioButton("4", true);
    	    	EI.radioButtons[COMPLEX] = new JRadioButton("6");

    	    	EO.radioButtons[SIMPLE] = new JRadioButton("4");
    	    	EO.radioButtons[AVERAGE] = new JRadioButton("5", true);
    	    	EO.radioButtons[COMPLEX] = new JRadioButton("7");
    	    	
    	    	EInq.radioButtons[SIMPLE] = new JRadioButton("3");
    	    	EInq.radioButtons[AVERAGE] = new JRadioButton("4", true);
    	    	EInq.radioButtons[COMPLEX] = new JRadioButton("6");
    	    	
    	    	ILF.radioButtons[SIMPLE] = new JRadioButton("7");
    	    	ILF.radioButtons[AVERAGE] = new JRadioButton("10", true);
    	    	ILF.radioButtons[COMPLEX] = new JRadioButton("15");
    	    	
    	    	EIF.radioButtons[SIMPLE] = new JRadioButton("5");
    	    	EIF.radioButtons[AVERAGE] = new JRadioButton("7", true);
    	    	EIF.radioButtons[COMPLEX] = new JRadioButton("10");
    	    	
    	    	// group the radio button
    	    	EI.group = new ButtonGroup();
    	    	EI.addToGroup(EI.group, EI.radioButtons);
    	    	
    	    	EO.group = new ButtonGroup();
    	    	EO.addToGroup(EO.group, EO.radioButtons);
    	    	
    	    	EInq.group = new ButtonGroup();
    	    	EInq.addToGroup(EInq.group, EInq.radioButtons);
    	    	
    	    	ILF.group = new ButtonGroup();
    	    	ILF.addToGroup(ILF.group, ILF.radioButtons);
    	    	
    	    	EIF.group = new ButtonGroup();
    	    	EIF.addToGroup(EIF.group, EIF.radioButtons);
    	    	
    	    	// output
    	    	EI.output = new JTextField(2);
    	    	EI.output.setEditable(false);
    	    	EO.output = new JTextField(2);
    	    	EO.output.setEditable(false);
    	    	EInq.output = new JTextField(2);
    	    	EInq.output.setEditable(false);
    	    	ILF.output = new JTextField(2);
    	    	ILF.output.setEditable(false);
    	    	EIF.output = new JTextField(2);
    	    	EIF.output.setEditable(false);
    	    	
    	    	// the second half of panel
    	    	Button compute_FP_button = new Button("Compute FP");
    	    	Button VAF_button = new Button("Value Adjustment");
    	    	Button compute_code_size_button = new Button("Compute Code Size");
    	    	Button change_language_button = new Button("Choose Language");
    	    	JLabel currentLanguage = new JLabel("Current Language");
    	    	JLabel totalCount = new JLabel("Total Count");
    	    	JTextField total = new JTextField(2);
    	    	total.setEditable(false);
    	    	JTextField languageField = new JTextField(2);
    	    	languageField.setEditable(false);
    	    	JTextField FPField = new JTextField(2);
    	    	FPField.setEditable(false);
    	    	JTextField VAFField = new JTextField("0", 2);
    	    	VAFField.setEditable(false);
    	    	JTextField CodeSizeField = new JTextField(2);
    	    	CodeSizeField.setEditable(false);
    	    	
        	    // setbounds
    	    	EI.label.setBounds(10,50,180,20);
    	    	EO.label.setBounds(10,70,180,20);
    	    	EInq.label.setBounds(10,90,180,20);
    	    	ILF.label.setBounds(10,110,180,20);
    	    	EIF.label.setBounds(10,130,180,20);
    	    	EI.textField.setBounds(190,50,50,20);
    	    	EO.textField.setBounds(190,70,50,20);
    	    	EInq.textField.setBounds(190,90,50,20);
    	    	ILF.textField.setBounds(190,110,50,20);
    	    	EIF.textField.setBounds(190,130,50,20);
    	    	EI.radioButtons[SIMPLE].setBounds(280,50,90,20);
    	    	EI.radioButtons[AVERAGE].setBounds(370,50,90,20);
    	    	EI.radioButtons[COMPLEX].setBounds(460,50,90,20);
    	    	EO.radioButtons[SIMPLE].setBounds(280,70,90,20);
    	    	EO.radioButtons[AVERAGE].setBounds(370,70,90,20);
    	    	EO.radioButtons[COMPLEX].setBounds(460,70,90,20);
    	    	EInq.radioButtons[SIMPLE].setBounds(280,90,90,20);
    	    	EInq.radioButtons[AVERAGE].setBounds(370,90,90,20);
    	    	EInq.radioButtons[COMPLEX].setBounds(460,90,90,20);
    	    	ILF.radioButtons[SIMPLE].setBounds(280,110,90,20);
    	    	ILF.radioButtons[AVERAGE].setBounds(370,110,90,20);
    	    	ILF.radioButtons[COMPLEX].setBounds(460,110,90,20);
    	    	EIF.radioButtons[SIMPLE].setBounds(280,130,90,20);
    	    	EIF.radioButtons[AVERAGE].setBounds(370,130,90,20);
    	    	EIF.radioButtons[COMPLEX].setBounds(460,130,90,20);
    	    	EI.output.setBounds(550,50,50,20);
    	    	EO.output.setBounds(550,70,50,20);
    	    	EInq.output.setBounds(550,90,50,20);
    	    	ILF.output.setBounds(550,110,50,20);
    	    	EIF.output.setBounds(550,130,50,20);
    	    	totalCount.setBounds(10,160,180,20);
    	    	total.setBounds(550,160,50,20);
    	    	compute_FP_button.setBounds(10,200,180,20);
    	    	VAF_button.setBounds(10,240,180,20);
    	    	compute_code_size_button.setBounds(10,280,180,20);
    	    	change_language_button.setBounds(10,320,180,20);
    	    	currentLanguage.setBounds(240,280,140,20);
    	    	languageField.setBounds(380,280,100,20);
    	    	FPField.setBounds(550,200,50,20);
    	    	VAFField.setBounds(550,240,50,20);
    	    	CodeSizeField.setBounds(550,280,50,20);
    	    	// currentLanguage languageField FPField VAFField CodeSizeField
        	    // add to panel
    	    	panel.add(EI.label);
    	    	panel.add(EO.label);
    	    	panel.add(EInq.label);
    	    	panel.add(ILF.label);
    	    	panel.add(EIF.label);
    	    	panel.add(EI.textField);
    	    	panel.add(EO.textField);
    	    	panel.add(EInq.textField);
    	    	panel.add(ILF.textField);
    	    	panel.add(EIF.textField);
    	    	panel.add(EI.radioButtons[SIMPLE]);
    	    	panel.add(EI.radioButtons[AVERAGE]);
    	    	panel.add(EI.radioButtons[COMPLEX]);
    	    	panel.add(EO.radioButtons[SIMPLE]);
    	    	panel.add(EO.radioButtons[AVERAGE]);
    	    	panel.add(EO.radioButtons[COMPLEX]);
    	    	panel.add(EInq.radioButtons[SIMPLE]);
    	    	panel.add(EInq.radioButtons[AVERAGE]);
    	    	panel.add(EInq.radioButtons[COMPLEX]);
    	    	panel.add(ILF.radioButtons[SIMPLE]);
    	    	panel.add(ILF.radioButtons[AVERAGE]);
    	    	panel.add(ILF.radioButtons[COMPLEX]);
    	    	panel.add(EIF.radioButtons[SIMPLE]);
    	    	panel.add(EIF.radioButtons[AVERAGE]);
    	    	panel.add(EIF.radioButtons[COMPLEX]);
    	    	panel.add(EI.output);
    	    	panel.add(EO.output);
    	    	panel.add(EInq.output);
    	    	panel.add(ILF.output);
    	    	panel.add(EIF.output);
    	    	panel.add(currentLanguage);
    	    	panel.add(compute_FP_button);
    	    	panel.add(VAF_button);
    	    	panel.add(compute_code_size_button);
    	    	panel.add(change_language_button);
    	    	panel.add(languageField);
    	    	panel.add(FPField);
    	    	panel.add(VAFField);
    	    	panel.add(CodeSizeField);
    	    	panel.add(totalCount);
    	    	panel.add(total);
    	    }
    	}
    }
}
