package tictactoe;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/***
 * Class which will set up the display for the TicTacToe Board by adding the
 * necessary buttons and options for users
 * 
 * @author James Nguyen
 *
 */
public class TicTacToeWindow extends JFrame {
	private JLabel text; // text that will be in the display
	private JButton playAgain; // play again button
	private JButton mainMenu;// main menu button
	private JButton next; // next button
	private JButton back;// back button
	private ArrayList<JButton> buttonPointers = new ArrayList<JButton>(); // array to point at the different buttons

	/***
	 * Constructor which will set up the TicTacToe Window by adding the board and
	 * buttons of the game as well as a Main Menu and Play Again button
	 * 
	 * @param game
	 *            the game that will be used by the display
	 */
	public TicTacToeWindow(TicTacToe game) {
		super("Tic Tac Toe Game");
		this.setLayout(new FlowLayout());

		addBoard(game);

		text = new JLabel("");
		text.setFont(new Font("Arial", Font.PLAIN, 20));
		this.add(text);
		addPlayMenuButton(game);
		addNextBackButtons(game);
		this.setSize(600, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/***
	 * Helper method which will create a JPanel which contains the different buttons
	 * and games of Tic Tac Toe
	 * 
	 * @param game
	 *            the game that will be used
	 */
	private void addBoard(TicTacToe game) {
		JPanel gameSection = new JPanel();
		gameSection.setPreferredSize(new Dimension(500, 500));
		gameSection.setLayout(new GridLayout(3, 3));
		JLabel title = new JLabel("TIC TAC TOE");
		title.setFont(new Font("Arial", Font.PLAIN, 60));
		this.add(title);
		for (int i = 1; i <= 9; i++) {
			final int j = i;
			JButton button = new JButton(" ");
			buttonPointers.add(button);
			button.setFont(new Font("Arial", Font.PLAIN, 40));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {// action that occurs if the Enter to Machine button is
					try {
						game.move(j);
					} catch (IOException e) {
						System.out.println("Input and Output Exception");
					}

				}
			});
			gameSection.add(button);
		}
		this.add(gameSection);
	}

	/***
	 * Helper method which will add the Play Again Button and Main Menu in which the
	 * play again button will reset the board and allow the users to play again
	 * while the Main Menu button will return the user to the main menu
	 * 
	 * @param game
	 *            the game that will be used
	 */
	private void addPlayMenuButton(TicTacToe game) {
		playAgain = new JButton("Play Again");
		mainMenu = new JButton("Main Menu");
		this.add(playAgain);
		this.add(mainMenu);
		playAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {// action that occurs if the Enter to Machine button is
				game.resetBoard();
				enableAllGameButtons();

			}
		});
		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {// action that occurs if the Enter to Machine button is
				game.mainMenuWindowVisible(true);
				nextBackButtonVisible(false);
				setVisible(false); // clicked

			}
		});
	}

	/***
	 * Helper method which will add the Next and Back buttons which will be used to
	 * replay previous games. These buttons will only be visible when they users
	 * decide to load a game.
	 * 
	 * @param game
	 *            the game that will be used
	 */
	private void addNextBackButtons(TicTacToe game) {
		next = new JButton("Next");
		back = new JButton("Back");
		nextBackButtonVisible(false);
		this.add(back);
		this.add(next);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {// action that occurs if the Enter to Machine button is
				game.nextMove();

			}
		});
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {// action that occurs if the Enter to Machine button is
				game.moveBefore(); // clicked

			}
		});
	}

	/***
	 * method which return the specific button on the board
	 * 
	 * @param i
	 *            the index of the specific button
	 * @return the specific button on the board
	 * @precondition i>=0 && i<9;
	 */
	public JButton getSpecificButton(int i) {
		assert i >= 0 && i < 9 : "Index must be greater than or equal to 0 and less than 9";
		return buttonPointers.get(i);

	}

	/***
	 * method which will set the visibility of the mainMenu and play buttons
	 * 
	 * @param visibility
	 *            true to set both buttons as Visible, false to set both buttons as
	 *            invisible
	 * 
	 */
	public void setVisibleMainMenuPlayButton(boolean visibility) {
		playAgain.setVisible(visibility);
		mainMenu.setVisible(visibility);
	}

	/***
	 * method which will set the visibility of the main menu button
	 * 
	 * @param visibility
	 *            true to set main menu buttons as Visible, false to set main menu
	 *            buttons as invisible
	 */
	public void setVisibleMainMenuButton(boolean visibility) {
		mainMenu.setVisible(visibility);
	}

	/***
	 * method which will update the text on the display by setting the text to the
	 * inputted text
	 * 
	 * @param text
	 *            the inputted text
	 */
	public void updateText(String text) {
		this.text.setText(text);
	}

	/***
	 * method which will disable all the buttons used for the Tic Tac Toe Game
	 */
	public void disableAllGameButtons() {
		for (int i = 0; i < 9; i++) {
			buttonPointers.get(i).setEnabled(false);
		}
	}

	/***
	 * method which will enable all the buttons used for the Tic Tac Toe Game
	 */
	public void enableAllGameButtons() {
		for (int i = 0; i < 9; i++) {
			buttonPointers.get(i).setEnabled(true);
		}
	}

	/***
	 * method which will disable the next button
	 */
	public void disableNextButton() {
		next.setEnabled(false);
	}

	/***
	 * method which will enable the next button
	 */
	public void enableNextButton() {
		next.setEnabled(true);
	}

	/***
	 * method which will disable the back button
	 */
	public void disableBackButton() {
		back.setEnabled(false);
	}

	/***
	 * method which will enable the back button
	 */
	public void enableBackButton() {
		back.setEnabled(true);
	}

	public void nextBackButtonVisible(boolean visibility) {
		next.setVisible(visibility);
		back.setVisible(visibility);
	}

}
