import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
/*
 * This class is to compute FP
 */
public class ComputeFP implements ActionListener {
	final int EI = 0, EO = 1, EInq = 2, ILF = 3, EIF = 4;
	private FPModel fp;
	private infomationDomain[] id;
	private JTextField total;
	private JTextField VAFField;
	private VafValue vaf_total_value;
	private JTextField FPField;
	// set fields
	public void setFields(FPModel fp, infomationDomain[] id,JTextField total,
			JTextField VAFField,VafValue vaf_total_value,JTextField FPField) {
		this.id=id;
		this.total=total;
		this.VAFField=VAFField;
		this.vaf_total_value=vaf_total_value;
		this.FPField=FPField;
		this.fp=fp;
	}
	// when a Compute FP button is clicked
	public void actionPerformed(ActionEvent e) {
		// validate
		if (	id[EI].input.getText().equals("") 
			||	id[EO].input.getText().equals("")
			||	id[EInq].input.getText().equals("")
			||	id[ILF].input.getText().equals("")
			||	id[EIF].input.getText().equals("")) {
			System.err.println("Error from ComputeFP. Users do not input fields");
			JOptionPane.showMessageDialog(null, "Please input all fields before computing FP", 
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// validate
		if (	!id[EI].input.getText().matches("[0-9]+") 
				||	!id[EO].input.getText().matches("[0-9]+")
				||	!id[EInq].input.getText().matches("[0-9]+")
				||	!id[ILF].input.getText().matches("[0-9]+")
				||	!id[EIF].input.getText().matches("[0-9]+")) {
			System.err.println("Error from ComputeFP. Users do not input numbers in the fields");
			JOptionPane.showMessageDialog(null, "Please input non-negative numbers in the fields", 
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
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
			id[EIF].output.setText(String.valueOf(fp.EIFCount));
			
			if (vaf_total_value.value==Integer.parseInt(VAFField.getText())) {
				DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
				total.setText(String.valueOf(fp.computeTotal()));
				FPField.setText(String.valueOf(format.format(fp.computeFP(vaf_total_value.value))));
			}
			// from open operation
			else {
				DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
				total.setText(String.valueOf(fp.computeTotal()));
				FPField.setText(String.valueOf(format.format(fp.computeFP(Integer.parseInt(VAFField.getText())))));
			}
			
		}
		// validate input
		else {
			// error
			System.err.println("Error");
			JOptionPane.showMessageDialog(null, "Inputs must be non-negative", "Alert", JOptionPane.ERROR_MESSAGE);
		}
	}
}
