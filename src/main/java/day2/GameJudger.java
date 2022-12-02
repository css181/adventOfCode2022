package day2;

public class GameJudger {

	private static int WIN_POINTS = 6;
	private static int TIE_POINTS = 3;
	private static int LOSE_POINTS = 0;
	
	public static int calculatePoints(Game game) {
		int score = 0;
		switch (game.getOpponents()) {
			case ROCK: {
				if(Moves.ROCK.equals(game.getYours())) {
					score += TIE_POINTS + 1;//For Rock
				} else if (Moves.PAPER.equals(game.getYours())) {
					score += WIN_POINTS + 2;//For Paper
				} else {
					score += LOSE_POINTS + 3;//For Scissors
				}
			}
			break;
			case PAPER: {
				if(Moves.ROCK.equals(game.getYours())) {
					score += LOSE_POINTS + 1;//For Rock
				} else if (Moves.PAPER.equals(game.getYours())) {
					score += TIE_POINTS + 2;//For Paper
				} else {
					score += WIN_POINTS + 3;//For Scissors
				}
			}
			break;
			case SCISSORS: {
				if(Moves.ROCK.equals(game.getYours())) {
					score += WIN_POINTS + 1;//For Rock
				} else if (Moves.PAPER.equals(game.getYours())) {
					score += LOSE_POINTS + 2;//For Paper
				} else {
					score += TIE_POINTS + 3;//For Scissors
				}
			}
			break;
		}
		return score;
	}
	
	public static Moves getYourMove(Moves opponent, Results result) {
		switch (opponent) {
			case ROCK: {
				if(result.WIN.equals(result)) {
					return Moves.PAPER;
				} else if (result.TIE.equals(result)) {
					return Moves.ROCK;
				} else {
					return Moves.SCISSORS;
				}
			}
			case PAPER: {
				if(result.WIN.equals(result)) {
					return Moves.SCISSORS;
				} else if (result.TIE.equals(result)) {
					return Moves.PAPER;
				} else {
					return Moves.ROCK;
				}
			}
			case SCISSORS: {
				if(result.WIN.equals(result)) {
					return Moves.ROCK;
				} else if (result.TIE.equals(result)) {
					return Moves.SCISSORS;
				} else {
					return Moves.PAPER;
				}
			}
		}
		throw new RuntimeException("Invalid opponent move: " + opponent);
	}

}
