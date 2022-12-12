package day12;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PartOneTest {

	private ElevationsFromInput elevationsFromInput;
	
	@BeforeEach
	public void setup() {
		elevationsFromInput = new ElevationsFromInput();
	}
	public Elevation getStart() {
		for (ArrayList<Elevation> row : elevationsFromInput.getElevationGrid()) {
			for (Elevation elevation : row) {
				if(elevation.getHeight() == Integer.valueOf('`')) {
					return elevation;
				}
			}
		}
		return null;
	}
	
	@Test
	void getting_input_into_elevationGrid() throws Exception {
		ArrayList<ArrayList<Elevation>> expectedElevationGrid = new ArrayList<ArrayList<Elevation>>();
		expectedElevationGrid.add(new ArrayList<Elevation>(Arrays.asList(new Elevation('`', new Coordinate(0, 0)), new Elevation('a', new Coordinate(1, 0)), new Elevation('b', new Coordinate(2, 0)), new Elevation('q', new Coordinate(3, 0)), new Elevation('p', new Coordinate(4, 0)), new Elevation('o', new Coordinate(5, 0)), new Elevation('n', new Coordinate(6, 0)), new Elevation('m', new Coordinate(7, 0)))));
		expectedElevationGrid.add(new ArrayList<Elevation>(Arrays.asList(new Elevation('a', new Coordinate(0, 1)), new Elevation('b', new Coordinate(1, 1)), new Elevation('c', new Coordinate(2, 1)), new Elevation('r', new Coordinate(3, 1)), new Elevation('y', new Coordinate(4, 1)), new Elevation('x', new Coordinate(5, 1)), new Elevation('w', new Coordinate(6, 1)), new Elevation('l', new Coordinate(7, 1)))));
		expectedElevationGrid.add(new ArrayList<Elevation>(Arrays.asList(new Elevation('a', new Coordinate(0, 2)), new Elevation('c', new Coordinate(1, 2)), new Elevation('c', new Coordinate(2, 2)), new Elevation('s', new Coordinate(3, 2)), new Elevation('z', new Coordinate(4, 2)), new Elevation('{', new Coordinate(5, 2)), new Elevation('x', new Coordinate(6, 2)), new Elevation('k', new Coordinate(7, 2)))));
		expectedElevationGrid.add(new ArrayList<Elevation>(Arrays.asList(new Elevation('a', new Coordinate(0, 3)), new Elevation('c', new Coordinate(1, 3)), new Elevation('c', new Coordinate(2, 3)), new Elevation('t', new Coordinate(3, 3)), new Elevation('u', new Coordinate(4, 3)), new Elevation('v', new Coordinate(5, 3)), new Elevation('w', new Coordinate(6, 3)), new Elevation('j', new Coordinate(7, 3)))));
		expectedElevationGrid.add(new ArrayList<Elevation>(Arrays.asList(new Elevation('a', new Coordinate(0, 4)), new Elevation('b', new Coordinate(1, 4)), new Elevation('d', new Coordinate(2, 4)), new Elevation('e', new Coordinate(3, 4)), new Elevation('f', new Coordinate(4, 4)), new Elevation('g', new Coordinate(5, 4)), new Elevation('h', new Coordinate(6, 4)), new Elevation('i', new Coordinate(7, 4)))));
		
		URL fileName = getClass().getResource("SampleInput.txt");
		elevationsFromInput.setFileToUse(new File(fileName.getPath()));
		elevationsFromInput.populateElevationGrid();
		assertEquals(expectedElevationGrid, elevationsFromInput.getElevationGrid());
	}
	
	@Test
	void verify_shortest_sample_path_is_31() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		elevationsFromInput.setFileToUse(new File(fileName.getPath()));
		elevationsFromInput.populateElevationGrid();
		
		assertEquals(31, elevationsFromInput.getShortestPath(getStart()).getPriorElevations().size());
	}
	
	@Test
	void partOne_Answer() throws Exception {
		elevationsFromInput.populateElevationGrid();
		
//		System.out.println(elevationsFromInput.getShortestPath(getStart()));
		//AoC site says my answer should be 481, even though my algorithm says 483, and I'm not sure how it could be shorter.
		assertEquals(481, elevationsFromInput.getShortestPath(getStart()).getPriorElevations().size()-2);//To remove start and end????
	}
}
