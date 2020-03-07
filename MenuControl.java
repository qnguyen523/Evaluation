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
    // code factoring MenuItem extends JMenuItem
    private JMenuItem preferences_option1,
    					metrics_option1;
    
    private JMenuItem[] file_option = new JMenuItem[4];
    
    private JFrame frame;
    private String projectName;
    
    public String text = new String();
    public FPModel fp = new FPModel();
    LanguageItemListener lanItem = new LanguageItemListener();
    // constructor
    public MenuControl(String projectName) { 
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
    	
    	// add ActionListener to JMenu file_option[0]
    	NewItemListener newItem = new NewItemListener();
    	newItem.setField(frame);
    	file_option[0].addActionListener(newItem);
    	
    	// add to JMenu file
    	file.add(file_option[0]);
    	file.add(file_option[1]);
    	file.add(file_option[2]);
    	file.add(file_option[3]);
    	
    	
    	// create menu items for preferences
    	preferences_option1 = new JMenuItem("Language");
    	lanItem.setField(fp, text);
    	preferences_option1.addActionListener(lanItem);
    	
    	// add to preferences
    	preferences.add(preferences_option1);
    	
    	// create menu items for metrics
    	metrics_option1 = new JMenuItem("Function Points");
    	// add ActionListener for metrics
    	FunctionPointItemListener fpItem = new FunctionPointItemListener();
    	fpItem.setFields(lanItem,frame,fp);
    	metrics_option1.addActionListener(fpItem);

    	// add to metrics
    	metrics.add(metrics_option1);
    	
    	// frame
    	frame.setLayout(new BorderLayout());
    	frame.setJMenuBar(menuBar);
    	frame.setSize(800, 600);
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


















