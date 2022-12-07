package day7;

public class CdInstruction extends Instruction {
    
	public CdInstruction(String commandArg) {
		super(commandArg, null);
	}

	@Override
	public void process() {
		Directory curDir = FileSystemFromInput.getCurDir();
		String dirName = getCommandArg();
		if(curDir == null) {
			FileSystemFromInput.setCurDir(new Directory(getCommandArg(), null));
		} else if(dirName.equals("..")) {
			FileSystemFromInput.setCurDir(curDir.getParent());
		} else if(curDir.getChildDirByName(dirName) == null) {
			Directory dir = new Directory(dirName, curDir);
			FileSystemFromInput.setCurDir(dir);
		} else {
			FileSystemFromInput.setCurDir(curDir.getChildDirByName(getCommandArg()));
		}
	}

}
