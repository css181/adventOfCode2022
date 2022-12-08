package day8;

public class Tree {

	private int height;
	private boolean isVisible;
	private long scenicScore;
	
	public Tree(int height) {
		this.height = height;
		this.isVisible = true;
		this.scenicScore = 0l;
	}

	protected int getHeight() {
		return height;
	}

	protected long getScenicScore() {
		return scenicScore;
	}

	protected void setScenicScore(long scenicScore) {
		this.scenicScore = scenicScore;
	}

	protected boolean isVisible() {
		return isVisible;
	}
	public void setIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
    public String toString() {
    	String print = "Height: " + height + " - isVis: " + isVisible + " - scenic: " + scenicScore;
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

        if(!(obj instanceof Tree)) { return false; }
        Tree other = (Tree) obj;

        if(this.height != other.height) { return false; }
        if(this.isVisible != other.isVisible) { return false; }
        
        return true;
    }

}
