package day13;

import java.util.ArrayList;
import java.util.List;

public class Signal {

	private ArrayList<SignalElement> signalElements;
	
	public Signal(ArrayList<SignalElement> list) {
		super();
		this.signalElements = list;
	}

	public Signal(String inputLine) {
		//filter out the opening char that is always there
		inputLine = inputLine.substring(1);
		
		signalElements = new ArrayList<SignalElement>();
		String number = "";
		for(int x=0; x<inputLine.length(); x++) {
			Character curLetter = inputLine.charAt(x);
			if(curLetter=='[') {
				int close = findCloseToThisListElement(inputLine, x+1);
				ArrayList<SignalElement> list = getElementList((String) inputLine.subSequence(x+1, close+1));
				signalElements.add(new SignalElement(list));
				x=close;
			} else if(curLetter==']') {
				if(number.equals("")) {
					//Do nothing, just finished adding a list
				}else {
					signalElements.add(new SignalElement(Integer.valueOf(number)));
					number = "";
				}
			} else if(curLetter==','){
				if(number.equals("")) {
					//Do nothing, just finished adding a list
				}else {
					signalElements.add(new SignalElement(Integer.valueOf(number)));
					number = "";
				}
			} else {
				number+=curLetter;
			}
		}
	}

	private int findCloseToThisListElement(String inputLine, int start) {
		int openCount=1;
		for(int x=start; x<inputLine.length(); x++) { 
			if(inputLine.charAt(x)=='[') {
				openCount++;
			}else if(inputLine.charAt(x)==']') {
				openCount--;
			}
			if(openCount==0) {
				return x;
			}
		}
		throw new RuntimeException("can't find close of this list.");
	}

	private ArrayList<SignalElement> getElementList(String inputLine) {
		if(!inputLine.contains("]")) {
			throw new RuntimeException("List doesn't contain ']'.");
		}
		ArrayList<SignalElement> list = new ArrayList<SignalElement>();
		String number = "";
		if(!inputLine.contains("[")) {
			for(int x=0; x<inputLine.length(); x++) {
				Character curLetter = inputLine.charAt(x);
				if(curLetter==']') {
					if(number.equals("")) {
						//Do nothing, just finished adding a list
					}else {
						list.add(new SignalElement(Integer.valueOf(number)));
						number = "";
					}
					return list; //Need to hit this at some point
				} else if(curLetter==','){
					list.add(new SignalElement(Integer.valueOf(number)));
					number = "";
				} else {
					number+=curLetter;
				}
			}
		} else {
			//we have an inner list
			for(int x=0; x<inputLine.length(); x++) {
				Character curLetter = inputLine.charAt(x);
				if(curLetter=='[') {
					int close = findCloseToThisListElement(inputLine, x+1);
					ArrayList<SignalElement> innerList = getElementList((String) inputLine.subSequence(x+1, close+1));
					list.add(new SignalElement(innerList));
					x=close;
				} else if(curLetter==']') {
					if(number.equals("")) {
						//Do nothing, just finished adding a list
					}else {
						list.add(new SignalElement(Integer.valueOf(number)));
						number = "";
					}
					return list; //Need to hit this at some point
				} else if(curLetter==','){
					if(number.equals("")) {
						//Do nothing, just finished adding a list
					}else {
						list.add(new SignalElement(Integer.valueOf(number)));
						number = "";
					}
				} else {
					number+=curLetter;
				}
			}
		}
		return list;//Can get here if we're adding an empty list.
	}

	protected List<SignalElement> getSignalElements() {
		return signalElements;
	}

	protected void setSignalElements(ArrayList<SignalElement> signalElements) {
		this.signalElements = signalElements;
	}

	@Override
    public String toString() {
    	String print = signalElements.toString();
		return print;
    } 
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof Signal)) { return false; }
        Signal other = (Signal) obj;

        if(!this.signalElements.equals(other.signalElements)) { return false; }
        
        return true;
    }

}
