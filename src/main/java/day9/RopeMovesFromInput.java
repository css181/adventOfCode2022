package day9;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import utilities.FileUtility;

public class RopeMovesFromInput {

	private static File file;
	private ArrayList<ArrayList<Spot>> grid;
	private ArrayList<Instruction> instructions;
	private Coordinate start;
	private Coordinate tail;
	private Coordinate head;
	private ArrayList<Coordinate> tailParts;
	
	public RopeMovesFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		grid = new ArrayList<ArrayList<Spot>>();
		instructions = new ArrayList<Instruction>();
		tailParts = new ArrayList<Coordinate>();
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
		tailParts.add(tail);
		tailParts.add(tail);
		tailParts.add(tail);
		tailParts.add(tail);
		tailParts.add(tail);
		tailParts.add(tail);
		tailParts.add(tail);
		tailParts.add(tail);
		tailParts.add(tail);
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
	protected void setInstructions(ArrayList<Instruction> instructions) {
		this.instructions = instructions;
	}
	

	protected ArrayList<Coordinate> getTailParts() {
		return tailParts;
	}

	public void performInstructions() {
		for (Instruction instruction : instructions) {
			head = instruction.moveHead(head);
			for(int x=0; x<instruction.getSpaces(); x++) {
				moveTailTowardsHead();
			}
		}
	}
	
	public void performInstructionsPart2() {
		for (Instruction instruction : instructions) {
			head = instruction.moveHead(head);
			for(int x=0; x<instruction.getSpaces(); x++) {//each move count in instruction
				Coordinate priorTailPart = head;
				for (int index=0; index<tailParts.size(); index++) {//each tailpart
					Coordinate curTailPart = tailParts.get(index);
					if(index==0) {
						priorTailPart = head;
					} else {
						priorTailPart = tailParts.get(index-1);
					}
					if(index==tailParts.size()-1) {
						tail = curTailPart;
						grid.get(tail.getY()).get(tail.getX()).setHasBeenVisited(true);
					}
					tailParts.set(index, moveTailSegmentTowardsOtherSegment(curTailPart, priorTailPart));
				}
			}
		}
	}

	//TODO: Cleanup duplications later
	private Coordinate moveTailSegmentTowardsOtherSegment(Coordinate tailPart, Coordinate prior) {
		Coordinate newPosition = tailPart;
		if(!headAndTailAreTouching(prior, tailPart)) {
			if(headAndTailAreSameRow(prior, tailPart)) {
				if(prior.getX()< tailPart.getX()) {
					newPosition = new Coordinate(tailPart.getX()-1, tailPart.getY());
				} else {
					newPosition = new Coordinate(tailPart.getX()+1, tailPart.getY());
				}
			}
			else if(headAndTailAreSameCol(prior, tailPart)) {
				if(prior.getY()< tailPart.getY()) {
					newPosition = new Coordinate(tailPart.getX(), tailPart.getY()-1);
				} else {
					newPosition = new Coordinate(tailPart.getX(), tailPart.getY()+1);
				}
			} else {
				if(prior.getX()< tailPart.getX()) {
					newPosition = new Coordinate(tailPart.getX()-1, tailPart.getY());
				} else {
					newPosition = new Coordinate(tailPart.getX()+1, tailPart.getY());
				}
				if(prior.getY()< tailPart.getY()) {
					newPosition = new Coordinate(newPosition.getX(), newPosition.getY()-1);
				} else {
					newPosition = new Coordinate(newPosition.getX(), newPosition.getY()+1);
				}
			}
		}
		return newPosition;
	}

	private void moveTailTowardsHead() {
		grid.get(tail.getY()).get(tail.getX()).setHasBeenVisited(true);
		if(!headAndTailAreTouching(head, tail)) {
			if(headAndTailAreSameRow(head, tail)) {
				moveTailHorizontal();
			}
			else if(headAndTailAreSameCol(head, tail)) {
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

	private boolean headAndTailAreSameCol(Coordinate head, Coordinate tail) {
		return head.getX() == tail.getX();
	}

	private boolean headAndTailAreSameRow(Coordinate head, Coordinate tail) {
		return head.getY() == tail.getY();
	}

	private boolean headAndTailAreTouching(Coordinate head, Coordinate tail) {
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
