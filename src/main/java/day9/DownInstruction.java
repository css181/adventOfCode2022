package day9;

public class DownInstruction extends Instruction {

	public DownInstruction(int spaces) {
		super(spaces);
	}

	@Override
	public Coordinate moveHead(Coordinate head) {
		return new Coordinate(head.getX(), head.getY()+getSpaces());
	}

}
