package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.paint.Color;
import model.CartItem;
import model.Product;
import java.sql.ResultSet;
import model.DatabaseConnector;

public class VegetablesController extends ProductBaseController {

	@FXML
	Label bellPepperPrice;
	@FXML
	Label carrotPrice;
	@FXML
	Label cauliflowerPrice;
	@FXML
	Label mushroomsPrice;
	@FXML
	Label onionsPrice;
	@FXML
	Label potatoesPrice;
	@FXML
	Label scallionsPrice;
	@FXML
	Label spinachPrice;
	@FXML
	Label tomatoesPrice;

	@FXML
	Spinner<Integer> bellPepperSpinner;
	@FXML
	Spinner<Integer> carrotSpinner;
	@FXML
	Spinner<Integer> cauliflowerSpinner;
	@FXML
	Spinner<Integer> mushroomsSpinner;
	@FXML
	Spinner<Integer> onionsSpinner;
	@FXML
	Spinner<Integer> potatoesSpinner;
	@FXML
	Spinner<Integer> scallionsSpinner;
	@FXML
	Spinner<Integer> spinachSpinner;
	@FXML
	Spinner<Integer> tomatoesSpinner;

	@FXML
	Button bellPepperButton;
	@FXML
	Button carrotButton;
	@FXML
	Button cauliflowerButton;
	@FXML
	Button mushroomsButton;
	@FXML
	Button onionsButton;
	@FXML
	Button potatoesButton;
	@FXML
	Button scallionsButton;
	@FXML
	Button spinachButton;
	@FXML
	Button tomatoesButton;

	public void initialize() {

//    	Fetch the Vegetable Products information from the Vegetables catalog in the Database
		try {
			ResultSet rs = DatabaseConnector.getItemsFromCatalog("Vegetables");
			while (rs.next()) {
				inventoryItems.put(rs.getString(1),
						new Product(rs.getString(1), rs.getString(2), rs.getInt(4), rs.getDouble(3), rs.getString(5)));
			}
			DatabaseConnector.closeStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

		bellPepperPrice.setText("$" + inventoryItems.get("VEG001").getPrice() + "/ct");
		carrotPrice.setText("$" + inventoryItems.get("VEG002").getPrice() + "/lb");
		cauliflowerPrice.setText("$" + inventoryItems.get("VEG003").getPrice() + "/ct");
		mushroomsPrice.setText("$" + inventoryItems.get("VEG004").getPrice() + "/lb");
		onionsPrice.setText("$" + inventoryItems.get("VEG005").getPrice() + "/lb");
		potatoesPrice.setText("$" + inventoryItems.get("VEG006").getPrice() + "/lb");
		scallionsPrice.setText("$" + inventoryItems.get("VEG007").getPrice() + "/lb");
		spinachPrice.setText("$" + inventoryItems.get("VEG008").getPrice() + "/bunch");
		tomatoesPrice.setText("$" + inventoryItems.get("VEG009").getPrice() + "/lb");

		bellPepperSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("VEG001").getQuantity(), 0));

		carrotSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("VEG002").getQuantity(), 0));

		cauliflowerSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("VEG003").getQuantity(), 0));

		mushroomsSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("VEG004").getQuantity(), 0));

		onionsSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("VEG005").getQuantity(), 0));

		potatoesSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("VEG006").getQuantity(), 0));

		scallionsSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("VEG007").getQuantity(), 0));

		spinachSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("VEG008").getQuantity(), 0));

		tomatoesSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("VEG009").getQuantity(), 0));

		if (inventoryItems.get("VEG001").getQuantity() == 0) {
			setOutOfStockField(bellPepperPrice, bellPepperSpinner, bellPepperButton);
		}
		if (inventoryItems.get("VEG002").getQuantity() == 0) {
			setOutOfStockField(carrotPrice, carrotSpinner, carrotButton);
		}
		if (inventoryItems.get("VEG003").getQuantity() == 0) {
			setOutOfStockField(cauliflowerPrice, cauliflowerSpinner, cauliflowerButton);
		}
		if (inventoryItems.get("VEG004").getQuantity() == 0) {
			setOutOfStockField(mushroomsPrice, mushroomsSpinner, mushroomsButton);
		}
		if (inventoryItems.get("VEG005").getQuantity() == 0) {
			setOutOfStockField(onionsPrice, onionsSpinner, onionsButton);
		}
		if (inventoryItems.get("VEG006").getQuantity() == 0) {
			setOutOfStockField(potatoesPrice, potatoesSpinner, potatoesButton);
		}
		if (inventoryItems.get("VEG007").getQuantity() == 0) {
			setOutOfStockField(scallionsPrice, scallionsSpinner, scallionsButton);
		}
		if (inventoryItems.get("VEG008").getQuantity() == 0) {
			setOutOfStockField(spinachPrice, spinachSpinner, spinachButton);
		}
		if (inventoryItems.get("VEG009").getQuantity() == 0) {
			setOutOfStockField(tomatoesPrice, tomatoesSpinner, tomatoesButton);
		}

	}

	// Add selected products to the cart list
	@FXML
	private void addToCart(javafx.event.ActionEvent event) {

		if (((Button) event.getTarget()).getId().toString().equals("bellPepperButton")) {
			CartItem ci = new CartItem("VEG001", inventoryItems.get("VEG001").getProductName(),
					(Integer) bellPepperSpinner.getValue(), inventoryItems.get("VEG001").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("carrotButton")) {
			CartItem ci = new CartItem("VEG002", inventoryItems.get("VEG002").getProductName(),
					(Integer) carrotSpinner.getValue(), inventoryItems.get("VEG002").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("cauliflowerButton")) {
			CartItem ci = new CartItem("VEG003", inventoryItems.get("VEG003").getProductName(),
					(Integer) cauliflowerSpinner.getValue(), inventoryItems.get("VEG003").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("mushroomsButton")) {
			CartItem ci = new CartItem("VEG004", inventoryItems.get("VEG004").getProductName(),
					(Integer) mushroomsSpinner.getValue(), inventoryItems.get("VEG004").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("onionsButton")) {
			CartItem ci = new CartItem("VEG005", inventoryItems.get("VEG005").getProductName(),
					(Integer) onionsSpinner.getValue(), inventoryItems.get("VEG005").getPrice());

			cart.addProduct(ci);
		}

		if (((Button) event.getTarget()).getId().toString().equals("potatoesButton")) {
			CartItem ci = new CartItem("VEG006", inventoryItems.get("VEG006").getProductName(),
					(Integer) potatoesSpinner.getValue(), inventoryItems.get("VEG006").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("scallionsButton")) {
			CartItem ci = new CartItem("VEG007", inventoryItems.get("VEG007").getProductName(),
					(Integer) scallionsSpinner.getValue(), inventoryItems.get("VEG007").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("spinachButton")) {
			CartItem ci = new CartItem("VEG008", inventoryItems.get("VEG008").getProductName(),
					(Integer) spinachSpinner.getValue(), inventoryItems.get("VEG008").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("tomatoesButton")) {
			CartItem ci = new CartItem("VEG009", inventoryItems.get("VEG009").getProductName(),
					(Integer) tomatoesSpinner.getValue(), inventoryItems.get("VEG009").getPrice());

			cart.addProduct(ci);

		}

	}

	// Traverse back to the Catalog Page
	@FXML
	private void backToCatalog(javafx.event.ActionEvent event) {
		ScreenController.goToCatalogPage(event);
	}

	// Go to the Order Summary Page
	@FXML
	private void goToCart(javafx.event.ActionEvent event) {
		ScreenController.goToCartPage(event);
	}

	// Logout of the application and return to the Login Page
	@FXML
	void goToLogin(ActionEvent event) {
		logOff();
		ScreenController.goToLoginPage(event);
	}

	// Disable the product item on the screen if it goes out of stock 
	private void setOutOfStockField(Label errorLabel, Spinner spinner, Button bt) {
		errorLabel.setText("Out of Stock");
		errorLabel.setTextFill(Color.RED);
		spinner.setDisable(true);
		bt.setDisable(true);
	}

}
