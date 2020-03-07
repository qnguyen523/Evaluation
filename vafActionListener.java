import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class vafActionListener implements ActionListener {
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
	        	vaf_total_value.value = final_ans;
	        	JOptionPane.showMessageDialog(f, final_ans, "VAF Total Value", JOptionPane.INFORMATION_MESSAGE);
	        	VAFField.setText(final_ans+"");
	        	
	        	f.dispose();
	        }  
		}); 
		
		vafCancelButton.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {       
				f.dispose();
			}  
		}); 
	}
	public VafValue vaf_total_value; public JTextField VAFField;
	public void setFields(VafValue vaf_total_value, JTextField VAFField) {
		this.VAFField=VAFField;this.vaf_total_value=vaf_total_value;
	}
}
