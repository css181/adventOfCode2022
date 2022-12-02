package day2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.FileUtility;

class PartOneTest {

	private GamesFromInput gamesFromInput;
	
	@BeforeEach
	public void setup() {
		gamesFromInput = new GamesFromInput();
	}
	
	@Test 
	void convertFileToArrayTest() {
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("A Y");
		expected.add("B X");
		expected.add("C Z");
		URL fileName = getClass().getResource("SampleInput.txt");
		ArrayList<String> actual = FileUtility.convertFileToStringArray(new File(fileName.getPath()));
		assertEquals(expected, actual);
	}
	
	@Test
	void convertStringsToMoves() throws Exception {
		assertEquals(Moves.ROCK, Moves.convert('A'));
		assertEquals(Moves.ROCK, Moves.convert('X'));
		assertEquals(Moves.PAPER, Moves.convert('B'));
		assertEquals(Moves.PAPER, Moves.convert('Y'));
		assertEquals(Moves.SCISSORS, Moves.convert('C'));
		assertEquals(Moves.SCISSORS, Moves.convert('Z'));
	}
	
	@Test
	void convertStringsToGames() throws Exception {
		ArrayList<Game> expectedGames = new ArrayList<Game>();
		expectedGames.add(new Game(Moves.ROCK, Moves.PAPER));
		expectedGames.add(new Game(Moves.PAPER, Moves.ROCK));
		expectedGames.add(new Game(Moves.SCISSORS, Moves.SCISSORS));
		URL fileName = getClass().getResource("SampleInput.txt");
		gamesFromInput.setFileToUse(new File(fileName.getPath()));
		gamesFromInput.populateGames();
		assertEquals(expectedGames, gamesFromInput.getGames());
	}
	
	@Test
	void verifyScoresOfEachSampleInputGame() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		gamesFromInput.setFileToUse(new File(fileName.getPath()));
		gamesFromInput.populateGames();
		assertEquals(8, gamesFromInput.getGames().get(0).getScore());
		assertEquals(1, gamesFromInput.getGames().get(1).getScore());
		assertEquals(6, gamesFromInput.getGames().get(2).getScore());
	}
	
	@Test
	void calculateTotalOfAllGames_Part1Answer() throws Exception {
		gamesFromInput.populateGames();
		int totalScore = 0;
		for (Game game : gamesFromInput.getGames()) {
			totalScore += game.getScore();
		}
//		System.out.println(totalScore);
		assertEquals(12679, totalScore);
	}
}
