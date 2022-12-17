package day17;

public class ChamberTile {

	public char value;
	public boolean isCurrentShape;
	
	public ChamberTile() {
		this.value = '.';
		this.isCurrentShape = false;
	}
	public ChamberTile(char c) {
		this.value = c;
		this.isCurrentShape = false;
	}
	public ChamberTile(char c, boolean isCurrentShape) {
		this.value = c;
		this.isCurrentShape = isCurrentShape;
	}
	
	@Override
    public String toString() {
		String print = String.valueOf(value);
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

        if(!(obj instanceof ChamberTile)) { return false; }
        ChamberTile other = (ChamberTile) obj;

        if(this.value != other.value) { return false; }
        if(this.isCurrentShape != other.isCurrentShape) { return false; }
        
        return true;
    }

}
