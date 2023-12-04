package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.paint.Color;
import model.DatabaseConnector;
import java.util.regex.*;

public class RegistrationController {

	@FXML
	private Button backToLogin;

	@FXML
	private Button btnSubmit;

	@FXML
	private TextField tfContact;

	@FXML
	private TextField tfEmail;

	@FXML
	private TextField tfName;

	@FXML
	private PasswordField tfUserPassword;

	@FXML
	private Label errorContact;

	@FXML
	private Label errorEmail;

	@FXML
	private Label errorNameField;

	@FXML
	private Label errorPassword;

	@FXML
	private Label lblName;

	@FXML
	private Label lblContact;

	@FXML
	private Label lblEmail;


	@FXML
	private Label lblPassword;


	// Validate the name, contact, email, and password fields to be valid before registration
	// Registers user successfully in the Database if validations are successful
	// Returns by Highlighting the invalid fields if validation fails
	@FXML
	void registerUser(ActionEvent event) {
		// Clearing the Error Labels and disable the hightlights for error field 
		clearFieldsStyle();
		maskErrorLabels();
		if (!isNameValid()) {
			tfName.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
			new animatefx.animation.Shake(tfName).play();
			errorNameField.setVisible(true);
			this.showDialog("Name should not be empty");
			return;
		}
		if (!isContactValid()) {
			tfContact.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
			new animatefx.animation.Shake(tfContact).play();
			errorContact.setVisible(true);
			this.showDialog("Phone number should be valid");
			return;
		}
		if (!isValidEmail()) {
			tfEmail.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
			new animatefx.animation.Shake(tfEmail).play();
			errorEmail.setVisible(true);
			this.showDialog("Please Enter valid Email\nValid emails are gmail, hotmail, yahoo, aol, or icloud.");
			return;
		}
		if (!isValidPassword()) {
			tfUserPassword.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
			new animatefx.animation.Shake(tfUserPassword).play();
			errorPassword.setVisible(true);
			this.showDialog("Password must contain:\nAt least 1 lowercase character\nAt least 1 uppercase character\nAt least 1 number\nAt least 1 special character");
			return;
		}
		String query = "insert into user_info values (?,?,?,?,?)";
		PreparedStatement preparedStmt;
		try {
			Connection conn = DatabaseConnector.getInstance();
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, tfEmail.getText());
			preparedStmt.setString(2, tfName.getText());
			preparedStmt.setString(3, tfContact.getText());
			preparedStmt.setString(4, tfEmail.getText());
			preparedStmt.setString(5, tfUserPassword.getText());
			preparedStmt.executeUpdate();
			preparedStmt.close();
			showDialog("User Registration successful!");
			clearFieldValues();
			ScreenController.goToLoginPage(event);
		} catch (Exception e) {
			System.out.println("Error Message: " + e.getMessage());
			e.printStackTrace();
		}

	}

	// Pops-up the window upon field validation is success or failure
	public void showDialog(String errorMessage) {
		Dialog<String> dialog = new Dialog<String>();
		dialog.setTitle("Registration");
		ButtonType type = new ButtonType("OK", ButtonData.OK_DONE);
		dialog.setContentText(errorMessage);
		dialog.getDialogPane().getButtonTypes().add(type);
		dialog.showAndWait();
	}

	public boolean isNameValid() {
		String name = tfName.getText().trim();
		if (name.length() > 0) {
			return true;
		}
		return false;
	}

	public boolean isContactValid() {
		String contact = tfContact.getText();
		// regex for checking if number is 10digits or not
		Pattern pattern = Pattern.compile("^\\d{10}$");
		Matcher matcher = pattern.matcher(contact);
		return matcher.matches();
	}

	public boolean isValidEmail() {
		String email = tfEmail.getText();
		// Using the email regex pattern to validate email field
		String emailRegex = "^[a-zA-Z0-9+_.-]+@(gmail.com|yahoo.com|aol.com|icloud.com)+$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public boolean isValidPassword() {
		String password = tfUserPassword.getText();
	    int digit=0;
	    int special=0;
	    int upCount=0;
	    int loCount=0;
	    
        for(int i =0;i<password.length();i++)
        {
            char c = password.charAt(i);
            
            if(Character.isUpperCase(c))
            {
                upCount++;
            }
            if(Character.isLowerCase(c))
            {
                loCount++;
            }
            if(Character.isDigit(c))
            {
                digit++;
            }
            if(c>=33 && c<=46||c>=58 && c<=64 || c>=123 && c<=126)
            {
                special++;
            }
        }
        if(special>=1 && loCount>=1 && upCount>=1 && digit>=1 && password.length() >= 8){
        	return true;
        }
        else
        {
        	return false;
        }
	}

	// Clear the field styles upon successful validation or on loading time
	private void clearFieldsStyle() {
		tfName.setStyle(null);
		tfContact.setStyle(null);
		tfEmail.setStyle(null);
		tfUserPassword.setStyle(null);
	}

	// Mask the Error Labels on load everytime
	private void maskErrorLabels() {
		errorContact.setVisible(false);
		errorEmail.setVisible(false);
		errorNameField.setVisible(false);
		errorPassword.setVisible(false);

	}

	// Clear the field labels on load everytime
	private void clearFieldValues() {
		tfName.setText(null);
		tfContact.setText(null);
		tfEmail.setText(null);
		tfUserPassword.setText(null);

	}

	// Traverse to the Login Page
	@FXML
	void goToLogin(ActionEvent event) {
		ScreenController.goToLoginPage(event);
	}

}