import java.awt.*;
import javax.swing.*;
public class main {

	public static void main(String[] args) {
//		System.out.println("Hello");
		JFrame frame=new JFrame("CECS 543 Metrics Suite");
//		frame.setLayout(new FlowLayout());
		SuiteControl suiteControl = new SuiteControl();
		MenuControl menu = new MenuControl();
		frame.setSize(800, 600);
		// add components
		frame.setJMenuBar(menu.getMenuBar());
		frame.add(suiteControl.getSuitePanel());
		
		// finalize frame
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
