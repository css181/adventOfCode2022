package day9;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day4.ElfPair;
import utilities.FileUtility;

public class RopeMovesFromInput {

	private static File file;
	private ArrayList<ArrayList<Spot>> grid;
	private ArrayList<Instruction> instructions;
	private Coordinate start;
	private Coordinate tail;
	private Coordinate head;
	
	public RopeMovesFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		grid = new ArrayList<ArrayList<Spot>>();
		instructions = new ArrayList<Instruction>();
	}
	
	protected void setFileToUse(File file) {
		RopeMovesFromInput.file = file;
	}

	public void generateGrid(int size) {
		for(int row=0; row<size; row++) {
			ArrayList<Spot> curRow = new ArrayList<Spot>();
			for(int col=0; col<size; col++) {
				curRow.add(new Spot());
			}
			grid.add(curRow);
		}
		start = new Coordinate(size/2, size/2);
		head = new Coordinate(size/2, size/2);
		tail= new Coordinate(size/2, size/2);
	}

	protected ArrayList<ArrayList<Spot>> getGrid() {
		return grid;
	}

	protected Coordinate getTail() {
		return tail;
	}

	protected void setTail(Coordinate tail) {
		this.tail = tail;
	}

	protected Coordinate getHead() {
		return head;
	}

	protected void setHead(Coordinate head) {
		this.head = head;
	}

	protected Coordinate getStart() {
		return start;
	}

	public void populateInstructions() {
		ArrayList<String> instructionList = FileUtility.convertFileToStringArray(file);
		for (String instructionString : instructionList) {
			instructions.add(InstructionFactory.create(instructionString));
		}
	}

	protected ArrayList<Instruction> getInstructions() {
		return instructions;
	}

	public void performInstructions() {
		for (Instruction instruction : instructions) {
			head = instruction.moveHead(head);
			for(int x=0; x<instruction.getSpaces(); x++) {
				moveTailTowardsHead();
			}
		}
	}

	private void moveTailTowardsHead() {
		grid.get(tail.getY()).get(tail.getX()).setHasBeenVisited(true);
		if(!headAndTailAreTouching()) {
			if(headAndTailAreSameRow()) {
				moveTailHorizontal();
			}
			else if(headAndTailAreSameCol()) {
				moveTailVertical();
			} else {
				moveTailHorizontal();
				moveTailVertical();
			}
		}
	}

	private void moveTailVertical() {
		if(head.getY()< tail.getY()) {
			tail = new Coordinate(tail.getX(), tail.getY()-1);
		} else {
			tail = new Coordinate(tail.getX(), tail.getY()+1);
		}
	}

	private void moveTailHorizontal() {
		if(head.getX()< tail.getX()) {
			tail = new Coordinate(tail.getX()-1, tail.getY());
		} else {
			tail = new Coordinate(tail.getX()+1, tail.getY());
		}
	}

	private boolean headAndTailAreSameCol() {
		return head.getX() == tail.getX();
	}

	private boolean headAndTailAreSameRow() {
		return head.getY() == tail.getY();
	}

	private boolean headAndTailAreTouching() {
		if((head.getX()-tail.getX() >=-1) && (head.getX()-tail.getX() <=1) &&
			(head.getY()-tail.getY() >=-1) && (head.getY()-tail.getY() <=1))
				return true;
		else return false;
	}

	public int getTotalVisitedSpots() {
		int total = 0;
		for(int y=0; y<grid.size(); y++) {
			for(int x=0; x<grid.get(y).size(); x++) {
				if(grid.get(y).get(x).isHasBeenVisited()) {
					total++;
				}
			}
		}
		return total;
	}

}
