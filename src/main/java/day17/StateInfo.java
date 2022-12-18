package day17;

public class StateInfo {

	private char[][] subGrid;
	private int directionIndex;
	private int totalShapestoYieldThis;
	
	
	public StateInfo(ChamberTile[][] grid, int directionIndex, int totalShapestoYieldThis) {
		super();
		this.subGrid=getSubGridFromGrid(grid);
		this.directionIndex = directionIndex;
		this.totalShapestoYieldThis = totalShapestoYieldThis;
	}

	private char[][] getSubGridFromGrid(ChamberTile[][] grid) {
		char[][] returnVal = new char[10][9];
		int end = Math.min(10, grid.length-1);
		for(int x=0; x<end; x++) {
			for(int y=0; y<9; y++) {
				returnVal[x][y] = grid[x][y].value;
			}
		}
		return returnVal;
	}

	protected int getDirectionIndex() {
		return directionIndex;
	}

	protected void setDirectionIndex(int directionIndex) {
		this.directionIndex = directionIndex;
	}

	protected int getTotalShapestoYieldThis() {
		return totalShapestoYieldThis;
	}

	protected void setTotalShapestoYieldThis(int totalShapestoYieldThis) {
		this.totalShapestoYieldThis = totalShapestoYieldThis;
	}

	protected char[][] getSubGrid() {
		return subGrid;
	}

	protected void setSubGrid(char[][] subGrid) {
		this.subGrid = subGrid;
	}

	@Override
    public String toString() {
		String print = "{SubGrid: " + subGrid + ", DirectionIndex: " + directionIndex + "}";
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

        if(!(obj instanceof StateInfo)) { return false; }
        StateInfo other = (StateInfo) obj;

        if(this.subGrid==null && other.subGrid!=null) { return false; }
        if(this.subGrid!=null && other.subGrid==null) { return false; }
		for(int row=0; row<subGrid.length; row++) {
			for(int col=0; col<subGrid[row].length; col++) {
				if(this.subGrid[row][col]!=(other.subGrid[row][col])) {
					return false;
				}
			}
		}
        if(this.directionIndex != other.directionIndex) { return false; }
        
        return true;
    }

}
