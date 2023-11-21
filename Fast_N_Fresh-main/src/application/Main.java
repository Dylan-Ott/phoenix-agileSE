package application;

import java.time.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Loading the Login Page
		LocalTime startMaint = LocalTime.parse("22:00:00");
		LocalTime stopMaint = LocalTime.parse("23:00:00");
		LocalTime now = LocalTime.now();
		
		Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
		if(now.isAfter(startMaint) && now.isBefore(stopMaint))
		{
			root = FXMLLoader.load(getClass().getResource("/view/Maintenance.fxml"));
		}
		Scene sceneRoot = new Scene(root);
		primaryStage.setTitle("home");
		primaryStage.setScene(sceneRoot);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
