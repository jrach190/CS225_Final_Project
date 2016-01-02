import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class FileRW {
	
	//Code to bring up the display window to allow users to save the current winner
	public void showSave(){
		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(5);
		final TextField name = new TextField();
		name.setPromptText("Winner");
		grid.getChildren().add(name);
		name.setPrefColumnCount(10);
		GridPane.setConstraints(name, 0, 0);
		
		//Define the save button
		Button save = new Button("Save the Current Winner");
		GridPane.setConstraints(save, 1, 0);
		grid.getChildren().add(save);
		
		//Make the save button actually save to a file
		save.setOnAction(e -> {
			writeFileMethod(name.getText());
		});
	
		//Make the scene for the new window to open
		Scene scene = new Scene(grid, 550, 100);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Save This Winner");
		stage.setResizable(true);
		stage.show();
	}
	
	//Code to bring up the display window to allow users to view the previous winner
	public void showPastWinner(){
		//Define the file name to be read
		String filename = "winner.txt";
		//Define the string to store the name in the file
		String line = null;
		
		//Try catch throw to handle file not found and IO exceptions
		try{
			
			FileReader fileread = new FileReader(filename);
			BufferedReader buffread = new BufferedReader(fileread); 
			line = buffread.readLine();
			buffread.close();
			
		}catch(FileNotFoundException e){
			
			//Displays a 404 error message stating the file with past winners cannot be found
			showError();
			
		}catch(IOException e){
			
			//prints the stack trace for the IO Exception if one occurs
			e.printStackTrace();
		}
		
		// Create the text label
		Label aboutLabel = new Label();
		aboutLabel.setWrapText(true);
		aboutLabel.setTextAlignment(TextAlignment.CENTER);
		aboutLabel.setFont(Font.font("Times New Roman", 16));
		aboutLabel.setText("The last winner was " + line + ".");
		
		// Add the label to a StackPane
		StackPane pane = new StackPane();
		pane.getChildren().add(aboutLabel);
		
		// Create and display said the aforementioned pane in a new stage 	
		Scene scene = new Scene(pane, 200, 200);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Previous Winner");
		stage.setResizable(true);
		stage.show();
		
	}
	
	//Method that writes the name of the winner to the file for later reading
	private void writeFileMethod(String winname){
		File winner = new File("winner.txt");
		//Try catch throw to handle an IO exception should an error occur while writing to the file
		try {
			
			//Write name of winner to the file
			winner.createNewFile();
			BufferedWriter buffwrite = new BufferedWriter(new FileWriter(winner, false));
			buffwrite.write(winname);
			buffwrite.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}	
	
	//Method to display new window of error if file with the last winner is not found.
	private void showError(){

		final String aboutError = "The file containing the name of the last winner was not found.";

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
		stage.setTitle("Error 404: File Not Found");
		stage.setResizable(false);
		stage.show();
	}
	
}
