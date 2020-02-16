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
//    	frame.setLayout(new FlowLayout());
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
        	newProjectFrame.setVisible(true);
        	// somebody needs to continue here
        }    
     }
    class LanguageItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
        	JFrame newProjectFrame=new JFrame("Choose a language");
        	newProjectFrame.setSize(300,600);
        	newProjectFrame.setVisible(true);
        	// somebody needs to continue here
        }
    }
    protected JTabbedPane tabPane;
    public class FunctionPointItemListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		frame.setLayout(new BorderLayout());
    		tabPane = new JTabbedPane();
    		tabPane.addTab("Function Points", new JPanel());
    		frame.getContentPane().add(tabPane, BorderLayout.CENTER);
    	    frame.setVisible(true);
    	}
    }
}
//