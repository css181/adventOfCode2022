package day8;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.FileUtility;


public class PartOneTest {

	private TreeGridFromInput treeGridFromInput;
	
	@BeforeEach
	public void setup() {
		treeGridFromInput = new TreeGridFromInput();
	}
	
	@Test 
	void convertFileToArrayTest() {
		ArrayList<ArrayList<Integer>> expected = new ArrayList<ArrayList<Integer>>();
		expected.add(new ArrayList<Integer>(Arrays.asList(3,0,3,7,3)));
		expected.add(new ArrayList<Integer>(Arrays.asList(2,5,5,1,2)));
		expected.add(new ArrayList<Integer>(Arrays.asList(6,5,3,3,2)));
		expected.add(new ArrayList<Integer>(Arrays.asList(3,3,5,4,9)));
		expected.add(new ArrayList<Integer>(Arrays.asList(3,5,3,9,0)));
		
		URL fileName = getClass().getResource("SampleInput.txt");
		ArrayList<ArrayList<Integer>> actual = FileUtility.convertFileToIntListOfLists(new File(fileName.getPath()));
		assertEquals(expected, actual);
	}
	
	@Test
	void getting_input_into_Trees() throws Exception {
		ArrayList<ArrayList<Tree>> expected = new ArrayList<ArrayList<Tree>>();
		expected.add(new ArrayList<Tree>(Arrays.asList(new Tree(3), new Tree(0), new Tree(3), new Tree(7), new Tree(3))));
		expected.add(new ArrayList<Tree>(Arrays.asList(new Tree(2), new Tree(5), new Tree(5), new Tree(1), new Tree(2))));
		expected.add(new ArrayList<Tree>(Arrays.asList(new Tree(6), new Tree(5), new Tree(3), new Tree(3), new Tree(2))));
		expected.add(new ArrayList<Tree>(Arrays.asList(new Tree(3), new Tree(3), new Tree(5), new Tree(4), new Tree(9))));
		expected.add(new ArrayList<Tree>(Arrays.asList(new Tree(3), new Tree(5), new Tree(3), new Tree(9), new Tree(0))));
		
		URL fileName = getClass().getResource("SampleInput.txt");
		treeGridFromInput.setFileToUse(new File(fileName.getPath()));
		treeGridFromInput.populateTreeGrid();
		assertEquals(expected, treeGridFromInput.getTreeGrid());
	}
	
	@Test
	void verify_isVisible_assignments() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		treeGridFromInput.setFileToUse(new File(fileName.getPath()));
		treeGridFromInput.populateTreeGrid();
		treeGridFromInput.assignVisibilities();
		
		ArrayList<ArrayList<Tree>> grid = treeGridFromInput.getTreeGrid();
		for(int x=0; x<grid.size(); x++)
			assertEquals(true, grid.get(0).get(x).isVisible());
		for(int x=0; x<grid.size(); x++)
			assertEquals(true, grid.get(grid.size()-1).get(x).isVisible());
		for(int x=1; x<grid.size(); x++)
			assertEquals(true, grid.get(x).get(0).isVisible());
		for(int x=1; x<grid.size(); x++)
			assertEquals(true, grid.get(x).get(grid.get(x).size()-1).isVisible());
		assertEquals(true, grid.get(1).get(1).isVisible());
		assertEquals(true, grid.get(1).get(2).isVisible());
		assertEquals(false, grid.get(1).get(3).isVisible());

		assertEquals(true, grid.get(2).get(1).isVisible());
		assertEquals(false, grid.get(2).get(2).isVisible());
		assertEquals(true, grid.get(2).get(3).isVisible());
		
		assertEquals(false, grid.get(3).get(1).isVisible());
		assertEquals(true, grid.get(3).get(2).isVisible());
		assertEquals(false, grid.get(3).get(3).isVisible());
	}

	@Test
	void verify_correct_Num_of_visible_trees() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		treeGridFromInput.setFileToUse(new File(fileName.getPath()));
		treeGridFromInput.populateTreeGrid();
		treeGridFromInput.assignVisibilities();
		
		assertEquals(21, treeGridFromInput.getNumOfVisibleTrees());
	}
	
	@Test
	void partOne_Answer() throws Exception {
		treeGridFromInput.populateTreeGrid();
		treeGridFromInput.assignVisibilities();
//		System.out.println(treeGridFromInput.getNumOfVisibleTrees());
		assertEquals(1787, treeGridFromInput.getNumOfVisibleTrees());
	}
}
