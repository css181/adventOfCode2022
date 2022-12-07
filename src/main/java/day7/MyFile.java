package day7;

public class MyFile {

	private String name;
	private long size;
	public MyFile(String name, long size) {
		super();
		this.name = name;
		this.size = size;
	}
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected long getSize() {
		return size;
	}
	protected void setSize(long size) {
		this.size = size;
	}
	
	@Override
    public String toString() {
    	String print = "Name: " + name + "  - " + size;
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

        if(!(obj instanceof MyFile)) { return false; }
        MyFile other = (MyFile) obj;

        if(this.size != other.size) { return false; }
        if(!this.name.equals(other.name)) { return false; }
        return true;
    }
}
