package chess;

public class Bishop extends Piece {

    public Bishop(PieceColour c) {
        this.colour = c;
        setSymbol((colour == PieceColour.WHITE) ? "\u2657" : "\u265D");
    }

    @Override
    public boolean isLegitMove(int i0, int j0, int i1, int j1) {

        //destination position
        Square newPosition = Board.getBoard()[i1][j1];
        String directionOfBishop;
        //distance to new piece which will be used to loop through diagonal path
        int distanceToNewPosByX = Math.abs(i1 - i0);
        int distanceToNewPosByY = Math.abs(j1 - j0);
        //square that will be tested for pieces
        Square checkingSquare;

        //if the user tries to move the piece up or down and not diagonal
        if (i0 == i1 || j0 == j1){
            return false;
        }


        if(distanceToNewPosByX == distanceToNewPosByY){
            //if the direction is upwards
            if (i1 < i0) {
                //if it's right
                if (j1 > j0) {
                    directionOfBishop = "northEast";
                }
                //if it's left
                else {
                    directionOfBishop = "northWest";
                }
            }
            //if the direction is downwards
            else {
                //if it's left
                if (j1 < j0) {
                    directionOfBishop = "southWest";
                } else {
                    //if it's right
                    directionOfBishop = "southEast";
                }
            }

            //for loop iterating through the diagonal path of the bishop
            for(int diagonalFromOrigin = 1; diagonalFromOrigin <= distanceToNewPosByX; diagonalFromOrigin++){

                if(directionOfBishop == "northEast"){
                    checkingSquare = Board.getBoard()[i0 - diagonalFromOrigin][j0 + diagonalFromOrigin];
                }
                else if(directionOfBishop == "southEast"){
                    checkingSquare = Board.getBoard()[i0 + diagonalFromOrigin][j0 + diagonalFromOrigin];
                }
                else if(directionOfBishop == "northWest"){
                    checkingSquare = Board.getBoard()[i0 - diagonalFromOrigin][j0 - diagonalFromOrigin];
                }
                else{ //southWest
                    checkingSquare = Board.getBoard()[i0 + diagonalFromOrigin][j0 - diagonalFromOrigin];
                }

                //this basically says if all of the diagonal positions before the actual position is checked and there is a piece before the piece location you want to go to then it can't move.
                //it is invalid because you are trying to move to a piece while there are pieces in between the actual piece you want to move to
                if((checkingSquare.hasPiece() == true) && (diagonalFromOrigin != distanceToNewPosByX)){
                    return false;
                }
                //otherwise if it reaches the x position and if the colour of the position equals the player colour then and it is blank then proceed.
                else if((diagonalFromOrigin == distanceToNewPosByX) && (!checkingSquare.hasPiece() || (checkingSquare.hasPiece() && (checkingSquare.getPiece().getColour() != Board.getBoard()[i0][j0].getPiece().getColour())))){
                    return true;
                }


            }
        }

        return false; //default return value
    }

}
