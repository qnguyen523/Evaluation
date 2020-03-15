import java.io.*;
@SuppressWarnings("serial")
public class SMI implements Serializable {
	double smi=0.0;
	int added=0,changed=0,deleted=0;
	int currentTotal=0;
	public int total (SMI x, int lastTotal) {
		currentTotal = lastTotal + added - deleted;
		return currentTotal;
	}
	public double smi (SMI x) {
		smi = ( currentTotal - (added+changed+deleted) )*100.0/ (currentTotal*100.0);
		return smi;
	}
	public String toString() {
		return smi+" "+added+" "+changed+" "+deleted+" "+currentTotal;
	}
	@Override
    public boolean equals(Object o) {
		if (o == this) { 
            return true; 
        }
		if (!(o instanceof SMI)) { 
            return false; 
        } 
		SMI c = (SMI) o;
		return Double.compare(smi,c.smi)==0 && added==c.added 
				&& changed==c.changed && deleted==c.deleted 
				&& currentTotal==c.currentTotal;
	}
}
