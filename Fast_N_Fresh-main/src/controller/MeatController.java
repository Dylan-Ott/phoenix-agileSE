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

public class MeatController extends ProductBaseController {

    @FXML
    private Spinner<Integer> beefSpinner;
    @FXML
    private Spinner<Integer> chickenSpinner;
    @FXML
    private Spinner<Integer> clamSpinner;
    @FXML
    private Spinner<Integer> crabSpinner;
    @FXML
    private Spinner<Integer> fishSpinner;
    @FXML
    private Spinner<Integer> lambSpinner;
    @FXML
    private Spinner<Integer> porkSpinner;
    @FXML
    private Spinner<Integer> prawnsSpinner;
    @FXML
    private Spinner<Integer> turkeySpinner;

	
	@FXML
    private Button beefButton;
    @FXML
    private Label beefPrice;
    @FXML
    private Button chickenButton;
    @FXML
    private Label chickenPrice;
    @FXML
    private Button clamButton;
    @FXML
    private Label clamPrice;
    @FXML
    private Button crabButton;
    @FXML
    private Label crabPrice;
    @FXML
    private Button fishButton;
    @FXML
    private Label fishPrice;
    @FXML
    private Button lambButton;
    @FXML
    private Label lambPrice;
    @FXML
    private Button porkButton;
    @FXML
    private Label porkPrice;
   @FXML
    private Button prawnsButton;
    @FXML
    private Label prawnsPrice;
    @FXML
    private Button turkeyButton;
    @FXML
    private Label turkeyPrice;





	public void initialize() {

//   	Fetch the Meat Products information from the Meat catalog in the Database	
		try {
			ResultSet rs = DatabaseConnector.getItemsFromCatalog("Meat");
			while (rs.next()) {
				inventoryItems.put(rs.getString(1),
						new Product(rs.getString(1), rs.getString(2), rs.getInt(4), rs.getDouble(3), rs.getString(5)));
			}
			DatabaseConnector.closeStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		chickenPrice.setText("$" + inventoryItems.get("MEA001").getPrice());
		fishPrice.setText("$" + inventoryItems.get("MEA002").getPrice());
		beefPrice.setText("$" + inventoryItems.get("MEA003").getPrice());
		crabPrice.setText("$" + inventoryItems.get("MEA004").getPrice());
		clamPrice.setText("$" + inventoryItems.get("MEA005").getPrice());
		lambPrice.setText("$" + inventoryItems.get("MEA006").getPrice());
		porkPrice.setText("$" + inventoryItems.get("MEA007").getPrice());
		turkeyPrice.setText("$" + inventoryItems.get("MEA008").getPrice());
		prawnsPrice.setText("$" + inventoryItems.get("MEA009").getPrice());

		chickenSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("MEA001").getQuantity(), 0));

		fishSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("MEA002").getQuantity(), 0));

		beefSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("MEA003").getQuantity(), 0));

		crabSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("MEA004").getQuantity(), 0));

		clamSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("MEA005").getQuantity(), 0));

		lambSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("MEA006").getQuantity(), 0));

		porkSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("MEA007").getQuantity(), 0));

		turkeySpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("MEA008").getQuantity(), 0));

		prawnsSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("MEA009").getQuantity(), 0));
		
		if(inventoryItems.get("MEA001").getQuantity() == 0) {
			setOutOfStockField(chickenPrice, chickenSpinner, chickenButton);
		}
		if(inventoryItems.get("MEA002").getQuantity() == 0) {
			setOutOfStockField(fishPrice, fishSpinner, fishButton);
		}
		if(inventoryItems.get("MEA003").getQuantity() == 0) {
			setOutOfStockField(beefPrice, beefSpinner, beefButton);
		}
		if(inventoryItems.get("MEA004").getQuantity() == 0) {
			setOutOfStockField(crabPrice, crabSpinner, crabButton);
		}
		if(inventoryItems.get("MEA005").getQuantity() == 0) {
			setOutOfStockField(clamPrice, clamSpinner, clamButton);
		}
		if(inventoryItems.get("MEA006").getQuantity() == 0) {
			setOutOfStockField(lambPrice, lambSpinner, lambButton);
		}
		if(inventoryItems.get("MEA007").getQuantity() == 0) {
			setOutOfStockField(porkPrice, porkSpinner, porkButton);
		}
		if(inventoryItems.get("MEA008").getQuantity() == 0) {
			setOutOfStockField(turkeyPrice, turkeySpinner, turkeyButton);
		}
		if(inventoryItems.get("MEA009").getQuantity() == 0) {
			setOutOfStockField(prawnsPrice, prawnsSpinner, prawnsButton);
		}

	}

	// Add selected products to the cart list
	@FXML
	private void addToCart(javafx.event.ActionEvent event) {

		if (((Button) event.getTarget()).getId().toString().equals("chickenButton")) {
			CartItem ci = new CartItem("MEA001", "Chicken", (Integer) chickenSpinner.getValue(),
					inventoryItems.get("MEA001").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("fishButton")) {
			CartItem ci = new CartItem("MEA002", "Fish", (Integer) fishSpinner.getValue(),
					inventoryItems.get("MEA002").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("beefButton")) {
			CartItem ci = new CartItem("MEA003","Beef", (Integer) beefSpinner.getValue(),
					inventoryItems.get("MEA003").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("crabButton")) {
			CartItem ci = new CartItem("MEA004", "Crab", (Integer) crabSpinner.getValue(),
					inventoryItems.get("MEA004").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("clamButton")) {
			CartItem ci = new CartItem("MEA005", "Clam", (Integer)clamSpinner.getValue(),
					inventoryItems.get("MEA005").getPrice());

			cart.addProduct(ci);
		}

		if (((Button) event.getTarget()).getId().toString().equals("lambButton")) {
			CartItem ci = new CartItem("MEA006", "Lamb", (Integer) lambSpinner.getValue(),
					inventoryItems.get("MEA006").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("porkButton")) {
			CartItem ci = new CartItem("MEA007", "Pork", (Integer) porkSpinner.getValue(),
					inventoryItems.get("MEA007").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("turkeyButton")) {
			CartItem ci = new CartItem("MEA008", "Turkey", (Integer) turkeySpinner.getValue(),
					inventoryItems.get("MEA008").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("prawnsButton")) {
			CartItem ci = new CartItem("MEA009", "Prawns", (Integer) prawnsSpinner.getValue(),
					inventoryItems.get("MEA009").getPrice());

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
