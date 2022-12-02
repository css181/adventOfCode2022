package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtility {

	public static ArrayList<Integer> convertFileToIntList(File file) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	array.add(Integer.valueOf(line));
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return array;
	}
	
	public static ArrayList<String> convertFileToStringArray(File file) {
		ArrayList<String> array = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	array.add(line);
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return array;
	}
	
	public static String convertFileToString(File file) {
		String oneLineInput = "";
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	oneLineInput = line;
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return oneLineInput;
	}

	
	//Used for putting into Array of Array of single character strings
	public static ArrayList<ArrayList<String>> convertFileToStringArrayOfArrays(File file) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
				ArrayList<String> curLine = new ArrayList<String>();
				for(int pos=0; pos<line.length(); pos++) {
					curLine.add(String.valueOf(line.charAt(pos)));
				}
				list.add(curLine);
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static ArrayList<ArrayList<Integer>> convertFileToIntListOfLists(File file) {
		ArrayList<ArrayList<Integer>> listOfLists = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> curList;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	curList = new ArrayList<Integer>();
				for(int x=0; x<line.length(); x++) {
					curList.add(Integer.valueOf(String.valueOf(line.charAt(x))));
				}
				listOfLists.add(curList);
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listOfLists;
	}
	

	public static int[][] convertFileToIntArrayOfArrays(File file) {
		ArrayList<ArrayList<Integer>> listOfLists = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> curList;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	curList = new ArrayList<Integer>();
				for(int x=0; x<line.length(); x++) {
					curList.add(Integer.valueOf(String.valueOf(line.charAt(x))));
				}
				listOfLists.add(curList);
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int[][] intArray = listOfLists.stream().map(  u  ->  u.stream().mapToInt(i->i).toArray()  ).toArray(int[][]::new);
		return intArray;
	}

	public static ArrayList<ArrayList<Long>> convertFileToListOfIntList(File file, String seperator) {
		ArrayList<ArrayList<Long>> listOfLists = new ArrayList<ArrayList<Long>>();
		ArrayList<Long> curList;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    curList = new ArrayList<Long>();
		    while ((line = br.readLine()) != null) {
		    	if(line.equals(seperator)) {
		    		listOfLists.add(curList);
		    		curList = new ArrayList<Long>();
		    	} else {
		    		curList.add(Long.valueOf(line));
		    	}
		    }
		    listOfLists.add(curList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listOfLists;
	}

}