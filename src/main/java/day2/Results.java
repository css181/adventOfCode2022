package day2;

public enum Results {
	WIN, LOSE, TIE;

	static Results convert(char letter) {
		switch (letter) {
		case 'X': {
			return LOSE;
		}
		case 'Y': {
			return TIE;
		}
		case 'Z': {
			return WIN;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + letter);
		}
	}
}
