package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ButtonBar.ButtonData;

public class CustomerServiceController extends ProductBaseController {
	
	@FXML
	private TextField tfName;
	
	@FXML
	private TextField tfEmail;
	
	@FXML
	private TextArea tfMessage;
	
	@FXML
	private Label lblName;
	
	@FXML
	private Label lblEmail;
	
	@FXML
	private Label lblMessage;

	@FXML
	private Button btnSubmit;
	
	@FXML
	private Button btnHome;

    @Override
	void initialize() {
		tfName.setText(null);
		tfEmail.setText(null);
		tfMessage.setText(null);
	}

    // This will submit a customer service ticket to the DB
    // When customer service logs in, they will be able to see all current tickets in the DB
	@FXML
	public void submitServiceRequest(ActionEvent event) {
		//Do something...
		if (fieldsAreFilled())
		{
			Dialog<String> dialog = new Dialog<String>();
			// Setting the title
			dialog.setTitle("Request Submitted");
			ButtonType type = new ButtonType("OK", ButtonData.OK_DONE);
			// Setting the content of the dialog
			dialog.setContentText("Thank you for your feedback!\nWe'll get back to you as soon as possible.");
			// Adding buttons to the dialog pane
			dialog.getDialogPane().getButtonTypes().add(type);
			dialog.showAndWait();
			goToHome(event);
		}
		else
		{
			Dialog<String> dialog = new Dialog<String>();
			// Setting the title
			dialog.setTitle("Error");
			ButtonType type = new ButtonType("OK", ButtonData.OK_DONE);
			// Setting the content of the dialog
			dialog.setContentText("Please fill all fields before submitting!");
			// Adding buttons to the dialog pane
			dialog.getDialogPane().getButtonTypes().add(type);
			dialog.showAndWait();
		}
	}
	
	public boolean fieldsAreFilled()
	{
		String name = tfName.getText().trim();
		String email = tfEmail.getText().trim();
		String mess = tfMessage.getText().trim();
		if (name.length() > 0 && email.length() > 0 && mess.length() > 0) {
			return true;
		}
		return false;
	}

	// Go to the User Registration Page
	@FXML
	public void goToHome(ActionEvent event) {

		ScreenController.goToHomePage(event);
	}

}