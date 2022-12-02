package innitialDayToCloneFrom;

public class POJO {

	int someInt;
	public POJO() {
	}
	
	
    @Override
    public String toString() {
    	String print = "";
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

        if(!(obj instanceof POJO)) { return false; }
        POJO other = (POJO) obj;
        
        if(this.someInt != other.someInt) { return false; }
        
        return true;
    }
}
