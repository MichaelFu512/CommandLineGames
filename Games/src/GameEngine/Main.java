package GameEngine;

//import java.io.*;
import java.util.Scanner;
import com.michaelfu.TicTacToe.*;

public class Main {

	public static void main(String[] args) {
		System.out.println("----------WELCOME TO GAME SELECT----------");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("");
		System.out.println("What game would you like to play? (Press the number)");
		listOfGames();
		if(scanner.nextInt() == 1) {
			System.out.println("Tic-Tac-Toe");
			TTTBoard.startGame();
		}
	}
	
	private static void listOfGames() {
		System.out.println("-----------------------");
		System.out.println("1) Tic-Tac-Toe");
		System.out.println("2) Connect-Four");
		System.out.println("3) Chess");
		System.out.println("-----------------------");
	}
	
	
}
