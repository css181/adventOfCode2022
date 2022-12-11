package day11;

public class Operation {
	private String toDo;
	private int operand;
	public Operation(String toDo, int operand) {
		super();
		this.toDo = toDo;
		this.operand = operand;
	}
	protected String getToDo() {
		return toDo;
	}
	protected void setToDo(String toDo) {
		this.toDo = toDo;
	}
	protected int getOperand() {
		return operand;
	}
	protected void setOperand(int operand) {
		this.operand = operand;
	}
	
	public long perform(long item) {
		long newNum = item;
		long curOperand = operand;
		if(operand==-1) { curOperand = item; }
		if(toDo.equals("*")) {
			newNum = item * curOperand;
		} else {
			newNum = item + curOperand;
		}
		return newNum;
	}
	
	@Override
    public String toString() {
    	String print = "{Operation toDo: " + toDo + ", operand:" + operand + "}";
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

        if(!(obj instanceof Operation)) { return false; }
        Operation other = (Operation) obj;

        if(!this.toDo.equals(other.toDo)) { return false; }
		if(this.operand!=other.operand) {return false;}
        
        return true;
    }

}
