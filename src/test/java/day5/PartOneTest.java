package day5;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.FileUtility;


public class PartOneTest {

	private StacksFromInput stacksFromInput;
	
	@BeforeEach
	public void setup() {
		stacksFromInput = new StacksFromInput();
	}
	
	@Test 
	void convertFileToArrayTest() {
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("    [D]    ");
		expected.add("[N] [C]    ");
		expected.add("[Z] [M] [P]");
		
		URL fileName = getClass().getResource("SampleInput.txt");
		ArrayList<String> actual = FileUtility.convertFileToStringArray(new File(fileName.getPath()));
		assertEquals(expected, actual);
	}
	
	@Test
	void getting_input_into_array_of_stacks() throws Exception {
		ArrayList<Stack<String>> expectedStacks = new ArrayList<Stack<String>>();
		Stack<String> stack1 = new Stack<>();
		stack1.push("Z");
		stack1.push("N");
		expectedStacks.add(stack1);
		Stack<String> stack2 = new Stack<>();
		stack2.push("M");
		stack2.push("C");
		stack2.push("D");
		expectedStacks.add(stack2);
		Stack<String> stack3 = new Stack<>();
		stack3.push("P");
		expectedStacks.add(stack3);
		
		URL fileName = getClass().getResource("SampleInput.txt");
		stacksFromInput.setFileToUse(new File(fileName.getPath()));
		stacksFromInput.populateStacksAndInstructions();
		assertEquals(expectedStacks, stacksFromInput.getStacks());
	}
	
	@Test 
	void convertInstructionsFileToArrayTest() {
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("move 1 from 2 to 1");
		expected.add("move 3 from 1 to 3");
		expected.add("move 2 from 2 to 1");
		expected.add("move 1 from 1 to 2");
		
		URL fileName = getClass().getResource("SampleInstructions.txt");
		stacksFromInput.setInstructionFileToUse(new File(fileName.getPath()));
		stacksFromInput.populateStacksAndInstructions();
		assertEquals(expected, stacksFromInput.getInstructions());
	}

	@Test
	void verify_NumToMove_from_instructions() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		stacksFromInput.setFileToUse(new File(fileName.getPath()));
		URL instructionFileName = getClass().getResource("SampleInstructions.txt");
		stacksFromInput.setInstructionFileToUse(new File(instructionFileName.getPath()));
		stacksFromInput.populateStacksAndInstructions();

		assertEquals(1, stacksFromInput.getNumToMove(stacksFromInput.getInstructions().get(0)));
		assertEquals(3, stacksFromInput.getNumToMove(stacksFromInput.getInstructions().get(1)));
		assertEquals(2, stacksFromInput.getNumToMove(stacksFromInput.getInstructions().get(2)));
		assertEquals(1, stacksFromInput.getNumToMove(stacksFromInput.getInstructions().get(3)));
	}
	
	@Test
	void verify_move_instructions() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		stacksFromInput.setFileToUse(new File(fileName.getPath()));
		URL instructionFileName = getClass().getResource("SampleInstructions.txt");
		stacksFromInput.setInstructionFileToUse(new File(instructionFileName.getPath()));
		stacksFromInput.populateStacksAndInstructions();
		
		stacksFromInput.processInstructions();
		ArrayList<Stack<String>> expectedStacks = new ArrayList<Stack<String>>();
		Stack<String> stack1 = new Stack<>();
		stack1.push("C");
		expectedStacks.add(stack1);
		Stack<String> stack2 = new Stack<>();
		stack2.push("M");
		expectedStacks.add(stack2);
		Stack<String> stack3 = new Stack<>();
		stack3.push("P");
		stack3.push("D");
		stack3.push("N");
		stack3.push("Z");
		expectedStacks.add(stack3);
		assertEquals(expectedStacks, stacksFromInput.getStacks());
	}

	@Test
	void verify_top_in_each_stack() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		stacksFromInput.setFileToUse(new File(fileName.getPath()));
		URL instructionFileName = getClass().getResource("SampleInstructions.txt");
		stacksFromInput.setInstructionFileToUse(new File(instructionFileName.getPath()));
		stacksFromInput.populateStacksAndInstructions();
		stacksFromInput.processInstructions();
		
		String expected = "CMZ";
		assertEquals(expected, stacksFromInput.getTopFromEachStack());
	}
	@Test
	void partOne_Answer() throws Exception {
		stacksFromInput.populateStacksAndInstructions();
		stacksFromInput.processInstructions();
//		System.out.println(stacksFromInput.getTopFromEachStack());
		assertEquals("BWNCQRMDB", stacksFromInput.getTopFromEachStack());
	}
}
