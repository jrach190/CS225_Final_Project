import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class Connect4 extends Application {
	//Declare pane
	private GridPane pane;
	
	private int i, j;
	
	//Declare buttons to be used to select column
	private Button c1, c2, c3, c4, c5, c6, c7;
	
	//Make array of buttons to make board adding more simple
	private Button[] butarr = {c1, c2, c3, c4, c5, c6, c7};

	//Declare Circles to be used in the Connect4 Grid
	private static Circle	bt22, bt23, bt24, bt25, bt26, bt27,
							bt32, bt33, bt34, bt35, bt36, bt37,
							bt42, bt43, bt44, bt45, bt46, bt47,
							bt52, bt53, bt54, bt55, bt56, bt57,
							bt62, bt63, bt64, bt65, bt66, bt67,
							bt72, bt73, bt74, bt75, bt76, bt77, 
							bt82, bt83, bt84, bt85, bt86, bt87;
							
	//Make array of circles that will comprise grid for Connect4
	public static Circle[][] Circles ={{bt22, bt23, bt24, bt25, bt26, bt27},
		{bt32, bt33, bt34, bt35, bt36, bt37},
		{bt42, bt43, bt44, bt45, bt46, bt47},
		{bt52, bt53, bt54, bt55, bt56, bt57},
		{bt62, bt63, bt64, bt65, bt66, bt67},
		{bt72, bt73, bt74, bt75, bt76, bt77}, 
		{bt82, bt83, bt84, bt85, bt86, bt87}};
	
	
	//Declare the menus and MenuBar
	private MenuBar menuBar;
	private Menu menu1, menu2;
	private MenuItem miHelp, miSave, miAbout, miClose, miLastWinner, miNewGame;
	
	
	@Override
	public void start(Stage mainStage) throws Exception {
		//Creating a new FileRW for File IO
		FileRW fileRW = new FileRW();
		
		//Create the BorderPane
		pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		
		//Create the Gameboard
		Board board = new Board();
		
		//Nested for loop to create the board buttons, set their text as blank and add them to the gridPane
		for (i=0; i<7; i++){
			for (j=0; j<6; j++){
				Circles[i][j] = new Circle(25);
				Circles[i][j].setFill(Color.WHITE);
				pane.add(Circles[i][j], i+2, j+2);
				}
			}	
		for (i=0; i<7; i++){
			butarr[i] = new Button();
			butarr[i].setText("Select");
			pane.add(butarr[i], i+2, j+3);
		}
		
		
		
		//Create the menu options
		menu1 = new Menu("File");
		//menu2 = new Menu("Settings");
		menu2 = new Menu("Help");
		
		miHelp = new MenuItem();
		miSave = new MenuItem();
		miAbout = new MenuItem();
		miClose = new MenuItem();
		miLastWinner = new MenuItem();
		miNewGame = new MenuItem();
		
		//Add MenuItems to the Menus
		menu1.getItems().addAll(miNewGame,miSave, miLastWinner);
		menu2.getItems().addAll(miHelp, miAbout, miClose);
		
		//Set Text for MenuItems
		miNewGame.setText("New Game");
		miHelp.setText("Help");
		miSave.setText("Save Winner");
		miAbout.setText("About");
		miClose.setText("Exit");
		miLastWinner.setText("Display Last Winner");
		
		//Add menu options to the menu bar
		menuBar = new MenuBar();
		menuBar.getMenus().addAll(menu1, menu2);
		
		//Display menu bar at top of the GridPane
		pane.add(menuBar, 0,0);
		
		//Clear the board when New Game is selected
		miNewGame.setOnAction(e -> board.clearBoard());
		
		//Close program when miClose MenuItem is clicked
		miClose.setOnAction(e -> System.exit(0));
		
		//Open new pane with information about the program when miAbout is selected
		miAbout.setOnAction(e -> showAbout());
		
		//Open new pane with information about how to play the game when miHelp is selected
		miHelp.setOnAction(e -> showHelp());
		
		//Open new pane that allows for user to save the current winner
		miSave.setOnAction(e -> fileRW.showSave());
		
		//Open new pane that displays past winner
		miLastWinner.setOnAction(e -> fileRW.showPastWinner());
		
		
		
		//Event Handlers for the buttons beneath each Column
		butarr[0].setOnAction(e -> {
			board.setLocation(0);
			//board.winCheck();
			});
		butarr[1].setOnAction(e -> {
			board.setLocation(1);
			//board.winCheck();
		});
		butarr[2].setOnAction(e -> {
			board.setLocation(2);
			//board.winCheck();
			});
		butarr[3].setOnAction(e -> {
			board.setLocation(3);
			//board.winCheck();
			});
		butarr[4].setOnAction(e -> {
			board.setLocation(4);
			//board.winCheck();
			});
		butarr[5].setOnAction(e -> {
			board.setLocation(5);
			//board.winCheck();
			});
		butarr[6].setOnAction(e -> {
			board.setLocation(6);
			//board.winCheck();
			});

		//Allow game to function correctly
		board.clearBoard();
		
		//Create scene and show the scene, pane and stage
		Scene scene = new Scene (pane, 700, 500);
		mainStage.setScene(scene);
		mainStage.setTitle("Connect 4!");
		mainStage.show();
	}


	public static void main(String[] args) {
		//Launch the application
		launch(args);
	}
	
	
/*Method to display a new pane with information about the program.
 * Most code taken from Lab9 EventsDemo.java file written by Sean.
 * Thanks Sean!
 */
private void showAbout(){
		
		final String aboutText = "This program was written by Jonathan Rach for the CS225 final project.  "
				+ "Freely reproducible and redistributable anywhere in the U.S. Void where prohibited. A's not guaranteed but highly recommended.";
		
		// Create the text label
		Label aboutLabel = new Label();
		aboutLabel.setWrapText(true);
		aboutLabel.setTextAlignment(TextAlignment.CENTER);
		aboutLabel.setFont(Font.font("Times New Roman", 16));
		aboutLabel.setText(aboutText);
		
		// Add the label to a StackPane
		StackPane pane = new StackPane();
		pane.getChildren().add(aboutLabel);
		
		// Create and display said the aforementioned pane in a new stage 	
		Scene scene = new Scene(pane, 550, 100);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("About This Program");
		stage.setResizable(false);
		stage.show();
}

/*Method to display a new pane with information on how to play the game. 
 * Most code taken from Lab 9 on event driven programming, written by Sean Holden.
 * Thanks Sean!
 */
private void showHelp(){
	final String HelpText = "This is a two player game of Connect4. Every time you click to make a selection, it changes whose turn it is automatically.  "
			+ "To place a checker, click on the 'Select' button beneath the column you wish your checker to fall under."
			+ "You can start a new game at any time by clicking on File-> New Game. You can save the winner of the current game by selecting"
			+ "File-> Save Winner and inputting the name of the winning player. You can also display the last winner through File->Display Previous Winner."
			+ "Beware- the program will not alert you if someone has won, you must keep track of winners and losers yourself."
			+ "You can learn about the program through Help->About. Within the Help menu, you can also exit the program.";
	
	// Create the text label
	Label aboutLabel = new Label();
	aboutLabel.setWrapText(true);
	aboutLabel.setTextAlignment(TextAlignment.CENTER);
	aboutLabel.setFont(Font.font("Times New Roman", 16));
	aboutLabel.setText(HelpText);
	
	// Add the label to a StackPane
	StackPane pane = new StackPane();
	pane.getChildren().add(aboutLabel);
	
	// Create and display said the aforementioned pane in a new stage 	
	Scene scene = new Scene(pane, 500, 300);
	Stage stage = new Stage();
	stage.setScene(scene);
	stage.setTitle("Help");
	stage.setResizable(false);
	stage.show();
	}
}