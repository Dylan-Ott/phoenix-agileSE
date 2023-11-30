package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import model.CartItem;
import model.DatabaseConnector;
import model.PastOrderItem;

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
    private TableView<PastOrderItem> orderHistoryTable;
    
    @FXML
    private TableColumn<PastOrderItem, String> histTableProductName;
    
    @FXML
    private TableColumn<PastOrderItem, String> histTableCost;
    
    @FXML
    private TableColumn<PastOrderItem, String> histTableDate;
    
	@FXML
	private Button btnCatalog;

	@FXML
	private Button btnLogout;
	
	private HashMap<String, PastOrderItem> orderHistory;
	

	@Override
	public void initialize() {
		name.setText("Hi, " + userName + "!");
		
		try {
			// Load Order History for current user
			Connection conn = DatabaseConnector.getInstance();
			Statement st = conn.createStatement();
			String query = "SELECT productId, purchasedPrice, order_timestamp FROM user_order_history where user_name = '" + userId + "'";
			ResultSet rs = st.executeQuery(query);
			
			// Iterate through query results and add to orderHistory
			orderHistory = new HashMap<String, PastOrderItem>();
			while(rs.next())
			{
				PastOrderItem item = new PastOrderItem(rs.getString(1), rs.getString(2), rs.getString(3));
				orderHistory.put(item.toString(), item);
			}
			
			ObservableList<PastOrderItem> list = FXCollections.observableArrayList(orderHistory.values());
			// Populate table
			histTableProductName.setCellValueFactory(new PropertyValueFactory<PastOrderItem, String>("productName"));
			histTableProductName.setStyle( "-fx-alignment: CENTER;");
			histTableCost.setCellValueFactory(new PropertyValueFactory<PastOrderItem, String>("cost"));
			histTableCost.setStyle( "-fx-alignment: CENTER;");
			histTableDate.setCellValueFactory(new PropertyValueFactory<PastOrderItem, String>("date"));
			histTableDate.setStyle( "-fx-alignment: CENTER;");
			orderHistoryTable.setItems(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
