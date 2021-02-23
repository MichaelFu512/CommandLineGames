package com.michaelfu.TicTacToe;

public class TTTAI {
	
	protected char symbol;

	private static final int firstSpot = 1;
	private static final int thirdSpot = 3;
	
	public TTTAI(char symbol) {
		this.symbol = symbol;
	}
	
	protected void easy(TTTBoard TTT, char symbol) {
		System.out.println(symbol + "'s turn!");
		boolean loop = true;
		while(loop == true) {
			int rowPosition = (int) (Math.random() * 3 + 1);
			System.out.println("rowposition = " + rowPosition);
			
			int colPosition = (int) (Math.random() * 3 + 1);
			System.out.println("colposition = " + colPosition);
			
			//internally correct the row and col
			rowPosition = positionCorrection(rowPosition);
			colPosition = positionCorrection(colPosition);
			
			//if we can successfully insert the symbol
			if(TTT.board[rowPosition][colPosition] == ' ') {
				TTT.board[rowPosition][colPosition] = symbol;
				TTT.printGameBoard(TTT.board);
				loop = false;
			}
		}
	}
	
	//TODO: make it actually medium difficulty
	protected void medium(TTTBoard TTT, char symbol) {
		System.out.println(symbol + "'s turn!");
		boolean loop = true;
		while(loop == true) {
			int rowPosition = (int) (Math.random() * 3 + 1);
			System.out.println("rowposition = " + rowPosition);
			
			int colPosition = (int) (Math.random() * 3 + 1);
			System.out.println("colposition = " + colPosition);
			
			//internally correct the row and col
			rowPosition = positionCorrection(rowPosition);
			colPosition = positionCorrection(colPosition);
			
			//if we can successfully insert the symbol
			if(TTT.board[rowPosition][colPosition] == ' ') {
				TTT.board[rowPosition][colPosition] = symbol;
				TTT.printGameBoard(TTT.board);
				loop = false;
			}
		}
	}
	
	//TODO: make it actually hard
	protected void hard(TTTBoard TTT, char symbol) {
		System.out.println(symbol + "'s turn!");
		boolean loop = true;
		while(loop == true) {
			int rowPosition = (int) (Math.random() * 3 + 1);
			System.out.println("rowposition = " + rowPosition);
			
			int colPosition = (int) (Math.random() * 3 + 1);
			System.out.println("colposition = " + colPosition);
			
			//internally correct the row and col
			rowPosition = positionCorrection(rowPosition);
			colPosition = positionCorrection(colPosition);
			
			//if we can successfully insert the symbol
			if(TTT.board[rowPosition][colPosition] == ' ') {
				TTT.board[rowPosition][colPosition] = symbol;
				TTT.printGameBoard(TTT.board);
				loop = false;
			}
		}
	}
	
	//TODO: Create code to check if board is almost won
	protected boolean almostWon(TTTBoard TTT) {
		return false;
	}
	
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
}
