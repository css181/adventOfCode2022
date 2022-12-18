package day17;

public class StateInfo {

	private int leftMost;
	private int rightMost;
	private int directionIndex;
	private int totalShapestoYieldThis;
	
	
	public StateInfo(int leftMost, int rightMost, int directionIndex, int totalShapestoYieldThis) {
		super();
		this.leftMost = leftMost;
		this.rightMost = rightMost;
		this.directionIndex = directionIndex;
		this.totalShapestoYieldThis = totalShapestoYieldThis;
	}

	protected int getLeftMost() {
		return leftMost;
	}

	protected void setLeftMost(int leftMost) {
		this.leftMost = leftMost;
	}

	protected int getRightMost() {
		return rightMost;
	}

	protected void setRightMost(int rightMost) {
		this.rightMost = rightMost;
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

	@Override
    public String toString() {
		String print = "{Left: " + leftMost + ", Right: " + rightMost + ", DirectionIndex: " + directionIndex + "}";
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

        if(this.leftMost != other.leftMost) { return false; }
        if(this.rightMost != other.rightMost) { return false; }
        if(this.directionIndex != other.directionIndex) { return false; }
        
        return true;
    }

}
