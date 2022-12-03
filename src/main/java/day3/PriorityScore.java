package day3;

public class PriorityScore {

	public static Integer calculate(char c) {
		if(c == Character.toLowerCase(c)) {
			return Integer.valueOf(c) - 96;
		}
		else
			return Integer.valueOf(c) - 38;
	}

}
