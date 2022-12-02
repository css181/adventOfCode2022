package day2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartTwoTest {

	private GamesFromInput gamesFromInput;
	
	@BeforeEach
	public void setup() {
		gamesFromInput = new GamesFromInput();
	}
	
	
	@Test
	void convertStringsToResults() throws Exception {
		assertEquals(Results.LOSE, Results.convert('X'));
		assertEquals(Results.TIE, Results.convert('Y'));
		assertEquals(Results.WIN, Results.convert('Z'));
	}
	
	@Test
	void convertStringsToGames() throws Exception {
		ArrayList<Game> expectedGames = new ArrayList<Game>();
		expectedGames.add(new Game(Moves.ROCK, Results.TIE));
		expectedGames.add(new Game(Moves.PAPER, Results.LOSE));
		expectedGames.add(new Game(Moves.SCISSORS, Results.WIN));
		URL fileName = getClass().getResource("SampleInput.txt");
		gamesFromInput.setFileToUse(new File(fileName.getPath()));
		gamesFromInput.populateGamesPart2();
		assertEquals(expectedGames, gamesFromInput.getGames());
	}
	
	@Test
	void verifySampleINputGames_YourMoves() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		gamesFromInput.setFileToUse(new File(fileName.getPath()));
		gamesFromInput.populateGamesPart2();
		assertEquals(Moves.ROCK, gamesFromInput.getGames().get(0).getYours());
		assertEquals(Moves.ROCK, gamesFromInput.getGames().get(1).getYours());
		assertEquals(Moves.ROCK, gamesFromInput.getGames().get(2).getYours());
	}
	
	@Test
	void verifyScoresOfEachSampleInputGame() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		gamesFromInput.setFileToUse(new File(fileName.getPath()));
		gamesFromInput.populateGamesPart2();
		assertEquals(4, gamesFromInput.getGames().get(0).getScore());
		assertEquals(1, gamesFromInput.getGames().get(1).getScore());
		assertEquals(7, gamesFromInput.getGames().get(2).getScore());
	}
	
	@Test
	void calculateTotalOfAllGames_Part2Answer() throws Exception {
		gamesFromInput.populateGamesPart2();
		int totalScore = 0;
		for (Game game : gamesFromInput.getGames()) {
			totalScore += game.getScore();
		}
//		System.out.println(totalScore);
		assertEquals(14470, totalScore);
	}
}
