package day8;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import utilities.FileUtility;

public class TreeGridFromInput {

	private static File file;
	private ArrayList<ArrayList<Tree>> treeGrid;
	
	public TreeGridFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		treeGrid = new ArrayList<ArrayList<Tree>>();
	}
	
	protected void setFileToUse(File file) {
		TreeGridFromInput.file = file;
	}

	public void populateTreeGrid() {
		ArrayList<ArrayList<Integer>> input = FileUtility.convertFileToIntListOfLists(file);
		for (ArrayList<Integer> treeRow : input) {
			ArrayList<Tree> curTreeRow = new ArrayList<Tree>();
			for (int tree : treeRow) {
				curTreeRow.add(new Tree(tree));
			}
			treeGrid.add(curTreeRow);
		}
	}

	protected ArrayList<ArrayList<Tree>> getTreeGrid() {
		return treeGrid;
	}

	//TODO: Make more efficient later
	public void assignVisibilities() {
		//Edges are always visible
		//Trees are all visible by default, update them to false if needed
		for(int row=1; row<treeGrid.size()-1; row++) {
			for(int col=1; col<treeGrid.get(row).size()-1; col++) {
				Tree curTree = treeGrid.get(row).get(col);
				if(!isVisibleVerticle(curTree.getHeight(), col, row) &&
						!isVisibleHorizontal(curTree.getHeight(), row, col)) {
					curTree.setIsVisible(false);
				}
			}
		}
	}

	private boolean isVisibleHorizontal(int height, int row, int col) {
		//Look Left
		boolean leftVis = true;
		for(int lookCol=0; lookCol<col; lookCol++) {
			int lookTreeHeight = treeGrid.get(row).get(lookCol).getHeight();
			if(lookTreeHeight >= height) {
				leftVis = false;
			}
		}
		//Look Right
		boolean rightVis = true;
		for(int lookCol=col+1; lookCol<treeGrid.get(row).size(); lookCol++) {
			int lookTreeHeight = treeGrid.get(row).get(lookCol).getHeight();
			if(lookTreeHeight >= height) {
				rightVis = false;
			}
		}
		return leftVis || rightVis;
	}

	private boolean isVisibleVerticle(int height, int col, int row) {
		//Look Up
		boolean upVis = true;
		for(int lookRow=0; lookRow<row; lookRow++) {
			int lookTreeHeight = treeGrid.get(lookRow).get(col).getHeight();
			if(lookTreeHeight >= height) {
				upVis = false;
			}
		}
		//Look Down
		boolean downVis = true;
		for(int lookRow=row+1; lookRow<treeGrid.size(); lookRow++) {
			int lookTreeHeight = treeGrid.get(lookRow).get(col).getHeight();
			if(lookTreeHeight >= height) {
				downVis = false;
			}
		}
		return upVis || downVis;
	}

	public int getNumOfVisibleTrees() {
		int total = 0;
		for(int row=0; row<treeGrid.size(); row++) {
			for(int col=0; col<treeGrid.get(row).size(); col++) {
				if(treeGrid.get(row).get(col).isVisible()) {
					total++;
				}
			}
		}
		return total;
	}

	public long getHighestScenicScore() {
		long highest = 0l;
		for(int row=0; row<treeGrid.size(); row++) {
			for(int col=0; col<treeGrid.get(row).size(); col++) {
				if(treeGrid.get(row).get(col).getScenicScore() > highest) {
					highest=treeGrid.get(row).get(col).getScenicScore();
				}
			}
		}
		return highest;
	}

	public void assignScenicScores() {
		for(int row=1; row<treeGrid.size()-1; row++) {
			for(int col=1; col<treeGrid.get(row).size()-1; col++) {
				Tree curTree = treeGrid.get(row).get(col);
				curTree.setScenicScore(
						treesVisLeft(curTree.getHeight(), row, col) * 
						treesVisRight(curTree.getHeight(), row, col) * 
						treesVisUp(curTree.getHeight(), row, col) * 
						treesVisDown(curTree.getHeight(), row, col)
				);
			}
		}
	}

	private int treesVisLeft(int height, int row, int col) {
		int canSee=0;
		for(int lookCol=col-1; lookCol>=0; lookCol--) {
			canSee++;
			if(treeGrid.get(row).get(lookCol).getHeight() >= height) {
				break;
			}
		}
		return canSee;
	}
	private int treesVisRight(int height, int row, int col) {
		int canSee=0;
		for(int lookCol=col+1; lookCol<treeGrid.get(row).size(); lookCol++) {
			canSee++;
			if(treeGrid.get(row).get(lookCol).getHeight() >= height) {
				break;
			}
		}
		return canSee;
	}
	private int treesVisUp(int height, int row, int col) {
		int canSee=0;
		for(int lookRow=row-1; lookRow>=0; lookRow--) {
			canSee++;
			if(treeGrid.get(lookRow).get(col).getHeight() >= height) {
				break;
			}
		}
		return canSee;
	}
	private int treesVisDown(int height, int row, int col) {
		int canSee=0;
		for(int lookRow=row+1; lookRow<treeGrid.size(); lookRow++) {
			canSee++;
			if(treeGrid.get(lookRow).get(col).getHeight() >= height) {
				break;
			}
		}
		return canSee;
	}
}
