package day4;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.FileUtility;


public class PartTwoTest {

	private ElfPairsFromInput elfPairsFromInput;
	
	@BeforeEach
	public void setup() {
		elfPairsFromInput = new ElfPairsFromInput();
	}
	
	@Test
	void verify_doesHaveAnyOverlap() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		elfPairsFromInput.setFileToUse(new File(fileName.getPath()));
		elfPairsFromInput.populateElfPairs();
		assertEquals(false, elfPairsFromInput.getElfPairs().get(0).doesHaveAnyOverlap());
		assertEquals(false, elfPairsFromInput.getElfPairs().get(1).doesHaveAnyOverlap());
		assertEquals(true, elfPairsFromInput.getElfPairs().get(2).doesHaveAnyOverlap());
		assertEquals(true, elfPairsFromInput.getElfPairs().get(3).doesHaveAnyOverlap());
		assertEquals(true, elfPairsFromInput.getElfPairs().get(4).doesHaveAnyOverlap());
		assertEquals(true, elfPairsFromInput.getElfPairs().get(5).doesHaveAnyOverlap());
	}
	
	@Test
	void verify_number_of_totalOverlapPairs_in_elfPairs() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		elfPairsFromInput.setFileToUse(new File(fileName.getPath()));
		elfPairsFromInput.populateElfPairs();
		assertEquals(4, elfPairsFromInput.getTotalOverlapPairs());
	}
	
	@Test
	void partTwo_Answer() throws Exception {
		elfPairsFromInput.populateElfPairs();
		System.out.println(elfPairsFromInput.getTotalOverlapPairs());
		assertEquals(815, elfPairsFromInput.getTotalOverlapPairs());
	}
}
