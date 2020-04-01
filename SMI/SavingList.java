package SMI;
import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
public class SavingList implements Serializable {
	ArrayList<SaveModel> saveObjectArray;
	ArrayList<SMI> SMI_list;
	public ArrayList<String> file_names;
	ProjectInfoModel projectInfo;
	String activeTabTitle = "";

	public SavingList() {
		saveObjectArray = new ArrayList<SaveModel>();
		SMI_list = new ArrayList<SMI>();
		file_names = new ArrayList<>();
		projectInfo = new ProjectInfoModel();
		activeTabTitle = "";
	}
}
