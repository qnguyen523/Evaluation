package ANTLR;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.*;
import org.antlr.runtime.*;
import org.antlr.runtime.RecognitionException;

import ANTLR.AddCodeListener.Statistics;
import SMI.SaveItemListener;

public class AddCodeListener implements ActionListener {
	public JFrame frame;
	public JTabbedPane tabPane;
	public JMenuItem statistics;
	public ArrayList<String> file_names;
	public SaveItemListener saveItem;
	public class MyPanel {
		JPanel panel;
		JTextArea display;
		JScrollPane sp;
	}
	public MyPanel[] my_panels;
	public void setFields(JFrame frame,JTabbedPane tabPane,
			ArrayList<String> file_names,SaveItemListener saveItem,JMenuItem statistics) {
		this.frame=frame;this.tabPane=tabPane;
		this.file_names=file_names;this.saveItem=saveItem;
		this.statistics=statistics;
	}
	public void actionPerformed(ActionEvent e) {
		file_names = new ArrayList<>();
		File f = new File("/Users/Peter/Documents/workspace/Test-Antlr/");
		JFileChooser inputFile=new JFileChooser(f);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".java", "txt", "java");
		inputFile.setFileFilter(filter);
		inputFile.setMultiSelectionEnabled(true);
		// open file
		if(inputFile.showOpenDialog(frame)==JFileChooser.APPROVE_OPTION)
		{
			// create panels
			File[] files = inputFile.getSelectedFiles();
			my_panels = new MyPanel[files.length];
			for (int i=0; i<my_panels.length; i++) {
				my_panels[i] = new MyPanel();
				my_panels[i].panel = new JPanel();
				file_names.add(files[i].getPath());
				String msg = "File name: "+files[i].getName()+
						"\nClick Project code statistics option for statistics";
				my_panels[i].display = new JTextArea(msg,25,60);
				my_panels[i].display.setEditable(false);
				my_panels[i].sp = new JScrollPane(my_panels[i].display);
				my_panels[i].sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				my_panels[i].panel.add(my_panels[i].sp);
				tabPane.addTab(files[i].getName(), my_panels[i].panel);
			}
			
			// add tabPane to frame
			frame.getContentPane().add(tabPane, BorderLayout.CENTER);
			frame.setVisible(true);
			
			statistics.addActionListener(new Statistics());
		} else {
			// Cancel button is clicked
			System.err.println("Cancel button is clicked");
			return;
		}
	}

	// inner class
	public class Statistics implements ActionListener  {
		public void actionPerformed(ActionEvent e) {
			if (file_names == null || file_names.isEmpty()) {
				System.err.println("Errors with file names");
				return;
			}
			
			// show statistics
			for (int i=0; i<file_names.size(); i++) {
				File file = new File(file_names.get(i));
				StringBuilder sb = new StringBuilder();
				try {
					JavaJavaLexer lexer = new JavaJavaLexer(new ANTLRFileStream(file_names.get(i)));
					CommonTokenStream tokens = new CommonTokenStream(lexer);
					JavaJavaParser parser = new JavaJavaParser(tokens);
					parser.compilationUnit();
					file_stats(parser,lexer,file,sb);
					halstead(parser,lexer,sb);
					mccabe(parser,sb);
				} catch (IOException | RecognitionException e1) {
					e1.printStackTrace();
				}
				
				// test output
				System.out.println(sb);
				
				my_panels[i].display.setText(sb.toString());
			}
			
			// save
			System.out.println("file_names in AddCodeLis: "+file_names);
//			saveItem.saving_list.file_names = file_names;
			saveItem.saving_list.file_names.addAll(file_names);
		}
		public void file_stats(JavaJavaParser parser, JavaJavaLexer lexer, File file,StringBuilder sb) {
			float percent_of_comments = (float) (lexer.line_of_comments * 100.0/lexer.line_of_code);
			sb.append("File name: "+file.getName()+"\n");
			sb.append("File length in bytes: "+ file.length()+"\n");
			sb.append("File white space: "+lexer.ws+"\n");
			sb.append("File comment space in bytes: "+(percent_of_comments*file.length()/100.0)+"\n");
			sb.append("Comment percentage of file: "+percent_of_comments +"%"+"\n");
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
			n2 = parser.JavaMetrics.uniqueIdentifiers.size() + parser.JavaMetrics.uIDSym.size()
					+ parser.JavaMetrics.uniqueConstants.size();
			N1 = parser.specialcount + parser.keywordCount;
			N2 = parser.identcount + lexer.constantcount;
			N = N1 + N2;
			n = n1 + n2;
			V = (float) (N* (Math.log(N) / Math.log(2)));
			D = (float) ((n1/2.0) * (n2/2.0));
			E = (float) (D * V);
			T = (float) (E/18.0);
			B = (float) (V/3000.0);
			sb.append("Halstead:"+"\n");
			sb.append("\t"+"Unique operators: " + n1+"\n");
			sb.append("\t"+"Unique operands: " + n2+"\n");
			sb.append("\t"+"Total operators: " + N1+"\n");
			sb.append("\t"+"Total operands: " + N2+"\n");
			sb.append("\t"+"Program length (N) = " + N+"\n");
			sb.append("\t"+"Program vocabulary (n) = " + n+"\n");
			sb.append("\t"+"Volume = " + V+"\n");
			sb.append("\t"+"Difficulty = " + D+"\n");
			sb.append("\t"+"Effort = " + T+"\n");
			sb.append("\t"+"Bugs expected = " + B+"\n");
		}
	}
}
