package day3;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.FileUtility;


public class PartOneTest {

	private RugsackFromInput rugsackFromInput;
	
	@BeforeEach
	public void setup() {
		rugsackFromInput = new RugsackFromInput();
	}
	
	@Test 
	void convertFileToArrayTest() {
		ArrayList<ArrayList<Character>> expected = new ArrayList<ArrayList<Character>>();
		expected.add((new ArrayList<>(Arrays.asList('v','J','r','w','p','W','t','w','J','g','W','r','h','c','s','F','M','M','f','F','F','h','F','p'))));
		expected.add((new ArrayList<>(Arrays.asList('j','q','H','R','N','q','R','j','q','z','j','G','D','L','G','L','r','s','F','M','f','F','Z','S','r','L','r','F','Z','s','S','L'))));
		expected.add((new ArrayList<>(Arrays.asList('P','m','m','d','z','q','P','r','V','v','P','w','w','T','W','B','w','g'))));
		expected.add((new ArrayList<>(Arrays.asList('w','M','q','v','L','M','Z','H','h','H','M','v','w','L','H','j','b','v','c','j','n','n','S','B','n','v','T','Q','F','n'))));
		expected.add((new ArrayList<>(Arrays.asList('t','t','g','J','t','R','G','J','Q','c','t','T','Z','t','Z','T'))));
		expected.add((new ArrayList<>(Arrays.asList('C','r','Z','s','J','s','P','P','Z','s','G','z','w','w','s','L','w','L','m','p','w','M','D','w'))));
		
		URL fileName = getClass().getResource("SampleInput.txt");
		ArrayList<ArrayList<Character>> actual = FileUtility.convertFileToCharacterArray(new File(fileName.getPath()));
		assertEquals(expected, actual);
	}
	
	@Test
	void verifyNumbers() throws Exception {
		assertEquals(1, PriorityScore.calculate('a'));
		assertEquals(26, PriorityScore.calculate('z'));
		assertEquals(27, PriorityScore.calculate('A'));
		assertEquals(52, PriorityScore.calculate('Z'));
	}
	
	@Test
	void split_rugsacks_into_two_compartments() throws Exception {
		ArrayList<Character> expectedCompartment1 = new ArrayList<>(Arrays.asList('v','J','r','w','p','W','t','w','J','g','W','r'));
		ArrayList<Character> expectedCompartment2 = new ArrayList<>(Arrays.asList('h','c','s','F','M','M','f','F','F','h','F','p'));
		
		URL fileName = getClass().getResource("SampleInput.txt");
		rugsackFromInput.setFileToUse(new File(fileName.getPath()));
		rugsackFromInput.populateRugSacks();
		assertEquals(expectedCompartment1, rugsackFromInput.getRugsackList().get(0).getCompartment1());
		assertEquals(expectedCompartment2, rugsackFromInput.getRugsackList().get(0).getCompartment2());
	}
	
	@Test
	void verify_priority_of_each_sample_rugsack() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		rugsackFromInput.setFileToUse(new File(fileName.getPath()));
		rugsackFromInput.populateRugSacks();
		ArrayList<Rugsack> sackList = rugsackFromInput.getRugsackList();
		assertEquals(16, sackList.get(0).getPriority());
		assertEquals(38, sackList.get(1).getPriority());
		assertEquals(42, sackList.get(2).getPriority());
		assertEquals(22, sackList.get(3).getPriority());
		assertEquals(20, sackList.get(4).getPriority());
		assertEquals(19, sackList.get(5).getPriority());
	}
	
	@Test
	void PartOne_Answer() throws Exception {
		rugsackFromInput.populateRugSacks();
		ArrayList<Rugsack> sackList = rugsackFromInput.getRugsackList();
		int totalPriority = 0;
		for (Rugsack rugsack : sackList) {
			totalPriority += rugsack.getPriority();
		}
//		System.out.println(totalPriority);
		assertEquals(8039, totalPriority);
	}
}
