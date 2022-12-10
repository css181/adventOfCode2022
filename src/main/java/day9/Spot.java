package day9;

public class Spot {

	private boolean hasBeenVisited;
	
	public Spot() {
		this.hasBeenVisited = false;
	}

	protected boolean isHasBeenVisited() {
		return hasBeenVisited;
	}

	protected void setHasBeenVisited(boolean hasBeenVisited) {
		this.hasBeenVisited = hasBeenVisited;
	}

	@Override
    public String toString() {
    	String print = "Been Visited: " + hasBeenVisited;
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

        if(!(obj instanceof Spot)) { return false; }
        Spot other = (Spot) obj;

        if(this.hasBeenVisited != other.hasBeenVisited) { return false; }
        
        return true;
    }

}
