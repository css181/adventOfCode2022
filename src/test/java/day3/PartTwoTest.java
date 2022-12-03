package day3;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.FileUtility;


public class PartTwoTest {

	private RugsackFromInput rugsackFromInput;
	
	@BeforeEach
	public void setup() {
		rugsackFromInput = new RugsackFromInput();
	}
	
	@Test
	void split_rugsacks_into_two_compartments() throws Exception {
		ArrayList<ArrayList<Character>> expectedElfGroup1Chars = new ArrayList<ArrayList<Character>>();
		expectedElfGroup1Chars.add((new ArrayList<>(Arrays.asList('v','J','r','w','p','W','t','w','J','g','W','r','h','c','s','F','M','M','f','F','F','h','F','p'))));
		expectedElfGroup1Chars.add((new ArrayList<>(Arrays.asList('j','q','H','R','N','q','R','j','q','z','j','G','D','L','G','L','r','s','F','M','f','F','Z','S','r','L','r','F','Z','s','S','L'))));
		expectedElfGroup1Chars.add((new ArrayList<>(Arrays.asList('P','m','m','d','z','q','P','r','V','v','P','w','w','T','W','B','w','g'))));
		
		ArrayList<ArrayList<Character>> expectedElfGroup2Chars = new ArrayList<ArrayList<Character>>();
		expectedElfGroup2Chars.add((new ArrayList<>(Arrays.asList('w','M','q','v','L','M','Z','H','h','H','M','v','w','L','H','j','b','v','c','j','n','n','S','B','n','v','T','Q','F','n'))));
		expectedElfGroup2Chars.add((new ArrayList<>(Arrays.asList('t','t','g','J','t','R','G','J','Q','c','t','T','Z','t','Z','T'))));
		expectedElfGroup2Chars.add((new ArrayList<>(Arrays.asList('C','r','Z','s','J','s','P','P','Z','s','G','z','w','w','s','L','w','L','m','p','w','M','D','w'))));
		
		URL fileName = getClass().getResource("SampleInput.txt");
		rugsackFromInput.setFileToUse(new File(fileName.getPath()));
		rugsackFromInput.populateRugSacks();
		assertEquals(expectedElfGroup1Chars, rugsackFromInput.getElfGroups().get(0).getSackListOfChars());
		assertEquals(expectedElfGroup2Chars, rugsackFromInput.getElfGroups().get(1).getSackListOfChars());
	}
	
	@Test
	void verify_badge_common_to_elf_group() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		rugsackFromInput.setFileToUse(new File(fileName.getPath()));
		rugsackFromInput.populateRugSacks();
		assertEquals(18, rugsackFromInput.getElfGroups().get(0).getPriority());
		assertEquals(52, rugsackFromInput.getElfGroups().get(1).getPriority());
	}
	
	@Test
	void part_two_answer() throws Exception {
		rugsackFromInput.populateRugSacks();
		ArrayList<ElfGroup> elfGroups = rugsackFromInput.getElfGroups();
		int totalPriority = 0;
		for (ElfGroup elfGroup : elfGroups) {
			totalPriority += elfGroup.getPriority();
		}
//		System.out.println(totalPriority);
		assertEquals(2510, totalPriority);
	}
}
