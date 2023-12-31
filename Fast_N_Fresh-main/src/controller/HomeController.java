package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController extends ProductBaseController {

	@FXML
	private Button btnRegister;

	@FXML
	private Button btnSignIn;
	
	@FXML
	private Button btnCatalog;
	
	@FXML
	private Button btnCustomerService;
    
    @Override
	void initialize() {
	}

    // Go to Catalog Page
	@FXML
	public void goToCatalog(ActionEvent event) {
		
		ScreenController.goToCatalogPage(event);		
	}

	// Go to the User Registration Page
	@FXML
	public void goToRegistration(ActionEvent event) {

		ScreenController.goToRegistrationPage(event);
	}
	
	// Go to the User Login Page
	@FXML
	public void goToLogin(ActionEvent event) {

		ScreenController.goToLoginPage(event);
	}
	
	@FXML
	public void goToCustomerService(ActionEvent event) {

		ScreenController.goToCustomerServicePage(event);
	}

}
