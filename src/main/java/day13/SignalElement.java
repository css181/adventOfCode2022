package day13;

import java.util.ArrayList;

public class SignalElement implements Comparable<SignalElement>{
	private int number;
	private ArrayList<SignalElement> elementList;
	public SignalElement(ArrayList<SignalElement> list) {
		this.number = -1;
		this.elementList = (ArrayList<SignalElement>)list;
	}
	public SignalElement(int number) {
		this.number = number;
		this.elementList = null;
	}
	protected int getNumber() {
		return number;
	}
	protected void setNumber(int number) {
		this.number = number;
	}
	protected ArrayList<SignalElement> getElementList() {
		return elementList;
	}
	protected void setElementList(ArrayList<SignalElement> numList) {
		this.elementList = numList;
	}
	
	@Override
    public String toString() {
		String print = "";
		if(this.elementList==null) {
			print = String.valueOf(number);
		} else {
			print = elementList.toString();
		}
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

        if(!(obj instanceof SignalElement)) { return false; }
        SignalElement other = (SignalElement) obj;

        if(this.number != other.number) { return false; }
        if(this.elementList!=null && other.elementList==null) { return false; }
        if(this.elementList==null && other.elementList!=null) { return false; }
        if((this.elementList!=null && other.elementList!=null) && !this.elementList.equals(other.elementList)) { return false; }
        
        return true;
    }
    
	@Override
	//return -1 if <, 0 if =, 1 if >
	public int compareTo(SignalElement o) {
		if(this.elementList==null && o.elementList==null) {
			//both numbers
			if(this.number<o.number) {
				System.out.println("TRUE-" + this.number + "<" + o.number);
				return -1;
			}else if (this.number==o.number) {
				return 0;
			}else {
				System.out.println("FALSE-" + this.number + ">" + o.number);
				return 1;
			}
		}else if(this.elementList!=null && o.elementList!=null) {
			//both lists
			for(int x=0; x<this.elementList.size(); x++) {
				if(x>=o.elementList.size()) {
					System.out.println("FALSE-" + "this has greater size");
					return 1;
				}
				int compare = this.elementList.get(x).compareTo(o.elementList.get(x));
				if(compare!=0) {
					System.out.println("?-" + this.elementList.get(x) + " compared to " + o.elementList.get(x) + " is " + compare);
					return compare;
				} else {
					//Compare next item
				}
			}
			if(o.elementList.size()>this.elementList.size()) {
				System.out.println("TRUE-" + "other has greater size");
				return -1;
			}else {
				return 0;
			}
		}else {
			//1 is list, 1 is number
			if(this.elementList!=null) {
				if(this.elementList.size()==0) {
					System.out.println("TRUE-" + "this is empty list");
					return -1;
				}
				int compare = this.elementList.get(0).compareTo(new SignalElement(o.number));
				if(compare!=0) {
					System.out.println("?-this list to other number - " + this.elementList.get(0) + " compared to " + o.number + " is " + compare);
					return compare;
				}else {
					if(this.elementList.size()==0) {
						return 0;
					}
					else {
						System.out.println("FALSE-" + "this list(0) = other number, but has more elements");
						return 1;
					}
				}
			}else {
				if(o.elementList.size()==0) {
					System.out.println("FALSE-" + "other is empty list");
					return 1;
				}
				int compare =(new SignalElement(this.number)).compareTo(o.elementList.get(0));
				if(compare!=0) {
					System.out.println("?-this number to other list - " + this.number + " compared to " + o.elementList.get(0) + " is " + compare);
					return compare;
				}else {
					if(o.elementList.size()==0) {
						return 0;
					}
					else {
						System.out.println("TRUE-" + "other list(0) = this number, but has more elements");
						return -1;
					}
				}
			}
		}
	}
}
