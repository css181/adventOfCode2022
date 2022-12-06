package day6;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import utilities.FileUtility;

public class DataStreamFromInput {

	private static File file;
	private String dataStream;
	
	public DataStreamFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
	}
	
	protected void setFileToUse(File file) {
		DataStreamFromInput.file = file;
	}

	public void populateDataStream() {
		dataStream = FileUtility.convertFileToString(file);
	}

	public String getDataStream() {
		return dataStream;
	}

	public int getIndexOfFirstUniqueXChars(int numOfUnique) {
		ArrayList<String> uniqueTestSet = new ArrayList<String>();
		initializeUniqueTestSet(uniqueTestSet, numOfUnique-1);
		for(int index=numOfUnique; index<dataStream.length(); index++) {
			uniqueTestSet.add(dataStream.substring(index-1, index));
			if(containsAllUniqueChars(uniqueTestSet)) {
				return index;
			}
			uniqueTestSet.remove(0);
		}
		return -1;
	}
	
	private void initializeUniqueTestSet(ArrayList<String> uniqueTestSet, int numOfCharsToInit) {
		for(int x=0; x<numOfCharsToInit; x++) {
			uniqueTestSet.add(dataStream.substring(x, x+1));
		}
	}

	private boolean containsAllUniqueChars(ArrayList<String> uniqueTestSet) {
		boolean areAllUnique = true;
		for(int x=0; x<uniqueTestSet.size()-1; x++) { //last one is guaranteed to be unique to the last index if the rest are
			if(uniqueTestSet.lastIndexOf(uniqueTestSet.get(x)) != x) {
				areAllUnique = false;
				break;
			}
		}
		return areAllUnique;
	}

}
