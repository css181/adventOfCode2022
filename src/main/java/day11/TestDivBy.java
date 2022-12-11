package day11;

public class TestDivBy {
	private int divBy;
	private int trueMonkeyIndex;
	private int falseMonkeyIndex;
	public TestDivBy(int divBy, int trueMonkeyIndex, int falseMonkeyIndex) {
		super();
		this.divBy = divBy;
		this.trueMonkeyIndex = trueMonkeyIndex;
		this.falseMonkeyIndex = falseMonkeyIndex;
	}
	protected int getDivBy() {
		return divBy;
	}
	protected void setDivBy(int divBy) {
		this.divBy = divBy;
	}
	protected int getTrueMonkeyIndex() {
		return trueMonkeyIndex;
	}
	protected void setTrueMonkeyIndex(int trueMonkeyIndex) {
		this.trueMonkeyIndex = trueMonkeyIndex;
	}
	protected int getFalseMonkeyIndex() {
		return falseMonkeyIndex;
	}
	protected void setFalseMonkeyIndex(int falseMonkeyIndex) {
		this.falseMonkeyIndex = falseMonkeyIndex;
	}
	
	@Override
    public String toString() {
    	String print = "{TestDivBy divBy: " + divBy + ", trueMonkey:" + trueMonkeyIndex + ", falseMonkey:" + falseMonkeyIndex + "}";
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

        if(!(obj instanceof TestDivBy)) { return false; }
        TestDivBy other = (TestDivBy) obj;

        if(this.divBy != other.divBy) { return false; }
        if(this.trueMonkeyIndex != other.trueMonkeyIndex) { return false; }
        if(this.falseMonkeyIndex != other.falseMonkeyIndex) { return false; }
        
        return true;
    }
}
