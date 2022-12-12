package day12;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PartTwoTest {

	private ElevationsFromInput elevationsFromInput;
	
	@BeforeEach
	public void setup() {
		elevationsFromInput = new ElevationsFromInput();
	}
	
	@Test
	void partTwo_Answer() throws Exception {
		elevationsFromInput.populateElevationGrid();
		ElevationPath shortestPath = elevationsFromInput.getShortestPath(elevationsFromInput.getElevationGrid().get(20).get(0));
		
		for (ArrayList<Elevation> row : elevationsFromInput.getElevationGrid()) {
			for (Elevation elevation : row) {
				if(elevation.getHeight()==Integer.valueOf('a')) {
					elevationsFromInput.resetAnswer();
					ElevationPath curShortestTest = elevationsFromInput.getShortestPath(elevation);
					if(curShortestTest!=null && curShortestTest.getPriorElevations().size() < shortestPath.getPriorElevations().size()) {
						shortestPath = curShortestTest;
					}
				}
			}
		}
		
		System.out.println("Answer:");
		System.out.println(shortestPath);
		System.out.println("Shortest Steps: " + shortestPath.getPriorElevations().size());
		assertEquals(480, shortestPath.getPriorElevations().size()-2);//Minus 2 to remove start and end???
	}
}
