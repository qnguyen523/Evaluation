import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class ComputeFP implements ActionListener {
	FPModel fp;
	final int EI = 0, EO = 1, EInq = 2, ILF = 3, EIF = 4;
	infomationDomain[] id;
	JTextField total;
	JTextField VAFField;
	VafValue vaf_total_value;
	JTextField FPField;
	public void setFields(FPModel fp, infomationDomain[] id,JTextField total,
			JTextField VAFField,VafValue vaf_total_value,JTextField FPField) {
		this.id=id;
		this.total=total;
		this.VAFField=VAFField;
		this.vaf_total_value=vaf_total_value;
		this.FPField=FPField;
		this.fp=fp;
	}
	public void actionPerformed(ActionEvent e) {
		// take care of information domain values
		fp.EICount = id[EI].input.getText().equals("") ? 0 : 
			Integer.valueOf(id[EI].input.getText()) * id[EI].complexity_value;
		fp.EOCount = id[EO].input.getText().equals("") ? 0 : 
			Integer.valueOf(id[EO].input.getText()) * id[EO].complexity_value;
		fp.EInqCount = id[EInq].input.getText().equals("") ? 0 : 
			Integer.valueOf(id[EInq].input.getText()) * id[EInq].complexity_value;
		fp.ILFCount = id[ILF].input.getText().equals("") ? 0 : 
			Integer.valueOf(id[ILF].input.getText()) * id[ILF].complexity_value;
		fp.EIFCount = id[EIF].input.getText().equals("") ? 0 : 
			Integer.valueOf(id[EIF].input.getText()) * id[EIF].complexity_value;
		if (fp.EICount>=0&&fp.EOCount>=0&&fp.EInqCount>=0&&fp.ILFCount>=0&&fp.EIFCount>=0) {
			id[EI].output.setText(String.valueOf(fp.EICount));
			id[EO].output.setText(String.valueOf(fp.EOCount));
			id[EInq].output.setText(String.valueOf(fp.EInqCount));
			id[ILF].output.setText(String.valueOf(fp.ILFCount));
			id[EIF].output.setText(String.valueOf(fp.ILFCount));
			
			// compute fp
			DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
			total.setText(String.valueOf(fp.computeTotal()));
			FPField.setText(String.valueOf(format.format(fp.computeFP(vaf_total_value.value))));
		}
		else {
			// error
			System.err.println("Error");
			JOptionPane.showMessageDialog(null, "Inputs must be non-negative", "Alert", JOptionPane.ERROR_MESSAGE);
		}
	}
}
