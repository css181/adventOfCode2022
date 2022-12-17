package day17;

public class Chamber {

	public ChamberTile[][] grid;
	public int leftMostShapeIndex;
	public int rightMostShapeIndex;
	public int highestRockIndex;
	public static final int DEFAULT_LEFT=8;
	public static final int DEFAULT_RIGHT=0;
	
	public Chamber() {
		this.leftMostShapeIndex=8;
		this.rightMostShapeIndex=0;
		this.highestRockIndex=3;
	}
	
	@Override
    public String toString() {
		return printGrid();
    } 
    
	public String printGrid() {
		String print = "";
		for(int row=0; row<grid.length; row++) {
			for(int col=0; col<grid[row].length; col++) {
				print+=grid[row][col];
			}
			print+="\n";
		}
		print=print.substring(0, print.length()-1);//remove last crlf
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

        if(!(obj instanceof Chamber)) { return false; }
        Chamber other = (Chamber) obj;

        if(this.grid==null && other.grid!=null) { return false; }
        if(this.grid!=null && other.grid==null) { return false; }
		for(int row=0; row<grid.length; row++) {
			for(int col=0; col<grid[row].length; col++) {
				if(!this.grid[row][col].equals(other.grid[row][col])) {
					return false;
				}
			}
		}
        
        return true;
    }

}
