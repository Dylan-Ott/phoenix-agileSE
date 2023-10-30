package controller;

import java.util.HashMap;
import java.util.Map;

import model.Cart;
import model.Product;

public abstract class ProductBaseController {
		// Static cart object instance is shared and used to hold the products list added by the user from various catalog screens
		public static Cart cart = new Cart();
		
		// inventoryItems map is used to hold the product lists fetched from the Database based on user traversal on screens
		public static Map<String, Product> inventoryItems = new HashMap<String, Product>();
		
		// Hold UserId information upon successful login
		public static String userId="";
		
		// Hold UserName information upon successful login
		public static String userName="";
		
		// Abstract method to initialize the fields and controls on loading the screens
		abstract void initialize();
		
		// Logoff method to clear the Hashmaps and inventory lists on clicking LogOff button from any screen
		public void logOff() {
			cart.clearCart();
			inventoryItems.clear();
		}
}
