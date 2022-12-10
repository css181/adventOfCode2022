package day9;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PartTwoTest {

	private RopeMovesFromInput ropeMovesFromInput;
	
	@BeforeEach
	public void setup() {
		ropeMovesFromInput = new RopeMovesFromInput();
	}
	
	@Test
	void getting_input_into_instructions() throws Exception {
		ArrayList<Instruction> expectedInstructions = new ArrayList<Instruction>();
		expectedInstructions.add(new RightInstruction(5));
		expectedInstructions.add(new UpInstruction(8));
		expectedInstructions.add(new LeftInstruction(8));
		expectedInstructions.add(new DownInstruction(3));
		expectedInstructions.add(new RightInstruction(17));
		expectedInstructions.add(new DownInstruction(10));
		expectedInstructions.add(new LeftInstruction(25));
		expectedInstructions.add(new UpInstruction(20));
		
		
		URL fileName = getClass().getResource("Sample2.txt");
		ropeMovesFromInput.setFileToUse(new File(fileName.getPath()));
		ropeMovesFromInput.populateInstructions();
		assertEquals(expectedInstructions, ropeMovesFromInput.getInstructions());
	}
	
	@Test
	void verify_head_position_for_sample2_input() throws Exception {
		URL fileName = getClass().getResource("Sample2.txt");
		ropeMovesFromInput.setFileToUse(new File(fileName.getPath()));
		ropeMovesFromInput.generateGrid(50);
		ropeMovesFromInput.populateInstructions();
		
		ropeMovesFromInput.performInstructionsPart2();
		Coordinate start = ropeMovesFromInput.getStart();
		assertEquals(new Coordinate(start.getX()-11, start.getY()-15), ropeMovesFromInput.getHead());
	}
	
	@Test
	void verify_tailsParts_after_one_move() throws Exception {
		ArrayList<Instruction> expectedInstructions = new ArrayList<Instruction>();
		expectedInstructions.add(new RightInstruction(5));
		ropeMovesFromInput.generateGrid(50);
		ropeMovesFromInput.setInstructions(expectedInstructions);
		
		ropeMovesFromInput.performInstructionsPart2();
		Coordinate start = ropeMovesFromInput.getStart();
		assertEquals(new Coordinate(start.getX()+5, start.getY()), ropeMovesFromInput.getHead());
		assertEquals(new Coordinate(start.getX()+4, start.getY()), ropeMovesFromInput.getTailParts().get(0));
		assertEquals(new Coordinate(start.getX()+3, start.getY()), ropeMovesFromInput.getTailParts().get(1));
		assertEquals(new Coordinate(start.getX()+2, start.getY()), ropeMovesFromInput.getTailParts().get(2));
		assertEquals(new Coordinate(start.getX()+1, start.getY()), ropeMovesFromInput.getTailParts().get(3));
		assertEquals(new Coordinate(start.getX(), start.getY()), ropeMovesFromInput.getTailParts().get(4));
		assertEquals(new Coordinate(start.getX(), start.getY()), ropeMovesFromInput.getTailParts().get(5));
		assertEquals(new Coordinate(start.getX(), start.getY()), ropeMovesFromInput.getTailParts().get(6));
		assertEquals(new Coordinate(start.getX(), start.getY()), ropeMovesFromInput.getTailParts().get(7));
		assertEquals(new Coordinate(start.getX(), start.getY()), ropeMovesFromInput.getTailParts().get(8));
	}
	
	@Test
	void verify_tailsParts_after_two_moves() throws Exception {
		ArrayList<Instruction> expectedInstructions = new ArrayList<Instruction>();
		expectedInstructions.add(new RightInstruction(5));
		expectedInstructions.add(new UpInstruction(8));
		ropeMovesFromInput.generateGrid(50);
		ropeMovesFromInput.setInstructions(expectedInstructions);
		
		ropeMovesFromInput.performInstructionsPart2();
		Coordinate start = ropeMovesFromInput.getStart();
		assertEquals(new Coordinate(start.getX()+5, start.getY()-8), ropeMovesFromInput.getHead());
		assertEquals(new Coordinate(start.getX()+5, start.getY()-7), ropeMovesFromInput.getTailParts().get(0));
		assertEquals(new Coordinate(start.getX()+5, start.getY()-6), ropeMovesFromInput.getTailParts().get(1));
		assertEquals(new Coordinate(start.getX()+5, start.getY()-5), ropeMovesFromInput.getTailParts().get(2));
		assertEquals(new Coordinate(start.getX()+5, start.getY()-4), ropeMovesFromInput.getTailParts().get(3));
		assertEquals(new Coordinate(start.getX()+4, start.getY()-4), ropeMovesFromInput.getTailParts().get(4));
		assertEquals(new Coordinate(start.getX()+3, start.getY()-3), ropeMovesFromInput.getTailParts().get(5));
		assertEquals(new Coordinate(start.getX()+2, start.getY()-2), ropeMovesFromInput.getTailParts().get(6));
		assertEquals(new Coordinate(start.getX()+1, start.getY()-1), ropeMovesFromInput.getTailParts().get(7));
		assertEquals(new Coordinate(start.getX(), start.getY()), ropeMovesFromInput.getTailParts().get(8));
	}
	
	@Test
	void verify_tail_position_and_total_visited_for_sample2_input() throws Exception {
		URL fileName = getClass().getResource("Sample2.txt");
		ropeMovesFromInput.setFileToUse(new File(fileName.getPath()));
		ropeMovesFromInput.generateGrid(50);
		ropeMovesFromInput.populateInstructions();
		
		ropeMovesFromInput.performInstructionsPart2();
		
		Coordinate start = ropeMovesFromInput.getStart();
		assertEquals(new Coordinate(start.getX()-11, start.getY()-6), ropeMovesFromInput.getTail());
		assertEquals(new Coordinate(start.getX()-11, start.getY()-14), ropeMovesFromInput.getTailParts().get(0));
		assertEquals(new Coordinate(start.getX()-11, start.getY()-13), ropeMovesFromInput.getTailParts().get(1));
		assertEquals(new Coordinate(start.getX()-11, start.getY()-12), ropeMovesFromInput.getTailParts().get(2));
		assertEquals(new Coordinate(start.getX()-11, start.getY()-11), ropeMovesFromInput.getTailParts().get(3));
		assertEquals(new Coordinate(start.getX()-11, start.getY()-10), ropeMovesFromInput.getTailParts().get(4));
		assertEquals(new Coordinate(start.getX()-11, start.getY()-9), ropeMovesFromInput.getTailParts().get(5));
		assertEquals(new Coordinate(start.getX()-11, start.getY()-8), ropeMovesFromInput.getTailParts().get(6));
		assertEquals(new Coordinate(start.getX()-11, start.getY()-7), ropeMovesFromInput.getTailParts().get(7));
		assertEquals(new Coordinate(start.getX()-11, start.getY()-6), ropeMovesFromInput.getTailParts().get(8));
	}
	
	@Test
	void verify_totalVisitedSpotsOfTail_sample2() throws Exception {
		URL fileName = getClass().getResource("Sample2.txt");
		ropeMovesFromInput.setFileToUse(new File(fileName.getPath()));
		ropeMovesFromInput.generateGrid(50);
		ropeMovesFromInput.populateInstructions();
		
		ropeMovesFromInput.performInstructionsPart2();
		
		assertEquals(36, ropeMovesFromInput.getTotalVisitedSpots());
	}
	
	@Test
	void part_two_answer() throws Exception {
		ropeMovesFromInput.generateGrid(2000);
		ropeMovesFromInput.populateInstructions();
		
		ropeMovesFromInput.performInstructionsPart2();
		System.out.println(ropeMovesFromInput.getTotalVisitedSpots());
	}
}
