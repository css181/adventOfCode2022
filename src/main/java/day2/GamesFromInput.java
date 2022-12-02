package day2;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import utilities.FileUtility;

public class GamesFromInput {
	
	private static File file;
	private ArrayList<Game> games;
	
	public GamesFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		games = new ArrayList<Game>();
	}

	protected void setFileToUse(File file) {
		GamesFromInput.file = file;
	}

	public void populateGames() {
		ArrayList<Game> gameList = new ArrayList<Game>();
		ArrayList<String> gamesFromInput = FileUtility.convertFileToStringArray(file);
		for (String game : gamesFromInput) {
			gameList.add(new Game(Moves.convert(game.charAt(0)), Moves.convert(game.charAt(2))));
		}
		games = gameList;
	}
	public void populateGamesPart2() {
		ArrayList<Game> gameList = new ArrayList<Game>();
		ArrayList<String> gamesFromInput = FileUtility.convertFileToStringArray(file);
		for (String game : gamesFromInput) {
			gameList.add(new Game(Moves.convert(game.charAt(0)), Results.convert(game.charAt(2))));
		}
		games = gameList;
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}
	
	
}
