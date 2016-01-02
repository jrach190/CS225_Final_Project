ximport javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class Board extends GameBoard{

	//Create array boardlocs to represent values of board arrays
	private int[][] boardlocs = 
		{{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0}};
	private int i=0, j=0, turn=0;

	//Method to clear the board to start a new game
	public void clearBoard(){
		for (i=0; i<7; i++){
			for (j=0; j<6; j++){
				boardlocs[i][j] = 0;
				//System.out.print(boardlocs[i][j]);
				Connect4.Circles[i][j].setFill(Color.WHITE);
			}
		}
		i=5;
		j=0;
	}

	@Override
	//Method to set the location of a newly dropped player given the button column location and which player selected the column
	public int setLocation(int choice){
		//Try-Throw-Catch for the Array out of Bounds that can occur when a player tries to select the button for a column after the column is full.
		//Displays an error message and allows the program to continue running in spite of array indices being out of bounds.
		try{
			//Use integer values to determine which player is playing
			turn++;
			int turnmod2 = turn%2 +1;
			
			//Algorithm to determine proper place in the array to set the location value
			while ((boardlocs[choice][i])==1||(boardlocs[choice][i]==2)){
				i--;
			}
			boardlocs[choice][i] = turnmod2;
			Connect4.Circles[choice][i].setFill( (turnmod2 == 1) ? Color.BLACK : Color.RED);
			i=5;

		} catch (IndexOutOfBoundsException e){
			//Display Error Message instead of crashing program
			showError();
			//Reset i so program can continue
			i=5;
			//Decrement turn so that it remains the same player's turn after incorrect selection
			turn--;

			return 1;

		}
		return 0;
	}

	//Method to check if a player has won. This method will be fully implemented at a later date, as it is not included in the requirements of the program.
	public void winCheck(){
		/*This method is to be implemented at a future date, but the base of the work is included here as a starting point for later work.
		 * 
		//Check Vertical Patterns for Win
		for (x=0; x<7; x++){
			for (y=5; y>3; y--){
				//Make sure location is not 0
				if(boardlocs[x][y]!=0){
					if((boardlocs[x][y]==boardlocs[x-1][y])&&(boardlocs[x][y]==boardlocs[x-2][y])&&(boardlocs[x][y]==boardlocs[x-3][y])){
						showWinner(turn%2 + 1);
					}
				}
			}
		}
		*/
	}

	//Error message window for the try-throw-catch
	private void showError(){

		final String aboutError = "The column is full, you cannot add any more checkers here!";

		// Create the text label
		Label aboutLabel = new Label();
		aboutLabel.setWrapText(true);
		aboutLabel.setTextAlignment(TextAlignment.CENTER);
		aboutLabel.setFont(Font.font("Times New Roman", 16));
		aboutLabel.setText(aboutError);

		// Add the label to a StackPane
		StackPane pane = new StackPane();
		pane.getChildren().add(aboutLabel);

		// Create and display said the aforementioned pane in a new stage 	
		Scene scene = new Scene(pane, 550, 100);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("About this program");
		stage.setResizable(false);
		stage.show();
	}
	
/* This code will be implemented at a future date along with the winCheck method but for the time being is commented out.
 * 
	//Code to print pane for the winner of the game
		private void showWinner(int color){

			final String aboutWinner = "CONGRATULATIONS," + color +"WINS!";

			// Create the text label
			Label aboutLabel = new Label();
			aboutLabel.setWrapText(true);
			aboutLabel.setTextAlignment(TextAlignment.CENTER);
			aboutLabel.setFont(Font.font("Times New Roman", 16));
			aboutLabel.setText(aboutWinner);

			// Add the label to a StackPane
			StackPane pane = new StackPane();
			pane.getChildren().add(aboutLabel);

			// Create and display said the aforementioned pane in a new stage 	
			Scene scene = new Scene(pane, 100, 100);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("About this program");
			stage.setResizable(false);
			stage.show();
		}
*/
}
