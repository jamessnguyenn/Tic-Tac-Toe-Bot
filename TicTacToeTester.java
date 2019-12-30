package tictactoe;

import java.util.ArrayList;
import java.util.Collections;

/***
 * Class which is used to the test the different methods of the other classes
 * 
 * @author James Nguyen
 *
 */
public class TicTacToeTester {
	/***
	 * main method which will test distinct methods from other classes
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		boolean failure = false;
		TicTacToeSaver tester = new TicTacToeSaver();
		for (int i = 1; i <= 100; i++) {
			tester.addGame(new TicTacToeMoveSaver());
		}
		TicTacToeSaver tester1 = new TicTacToeSaver();
		for (int i = 1; i <= 30; i++) {
			tester1.addGame(new TicTacToeMoveSaver());
		}
		TicTacToeSaver tester2 = new TicTacToeSaver();
		for (int i = 1; i <= 75; i++) {
			tester2.addGame(new TicTacToeMoveSaver());
		}
		TicTacToeSaver tester3 = new TicTacToeSaver();
		for (int i = 1; i <= 150; i++) {
			tester3.addGame(new TicTacToeMoveSaver());
		}
		TicTacToeSaver tester10 = new TicTacToeSaver();
		for (int i = 1; i <= 150; i++) {
			tester10.addGame(new TicTacToeMoveSaver());
		}
		TicTacToeSaver tester11 = new TicTacToeSaver();
		for (int i = 1; i <= 150; i++) {
			tester11.addGame(new TicTacToeMoveSaver());
		}
		ArrayList<TicTacToeSaver> testerList = new ArrayList<TicTacToeSaver>();
		System.out.println("CHECKING IF getNumberOfGames IS CORRECT");
		testerList.add(tester);
		if (tester.getNumberOfGames() == 100) {
			System.out.println("Tester has correct amount of games.  PASSED");
		} else {
			System.out.println("Tester has incorrect amount of games.  FAILED");
			failure = true;
		}
		testerList.add(tester1);
		if (tester1.getNumberOfGames() == 30) {
			System.out.println("Tester 1 has correct amount of games.  PASSED");
		} else {
			System.out.println("Tester 1 has incorrect amount of games.  FAILED");
			failure = true;
		}
		testerList.add(tester2);
		if (tester2.getNumberOfGames() == 75) {
			System.out.println("Tester 2 has correct amount of games.  PASSED");
		} else {
			System.out.println("Tester 2 has incorrect amount of games.  FAILED");
			failure = true;
		}
		testerList.add(tester3);
		if (tester3.getNumberOfGames() == 150) {
			System.out.println("Tester 3 has correct amount of games.  PASSED");
		} else {
			System.out.println("Tester 3 has incorrect amount of games.  FAILED");
			failure = true;
		}
		
		System.out.println("---------SORTING BY NUM OF GAMES---------");
		Collections.sort(testerList, tester.comparatorByNumOfGames());
		System.out.println("TEST BY NUMBER OF GAMES");
		for (int i = 0; i < testerList.size() - 1; i++) {
			if (testerList.get(i).getNumberOfGames() <= testerList.get(i + 1).getNumberOfGames()) {
				System.out.println(testerList.get(i).getNumberOfGames() + " games is less than or eqaul to "
						+ testerList.get(i + 1).getNumberOfGames() + " games PASSED");
			} else {
				System.out.println(testerList.get(i).getNumberOfGames() + " games is less than or eqaul to "
						+ testerList.get(i + 1).getNumberOfGames() + " games FAILED");
				failure = true;
			}
		}
		System.out.println("------------TEST OF EQUALS AND HASHCODE------------");
		if (tester3.equals(tester10) && tester10.equals(tester11)) {
			System.out.println("SYMMETRIC PASSED");
			if(tester3.hashCode() == tester10.hashCode()) {
				System.out.println("HASH CODE PASSED");
			}else {
				failure = true;
				System.out.println("HASH CODE FAILED");
			}
		} else {
			System.out.println("SYMMETRIC FAILED");
			failure = true;
		}
		if (tester3.equals(tester3)) {
			System.out.println("RELFEXIVE PASSED");
		} else {
			System.out.println("REFLEXIVE FAILED");
			failure = true;
		}
		if (tester3.equals(tester10) && tester10.equals(tester11) && tester3.equals(tester11)) {
			System.out.println("TRANSITIVE PASSED");
		} else {
			System.out.println("TRANSITIVE FAILED");
			failure = true;
		}
		System.out.println("_________________________________________________________________________________________");
		System.out.println();
		System.out.println("TEST BY NUMBER OF MOVES");
		TicTacToeMoveSaver tester4 = new TicTacToeMoveSaver();
		for (int i = 1; i <= 123; i++) {
			tester4.addMove(new char[3][3]);
		}
		TicTacToeMoveSaver tester5 = new TicTacToeMoveSaver();
		for (int i = 1; i <= 23; i++) {
			tester5.addMove(new char[3][3]);
		}
		TicTacToeMoveSaver tester6 = new TicTacToeMoveSaver();
		for (int i = 1; i <= 260; i++) {
			tester6.addMove(new char[3][3]);
		}
		TicTacToeMoveSaver tester7 = new TicTacToeMoveSaver();
		for (int i = 1; i <= 10; i++) {
			tester7.addMove(new char[3][3]);
		}
		TicTacToeMoveSaver tester8 = new TicTacToeMoveSaver();
		for (int i = 1; i <= 10; i++) {
			tester8.addMove(new char[3][3]);
		}
		TicTacToeMoveSaver tester9 = new TicTacToeMoveSaver();
		for (int i = 1; i <= 10; i++) {
			tester9.addMove(new char[3][3]);
		}
		ArrayList<TicTacToeMoveSaver> testerList2 = new ArrayList<TicTacToeMoveSaver>();
		testerList2.add(tester4);
		System.out.println("CHECKING if getNumberOfMoves IS CORRECT");
		if (tester4.getNumberOfMoves() == 123) {
			System.out.println("Tester 4 has correct amount of moves.  PASSED");
		} else {
			System.out.println("Tester 4 has incorrect amount of moves.  FAILED");
			failure = true;
		}
		testerList2.add(tester5);
		if (tester5.getNumberOfMoves() == 23) {
			System.out.println("Tester 5 has correct amount of moves.  PASSED");
		} else {
			System.out.println("Tester 5 has incorrect amount of moves.  FAILED");
			failure = true;
		}
		testerList2.add(tester6);
		if (tester6.getNumberOfMoves() == 260) {
			System.out.println("Tester 6 has correct amount of moves.  PASSED");
		} else {
			System.out.println("Tester 6 has incorrect amount of moves.  FAILED");
			failure = true;
		}
		testerList2.add(tester7);
		if (tester7.getNumberOfMoves() == 10) {
			System.out.println("Tester 7 has correct amount of moves.  PASSED");
		} else {
			System.out.println("Tester 7 has incorrect amount of moves.  FAILED");
			failure = true;
		}
		System.out.println("---------SORTING BY NUM OF MOVES---------");
		Collections.sort(testerList2, tester4.comparatorByNumOfMoves());
		for (int i = 0; i < testerList2.size() - 1; i++) {
			if (testerList2.get(i).getNumberOfMoves() <= testerList2.get(i + 1).getNumberOfMoves()) {
				System.out.println(testerList2.get(i).getNumberOfMoves() + " moves is less than or eqaul to "
						+ testerList2.get(i + 1).getNumberOfMoves() + " moves PASSED");
			} else {
				System.out.println(testerList2.get(i).getNumberOfMoves() + " moves is less than or eqaul to "
						+ testerList2.get(i + 1).getNumberOfMoves() + " moves FAILED");
				failure = true;
			}
		}
		System.out.println("------------TEST OF EQUALS AND HASH CODE------------");
		if (tester7.equals(tester8) && tester8.equals(tester7)) {
			System.out.println("SYMMETRIC PASSED");
			if(tester7.hashCode() == tester8.hashCode()) {
				System.out.println("HASH CODE PASSED");
			}else {
				System.out.println("HASH CODE FAILED");
				failure = true;
			}
		} else {
			System.out.println("SYMMETRIC FAILED");
			failure = true;
		}
		if (tester7.equals(tester7)) {
			System.out.println("RELFEXIVE PASSED");
		} else {
			System.out.println("REFLEXIVE FAILED");
			failure = true;
		}
		if (tester7.equals(tester8) && tester8.equals(tester9) && tester7.equals(tester9)) {
			System.out.println("TRANSITIVE PASSED");
		} else {
			System.out.println("TRANSITIVE FAILED");
			failure = true;
		}
		System.out.println("_________________________________________________________________________________________");
		System.out.println();
		if (failure != true) {
			System.out.println("!!!ALL TEST PASSED!!!");
		} else {
			System.out.println("AT LEAST ONE TEST FAILED");
		}
	}

}
