package day7;

import java.util.ArrayList;

public class Directory {

	private String name;
	private Directory parent;
	private ArrayList<Directory> children;
	private ArrayList<MyFile> fileList;

    public Directory(String name, Directory parent) {
    	this.name = name;
    	this.parent = parent;
    	this.children = new ArrayList<Directory>();
    	this.fileList = new ArrayList<MyFile>();
    	if(parent!=null)
    		parent.children.add(this);
	}

	public long getTotalSize() {
		long total = 0l;
		for (Directory child : children) {
			total+=child.getTotalSize();
		}
		for (MyFile myFile : fileList) {
			total+=myFile.getSize();
		}
		return total;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected Directory getParent() {
		return parent;
	}

	protected void setParent(Directory parent) {
		this.parent = parent;
	}

	protected ArrayList<Directory> getChildren() {
		return children;
	}

	protected void setChildren(ArrayList<Directory> children) {
		this.children = children;
	}

	protected ArrayList<MyFile> getFileList() {
		return fileList;
	}

	protected void setFileList(ArrayList<MyFile> fileList) {
		this.fileList = fileList;
	}
	
	//Returns null if no child dir with that name exists
	public Directory getChildDirByName(String name) {
		for (Directory dir : children) {
			if(dir.getName().equals(name)) {
				return dir;
			}
		}
		return null;
	}
	//Returns null if no child dir with that name exists
	public MyFile getFileByName(String name) {
		for (MyFile file : fileList) {
			if(file.getName().equals(name)) {
				return file;
			}
		}
		return null;
	}

	@Override
    public String toString() {
		String childDirNames = "";
		for (Directory child : children) {
			childDirNames+=child.getName() + ",";
		}
		String fileNames = "";
		for (MyFile myFile : fileList) {
			fileNames+=myFile.getName() + ",";
		}
    	String print = "Name: " + name + "  - " + getTotalSize() + 
    			", {parentName=" + ((parent==null)?"null":parent.getName()) + "}" +
    			" ~ children dirs: " + childDirNames + 
    			" ~ files: " + fileNames;
		return print;
    } 

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof Directory)) { return false; }
        Directory other = (Directory) obj;

        if(this.getTotalSize() != other.getTotalSize()) { return false; }
        if(!this.name.equals(other.name)) { return false; }
        if(this.parent==null && other.parent==null) {
        	//skip check
        } else if((this.parent==null && other.parent!=null) || this.parent!=null && other.parent==null){
        	return false;
        } else {
        	if(!this.parent.getName().equals(other.parent.getName())) { return false; }
        }
        if(!this.children.equals(other.children)) { return false; }
        if(!this.fileList.equals(other.fileList)) { return false; }
        
        return true;
    }

	public void addFile(MyFile file) {
		fileList.add(file);
	}

}
