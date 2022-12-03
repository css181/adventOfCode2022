package day3;

import java.util.ArrayList;

public class Rugsack {

	ArrayList<Character> compartment1;
	ArrayList<Character> compartment2;
	ArrayList<Character> allChars;
	public Rugsack() {
	}
	
	public Rugsack(ArrayList<Character> input) {
		allChars = input;
		compartment1 = new ArrayList<Character>();
		compartment2 = new ArrayList<Character>();
		for(int pos=0; pos<input.size()/2; pos++) {
			compartment1.add(input.get(pos));
		}
		for(int pos=input.size()/2; pos<input.size(); pos++) {
			compartment2.add(input.get(pos));
		}
	}
	
	public ArrayList<Character> getAllChars() {
		return allChars;
	}
	
    public ArrayList<Character> getCompartment1() {
		return compartment1;
	}

	public ArrayList<Character> getCompartment2() {
		return compartment2;
	}

	@Override
    public String toString() {
    	String print = "Comp1: " + compartment1 + " ~ Comp2: " + compartment2;
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

        if(!(obj instanceof Rugsack)) { return false; }
        Rugsack other = (Rugsack) obj;
        
        if(!this.compartment1.equals(other.compartment1)) { return false; }
        if(!this.compartment2.equals(other.compartment2)) { return false; }
        
        return true;
    }

	public Integer getPriority() {
		return PriorityScore.calculate(getCharInBothCompartments());
	}

	private char getCharInBothCompartments() {
		for (Character character : compartment1) {
			if(compartment2.contains(character)) {
				return character;
			}
		}
		throw new RuntimeException("No duplicate between compartments.");
	}
}
