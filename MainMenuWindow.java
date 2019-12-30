package tictactoe;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/***
 * Class which will show the Main Menu of the Tic Tac Toe applicaton. Users will
 * be able to choose options to either play a game of tic tac toe or view a past
 * game.
 * 
 * @author nguye
 *
 */
public class MainMenuWindow extends JFrame {
	private JComboBox difficulties; // the difficulty choices
	private JComboBox numberOfGames; // the number of Games choices
	private ArrayList<String> games; // the String array of the different game numbers

	/***
	 * Constructor which will set up the main menu window by adding the necessary
	 * buttons, text, and combo boxes
	 * 
	 * @param game
	 *            the game that will be used
	 */
	public MainMenuWindow(TicTacToe game) {
		super("MainMenu");
		this.setLayout(new FlowLayout());
		JLabel title = new JLabel("MAIN MENU");
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(title);
		addPlayDifficulties(game);
		addPreviousGames(game);
		this.setSize(290, 180);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/***
	 * Helper method which will add the play button as well as the difficulty combo
	 * box to choose which difficulty the user would like to play
	 * 
	 * @param game
	 *            the game that will be used
	 */
	private void addPlayDifficulties(TicTacToe game) {
		JButton play = new JButton("Play Tic Tac Toe");

		this.add(play);
		String[] difficultyString = { "Difficulty 1", "Difficulty 2" };
		difficulties = new JComboBox(difficultyString);
		this.add(difficulties);
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {// action that occurs if the Enter to Machine button is
				game.setTicTacToeWindowVisibile(true);
				game.setDifficulty(getNumberOfDifficulty());
				game.resetBoard();// clicked
				setVisible(false);

			}
		});
	}

	/***
	 * Helper method will add the view Past Games button and creates a combobox to
	 * choose which game one look to view
	 * 
	 * @param game
	 *            the game that will be used
	 */
	private void addPreviousGames(TicTacToe game) {
		JButton viewPastGames = new JButton("View Past Game");
		this.add(viewPastGames);
		viewPastGames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {// action that occurs if the Enter to Machine button is
				if (numberOfGames.getItemCount() != 0) {
					game.setTicTacToeWindowVisibile(true);
					game.loadSelectedGame();
					setVisible(false);
				} else {
					JFrame popUpWindow = new JFrame("No Previous Games");
					JLabel label = new JLabel("There are no previous games to view.");
					popUpWindow.add(label);
					popUpWindow.setLayout(new FlowLayout());
					popUpWindow.setSize(300, 100);
					popUpWindow.setVisible(true);
					popUpWindow.setLocationRelativeTo(null);

				}

			}
		});
		games = new ArrayList<String>();
		for (int i = 1; i <= game.numberOfLoadedGames(); i++) {
			games.add("Game #" + i);
		}
		numberOfGames = new JComboBox(games.toArray());
		this.add(numberOfGames);
	}

	/***
	 * Method which will add an additional game number to the combo box
	 */
	public void addGame() {

		numberOfGames.addItem("Game #" + (numberOfGames.getItemCount() + 1));
	}

	/***
	 * Method which will get the selected Number of the Game to view
	 * 
	 * @return the selected Number of the game to view
	 */
	public int getNumberOfGame() {
		String selected = numberOfGames.getSelectedItem().toString();
		return Integer.parseInt(selected.substring(selected.indexOf("#") + 1));
	}

	/**
	 * Method which will get the selected difficulty of the game
	 * 
	 * @return the selected difficulty
	 */
	public int getNumberOfDifficulty() {
		String selected = difficulties.getSelectedItem().toString();
		return Integer.parseInt(selected.substring(selected.indexOf(" ") + 1));
	}
}
