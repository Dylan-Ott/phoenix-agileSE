package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.paint.Color;
import model.Cart;
import model.CartItem;
import model.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import model.DatabaseConnector;

public class SnacksController extends ProductBaseController {

	@FXML
	Label blueberryBarPrice;
	@FXML
	Label cheetosPrice;
	@FXML
	Label chipsPrice;
	@FXML
	Label chocolatePrice;
	@FXML
	Label onionDipPrice;
	@FXML
	Label popcornPrice;
	@FXML
	Label pringlesPrice;
	@FXML
	Label tostitosPrice;
	@FXML
	Label trailPrice;

	@FXML
	Spinner<Integer> blueberryBarSpinner;
	@FXML
	Spinner<Integer> cheetosSpinner;
	@FXML
	Spinner<Integer> chipsSpinner;
	@FXML
	Spinner<Integer> chocolateSpinner;
	@FXML
	Spinner<Integer> onionDipSpinner;
	@FXML
	Spinner<Integer> popcornSpinner;
	@FXML
	Spinner<Integer> pringlesSpinner;
	@FXML
	Spinner<Integer> tostitosSpinner;
	@FXML
	Spinner<Integer> trailSpinner;

	@FXML
	Button blueberryBarButton;
	@FXML
	Button cheetosButton;
	@FXML
	Button chipsButton;
	@FXML
	Button chocolateButton;
	@FXML
	Button onionDipButton;
	@FXML
	Button popcornButton;
	@FXML
	Button pringlesButton;
	@FXML
	Button tostitosButton;
	@FXML
	Button trailButton;

	public void initialize() {

//   	Fetch the Snacks Products information from the Snacks catalog in the Database	
		try {
			ResultSet rs = DatabaseConnector.getItemsFromCatalog("Snacks");
			while (rs.next()) {
				inventoryItems.put(rs.getString(1),
						new Product(rs.getString(1), rs.getString(2), rs.getInt(4), rs.getDouble(3), rs.getString(5)));
			}
			DatabaseConnector.closeStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

		blueberryBarPrice.setText("$" + inventoryItems.get("SNA001").getPrice() + "/ct");
		cheetosPrice.setText("$" + inventoryItems.get("SNA002").getPrice() + "/ct");
		chipsPrice.setText("$" + inventoryItems.get("SNA003").getPrice() + "/ct");
		chocolatePrice.setText("$" + inventoryItems.get("SNA004").getPrice() + "/ct");
		onionDipPrice.setText("$" + inventoryItems.get("SNA005").getPrice() + "/ct");
		popcornPrice.setText("$" + inventoryItems.get("SNA006").getPrice() + "/ct");
		pringlesPrice.setText("$" + inventoryItems.get("SNA007").getPrice() + "/ct");
		tostitosPrice.setText("$" + inventoryItems.get("SNA008").getPrice() + "/ct");
		trailPrice.setText("$" + inventoryItems.get("SNA009").getPrice() + "/ct");

		blueberryBarSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("SNA001").getQuantity(), 0));

		cheetosSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("SNA002").getQuantity(), 0));

		chipsSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("SNA003").getQuantity(), 0));

		chocolateSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("SNA004").getQuantity(), 0));

		onionDipSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("SNA005").getQuantity(), 0));

		popcornSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("SNA006").getQuantity(), 0));

		pringlesSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("SNA007").getQuantity(), 0));

		tostitosSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("SNA008").getQuantity(), 0));

		trailSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("SNA009").getQuantity(), 0));

		if (inventoryItems.get("SNA001").getQuantity() == 0) {
			setOutOfStockField(blueberryBarPrice, blueberryBarSpinner, blueberryBarButton);
		}
		if (inventoryItems.get("SNA002").getQuantity() == 0) {
			setOutOfStockField(cheetosPrice, cheetosSpinner, cheetosButton);
		}
		if (inventoryItems.get("SNA003").getQuantity() == 0) {
			setOutOfStockField(chipsPrice, chipsSpinner, chipsButton);
		}
		if (inventoryItems.get("SNA004").getQuantity() == 0) {
			setOutOfStockField(chocolatePrice, chocolateSpinner, chocolateButton);
		}
		if (inventoryItems.get("SNA005").getQuantity() == 0) {
			setOutOfStockField(onionDipPrice, onionDipSpinner, onionDipButton);
		}
		if (inventoryItems.get("SNA006").getQuantity() == 0) {
			setOutOfStockField(popcornPrice, popcornSpinner, popcornButton);
		}
		if (inventoryItems.get("SNA007").getQuantity() == 0) {
			setOutOfStockField(pringlesPrice, pringlesSpinner, pringlesButton);
		}
		if (inventoryItems.get("SNA008").getQuantity() == 0) {
			setOutOfStockField(tostitosPrice, tostitosSpinner, tostitosButton);
		}
		if (inventoryItems.get("SNA009").getQuantity() == 0) {
			setOutOfStockField(trailPrice, trailSpinner, trailButton);
		}

	}

//	Add selected products to the cart list
	@FXML
	private void addToCart(ActionEvent event) {

		if (((Button) event.getTarget()).getId().toString().equals("blueberryBarButton")) {
			CartItem ci = new CartItem("SNA001", inventoryItems.get("SNA001").getProductName(),
					(Integer) blueberryBarSpinner.getValue(), inventoryItems.get("SNA001").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("cheetosButton")) {
			CartItem ci = new CartItem("SNA002", inventoryItems.get("SNA002").getProductName(),
					(Integer) cheetosSpinner.getValue(), inventoryItems.get("SNA002").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("chipsButton")) {
			CartItem ci = new CartItem("SNA003", inventoryItems.get("SNA003").getProductName(),
					(Integer) chipsSpinner.getValue(), inventoryItems.get("SNA003").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("chocolateButton")) {
			CartItem ci = new CartItem("SNA004", inventoryItems.get("SNA004").getProductName(),
					(Integer) chocolateSpinner.getValue(), inventoryItems.get("SNA004").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("onionDipButton")) {
			CartItem ci = new CartItem("SNA005", inventoryItems.get("SNA005").getProductName(),
					(Integer) onionDipSpinner.getValue(), inventoryItems.get("SNA005").getPrice());

			cart.addProduct(ci);
		}

		if (((Button) event.getTarget()).getId().toString().equals("popcornButton")) {
			CartItem ci = new CartItem("SNA006", inventoryItems.get("SNA006").getProductName(),
					(Integer) popcornSpinner.getValue(), inventoryItems.get("SNA006").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("pringlesButton")) {
			CartItem ci = new CartItem("SNA007", inventoryItems.get("SNA007").getProductName(),
					(Integer) pringlesSpinner.getValue(), inventoryItems.get("SNA007").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("tostitosButton")) {
			CartItem ci = new CartItem("SNA008", inventoryItems.get("SNA008").getProductName(),
					(Integer) tostitosSpinner.getValue(), inventoryItems.get("SNA008").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("trailButton")) {
			CartItem ci = new CartItem("SNA009", inventoryItems.get("SNA009").getProductName(),
					(Integer) trailSpinner.getValue(), inventoryItems.get("SNA009").getPrice());

			cart.addProduct(ci);

		}

	}

	// Traverse back to the Catalog Page
	@FXML
	private void backToCatalog(ActionEvent event) {

		ScreenController.goToCatalogPage(event);
	}

	// Go to the Order Summary Page
	@FXML
	private void goToCart(ActionEvent event) {

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
