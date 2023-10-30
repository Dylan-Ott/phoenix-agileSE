package controller;

import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.paint.Color;
import model.CartItem;
import model.DatabaseConnector;
import model.Product;

public class BeveragesController extends ProductBaseController {
	@FXML Label waterPrice;
	@FXML Label celsiusPrice;
	@FXML Label koolAidPrice;
	@FXML Label fruitPunchPrice;
	@FXML Label gatoradePrice;
	@FXML Label orangeJuicePrice;
	@FXML Label pepsiPrice;
	@FXML Label redBullPrice;
	@FXML Label seltzerPrice;

	@FXML Spinner<Integer> waterSpinner;
	@FXML Spinner<Integer> celsiusSpinner;
	@FXML Spinner<Integer> koolAidSpinner;
	@FXML Spinner<Integer> fruitPunchSpinner;
	@FXML Spinner<Integer> gatoradeSpinner;
	@FXML Spinner<Integer> orangeJuiceSpinner;
	@FXML Spinner<Integer> pepsiSpinner;
	@FXML Spinner<Integer> redBullSpinner;
	@FXML Spinner<Integer> seltzerSpinner;

	@FXML Button waterButton;
	@FXML Button celsiusButton;
	@FXML Button koolAidButton;
	@FXML Button fruitPunchButton;
	@FXML Button gatoradeButton;
	@FXML Button orangeJuiceButton;
	@FXML Button pepsiButton;
	@FXML Button redBullButton;
	@FXML Button seltzerButton;

	public void initialize() {
//		Fetch the Beverages Products information from the Beverages catalog in the Database
		try {
			ResultSet rs = DatabaseConnector.getItemsFromCatalog("Beverages");
			while (rs.next()) {
				inventoryItems.put(rs.getString(1),
						new Product(rs.getString(1), rs.getString(2), rs.getInt(4), rs.getDouble(3), rs.getString(5)));
			}
			DatabaseConnector.closeStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

		waterPrice.setText("$" + inventoryItems.get("BEV001").getPrice());
		celsiusPrice.setText("$" + inventoryItems.get("BEV002").getPrice());
		koolAidPrice.setText("$" + inventoryItems.get("BEV003").getPrice());
		fruitPunchPrice.setText("$" + inventoryItems.get("BEV004").getPrice());
		gatoradePrice.setText("$" + inventoryItems.get("BEV005").getPrice());
		orangeJuicePrice.setText("$" + inventoryItems.get("BEV006").getPrice());
		pepsiPrice.setText("$" + inventoryItems.get("BEV007").getPrice());
		redBullPrice.setText("$" + inventoryItems.get("BEV008").getPrice());
		seltzerPrice.setText("$" + inventoryItems.get("BEV009").getPrice());

		waterSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BEV001").getQuantity(), 0));

		celsiusSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BEV002").getQuantity(), 0));

		koolAidSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BEV003").getQuantity(), 0));

		fruitPunchSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BEV004").getQuantity(), 0));

		gatoradeSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BEV005").getQuantity(), 0));

		orangeJuiceSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BEV006").getQuantity(), 0));

		pepsiSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BEV007").getQuantity(), 0));

		redBullSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BEV008").getQuantity(), 0));

		seltzerSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BEV009").getQuantity(), 0));
		
		if(inventoryItems.get("BEV001").getQuantity() == 0) {
			setOutOfStockField(waterPrice, waterSpinner, waterButton);
		}
		if(inventoryItems.get("BEV002").getQuantity() == 0) {
			setOutOfStockField(celsiusPrice, celsiusSpinner, celsiusButton);
		}
		if(inventoryItems.get("BEV003").getQuantity() == 0) {
			setOutOfStockField(koolAidPrice, koolAidSpinner, koolAidButton);
		}
		if(inventoryItems.get("BEV004").getQuantity() == 0) {
			setOutOfStockField(fruitPunchPrice, fruitPunchSpinner, fruitPunchButton);
		}
		if(inventoryItems.get("BEV005").getQuantity() == 0) {
			setOutOfStockField(gatoradePrice, gatoradeSpinner, gatoradeButton);
		}
		if(inventoryItems.get("BEV006").getQuantity() == 0) {
			setOutOfStockField(orangeJuicePrice, orangeJuiceSpinner, orangeJuiceButton);
		}
		if(inventoryItems.get("BEV007").getQuantity() == 0) {
			setOutOfStockField(pepsiPrice, pepsiSpinner, pepsiButton);
		}
		if(inventoryItems.get("BEV008").getQuantity() == 0) {
			setOutOfStockField(redBullPrice, redBullSpinner, redBullButton);
		}
		if(inventoryItems.get("BEV009").getQuantity() == 0) {
			setOutOfStockField(seltzerPrice, seltzerSpinner, seltzerButton);
		}

	}

	// Add selected products to the cart list
	@FXML
	private void addToCart(javafx.event.ActionEvent event) {

		if (((Button) event.getTarget()).getId().toString().equals("waterButton")) {
			CartItem ci = new CartItem("BEV001", "Water", (Integer) waterSpinner.getValue(),
					inventoryItems.get("BEV001").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("celsiusButton")) {
			CartItem ci = new CartItem("BEV002", "Celsius", (Integer) celsiusSpinner.getValue(),
					inventoryItems.get("BEV002").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("koolAidButton")) {
			CartItem ci = new CartItem("BEV003", "KoolAid", (Integer) koolAidSpinner.getValue(),
					inventoryItems.get("BEV003").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("fruitPunchButton")) {
			CartItem ci = new CartItem("BEV004", "Fruit Punch", (Integer) fruitPunchSpinner.getValue(),
					inventoryItems.get("BEV004").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("gatoradeButton")) {
			CartItem ci = new CartItem("BEV005", "Gatorade", (Integer)gatoradeSpinner.getValue(),
					inventoryItems.get("BEV005").getPrice());

			cart.addProduct(ci);
		}

		if (((Button) event.getTarget()).getId().toString().equals("orangeJuiceButton")) {
			CartItem ci = new CartItem("BEV006", "Orange Juice", (Integer) orangeJuiceSpinner.getValue(),
					inventoryItems.get("BEV006").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("pepsiButton")) {
			CartItem ci = new CartItem("BEV007", "Pepsi", (Integer) pepsiSpinner.getValue(),
					inventoryItems.get("BEV007").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("redBullButton")) {
			CartItem ci = new CartItem("BEV008", "Red Bull", (Integer) redBullSpinner.getValue(),
					inventoryItems.get("BEV008").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("seltzerButton")) {
			CartItem ci = new CartItem("BEV009", "Seltzer", (Integer) seltzerSpinner.getValue(),
					inventoryItems.get("BEV009").getPrice());

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
	private void setOutOfStockField(Label errorLabel, Spinner spinner, Button bt){
		errorLabel.setText("Out of Stock");
		errorLabel.setTextFill(Color.RED);
		spinner.setDisable(true);
		bt.setDisable(true);
	}

}
