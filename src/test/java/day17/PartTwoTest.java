package day17;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PartTwoTest {

	private ShapeMovesFromInput shapeMovesFromInput;
	
	@BeforeEach
	public void setup() {
		shapeMovesFromInput = new ShapeMovesFromInput();
	}
	
	@Test
	void verify_1Trill_answer_for_sample() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		shapeMovesFromInput.setFileToUse(new File(fileName.getPath()));
		shapeMovesFromInput.populateDirectionsAndShapesList();
		
		assertEquals(1514285714288l, shapeMovesFromInput.getTotalHeightOf1TrillionShapes());
	}
	
	@Test
	void partTwo_Answer() throws Exception {
		shapeMovesFromInput.populateDirectionsAndShapesList();
		shapeMovesFromInput.generateStartGrid(3);
		
		System.out.println("So for 1000000000000: " + shapeMovesFromInput.getTotalHeightOf1TrillionShapes());
		//		assertEquals(1514285714288l, 1000000000000l/50*79);
	}
}
