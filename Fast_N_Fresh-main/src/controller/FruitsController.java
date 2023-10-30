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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import model.DatabaseConnector;

public class FruitsController extends ProductBaseController {

	@FXML
	Label applePrice;
	@FXML
	Label avocadoPrice;
	@FXML
	Label bananaPrice;
	@FXML
	Label blueberriesPrice;
	@FXML
	Label guavaPrice;
	@FXML
	Label orangePrice;
	@FXML
	Label plumPrice;
	@FXML
	Label strawberriesPrice;
	@FXML
	Label watermelonPrice;
	
	@FXML
	Spinner<Integer> appleSpinner;
	@FXML
	Spinner<Integer> avocadoSpinner;
	@FXML
	Spinner<Integer> bananaSpinner;
	@FXML
	Spinner<Integer> blueberriesSpinner;
	@FXML
	Spinner<Integer> guavaSpinner;
	@FXML
	Spinner<Integer> orangeSpinner;
	@FXML
	Spinner<Integer> plumSpinner;
	@FXML
	Spinner<Integer> strawberriesSpinner;
	@FXML
	Spinner<Integer> watermelonSpinner;

	@FXML
	Button appleButton;
	@FXML
	Button avocadoButton;
	@FXML
	Button bananaButton;
	@FXML
	Button blueberriesButton;
	@FXML
	Button guavaButton;
	@FXML
	Button orangeButton;
	@FXML
	Button plumButton;
	@FXML
	Button strawberriesButton;
	@FXML
	Button watermelonButton;

	@Override
	public void initialize() {

//    	Fetch the Fruit Products information from the Fruits catalog in the Database
		try {
			ResultSet rs = DatabaseConnector.getItemsFromCatalog("Fruits");
			while (rs.next()) {
				inventoryItems.put(rs.getString(1),
						new Product(rs.getString(1), rs.getString(2), rs.getInt(4), rs.getDouble(3), rs.getString(5)));
			}
			DatabaseConnector.closeStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		applePrice.setText("$" + inventoryItems.get("FRU001").getPrice() + "/lb");
		avocadoPrice.setText("$" + inventoryItems.get("FRU002").getPrice() + "/lb");
		bananaPrice.setText("$" + inventoryItems.get("FRU003").getPrice() + "/lb");
		blueberriesPrice.setText("$" + inventoryItems.get("FRU004").getPrice() + "/lb");
		guavaPrice.setText("$" + inventoryItems.get("FRU005").getPrice() + "/lb");
		orangePrice.setText("$" + inventoryItems.get("FRU006").getPrice() + "/ct");
		plumPrice.setText("$" + inventoryItems.get("FRU007").getPrice() + "/lb");
		strawberriesPrice.setText("$" + inventoryItems.get("FRU008").getPrice() + "/lb");
		watermelonPrice.setText("$" + inventoryItems.get("FRU009").getPrice() + "/ct");

		appleSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("FRU001").getQuantity(), 0));

		avocadoSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("FRU002").getQuantity(), 0));

		bananaSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("FRU003").getQuantity(), 0));

		blueberriesSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("FRU004").getQuantity(), 0));

		guavaSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("FRU005").getQuantity(), 0));

		orangeSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("FRU006").getQuantity(), 0));

		plumSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("FRU007").getQuantity(), 0));

		strawberriesSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("FRU008").getQuantity(), 0));

		watermelonSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("FRU009").getQuantity(), 0));
		
		
		if(inventoryItems.get("FRU001").getQuantity() == 0) {
			setOutOfStockField(applePrice, appleSpinner, appleButton);
		}
		if(inventoryItems.get("FRU002").getQuantity() == 0) {
			setOutOfStockField(avocadoPrice, avocadoSpinner, avocadoButton);
		}
		if(inventoryItems.get("FRU003").getQuantity() == 0) {
			setOutOfStockField(bananaPrice, bananaSpinner, bananaButton);
		}
		if(inventoryItems.get("FRU004").getQuantity() == 0) {
			setOutOfStockField(blueberriesPrice, blueberriesSpinner, blueberriesButton);
		}
		if(inventoryItems.get("FRU005").getQuantity() == 0) {
			setOutOfStockField(guavaPrice, guavaSpinner, guavaButton);
		}
		if(inventoryItems.get("FRU006").getQuantity() == 0) {
			setOutOfStockField(orangePrice,orangeSpinner, orangeButton);
		}
		if(inventoryItems.get("FRU007").getQuantity() == 0) {
			setOutOfStockField(plumPrice, plumSpinner, plumButton);
		}
		if(inventoryItems.get("FRU008").getQuantity() == 0) {
			setOutOfStockField(strawberriesPrice, strawberriesSpinner, strawberriesButton);
		}
		if(inventoryItems.get("FRU009").getQuantity() == 0) {
			setOutOfStockField(watermelonPrice, watermelonSpinner, watermelonButton);
		}
		
		
	}

	// Add selected products to the cart list
	@FXML
	private void addToCart(javafx.event.ActionEvent event) {

		if (((Button) event.getTarget()).getId().toString().equals("appleButton")) {
			CartItem ci = new CartItem("FRU001", inventoryItems.get("FRU001").getProductName(), 
					(Integer) appleSpinner.getValue(), inventoryItems.get("FRU001").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("avocadoButton")) {
			CartItem ci = new CartItem("FRU002", inventoryItems.get("FRU002").getProductName(), 
					(Integer) avocadoSpinner.getValue(), inventoryItems.get("FRU002").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("bananaButton")) {
			CartItem ci = new CartItem("FRU003", inventoryItems.get("FRU003").getProductName(), 
					(Integer) bananaSpinner.getValue(), inventoryItems.get("FRU003").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("blueberriesButton")) {
			CartItem ci = new CartItem("FRU004", inventoryItems.get("FRU004").getProductName(), 
					(Integer) blueberriesSpinner.getValue(), inventoryItems.get("FRU004").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("guavaButton")) {
			CartItem ci = new CartItem("FRU005", inventoryItems.get("FRU005").getProductName(), 
					(Integer) guavaSpinner.getValue(), inventoryItems.get("FRU005").getPrice());

			cart.addProduct(ci);
		}

		if (((Button) event.getTarget()).getId().toString().equals("orangeButton")) {
			CartItem ci = new CartItem("FRU006", inventoryItems.get("FRU006").getProductName(), 
					(Integer) orangeSpinner.getValue(), inventoryItems.get("FRU006").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("plumButton")) {
			CartItem ci = new CartItem("FRU007", inventoryItems.get("FRU007").getProductName(), 
					(Integer) plumSpinner.getValue(), inventoryItems.get("FRU007").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("strawberriesButton")) {
			CartItem ci = new CartItem("FRU008", inventoryItems.get("FRU008").getProductName(), 
					(Integer) strawberriesSpinner.getValue(), inventoryItems.get("FRU008").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("watermelonButton")) {
			CartItem ci = new CartItem("FRU009", inventoryItems.get("FRU009").getProductName(), 
					(Integer) watermelonSpinner.getValue(), inventoryItems.get("FRU009").getPrice());

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
