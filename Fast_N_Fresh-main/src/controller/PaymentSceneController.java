
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.CartItem;
import model.DatabaseConnector;
import model.Product;

public class PaymentSceneController extends ProductBaseController {

	private boolean codStatus = true;

	@FXML
	private Button confirmorder;

	@FXML
	private TextField addressField;

	@FXML
	private AnchorPane myAnchorPane;

	@FXML
	private TextField cardnumber;

	@FXML
	private TextField cvvField;

	@FXML
	private TextField nameField;

	@FXML
	private DatePicker dateField;

	@FXML
	private RadioButton cashondelivery;

	@FXML
	private RadioButton creditcard;

	@FXML
	private Label lbl;

	@FXML
	private Label errorAddress;

	@FXML
	private Label errorCardNumber;

	@FXML
	private ToggleGroup payment;

	// Logout of the application and return to the Login Page
	@FXML
	void goToLogin(ActionEvent event) {
		logOff();
		ScreenController.goToLoginPage(event);
	}

	// Go to the Order Summary Page
	@FXML
	void goToCart(ActionEvent event) {
		ScreenController.goToCartPage(event);
	}

	// Disable Card fields upon COD selection
	@FXML
	void alterCardFields(ActionEvent event) {
		codStatus = true;
		cardnumber.setDisable(codStatus);
		cvvField.setDisable(codStatus);
		nameField.setDisable(codStatus);
		dateField.setDisable(codStatus);
	}

	// Enable Card fields upon Debit/Credit card selection
	@FXML
	void alterCOD(ActionEvent event) {
		codStatus = false;
		cardnumber.setDisable(codStatus);
		cvvField.setDisable(codStatus);
		nameField.setDisable(codStatus);
		dateField.setDisable(codStatus);
	}

	@Override
	public void initialize() {
		cardnumber.setDisable(codStatus);
		cvvField.setDisable(codStatus);
		nameField.setDisable(codStatus);
		dateField.setDisable(codStatus);
	}

	private String order_date = new SimpleDateFormat("YYYY-MM-dd").format(new Date());

	// Validate and place order successfully
	@FXML
	public void handlebtnconfirmorder(ActionEvent event) {
		if (isAddressValid()) {
			errorAddress.setVisible(false);
			addressField.setStyle(null);
			// Proceed to Ordering if Cash On Delivery is selected
			// Else do Credit card number validation
			if (codStatus) {
				proceedOrder(event);
			} else {
				String cardnum = cardnumber.getText();
				long number = Long.parseLong(cardnum);
				if (isValid(number)) {
					errorCardNumber.setVisible(false);
					cardnumber.setStyle(null);
					proceedOrder(event);
				} else {
					errorCardNumber.setVisible(true);
					cardnumber.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
					new animatefx.animation.Shake(cardnumber).play();
					Dialog<String> dialog = new Dialog<String>();
					// Setting the title
					dialog.setTitle("Invalid Card");
					ButtonType type = new ButtonType("OK", ButtonData.OK_DONE);
					// Setting the content of the dialog
					dialog.setContentText("Card Number is invalid");
					// Adding buttons to the dialog pane
					dialog.getDialogPane().getButtonTypes().add(type);
					dialog.showAndWait();

				}
			}
		} else {
			errorAddress.setVisible(true);
			addressField.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
			new animatefx.animation.Shake(addressField).play();
			Dialog<String> dialog = new Dialog<String>();
			// Setting the title
			dialog.setTitle("Invalid Address");
			ButtonType type = new ButtonType("OK", ButtonData.OK_DONE);
			// Setting the content of the dialog
			dialog.setContentText("Address cannot be empty");
			// Adding buttons to the dialog pane
			dialog.getDialogPane().getButtonTypes().add(type);
			dialog.showAndWait();
		}

	}

	// Update the Database inventory and the user_order history and show successful message after placing order
	private void proceedOrder(ActionEvent event) {
		Dialog<String> dialog = new Dialog<String>();
		updateCartItems();
		// Setting the title
		dialog.setTitle("Order Confirmation");
		ButtonType type = new ButtonType("OK", ButtonData.OK_DONE);
		// Setting the content of the dialog
		dialog.setContentText("Order is successfully placed!");
		// Adding buttons to the dialog pane
		dialog.getDialogPane().getButtonTypes().add(type);
		dialog.showAndWait();
		logOff();
		ScreenController.goToCatalogPage(event);
	}

	// Update the inventory items based on the amount of the product purchased by the user
	private void updateCartItems() {
		try {
			List<CartItem> cartList = cart.getCartItems();
			Connection conn = DatabaseConnector.getInstance();
			for (CartItem item : cartList) {
				String key = item.getProductId();
				Product productInventory = inventoryItems.get(key);
				int netQuantity = productInventory.getQuantity() - item.getQuantity();
				saveOrderHistory(conn, item, productInventory);
				updateInventory(conn, item.getProductId(), netQuantity);
			}
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Update method that interacts with product_list table to set the new quantity available in the store after purchase
	private void updateInventory(Connection conn, String key, int quantity) {
		String query = "update product_list set productQuantity = ? where productId = ?";
		PreparedStatement preparedStmt;
		try {

			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, quantity);
			preparedStmt.setString(2, key);
			preparedStmt.executeUpdate();
			preparedStmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Update method that interacts with the user_order_history table to store user order information for Analytics purposes
	private void saveOrderHistory(Connection conn, CartItem item, Product product) {
		String query = "insert into user_order_history values (?,?,?,?,?)";
		PreparedStatement orderStmt;
		try {
			orderStmt = conn.prepareStatement(query);
			orderStmt.setString(1, userId);
			orderStmt.setString(2, item.getProductId());
			orderStmt.setString(3, item.getItemTotalValue());
			orderStmt.setString(4, product.getCatalog());
			orderStmt.setString(5, order_date);
			orderStmt.executeUpdate();
			orderStmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Validate the address field to not be empty
	public boolean isAddressValid() {
		String name = addressField.getText().trim();
		if (name.length() > 0) {
			return true;
		}
		return false;
	}

	/** Return true if the card number is valid */
	public static boolean isValid(long number) {
		// TODO: write your code here
		int flag = 0, sumOfDoubleEven = 0, sumOfOdd = 0, totalSum = 0;
		/*** Count of digits ***/
		int countOfDigits = getSize(number);
		// System.out.println("Count of digits is:"+countOfDigits);
		// Credit card number must be between 13 and 16 digits.
		if (countOfDigits >= 13 && countOfDigits <= 16) {
			// It must start with either 4,5,37 or 6
			if (prefixMatched(number, 4) || prefixMatched(number, 5) || prefixMatched(number, 37)
					|| prefixMatched(number, 6)) {
				/*
				 * Double every even digit. If doubling results in a two-digit number, add up
				 * the two digits to one
				 */
				// System.out.println("Prefixed is matched");
				sumOfDoubleEven = sumOfDoubleEvenPlace(number);
				// System.out.println("sum of Double Even Place method is"+ sumOfDoubleEven);
				sumOfOdd = sumOfOddPlace(number);
				// System.out.println("sum of Odd Place method is"+ sumOfOdd);

				totalSum = sumOfDoubleEven + sumOfOdd;
				if (totalSum % 10 == 0) {
					flag = 1;

				}
			}

		}

		if (flag == 1) {
			return true;

		} else

			return false;

	}

	/** Get the result from Step 2 */
	public static int sumOfDoubleEvenPlace(long number) {

		// TODO: write your code here
		// int num=(int)number;
		int c = 1, sumOfDoubleEven = 0;

		while (number != 0) {
			long digit = number % 10;
			if (c % 2 == 0) {
				digit = digit * 2;
				// Digit has value after multiplied by 2

				int digitInt = (int) digit;
				int oneDigitValue = getDigit(digitInt);

				sumOfDoubleEven = sumOfDoubleEven + oneDigitValue;
			}
			c++;
			number = number / 10;
		}

		return sumOfDoubleEven;
	}

	/**
	 * Return this number if it is a single digit, otherwise, return the sum of the
	 * two digits
	 */
	public static int getDigit(int number) {
		// TODO: write your code here
		int countDigits = 0;
		int numberToCount = number;
		while (numberToCount != 0) {
			numberToCount /= 10;
			++countDigits;
		}
		if (countDigits == 1)
			return number;
		else {

			int sum = 0, digit;
			while (number != 0) {
				digit = number % 10;
				sum = sum + digit;
				number = number / 10;

			}
			return sum;

		}
	}

	/** Return sum of odd place digits in number this is step3 */
	public static int sumOfOddPlace(long number) {
		// TODO: write your code here

		long sumOdd = 0, c = 1, digit;
		while (number != 0) {
			if (c % 2 != 0) {
				digit = number % 10;
				sumOdd = sumOdd + digit;
			}
			number /= 10;
			c++;

		}
		int sumOddInt = (int) sumOdd;

		return sumOddInt;
	}

	/** Return true if the digit d is a prefix for number */
	public static boolean prefixMatched(long number, int d) {
		int numberOfDigits = Integer.toString(d).length();
		long Prefix = getPrefix(number, numberOfDigits);

		// TODO: write your code here
		String numStr = String.valueOf(number);
		String digitStr = String.valueOf(d);
		int numSize = numStr.length();
		int digitSize = digitStr.length();

		if (digitSize > numSize) {
			return false;
		}

		for (int i = 0; i < digitSize; i++) {
			if (numStr.charAt(i) != digitStr.charAt(i)) {
				return false;

			}

		}
		return true;
	}

	/** Return the number of digits in d */
	public static int getSize(long d) {
		// TODO: write your code here
		int countDigits = 0;
		while (d != 0) {
			d /= 10;
			++countDigits;
		}

		return countDigits;
	}

	/**
	 * Return the first k number of digits from number. If the number of digits in
	 * number is less than k, return number.
	 */
	public static long getPrefix(long number, int k) {
		// TODO: write your code here
		int numOfDigits = getSize(number);
		if (numOfDigits >= k) {
			String strNum = String.valueOf(number);
			String subString = strNum.substring(0, k);
			int num = Integer.parseInt(subString);
			return num;

		} else
			return number;

	}

}
