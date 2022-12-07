package day7;

import java.util.ArrayList;

public class LsInstruction extends Instruction {
	
	public LsInstruction(ArrayList<String> results) {
		super(null, results);
	}

	@Override
	public void process() {
		for (String result : getResults()) {
			if(result.startsWith("dir")) {
				addDirectory(result);
			} else {
				addFile(result);
			}
		}
	}

	private void addDirectory(String result) {
		Directory curDir = FileSystemFromInput.getCurDir();
		String dirName = result.substring(result.indexOf(" ") + 1);
		if(curDir == null) {
			throw new RuntimeException("Can't add directory. FileSystem has no root.");
		} else if(curDir.getChildDirByName(dirName) == null) {
			new Directory(dirName, curDir);
		} else {
			//Dir has already been added.
		}
	}

	private void addFile(String result) {
		Directory curDir = FileSystemFromInput.getCurDir();
		long size = Long.valueOf(result.substring(0, result.indexOf(" ")));
		String name = result.substring(result.indexOf(" ") + 1);
		if(curDir == null) {
			throw new RuntimeException("Can't add file. FileSystem has no root.");
		} else if(curDir.getFileByName(name) == null) {
			curDir.addFile(new MyFile(name, size));
		} else {
			//Dir has already been added.
		}
	}
}
