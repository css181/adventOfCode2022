package day9;

public class RightInstruction extends Instruction {

	public RightInstruction(int spaces) {
		super(spaces);
	}

	@Override
	public Coordinate moveHead(Coordinate head) {
		return new Coordinate(head.getX()+getSpaces(), head.getY());
	}

}
