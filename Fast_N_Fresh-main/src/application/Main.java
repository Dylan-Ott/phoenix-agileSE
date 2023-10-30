package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Loading the Login Page
		Parent rootLogin = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		Scene sceneLogin = new Scene(rootLogin);
		primaryStage.setTitle("Login Page");
		primaryStage.setScene(sceneLogin);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
