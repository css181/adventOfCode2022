package day7;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import utilities.FileUtility;

public class FileSystemFromInput {

	private static File file;
	private ArrayList<I_Instruction> instructions;
	private static Directory curDir;
	
	public FileSystemFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		instructions = new ArrayList<I_Instruction>();
	}
	
	protected void setFileToUse(File file) {
		FileSystemFromInput.file = file;
	}

	public void populateInstructions() {
		ArrayList<String> input = FileUtility.convertFileToStringArray(file);
		for (int index=0; index< input.size()-1; index++) {
			String curLine = input.get(index);
			//TODO: Optimize this double loop away later
			ArrayList<String> results = new ArrayList<String>();
			for(int resultIndex=index+1; resultIndex<input.size(); resultIndex++) {
				String possibleResultLine = input.get(resultIndex);
				if(possibleResultLine.startsWith("$")) {
					break;
				} else {
					results.add(possibleResultLine);
				}
			}
			if(curLine.startsWith("$")) {
				instructions.add(InstructionFactory.create(curLine, results));
			}
		}
	}

	public ArrayList<I_Instruction> getInstructions() {
		return instructions;
	}

	public static Directory getCurDir() {
		return curDir;
	}
	public static void setCurDir(Directory dir) {
		curDir = dir;
	}
	

	public void processInstructionsToBuildDirTree() {
		for (I_Instruction instruction : instructions) {
			instruction.process();
		}
	}

	public Directory getRootDir() {
		while (curDir.getParent()!=null) {
			curDir = curDir.getParent();
		}
		return curDir;
	}
	
	//TODO: find a more efficient way of finding all Dirs
	@SuppressWarnings("unchecked")
	public ArrayList<Directory> getListOfAllDirs() {
		ArrayList<Directory> allDirs = new ArrayList<Directory>();
		ArrayList<Directory> curList = new ArrayList<Directory>();
		allDirs.add(getRootDir());
		int allDirStartCount = 1;
		do {			
			allDirStartCount = allDirs.size();
			curList = (ArrayList<Directory>) allDirs.clone();
			for (Directory dir : curList) {				
				for (Directory child : dir.getChildren()) {
					if(!allDirs.contains(child)) {
						allDirs.add(child);
					}
				}
			}
		} while (allDirStartCount!=allDirs.size());
		return allDirs;
	}

	public Long getSumOfDirSizesThatAreLTE100000() {
		long total = 0;
		
		for (Directory dir : getListOfAllDirs()) {
			if(dir.getTotalSize() <= 100000) {
				total+=dir.getTotalSize();
			}
		}
		return total;
	}
}
