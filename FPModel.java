import javax.swing.*;
public class FPModel {
	public enum LANGUAGE 
	{ 
	    ASSEMBLER,ADA_95,CL,CLPLUS,CSHARP,COBOL,FORTRAN,
	    	HTML,JAVA,JAVASCRIPT,VBSCRIPT,VISUAL_BASIC,DEFAULT; 
	}
	public LANGUAGE currentLanguge = LANGUAGE.DEFAULT;
	int LOC;
	public int EICount,EOCount,EInqCount,ILFCount,EIFCount;
	public int totalCount;
	double fp=0.0;
	// constructor
	public FPModel() {
		EICount=EOCount=EInqCount=ILFCount=EIFCount=totalCount=0;
	}
	// computer total count
	public int computeTotal() { 
		return EICount+EOCount+EInqCount+ILFCount+EIFCount;
	}
	// fp = total_count * [0.65 + 0.01 sum(f_i)]
	public double computeFP(int vaf_total_value) {
		totalCount = computeTotal();
		fp = totalCount * (0.65 + 0.01 * vaf_total_value);
		return fp;
		
	}
	public void selectLanguageSize() {
		switch(currentLanguge) {
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
		default:
			LOC = 0;
			System.err.println("Error");
			JOptionPane.showMessageDialog(null, "Please choose a language", "alert", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
	public int computeCodeSize() {
		selectLanguageSize();
		return (int) Math.round(LOC*fp);
	}
}
