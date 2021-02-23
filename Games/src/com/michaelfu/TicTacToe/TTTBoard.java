package com.michaelfu.TicTacToe;

import java.util.Scanner;

public class TTTBoard {
	
	protected char[][] board;
	
	private static final int fRow = 0;
	private static final int sRow = 2;
	private static final int tRow = 4;
	
	private static final int fCol = 0;
	private static final int sCol = 2;
	private static final int tCol = 4;
	
	private static final String oWins = "O wins!";
	private static final String xWins = "X wins!";
	
	/*
	 * Constructor for TTTBoard
	 * 
	 */
	public TTTBoard() {
		board = new char[5][5];
		board = setUp(board);
	}
	
	/*
	 * Starts the game
	 * 
	 */
	public static void startGame() {
		TTTBoard TTT = new TTTBoard();
		TTTGame game = new TTTGame();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome to Tic-Tac-Toe!");
		int playType = printIntro();
		if(playType == 1) {
			System.out.println("What shoud be the difficulty of the AI?");
			System.out.println("1) Easy " + '\n' + "2) Medium" + '\n' + "3) Hard");
			int difficulty = scanner.nextInt();
			while(difficulty > 3 || difficulty < 1) {
				System.out.println("Invalid difficulty. Please try again");
				difficulty = scanner.nextInt();
			}
			game.startAI(TTT, difficulty);
		}
		else {
			TTT.printGameBoard(TTT.board);
			game.start2p(TTT);
		}

		System.out.println("Do you want to play again? (Y/N)");
		
		char restart = scanner.next().trim().charAt(0);
		if(Character.toLowerCase(restart) == 'y') {
			TTT.setUp(TTT.board);
			playType = printIntro();
			TTT.printGameBoard(TTT.board);
			game.start2p(TTT);
			System.out.print("Do you want to play again? (Y/N)");
			restart = scanner.next().trim().charAt(0);
		}
		System.out.println("Thank you for playing tic-tac-toe!");
		
	}
	
	/*
	 * Prints out the intro for TTT
	 */
	private static int printIntro() {
		System.out.println("How would you like to play Tic-Tac-Toe?");
		System.out.println("1) Versus AI");
		System.out.println("2) 2-player");
		System.out.println("(Press the corresponding number)");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int gamePlay = scanner.nextInt();
		
		return gamePlay;
	}
	
	/*
	 * Sets up the gameboard 
	 * 
	 * @param 	board = the TTT board
	 * 
	 * @return	the set up TTT board
	 */
	private char[][] setUp(char[][] board) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(i % 2 == 0) {
					if(j % 2 == 0) {
						board[i][j] = ' ';
					}
					else {
						board[i][j] = '|';
					}
				}
				else {
					board[i][j] = '-';
				}
			}
		}
		return board;
	}
	
	/*
	 * Prints out the board
	 * 
	 * @param 	gameBoard = the board
	 */
	protected void printGameBoard(char[][] gameBoard) {
		System.out.println();
		for(char[] row : gameBoard) {
			for(char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/*
	 * Checks if anyone has won yet
	 * 
	 * @param 	gameBoard = the board
	 * @param 	x = the char 'x'
	 * @param	o = the char 'o'
	 * 
	 * @return	true if someone won, false otherwise
	 */
	protected boolean checkGameState(char[][] gameBoard, char x, char o) {
		boolean gameEnd = verticalCheck(gameBoard, x, o);
		
		if(!gameEnd) {
			gameEnd = horizontalCheck(gameBoard, x, o);
			
			if(gameEnd) {
				return gameEnd;
			}
			
			gameEnd = diagonalCheck(gameBoard, x, o);
			
			if(gameEnd) {
				return gameEnd;
			}
			
			gameEnd = isBoardFull(gameBoard);
		}
		return gameEnd;
	}	
	
	protected boolean isBoardFull(char[][] gameBoard) {
		boolean boardFull = true; // used to remember if board is full
		
		//loops through entire board looking for an empty space
		for(char[] x : gameBoard) {
			for(char c : x) {
				
				//if there is an empty space, then board is not full
				if(c == ' ') {
					boardFull = false;
					return boardFull;
				}
			}	
		}
		return boardFull;
	}
	
	/*
	 * Performs the diagonal checks to determine a winner
	 * Calls upDiagCheck and downDiagCheck
	 * 
	 * @param	gameBoard = the bard
	 * @param 	x = the char 'x'
	 * @param 	o = the char 'o'
	 * 
	 * @return	true if someone won diagonally, false otherwise
	 */
	protected boolean diagonalCheck(char[][] gameBoard, char x, char o) {
		boolean check = false;
		check = upDiagCheck(gameBoard, x, o);
		if(check) {
			return true;
		}
		
		check = downDiagCheck(gameBoard, x, o);
		if(check) {
			return true;
		}
		
		return false;
	}
	
	/*
	 * x - -
	 * - x -
	 * - - x
	 * Checks for the above
	 * 
	 * @param	gameBoard = the board
	 * @param 	x = the char 'x'
	 * @param 	o = the char 'o'
	 * 
	 * @return	true if someone like above, false otherwise
	 */
	protected boolean upDiagCheck(char[][] gameBoard, char x, char o) {
		char checker = gameBoard[fRow][fCol];
		if(checker == ' ' ) {
			return false;
		}
		//this check checks [2][2], [4][4]
		for(int i = sRow; i <= tRow; i+= 2) {
			if(gameBoard[i][i] != checker) {
				return false;
			}
		}
		
		if(checker == x) {
			System.out.println(xWins);
		}
		else {
			System.out.println(oWins);
		}
		
		return true;
	}
	
	/*
	 * - - x
	 * - x -
	 * x - -
	 * Checks for the above
	 * 
	 * @param	gameBoard = the board
	 * @param 	x = the char 'x'
	 * @param 	o = the char 'o'
	 * 
	 * @return	true someone won through the above, false otherwise
	 */
	protected  boolean downDiagCheck(char[][] gameBoard, char x, char o) {
		char checker = gameBoard[fRow][tCol];
		if(checker == ' ' ) {
			return false;
		}
		
		//check [2][2]
		if(gameBoard[sRow][sCol] != checker) {
			return false;
		}
		
		//check [4][0]
		if(gameBoard[tRow][fCol] != checker) {
			return false;
		}
		
		if(checker == x) {
			System.out.println(xWins);
		}
		else {
			System.out.println(oWins);
		}
		
		return true;
	}
	
	/*
	 *  x x x
	 *  - - -
	 *  - - -
	 * Checks for the above in all 3 rows
	 * 
	 * @param	gameBoard = the board
	 * @param 	x = the char 'x'
	 * @param 	o = the char 'o'
	 * 
	 * @return	true someone won through the above, false otherwise
	 */
	protected boolean horizontalCheck(char[][] gameBoard, char x, char o) {
		char checker = ' ';
		
		//outer for loop checks [0][x] , [2][x], [4][x] with x being the column number
		for(int i = fRow; i <= tRow; i+=2) {
			
			//set the checker to be [i][0]
			checker = gameBoard[i][fCol];
			if(checker == ' ') {
				continue;
			}
			
			//inner for loop checks [i][2], [i][4]
			for(int j = sCol; j <= tCol; j+=2) {
				char check = gameBoard[i][j];
				
				//if [i][x] != [i][0]
				if(check != checker) {
					break;
				}
				
				//if we made it to [i][4] someone won
				if(j == tCol) {
					if(checker == x) {
						System.out.println(xWins);
					}
					else {
						System.out.println(oWins);
					}
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * x - -
	 * x - -
	 * x - -
	 * Checks for the above in all 3 columns
	 * 
	 * @param	gameBoard = the board
	 * @param 	x = the char 'x'
	 * @param 	o = the char 'o'
	 * 
	 * @return	true someone won through the above, false otherwise
	 */
	protected boolean verticalCheck(char[][] gameBoard, char x, char o) {
		char checker = ' ';
		
		//outer for loop checks [x][i] with x being 0,2,4
		for(int i = fCol; i <= tCol; i+=2) {
			
			//set checker to be [0][i]
			checker = gameBoard[fRow][i];
			
			//go to next column if [0][i] is not a symbol
			if(checker == ' ') {
				continue;
			}
			
			//inner for loop checks [2][i] and [4][i]
			for(int j = sRow; j <= tRow; j+=2) {
				char check = gameBoard[j][i];
				
				//if [x][i] != [0][i]
				if(check != checker) {
					break;
				}
				
				//if we made it to [4][i] someone won
				if(j == tRow) {
					if(checker == x) {
						System.out.println(xWins);
					}
					else {
						System.out.println(oWins);
					}
					return true;
				}
			}
			
		} 
		return false;
	}
}
