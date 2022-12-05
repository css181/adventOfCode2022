package day5;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Stack;

import utilities.FileUtility;

public class StacksFromInput {

	private static File file;
	private static File instructionsFile;
	private ArrayList<Stack<String>> stacks;
	private ArrayList<String> instructions;
	
	public StacksFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		URL instructionFileName = getClass().getResource("Instructions.txt");
		file = new File(fileName.getPath());
		instructionsFile = new File(instructionFileName.getPath());
		stacks = new ArrayList<Stack<String>>();
		instructions = new ArrayList<String>();
	}
	
	protected void setFileToUse(File file) {
		StacksFromInput.file = file;
	}
	protected void setInstructionFileToUse(File file) {
		StacksFromInput.instructionsFile = file;
	}

	public void populateStacksAndInstructions() {
		ArrayList<String> input = FileUtility.convertFileToStringArray(file);
		setupStackList(input);
		for (int rowIndes = input.size()-1; rowIndes >= 0; rowIndes--) {
			String row = input.get(rowIndes);
			int stackIndex = 0;
			for(int posInRow=1; posInRow<row.length(); posInRow+=4) {
				String curLetter = row.substring(posInRow, posInRow+1);
				if(!curLetter.equals(" ")) {
					stacks.get(stackIndex).add(curLetter);
				}
				stackIndex ++;
			}
		}
		
		instructions = FileUtility.convertFileToStringArray(instructionsFile);
	}

	private void setupStackList(ArrayList<String> input) {
		for(int posInRow=1; posInRow<input.get(0).length(); posInRow+=4) {
			stacks.add(new Stack<String>());
		}
	}

	public ArrayList<Stack<String>> getStacks() {
		return stacks;
	}

	public ArrayList<String> getInstructions() {
		return instructions;
	}

	public void processInstructions() {
		for (String instruction : instructions) {
			int numToMove = getNumToMove(instruction);
			int fromStackIndex = getFromStackIndex(instruction);
			int toStackIndex = getToStackIndex(instruction);
			
			for(int moveNum=0; moveNum<numToMove; moveNum++) {
				String letter = stacks.get(fromStackIndex).pop();
				stacks.get(toStackIndex).push(letter);
			}
		}
	}

	public void processInstructions9001() {
		for (String instruction : instructions) {
			int numToMove = getNumToMove(instruction);
			int fromStackIndex = getFromStackIndex(instruction);
			int toStackIndex = getToStackIndex(instruction);
			
			ArrayList<String> letters = new ArrayList<String>();
			for(int moveNum=0; moveNum<numToMove; moveNum++) {
				letters.add(stacks.get(fromStackIndex).pop());
			}
			for (int index=letters.size()-1; index>=0; index--) {
				stacks.get(toStackIndex).push(letters.get(index));
			}
		}
	}

	protected Integer getNumToMove(String instruction) {
		return Integer.valueOf(instruction.substring(5, instruction.indexOf("from")-1));
	}

	private int getFromStackIndex(String instruction) {
		return Integer.valueOf(instruction.substring(instruction.indexOf("from")+5, instruction.indexOf("to")-1)) - 1;
	}

	private int getToStackIndex(String instruction) {
		return Integer.valueOf(instruction.substring(instruction.indexOf("to")+3)) - 1;
	}

	public String getTopFromEachStack() {
		String returnVal = "";
		for (Stack<String> stack : stacks) {
			returnVal+=stack.pop();
		}
		return returnVal;
	}

}
