package day12;

public class Elevation {

	private int height;
	private Coordinate coordinate;
	
	public Elevation() {
	}
	
	public Elevation(Character height, Coordinate coordinate) {
		this.height = Integer.valueOf(height);
		this.coordinate = coordinate;
	}

	protected int getHeight() {
		return height;
	}

	protected void setHeight(int height) {
		this.height = height;
	}


	protected Coordinate getCoordinate() {
		return coordinate;
	}

	protected void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	@Override
    public String toString() {
		return coordinate.toString();
    } 	
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof Elevation)) { return false; }
        Elevation other = (Elevation) obj;

        if(this.height != other.height) { return false; }
        if(!this.coordinate.equals(other.coordinate)) { return false; }
        
        return true;
    }
}
