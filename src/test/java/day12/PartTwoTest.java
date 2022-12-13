package day12;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URL;
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
	void verify_shortest_sample_path_is_29_from_any_a() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		elevationsFromInput.setFileToUse(new File(fileName.getPath()));
		elevationsFromInput.populateElevationGrid();
		
		ElevationPath shortestPath = getShortestPathFromAnyA(elevationsFromInput.getElevationGrid().get(0).get(0));
		
		
		assertEquals(29, shortestPath.getPriorElevations().size());
	}
	
	@Test
	void partTwo_Answer() throws Exception {
		elevationsFromInput.populateElevationGrid();
		ElevationPath shortestPath = getShortestPathFromAnyA(elevationsFromInput.getElevationGrid().get(20).get(0));
		
		System.out.println("Answer:");
		System.out.println(shortestPath);
		System.out.println("Shortest Steps: " + shortestPath.getPriorElevations().size());
		assertEquals(480, shortestPath.getPriorElevations().size()-2);//Minus 2 to remove start and end???
	}

	private ElevationPath getShortestPathFromAnyA(Elevation initialStart) {
		ElevationPath shortestPath = elevationsFromInput.getShortestPath(initialStart);
		
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
		return shortestPath;
	}
}
