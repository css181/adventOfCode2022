package day5;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.FileUtility;


public class PartTwoTest {

	private StacksFromInput stacksFromInput;
	
	@BeforeEach
	public void setup() {
		stacksFromInput = new StacksFromInput();
	}
	
	@Test
	void verify_move_instructions_9001Crane() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		stacksFromInput.setFileToUse(new File(fileName.getPath()));
		URL instructionFileName = getClass().getResource("SampleInstructions.txt");
		stacksFromInput.setInstructionFileToUse(new File(instructionFileName.getPath()));
		stacksFromInput.populateStacksAndInstructions();
		
		stacksFromInput.processInstructions9001();
		ArrayList<Stack<String>> expectedStacks = new ArrayList<Stack<String>>();
		Stack<String> stack1 = new Stack<>();
		stack1.push("M");
		expectedStacks.add(stack1);
		Stack<String> stack2 = new Stack<>();
		stack2.push("C");
		expectedStacks.add(stack2);
		Stack<String> stack3 = new Stack<>();
		stack3.push("P");
		stack3.push("Z");
		stack3.push("N");
		stack3.push("D");
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
		stacksFromInput.processInstructions9001();
		
		String expected = "MCD";
		assertEquals(expected, stacksFromInput.getTopFromEachStack());
	}
	
	@Test
	void partTwo_Answer() throws Exception {
		stacksFromInput.populateStacksAndInstructions();
		stacksFromInput.processInstructions9001();
//		System.out.println(stacksFromInput.getTopFromEachStack());
		assertEquals("NHWZCBNBF", stacksFromInput.getTopFromEachStack());
	}
}
