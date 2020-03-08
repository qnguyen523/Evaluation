import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewItemListener implements ActionListener {
    	public String projectNameField;
    	JFrame frame;
    	ProjectInfoModel projectInfo;// = new ProjectInfoModel(); 
    	public void setFields(JFrame frame,ProjectInfoModel projectInfo) {
    		this.frame=frame;this.projectInfo=projectInfo;
    	}
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
//    		JTextField newProjectText = new JTextField("Project Name cannot be empty", 5);
    		projectInfo.newProjectText = new JTextField("Project Name cannot be empty", 5);
    		
    		projectInfo.newProjectText.setForeground(Color.GRAY);
    		projectInfo.newProjectText.addFocusListener(new FocusListener() {
    			@Override
    			public void focusGained(FocusEvent e) {
    				if(projectInfo.newProjectText.getText().equals("Project Name cannot be empty")
    						&& projectNameField.isEmpty())
    				{
    					projectInfo.newProjectText.setText("");
    					projectInfo.newProjectText.setForeground(Color.BLACK);
    				}

    			}
    			@Override
    			public void focusLost(FocusEvent e) {
    				if (projectInfo.newProjectText.getText().isEmpty()) {
    					projectInfo.newProjectText.setForeground(Color.GRAY);
    					projectInfo.newProjectText.setText("Project Name cannot be empty");
    					projectNameField = "";
    				}
    				else projectNameField = projectInfo.newProjectText.getText();
    			}
    		});

//    		JTextField productNameText = new JTextField(5);
//    		JTextField creatorText = new JTextField(5);
//    		JTextArea commentTextArea = new JTextArea();
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
    		projectInfo.newProjectText.setBounds(110,60,250,20);

    		productName.setBounds(10,90,250,20);
    		projectInfo.productNameText.setBounds(110,90,250,20);

    		creator.setBounds(10,120,250,20);
    		projectInfo.creatorText.setBounds(110,120,250,20);

    		comments.setBounds(10,150,250,20);
    		projectInfo.commentTextArea.setBounds(10,170,500,200);

    		OKButton.setBounds(10,400,70,20);
    		CancelButton.setBounds(90,400,70,20);
    		// add to JFrame newProjectFrame
    		newProjectFrame.add(newProject);

    		newProjectFrame.add(projectName);
    		newProjectFrame.add(projectInfo.newProjectText);

    		newProjectFrame.add(productName);
    		newProjectFrame.add(projectInfo.productNameText);

    		newProjectFrame.add(creator);
    		newProjectFrame.add(projectInfo.creatorText);

    		newProjectFrame.add(comments);
    		newProjectFrame.add(projectInfo.commentTextArea);

    		newProjectFrame.add(OKButton);
    		newProjectFrame.add(CancelButton);

    		// testing
    		newProjectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	}    
    }