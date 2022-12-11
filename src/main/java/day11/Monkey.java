package day11;

import java.util.ArrayList;

public class Monkey {

	private ArrayList<Integer> itemList;
	private Operation operation;
	private TestDivBy testDivBy;
	private int numOfPasses;
	
	public Monkey() {
	}
	
	public Monkey(ArrayList<Integer> itemList, Operation operation, TestDivBy testDivBy) {
		this.itemList = itemList;
		this.operation = operation;
		this.testDivBy = testDivBy;
	}

	protected ArrayList<Integer> getItemList() {
		return itemList;
	}

	protected Operation getOperation() {
		return operation;
	}

	protected TestDivBy getTestDivBy() {
		return testDivBy;
	}

	protected int getNumOfPasses() {
		return numOfPasses;
	}

	protected void setNumOfPasses(int numOfPasses) {
		this.numOfPasses = numOfPasses;
	}

	@Override
    public String toString() {
    	String print = "Monkey has items " + itemList + ", " + operation + ", " + testDivBy;
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

        if(!(obj instanceof Monkey)) { return false; }
        Monkey other = (Monkey) obj;

        if(!this.itemList.equals(other.itemList)) { return false; }
        if(!this.operation.equals(other.operation)) { return false; }
        if(!this.testDivBy.equals(other.testDivBy)) { return false; }
        
        return true;
    }
}
