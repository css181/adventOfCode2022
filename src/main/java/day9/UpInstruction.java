package day9;

public class UpInstruction extends Instruction {

	public UpInstruction(int spaces) {
		super(spaces);
	}

	@Override
	public Coordinate moveHead(Coordinate head) {
		return new Coordinate(head.getX(), head.getY()-getSpaces());
	}

}
