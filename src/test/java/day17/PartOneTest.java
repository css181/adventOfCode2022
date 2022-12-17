package day17;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PartOneTest {

	private ShapeMovesFromInput shapeMovesFromInput;
	
	@BeforeEach
	public void setup() {
		shapeMovesFromInput = new ShapeMovesFromInput();
	}
	
	@Test
	void can_print_all_shapes() throws Exception {
		String expectedHorizontalPrint = "####";
		String horizontalPrint = new HorizontalShape().toString();
		assertEquals(expectedHorizontalPrint, horizontalPrint);
		
		String expectedPlusPrint = ".#." + "\n" +
								   "###" + "\n" +
								   ".#.";
		String PlusPrint = new PlusShape().toString();
		assertEquals(expectedPlusPrint, PlusPrint);

		String expectedLPrint = "..#" + "\n" +
					 		    "..#" + "\n" +
							    "###";
		String LPrint = new LShape().toString();
		assertEquals(expectedLPrint, LPrint);
		
		String expectedVerticalPrint = 	"#" + "\n" +
										"#" + "\n" +
										"#" + "\n" +
										"#";
		String vertiacalPrint = new VerticalShape().toString();
		assertEquals(expectedVerticalPrint, vertiacalPrint);

		String expectedBoxPrint = 	"##" + "\n" +
					 		    	"##";
		String BoxPrint = new BoxShape().toString();
		assertEquals(expectedBoxPrint, BoxPrint);
	}
	
	@Test
	void can_print_start_grid() throws Exception {
		shapeMovesFromInput.generateStartGrid(3);
		String expectedPrint = 	"|.......|\n"
							  + "|.......|\n"
							  + "|.......|\n"
							  + "+#######+";
		assertEquals(expectedPrint, shapeMovesFromInput.printGrid());
	}
	
	@Test
	void each_shape_can_be_added_to_starting_grid() throws Exception {
		shapeMovesFromInput.generateStartGrid(3);
		shapeMovesFromInput.addShapeToGrid(new HorizontalShape());
		String expectedPrint = 	"|..####.|\n"
							  + "|.......|\n"
							  + "|.......|\n"
							  + "|.......|\n"
							  + "+#######+";
		assertEquals(expectedPrint, shapeMovesFromInput.printGrid());
		
		shapeMovesFromInput.generateStartGrid(3);
		shapeMovesFromInput.addShapeToGrid(new LShape());
		expectedPrint =  		"|....#..|\n"
							  + "|....#..|\n"
							  + "|..###..|\n"
							  + "|.......|\n"
							  + "|.......|\n"
							  + "|.......|\n"
							  + "+#######+";
		assertEquals(expectedPrint, shapeMovesFromInput.printGrid());
	}
	
	@Test
	void shapes_in_order_are_horizontal_plus_l_vertical_box() throws Exception {
		shapeMovesFromInput.populateDirectionsAndShapesList();
		ArrayList<Shape> expectedShapeArray = new ArrayList<Shape>();
		expectedShapeArray.add(new HorizontalShape());
		expectedShapeArray.add(new PlusShape());
		expectedShapeArray.add(new LShape());
		expectedShapeArray.add(new VerticalShape());
		expectedShapeArray.add(new BoxShape());
		assertEquals(expectedShapeArray, shapeMovesFromInput.getShapesOrderedList());
	}
	
	@Test
	void shapes_move_left_or_right_and_down_step_by_step_until_or_unless_hitting_something() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		shapeMovesFromInput.setFileToUse(new File(fileName.getPath()));
		shapeMovesFromInput.populateDirectionsAndShapesList();
		shapeMovesFromInput.generateStartGrid(3);
		shapeMovesFromInput.processNewShapeDropping();
		
		String expectedPrint = 	  "|.......|\n"
								+ "|.......|\n"
								+ "|.......|\n"
								+ "|..####.|\n"
								+ "+#######+";
		assertEquals(expectedPrint, shapeMovesFromInput.printGrid());
	}
	
	@Test
	void a_new_rock_starts_with_3_units_of_space_from_the_top_of_the_last_settled_shape() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		shapeMovesFromInput.setFileToUse(new File(fileName.getPath()));
		shapeMovesFromInput.populateDirectionsAndShapesList();
		shapeMovesFromInput.generateStartGrid(3);
		shapeMovesFromInput.processNewShapeDropping();
		
		shapeMovesFromInput.addShapeToGrid(new PlusShape());
		String expectedPrint = "|...#...|\n"
							 + "|..###..|\n"
							 + "|...#...|\n"
							 + "|.......|\n"
							 + "|.......|\n"
							 + "|.......|\n"
							 + "|..####.|\n"
							 + "+#######+";
		assertEquals(expectedPrint, shapeMovesFromInput.printGrid());
	}
	
	@Test
	void first_two_shapes_with_sample_directions_should_look_correct() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		shapeMovesFromInput.setFileToUse(new File(fileName.getPath()));
		shapeMovesFromInput.populateDirectionsAndShapesList();
		shapeMovesFromInput.generateStartGrid(3);
		
		shapeMovesFromInput.processNewShapeDropping();
		shapeMovesFromInput.processNewShapeDropping();
		
		String expectedPring = "|.......|\n"
							 + "|.......|\n"
							 + "|.......|\n"
							 + "|...#...|\n"
							 + "|..###..|\n"
							 + "|...#...|\n"
							 + "|..####.|\n"
							 + "+#######+";
		assertEquals(expectedPring, shapeMovesFromInput.printGrid());
	}
	
	@Test
	void first_10_shapes_with_sample_directions_should_look_correct() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		shapeMovesFromInput.setFileToUse(new File(fileName.getPath()));
		shapeMovesFromInput.populateDirectionsAndShapesList();
		shapeMovesFromInput.generateStartGrid(3);
		
		shapeMovesFromInput.processNewShapeDropping();
		shapeMovesFromInput.processNewShapeDropping();
		shapeMovesFromInput.processNewShapeDropping();
		shapeMovesFromInput.processNewShapeDropping();
		shapeMovesFromInput.processNewShapeDropping();
		shapeMovesFromInput.processNewShapeDropping();
		shapeMovesFromInput.processNewShapeDropping();
		shapeMovesFromInput.processNewShapeDropping();
		shapeMovesFromInput.processNewShapeDropping();
		shapeMovesFromInput.processNewShapeDropping();
		shapeMovesFromInput.addShapeToGrid(new HorizontalShape());
		
		String expectedPring = 
				  "|..####.|\n"
				+ "|.......|\n"
				+ "|.......|\n"
				+ "|.......|\n"
				+ "|....#..|\n"
				+ "|....#..|\n"
				+ "|....##.|\n"
				+ "|##..##.|\n"
				+ "|######.|\n"
				+ "|.###...|\n"
				+ "|..#....|\n"
				+ "|.####..|\n"
				+ "|....##.|\n"
				+ "|....##.|\n"
				+ "|....#..|\n"
				+ "|..#.#..|\n"
				+ "|..#.#..|\n"
				+ "|#####..|\n"
				+ "|..###..|\n"
				+ "|...#...|\n"
				+ "|..####.|\n"
				+ "+#######+";
		assertEquals(expectedPring, shapeMovesFromInput.printGrid());
	}
	
	@Test
	void verify_2022_shapes_in_sample_input_tower_is_3068_tall() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		shapeMovesFromInput.setFileToUse(new File(fileName.getPath()));
		shapeMovesFromInput.populateDirectionsAndShapesList();
		shapeMovesFromInput.generateStartGrid(3);
		
		for(int x=0; x<2022; x++)
			shapeMovesFromInput.processNewShapeDropping();
		shapeMovesFromInput.addShapeToGrid(new HorizontalShape());
		
		assertEquals(3068, shapeMovesFromInput.getGrid().length-5);//3 empty + floor + new Horizontal
	}
	
	@Test
	void partOne_Answer() throws Exception {
		shapeMovesFromInput.populateDirectionsAndShapesList();
		shapeMovesFromInput.generateStartGrid(3);
		
		for(int x=0; x<2022; x++)
			shapeMovesFromInput.processNewShapeDropping();
		shapeMovesFromInput.addShapeToGrid(new HorizontalShape());
		
		System.out.println(shapeMovesFromInput.getGrid().length-5);//3 empty + floor + new Horizontal
//		assertEquals(3068, shapeMovesFromInput.getGrid().length-5);//3 empty + floor + new Horizontal
	}
}
