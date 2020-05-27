package chess;

public class Rook extends Piece{
    public Rook(PieceColour c) {
        this.colour = c;
        setSymbol((colour == PieceColour.WHITE) ? "\u2656" : "\u265C");
    }

    @Override
    public boolean isLegitMove(int i0, int j0, int i1, int j1) {

        Square newPosition = Board.getBoard()[i1][j1];
        Square checkingSquare; //square that will be tested for pieces
        String directionOfRook;

        //if the x and y coordinates do not result in a direction that goes up, down, left or right then
        //false will be returned
        if(i1 == i0){
            if(j1 > j0){
                directionOfRook = "right";
            }
            else{
                directionOfRook = "left";
            }
        }
        else if(j1 == j0){
            if(i1 > i0){
                directionOfRook = "down";
            }
            else{
                directionOfRook = "up";
            }
        }
        else{
            return false;
        }

        //checks if the rook is meant to move right or left
        if((directionOfRook == "right") || (directionOfRook == "left")){
            int maxDistanceFromY = Math.abs(j1 - j0); //max distance that rook can move from its current y position

            //first part of if statement check if the move is right, if it is then the rook is simulated
            //until either it collides with a piece in between in which case it would be false
            //or until an empty space is found or opposition piece is killed in which case this is true
            for(int distanceFromY = 1; distanceFromY <= maxDistanceFromY; distanceFromY++){
                if(directionOfRook == "right"){
                    checkingSquare = Board.getBoard()[i0][j0 + distanceFromY];

                    if((checkingSquare.hasPiece() == true) && (distanceFromY != maxDistanceFromY)){
                        return false;
                    }
                    else if((distanceFromY == maxDistanceFromY) && ((checkingSquare.hasPiece() == false) || checkingSquare.getPiece().getColour() != Board.getBoard()[i0][j0].getPiece().getColour())){
                        return  true;
                    }
                }
                else{

                    //the same process takes place, just this time for the left direction
                    checkingSquare = Board.getBoard()[i0][j0 - distanceFromY];

                    if((checkingSquare.hasPiece() == true) && (distanceFromY != maxDistanceFromY)){
                        return false;
                    }
                    else if((distanceFromY == maxDistanceFromY) && ((checkingSquare.hasPiece() == false) || (checkingSquare.getPiece().getColour() != Board.getBoard()[i0][j0].getPiece().getColour()))){
                        return true;
                    }
                }
            }
        }
        else{
            int maxDistanceFromX = Math.abs(i1 - i0);

            for(int distanceFromX = 1; distanceFromX <= maxDistanceFromX; distanceFromX++){

                //same process with upwards
                if(directionOfRook == "up"){
                    checkingSquare = Board.getBoard()[i0 - distanceFromX][j0];

                    if((checkingSquare.hasPiece() == true) && (distanceFromX != maxDistanceFromX)){
                        return false;
                    }
                    else if ((distanceFromX == maxDistanceFromX) && ((checkingSquare.hasPiece() == false) || (checkingSquare.getPiece().getColour() != Board.getBoard()[i0][j0].getPiece().getColour()))){
                        return true;
                    }
                }
                //and downwards
                else{
                    checkingSquare = Board.getBoard()[i0 + distanceFromX][j0];

                    if((checkingSquare.hasPiece() == true) && (distanceFromX != maxDistanceFromX)){
                        return false;
                    }
                    else if((distanceFromX == maxDistanceFromX) && ((checkingSquare.hasPiece() == false) || (checkingSquare.getPiece().getColour() != Board.getBoard()[i0][j0].getPiece().getColour()))){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
