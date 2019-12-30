package tictactoe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/***
 * Class which develop a saver which will save all the different tictoe games,
 * load previous games from a data file, and write all games to a data file
 * 
 * @author James Nguyen
 *
 */
public class TicTacToeSaver {
	private ArrayList<TicTacToeMoveSaver> gameSaver;// arrayList which will contain all the games

	/***
	 * Constructor which intializes the gameSaver
	 */
	public TicTacToeSaver() {
		gameSaver = new ArrayList<TicTacToeMoveSaver>();
	}

	/***
	 * method which returns a game that is specified based on the index entered
	 * 
	 * @param i
	 *            the index entered
	 * @return the game at the index inputted
	 * @preconditon i<gameSaver.size() && i>=0
	 */
	public TicTacToeMoveSaver getGame(int i) {
		assert i < gameSaver.size() : "index must be less than the number of games";
		return gameSaver.get(i);
	}

	/***
	 * method which returns the number of games that are currently saved
	 * 
	 * @return the number of games that are currently saved
	 */
	public int getNumberOfGames() {
		return gameSaver.size();
	}

	/***
	 * method which addsAGame to the saver
	 * 
	 * @param moves
	 *            the gamesaver with all the moves which will be saved
	 */
	public void addGame(TicTacToeMoveSaver moves) {
		gameSaver.add(moves);
	}

	/***
	 * method which will load all the different games that are saved in the data
	 * file
	 * 
	 * @throws IOException
	 *             input output exception
	 */
	public void loadGames() throws IOException {
		File DataFile = new File(System.getProperty("user.home") + "/Desktop", "DATA_TicTacToe.txt");
		if (!DataFile.exists()) {
			DataFile.createNewFile();
		}
		FileReader fr;
		try {
			fr = new FileReader(DataFile);
			BufferedReader br = new BufferedReader(fr);
			String nextLine = br.readLine();
			int gameNumber = 0;
			while (nextLine != null) {
				if (nextLine.equals("END")) {
					break;
				}
				if (nextLine.charAt(0) == 'g') {// sets game number to the value after g
					gameNumber = Integer.parseInt(nextLine.substring(1));
					gameSaver.add(new TicTacToeMoveSaver());
				} else {
					gameSaver.get(gameNumber).addMove(stringToArray(nextLine));// turns string to array
				}
				nextLine = br.readLine();

			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e1) {
			System.out.println("File unable to be found");
		}
	}

	/***
	 * helper method which will transfer a line of text into a 2D array with all the
	 * moves by removing all brackets, spltting the data into a String array, and
	 * putting the data into their specified position in a 2D array
	 * 
	 * @param line
	 *            the line which will be converting
	 * @return the result array after converting the line
	 */
	private char[][] stringToArray(String line) {
		char[][] move = new char[3][3];
		line = line.replaceAll("\\[", "").replaceAll("\\]", "");
		String[] allCharacters = line.split(", ");
		for (int i = 0; i < 9; i++) {
			move[i / 3][i % 3] = allCharacters[i].charAt(0);
		}
		return move;
	}

	/***
	 * method which will write all the games into a dataFile which can be later be
	 * converted to an array again
	 * 
	 * @throws IOException
	 *             input output exception
	 */
	public void writeGames() throws IOException {
		File DataFile;
		try {
			DataFile = new File(System.getProperty("user.home") + "/Desktop", "DATA_TicTacToe.txt");
			FileWriter fw = new FileWriter(DataFile);
			PrintWriter pw = new PrintWriter(fw);
			for (int i = 0; i < gameSaver.size(); i++) {
				pw.println("g" + i);
				for (int j = 0; j < gameSaver.get(i).getNumberOfMoves(); j++) {
					pw.println(Arrays.deepToString(gameSaver.get(i).getMove(j)));

				}
			}
			pw.println("END");
			pw.close();
			fw.close();
		} catch (FileNotFoundException e1) {
			System.out.println("File unable to be found");
		}

	}

	/***
	 * Method which will return a Tic Tac Toe comparartor which will compare the Tic
	 * Tac Toe Saver with another Tic Tac Toe Saver based on the number of games the
	 * saver contains
	 * 
	 * @return comparator which compares tictactoe savers based on number of games
	 */
	public static Comparator<TicTacToeSaver> comparatorByNumOfGames() {
		return new Comparator<TicTacToeSaver>() {
			public int compare(TicTacToeSaver tictactoeSaver1, TicTacToeSaver tictactoeSaver2) {
				return (new Integer(tictactoeSaver1.getNumberOfGames())
						.compareTo(new Integer(tictactoeSaver2.getNumberOfGames())));
			}
		};
	}

	/**
	 * method which overrides the equal methods and sets equality based on the
	 * number of games
	 */
	public boolean equals(Object other) {
		TicTacToeSaver otherTicTacToeSaver = (TicTacToeSaver) other;
		return this.getNumberOfGames() == otherTicTacToeSaver.getNumberOfGames();
	}

	/***
	 * method which overrides the hashcode method and sets it based to the number of
	 * Games
	 */
	public int hashCode() {
		return this.getNumberOfGames();
	}

}
