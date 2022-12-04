package day4;

public class ElfPair {

	private Elf smallerElf;
	private Elf largerElf;
	
	public ElfPair() {
	}
	
	public ElfPair(Elf elf1, Elf elf2) {
		if(elf1.getRangeSize() <= elf2.getRangeSize()) {
			this.smallerElf = elf1;
			this.largerElf = elf2;
		} else {
			this.smallerElf = elf2;
			this.largerElf = elf1;
		}
	}

	public ElfPair(String elfPair) {
		this(new Elf(elfPair.substring(0, elfPair.indexOf(","))), new Elf(elfPair.substring(elfPair.indexOf(",")+1)));
	}
	
	public Elf getSmallerElf() {
		return smallerElf;
	}

	public Elf getLargerElf() {
		return largerElf;
	}

	public boolean isSmallerContainedInLarger() {
		return smallerElf.getStartRange() >= largerElf.getStartRange() &&
				smallerElf.getEndrange() <= largerElf.getEndrange();
	}

	public boolean doesHaveAnyOverlap() {
		return (smallerElf.getStartRange() >= largerElf.getStartRange() &&
				smallerElf.getStartRange() <= largerElf.getEndrange()) ||
				(smallerElf.getEndrange() <= largerElf.getEndrange() &&
				smallerElf.getEndrange() >= largerElf.getStartRange());
	}
	
	@Override
    public String toString() {
    	String print = "Elf1: " + smallerElf + "Elf2: " + largerElf;
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

        if(!(obj instanceof ElfPair)) { return false; }
        ElfPair other = (ElfPair) obj;

        if(!this.smallerElf.equals(other.smallerElf)) { return false; }
        if(!this.largerElf.equals(other.largerElf)) { return false; }
        
        return true;
    }
}
