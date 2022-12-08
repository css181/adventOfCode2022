package day8;

public class Tree {

	private int height;
	private boolean isVisible;
	
	public Tree(int height) {
		this.height = height;
		this.isVisible = true;
	}

	protected int getHeight() {
		return height;
	}

	protected boolean isVisible() {
		return isVisible;
	}
	public void setIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
    public String toString() {
    	String print = "Height: " + height + " - isVis: " + isVisible;
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
