package day11;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PartOneTest {

	private MonkeysFromInput monkeysFromInput;
	private static final int divByForGettingBored = 3;
	
	@BeforeEach
	public void setup() {
		monkeysFromInput = new MonkeysFromInput();
	}
	
	@Test
	void getting_input_into_monkeys() throws Exception {
		ArrayList<Monkey> expectedMonkeys = new ArrayList<Monkey>();
		Monkey expected0 = new Monkey(new ArrayList<Long>(Arrays.asList(79l,98l)), new Operation("*", 19), new TestDivBy(23,2,3));
		expectedMonkeys.add(expected0);
		expectedMonkeys.add(new Monkey(new ArrayList<Long>(Arrays.asList(54l,65l,75l,74l)), new Operation("+", 6), new TestDivBy(19,2,0)));
		expectedMonkeys.add(new Monkey(new ArrayList<Long>(Arrays.asList(79l,60l,97l)), new Operation("*", -1), new TestDivBy(13,1,3)));
		expectedMonkeys.add(new Monkey(new ArrayList<Long>(Arrays.asList(74l)), new Operation("+", 3), new TestDivBy(17,0,1)));
		
		
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

		monkeysFromInput.performRound(divByForGettingBored);
		
		assertEquals(new ArrayList<Long>(Arrays.asList(20l,23l,27l,26l)), monkeysFromInput.getMonkeys().get(0).getItemList());
		assertEquals(new ArrayList<Long>(Arrays.asList(2080l,25l,167l,207l,401l,1046l)), monkeysFromInput.getMonkeys().get(1).getItemList());
		assertEquals(new ArrayList<Integer>(), monkeysFromInput.getMonkeys().get(2).getItemList());
		assertEquals(new ArrayList<Integer>(), monkeysFromInput.getMonkeys().get(3).getItemList());
	}
	
	@Test
	void verify_twenty_rounds_of_monkeying() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		monkeysFromInput.setFileToUse(new File(fileName.getPath()));
		monkeysFromInput.populateMonkeys();

		for(int x=0; x<20; x++) {
			monkeysFromInput.performRound(divByForGettingBored);
		}

		assertEquals(new ArrayList<Long>(Arrays.asList(10l, 12l, 14l, 26l, 34l)), monkeysFromInput.getMonkeys().get(0).getItemList());
		assertEquals(new ArrayList<Long>(Arrays.asList(245l, 93l, 53l, 199l, 115l)), monkeysFromInput.getMonkeys().get(1).getItemList());
		assertEquals(new ArrayList<Long>(), monkeysFromInput.getMonkeys().get(2).getItemList());
		assertEquals(new ArrayList<Long>(), monkeysFromInput.getMonkeys().get(3).getItemList());
	}

	@Test
	void verify_monkey_passes_for_twenty_rounds() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		monkeysFromInput.setFileToUse(new File(fileName.getPath()));
		monkeysFromInput.populateMonkeys();

		for(int x=0; x<20; x++) {
			monkeysFromInput.performRound(divByForGettingBored);
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
			monkeysFromInput.performRound(divByForGettingBored);
		}

		assertEquals(10605, monkeysFromInput.getMonkeyBusiness());
	}
	
	@Test
	void partOne_Answer() throws Exception {
		monkeysFromInput.populateMonkeys();
		for(int x=0; x<20; x++) {
			monkeysFromInput.performRound(divByForGettingBored);
		}
		
//		System.out.println(monkeysFromInput.getMonkeyBusiness());
		assertEquals(90882, monkeysFromInput.getMonkeyBusiness());
	}
}
