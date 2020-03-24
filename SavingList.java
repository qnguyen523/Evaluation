import java.io.*;
import java.util.*;
@SuppressWarnings("serial")
public class SavingList implements Serializable {
	ArrayList<SaveModel> saveObjectArray;
	ArrayList<SMI> SMI_list;
	ProjectInfoModel projectInfo;
	public SavingList() {
		saveObjectArray=new ArrayList<SaveModel>();
		SMI_list = new ArrayList<SMI>();
		projectInfo = new ProjectInfoModel();
	}
}
