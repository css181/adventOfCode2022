package day15;

public class Beacon {

	private Coordinate coordinate;
	
	public Beacon(int x, int y) {
		this.coordinate = new Coordinate(x, y);
	}

	@Override
    public String toString() {
    	String print = coordinate.toString();
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

        if(!(obj instanceof Beacon)) { return false; }
        Beacon other = (Beacon) obj;

        if(!this.coordinate.equals(other.coordinate)) { return false; }
        
        return true;
    }

}
