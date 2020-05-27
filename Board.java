package chess;

public class Board {
	private static Square [][] board = new Square[8][8];

	public Board(){
		for (int i=0; i<board[0].length; i++){
			for (int j=0; j<board[1].length; j++)
					board[i][j]=new Square(i,j);
		}
	}


	public void printBoard(){
		System.out.print("\n  a b c d e f g h \n");
		System.out.print("  -----------------\n");
		String wsp=" ";String bar="|";

		for (int i=0; i<board[0].length; i++){
			int row=i+1;
			for (int j=0; j<board[1].length; j++){
				if ((j==0) && board[i][j].hasPiece())
					System.out.print(row + " " + board[i][j].getPiece().getSymbol());
				else if ((j==0) && !board[i][j].hasPiece())
					System.out.print(row+"  " );
				else if (board[i][j].hasPiece())
					System.out.print("|" + board[i][j].getPiece().getSymbol());
				else
					System.out.print("| ");
			}
			System.out.print("  "+row+"\n");
		}
		System.out.print("  -----------------");
		System.out.print("\n  a b c d e f g h \n");
		System.out.println();
	}

	public void initialisePieces(){
//initialising black side

		setPiece(0, 0, new Rook(PieceColour.BLACK));
		setPiece(0, 1, new Knight(PieceColour.BLACK));
		setPiece(0, 2, new Bishop(PieceColour.BLACK));
		setPiece(0, 3, new Queen(PieceColour.BLACK));
		setPiece(0, 4, new King(PieceColour.BLACK));
		setPiece(0, 5, new Bishop(PieceColour.BLACK));
		setPiece(0, 6, new Knight(PieceColour.BLACK));
		setPiece(0, 7, new Rook(PieceColour.BLACK));

		for (int j=0; j<8; j++) {
			setPiece(1, j, new Pawn(PieceColour.BLACK));
		}


		//initialising white side

		for (int j=0; j<8; j++) {
			setPiece(6, j, new Pawn(PieceColour.WHITE));
		}

		setPiece(7, 0, new Rook(PieceColour.WHITE));
		setPiece(7, 1, new Knight(PieceColour.WHITE));
		setPiece(7, 2, new Bishop(PieceColour.WHITE));
		setPiece(7, 3, new Queen(PieceColour.WHITE));
		setPiece(7, 4, new King(PieceColour.WHITE));
		setPiece(7, 5, new Bishop(PieceColour.WHITE));
		setPiece(7, 6, new Knight(PieceColour.WHITE));
		setPiece(7, 7, new Rook(PieceColour.WHITE));


	}

	public boolean movePiece(int i0, int j0, int i1, int j1, Piece p){

		String whiteKing = "\u2654";
		String blackKing = "\u265A";

		if((board[i1][j1].hasPiece()) && ((board[i1][j1].getPiece().getSymbol().equals(whiteKing)) || (board[i1][j1].getPiece().getSymbol().equals(blackKing)))){
			board[i1][j1].setPiece(p);
			board[i0][j0].removePiece();
			return true;
		}else
		board[i1][j1].setPiece(p);
		board[i0][j0].removePiece();
		return false;
	}

	public void setPiece(int x, int y, Piece piece){
		board[x][y].setPiece(piece);
	}

	public Piece getPiece(int x, int y){
		return board[x][y].getPiece();
	}

	public boolean hasPiece(int x, int y) {
		return board[x][y].hasPiece();
	}

	public static Square[][] getBoard(){
		return board;
	}
}
