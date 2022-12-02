package day1;

import java.util.ArrayList;

public class Elf {
	private ArrayList<Long> calorieList;

	public Elf(ArrayList<Long> arrayList) {
		this.calorieList = (ArrayList<Long>) arrayList;
	}

	public ArrayList<Long> getCalorieList() {
		return calorieList;
	}

	public void setCalorieList(ArrayList<Long> calorieList) {
		this.calorieList = calorieList;
	}
	

	public Long getTotalCalories() {
		long total = 0;
		for (Long calorie : calorieList) {
			total+= calorie;
		}
		return total;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof Elf)) { return false; }
        Elf other = (Elf) obj;
        
        if(!this.calorieList.equals(other.calorieList)) { return false; }
        
        return true;
    }
}
