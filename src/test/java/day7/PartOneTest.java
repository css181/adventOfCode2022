package day7;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.FileUtility;


public class PartOneTest {

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
	void getting_input_into_instructions_and_results() throws Exception {
		ArrayList<Instruction> expectedInstructions = new ArrayList<Instruction>();
		expectedInstructions.add(new CdInstruction("/"));
		expectedInstructions.add(new LsInstruction(new ArrayList<>(
				Arrays.asList("dir a", "14848514 b.txt", "8504156 c.dat", "dir d"))));
		expectedInstructions.add(new CdInstruction("a"));
		expectedInstructions.add(new LsInstruction(new ArrayList<>(
						Arrays.asList("dir e", "29116 f", "2557 g", "62596 h.lst"))));
		expectedInstructions.add(new CdInstruction("e"));
		expectedInstructions.add(new LsInstruction(new ArrayList<>(
				Arrays.asList("584 i"))));
		expectedInstructions.add(new CdInstruction(".."));
		expectedInstructions.add(new CdInstruction(".."));
		expectedInstructions.add(new CdInstruction("d"));
		expectedInstructions.add(new LsInstruction(new ArrayList<>(
				Arrays.asList("4060174 j", "8033020 d.log", "5626152 d.ext", "7214296 k"))));
		
		URL fileName = getClass().getResource("SampleInput.txt");
		fileSystemFromInput.setFileToUse(new File(fileName.getPath()));
		fileSystemFromInput.populateInstructions();
		assertEquals(expectedInstructions, fileSystemFromInput.getInstructions());
	}

	@Test
	void can_build_system_from_processing_all_instructions() throws Exception {
		MyFile i = new MyFile("i", 584l);
		MyFile f = new MyFile("f", 29116l);
		MyFile g = new MyFile("g", 2557l);
		MyFile h = new MyFile("h.lst", 62596l);
		MyFile b = new MyFile("b.txt", 14848514l);
		MyFile c = new MyFile("c.dat", 8504156l);
		MyFile j = new MyFile("j", 4060174l);
		MyFile dlog = new MyFile("d.log", 8033020l);
		MyFile dexe = new MyFile("d.ext", 5626152l);
		MyFile k = new MyFile("k", 7214296l);
		Directory root = new Directory("/", null);
		root.addFile(b);
		root.addFile(c);
		Directory a = new Directory("a", root);
		a.addFile(f);
		a.addFile(g);
		a.addFile(h);
		Directory e = new Directory("e", a);
		e.addFile(i);
		Directory d = new Directory("d", root);
		d.addFile(j);
		d.addFile(dlog);
		d.addFile(dexe);
		d.addFile(k);
		/*- / (dir)
		  - a (dir)
		    - e (dir)
		      - i (file, size=584)
		    - f (file, size=29116)
		    - g (file, size=2557)
		    - h.lst (file, size=62596)
		  - b.txt (file, size=14848514)
		  - c.dat (file, size=8504156)
		  - d (dir)
		    - j (file, size=4060174)
		    - d.log (file, size=8033020)
		    - d.ext (file, size=5626152)
		    - k (file, size=7214296)*/
		URL fileName = getClass().getResource("SampleInput.txt");
		fileSystemFromInput.setFileToUse(new File(fileName.getPath()));
		fileSystemFromInput.populateInstructions();
		fileSystemFromInput.processInstructionsToBuildDirTree();
		assertEquals(root, fileSystemFromInput.getRootDir());
	}
	
	@Test
	void verify_total_size_of_directories() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		fileSystemFromInput.setFileToUse(new File(fileName.getPath()));
		fileSystemFromInput.populateInstructions();
		fileSystemFromInput.processInstructionsToBuildDirTree();
		assertEquals(584l, fileSystemFromInput.getRootDir().getChildDirByName("a").getChildDirByName("e").getTotalSize());
		assertEquals(94853l, fileSystemFromInput.getRootDir().getChildDirByName("a").getTotalSize());
		assertEquals(24933642l, fileSystemFromInput.getRootDir().getChildDirByName("d").getTotalSize());
		assertEquals(48381165l, fileSystemFromInput.getRootDir().getTotalSize());
		
	}
	
	@Test
	void partOne_sample_verify() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		fileSystemFromInput.setFileToUse(new File(fileName.getPath()));
		fileSystemFromInput.populateInstructions();
		fileSystemFromInput.processInstructionsToBuildDirTree();
		assertEquals(95437l, fileSystemFromInput.getSumOfDirSizesThatAreLTE100000());
	}
	
	@Test
	void partOne_answer() throws Exception {
		fileSystemFromInput.populateInstructions();
		fileSystemFromInput.processInstructionsToBuildDirTree();
//		System.out.println(fileSystemFromInput.getSumOfDirSizesThatAreLTE100000());
		assertEquals(1306611, fileSystemFromInput.getSumOfDirSizesThatAreLTE100000());
	}
}

