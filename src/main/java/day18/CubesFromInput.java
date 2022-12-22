package day18;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

import utilities.FileUtility;

public class CubesFromInput {

	private static File file;
	private ArrayList<Cube> cubes;
	private ArrayList<Cube> airCubes;
	private ArrayList<Cube> airPocketCubes;
	private HashSet<Cube> outsideCubes;
	private HashSet<Cube> curVisitedSet;
	private int highX=0;
	private int highY=0;
	private int highZ=0;
	
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
			int curX = Integer.valueOf(elements[0]);
			int curY = Integer.valueOf(elements[1]);
			int curZ = Integer.valueOf(elements[2]);
			cubes.add(new Cube(curX, curY, curZ));
			if(curX>highX) { highX = curX; }
			if(curY>highY) { highY = curY; }
			if(curZ>highZ) { highZ = curZ; }
		}
	}

	public ArrayList<Cube> getCubes() {
		return cubes;
	}

	public int getTotalSurfaceAreaOfCubes() {
		int area = 0;
		for(int cubeIndex=0; cubeIndex<cubes.size(); cubeIndex++) {
			Cube curCube = cubes.get(cubeIndex);
			int adjacentSides = numberOfSidesAdjacentToOtherCubes(curCube, cubes);
			area+= 6-adjacentSides;
		}
		return area;
	}
	
	public int getAirPocketSurfaceArea() {
		int area = 0;
		for(int cubeIndex=0; cubeIndex<airPocketCubes.size(); cubeIndex++) {
			Cube curAirCube = airPocketCubes.get(cubeIndex);
			int adjacentSides = numberOfSidesAdjacentToOtherCubes(curAirCube, airPocketCubes);
			area+= 6-adjacentSides;
		}
		return area;
	}

	private int numberOfSidesAdjacentToOtherCubes(Cube curCube, ArrayList<Cube> cubeList) {
		int sidesAdjacentToOthers = 0;
		for (Cube cube : cubeList) {
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
	

	public void generateAirCubes() {
		airCubes = new ArrayList<Cube>();
		for(int x=1; x<=highX-1; x++) {
			for(int y=1; y<=highY-1; y++) {
				for(int z=1; z<=highZ-1; z++) {
					Cube curCube = new Cube(x,y,z);
					if(	!cubes.contains(curCube)) {
//						System.out.println("AIr Cube:(" + x + "," + y + "," + z + ")");
						airCubes.add(new Cube(x,y,z));
					}
				}
			}
		}
	}

	protected ArrayList<Cube> getAirPocketCubes() {
		return airPocketCubes;
	}
	
	public void calculateAirPocketCubes() {
		generateAirCubes();
		generateOutsideCubeSet();
		airPocketCubes = new ArrayList<Cube>();
//		int count = 0;
		for (Cube airCube : airCubes) {
			curVisitedSet = new HashSet<Cube>();
//			System.out.println("Testing air cube: " + count + "/" + airCubes.size());
			if(!canGetToOutside(airCube)) {
//				System.out.println("Air Pocket Cube: " + airCube);
				airPocketCubes.add(airCube);
			}
//			count++;
		}
	}


	private void generateOutsideCubeSet() {
		//Go through every cube from -1 to max+1
		//If it's not a lava or air cube, it's outside.
		outsideCubes = new HashSet<Cube>();
		for(int x=-1; x<=highX+1; x++) {
			for(int y=-1; y<=highY+1; y++) {
				for(int z=-1; z<=highZ+1; z++) {
					Cube curCube = new Cube(x,y,z);
					if(curCube.equals(new Cube(0,1,1))) {
						System.out.println("break");
					}
					if(!cubes.contains(curCube) && !airCubes.contains(curCube)) {
						outsideCubes.add(curCube);
					}
				}
			}
		}
	}

	private boolean canGetToOutside(Cube cube) {
		curVisitedSet.add(cube);
		for (Cube outside : outsideCubes) {
			if(outside.equals(cube)) {
				return true;
			}
		}
		if(cubes.contains(cube)) {
			return false;
		}
		if(canAnySurroundingCubeGetOutside(cube)) {
			outsideCubes.add(cube);
			return true;
		} else {
			return false;
		}
	}

	private boolean canAnySurroundingCubeGetOutside(Cube cube) {
		Cube xMinus1 = new Cube(cube.getX()-1, cube.getY(), cube.getZ());
		Cube xPlus1 = new Cube(cube.getX()+1, cube.getY(), cube.getZ());
		Cube yMinus1 = new Cube(cube.getX(), cube.getY()-1, cube.getZ());
		Cube yPlus1 = new Cube(cube.getX(), cube.getY()+1, cube.getZ());
		Cube zMinus1 = new Cube(cube.getX(), cube.getY(), cube.getZ()-1);
		Cube zPlus1 = new Cube(cube.getX(), cube.getY(), cube.getZ()+1);
		return (!hasAlreadyBeenVisited(xMinus1) && canGetToOutside(xMinus1)) ||
				(!hasAlreadyBeenVisited(xPlus1) && canGetToOutside(xPlus1)) ||
				(!hasAlreadyBeenVisited(yMinus1) && canGetToOutside(yMinus1)) ||
				(!hasAlreadyBeenVisited(yPlus1) && canGetToOutside(yPlus1)) ||
				(!hasAlreadyBeenVisited(zMinus1) && canGetToOutside(zMinus1)) ||
				(!hasAlreadyBeenVisited(zPlus1) && canGetToOutside(zPlus1));
	}
	
	private boolean hasAlreadyBeenVisited(Cube cube) {
		for (Cube visited : curVisitedSet) {
			if(visited.equals(cube)) {
				return true;
			}
		}
		return false;
	}
	
}
