package day7;

import java.util.ArrayList;

public class InstructionFactory {

	public static I_Instruction create(String curLine, ArrayList<String> results) {
		String command = curLine.substring(2, 4);
		switch (command) {
		case "cd": {
			return new CdInstruction(curLine.substring(5));
		}
		case "ls": {
			return new LsInstruction(results);
		}
		default:
			throw new RuntimeException("Invalid Instruction command: " + command);
		}
	}

}
