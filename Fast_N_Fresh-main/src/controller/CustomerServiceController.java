package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CustomerServiceController extends ProductBaseController {
	
	@FXML
	private TextField tfName;
	
	@FXML
	private TextField tfEmail;
	
	@FXML
	private TextField tfMessage;
	
	@FXML
	private Label lblName;
	
	@FXML
	private Label lblEmail;
	
	@FXML
	private Label lblMessage;

	@FXML
	private Button btnSubmit;

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
	}

	// Go to the User Registration Page
	@FXML
	public void goToHome(ActionEvent event) {

		ScreenController.goToHomePage(event);
	}

}