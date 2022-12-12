package day12;

import java.io.File;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import utilities.FileUtility;

public class ElevationsFromInput {

	private static File file;
	private ArrayList<ArrayList<Elevation>> elevationGrid;
	private ElevationPath answer = null;
	
	public ElevationsFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		elevationGrid = new ArrayList<ArrayList<Elevation>>();
	}
	
	protected void setFileToUse(File file) {
		ElevationsFromInput.file = file;
	}

	public void populateElevationGrid() {
		ArrayList<ArrayList<Character>> grid = FileUtility.convertFileToCharacterArray(file);
		for (int row=0; row<grid.size(); row++) {
			ArrayList<Character> curCharRow = grid.get(row);
			ArrayList<Elevation> curElevationRow = new ArrayList<Elevation>();
			for (int col=0; col<curCharRow.size(); col++) {
				Character letter = curCharRow.get(col);
				curElevationRow.add(new Elevation(letter, new Coordinate(col, row)));
			}
			elevationGrid.add(curElevationRow);
		}
	}

	protected ArrayList<ArrayList<Elevation>> getElevationGrid() {
		return elevationGrid;
	}

	public ElevationPath getShortestPath(Elevation start) {
		Queue<ElevationPath> queue = new ArrayDeque<ElevationPath>();
		Set<Elevation> visited = new HashSet<Elevation>();
		Elevation end = getEnd();
		
		return search(new ElevationPath(start, new LinkedList<Elevation>()), end, queue, visited);
	}
			
	private ElevationPath search(ElevationPath start, Elevation end, Queue<ElevationPath> queue, Set<Elevation> visited) {
		if(start.getElevation().equals(end)) {
			if(answer==null || start.getPriorElevations().size() < answer.getPriorElevations().size()) {
				answer = start;
				System.out.println(start);
//				System.out.println(printAnswerGrid(start.getPriorElevations()));
			}
			return answer;//we're done.
		}
//		System.out.println(visited.size() + "/" + (elevationGrid.size()*elevationGrid.get(0).size()));
		visited.add(start.getElevation());
		@SuppressWarnings("unchecked")
		LinkedList<Elevation> curPath = (LinkedList<Elevation>) start.getPriorElevations().clone();
		curPath.add(start.getElevation());
		//add things to the queue
		int curX = start.getElevation().getCoordinate().getX();
		int curY = start.getElevation().getCoordinate().getY();
		int curHeight = start.getElevation().getHeight();
		//UP
		if(curY > 0 && 
				elevationGrid.get(curY-1).get(curX).getHeight() <= (curHeight+1)) {
			if(!visited.contains(elevationGrid.get(curY-1).get(curX))) {
				Elevation newElevation = elevationGrid.get(curY-1).get(curX);
				queue.add(new ElevationPath(newElevation, curPath));
			}
		}
		//DOWN
		if(curY < (elevationGrid.size()-1) && 
				elevationGrid.get(curY+1).get(curX).getHeight() <= (curHeight+1)) {
			if(!visited.contains(elevationGrid.get(curY+1).get(curX))) {
				Elevation newElevation = elevationGrid.get(curY+1).get(curX);
				queue.add(new ElevationPath(newElevation, curPath));
			}
		}
		//LEFT
		if(curX > 0 
				&& elevationGrid.get(curY).get(curX-1).getHeight() <= (curHeight+1)) {
			if(!visited.contains(elevationGrid.get(curY).get(curX-1))) {
				Elevation newElevation = elevationGrid.get(curY).get(curX-1);
				queue.add(new ElevationPath(newElevation, curPath));
			}
		}
		//RIGHT
		if(curX < (elevationGrid.get(curY).size() - 1)
				&& elevationGrid.get(curY).get(curX+1).getHeight() <= (curHeight+1)) {
			if(!visited.contains(elevationGrid.get(curY).get(curX+1))) {
				Elevation newElevation = elevationGrid.get(curY).get(curX+1);
				queue.add(new ElevationPath(newElevation, curPath));
			}
		}
		while (queue.size()>0) {
			ElevationPath newTrial = queue.poll();
			if(!visited.contains(newTrial.getElevation()))
				search(newTrial, end, queue, visited);
		}
		return answer;
	}

	@SuppressWarnings("unused")
	//Used for debugging
	private String printAnswerGrid(LinkedList<Elevation> priorElevations) {
		String print="";
		for(int row=0; row<elevationGrid.size(); row++) {
			for(int col=0; col<elevationGrid.get(row).size(); col++) {
				Elevation curElevation = elevationGrid.get(row).get(col);
				if(!priorElevations.contains(curElevation)) { 
					print+=(char)curElevation.getHeight();
				} else {
					print+="#";
				}
			}
			print+="\n";
		}
		return print;
	}

	public void resetAnswer() {
		this.answer = null;
	}
	private Elevation getEnd() {
		for (ArrayList<Elevation> row : elevationGrid) {
			for (Elevation elevation : row) {
				if(elevation.getHeight() == Integer.valueOf('{')) {
					return elevation;
				}
			}
		}
		throw new RuntimeException("Could not find end.");
	}
	
}
