package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenController {
	// Set the stage as per the traversal of the screens by the user
	public static void showStage(ActionEvent event, String filename, String sceneName) {
        try {
            Parent parent = FXMLLoader.load(ScreenController.class.getResource(filename));
            Scene scene = new Scene(parent, 900, 700);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setTitle(sceneName);
            appStage.setScene(scene);
            appStage.setResizable(false);
            appStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
	public static void goToFruitsPage(ActionEvent event) {
        showStage(event, "../view/Fruits.fxml", "Fruits");
    }
	
	public static void goToVegetablesPage(ActionEvent event) {
        showStage(event, "../view/Vegetables.fxml", "Vegetables");
    }
	
	public static void goToMeatPage(ActionEvent event) {
        showStage(event, "../view/Meat.fxml", "Meat");
    }
	static void goToCartPage(ActionEvent event) {
        showStage(event, "../view/OrderCart.fxml", "Cart");
    }
	
	static void goToCatalogPage(ActionEvent event) {
        showStage(event, "../view/Catalog.fxml", "Catalog");
    }

	static void goToPaymentPage(ActionEvent event) {
        showStage(event, "../view/PaymentScene.fxml", "Payment");
    }
	
	static void goToRegistrationPage(ActionEvent event) {
        showStage(event, "../view/Registration.fxml", "Registration");
    }
	
	static void goToLoginPage(ActionEvent event) {
        showStage(event, "../view/Login.fxml", "Login");
	}
        
	public static void goToDairyPage(ActionEvent event) {
        showStage(event, "../view/Dairy.fxml", "Dairy");
    }

    public static void goToSnacksPage(ActionEvent event) {
        showStage(event, "../view/Snacks.fxml", "Snacks");
    }
    
    public static void goToBeveragesPage(ActionEvent event) {
        showStage(event, "../view/Beverages.fxml", "Beverages");
    }
    
    public static void goToCatalogExpensesPage(ActionEvent event) {
        showStage(event, "../view/CatalogExpenses.fxml", "Catalog wise Expenses");
    }
    
    public static void goToOrderHistoryPage(ActionEvent event) {
        showStage(event, "../view/OrderExpenses.fxml", "Order History");
    }
}
