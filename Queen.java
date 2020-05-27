package chess;

public class Queen extends Piece{
    public Queen(PieceColour c) {
        this.colour = c;
        setSymbol((colour == PieceColour.WHITE) ? "\u2655" : "\u265B");
    }

    @Override
    public boolean isLegitMove(int i0, int j0, int i1, int j1) {
        Square newPosition = Board.getBoard()[i1][j1];
        String directionofQueen;
        String straightOrDiag;
        Square checkingSquare; //square that will be tested for pieces

        //if the direction is upwards
        if (i1 == i0){
            //if the direction is right
            if(j1 > j0){
                directionofQueen = "right";
                straightOrDiag = "straight";
            }
            //if the direction is left
            else{
                directionofQueen = "left";
                straightOrDiag = "straight";
            }
        }
        //if the direction is downwards
        else if(j1 == j0){
            //if the direction is straight down
            if(i1 > i0){
                directionofQueen = "down";
                straightOrDiag = "straight";
            }
            //if the direction is up
            else{
                directionofQueen = "up";
                straightOrDiag = "straight";
            }
        }
        //top right
        else if(j1 > j0){
                if(i1 < i0){
                    directionofQueen = "northEast";
                    straightOrDiag = "diagonal";
                }
                //bottom right
                else{

                    directionofQueen = "southEast";
                    straightOrDiag = "diagonal";
                }
        }
        //top left
        else if(j1 < j0){
            if(i1 < i0){
                directionofQueen = "northWest";
                straightOrDiag = "diagonal";
            }
            //bottom left
            else{
                directionofQueen = "southWest";
                straightOrDiag = "diagonal";
            }
        }
        else{
            return false;
        }

        if(straightOrDiag == "diagonal"){

            if (i0 == i1 || j0 == j1){
                return false;
            }

            int distanceFromX = Math.abs(i1 - i0);
            int distanceFromY = Math.abs(j1 - j0);

            if(distanceFromX == distanceFromY){
                //for loop iterating through the diagonal path of the queen
                for(int diagonalFromOrigin = 1; diagonalFromOrigin <= distanceFromY; diagonalFromOrigin++){

                    if(directionofQueen == "northEast"){
                        checkingSquare = Board.getBoard()[i0 - diagonalFromOrigin][j0 + diagonalFromOrigin];
                    }
                    else if(directionofQueen == "southEast"){
                        checkingSquare = Board.getBoard()[i0 + diagonalFromOrigin][j0 + diagonalFromOrigin];
                    }
                    else if(directionofQueen == "northWest"){
                        checkingSquare = Board.getBoard()[i0 - diagonalFromOrigin][j0 - diagonalFromOrigin];
                    }
                    else{//southWest
                        checkingSquare = Board.getBoard()[i0 + diagonalFromOrigin][j0 - diagonalFromOrigin];
                    }

                    if((checkingSquare.hasPiece() == true) && (diagonalFromOrigin != distanceFromY)){
                        return false;
                    }
                    else if((diagonalFromOrigin == distanceFromY) && ((checkingSquare.hasPiece() == false) || (checkingSquare.getPiece().getColour() != Board.getBoard()[i0][j0].getPiece().getColour()))){
                        return true;
                    }
                }
            }
        }
        else{//straight
            if((directionofQueen == "right") || (directionofQueen == "left")){
                int maxDistanceFromY = Math.abs(j1 - j0); //max distance that rook can move from its current y position

                //first part of if statement check if the move is right or left, if it is then a vertical move is simulated
                //until either the piece collides with a piece in between in which case it would be false
                //or until an empty space is found or opposition piece is killed in which this is true when the distance is reached, in which case it would be true
                for(int distanceFromY = 1; distanceFromY <= maxDistanceFromY; distanceFromY++){
                    if(directionofQueen == "right"){
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

                        //this basically says if all of the diagonal positions before the actual position is checked and there is a piece before the piece location you want to go to then it can't move.
                        //it is invalid because you are trying to move to a piece while there are pieces in between the actual piece you want to move to
                        if((checkingSquare.hasPiece() == true) && (distanceFromY != maxDistanceFromY)){
                            return false;
                        }
                        //otherwise if it reaches the x position and if the colour of the position equals the player colour then and it is blank then proceed.
                        else if((distanceFromY == maxDistanceFromY) && ((checkingSquare.hasPiece() == false) || (checkingSquare.getPiece().getColour() != Board.getBoard()[i0][j0].getPiece().getColour()))){
                            return true;
                        }
                    }
                }
            }
            else{//upwards and downwards are checked
                int maxDistanceFromX = Math.abs(i1 - i0);

                for(int distanceFromX = 1; distanceFromX <= maxDistanceFromX; distanceFromX++) {

                    //same process with upwards
                    if (directionofQueen == "up") {
                        checkingSquare = Board.getBoard()[i0 - distanceFromX][j0];

                        if ((checkingSquare.hasPiece() == true) && (distanceFromX != maxDistanceFromX)) {
                            return false;
                        } else if ((distanceFromX == maxDistanceFromX) && ((checkingSquare.hasPiece() == false) || (checkingSquare.getPiece().getColour() != Board.getBoard()[i0][j0].getPiece().getColour()))) {
                            return true;
                        }
                    }
                    //and downwards
                    else {
                        checkingSquare = Board.getBoard()[i0 + distanceFromX][j0];

                        if ((checkingSquare.hasPiece() == true) && (distanceFromX != maxDistanceFromX)) {
                            return false;
                        } else if ((distanceFromX == maxDistanceFromX) && ((checkingSquare.hasPiece() == false) || (checkingSquare.getPiece().getColour() != Board.getBoard()[i0][j0].getPiece().getColour()))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
