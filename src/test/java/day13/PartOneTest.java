package day13;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PartOneTest {

	private SignalPairsFromInput signalPairsFromInput;
	
	@BeforeEach
	public void setup() {
		signalPairsFromInput = new SignalPairsFromInput();
	}
	
	@Test
	void getting_input_into_signalPairs() throws Exception {
		ArrayList<SignalPair> expectedSignalPairs = new ArrayList<SignalPair>();
		Signal left = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(1), new SignalElement(1), new SignalElement(3), new SignalElement(1), new SignalElement(1))));
		Signal right = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(1), new SignalElement(1), new SignalElement(5), new SignalElement(1), new SignalElement(1))));
		expectedSignalPairs.add(new SignalPair(left, right));
		
		left = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(1)))), new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(2),new SignalElement(3),new SignalElement(4)))))));
		right = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(1)))), new SignalElement(4))));
		expectedSignalPairs.add(new SignalPair(left, right));
		
		left = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(9))));
		right = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(8),new SignalElement(7),new SignalElement(6)))))));
		expectedSignalPairs.add(new SignalPair(left, right));

		left = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(4),new SignalElement(4)))), new SignalElement(4), new SignalElement(4))));
		right = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(4),new SignalElement(4)))), new SignalElement(4), new SignalElement(4), new SignalElement(4))));
		expectedSignalPairs.add(new SignalPair(left, right));

		left = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(7), new SignalElement(7), new SignalElement(7), new SignalElement(7))));
		right = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(7), new SignalElement(7), new SignalElement(7))));
		expectedSignalPairs.add(new SignalPair(left, right));

		left = new Signal(new ArrayList<SignalElement>(Arrays.asList()));
		right = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(3))));
		expectedSignalPairs.add(new SignalPair(left, right));

		left = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(new ArrayList<SignalElement>(Arrays.asList()))))))));
		right = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(new ArrayList<SignalElement>(Arrays.asList())))));
		expectedSignalPairs.add(new SignalPair(left, right));

		SignalElement leftInner = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(5), new SignalElement(6), new SignalElement(7))));
		SignalElement leftPart1 = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(4), leftInner)));
		SignalElement leftPart2 = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(3), leftPart1)));
		SignalElement leftPart3 = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(2), leftPart2)));
		left = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(1), leftPart3, new SignalElement(8), new SignalElement(9))));
		SignalElement rightInner = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(5), new SignalElement(6), new SignalElement(0))));
		SignalElement rightPart1 = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(4), rightInner)));
		SignalElement rightPart2 = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(3), rightPart1)));
		SignalElement rightPart3 = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(2), rightPart2)));
		right = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(1), rightPart3, new SignalElement(8), new SignalElement(9))));
		expectedSignalPairs.add(new SignalPair(left, right));
		
		URL fileName = getClass().getResource("SampleInput.txt");
		signalPairsFromInput.setFileToUse(new File(fileName.getPath()));
		signalPairsFromInput.populateSignalPairs();
		for (int x=0; x<expectedSignalPairs.size(); x++) {
			SignalPair expectedPair = expectedSignalPairs.get(x);
			assertEquals(expectedPair, signalPairsFromInput.getSignalPairs().get(x));
		}
	}

	@Test
	void getting_input_into_signalPairs2() throws Exception {
		ArrayList<SignalPair> expectedSignalPairs = new ArrayList<SignalPair>();

		SignalElement oneList = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(1))));
		SignalElement zeroList = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(0))));
		SignalElement innerList = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(zeroList, new SignalElement(5))));
		Signal left = new Signal(new ArrayList<SignalElement>(Arrays.asList(oneList, innerList)));
		SignalElement tenList = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(10))));
		Signal right = new Signal(new ArrayList<SignalElement>(Arrays.asList(tenList, innerList)));
		expectedSignalPairs.add(new SignalPair(left, right));
		
		URL fileName = getClass().getResource("Sample2.txt");
		signalPairsFromInput.setFileToUse(new File(fileName.getPath()));
		signalPairsFromInput.populateSignalPairs();
		for (int x=0; x<expectedSignalPairs.size(); x++) {
			SignalPair expectedPair = expectedSignalPairs.get(x);
			assertEquals(expectedPair, signalPairsFromInput.getSignalPairs().get(x));
		}
	}

	@Test
	void if_all_elements_are_same_numbers_result_is_true_if_left_has_less_or_same_elements() throws Exception {
		Signal left = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(7), new SignalElement(7), new SignalElement(7))));
		Signal right = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(7), new SignalElement(7), new SignalElement(7), new SignalElement(7))));
		assertEquals(true, signalPairsFromInput.isSignalInRightOrder(new SignalPair(left, right)));
		
		left = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(7), new SignalElement(7), new SignalElement(7))));
		right = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(7), new SignalElement(7), new SignalElement(7))));
		assertEquals(true, signalPairsFromInput.isSignalInRightOrder(new SignalPair(left, right)));
	}
	@Test
	void if_all_elements_are_same_numbers_result_is_false_if_left_has_more_elements() throws Exception {
		Signal left = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(7), new SignalElement(7), new SignalElement(7), new SignalElement(7))));
		Signal right = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(7), new SignalElement(7), new SignalElement(7))));
		assertEquals(false, signalPairsFromInput.isSignalInRightOrder(new SignalPair(left, right)));
	}
	@Test
	void if_all_elements_are_different_numbers_result_is_true_if_left_has_a_lower_number() throws Exception {
		Signal left = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(7), new SignalElement(7), new SignalElement(6), new SignalElement(7))));
		Signal right = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(7), new SignalElement(7), new SignalElement(7))));
		assertEquals(true, signalPairsFromInput.isSignalInRightOrder(new SignalPair(left, right)));
	}
	@Test
	void if_all_elements_are_different_numbers_result_is_false_if_left_has_a_higher_number() throws Exception {
		Signal left = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(7), new SignalElement(7), new SignalElement(8), new SignalElement(7))));
		Signal right = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(7), new SignalElement(7), new SignalElement(7))));
		assertEquals(false, signalPairsFromInput.isSignalInRightOrder(new SignalPair(left, right)));
	}

	@Test
	void if_all_elements_are_same_lists_result_is_true_if_left_has_less_or_same_lists() throws Exception {
		SignalElement element = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(4),new SignalElement(4))));
		Signal left = new Signal(new ArrayList<SignalElement>(Arrays.asList(element, element)));
		Signal right = new Signal(new ArrayList<SignalElement>(Arrays.asList(element, element, element)));
		assertEquals(true, signalPairsFromInput.isSignalInRightOrder(new SignalPair(left, right)));
	
		left = new Signal(new ArrayList<SignalElement>(Arrays.asList(element, element)));
		right = new Signal(new ArrayList<SignalElement>(Arrays.asList(element, element)));
		assertEquals(true, signalPairsFromInput.isSignalInRightOrder(new SignalPair(left, right)));
	}
	@Test
	void if_all_elements_are_same_lists_result_is_false_if_left_has_more_lists() throws Exception {
		SignalElement element = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(4),new SignalElement(4))));
		Signal left = new Signal(new ArrayList<SignalElement>(Arrays.asList(element, element, element)));
		Signal right = new Signal(new ArrayList<SignalElement>(Arrays.asList(element, element)));
		assertEquals(false, signalPairsFromInput.isSignalInRightOrder(new SignalPair(left, right)));
	}
	@Test
	void if_all_elements_are_different_lists_result_is_true_if_left_has_a_lower_number_in_list() throws Exception {
		SignalElement element = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(4),new SignalElement(4))));
		SignalElement lowerElement = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(4),new SignalElement(3))));
		Signal left = new Signal(new ArrayList<SignalElement>(Arrays.asList(element, lowerElement, element)));
		Signal right = new Signal(new ArrayList<SignalElement>(Arrays.asList(element, element)));
		assertEquals(true, signalPairsFromInput.isSignalInRightOrder(new SignalPair(left, right)));
	}
	@Test
	void if_all_elements_are_different_lists_result_is_false_if_left_has_a_higher_number_in_list() throws Exception {
		SignalElement element = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(4),new SignalElement(4))));
		SignalElement higherElement = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(4),new SignalElement(5))));
		Signal left = new Signal(new ArrayList<SignalElement>(Arrays.asList(element, higherElement, element)));
		Signal right = new Signal(new ArrayList<SignalElement>(Arrays.asList(element, element)));
		assertEquals(false, signalPairsFromInput.isSignalInRightOrder(new SignalPair(left, right)));
	}
	
	@Test
	void compare_number_to_list_result_is_true_if_number_is_less_than_or_equal_to_first_element_in_list() throws Exception {
		Signal left = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(1))));
		SignalElement element = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(4),new SignalElement(4))));
		Signal right = new Signal(new ArrayList<SignalElement>(Arrays.asList(element, element)));
		assertEquals(true, signalPairsFromInput.isSignalInRightOrder(new SignalPair(left, right)));
		
		left = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(4))));
		element = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(4),new SignalElement(4))));
		right = new Signal(new ArrayList<SignalElement>(Arrays.asList(element, element)));
		assertEquals(true, signalPairsFromInput.isSignalInRightOrder(new SignalPair(left, right)));
	}
	@Test
	void compare_number_to_list_result_is_false_if_number_is_greater_than_or_equal_to_first_element_in_list() throws Exception {
		Signal left = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(5))));
		SignalElement element = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(4),new SignalElement(4))));
		Signal right = new Signal(new ArrayList<SignalElement>(Arrays.asList(element, element)));
		assertEquals(false, signalPairsFromInput.isSignalInRightOrder(new SignalPair(left, right)));
	}
	
	@Test
	void compare_is_true_if_left_has_empty_list() throws Exception {
		Signal left = new Signal(new ArrayList<SignalElement>(Arrays.asList()));
		Signal right = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(5))));
		assertEquals(true, signalPairsFromInput.isSignalInRightOrder(new SignalPair(left, right)));
		
		left = new Signal(new ArrayList<SignalElement>(Arrays.asList()));
		SignalElement element = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(4),new SignalElement(4))));
		right = new Signal(new ArrayList<SignalElement>(Arrays.asList(element)));
		assertEquals(true, signalPairsFromInput.isSignalInRightOrder(new SignalPair(left, right)));
	}
	@Test
	void compare_is_false_if_right_has_empty_list() throws Exception {
		Signal right = new Signal(new ArrayList<SignalElement>(Arrays.asList()));
		Signal left = new Signal(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(5))));
		assertEquals(false, signalPairsFromInput.isSignalInRightOrder(new SignalPair(left, right)));
		
		right = new Signal(new ArrayList<SignalElement>(Arrays.asList()));
		SignalElement element = new SignalElement(new ArrayList<SignalElement>(Arrays.asList(new SignalElement(4),new SignalElement(4))));
		left = new Signal(new ArrayList<SignalElement>(Arrays.asList(element)));
		assertEquals(false, signalPairsFromInput.isSignalInRightOrder(new SignalPair(left, right)));
	}
	
	
	@Test
	void verify_sample_indexes_in_right_order_are_1_2_4_and_6() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		signalPairsFromInput.setFileToUse(new File(fileName.getPath()));
		signalPairsFromInput.populateSignalPairs();
		
		//Note: Indexes for AoC start at 1, so we need to subtract 1 when we do a .get()
		ArrayList<SignalPair> pairs = signalPairsFromInput.getSignalPairs();
		assertEquals(true, signalPairsFromInput.isSignalInRightOrder(pairs.get(0)));
		assertEquals(true, signalPairsFromInput.isSignalInRightOrder(pairs.get(1)));
		assertEquals(false, signalPairsFromInput.isSignalInRightOrder(pairs.get(2)));
		assertEquals(true, signalPairsFromInput.isSignalInRightOrder(pairs.get(3)));
		assertEquals(false, signalPairsFromInput.isSignalInRightOrder(pairs.get(4)));
		assertEquals(true, signalPairsFromInput.isSignalInRightOrder(pairs.get(5)));
		assertEquals(false, signalPairsFromInput.isSignalInRightOrder(pairs.get(6)));
		assertEquals(false, signalPairsFromInput.isSignalInRightOrder(pairs.get(7)));
	}
	
	@Test
	void verify_sumOfIndexesInCorrectOrder_is_13() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		signalPairsFromInput.setFileToUse(new File(fileName.getPath()));
		signalPairsFromInput.populateSignalPairs();
		
		assertEquals(13, signalPairsFromInput.getSumOfIndexesInCorrectOrder());
//		System.out.println("Answer: " + signalPairsFromInput.getSumOfIndexesInCorrectOrder());
	}
	
	@Test
	void partOne_Answer() throws Exception {
		signalPairsFromInput.populateSignalPairs();

		System.out.println("Answer: " + signalPairsFromInput.getSumOfIndexesInCorrectOrder());
	}
}
