package com.michaelfu.TicTacToe;

import java.util.Scanner;

public class TTTGame {
	private static final char X = 'X';
	private static final char O = 'O';
	
	private static final int firstSpot = 1;
	private static final int thirdSpot = 3;

	/*
	 * Plays TTT with another player
	 * 
	 * @param	TTT = the gameBoard
	 * 
	 */
	protected void start2p(TTTBoard TTT) {
		char p1 = chooseSymbol(X, O);
		char p2 = ' ';
		if(p1 == X) {
			p2 = O;
		}
		else {
			p2 = X;
		}
		
		boolean gameDone = false;
		while(gameDone == false) {
			choiceOfPositioning(TTT, p1);
			gameDone = TTT.checkGameState(TTT.board, X, O);
		
			if(gameDone == true) {
				break;
			}
			
			choiceOfPositioning(TTT, p2);
			gameDone = TTT.checkGameState(TTT.board, X, O);
		}
		System.out.println("FINAL BOARD");
		TTT.printGameBoard(TTT.board);
	}
	
	/*
	 * Plays TTT with an AI
	 * 
	 * @param	TTT = the gameBoard
	 * @param 	difficulty = difficulty of the AI
	 * 
	 */
	protected void startAI(TTTBoard TTT, int difficulty) {
		char p1 = chooseSymbol(X, O);
		char p2 = ' ';
		if(p1 == X) {
			p2 = O;
		}
		else {
			p2 = X;
		}
		TTTAI ai = new TTTAI(p2);
		performAIGame(TTT, difficulty, p1, ai);
		
		System.out.println("FINAL BOARD");
		TTT.printGameBoard(TTT.board);
	}
	
	/*
	 * Plays TTT with an AI
	 * 
	 * @param	TTT = the gameBoard
	 * @param 	difficulty = difficulty of the AI
	 * @param	p1 = player
	 * @param	ai = the ai
	 * 
	 */
	protected void performAIGame(TTTBoard TTT, int difficulty, char p1, TTTAI ai) {
		boolean gameDone = false;
		
		//easy AI
		if(difficulty == 1) {
			while(gameDone == false) {
				choiceOfPositioning(TTT, p1);
				gameDone = TTT.checkGameState(TTT.board, X, O);
				if(gameDone == true) {
					break;
				}
				ai.easy(TTT, ai.symbol);
				gameDone = TTT.checkGameState(TTT.board, X, O);
			}
		}
		//medium AI
		else if(difficulty == 2) {
			while(gameDone == false) {
				choiceOfPositioning(TTT, p1);
				gameDone = TTT.checkGameState(TTT.board, X, O);
			
				if(gameDone == true) {
					break;
				}
				
				ai.medium(TTT, ai.symbol);
				gameDone = TTT.checkGameState(TTT.board, X, O);
			}
		}
		//hard AI
		else {
			while(gameDone == false) {
				choiceOfPositioning(TTT, p1);
				gameDone = TTT.checkGameState(TTT.board, X, O);
			
				if(gameDone == true) {
					break;
				}
				
				ai.hard(TTT, ai.symbol);
				gameDone = TTT.checkGameState(TTT.board, X, O);
			}
		}
	}
	
	/*
	 * Asks the user what symbol they want to be
	 * 
	 * @param	x = the character 'x'
	 * @param 	o = the character 'o'
	 * 
	 */
	protected char chooseSymbol(char x, char o) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		System.out.print("What letter do you want to be (X | O)? ");
		char symbol = scan.next().trim().charAt(0);
		
		while(Character.toUpperCase(symbol) != x && Character.toUpperCase(symbol) != o) {
			System.out.println("Error: Not a valid symbol, try again!");
			System.out.print("What letter do you want to be? (X | O) ");
			symbol = scan.next().charAt(0);
		}
		
		symbol = Character.toUpperCase(symbol);
		System.out.println("You are " + symbol + "!");

		return symbol;
	}
	
	/*
	 * Asks the user where to put their symbol
	 * 
	 * @param	gameBoard = the board
	 * @param 	symbol = the player's symbol
	 * 
	 */
	protected void choiceOfPositioning(TTTBoard TTT, char symbol) {
		System.out.println(symbol + "'s turn!");
		boolean loop = true;
		while(loop == true) {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			
			System.out.print("Enter the row you want to input in (1-3): ");
			int rowPosition = scan.nextInt();
			
			//if they enter out of bounds for row
			while(rowPosition > thirdSpot || rowPosition < firstSpot) {
				System.out.println("Invalid row! Please enter a valid row. ");
				System.out.print("Enter the row you want to input in (1-3): ");
				rowPosition = scan.nextInt();
			}
			
			System.out.print("Enter the column you want to input in (1-3): ");
			int colPosition = scan.nextInt();
			
			//if they enter out of bounds for col
			while(colPosition > thirdSpot || colPosition < firstSpot) {
				System.out.println("Invalid column! Please enter a valid column. ");
				System.out.print("Enter the column you want to input in (1-3): ");
				colPosition = scan.nextInt();
			}
			
			//internally correct the row and col
			rowPosition = positionCorrection(rowPosition);
			colPosition = positionCorrection(colPosition);
			
			//if we can successfully insert the symbol
			if(TTT.board[rowPosition][colPosition] == ' ') {
				TTT.board[rowPosition][colPosition] = symbol;
				TTT.printGameBoard(TTT.board);
				loop = false;
			}
			
			else {
				System.out.println();
				System.out.println("That spot already has a symbol, try another spot");
				TTT.printGameBoard(TTT.board);
			}
		}
	}
	
	/*
	 * Corrects the user's row and column input for the char[][] gameBoard
	 * 
	 * @param	position = the position to correct
	 * 
	 * @return	corrected position
	 */
	protected int positionCorrection(int position) {
		//If user put 1, turn to 0
		if(position == firstSpot) {
			position = position - 1;
		}
		//if user put 3, turn to 4
		else if(position == thirdSpot) {
			position = position + 1;
		}
		return position;
	}
	
	/*
	 * Asks the user where to put their symbol
	 * 
	 * @param	gameBoard = the board
	 * @param 	symbol = the player's symbol
	 * 
	 */
	protected boolean choiceOfPositioning(TTTBoard TTT, char symbol, int row, int col) {
		System.out.println(symbol + "'s turn!");
		
		//if we can successfully insert the symbol
		if(TTT.board[row][col] == ' ') {
			TTT.board[row][col] = symbol;
			TTT.printGameBoard(TTT.board);
		}
		else {
			return false;
		}
		
		return true;
	}
	
}
