package day7;

import java.util.ArrayList;

public abstract class Instruction implements I_Instruction{

	private String commandArg;
	private ArrayList<String> results;
	
	public Instruction() {
		this.commandArg = "";
		this.results = new ArrayList<String>();
	}

    public Instruction(String command, ArrayList<String> results) {
		super();
		this.commandArg = command;
		this.results = results;
	}
    
	public String getCommandArg() {
		return commandArg;
	}

	protected void setCommandArg(String commandArg) {
		this.commandArg = commandArg;
	}

	public ArrayList<String> getResults() {
		return results;
	}

	protected void setResults(ArrayList<String> results) {
		this.results = results;
	}

	@Override
    public String toString() {
    	String print = this.getClass().getSimpleName() + "{Command: " + commandArg + ", Results: " + results + "}";
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

        if(!(obj instanceof Instruction)) { return false; }
        Instruction other = (Instruction) obj;
        
        if(this.commandArg==null && other.commandArg!=null) { return false; }
        if(this.commandArg==null && other.commandArg==null) {
        	//skip check
        } else {
        	if(!this.commandArg.equals(other.commandArg)) { return false; }
        }
        if(this.results==null && other.results!=null) { return false; }
        if(this.results==null && other.results==null) {
        	//skip check
        } else {
        	if(!this.results.equals(other.results)) { return false; }
        }
        return true;
    }
}
