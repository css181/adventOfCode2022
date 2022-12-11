package day11;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import utilities.FileUtility;

public class MonkeysFromInput {

	private static File file;
	private ArrayList<Monkey> monkeys;
	
	public MonkeysFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		monkeys = new ArrayList<Monkey>();
	}
	
	protected void setFileToUse(File file) {
		MonkeysFromInput.file = file;
	}

	public void populateMonkeys() {
		ArrayList<Integer> curItemList = new ArrayList<Integer>();
		Operation curOp = null;
		ArrayList<Integer> curDivByNums = new ArrayList<Integer>();

		ArrayList<String> input = FileUtility.convertFileToStringArray(file);
		for (String line : input) {
			if(line.contains("Starting")) {
				curItemList = getItemList(line);
			}else if(line.contains("Operation")) {
				curOp = getOperation(line);
			}else if(line.contains("Test")) {
				curDivByNums.add(Integer.valueOf(line.substring(line.lastIndexOf(" ") +1)));
			}else if(line.contains("If true")) {
				curDivByNums.add(Integer.valueOf(line.substring(line.lastIndexOf(" ") +1)));
			}else if(line.contains("If false")) {
				curDivByNums.add(Integer.valueOf(line.substring(line.lastIndexOf(" ") +1)));
				
				//generate Monkey as the last line of input for that monkey
				monkeys.add(new Monkey(curItemList, curOp, new TestDivBy(curDivByNums.get(0), curDivByNums.get(1), curDivByNums.get(2))));
				
				//reset all vars for next monkey
				curItemList = new ArrayList<Integer>();
				curOp = null;
				curDivByNums = new ArrayList<Integer>();
			}
		}
	}

	private ArrayList<Integer> getItemList(String line) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		line = line.replace(",", "");
		for (String segment : line.split(" ")) {
			if(isNumeric(segment)) {
				list.add(Integer.valueOf(segment));
			}
		}
		return list;
	}
	private Operation getOperation(String line) {
		String toDo = line.contains("*") ? "*" : "+";
		String lastSegment = line.substring(line.lastIndexOf(" ") +1);
		int operand = lastSegment.equals("old") ? -1 : Integer.valueOf(lastSegment);
		return new Operation(toDo, operand);
	}

	private boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}

	public ArrayList<Monkey> getMonkeys() {
		return monkeys;
	}

	public void performRound() {
		for(int x=0; x<monkeys.size(); x++) {
			Monkey curMonkey = monkeys.get(x);
			while (curMonkey.getItemList().size()>0) {
				int item = curMonkey.getItemList().get(0);
				int newItemNum = curMonkey.getOperation().perform(item);
				newItemNum/=3; //from getting bored and your worry going down
				if(newItemNum%curMonkey.getTestDivBy().getDivBy()==0) {
					monkeys.get(curMonkey.getTestDivBy().getTrueMonkeyIndex()).getItemList().add(newItemNum);
				} else {
					monkeys.get(curMonkey.getTestDivBy().getFalseMonkeyIndex()).getItemList().add(newItemNum);;
				}
				curMonkey.setNumOfPasses(curMonkey.getNumOfPasses()+1);
				curMonkey.getItemList().remove(0);
			}
		}
	}

	public long getMonkeyBusiness() {
		int highestPass = 0;
		int secondHighPass = 0;
		for (Monkey monkey : monkeys) {
			int curPasses = monkey.getNumOfPasses() ;
			if(curPasses > highestPass) {
				secondHighPass = highestPass;
				highestPass = curPasses;
			}
			else if(curPasses > secondHighPass) {
				secondHighPass = curPasses;
			}
		}
		return highestPass * secondHighPass;
	}

}
