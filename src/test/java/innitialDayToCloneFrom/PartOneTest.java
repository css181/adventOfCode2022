package innitialDayToCloneFrom;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.FileUtility;


public class PartOneTest {

	private DayXFromInput dayXFromInput;
	
	@BeforeEach
	public void setup() {
		dayXFromInput = new DayXFromInput();
	}
	
	@Test 
	void convertFileToArrayTest() {
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("A Y");
		expected.add("B X");
		expected.add("C Z");
		URL fileName = getClass().getResource("SampleInput.txt");
		ArrayList<String> actual = FileUtility.convertFileToStringArray(new File(fileName.getPath()));
		assertEquals(expected, actual);
	}
	
}
