package day13;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import utilities.FileUtility;

public class SignalPairsFromInput {

	private static File file;
	private ArrayList<SignalPair> signalPairs;
	
	public SignalPairsFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		signalPairs = new ArrayList<SignalPair>();
	}
	
	protected void setFileToUse(File file) {
		SignalPairsFromInput.file = file;
	}

	public void populateSignalPairs() {
		ArrayList<String> input = FileUtility.convertFileToStringArray(file);
		String leftString = "";
		String rightString = "";
		for (String line : input) {
			if(line.length()==0) {
				signalPairs.add(new SignalPair(new Signal(leftString), new Signal(rightString)));
				leftString = "";
				rightString = "";
			}
			else if(leftString.equals("")) {
				leftString = line;
			}
			else {
				rightString = line;
			}
		}
	}

	public ArrayList<SignalPair> getSignalPairs() {
		return signalPairs;
	}

	//true=left is <= right, false=left>right
	public boolean isSignalInRightOrder(SignalPair signalPair) {
		List<SignalElement> leftElems = signalPair.getLeft().getSignalElements();
		List<SignalElement> rightElems = signalPair.getRight().getSignalElements();
		for (int x=0; x<leftElems.size(); x++) {
			if(x>(rightElems.size()-1)) {
				System.out.println("FALSE-" + "left still has elems, right does not");
				return false;
			}
			int compare = leftElems.get(x).compareTo(rightElems.get(x));
			if(compare==1) {
				return false;
			}else if(compare==-1) {
				return true;
			} else {
				//Go to next element and compare
			}
		}
		System.out.println("TRUE-" + "ran out of elems in left");
		return true;
	}

	public int getSumOfIndexesInCorrectOrder() {
		int indexSum=0;
		for(int x=0; x<signalPairs.size(); x++) {
			System.out.println("\n#" + x);
			if(isSignalInRightOrder(signalPairs.get(x))) {
				indexSum+=(x+1);
			}
		}
		return indexSum;
	}

}
