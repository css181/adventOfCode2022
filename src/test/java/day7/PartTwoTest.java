package day7;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PartTwoTest {

	private FileSystemFromInput fileSystemFromInput;
	
	@BeforeEach
	public void setup() {
		fileSystemFromInput = new FileSystemFromInput();
	}
	
	@AfterEach
	public void tearDown() {
		FileSystemFromInput.setCurDir(null);
	}
	
	@Test
	void verify_root_dir_size_of_sample() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		fileSystemFromInput.setFileToUse(new File(fileName.getPath()));
		fileSystemFromInput.populateInstructions();
		fileSystemFromInput.processInstructionsToBuildDirTree();
		assertEquals(48381165l, fileSystemFromInput.getRootDir().getTotalSize());
	}
	
	@Test
	void verify_size_of_actual_input_root() throws Exception {
		fileSystemFromInput.populateInstructions();
		fileSystemFromInput.processInstructionsToBuildDirTree();
//		System.out.println(fileSystemFromInput.getRootDir().getTotalSize());
		assertEquals(50822529l, fileSystemFromInput.getRootDir().getTotalSize());
	}
	
	@Test
	void partTwo_answer() throws Exception {
		fileSystemFromInput.populateInstructions();
		fileSystemFromInput.processInstructionsToBuildDirTree();
		//Get the list of all dirs, sort them, then find the one closest to, but above:
			//30000000-(70000000-50822529l)
		ArrayList<Directory> allDirs = new ArrayList<Directory>();
		allDirs = fileSystemFromInput.getListOfAllDirs();
		long closest = Integer.MAX_VALUE;
		long target = 30000000-(70000000-50822529l);
		for (Directory dir : allDirs) {
			if(dir.getTotalSize() > target && dir.getTotalSize() < closest) {
				closest = dir.getTotalSize();
			}
		}
//		System.out.println(closest);
		assertEquals(13210366, closest);
	}
}

