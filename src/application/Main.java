package application;
	
import gui.Login;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Login Stage
		primaryStage.setTitle("Recipe Maker");
		Image icon = new Image(getClass().getResourceAsStream("/img/muffin.jpg"));
		primaryStage.setScene(Login.loginScene());
		primaryStage.getIcons().add(icon);
		
		//Entering login details
		
		
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
