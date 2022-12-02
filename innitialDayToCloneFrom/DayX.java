package innitialDayToCloneFrom;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;

public class DayX {

	private static File file;
	
	public DayX() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		
		ArrayList<String> inputs = FileUtility.convertFileToStringArray(file);
	}
	
	protected void setFileToUse(File file) {
		DayX.file = file;
	}


}
