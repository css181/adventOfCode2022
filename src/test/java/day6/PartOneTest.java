package day6;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.FileUtility;


public class PartOneTest {

	private DataStreamFromInput dataStreamFromInput;
	
	@BeforeEach
	public void setup() {
		dataStreamFromInput = new DataStreamFromInput();
	}
	
	@Test 
	void convertFileToArrayTest() {
		String expected = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
		
		URL fileName = getClass().getResource("SampleInput.txt");
		String actual = FileUtility.convertFileToString(new File(fileName.getPath()));
		assertEquals(expected, actual);
	}
	
	@Test
	void get_index_of_first_unique_4_characters() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		dataStreamFromInput.setFileToUse(new File(fileName.getPath()));
		dataStreamFromInput.populateDataStream();
		assertEquals(7, dataStreamFromInput.getIndexOfFirstUniqueXChars(4));
	}
	
	@Test
	void partOne_Answer() throws Exception {
		dataStreamFromInput.populateDataStream();
//		System.out.println(dataStreamFromInput.getIndexOfFirstUniqueXChars(4));
		assertEquals(1892, dataStreamFromInput.getIndexOfFirstUniqueXChars(4));
	}
}
