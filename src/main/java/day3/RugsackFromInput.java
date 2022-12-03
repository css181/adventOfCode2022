package day3;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import utilities.FileUtility;

public class RugsackFromInput {

	private static File file;
	private ArrayList<Rugsack> rugsackList;
	private ArrayList<ElfGroup> elfGroups;
	
	public RugsackFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
	}
	
	protected void setFileToUse(File file) {
		RugsackFromInput.file = file;
	}

	public void populateRugSacksAndElfGroups() {
		ArrayList<Rugsack> rugsackList = new ArrayList<Rugsack>();
		ArrayList<ArrayList<Character>> input = FileUtility.convertFileToCharacterArray(file);
		ElfGroup curElfGroup = new ElfGroup();
		elfGroups = new ArrayList<ElfGroup>();
		int counter = 0;
		for (ArrayList<Character> inputSack : input) {
			counter++;
			Rugsack sack = new Rugsack(inputSack);
			rugsackList.add(sack);
			curElfGroup.addSack(sack);
			if(counter%3==0) {
				elfGroups.add(curElfGroup);
				curElfGroup = new ElfGroup();
			}
		}
		this.rugsackList = rugsackList;
	}

	public ArrayList<Rugsack> getRugsackList() {
		return rugsackList;
	}

	public ArrayList<ElfGroup> getElfGroups() {
		return elfGroups;
	}
}
