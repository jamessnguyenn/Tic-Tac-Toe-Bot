package tictactoe;

import java.io.IOException;
import java.util.Random;


/***
 * This class will create a tic tac toe game in which users are able to play Tic
 * Tac Toe based on different difficulties as well as allow user to load and
 * view past games
 * 
 * @author James Nguyen
 *
 */
public class TicTacToe {
	private final MainMenuWindow menu;// menu window
	private int difficulty = 1; // difficulty preset to 1
	private int moveNumber = 0; // the number of moves which will be used to load past games
	private int gameNumber = 0; // the game number which will be loaded
	private final TicTacToeWindow display; // the tic tac toe window which will display the board
	private TicTacToeSaver games; // saver which hold the different past games
	private TicTacToeMoveSaver moves; // saver which holds the moves of the game as its played
	private char[][] tictactoeBoard; // the tic tac toe board
	private boolean gameIsOver;// boolean to find out whether the game is over or not
	private char compChar; // the character of the computer; it will either be X or 0
	private char userChar; // the character of the computer;
	private boolean firstPlayerIsUser; // boolean to find out who is the first player
	private String winner; // string that will be used to keep track of the winner
	private int counter = 0;// counter to count the number of moves
	private final Random generator; // random generator to decide the moves of the computer as well as whether the
						// computer or user goes first

	/***
	 * Constructor that will be used to initiate the different fields, load the
	 * games from the data file and launch the menu window
	 * 
	 * @throws IOException
	 */
	public TicTacToe() throws IOException {
		generator = new Random(); // initiate the random generator
		games = new TicTacToeSaver();
		games.loadGames();
		menu = new MainMenuWindow(this);
		display = new TicTacToeWindow(this);
		menu.setVisible(true);

	}

	/***
	 * method which will set the difficulty of the game
	 * 
	 * @param i
	 *            the difficulty of the game
	 * @precondition i ==1 || i ==2
	 */
	public void setDifficulty(int i) {
		assert i == 1 || i == 2 : "The difficulty must be equal to 1 or 2";
		difficulty = i;
	}

	/***
	 * method which will find the selected game based on the selected game number on
	 * the main menu window and puts the first move on the display window
	 * 
	 * @precondition games.getNumberOfGames()>0
	 */
	public void loadSelectedGame() {
		assert games.getNumberOfGames() > 0 : "There needs to be at least 1 Game Saved.";
		moveNumber =0;
		display.nextBackButtonVisible(true);
		display.enableNextButton();
		gameNumber = menu.getNumberOfGame() - 1;
		display.disableAllGameButtons();
		display.setVisibleMainMenuPlayButton(false);
		display.setVisibleMainMenuButton(true);
		display.disableBackButton();
		display.updateText("    Press Next or Back to View the Moves in the Game    ");
		updateAllButton(games.getGame(gameNumber).getMove(0));

	}

	/***
	 * method which will load the next move in the selected loaded game
	 * 
	 * @precondition moveNumber+1<games.getGame(gameNumber).getNumberOfMoves
	 */
	public void nextMove() {
		assert (moveNumber + 1) < games.getGame(gameNumber)
				.getNumberOfMoves() : "moveNumber increased must be less than the totalNumber of Moves"+ games.getGame(gameNumber).getNumberOfMoves() + moveNumber;
		moveNumber++;
		display.enableBackButton();
		updateAllButton(games.getGame(gameNumber).getMove(moveNumber));
		if (games.getGame(gameNumber).getNumberOfMoves() == (moveNumber + 1)) {
			display.disableNextButton();
		} else {
			display.enableNextButton();
		}

	}

	/***
	 * method which will load the previous move in the selected loaded game
	 * 
	 * @precondition (moveNumber-1)>=0
	 */
	public void moveBefore() {
		assert (moveNumber - 1) >= 0 : "moveNumber subtracted must be greater thna or equal to 0";
		moveNumber--;
		display.enableNextButton();
		updateAllButton(games.getGame(gameNumber).getMove(moveNumber));
		if (moveNumber == 0) {
			display.disableBackButton();
		} else {
			display.enableBackButton();
		}

	}

	/***
	 * set the visibility of the display. Will be used by the Main Menu Window to
	 * control the Tic Tac Toe Window
	 * 
	 * @param visibility
	 *            true to set tictactoe window as visible, false to disable
	 *            visibility of tictactoe window
	 * 
	 */
	public void setTicTacToeWindowVisibile(boolean visibility) {
		display.setVisible(visibility);
	}

	/***
	 * get the number of loaded games. Will be used by the Main Menu Window to check
	 * the number of loaded games
	 * 
	 * @return the number of loaded games
	 */
	public int numberOfLoadedGames() {
		return games.getNumberOfGames();

	}

	/**
	 * method which will reset the tic tac toe board by reenabling buttons, deciding
	 * a player, making the first move if the first player is computer, and updating
	 * the board
	 */
	public void resetBoard() {
		display.enableAllGameButtons();
		display.setVisibleMainMenuPlayButton(false);
		tictactoeBoard = new char[3][3];// initializing the tic tac toe board into a 3x3 board
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				tictactoeBoard[row][col] = ' ';
			}
		}
		moves = new TicTacToeMoveSaver();
		decidePlayer();
		counter = 0;
		gameIsOver = false;
		winner = "";
		if (!firstPlayerIsUser) {
			computerMove();
			counter++;
			moves.addMove(tictactoeBoard);
		}
		updateAllButton();
		if (firstPlayerIsUser) {
			display.updateText("You are X, and will be starting first.");
		} else {
			display.updateText("The Computer is X, and you will be going second as O");
		}
	}

	/***
	 * method which will be used to decide whether the computer and user will be
	 * going first and sets the symbols of the user and character
	 */
	private void decidePlayer() {
		int firstPlayerDecider = generator.nextInt(2);// using a random number generator to generate a number that is
		// either 1 or 0
		if (firstPlayerDecider == 1) {// if the number is 1, then the computer will go first
			compChar = 'X';// computer is set to X since it is going first
			userChar = 'O';
			firstPlayerIsUser = false;
		} else { // if the number is 0, the user goes first
			compChar = 'O'; // computer is set to O since it is going second
			userChar = 'X';
			firstPlayerIsUser = true;

		}
	}

	/**
	 * method which will update all the buttons of the display board to match those
	 * of the tic tac toe array
	 */
	private void updateAllButton() {
		for (int i = 1; i <= 9; i++) {
			char data = tictactoeBoard[getRowFromInt(i)][getColumnFromInt(i)];
			display.getSpecificButton(i - 1).setText(Character.toString(data));

		}

	}

	/***
	 * method which will update all the buttons of the display board to match those
	 * of the entered data array
	 * 
	 * @param dataMove
	 *            the entered data array to be displayed
	 * @precondtion dataMove.length * dataMove[0].length == 9
	 */
	private void updateAllButton(char[][] dataMove) {
		assert dataMove.length * dataMove[0].length == 9 : "entered data must have 9 total spaces";
		for (int i = 1; i <= 9; i++) {
			char data = dataMove[getRowFromInt(i)][getColumnFromInt(i)];
			display.getSpecificButton(i - 1).setText(Character.toString(data));

		}

	}

	/***
	 * method which sets the visibilty of the mainMenu window. This method will be
	 * used by the Tic Tac Toe window to control the MainMenu Window
	 * 
	 * @param visibility
	 *            true to set the main menu as visbile, and false to set the main
	 *            menu as invisible
	 */
	public void mainMenuWindowVisible(boolean visibility) {
		menu.setVisible(visibility);
	}

	/**
	 * Method that will test if the tic tac toe board array has any winning cases in
	 * which either the computer or user wins
	 * 
	 * @return true if there is a winning case and false if there is no winning case
	 */
	private void updateWinner() {
		// different cases of tic tac toe in which there can be three in a row or
		// diagonal of 'X' or 'O'
		String checker = "" + tictactoeBoard[0][0] + tictactoeBoard[0][1] + tictactoeBoard[0][2];
		String checker1 = "" + tictactoeBoard[1][0] + tictactoeBoard[1][1] + tictactoeBoard[1][2];
		String checker2 = "" + tictactoeBoard[2][0] + tictactoeBoard[2][1] + tictactoeBoard[2][2];
		String checker3 = "" + tictactoeBoard[0][0] + tictactoeBoard[1][1] + tictactoeBoard[2][2];
		String checker4 = "" + tictactoeBoard[2][0] + tictactoeBoard[1][1] + tictactoeBoard[0][2];
		String checker5 = "" + tictactoeBoard[0][0] + tictactoeBoard[1][0] + tictactoeBoard[2][0];
		String checker6 = "" + tictactoeBoard[0][2] + tictactoeBoard[1][2] + tictactoeBoard[2][2];
		String checker7 = "" + tictactoeBoard[0][1] + tictactoeBoard[1][1] + tictactoeBoard[2][1];
		// checks if any of the rows or diagnols are all 'XXX'
		if (checker.equals("XXX") || checker1.equals("XXX") || checker2.equals("XXX") || checker3.equals("XXX")
				|| checker4.equals("XXX") || checker5.equals("XXX") || checker6.equals("XXX")
				|| checker7.equals("XXX")) {
			gameIsOver = true;
			if (compChar == 'X') { // if the computer is the X, the computer is set as the winner, otherwise the
									// winner is set to the user
				winner = "The Computer is";
			} else {
				winner = "You are";
			}
		}
		// checks if any of the rows or diagonal are all '000'
		if (checker.equals("OOO") || checker1.equals("OOO") || checker2.equals("OOO") || checker3.equals("OOO")
				|| checker4.equals("OOO") || checker5.equals("OOO") || checker6.equals("OOO")
				|| checker7.equals("OOO")) {
			gameIsOver = true;// return true that the game is over
			if (compChar == 'O') { // if the computer is 0, the computer is set as the winner, otherwise the winner
									// is set to the user
				winner = "The Computer is";
			} else {
				winner = "You are";
			}

		}

	}

	/***
	 * method which will compute the row of the array based on the corresponding
	 * number that the user chooses
	 * 
	 * @param index
	 *            the corresponding number that user chooses
	 * @return 2 if the index is 7, 8, or 9; 1 if the index is 4, 5, or 6; 0 if the
	 *         index is 1, 2, 3
	 * @precondition index =<9 && index >=1;
	 * 
	 */
	private int getRowFromInt(int index) {
		assert index <= 9
				&& index >= 1 : "Entered index must be less than or equal to 9 and greater than or equal to 1";
		if (index == 7 || index == 8 || index == 9) {
			return 2;
		}
		if (index == 4 || index == 5 || index == 6) {
			return 1;
		}
		return 0;
	}

	/****
	 * method which will compute the column of the array based on the corresponding
	 * number that the user chooses
	 * 
	 * @param index
	 *            the corresponding number that the user chooses
	 * @return 2 if the index is 3, 6, or 9; 1 if the index is 2, 5, or 8; 0 if the
	 *         index is 1, 4, 7
	 * @precondition index =<9 && index >=1;
	 */
	private int getColumnFromInt(int index) {
		assert index <= 9
				&& index >= 1 : "Entered index must be less than or equal to 9 and greater than or equal to 1";
		if (index == 3 || index == 6 || index == 9) {
			return 2;
		}
		if (index == 2 || index == 5 || index == 8) {
			return 1;
		}
		return 0;
	}

	/***
	 * method which will compute the next move for the computer
	 * 
	 * @precondition counter <9
	 */
	private void computerMove() {
		assert counter < 9 : "There are no moves that can be made as the board is filled";
		if (difficulty == 1) {
			easyComputerMove();

		} else if (difficulty == 2) {
			hardComputerMove();
		}

	}

	/***
	 * helper method which will compute the computer move in the medium difficulty
	 * field. The computer will place its computer move in a random corner if it has
	 * the first move. The computer will place its computer move in either the
	 * center or corner. Further moves will be randomized, but the computer will
	 * defend against any moves that will result to three in a row
	 */
	private void hardComputerMove() {
		if (counter == 0 && !firstPlayerIsUser) {
			computerCornerMove();
		}
		if (counter == 1 && firstPlayerIsUser) {
			if (tictactoeBoard[0][0] == userChar || tictactoeBoard[0][2] == userChar || tictactoeBoard[2][0] == userChar
					|| tictactoeBoard[2][2] == userChar) {
				tictactoeBoard[1][1] = compChar;
			} else {
				computerCornerMove();
			}
		}
		if (counter >= 2 && !firstPlayerIsUser) {
			computerDefense('O');
		} else if (counter >= 2 && firstPlayerIsUser) {
			computerDefense('X');
		}
	}

	/***
	 * helper method which will compute a corner move for the computer
	 */
	private void computerCornerMove() {
		int corner = generator.nextInt(4);
		if (corner == 0) {
			tictactoeBoard[0][0] = compChar;
		} else if (corner == 1) {
			tictactoeBoard[0][2] = compChar;
		} else if (corner == 2) {
			tictactoeBoard[2][0] = compChar;
		} else if (corner == 3) {
			tictactoeBoard[2][2] = compChar;
		}
	}

	/***
	 * helper method which helps the computer defend itself if there user has the
	 * possibility of winning in the next move
	 * 
	 * @param x
	 *            the character of the other User
	 */
	private void computerDefense(char x) {

		String checker = "" + tictactoeBoard[0][0] + tictactoeBoard[0][1] + tictactoeBoard[0][2];
		String checker1 = "" + tictactoeBoard[1][0] + tictactoeBoard[1][1] + tictactoeBoard[1][2];
		String checker2 = "" + tictactoeBoard[2][0] + tictactoeBoard[2][1] + tictactoeBoard[2][2];
		String checker3 = "" + tictactoeBoard[0][0] + tictactoeBoard[1][1] + tictactoeBoard[2][2];
		String checker4 = "" + tictactoeBoard[2][0] + tictactoeBoard[1][1] + tictactoeBoard[0][2];
		String checker5 = "" + tictactoeBoard[0][0] + tictactoeBoard[1][0] + tictactoeBoard[2][0];
		String checker6 = "" + tictactoeBoard[0][2] + tictactoeBoard[1][2] + tictactoeBoard[2][2];
		String checker7 = "" + tictactoeBoard[0][1] + tictactoeBoard[1][1] + tictactoeBoard[2][1];

		if (checker.equals(x + " " + x) || checker.equals("" + x + x + " ") || checker.equals(" " + x + x)) {
			tictactoeBoard[0][checker.indexOf(' ')] = compChar;
		} else if (checker1.equals(x + " " + x) || checker1.equals("" + x + x + " ") || checker1.equals(" " + x + x)) {
			tictactoeBoard[1][checker1.indexOf(' ')] = compChar;
		} else if (checker2.equals(x + " " + x) || checker2.equals("" + x + x + " ") || checker2.equals(" " + x + x)) {
			tictactoeBoard[2][checker2.indexOf(' ')] = compChar;
		} else if (checker5.equals(x + " " + x) || checker5.equals("" + x + x + " ") || checker5.equals(" " + x + x)) {
			tictactoeBoard[checker5.indexOf(' ')][0] = compChar;
		} else if (checker7.equals(x + " " + x) || checker7.equals("" + x + x + " ") || checker7.equals(" " + x + x)) {
			tictactoeBoard[checker7.indexOf(' ')][1] = compChar;
		} else if (checker6.equals(x + " " + x) || checker6.equals("" + x + x + " ") || checker6.equals(" " + x + x)) {
			tictactoeBoard[checker6.indexOf(' ')][2] = compChar;
		} else if (checker3.equals(x + " " + x) || checker3.equals("" + x + x + " ") || checker3.equals(" " + x + x)) {
			tictactoeBoard[checker3.indexOf(' ')][checker3.indexOf(' ')] = compChar;
		} else if (checker4.equals(x + " " + x) || checker4.equals("" + x + x + " ") || checker4.equals(" " + x + x)) {
			if (checker4.equals(x + " " + x)) {
				tictactoeBoard[1][1] = compChar;
			}
			if (checker4.equals(" " + x + x)) {
				tictactoeBoard[2][0] = compChar;
			}
			if (checker4.equals("" + x + x + " ")) {
				tictactoeBoard[0][2] = compChar;
			}
		} else {
			easyComputerMove();
		}

	}

	/***
	 * helper method for computer to compute an easy move in which the computer will
	 * move to a random valid open space
	 */
	private void easyComputerMove() {
		int row = generator.nextInt(3); // using a number generator to compute a number between 0 and 2 for the row
		int col = generator.nextInt(3); // using a number generator to compute a number between 0 and 2 for the col
		while (tictactoeBoard[row][col] != ' ') {// using a wild loop to find space that is empty to put the computers
													// character in
			row = generator.nextInt(3);
			col = generator.nextInt(3);
		}
		tictactoeBoard[row][col] = compChar;
		updateAllButton();
		if (counter == 9) {// if the counter is 9, the loop is broken as the board is full
			updateWinner();// is over is called to see there is any winners and set the winners
		}
	}

	/***
	 * method that will input a movement by the User and then adds a computer move
	 * if there are move moves that can be made
	 * 
	 * @throws IOException
	 *             input output exception
	 * @precondition i>=1 && i<=9
	 */
	public void move(int i) throws IOException {
		assert i >= 1 && i <= 9 : "entered board index bust be greater or equal to 1 and less than or equal to 9";
		if (tictactoeBoard[getRowFromInt(i)][getColumnFromInt(i)] != ' ') {
			display.updateText("Invalid Entry. Choose a space that is empty.");// check if the number is on a
																				// empty box or an error message
																				// is give
		} else {
			tictactoeBoard[getRowFromInt(i)][getColumnFromInt(i)] = userChar;
			moves.addMove(tictactoeBoard);
			updateAllButton();
			counter++;
			updateWinner();
			if (counter != 9 && !gameIsOver) {
				computerMove();
				updateAllButton();
				moves.addMove(tictactoeBoard);
				counter++;
				display.updateText("Your move.");
			}
			updateWinner();

		}
		updateGameOver();

	}

	/***
	 * helper method which will test if the game is over and update the text and
	 * buttons on the display if the game is over
	 * 
	 * @throws IOException
	 *             input out expception
	 */
	private void updateGameOver() throws IOException {
		if (counter == 9 && winner.equals("")) {
			display.updateText("                  GAME OVER. There were no winners.                  ");
			games.addGame(moves);
			display.setVisibleMainMenuPlayButton(true);
			display.disableAllGameButtons();
			games.writeGames();
			menu.addGame();
		} else if (gameIsOver) {
			display.updateText("                  GAME OVER. " + winner + " the winner!                  ");
			games.addGame(moves);
			games.writeGames();
			menu.addGame();
			display.setVisibleMainMenuPlayButton(true);
			display.disableAllGameButtons();

		}

	}

}
