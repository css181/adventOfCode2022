package day18;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PartTwoTest {

	private CubesFromInput cubesFromInput;
	
	@BeforeEach
	public void setup() {
		cubesFromInput = new CubesFromInput();
	}


	@Test
	void verify_there_is_only_1_air_cube_anywhere_in_sample() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		cubesFromInput.setFileToUse(new File(fileName.getPath()));
		cubesFromInput.populateCubes();
		cubesFromInput.calculateAirPocketCubes();
		
		assertEquals(1, cubesFromInput.getAirPocketCubes().size());
	}
	
	@Test
	void airPocketCubes_are_all_air_cubes_that_cant_get_to_outside() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		cubesFromInput.setFileToUse(new File(fileName.getPath()));
		cubesFromInput.populateCubes();
		cubesFromInput.calculateAirPocketCubes();
		
		assertEquals(new Cube(2,2,5), cubesFromInput.getAirPocketCubes().get(0));
	}
	
	@Test
	void verify_sample_surface_area_minus_pockets_is_58() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		cubesFromInput.setFileToUse(new File(fileName.getPath()));
		cubesFromInput.populateCubes();
		cubesFromInput.calculateAirPocketCubes();
		
		assertEquals(58, cubesFromInput.getTotalSurfaceAreaOfCubes() - cubesFromInput.getAirPocketSurfaceArea());
	}

	@Test
	void partTwo_Answer() throws Exception {
		cubesFromInput.populateCubes();
		cubesFromInput.calculateAirPocketCubes();
		System.out.println("Part 2 - Total air pocket cubes: " + cubesFromInput.getAirPocketCubes().size());
		System.out.println(cubesFromInput.getTotalSurfaceAreaOfCubes() - cubesFromInput.getAirPocketSurfaceArea());
//		assertEquals(500, cubesFromInput.getTotalSurfaceAreaOfCubes());
	}
}
