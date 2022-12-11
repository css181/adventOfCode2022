package day11;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PartOneTest {

	private MonkeysFromInput monkeysFromInput;
	
	@BeforeEach
	public void setup() {
		monkeysFromInput = new MonkeysFromInput();
	}
	
	@Test
	void getting_input_into_monkeys() throws Exception {
		ArrayList<Monkey> expectedMonkeys = new ArrayList<Monkey>();
		expectedMonkeys.add(new Monkey(new ArrayList<Integer>(Arrays.asList(79,98)), new Operation("*", 19), new TestDivBy(23,2,3)));
		expectedMonkeys.add(new Monkey(new ArrayList<Integer>(Arrays.asList(54,65,75,74)), new Operation("+", 6), new TestDivBy(19,2,0)));
		expectedMonkeys.add(new Monkey(new ArrayList<Integer>(Arrays.asList(79,60,97)), new Operation("*", -1), new TestDivBy(13,1,3)));
		expectedMonkeys.add(new Monkey(new ArrayList<Integer>(Arrays.asList(74)), new Operation("+", 3), new TestDivBy(17,0,1)));
		
		
		URL fileName = getClass().getResource("SampleInput.txt");
		monkeysFromInput.setFileToUse(new File(fileName.getPath()));
		monkeysFromInput.populateMonkeys();
		assertEquals(expectedMonkeys, monkeysFromInput.getMonkeys());
	}
	
	@Test
	void verify_one_round_of_monkeying() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		monkeysFromInput.setFileToUse(new File(fileName.getPath()));
		monkeysFromInput.populateMonkeys();

		monkeysFromInput.performRound();
		
		assertEquals(new ArrayList<Integer>(Arrays.asList(20,23,27,26)), monkeysFromInput.getMonkeys().get(0).getItemList());
		assertEquals(new ArrayList<Integer>(Arrays.asList(2080,25,167,207,401,1046)), monkeysFromInput.getMonkeys().get(1).getItemList());
		assertEquals(new ArrayList<Integer>(), monkeysFromInput.getMonkeys().get(2).getItemList());
		assertEquals(new ArrayList<Integer>(), monkeysFromInput.getMonkeys().get(3).getItemList());
	}
	
	@Test
	void verify_twenty_rounds_of_monkeying() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		monkeysFromInput.setFileToUse(new File(fileName.getPath()));
		monkeysFromInput.populateMonkeys();

		for(int x=0; x<20; x++) {
			monkeysFromInput.performRound();
		}

		assertEquals(new ArrayList<Integer>(Arrays.asList(10, 12, 14, 26, 34)), monkeysFromInput.getMonkeys().get(0).getItemList());
		assertEquals(new ArrayList<Integer>(Arrays.asList(245, 93, 53, 199, 115)), monkeysFromInput.getMonkeys().get(1).getItemList());
		assertEquals(new ArrayList<Integer>(), monkeysFromInput.getMonkeys().get(2).getItemList());
		assertEquals(new ArrayList<Integer>(), monkeysFromInput.getMonkeys().get(3).getItemList());
	}

	@Test
	void verify_monkey_passes_for_twenty_rounds() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		monkeysFromInput.setFileToUse(new File(fileName.getPath()));
		monkeysFromInput.populateMonkeys();

		for(int x=0; x<20; x++) {
			monkeysFromInput.performRound();
		}

		assertEquals(101, monkeysFromInput.getMonkeys().get(0).getNumOfPasses());
		assertEquals(95, monkeysFromInput.getMonkeys().get(1).getNumOfPasses());
		assertEquals(7, monkeysFromInput.getMonkeys().get(2).getNumOfPasses());
		assertEquals(105, monkeysFromInput.getMonkeys().get(3).getNumOfPasses());
	}
	
	@Test
	void verify_sample_monkeyBusiness_score() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		monkeysFromInput.setFileToUse(new File(fileName.getPath()));
		monkeysFromInput.populateMonkeys();

		for(int x=0; x<20; x++) {
			monkeysFromInput.performRound();
		}

		assertEquals(10605, monkeysFromInput.getMonkeyBusiness());
	}
	
	@Test
	void partOne_Answer() throws Exception {
		monkeysFromInput.populateMonkeys();
		for(int x=0; x<20; x++) {
			monkeysFromInput.performRound();
		}
		
//		System.out.println(monkeysFromInput.getMonkeyBusiness());
		assertEquals(90882, monkeysFromInput.getMonkeyBusiness());
	}
}
