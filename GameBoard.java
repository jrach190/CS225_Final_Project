//This is the abstract GameBoard class, designed for reuse in any future board game designs I may choose to make.
public abstract class GameBoard {
	int number_board_spaces;
	//Abstract method to clear board for different types of games
	public abstract void clearBoard();
	
	//Abstract method to determine winner for different types of games
	public abstract void winCheck();

	//Abstract method to set board location for different types of games
	public int setLocation(int choice) {
		return 0;
	}
}
