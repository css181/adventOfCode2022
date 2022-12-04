package day4;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import utilities.FileUtility;

public class ElfPairsFromInput {

	private static File file;
	private ArrayList<ElfPair> elfPairs;
	
	public ElfPairsFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		elfPairs = new ArrayList<ElfPair>();
	}
	
	protected void setFileToUse(File file) {
		ElfPairsFromInput.file = file;
	}

	public void populateElfPairs() {
		ArrayList<String> input = FileUtility.convertFileToStringArray(file);
		for (String elfPair : input) {
			elfPairs.add(new ElfPair(elfPair));
		}
	}

	public ArrayList<ElfPair> getElfPairs() {
		return elfPairs;
	}

	public int getTotalContainedPairs() {
		int total = 0;
		for (ElfPair elfPair : elfPairs) {
			if(elfPair.isSmallerContainedInLarger()) { total++; }
		}
		return total;
	}

	public int getTotalOverlapPairs() {
		int total = 0;
		for (ElfPair elfPair : elfPairs) {
			if(elfPair.doesHaveAnyOverlap()) { total++; }
		}
		return total;
	}
}
