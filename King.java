package chess;

public class King extends Piece{

    public King(PieceColour c) {
        this.colour = c;
        setSymbol((colour == PieceColour.WHITE) ? "\u2654" : "\u265A");
    }

    @Override
    public boolean isLegitMove(int i0, int j0, int i1, int j1) {

        Square newPosition = Board.getBoard()[i1][j1];

        //loops through all squares around the  king to check if it's a valid move
        for(int distanceFromY = -1; distanceFromY <= 1; distanceFromY++){
            for(int distanceFromX = -1; distanceFromX <=1; distanceFromX++){
                if(j1 == j0 + distanceFromY && i1 == i0 + distanceFromX){
                    if((newPosition.hasPiece() == true) && (newPosition.getPiece().getColour() != Board.getBoard()[i0][j0].getPiece().getColour())){
                        return true;
                    }
                    else if(newPosition.hasPiece() == false){
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
