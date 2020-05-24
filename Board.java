import java.io.IOException;

public class Board {
	
	private String moveStatus = "";
	private Piece[][] board;
	
	/*
	 * Constructor for board
	 */
	public Board() {
		// create board object
		board = new Piece[8][8];
		
		// set middle four ranks to empty piece objects
		for (int i = 0; i <= 7; i++) {
			for (int j = 2; j <= 5; j++) {
				board[i][j] = new Piece();
			}
		}
		
		// set second and seventh ranks to pawns
		board[0][1] = new Piece("W", "P");
		board[1][1] = new Piece("W", "P");
		board[2][1] = new Piece("W", "P");
		board[3][1] = new Piece("W", "P");
		board[4][1] = new Piece("W", "P");
		board[5][1] = new Piece("W", "P");
		board[6][1] = new Piece("W", "P");
		board[7][1] = new Piece("W", "P");
		board[0][6] = new Piece("B", "p");
		board[1][6] = new Piece("B", "p");
		board[2][6] = new Piece("B", "p");
		board[3][6] = new Piece("B", "p");
		board[4][6] = new Piece("B", "p");
		board[5][6] = new Piece("B", "p");
		board[6][6] = new Piece("B", "p");
		board[7][6] = new Piece("B", "p");
		
		// set first and eighth ranks pieces
		board[0][0] = new Piece("W", "R");
		board[1][0] = new Piece("W", "N");
		board[2][0] = new Piece("W", "B");
		board[3][0] = new Piece("W", "Q");
		board[4][0] = new Piece("W", "K");
		board[5][0] = new Piece("W", "B");
		board[6][0] = new Piece("W", "N");
		board[7][0] = new Piece("W", "R");
		board[0][7] = new Piece("B", "r");
		board[1][7] = new Piece("B", "n");
		board[2][7] = new Piece("B", "b");
		board[3][7] = new Piece("B", "q");
		board[4][7] = new Piece("B", "k");
		board[5][7] = new Piece("B", "b");
		board[6][7] = new Piece("B", "n");
		board[7][7] = new Piece("B", "r");
	}
	
	/*
	 * Returns string represeting turn (WHITE or BLACK)
	 */
	private String getPlayerTurn() {
		if (Main.turn) {
			return "W";
		} else {
			return "B";
		}
	}
	
	public boolean movePiece(int sourceX, int sourceY, int destX, int destY) {
		
		// check if piece being moved is of correct color
		if (!board[sourceX][sourceY].getColor().equals(getPlayerTurn())) {
			moveStatus = "Wrong color attempted to move";
			return false;
		}
		
		// check that empty square isn't being moved
		if (board[sourceX][sourceY].getType().equals(" ")) {
			moveStatus = "Tried to move empty square";
			return false;
		}
		
		// check that piece does not attack it's own color
		if (board[sourceX][sourceY].getColor().equals(board[destX][destY].getColor())) {
			moveStatus = "Tried to attack own color";
			return false;
		}
		
		// movement logic for each piece type
		switch(board[sourceX][sourceY].getType()) {
			case "K":
				// check that the king isn't being moved more than one square
				if (Math.abs(sourceX - destX) > 1 || Math.abs(sourceY - destY) > 1) {
					moveStatus = "King moved illegally";
					return false;
				}
			case "Q":
				
			case "B":
				
			case "N":
				
			case "R":
				if (!(Math.abs(sourceX - destX) == 0 && Math.abs(sourceY - destY) != 0) && !(Math.abs(sourceX - destX) != 0 && Math.abs(sourceY - destY) == 0)) {
					moveStatus = "Rook moved illegally";
					return false;
				}
			case "P":
				
		}
		
		// move piece onto opponents piece or move to empty square
		if (!board[destX][destY].getColor().equals(getPlayerTurn())) {
			board[destX][destY] = board[sourceX][sourceY];
			board[sourceX][sourceY] = new Piece();
		} else {
			Piece temp = board[destX][destY];
			board[destX][destY] = board[sourceX][sourceY];
			board[sourceX][sourceY] = temp;
		}
		
		moveStatus = "Success";
		return true;
	}
	
	/*
	 * Prints board and it's current contents to console
	 */
	public void printBoard() {
		// clear console
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (NullPointerException | IndexOutOfBoundsException | SecurityException | UnsupportedOperationException | IOException | InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		// print turn header
		System.out.println("\n                      " + getPlayerTurn() + "'S TURN!\n");
		System.out.println("Past move status: " + moveStatus + "		Turn: " + Main.turn);
		
		// print board
		System.out.println("*********************************************************");
		System.out.println("*      A     B     C     D     E     F     G     H      *");
		System.out.println("*   -------------------------------------------------   *");
		System.out.println("*   |*****|     |*****|     |*****|     |*****|     |   *");
		System.out.println("* 8 |**" + board[0][7].getType() + "**|  " + board[1][7].getType() + "  |**" + board[2][7].getType() + "**|  " + board[3][7].getType() + "  |**" + board[4][7].getType() + "**|  " + board[5][7].getType() + "  |**" + board[6][7].getType() + "**|  " + board[7][7].getType() + "  | 8 *");
		System.out.println("*   |*****|     |*****|     |*****|     |*****|     |   *");
		System.out.println("*   -------------------------------------------------   *");
		System.out.println("*   |     |*****|     |*****|     |*****|     |*****|   *");
		System.out.println("* 7 |  " + board[0][6].getType() + "  |**" + board[1][6].getType() + "**|  " + board[2][6].getType() + "  |**" + board[3][6].getType() + "**|  " + board[4][6].getType() + "  |**" + board[5][6].getType() + "**|  " + board[6][6].getType() + "  |**" + board[7][6].getType() + "**| 7 *");
		System.out.println("*   |     |*****|     |*****|     |*****|     |*****|   *");
		System.out.println("*   -------------------------------------------------   *");
		System.out.println("*   |*****|     |*****|     |*****|     |*****|     |   *");
		System.out.println("* 6 |**" + board[0][5].getType() + "**|  " + board[1][5].getType() + "  |**" + board[2][5].getType() + "**|  " + board[3][5].getType() + "  |**" + board[4][5].getType() + "**|  " + board[5][5].getType() + "  |**" + board[6][5].getType() + "**|  " + board[7][5].getType() + "  | 6 *");
		System.out.println("*   |*****|     |*****|     |*****|     |*****|     |   *");
		System.out.println("*   -------------------------------------------------   *");
		System.out.println("*   |     |*****|     |*****|     |*****|     |*****|   *");
		System.out.println("* 5 |  " + board[0][4].getType() + "  |**" + board[1][4].getType() + "**|  " + board[2][4].getType() + "  |**" + board[3][4].getType() + "**|  " + board[4][4].getType() + "  |**" + board[5][4].getType() + "**|  " + board[6][4].getType() + "  |**" + board[7][4].getType() + "**| 5 *");
		System.out.println("*   |     |*****|     |*****|     |*****|     |*****|   *");
		System.out.println("*   -------------------------------------------------   *");
		System.out.println("*   |*****|     |*****|     |*****|     |*****|     |   *");
		System.out.println("* 4 |**" + board[0][3].getType() + "**|  " + board[1][3].getType() + "  |**" + board[2][3].getType() + "**|  " + board[3][3].getType() + "  |**" + board[4][3].getType() + "**|  " + board[5][3].getType() + "  |**" + board[6][3].getType() + "**|  " + board[7][3].getType() + "  | 4 *");
		System.out.println("*   |*****|     |*****|     |*****|     |*****|     |   *");
		System.out.println("*   -------------------------------------------------   *");
		System.out.println("*   |     |*****|     |*****|     |*****|     |*****|   *");
		System.out.println("* 3 |  " + board[0][2].getType() + "  |**" + board[1][2].getType() + "**|  " + board[2][2].getType() + "  |**" + board[3][2].getType() + "**|  " + board[4][2].getType() + "  |**" + board[5][2].getType() + "**|  " + board[6][2].getType() + "  |**" + board[7][2].getType() + "**| 3 *");
		System.out.println("*   |     |*****|     |*****|     |*****|     |*****|   *");
		System.out.println("*   -------------------------------------------------   *");
		System.out.println("*   |*****|     |*****|     |*****|     |*****|     |   *");
		System.out.println("* 2 |**" + board[0][1].getType() + "**|  " + board[1][1].getType() + "  |**" + board[2][1].getType() + "**|  " + board[3][1].getType() + "  |**" + board[4][1].getType() + "**|  " + board[5][1].getType() + "  |**" + board[6][1].getType() + "**|  " + board[7][1].getType() + "  | 2 *");
		System.out.println("*   |*****|     |*****|     |*****|     |*****|     |   *");
		System.out.println("*   -------------------------------------------------   *");
		System.out.println("*   |     |*****|     |*****|     |*****|     |*****|   *");
		System.out.println("* 1 |  " + board[0][0].getType() + "  |**" + board[1][0].getType() + "**|  " + board[2][0].getType() + "  |**" + board[3][0].getType() + "**|  " + board[4][0].getType() + "  |**" + board[5][0].getType() + "**|  " + board[6][0].getType() + "  |**" + board[7][0].getType() + "**| 1 *");
		System.out.println("*   |     |*****|     |*****|     |*****|     |*****|   *");
		System.out.println("*   -------------------------------------------------   *");
		System.out.println("*      A     B     C     D     E     F     G     H      *");
		System.out.println("*********************************************************");
	}
}