package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import model.CartItem;
import model.DatabaseConnector;

public class ProfileController extends ProductBaseController {
	
	@FXML
	private Label name;
	
	@FXML
	private Label orderItem;
	
	@FXML
    private Label orderTotal;

    @FXML
    private Label orderDate;
    
    @FXML
    private TableView<CartItem> orderHistoryTable;
    
    @FXML
    private TableColumn<CartItem, String> histTableProductName;
    
    @FXML
    private TableColumn<CartItem, String> histTableCost;
    
    @FXML
    private TableColumn<CartItem, String> histTableDate;
    
	@FXML
	private Button btnCatalog;

	@FXML
	private Button btnLogout;
	
	// Loads the order history information from the DB into a list
	// Query DB
	
	// Create list
	ObservableList<CartItem> orderHist = FXCollections.observableArrayList(cart.getCartItems());
    
    @Override
	public void initialize() {
		name.setText("Hi, " + userName + "!");
	}

    // Validate the user credentials and go to Catalog Page
	@FXML
	public void goToCatalog(ActionEvent event) {
		ScreenController.goToCatalogPage(event);
	}

	// Go to the User Registration Page
	@FXML
	public void logout(ActionEvent event) {
		logOff();
		ScreenController.goToHomePage(event);
	}

}
