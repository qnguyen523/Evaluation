import java.awt.*;
import javax.swing.*; 
// this will show result for language metrics
public class SuiteControl {
	private JLabel weightingFactors;
	private JPanel panel;
	public SuiteControl() {
		weightingFactors = new JLabel("", JLabel.CENTER);
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		weightingFactors.setText("Weighting Factors");
		panel.add(weightingFactors);
	}
	public JPanel getSuitePanel() {
		return panel;
	}
	public JLabel getWeightingFactors() {
		return weightingFactors;
	}
}
