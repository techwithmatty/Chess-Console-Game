package chess;


public class Square {
	private int i;
	private int j;
	private boolean hasPiece;
	private Piece p;

	public Square(int x, int y){
		i = x;
		j = y;
	}

	public Piece getPiece(){
		return p;
	}

	public void setPiece(Piece piece){
		this.p = piece;
		hasPiece = true;
	}

	public void removePiece(){
		this.p = null;
		hasPiece = false;
	}

	public boolean hasPiece() {
		return hasPiece;
	}

	public int getI(){return i;}

	public int getJ(){return j;}


}
