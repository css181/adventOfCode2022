package day17;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

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
		if(curShapeIndex==shapes.size()-1) {
			curShapeIndex=0;
		}
		Shape nextShape = shapes.get(curShapeIndex++);
		chamber = nextShape.addShapeToGrid(chamber);
		boolean shapeIsStillMovingDown = false;
		do {
			if(directionIndex==directions.size()-1) {
				directionIndex=0;
			}
			shapeIsStillMovingDown = nextShape.processOneMove(directions.get(directionIndex ++), chamber);
		} while (shapeIsStillMovingDown );
	}

	public String printGrid() {
		return chamber.printGrid();
	}

}
