package day17;

public class PlusShape extends Shape {

	public PlusShape() {
		super();
		this.peice = new char[3][3];
		peice[0][0] = '.';
		peice[0][1] = '#';
		peice[0][2] = '.';
		peice[1][0] = '#';
		peice[1][1] = '#';
		peice[1][2] = '#';
		peice[2][0] = '.';
		peice[2][1] = '#';
		peice[2][2] = '.';
	}
	
}
