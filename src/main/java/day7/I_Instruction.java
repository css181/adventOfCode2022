package day7;

import java.util.ArrayList;

public interface I_Instruction {

	public String getCommandArg();
	public ArrayList<String> getResults();
	public void process();
}
