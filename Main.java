import java.util.Scanner;

public class Main {
	
	static boolean turn;
	static int moveCount;
	
	private static Board board;
	private static Scanner scan;
	
	public static void main(String[] args) {
		moveCount = 1;
		scan = new Scanner(System.in);
		turn = true;
		
		board = new Board();
		do {
			board.printBoard();
		} while (movePiece());
	}

	/*
	 * Process input to move piece (logic handled in Board.java)
	 */
	private static boolean movePiece() {
		String read = "";
		int sourceX = -1;
		int sourceY = -1;
		int destX = -1;
		int destY = -1;
		
		// get source piece location
		char file = '\u0000';
		int rank = 0;
		do {
			System.out.print("\nRank and file of piece to move (<a-h><1-8>): ");
			read = scan.nextLine();
			if (read.length() != 2) {
				continue;
			}
			file = Character.toLowerCase(read.charAt(0));
			rank = Character.getNumericValue(read.charAt(1));
		} while (file < 'a' || file > 'i' || rank < 1 || rank > 8);
		
		// check for sentinel to end - TEMPORARY FOR DEVELOPMENT
		if (read.charAt(0) == 'i') {
			System.exit(0);
		}
		
		// set source piece location
		sourceX = file - 97;
		sourceY = rank - 1;
		
		// get destination square
		do {
			System.out.print("\nRank and file of destination square (<a-h><1-8>): ");
			read = scan.nextLine();
			if (read.length() != 2) {
				continue;
			}
			file = Character.toLowerCase(read.charAt(0));
			rank = Character.getNumericValue(read.charAt(1));
		} while (file < 'a' || file > 'h' || rank < 1 || rank > 8);
		
		// set destination square
		destX = file - 97;
		destY = rank - 1;
		
		System.out.println("");
		
		// move piece
		if (board.movePiece(sourceX, sourceY, destX, destY)) {
			turn = !turn;
		}

		return true;
	}
}