package chess;

public class Knight extends Piece{
    public Knight(PieceColour c) {
        this.colour = c;
        setSymbol((colour == PieceColour.WHITE) ? "\u2658" : "\u265E");
    }

    @Override
    public boolean isLegitMove(int i0, int j0, int i1, int j1) {
        Square newPosition = Board.getBoard()[i1][j1];

        boolean correctMove = false;

        for (int distanceFromY = -2; distanceFromY <=2; distanceFromY++){
            //if the y coordinate is 0 then that cannot be a valid move so it skips the process
            if(distanceFromY != 0){
                if(j1 == j0 + distanceFromY){

                    //if the location you are trying to move to is one of the top or bottom ones
                    //check whether it is the top right, bottom left, top left or bottom right
                    if(Math.abs(distanceFromY) == 1){
                        for (int distanceFromX = -2; distanceFromX <=2; distanceFromX +=4){
                                 if(i1 == i0 + distanceFromX){
                                     correctMove = true;
                                 }
                        }
                    }
                    //if the location you are trying to move to isn't one of the top or bottom ones (i.e. the left and right ones)
                    //check whether location on the left or right it is.
                    else{
                        for (int distanceFromX = -1; distanceFromX<=1; distanceFromX += 2){
                            if(i1 == i0 + distanceFromX){
                                correctMove = true;
                            }
                        }
                    }
                }
            }
        }
        if(correctMove == true){
            if((newPosition.hasPiece() == false) || (newPosition.getPiece().getColour() != Board.getBoard()[i0][j0].getPiece().getColour())){
                return true;
            }
        }
        // in all cases where none of these conditions turn out to be true, return false
        return false;
    }

}
