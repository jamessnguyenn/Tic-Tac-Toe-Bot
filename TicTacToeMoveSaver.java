package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/***
 * Class which will hold all the 2D array of the different moves within a game
 * 
 * @author James Nguyen
 *
 */
public class TicTacToeMoveSaver {
	private ArrayList<char[][]> moveSaver;// arraylist to hold all the moves

	/***
	 * Constructor which initializes the moveSaver list
	 */
	public TicTacToeMoveSaver() {
		moveSaver = new ArrayList<char[][]>();
	}

	/***
	 * method which will add a move into the list
	 * 
	 * @param move
	 *            the move of the tic tac toe game
	 * @precondition move.length*move[0].length ==9
	 */
	public void addMove(char[][] move) {
		assert move.length * move[0].length == 9 : "The length of the move array must have 9 spaces";
		char[][] copy = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				copy[i][j] = move[i][j];
			}
		}
		moveSaver.add(copy);
	}

	/***
	 * get the move at a specified index
	 * 
	 * @param i
	 *            the specified index
	 * @return the move at i
	 */
	public char[][] getMove(int i) {
		return moveSaver.get(i);
	}

	/***
	 * get the number of moves in the game
	 * 
	 * @return the number of the moves in the game
	 */
	public int getNumberOfMoves() {
		return moveSaver.size();
	}

	/***
	 * method which will return a TicTacToe Game Saver Comparartor which will
	 * compare the TicTacToe Game Saver with another Game Saver based on the number
	 * of Moves
	 * 
	 * @return comparartor which compares gameSavers based on number of moves
	 */
	public static Comparator<TicTacToeMoveSaver> comparatorByNumOfMoves() {
		return new Comparator<TicTacToeMoveSaver>() {
			public int compare(TicTacToeMoveSaver gameSaver1, TicTacToeMoveSaver gameSaver2) {
				return (new Integer(gameSaver1.getNumberOfMoves())
						.compareTo(new Integer(gameSaver2.getNumberOfMoves())));
			}
		};
	}

	/***
	 * Method which overrides the equals method and sets the equality of this class
	 * based on the number of moves
	 */
	public boolean equals(Object other) {
		TicTacToeMoveSaver otherMoveSaver = (TicTacToeMoveSaver) other;
		return this.getNumberOfMoves() == otherMoveSaver.getNumberOfMoves();
	}

	/***
	 * method which will override the hash code function
	 * 
	 * @Override
	 */
	public int hashCode() {
		return this.getNumberOfMoves();
	}

	/***
	 * method which will create a ToString based on the 2D array stored inside the
	 * moveSaver
	 */
	public String toString() {
		String returnedString = "";
		for (int i = 0; i < moveSaver.size(); i++) {
			returnedString = returnedString + Arrays.deepToString(moveSaver.get(i));
		}
		return returnedString;
	}
}
