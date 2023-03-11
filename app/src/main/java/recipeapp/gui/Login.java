package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Login {

	public static Scene loginScene() {
		// PART 1 making grid
		GridPane grid = new GridPane();
		// grid default position is the top left his puts in the center of scene
		grid.setAlignment(Pos.CENTER);
		// gap between the rows and coloumns on the grid
		grid.setHgap(10);
		grid.setVgap(10);
		//pads the edges of the grid from the scene
		grid.setPadding(new Insets(25,25,25,25));
		
		//PART 2: Text and labels
		Label userName = new Label("User Name:");
		TextField  userTF = new TextField();
		grid.add(userName, 0, 1);
		grid.add(userTF, 1, 1);
		Label pass = new Label("Password:");
		PasswordField passfield = new PasswordField();
		grid.add(pass, 0, 2);
		grid.add(passfield, 1, 2);
		
		Button login = new Button("Login");
		grid.add(login, 2, 3);
		
		
		Scene scene = new Scene(grid, 400, 375);
		return scene;
	}
}
