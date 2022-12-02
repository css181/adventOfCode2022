package day1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.FileUtility;

class PartOneTest {

	private ElvesFromInput elvesFromInput;
	
	@BeforeEach
	public void setup() {
		elvesFromInput = new ElvesFromInput();
	}
	
	@Test 
	void convertFileToArrayTest() {
		ArrayList<ArrayList<Long>> expected = new ArrayList<ArrayList<Long>>();
		expected.add(new ArrayList<Long> (Arrays.asList(1000l,2000l,3000l)));
		expected.add(new ArrayList<Long> (Arrays.asList(4000l)));
		expected.add(new ArrayList<Long> (Arrays.asList(5000l,6000l)));
		expected.add(new ArrayList<Long> (Arrays.asList(7000l,8000l,9000l)));
		expected.add(new ArrayList<Long> (Arrays.asList(10000l)));
		URL fileName = getClass().getResource("SampleInput.txt");
		ArrayList<ArrayList<Long>> actual = FileUtility.convertFileToListOfIntList(new File(fileName.getPath()), "");
		assertEquals(expected, actual);
	}
	
	@Test
	void getSampleListOfElves() throws Exception {
		ArrayList<Elf> expected = new ArrayList<Elf>();
		expected.add(new Elf(new ArrayList<Long> (Arrays.asList(1000l,2000l,3000l))));
		expected.add(new Elf(new ArrayList<Long> (Arrays.asList(4000l))));
		expected.add(new Elf(new ArrayList<Long> (Arrays.asList(5000l,6000l))));
		expected.add(new Elf(new ArrayList<Long> (Arrays.asList(7000l,8000l,9000l))));
		expected.add(new Elf(new ArrayList<Long> (Arrays.asList(10000l))));
		URL fileName = getClass().getResource("SampleInput.txt");
		elvesFromInput.setFileToUse(new File(fileName.getPath()));
		elvesFromInput.populateElves();
		assertEquals(expected, elvesFromInput.getElves());
	}
	
	@Test
	void verifyEachElfTotal_In_SampleInput() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		elvesFromInput.setFileToUse(new File(fileName.getPath()));
		elvesFromInput.populateElves();
		assertEquals(6000l, elvesFromInput.getElves().get(0).getTotalCalories());
		assertEquals(4000l, elvesFromInput.getElves().get(1).getTotalCalories());
		assertEquals(11000l, elvesFromInput.getElves().get(2).getTotalCalories());
		assertEquals(24000l, elvesFromInput.getElves().get(3).getTotalCalories());
		assertEquals(10000l, elvesFromInput.getElves().get(4).getTotalCalories());
	}
	
	@Test
	void getHighestCalorieCount() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		elvesFromInput.setFileToUse(new File(fileName.getPath()));
		elvesFromInput.populateElves();
		assertEquals(24000l, elvesFromInput.getHighestCalorieCount());
	}
	
	@Test
	void partOneAnswer() throws Exception {
		elvesFromInput.populateElves();
//		System.out.println(elvesFromInput.getHighestCalorieCount());
		assertEquals(72511l, elvesFromInput.getHighestCalorieCount());
	}
}
