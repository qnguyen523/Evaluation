package ANTLR;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.antlr.runtime.*;
import org.antlr.runtime.RecognitionException;

import ANTLR.AddCodeListener.Statistics;
import SMI.SaveItemListener;

public class AddCodeListener implements ActionListener {
	public JFrame frame;
	public JTabbedPane tabPane;
	public JMenuItem statistics;
	public JMenuItem add_code;
	public ArrayList<String> file_names;
	public ArrayList<String> names;
	public SaveItemListener saveItem;
	// project tree
	JTree jt;
	public Map<String, String> file_map;
	
	public class MyPanel {
		public JPanel panel;
		public JTextArea display;
		public JScrollPane sp;
		public StringBuilder sb;
	}
	public MyPanel[] my_panels;
	public void setFields(JFrame frame,JTabbedPane tabPane,
			ArrayList<String> file_names,SaveItemListener saveItem,
			JMenuItem statistics,JTree jt,Map<String, String> file_map,
			JMenuItem add_code) {
		this.frame=frame;this.tabPane=tabPane;
		this.file_names=file_names;this.saveItem=saveItem;
		this.statistics=statistics;
		this.jt=jt;
		this.file_map=file_map;
		this.add_code=add_code;
	}
	public void actionPerformed(ActionEvent e) {
		names = new ArrayList<String>();
//		file_names = new ArrayList<>();
		File f = new File("/Users/Peter/Documents/workspace2/Metrics-Suite/");
		JFileChooser inputFile=new JFileChooser(f);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".java", "java");
		inputFile.setFileFilter(filter);
		inputFile.setMultiSelectionEnabled(true);
		// open file
		if(inputFile.showOpenDialog(frame)==JFileChooser.APPROVE_OPTION)
		{
			
			// create panels
			File[] files = inputFile.getSelectedFiles();
			my_panels = new MyPanel[files.length];
			// validate
			DefaultMutableTreeNode node;
			DefaultTreeModel model = (DefaultTreeModel)jt.getModel();
			DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
			for (int i=0; i<my_panels.length; i++) {
				node = new DefaultMutableTreeNode(files[i].getName());
				TreePath path = null;
				if (path != null || file_map.containsKey(files[i].getName())) {
					JOptionPane.showMessageDialog(frame, "Error. Cannot add the same file", 
							"Error", JOptionPane.ERROR_MESSAGE);
					System.err.println("Cannot add the same file");
					return;
				}
			}
			
			for (int i=0; i<my_panels.length; i++) {
				// add tree node to the root
//				DefaultMutableTreeNode node = new DefaultMutableTreeNode(files[i].getName());
//				DefaultTreeModel model = (DefaultTreeModel)jt.getModel();
//				DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
//				TreePath path = null;
//				int row = (path == null ? 0 : jt.getRowForPath(path));
//				path = jt.getNextMatch(files[i].getName(), row, Position.Bias.Forward);
//				if (path != null || file_map.containsKey(files[i].getName())) {
//					JOptionPane.showMessageDialog(frame, "Error. Cannot add the same file", 
//							"Error", JOptionPane.ERROR_MESSAGE);
//					System.err.println("Cannot add the same file");
//					return;
//				}
				node = new DefaultMutableTreeNode(files[i].getName());
				root.add(node);
				model.reload();
				// parse 
				if (file_map.containsKey(files[i].getName())) {
					JOptionPane.showMessageDialog(frame, "Error. Cannot parse the same file", 
							"Error", JOptionPane.ERROR_MESSAGE);
					System.err.println("Cannot parse the same file");
					return;
				}
				
				my_panels[i] = new MyPanel();
				my_panels[i].panel = new JPanel();
				my_panels[i].sb = new StringBuilder();
				names.add(files[i].getPath());
				
				new Statistics().parse(files[i],my_panels[i].sb,my_panels[i],tabPane,true,tabPane.getTabCount());
				
				// put into map
				String key = files[i].getName();
				String value = files[i].getPath();
				file_map.put(key, value);
			}
			
			// add tabPane to frame
//			frame.getContentPane().add(tabPane, BorderLayout.CENTER);
			frame.setVisible(true);
			
			// save
			file_names.addAll(names);
//			saveItem.saving_list.file_names.addAll(file_names);
			// trigger to show statistics
			if (statistics.getActionListeners().length == 0)
				statistics.addActionListener(new Statistics());
			// set enable
			statistics.setEnabled(true);
			add_code.setEnabled(false);
		} else {
			// Cancel button is clicked
			System.err.println("Cancel button is clicked");
			return;
		}
	}

	// inner class
	public class Statistics implements ActionListener  {
		public void parse(File file, StringBuilder sb, MyPanel mp, JTabbedPane tabPane, 
				boolean isFirstOpened, int atIndex) {
			try {
				JavaJavaLexer lexer = new JavaJavaLexer(new ANTLRFileStream(file.getPath()));
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				JavaJavaParser parser = new JavaJavaParser(tokens);
				parser.compilationUnit();
				file_stats(parser,lexer,file,sb);
				halstead(parser,lexer,sb);
				mccabe(parser,sb);
				String msg = "File name: "+file.getName()+
						"\nClick Project code statistics option for statistics";
				mp.display = new JTextArea((isFirstOpened) ? msg : sb.toString(),25,50);
				mp.display.setEditable(false);
				mp.sp = new JScrollPane(mp.display);
				mp.sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				mp.panel.add(mp.sp);
				
//				tabPane.addTab(file.getName(), mp.panel);
				tabPane.insertTab(file.getName(), null, mp.panel, "", atIndex);
				tabPane.setSelectedIndex(atIndex);
				
			} catch (IOException | RecognitionException e1) {
				e1.printStackTrace();
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (names == null || names.isEmpty()) {
				System.err.println("Errors with file names");
				return;
			}
			// show statistics
			for (int i=0; i<names.size(); i++) {
				my_panels[i].display.setText(my_panels[i].sb.toString());
			}
			
			// save
			System.out.println("file_names in AddCodeLis: "+names);
			// set enable
						statistics.setEnabled(false);
						add_code.setEnabled(true);
		}
		public void file_stats(JavaJavaParser parser, JavaJavaLexer lexer, File file,StringBuilder sb) {
			float percent_of_comments = (float) (lexer.commentcount * 100.0/file.length());
			percent_of_comments = (float)Math.round(percent_of_comments * 1000) / 1000;
			sb.append("File name: "+file.getName()+"\n");
			sb.append("File length in bytes: "+ file.length()+"\n");
			sb.append("File white space: "+lexer.ws+"\n");
			sb.append("File comment space in bytes: "+lexer.commentcount+"\n");
//			String hold = String.format("Comment percentage of file: %.3f \n", percent_of_comments);
			sb.append("Comment percentage of file: "+percent_of_comments+"%\n");
			
//			System.out.println("Line of code: "+lexer.line_of_code);
			System.err.println("comment count: "+lexer.commentcount);
		}
		public void mccabe(JavaJavaParser parser,StringBuilder sb) {
			sb.append("\n\nMcCabe's Cyclomatic Complexity: "+"\n");
			for (String s : parser.JavaMetrics.mccabeValues)
				sb.append("\t"+s+"\n");
		}
		public  void halstead(JavaJavaParser parser,JavaJavaLexer lexer,StringBuilder sb) {
			int n1,n2,N1,N2,N,n;
			float V,D,E,T,B;
			n1 = parser.JavaMetrics.uniqueSpecial.size() + parser.JavaMetrics.uniqueKeywords.size();
			n2 = parser.JavaMetrics.uniqueIdentifiers.size() 
			+ parser.JavaMetrics.uniqueConstants.size();
			N1 = parser.specialcount + parser.keywordCount;
			N2 = parser.identcount + lexer.constantcount;
			N = N1 + N2;
			n = n1 + n2;
			
			V = (float) (N* Math.round(Math.log(n) / Math.log(2)));
			D = (float) ((n1/2.0) * (N2/n2));
			E = (float) (D * V);
			T = (float) (E/18.0);
			B = (float) (V/3000.0);
			sb.append("Halstead:"+"\n");
			sb.append("\tUnique operators: " + n1+"\n");
			sb.append("\tUnique operands: " + n2+"\n");
			sb.append("\tTotal operators: " + N1+"\n");
			sb.append("\tTotal operands: " + N2+"\n");
			sb.append("\tProgram length (N) = " + N+"\n");
			sb.append("\tProgram vocabulary (n) = " + n+"\n");
			sb.append("\tVolume = " + V+"\n");
			sb.append("\tDifficulty = " + D+"\n");
			sb.append("\tEffort = " + E+"\n");
			String hold = String.format("\tTime = %.3f sec (%.3f mins or %.3f hrs or %.3f person-months)\n",
					T,T/60.0,T/(60.0*60.0),T/(60.0*60.0*140.0));
			sb.append(hold);
			sb.append("\tBugs expected = " + B+"\n");
		}
	}
}
