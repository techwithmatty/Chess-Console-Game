package chess;
import java.util.Scanner;

public class Game {
	private static boolean gameEnd=false;
	char LETTERS[] = {'a','b','c','d','e','f','g','h'};
	private static PieceColour currentPlayer;

	public Game(){
		Board b = new Board();
		b.initialisePieces();
		b.printBoard();
		currentPlayer = PieceColour.WHITE;
		while (!gameEnd){
			//write the game logic
			if (currentPlayer == PieceColour.WHITE){
				System.out.println("- - - - - -  Whites move  - - - - - -");
			}else{
				System.out.println("- - - - - -  Blacks move  - - - - - -");
			}
			int i0 = 0;
			int j0 = 0;
			int i1 = 0;
			int j1 = 0;


			Scanner scanner  = new Scanner(System.in);
			CheckInput c = new CheckInput();

			boolean bothCoordinatesValid = false;


			while (bothCoordinatesValid == false){
				boolean isOriginValid = false;
				boolean isDestinationValid = false;
				while(isOriginValid == false){
					System.out.println("> Enter origin:");
					String origin = scanner.nextLine();
					if(origin.equals("END")){
						endOfTurn();
						System.out.println(Game.getCurrentPlayer() + "'S HAVE WON!");
						gameEnd = true;
						bothCoordinatesValid = true;
						break;
					}
					isOriginValid = c.checkCoordinateValidity(origin);
					if (isOriginValid == false){
						System.out.println("Input invalid please try again");
					}
					else{
						i0 = Character.getNumericValue(origin.charAt(0)) - 1;
						char charj0 = origin.charAt(1);

						j0 = convertCharacter(charj0);

						if ((b.hasPiece(i0, j0) == true) && (b.getPiece(i0, j0).getColour() == Game.currentPlayer)){
							isOriginValid = true;
						}
						else{
							System.out.println("Input invalid please try again");
							isOriginValid = false;
						}
					}
				}

				if (bothCoordinatesValid == true){
					break;
				}

				System.out.println("> Enter destination:");
				String destination = scanner.nextLine();
				isDestinationValid = c.checkCoordinateValidity(destination);
				if (isDestinationValid == false){
					System.out.println("Input invalid please try again");
					bothCoordinatesValid = false;
				}
				else{
					i1 = Character.getNumericValue(destination.charAt(0)) - 1;
					char charj1 = destination.charAt(1);
					j1 = convertCharacter(charj1);

					Piece p = b.getPiece(i0,j0);
					if (p.isLegitMove(i0, j0, i1, j1) == true){
						if(b.movePiece(i0, j0, i1, j1, p) == false){
							bothCoordinatesValid = true;
							b.printBoard();
							endOfTurn();
						}else{
							bothCoordinatesValid = true;
							System.out.println(Game.getCurrentPlayer() + "'S HAVE WON!");
							gameEnd = true;
						}
					}
					else{
						System.out.println("Input invalid please try again");
						bothCoordinatesValid = false;
					}
				}
			}
		}
	}

	public int convertCharacter(char characterInputted){
		//the user will enter a char but it need to be a num for program
		int correctNumber = 0;
		for(int i = 0; i < LETTERS.length; i++){
			if(LETTERS[i] == characterInputted){
				correctNumber = i;
			}
		}
		return correctNumber;
	}

	//used in game class to output winning player
	public static PieceColour getCurrentPlayer(){return currentPlayer;}

	public void endOfTurn(){
		if (currentPlayer == PieceColour.WHITE){
			currentPlayer = PieceColour.BLACK;
		} else {
			currentPlayer = PieceColour.WHITE;
		}
	}

	public static void main (String args[]){Game g  = new Game();}
}
