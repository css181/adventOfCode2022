package day17;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import utilities.FileUtility;

public class ShapeMovesFromInput {

	private static File file;
	private ArrayList<Shape> shapes;
	private ArrayList<Character> directions;
	private Chamber chamber;
	private int curShapeIndex = 0;
	private int directionIndex = 0;
	
	public ShapeMovesFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		shapes = new ArrayList<Shape>();
		chamber = new Chamber();
	}
	
	protected void setFileToUse(File file) {
		ShapeMovesFromInput.file = file;
	}
	
	public void populateDirectionsAndShapesList() {
		directions = FileUtility.convertFileToCharacters(file);
		shapes.add(new HorizontalShape());
		shapes.add(new PlusShape());
		shapes.add(new LShape());
		shapes.add(new VerticalShape());
		shapes.add(new BoxShape());
	}

	public void generateStartGrid(int maxHeight) {
		chamber.grid = new ChamberTile[maxHeight+1][9];
		for(int row=0; row<chamber.grid.length; row++) {
			for(int col=0; col<chamber.grid[row].length; col++) {
				chamber.grid[row][col] = new ChamberTile();
			}
		}
		chamber.grid[maxHeight][0].value='+';
		chamber.grid[maxHeight][8].value='+';
		for(int x=1; x<8; x++) {
			chamber.grid[maxHeight][x].value='#';
		}
		for(int row=0; row<maxHeight; row++) {
			chamber.grid[row][0].value='|';
			chamber.grid[row][8].value='|';
		}
		chamber.lowestRockIndex=3;
		curShapeIndex=0;
		directionIndex=0;
	}

	public ChamberTile[][] getGrid() {
		return chamber.grid;
	}
	public ArrayList<Shape> getShapesOrderedList() {
		return shapes;
	}
	
	public void addShapeToGrid(Shape shape) {
		chamber = shape.addShapeToGrid(chamber);
	}

	public void processNewShapeDropping() {
		if(curShapeIndex==shapes.size()) {
			curShapeIndex=0;
		}
		Shape nextShape = shapes.get(curShapeIndex++);
		chamber = nextShape.addShapeToGrid(chamber);
		boolean shapeIsStillMovingDown = false;
		do {
			if(directionIndex==directions.size()) {
				directionIndex=0;
			}
			shapeIsStillMovingDown = nextShape.processOneMove(directions.get(directionIndex ++), chamber);
		} while (shapeIsStillMovingDown );
		chamber = getChamberToHaveHighestRockIndex3(chamber);
	}
	
	public static Chamber getChamberToHaveHighestRockIndex3(Chamber chamber) {
		Chamber temp = new Chamber();
		while (chamber.lowestRockIndex<3) {
			temp.grid = new ChamberTile[chamber.grid.length+1][chamber.grid[0].length];
			for(int col=0; col<9; col++) {
				temp.grid[0][col] = new ChamberTile();
			}
			temp.grid[0][0].value='|';
			temp.grid[0][8].value='|';
			for(int row=0; row<chamber.grid.length; row++) {
				for(int col=0; col<9; col++) {
					temp.grid[row+1][col] = chamber.grid[row][col];
				}
			}
			chamber.lowestRockIndex++;
			chamber.grid=temp.grid;
		}
		while (chamber.lowestRockIndex>3) {
			temp.grid = new ChamberTile[chamber.grid.length-1][chamber.grid[0].length];
			for(int row=1; row<chamber.grid.length; row++) {
				for(int col=0; col<9; col++) {
					temp.grid[row-1][col] = chamber.grid[row][col];
				}
			}
			chamber.lowestRockIndex--;
			chamber.grid=temp.grid;
		}
		return chamber;
	}

	public String printGrid() {
		return chamber.printGrid();
	}

	public StateInfo getStateInfoThatYieldsPattern() {
		generateStartGrid(3);
		Set<StateInfo> stateInfoWhenShapesReset = new HashSet<StateInfo>();
		ArrayList<StateInfo> stateInfoList = new ArrayList<StateInfo>();
		int count = 0;
		do {
			count++;
			processNewShapeDropping();
			if(curShapeIndex==shapes.size()) {
				StateInfo stateInfo = new StateInfo(chamber.priorLeftMostIndex, chamber.priorRightMostIndex, directionIndex, count);
				stateInfoList.add(stateInfo);
				if(doesSetContainCurInfo(stateInfo, stateInfoWhenShapesReset)) {
//					System.out.println("Pattern repeats at directionIndex: " + directionIndex);
//					System.out.println("Size at time of repeat = " + (chamber.grid.length-4));//3 empty + floor
//					System.out.println("Pattern Detected at StateInfo: " + stateInfo);
//					System.out.println(stateInfoList);
					return stateInfo;
				}
//				System.out.println("Adding stateInfo: " + stateInfo);
				stateInfoWhenShapesReset.add(stateInfo);
			}
		} while (count<100000);
		throw new RuntimeException("Couldn't find pattern within 100,000");
	}
	
	private boolean doesSetContainCurInfo(StateInfo stateInfo, Set<StateInfo> stateInfoWhenShapesReset) {
		for (StateInfo info : stateInfoWhenShapesReset) {
			if(stateInfo.equals(info)) {
				return true;
			}
		}
		return false;
	}

	public int getHeightAfterXShapes(long shapesCount) {
		generateStartGrid(3);
		for(int x=0; x<shapesCount; x++)
			processNewShapeDropping();
		return chamber.grid.length-4;//3 empty + floor
	}
	
	public long getTotalHeightOf1TrillionShapes() {
		StateInfo patternInfo = getStateInfoThatYieldsPattern();
		int heightThroughfirstPattern = getHeightAfterXShapes(patternInfo.getTotalShapestoYieldThis());
		int numOfShapesUntilPatternStarts = getNumOfShapesTillPatternStarts(patternInfo);
		int numOfShapesInPattern = patternInfo.getTotalShapestoYieldThis() - numOfShapesUntilPatternStarts;
		int heightAtStartOfPattern = getHeightAfterXShapes(numOfShapesUntilPatternStarts);
		int patternHeight = heightThroughfirstPattern - heightAtStartOfPattern;
		
		long remainingShapes = 1000000000000l - patternInfo.getTotalShapestoYieldThis();
		long heightThroughLastPattern = remainingShapes/numOfShapesInPattern * patternHeight + heightThroughfirstPattern;
		
		long remainderShapesAfterLastPattern = remainingShapes%numOfShapesInPattern;
		int heightAtRemainderPlusThroughFirstPattern = getHeightAfterXShapes(patternInfo.getTotalShapestoYieldThis() + remainderShapesAfterLastPattern);
		int heightOfRemainderShapes = heightAtRemainderPlusThroughFirstPattern-heightThroughfirstPattern;
		long answer = heightThroughLastPattern + heightOfRemainderShapes;
		System.out.println("Pattern size=" + numOfShapesInPattern + ", heightThroughFirstPattern=" + heightThroughfirstPattern);
		System.out.println("Pattern height=" + patternHeight);
		System.out.println("Remaining shapes=" + remainderShapesAfterLastPattern + ", height of those=" + heightOfRemainderShapes);
		return answer;
	}

	private int getNumOfShapesTillPatternStarts(StateInfo patternInfo) {
		generateStartGrid(3);
		int count = 0;
		do {
			count++;
			processNewShapeDropping();
			StateInfo stateInfo = new StateInfo(chamber.priorLeftMostIndex, chamber.priorRightMostIndex, directionIndex, count);
			if(patternInfo.equals(stateInfo)) {
				System.out.println("Pattern begins after only: " + count);
				return count;
			}
		} while (count<100000);
		throw new RuntimeException("Couldn't find pattern within 100,000");
	}

}
