package chess;

public class Pawn extends Piece{
    public Pawn(PieceColour c) {
        this.colour = c;
        setSymbol((colour == PieceColour.WHITE) ? "\u2659" : "\u265F");
    }

    @Override
    public boolean isLegitMove(int i0, int j0, int i1, int j1) {

        Square newPosition = Board.getBoard()[i1][j1];

        int firstMoveDistance;
        int regularMoveDistance;
        int rowThatPawnIsOn;

        //assigns values to variables above based on whether the current player is black or white

        if (Board.getBoard()[i0][j0].getPiece().getColour() == PieceColour.WHITE) {
            firstMoveDistance = -2;
            regularMoveDistance = -1;
            rowThatPawnIsOn = 6;
        } else {
            firstMoveDistance = 2;
            regularMoveDistance = 1;
            rowThatPawnIsOn = 1;
        }

        //this code carries out the diagonal attack of the pawn

        if (i1 == i0 + regularMoveDistance) {
            //the piece that the pawn attacks needs to be a different colour
            if ((j1 == j0 - 1) || (j1 == j0 + 1)) {
                if ((newPosition.hasPiece() == true) && (newPosition.getPiece().getColour() != Board.getBoard()[i0][j0].getPiece().getColour())) {
                    return true;
                }
                //if there isn't a diagonal piece of the opposite colour the pawn can attack then it will just move forwards by one square
            } else if ((j1 == j0) && (newPosition.hasPiece() == false)) {
                return true;
            }
        }
        //checks if it's the first move of the pawn so it moves two forward two places
        else if ((i1 == i0 + firstMoveDistance) && (j1 == j0) && (newPosition.hasPiece() == false)) {
            if (i0 == rowThatPawnIsOn) {
                return true;
            }
        }
        //if everything else fails return false
        return false;
    }

}
