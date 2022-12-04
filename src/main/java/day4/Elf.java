package day4;

public class Elf {

	private int startRange;
	private int endrange;
	private int rangeSize;
	
	public Elf(String range) {
		int splitIndex = range.indexOf("-");
		this.startRange = Integer.valueOf(range.substring(0, splitIndex));
		this.endrange = Integer.valueOf(range.substring(splitIndex+1));
		this.rangeSize = endrange - startRange + 1;
	}

	public int getStartRange() {
		return startRange;
	}

	public int getEndrange() {
		return endrange;
	}

	public int getRangeSize() {
		return rangeSize;
	}
	
	@Override
    public String toString() {
    	String print = "Range: " + startRange + " - " + endrange;
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

        if(!(obj instanceof Elf)) { return false; }
        Elf other = (Elf) obj;

        if(this.startRange != other.startRange) { return false; }
        if(this.endrange != other.endrange) { return false; }
        
        return true;
    }

}
