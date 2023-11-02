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
		Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
		Scene sceneRoot = new Scene(root);
		primaryStage.setTitle("home");
		primaryStage.setScene(sceneRoot);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
