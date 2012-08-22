/**
 * 
 */
package com.emergestudio.test.ui.impl;

import javax.swing.JOptionPane;

import com.emergestudio.test.ui.ITicTacToeSquare;
import com.emergestudio.test.ui.Player;

/**
 * @author jasonbruwer
 *
 *	TODO Finish this class so that the Tic Tac Toe application works according to http://en.wikipedia.org/wiki/Tic-tac-toe
 */
public class TicTacToeSquare implements ITicTacToeSquare{
	private Symbol[][] square;

	private static final int DIMENSION = 3;

	/**
	 */
	public TicTacToeSquare() 
	{
		super();

		this.newGame();
	}

	/**
	 * TODO nothing needs to be done here.
	 * 
	 * @see com.emergestudio.test.ui.ITicTacToeSquare#newGame()
	 */
	@Override
	public void newGame() {
		this.square = new Symbol[DIMENSION][];

		for(int indexX = 0;indexX < DIMENSION;indexX++)
		{
			this.square[indexX] = new Symbol[DIMENSION];

			for(int indexY = 0;indexY < DIMENSION;indexY++)
			{
				this.square[indexX][indexY] = Symbol.None;
			}
		}
	}

	/**
	 * TODO finish the implementation.
	 * TODO @see com.emergestudio.test.ui.ITicTacToeSquare#play(com.emergestudio.test.ui.Player, int, int) for required result. 
	 * @see com.emergestudio.test.ui.ITicTacToeSquare#play(com.emergestudio.test.ui.Player, int, int)
	 */
	@Override
	public boolean play(
			Player playerParam, 
			int xLocationParam,
			int yLocationParam){

		// Check to see if the player is pressing an already used button
		if (this.square[xLocationParam-1][yLocationParam-1].equals(Symbol.None)) {
			this.square[xLocationParam-1][yLocationParam-1] = playerParam.getSymbol();
		}
		else {
			// Unfortunately I cannot change the MainFrame code. :(
			// I couldn't figure out how to manipulate the swing components. :(
			// Wasn't sure if I could extend the class or not and then override the
			// btnTicTacToeAction() method and write a new Main method?
			// Wasn't sure if a dialog should be allowed in this class. I like
			// the separation of GUI from logic. But I put one in anyway.
			// So, I allowed the switch of player but without recording the previous 
			// players choice so as to punish the cheater.
			JOptionPane.showMessageDialog(null, 
					"You have pressed an already used button you will be punished by loosing your turn.", 
					"Cheater!",
					JOptionPane.ERROR_MESSAGE);
		}
		return playerWins(playerParam.getSymbol(), xLocationParam-1, yLocationParam-1) || wasGameADraw();		
	}

	/**
	 * Checks if the game was drawn.
	 * 
	 * @see com.emergestudio.test.ui.ITicTacToeSquare#wasGameADraw()
	 */
	@Override
	public boolean wasGameADraw() {

		// Checking that their are no moves left in the game
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.square[i][j].equals(Symbol.None)) {
					return false;
				}
			}
		}

		// Check to see if a player might have actually won on the last move.
		return (!(playerWins(Symbol.Cross) || playerWins(Symbol.Nought)));
	}

	/**
	 * Checks to see if the player wins
	 * 
	 * @param symbol - the players symbol either "cross" or "nought"
	 * @return
	 */
	private boolean playerWins(Symbol symbol) {
		return (AnyRowOrColumnWin(symbol, true) 
				|| AnyRowOrColumnWin(symbol, false) 
				|| diagonalWin(symbol));
	}

	/**
	 * Checks to see if the player wins
	 * 
	 * @param symbol - the players symbol either "cross" or "nought"
	 * @param xPosition - the x axis position last chosen
	 * @param yPostion - the y axis position last chosen
	 * @return
	 */
	private boolean playerWins(Symbol symbol, int xPosition, int yPostion) {
		return (RowOrColumnWin(symbol, true, yPostion) 
				|| RowOrColumnWin(symbol, false, xPosition) 
				|| diagonalWin(symbol));
	}

	/**
	 * If either the currently chosen column or row returns a win for the player
	 * 
	 * @param symbol - the players symbol either "cross" or "nought"
	 * @param row - row or column? true for row check, false for column
	 * @param position -  the position the player has just chosen either x or y axis depending on row.
	 * @return
	 */
	private final boolean RowOrColumnWin(Symbol symbol, boolean row, int position) {
		if (row) {
			return this.square[0][position] == symbol
					&& this.square[1][position] == symbol
					&& this.square[2][position] == symbol;
		} else {
			return this.square[position][0] == symbol
					&& this.square[position][1] == symbol
					&& this.square[position][2] == symbol;
		}
	}

	/**
	 * If any row or column returns a win for the player
	 * 
	 * @param symbol - the players symbol either "cross" or "nought"
	 * @param row - row or column? true for row check, false for column
	 * @return
	 */
	private final boolean AnyRowOrColumnWin(Symbol symbol, boolean row) {
		for (int i = 0; i < 3; i++) {
			if (RowOrColumnWin(symbol, row, i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * If any diagonals returns a win for the player.
	 * 
	 * @param symbol - the players symbol either "cross" or "nought"
	 * @return
	 */
	private final boolean diagonalWin(Symbol symbol) {
		return (this.square[0][0] == symbol
				&& this.square[1][1] == symbol
				&& this.square[2][2] == symbol) 
				|| (this.square[2][0] == symbol
				&& this.square[1][1] == symbol
				&& this.square[0][2] == symbol);
	}
}
