package day9;

public abstract class Instruction {
	
	private int spaces;
	abstract public Coordinate moveHead(Coordinate head);
	
	public int getSpaces() {
		return spaces;
	}
	public void setSpaces(int spaces) {
		this.spaces = spaces;
	}

	public Instruction(int spaces) {
		super();
		this.spaces = spaces;
	}
	
	@Override
    public String toString() {
    	String print = this.getClass().getSimpleName() + ", Spaces: " + spaces;
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

        if(!(obj instanceof Instruction)) { return false; }
        Instruction other = (Instruction) obj;
        
        if(this.spaces!=other.spaces) { return false; }
        return true;
    }
}
