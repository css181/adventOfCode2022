package innitialDayToCloneFrom;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import utilities.FileUtility;

public class DayXFromInput {

	private static File file;
	private ArrayList<POJO> pojoList;
	
	public DayXFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
	}
	
	protected void setFileToUse(File file) {
		DayXFromInput.file = file;
	}

	public void populatePojo() {
		ArrayList<POJO> pojoList = new ArrayList<POJO>();
		ArrayList<String> pojosFromInput = FileUtility.convertFileToStringArray(file);
		for (String pojo : pojosFromInput) {
			pojoList.add(new POJO(pojo));
		}
		this.pojoList = pojoList;
	}

}
