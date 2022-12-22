package day18;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PartOneTest {

	private CubesFromInput cubesFromInput;
	
	@BeforeEach
	public void setup() {
		cubesFromInput = new CubesFromInput();
	}
	
	@Test
	void getting_input_into_cubes() throws Exception {
		ArrayList<Cube> expectedCubes = new ArrayList<Cube>();
		expectedCubes.add(new Cube(2,2,2));
		expectedCubes.add(new Cube(1,2,2));
		expectedCubes.add(new Cube(3,2,2));
		expectedCubes.add(new Cube(2,1,2));
		expectedCubes.add(new Cube(2,3,2));
		expectedCubes.add(new Cube(2,2,1));
		expectedCubes.add(new Cube(2,2,3));
		expectedCubes.add(new Cube(2,2,4));
		expectedCubes.add(new Cube(2,2,6));
		expectedCubes.add(new Cube(1,2,5));
		expectedCubes.add(new Cube(3,2,5));
		expectedCubes.add(new Cube(2,1,5));
		expectedCubes.add(new Cube(2,3,5));
		
		URL fileName = getClass().getResource("SampleInput.txt");
		cubesFromInput.setFileToUse(new File(fileName.getPath()));
		cubesFromInput.populateCubes();
		assertEquals(expectedCubes, cubesFromInput.getCubes());
	}

	@Test
	void verify_sample_surface_area_is_64() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		cubesFromInput.setFileToUse(new File(fileName.getPath()));
		cubesFromInput.populateCubes();
		
		assertEquals(64, cubesFromInput.getTotalSurfaceAreaOfCubes());
	}
	
	@Test
	void partOne_Answer() throws Exception {
		cubesFromInput.populateCubes();
//		System.out.println(cubesFromInput.getTotalSurfaceAreaOfCubes());
		assertEquals(4536, cubesFromInput.getTotalSurfaceAreaOfCubes());
	}
}
