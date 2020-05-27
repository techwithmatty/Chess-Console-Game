package chess;

public abstract class Piece {
private int row;
private int column;
private String symbol;
protected PieceColour colour;


public Piece(){}

public String getSymbol(){return symbol;}

public void setSymbol(String symbolType){
    this.symbol = symbolType;
}

public PieceColour getColour(){return colour;}

public void updateCoordinates(int newRow, int newColumn){
    this.row = newRow;
    this.column = newColumn;
}

public abstract boolean isLegitMove(int initI, int initJ, int finI, int finJ);

}
