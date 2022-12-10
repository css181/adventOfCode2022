package day9;

public class InstructionFactory {

	public static Instruction create(String instructionString) {
		String letter = instructionString.substring(0, 1).toUpperCase();
		int spaces = Integer.valueOf(instructionString.substring(instructionString.indexOf(" ") + 1));
		switch (letter) {
		case "R":
			return new RightInstruction(spaces);
		case "U":
			return new UpInstruction(spaces);
		case "L":
			return new LeftInstruction(spaces);
		case "D":
			return new DownInstruction(spaces);
		default:
			throw new IllegalArgumentException("Unexpected value: " + letter);
		}
	}

}
