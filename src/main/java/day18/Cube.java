package day18;

public class Cube {

	private int x;
	private int y;
	private int z;
	
	public Cube(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	protected int getX() {
		return x;
	}

	protected int getY() {
		return y;
	}

	protected int getZ() {
		return z;
	}

	@Override
    public String toString() {
    	String print = "(" + x + "," + y + "," + z + ")";
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

        if(!(obj instanceof Cube)) { return false; }
        Cube other = (Cube) obj;

        if(this.x != other.x) { return false; }
        if(this.y != other.y) { return false; }
        if(this.z != other.z) { return false; }
        
        return true;
    }

}
