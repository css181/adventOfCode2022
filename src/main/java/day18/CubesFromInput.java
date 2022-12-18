package day18;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import utilities.FileUtility;

public class CubesFromInput {

	private static File file;
	private ArrayList<Cube> cubes;
	
	public CubesFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		cubes = new ArrayList<Cube>();
	}
	
	protected void setFileToUse(File file) {
		CubesFromInput.file = file;
	}

	public void populateCubes() {
		ArrayList<String> input = FileUtility.convertFileToStringArray(file);
		for (String cubeLine : input) {
			String[] elements = cubeLine.split(",");
			cubes.add(new Cube(Integer.valueOf(elements[0]), Integer.valueOf(elements[1]), Integer.valueOf(elements[2])));
		}
	}

	public ArrayList<Cube> getCubes() {
		return cubes;
	}

	public int getTotalSurfaceAreaOfCubes() {
		int area = 0;
		for(int cubeIndex=0; cubeIndex<cubes.size(); cubeIndex++) {
			Cube curCube = cubes.get(cubeIndex);
			int adjacentSides = numberOfSidesAdjacentToOtherCubes(curCube);
			area+= 6-adjacentSides;
		}
		return area;
	}

	private int numberOfSidesAdjacentToOtherCubes(Cube curCube) {
		int sidesAdjacentToOthers = 0;
		for (Cube cube : cubes) {
			if((curCube.getX()-cube.getX()==1 || cube.getX()-curCube.getX()==1) &&
					curCube.getY()==cube.getY() && curCube.getZ()==cube.getZ()){
				sidesAdjacentToOthers++;
			}
			if((curCube.getY()-cube.getY()==1 || cube.getY()-curCube.getY()==1)  &&
					curCube.getX()==cube.getX() && curCube.getZ()==cube.getZ()){
				sidesAdjacentToOthers++;
			}
			if((curCube.getZ()-cube.getZ()==1 || cube.getZ()-curCube.getZ()==1)  &&
					curCube.getY()==cube.getY() && curCube.getX()==cube.getX()){
				sidesAdjacentToOthers++;
			}
		}
		return sidesAdjacentToOthers;
	}

}
