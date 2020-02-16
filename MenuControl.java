import javax.swing.*; 
// create a menu bar
public class MenuControl {
	// menubar 
    private JMenuBar menuBar; 
  
    // JMenu 
    private JMenu file, edit, preferences, metrics, help;
  
    // Menu items 
    private JMenuItem file_option1, file_option2, 
    					file_option3, file_option4,
    					preferences_option1,
    					metrics_option1;
    
    // default constructor
    public MenuControl() {
    	menuBar = new JMenuBar();
    	// create JMenu objects
    	file = new JMenu("File");
    	edit = new JMenu("Edit");
    	preferences = new JMenu("Preferences");
    	metrics = new JMenu("Metrics");
    	help = new JMenu("Help");
    	// create menu items for file
    	file_option1 = new JMenuItem("New"); 
    	file_option2 = new JMenuItem("Open"); 
    	file_option3 = new JMenuItem("Save"); 
    	file_option4 = new JMenuItem("Exit");
    	
    	// add to file
    	file.add(file_option1);
    	file.add(file_option2);
    	file.add(file_option3);
    	file.add(file_option4);
    	
    	// create menu items for preferences
    	preferences_option1 = new JMenuItem("Language");
    	
    	// add to preferences
    	preferences.add(preferences_option1);
    	
    	// create menu items for metrics
    	metrics_option1 = new JMenuItem("Function Points");
    	metrics.add(metrics_option1);
    	
    	// add to menuBar
    	menuBar.add(file);
    	menuBar.add(edit);
    	menuBar.add(preferences);
    	menuBar.add(metrics);
    	menuBar.add(help);
    }
    // get the menuBar
    public JMenuBar getMenuBar() {
    	return menuBar;
    }
}
