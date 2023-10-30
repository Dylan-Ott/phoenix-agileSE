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

public class DairyController extends ProductBaseController {

	@FXML Label milkPrice;
	@FXML Label butterPrice;
	@FXML Label heavyCreamPrice;
	@FXML Label iceCreamPrice;
	@FXML Label chocolateMilkPrice;
	@FXML Label sourCreamPrice;
	@FXML Label stringCheesePrice;
	@FXML Label mozzarellaCheesePrice;
	@FXML Label yogurtPrice;

	@FXML Spinner<Integer> milkSpinner;
	@FXML Spinner<Integer> butterSpinner;
	@FXML Spinner<Integer> heavyCreamSpinner;
	@FXML Spinner<Integer> iceCreamSpinner;
	@FXML Spinner<Integer>chocolateMilkSpinner;
	@FXML Spinner<Integer> sourCreamSpinner;
	@FXML Spinner<Integer> stringCheeseSpinner;
	@FXML Spinner<Integer> mozzarellaCheeseSpinner;
	@FXML Spinner<Integer> yogurtSpinner;

	@FXML Button milkButton;
	@FXML Button butterButton;
	@FXML Button heavyCreamButton;
	@FXML Button iceCreamButton;
	@FXML Button chocolateMilkButton;
	@FXML Button sourCreamButton;
	@FXML Button stringCheeseButton;
	@FXML Button mozzarellaCheeseButton;
	@FXML Button yogurtButton;

	@Override
	public void initialize() {
		// Fetch the Fruit Products information from the Fruits catalog in the Database
		try {
			ResultSet rs = DatabaseConnector.getItemsFromCatalog("Dairy");
			while (rs.next()) {
				inventoryItems.put(rs.getString(1),
						new Product(rs.getString(1), rs.getString(2), rs.getInt(4), rs.getDouble(3), rs.getString(5)));
			}
			DatabaseConnector.closeStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

		milkPrice.setText("$" + inventoryItems.get("DAI001").getPrice());
		butterPrice.setText("$" + inventoryItems.get("DAI002").getPrice());
		heavyCreamPrice.setText("$" + inventoryItems.get("DAI003").getPrice());
		iceCreamPrice.setText("$" + inventoryItems.get("DAI004").getPrice());
		chocolateMilkPrice.setText("$" + inventoryItems.get("DAI005").getPrice());
		sourCreamPrice.setText("$" + inventoryItems.get("DAI006").getPrice());
		stringCheesePrice.setText("$" + inventoryItems.get("DAI007").getPrice());
		mozzarellaCheesePrice.setText("$" + inventoryItems.get("DAI008").getPrice());
		yogurtPrice.setText("$" + inventoryItems.get("DAI009").getPrice());

		milkSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("DAI001").getQuantity(), 0));

		butterSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("DAI002").getQuantity(), 0));

		heavyCreamSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("DAI003").getQuantity(), 0));

		iceCreamSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("DAI004").getQuantity(), 0));

		chocolateMilkSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("DAI005").getQuantity(), 0));

		sourCreamSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("DAI006").getQuantity(), 0));

		stringCheeseSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("DAI007").getQuantity(), 0));

		mozzarellaCheeseSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("DAI008").getQuantity(), 0));

		yogurtSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("DAI009").getQuantity(), 0));
		
		if(inventoryItems.get("DAI001").getQuantity() == 0) {
			setOutOfStockField(milkPrice, milkSpinner, milkButton);
		}
		if(inventoryItems.get("DAI002").getQuantity() == 0) {
			setOutOfStockField(butterPrice, butterSpinner, butterButton);
		}
		if(inventoryItems.get("DAI003").getQuantity() == 0) {
			setOutOfStockField(heavyCreamPrice, heavyCreamSpinner, heavyCreamButton);
		}
		if(inventoryItems.get("DAI004").getQuantity() == 0) {
			setOutOfStockField(iceCreamPrice, iceCreamSpinner, iceCreamButton);
		}
		if(inventoryItems.get("DAI005").getQuantity() == 0) {
			setOutOfStockField(chocolateMilkPrice, chocolateMilkSpinner, chocolateMilkButton);
		}
		if(inventoryItems.get("DAI006").getQuantity() == 0) {
			setOutOfStockField(sourCreamPrice, sourCreamSpinner, sourCreamButton);
		}
		if(inventoryItems.get("DAI007").getQuantity() == 0) {
			setOutOfStockField(stringCheesePrice, stringCheeseSpinner, stringCheeseButton);
		}
		if(inventoryItems.get("DAI008").getQuantity() == 0) {
			setOutOfStockField(mozzarellaCheesePrice, mozzarellaCheeseSpinner, mozzarellaCheeseButton);
		}
		if(inventoryItems.get("DAI009").getQuantity() == 0) {
			setOutOfStockField(yogurtPrice, yogurtSpinner, yogurtButton);
		}

	}

	// Add selected products to the cart list
	@FXML
	private void addToCart(javafx.event.ActionEvent event) {

		if (((Button) event.getTarget()).getId().toString().equals("milkButton")) {
			CartItem ci = new CartItem("DAI001", "Milk", (Integer) milkSpinner.getValue(),
					inventoryItems.get("DAI001").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("butterButton")) {
			CartItem ci = new CartItem("DAI002", "Butter", (Integer) butterSpinner.getValue(),
					inventoryItems.get("DAI002").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("heavyCreamButton")) {
			CartItem ci = new CartItem("DAI003", "Heavy Cream", (Integer) heavyCreamSpinner.getValue(),
					inventoryItems.get("DAI003").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("iceCreamButton")) {
			CartItem ci = new CartItem("DAI004", "Ice Cream", (Integer) iceCreamSpinner.getValue(),
					inventoryItems.get("DAI004").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("chocolateMilkButton")) {
			CartItem ci = new CartItem("DAI005", "Chocolate Milk", (Integer)chocolateMilkSpinner.getValue(),
					inventoryItems.get("DAI005").getPrice());

			cart.addProduct(ci);
		}

		if (((Button) event.getTarget()).getId().toString().equals("sourCreamButton")) {
			CartItem ci = new CartItem("DAI006", "Sour Cream", (Integer) sourCreamSpinner.getValue(),
					inventoryItems.get("DAI006").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("stringCheeseButton")) {
			CartItem ci = new CartItem("DAI007", "String Cheese", (Integer) stringCheeseSpinner.getValue(),
					inventoryItems.get("DAI007").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("mozzarellaCheeseButton")) {
			CartItem ci = new CartItem("DAI008", "Mozzarella Cheese", (Integer) mozzarellaCheeseSpinner.getValue(),
					inventoryItems.get("DAI008").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("yogurtButton")) {
			CartItem ci = new CartItem("DAI009", "Yogurt", (Integer) yogurtSpinner.getValue(),
					inventoryItems.get("DAI009").getPrice());

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
