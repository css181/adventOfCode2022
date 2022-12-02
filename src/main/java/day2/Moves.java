package day2;

public enum Moves {
	ROCK, PAPER, SCISSORS;
	static Moves convert(char letter) {
		switch (letter) {
		case 'A','X': {
			return ROCK;
		}
		case 'B','Y': {
			return PAPER;
		}
		case 'C','Z': {
			return SCISSORS;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + letter);
		}
	}
}
