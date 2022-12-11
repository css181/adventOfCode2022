package day11;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PartTwoTest {

	private MonkeysFromInput monkeysFromInput;
	private static final int divByForGettingBored = 1;
	
	@BeforeEach
	public void setup() {
		monkeysFromInput = new MonkeysFromInput();
	}
	
	@Test
	void verify_monkey_passes_for_one_round() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		monkeysFromInput.setFileToUse(new File(fileName.getPath()));
		monkeysFromInput.populateMonkeys();

		monkeysFromInput.performRound(divByForGettingBored);

		assertEquals(2, monkeysFromInput.getMonkeys().get(0).getNumOfPasses());
		assertEquals(4, monkeysFromInput.getMonkeys().get(1).getNumOfPasses());
		assertEquals(3, monkeysFromInput.getMonkeys().get(2).getNumOfPasses());
		assertEquals(6, monkeysFromInput.getMonkeys().get(3).getNumOfPasses());
	}
	
	
	@Test
	void verify_monkey_passes_for_twenty_rounds() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		monkeysFromInput.setFileToUse(new File(fileName.getPath()));
		monkeysFromInput.populateMonkeys();

		for(int x=0; x<20; x++) {
			monkeysFromInput.performRound(divByForGettingBored);
		}
		
		assertEquals(99, monkeysFromInput.getMonkeys().get(0).getNumOfPasses());
		assertEquals(97, monkeysFromInput.getMonkeys().get(1).getNumOfPasses());
		assertEquals(8, monkeysFromInput.getMonkeys().get(2).getNumOfPasses());
		assertEquals(103, monkeysFromInput.getMonkeys().get(3).getNumOfPasses());
	}
	
	@Test
	void verify_monkey_passes_for_1000_rounds() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		monkeysFromInput.setFileToUse(new File(fileName.getPath()));
		monkeysFromInput.populateMonkeys();

		for(int x=0; x<1000; x++) {
			monkeysFromInput.performRound(divByForGettingBored);
		}
		
		assertEquals(5204, monkeysFromInput.getMonkeys().get(0).getNumOfPasses());
		assertEquals(4792, monkeysFromInput.getMonkeys().get(1).getNumOfPasses());
		assertEquals(199, monkeysFromInput.getMonkeys().get(2).getNumOfPasses());
		assertEquals(5192, monkeysFromInput.getMonkeys().get(3).getNumOfPasses());
	}
	
	
	@Test
	void verify_sample_monkeyBusiness_score() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		monkeysFromInput.setFileToUse(new File(fileName.getPath()));
		monkeysFromInput.populateMonkeys();

		for(int x=0; x<10000; x++) {
			monkeysFromInput.performRound(divByForGettingBored);
		}

		assertEquals(2713310158d, monkeysFromInput.getMonkeyBusiness());
	}
	
	@Test
	void partTwo_Answer() throws Exception {
		monkeysFromInput.populateMonkeys();
		for(int x=0; x<10000; x++) {
			monkeysFromInput.performRound(divByForGettingBored);
		}
		
//		System.out.println(monkeysFromInput.getMonkeyBusiness());
		assertEquals(30893109657d, monkeysFromInput.getMonkeyBusiness());
	}
}
