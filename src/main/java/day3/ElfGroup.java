package day3;

import java.util.ArrayList;

public class ElfGroup {

	private ArrayList<Rugsack> sacks;
	
	public ElfGroup() {
		sacks = new ArrayList<Rugsack>();
	}
	
	@Override
    public String toString() {
    	String print = "Sacks: " + sacks;
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

        if(!(obj instanceof ElfGroup)) { return false; }
        ElfGroup other = (ElfGroup) obj;
        
        if(this.sacks != other.sacks) { return false; }
        
        return true;
    }

	public Integer getPriority() {
		return PriorityScore.calculate(getCharInCommonOfAllSacks());
	}

	private char getCharInCommonOfAllSacks() {
		for (Character letter : sacks.get(0).getAllChars()) {
			if(sacks.get(1).getAllChars().contains(letter) &&
					sacks.get(2).getAllChars().contains(letter)) {
				return letter;
			}
		}
		throw new RuntimeException("No common letter in all 3 sacks.");
	}

	public void addSack(Rugsack sack) {
		sacks.add(sack);
	}
	
	public ArrayList<Rugsack> getSacks() {
		return sacks;
	}
}
