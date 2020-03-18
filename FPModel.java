import java.io.Serializable;

import javax.swing.*;
/*
 * This class allows users to compute fp and code size
 */
@SuppressWarnings("serial")
public class FPModel implements Serializable {
	public enum LANGUAGE 
	{ 
	    ASSEMBLER,ADA_95,CL,CLPLUS,CSHARP,COBOL,FORTRAN,
	    	HTML,JAVA,JAVASCRIPT,VBSCRIPT,VISUAL_BASIC,DEFAULT; 
	}
	public LANGUAGE currentLanguage = LANGUAGE.DEFAULT;
	int LOC;
	public int EICount,EOCount,EInqCount,ILFCount,EIFCount;
	public int totalCount;
	double fp=0.0;
	int vaf=0;
	// constructor
	public FPModel() {
		EICount=EOCount=EInqCount=ILFCount=EIFCount=totalCount=0;
	}
	// compute total count
	public int computeTotal() { 
		return EICount+EOCount+EInqCount+ILFCount+EIFCount;
	}
	// compute fp
	public double computeFP(int vaf_total_value) {
		// fp = total_count * [0.65 + 0.01 sum(f_i)]
		vaf = vaf_total_value;
		totalCount = computeTotal();
		fp = totalCount * (0.65 + 0.01 * vaf_total_value);
		return fp;
		
	}
	// select language
	public void selectLanguageSize() {
		switch(currentLanguage) {
		case ASSEMBLER:
			LOC = 209;
			break;
		case ADA_95:
			LOC = 154;
			break;
		case CL:
			LOC = 148;
			break;
		case CLPLUS:
			LOC = 59;
			break;
		case CSHARP:
			LOC = 58;
			break;
		case COBOL:
			LOC = 80;
			break;
		case FORTRAN:
			LOC = 90;
			break;
		case HTML:
			LOC = 43;
			break;
		case JAVA:
			LOC = 55;
			break;
		case JAVASCRIPT:
			LOC = 54;
			break;
		case VBSCRIPT:
			LOC = 38;
			break;
		case VISUAL_BASIC:
			LOC = 50;
			break;
		case DEFAULT:
			LOC = 0;
			System.err.println("Error");
			JOptionPane.showMessageDialog(null, "Please choose a language", "alert", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			LOC = 0;
			System.err.println("Error");
			JOptionPane.showMessageDialog(null, "Please choose a language", "alert", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
	// compute total count
	public int computeCodeSize() {
		selectLanguageSize();
		return (int) Math.round(LOC*fp);
	}
	// select language from save
	public void selectLanguageSize(JTextField languageField) {
		String hold = languageField.getText();
		
		// no language is selected when opening a new tab
		if (hold.equals("ASSEMBLER")) {
			LOC = 209;
		} else if (hold.equals("ADA 95")) {
			LOC = 154;
		} else if (hold.equals("C")) {
			LOC = 148;
		} else if (hold.equals("C++")) {
			LOC = 59;
		} else if (hold.equals("C#")) {
			LOC = 58;
		} else if (hold.equals("COBOL")) {
			LOC = 80;
		} else if (hold.equals("FORTRAN")) {
			LOC = 90;
		} else if (hold.equals("HTML")) {
			LOC = 43;
		} else if (hold.equals("JAVA")) {
			LOC = 55;
		} else if (hold.equals("JAVASCRIPT")) {
			LOC = 54;
		} else if (hold.equals("VBSCRIPT")) {
			LOC = 38;
		} else if (hold.equals("VISUAL_BASIC")) {
			LOC = 50;
		} else {
			LOC = 0;
		}		
				
	}
	// compute code size from save
	public int computeCodeSizeFromSave(JTextField languageField) {
		selectLanguageSize(languageField);
		return (int) Math.round(LOC*fp);
	}
}
