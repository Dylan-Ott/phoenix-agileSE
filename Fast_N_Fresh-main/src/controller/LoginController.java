package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.DatabaseConnector;

public class LoginController extends ProductBaseController {
	
	@FXML
	private TextField tfUsername;
	
	@FXML
	private PasswordField tfPassword;
	
	@FXML
	private Label lblPassword;
	
	@FXML
	private Label lblUsername;

	@FXML
	private Button btnRegister;

	@FXML
	private Button btnSignIn;
	
	@FXML
    private Label errorPassLabel;

    @FXML
    private Label errorUserLabel;
    
    @Override
	void initialize() {
		tfUsername.setText(null);
		tfPassword.setText(null);
	}

    // Validate the user credentials and go to Catalog Page
	@FXML
	public void goToCatalog(ActionEvent event) {
		try {
			Connection conn = DatabaseConnector.getInstance();
			Statement st = conn.createStatement();
			String query = "select password, full_name from user_info where user_name = '" + tfUsername.getText() + "'";
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				String storedPassword = rs.getString(1);
				tfUsername.setStyle(null);
				errorUserLabel.setVisible(false);
				if (storedPassword.equals(tfPassword.getText())) {
					// Pop up login successful
					userId = tfUsername.getText();
					userName = rs.getString(2);
					tfPassword.setBorder(null);
					tfPassword.setStyle(null);
					errorPassLabel.setVisible(false);
					Dialog<String> dialog = new Dialog<String>();
					dialog.setTitle("Login");
					ButtonType type = new ButtonType("OK", ButtonData.OK_DONE);
					dialog.setContentText("Login successful!");
					dialog.getDialogPane().getButtonTypes().add(type);
					dialog.showAndWait();
					ScreenController.goToCatalogPage(event);
				} else {
					// Throw error saying Invalid Password.
					tfPassword.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
					new animatefx.animation.Shake(tfPassword).play();
					errorPassLabel.setVisible(true);
					Dialog<String> dialog = new Dialog<String>();
					dialog.setTitle("Login");
					ButtonType type = new ButtonType("OK", ButtonData.OK_DONE);
					dialog.setContentText("Incorrect Password");
					dialog.getDialogPane().getButtonTypes().add(type);
					dialog.showAndWait();
				
					System.out.println("Incorrect Password");
				}
			} else {
				tfUsername.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
				new animatefx.animation.Shake(tfUsername).play();
				errorUserLabel.setVisible(true);
				Dialog<String> dialog = new Dialog<String>();
				dialog.setTitle("Login");
				ButtonType type = new ButtonType("OK", ButtonData.OK_DONE);
				dialog.setContentText("Incorrect Username");
				dialog.getDialogPane().getButtonTypes().add(type);
				dialog.showAndWait();
				
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Go to the User Registration Page
	@FXML
	public void goToRegistration(ActionEvent event) {

		ScreenController.goToRegistrationPage(event);
	}

}
