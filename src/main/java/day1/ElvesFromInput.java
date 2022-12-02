package day1;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import utilities.FileUtility;

public class ElvesFromInput {

	private static File file;
	private ArrayList<Elf> elves;
	
	public ElvesFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		elves = new ArrayList<Elf>();
	}

	protected void setFileToUse(File file) {
		ElvesFromInput.file = file;
	}

	public void populateElves() {
		ArrayList<ArrayList<Long>> inputList = FileUtility.convertFileToListOfIntList(file, "");
		for (ArrayList<Long> list : inputList) {
			elves.add(new Elf(list));
		}
	}

	public ArrayList<Elf> getElves() {
		return elves;
	}

	public void setElves(ArrayList<Elf> elves) {
		this.elves = elves;
	}

	public Long getHighestCalorieCount() {
		long highestCalorie = 0;
		for (Elf elf : elves) {
			if(elf.getTotalCalories() > highestCalorie) {
				highestCalorie = elf.getTotalCalories();
			}
		}
		return highestCalorie;
	}

}
