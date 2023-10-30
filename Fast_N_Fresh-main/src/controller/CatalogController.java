package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
//import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class CatalogController extends ProductBaseController {

	@FXML
	private Label lblUserName;

	// CatalogController object is used to traverse across the Screens and Welcome the User post successful Login
	@Override
	public void initialize() {
		lblUserName.setText("Hi " + userName + ", We are Open!!");
	}

	@FXML
	public void goToFruits(ActionEvent event) {

		ScreenController.goToFruitsPage(event);
	}

	@FXML
	public void goToVegetables(ActionEvent event) {

		ScreenController.goToVegetablesPage(event);
	}

	@FXML
	public void goToSnacks(ActionEvent event) {

		ScreenController.goToSnacksPage(event);
	}

	@FXML
	public void goToDairy(ActionEvent event) {

		ScreenController.goToDairyPage(event);
	}
	
	@FXML
	public void goToBeverages(ActionEvent event) {

		ScreenController.goToBeveragesPage(event);
	}

	@FXML
	public void goToMeat(ActionEvent event) {

		ScreenController.goToMeatPage(event);
	}

	@FXML
	void goToLogin(ActionEvent event) {
		logOff();
		ScreenController.goToLoginPage(event);
	}

	@FXML
	public void goToCatalogExpenses(ActionEvent event) {

		ScreenController.goToCatalogExpensesPage(event);
	}

	@FXML
	void goToOrderHistory(ActionEvent event) {
		ScreenController.goToOrderHistoryPage(event);
	}

}
