package day13;

public class SignalPair {

	private Signal left;
	private Signal right;
	
	public SignalPair() {
	}

	
	public SignalPair(Signal left, Signal right) {
		super();
		this.left = left;
		this.right = right;
	}


	protected Signal getRight() {
		return right;
	}


	protected void setRight(Signal right) {
		this.right = right;
	}


	protected Signal getLeft() {
		return left;
	}


	protected void setLeft(Signal left) {
		this.left = left;
	}


	@Override
    public String toString() {
    	String print = "Right: " + right + ", Left: " + left;
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

        if(!(obj instanceof SignalPair)) { return false; }
        SignalPair other = (SignalPair) obj;

        if(!this.right.equals(other.right)) { return false; }
        if(!this.left.equals(other.left)) { return false; }
        
        return true;
    }
}
