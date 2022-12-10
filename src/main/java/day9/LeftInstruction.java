package day9;

public class LeftInstruction extends Instruction {

	public LeftInstruction(int spaces) {
		super(spaces);
	}

	@Override
	public Coordinate moveHead(Coordinate head) {
		return new Coordinate(head.getX()-getSpaces(), head.getY());
	}

}
