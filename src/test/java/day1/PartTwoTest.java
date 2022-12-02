package day1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.FileUtility;

class PartTwoTest {

	private ElvesFromInput elvesFromInput;
	
	@BeforeEach
	public void setup() {
		elvesFromInput = new ElvesFromInput();
	}
	
	@Test
	void getHighestCalorieCount_OfTop3Elves() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		elvesFromInput.setFileToUse(new File(fileName.getPath()));
		elvesFromInput.populateElves();
		assertEquals(45000l, elvesFromInput.getHighestCalorieCountTop3Elves());
	}
	
	@Test
	void partTwoAnswer() throws Exception {
		elvesFromInput.populateElves();
//		System.out.println(elvesFromInput.getHighestCalorieCountTop3Elves());
		assertEquals(212117, elvesFromInput.getHighestCalorieCount());
	}
}
