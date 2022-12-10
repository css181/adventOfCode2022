package day9;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PartOneTest {

	private RopeMovesFromInput ropeMovesFromInput;
	
	@BeforeEach
	public void setup() {
		ropeMovesFromInput = new RopeMovesFromInput();
	}
	
	@Test 
	void can_create_grid_of_spots_of_any_size_with_head_tail_and_start_defaulting_to_middle() {
		ArrayList<ArrayList<Spot>> grid = new ArrayList<ArrayList<Spot>>();
		grid.add(new ArrayList<Spot>(Arrays.asList(new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot())));
		grid.add(new ArrayList<Spot>(Arrays.asList(new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot())));
		grid.add(new ArrayList<Spot>(Arrays.asList(new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot())));
		grid.add(new ArrayList<Spot>(Arrays.asList(new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot())));
		grid.add(new ArrayList<Spot>(Arrays.asList(new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot())));
		grid.add(new ArrayList<Spot>(Arrays.asList(new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot())));
		grid.add(new ArrayList<Spot>(Arrays.asList(new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot())));
		grid.add(new ArrayList<Spot>(Arrays.asList(new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot())));
		grid.add(new ArrayList<Spot>(Arrays.asList(new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot())));
		grid.add(new ArrayList<Spot>(Arrays.asList(new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot(), new Spot())));

		ropeMovesFromInput.generateGrid(10);
		ArrayList<ArrayList<Spot>> actual = ropeMovesFromInput.getGrid();
		assertEquals(grid, actual);
		assertEquals(new Coordinate(5,5), ropeMovesFromInput.getStart());
		assertEquals(new Coordinate(5,5), ropeMovesFromInput.getHead());
		assertEquals(new Coordinate(5,5), ropeMovesFromInput.getTail());
	}
	
	@Test
	void getting_input_into_instructions() throws Exception {
		ArrayList<Instruction> expectedInstructions = new ArrayList<Instruction>();
		expectedInstructions.add(new RightInstruction(4));
		expectedInstructions.add(new UpInstruction(4));
		expectedInstructions.add(new LeftInstruction(3));
		expectedInstructions.add(new DownInstruction(1));
		expectedInstructions.add(new RightInstruction(4));
		expectedInstructions.add(new DownInstruction(1));
		expectedInstructions.add(new LeftInstruction(5));
		expectedInstructions.add(new RightInstruction(2));
		
		
		URL fileName = getClass().getResource("SampleInput.txt");
		ropeMovesFromInput.setFileToUse(new File(fileName.getPath()));
		ropeMovesFromInput.populateInstructions();
		assertEquals(expectedInstructions, ropeMovesFromInput.getInstructions());
	}
	
	@Test
	void after_processing_sample_input_head_is_up2_right2_from_start() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		ropeMovesFromInput.setFileToUse(new File(fileName.getPath()));
		ropeMovesFromInput.generateGrid(20);
		ropeMovesFromInput.populateInstructions();
		
		ropeMovesFromInput.performInstructions();
		Coordinate start = ropeMovesFromInput.getStart();
		assertEquals(new Coordinate(start.getX()+2, start.getY()-2), ropeMovesFromInput.getHead());
	}
	
	@Test
	void verify_tail_after_processing_sample_input_tail_is_up2_right1_from_start() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		ropeMovesFromInput.setFileToUse(new File(fileName.getPath()));
		ropeMovesFromInput.generateGrid(20);
		ropeMovesFromInput.populateInstructions();
		
		ropeMovesFromInput.performInstructions();
		Coordinate start = ropeMovesFromInput.getStart();
		assertEquals(new Coordinate(start.getX()+1, start.getY()-2), ropeMovesFromInput.getTail());
	}
	
	@Test
	void verifty_13_spots_have_been_visited_from_sample_input() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		ropeMovesFromInput.setFileToUse(new File(fileName.getPath()));
		ropeMovesFromInput.generateGrid(20);
		ropeMovesFromInput.populateInstructions();
		
		ropeMovesFromInput.performInstructions();
		assertEquals(13, ropeMovesFromInput.getTotalVisitedSpots());
	}
	
	@Test
	void part_one_answer() throws Exception {
		ropeMovesFromInput.generateGrid(2000);
		ropeMovesFromInput.populateInstructions();
		
		ropeMovesFromInput.performInstructions();
//		System.out.println(ropeMovesFromInput.getTotalVisitedSpots());
		assertEquals(6406, ropeMovesFromInput.getTotalVisitedSpots());
	}
}
