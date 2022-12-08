package day8;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.FileUtility;


public class PartTwoTest {

	private TreeGridFromInput treeGridFromInput;
	
	@BeforeEach
	public void setup() {
		treeGridFromInput = new TreeGridFromInput();
	}
	
	@Test
	void verify_scenicScore_assignments() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		treeGridFromInput.setFileToUse(new File(fileName.getPath()));
		treeGridFromInput.populateTreeGrid();
		treeGridFromInput.assignScenicScores();
		
		ArrayList<ArrayList<Tree>> grid = treeGridFromInput.getTreeGrid();
		for(int x=0; x<grid.size(); x++)
			assertEquals(0, grid.get(0).get(x).getScenicScore());
		for(int x=0; x<grid.size(); x++)
			assertEquals(0, grid.get(grid.size()-1).get(x).getScenicScore());
		for(int x=1; x<grid.size(); x++)
			assertEquals(0, grid.get(x).get(0).getScenicScore());
		for(int x=1; x<grid.size(); x++)
			assertEquals(0, grid.get(x).get(grid.get(x).size()-1).getScenicScore());
		
		assertEquals(1, grid.get(1).get(1).getScenicScore());
		assertEquals(4, grid.get(1).get(2).getScenicScore());
		assertEquals(1, grid.get(1).get(3).getScenicScore());

		assertEquals(6, grid.get(2).get(1).getScenicScore());
		assertEquals(1, grid.get(2).get(2).getScenicScore());
		assertEquals(2, grid.get(2).get(3).getScenicScore());
		
		assertEquals(1, grid.get(3).get(1).getScenicScore());
		assertEquals(8, grid.get(3).get(2).getScenicScore());
		assertEquals(3, grid.get(3).get(3).getScenicScore());
	}
	
	@Test
	void verify_highest_scenic_from_sample_is_8() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		treeGridFromInput.setFileToUse(new File(fileName.getPath()));
		treeGridFromInput.populateTreeGrid();
		treeGridFromInput.assignScenicScores();
		
		assertEquals(8, treeGridFromInput.getHighestScenicScore());
	}

	@Test
	void partOne_Answer() throws Exception {
		treeGridFromInput.populateTreeGrid();
		treeGridFromInput.assignScenicScores();
//		System.out.println(treeGridFromInput.getHighestScenicScore());
		assertEquals(440640, treeGridFromInput.getHighestScenicScore());
	}
}
