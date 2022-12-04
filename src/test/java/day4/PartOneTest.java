package day4;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.FileUtility;


public class PartOneTest {

	private ElfPairsFromInput elfPairsFromInput;
	
	@BeforeEach
	public void setup() {
		elfPairsFromInput = new ElfPairsFromInput();
	}
	
	@Test 
	void convertFileToArrayTest() {
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("2-4,6-8");
		expected.add("2-3,4-5");
		expected.add("5-7,7-9");
		expected.add("2-8,3-7");
		expected.add("6-6,4-6");
		expected.add("2-6,4-8");
		
		URL fileName = getClass().getResource("SampleInput.txt");
		ArrayList<String> actual = FileUtility.convertFileToStringArray(new File(fileName.getPath()));
		assertEquals(expected, actual);
	}
	
	@Test
	void getting_input_into_elfPairs() throws Exception {
		ArrayList<ElfPair> expectedElfPairs = new ArrayList<ElfPair>();
		expectedElfPairs.add(new ElfPair(new Elf("2-4"), new Elf("6-8")));
		expectedElfPairs.add(new ElfPair(new Elf("2-3"), new Elf("4-5")));
		expectedElfPairs.add(new ElfPair(new Elf("5-7"), new Elf("7-9")));
		expectedElfPairs.add(new ElfPair(new Elf("2-8"), new Elf("3-7")));
		expectedElfPairs.add(new ElfPair(new Elf("6-6"), new Elf("4-6")));
		expectedElfPairs.add(new ElfPair(new Elf("2-6"), new Elf("4-8")));
		
		URL fileName = getClass().getResource("SampleInput.txt");
		elfPairsFromInput.setFileToUse(new File(fileName.getPath()));
		elfPairsFromInput.populateElfPairs();
		assertEquals(expectedElfPairs, elfPairsFromInput.getElfPairs());
	}

	@Test
	//SmallerElf=>Elf with smaller range.  If both same range, then the first input Elf.
	void make_correct_elf_smallerElf_for_each_pair() throws Exception {
		ArrayList<ElfPair> expectedElfPairs = new ArrayList<ElfPair>();
		expectedElfPairs.add(new ElfPair(new Elf("2-4"), new Elf("6-8")));
		expectedElfPairs.add(new ElfPair(new Elf("2-3"), new Elf("4-5")));
		expectedElfPairs.add(new ElfPair(new Elf("5-7"), new Elf("7-9")));
		expectedElfPairs.add(new ElfPair(new Elf("2-8"), new Elf("3-7")));
		expectedElfPairs.add(new ElfPair(new Elf("6-6"), new Elf("4-6")));
		expectedElfPairs.add(new ElfPair(new Elf("2-6"), new Elf("4-8")));
		
		URL fileName = getClass().getResource("SampleInput.txt");
		elfPairsFromInput.setFileToUse(new File(fileName.getPath()));
		elfPairsFromInput.populateElfPairs();
		assertEquals(new Elf("2-4"), elfPairsFromInput.getElfPairs().get(0).getSmallerElf());
		assertEquals(new Elf("2-3"), elfPairsFromInput.getElfPairs().get(1).getSmallerElf());
		assertEquals(new Elf("5-7"), elfPairsFromInput.getElfPairs().get(2).getSmallerElf());
		assertEquals(new Elf("3-7"), elfPairsFromInput.getElfPairs().get(3).getSmallerElf());
		assertEquals(new Elf("6-6"), elfPairsFromInput.getElfPairs().get(4).getSmallerElf());
		assertEquals(new Elf("2-6"), elfPairsFromInput.getElfPairs().get(5).getSmallerElf());
	}
	
	@Test
	void verify_isSmallerContainedInLarger() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		elfPairsFromInput.setFileToUse(new File(fileName.getPath()));
		elfPairsFromInput.populateElfPairs();
		assertEquals(false, elfPairsFromInput.getElfPairs().get(0).isSmallerContainedInLarger());
		assertEquals(false, elfPairsFromInput.getElfPairs().get(1).isSmallerContainedInLarger());
		assertEquals(false, elfPairsFromInput.getElfPairs().get(2).isSmallerContainedInLarger());
		assertEquals(true, elfPairsFromInput.getElfPairs().get(3).isSmallerContainedInLarger());
		assertEquals(true, elfPairsFromInput.getElfPairs().get(4).isSmallerContainedInLarger());
		assertEquals(false, elfPairsFromInput.getElfPairs().get(5).isSmallerContainedInLarger());
	}
	
	@Test
	void verify_number_of_totalContainedPairs_in_elfPairs() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		elfPairsFromInput.setFileToUse(new File(fileName.getPath()));
		elfPairsFromInput.populateElfPairs();
		assertEquals(2, elfPairsFromInput.getTotalContainedPairs());
	}
	
	@Test
	void partOne_Answer() throws Exception {
		elfPairsFromInput.populateElfPairs();
//		System.out.println(elfPairsFromInput.getTotalContainedPairs());
		assertEquals(500, elfPairsFromInput.getTotalContainedPairs());
	}
}
