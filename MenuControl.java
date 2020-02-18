import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

// create a menu bar
public class MenuControl {
	// menubar 
    private JMenuBar menuBar; 
  
    // JMenu 
    private JMenu file, edit, preferences, metrics, help;
  
    // Menu items 
    private JMenuItem preferences_option1,
    					metrics_option1;
    
    private JMenuItem[] file_option = new JMenuItem[4];
    
    private TabFrame frame;
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
    	frame = new TabFrame(projectName);
    	frame.setJMenuBar(menuBar);
    	frame.setSize(800, 600);
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // get the menuBar
    public JMenuBar getMenuBar() {
    	return menuBar;
    }
    
    class NewItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {            
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
        			newProjectText.setText("");
        			newProjectText.setForeground(Color.BLACK);
        		}
        		@Override
        		public void focusLost(FocusEvent e) {
        			if (newProjectText.getText().isEmpty()) {
        				newProjectText.setForeground(Color.GRAY);
        				newProjectText.setText("Project Name cannot be empty");
        			}
        		}
        	});
        	
        	JTextField projectNameText = new JTextField(5);
        	JTextField creatorText = new JTextField(5);
        	JTextArea commentTextArea = new JTextArea();
        	// buttons
        	Button OKButton = new Button("OK");
            Button CancelButton = new Button("Cancel");
            
            // need to continue
            // from here
            OKButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  System.out.println("Ok Button clicked.");
               }
            });
            
            CancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                   System.out.println("Cancel Button clicked.");
                }
             });
            // to here
            
        	// setbounds
        	newProject.setBounds(50,20,250,20);
        	
        	projectName.setBounds(10,60,250,20);
        	newProjectText.setBounds(110,60,250,20);
        	
        	productName.setBounds(10,90,250,20);
        	projectNameText.setBounds(110,90,250,20);
        	
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
        	newProjectFrame.add(projectNameText);
        	
        	newProjectFrame.add(creator);
        	newProjectFrame.add(creatorText);
        	
        	newProjectFrame.add(comments);
        	newProjectFrame.add(commentTextArea);
        	
        	newProjectFrame.add(OKButton);
        	newProjectFrame.add(CancelButton);
        	
        	newProjectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }    
     }
    class LanguageItemListener implements ActionListener {
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
        	checkBoxes[0] = new JCheckBox("Assembly");
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
        	doneButton.addActionListener(new ActionListener() {
        		@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
        			if (checkBoxes[0].isSelected())
        				System.out.println(checkBoxes[0].getText() + " is clicked");
        			else
        				System.out.println("Nothing is clicked");
        		}
        	});
    		// add to JFrame newProjectFrame
        	newProjectFrame.add(selectLanguage);
        	for (JCheckBox c : checkBoxes) newProjectFrame.add(c);
        	newProjectFrame.add(doneButton);
        }
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
        	// somebody needs to continue here
    	    panel.setLayout(null);
    	    // labels
    	    JLabel weightingFactors = new JLabel("Weighting Factors", JLabel.CENTER);
    	    JLabel simple = new JLabel("Simple   Average   Complex", JLabel.CENTER);
    	    panel.add(weightingFactors);
    	    panel.add(simple);
    	    
    	    // setbounds
    	    weightingFactors.setBounds(0,10,800,20);
    	    simple.setBounds(0,30,800,20);
    	}
    }
}
//