package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Cart {

	private HashMap<String, CartItem> cartItems = new HashMap<String, CartItem>();
	
	// Cart Object is used to hold the products list added to the cart by the user in a cartItems Hashmap
	public Cart() {
	}

	// Returns the products List in a ArrayList to present the data in the Order Summary table
	public List<CartItem> getCartItems() {
		List<CartItem> itemsList = new ArrayList<CartItem>();
		Iterator<Entry<String, CartItem>> cartItemsIterator = cartItems.entrySet().iterator();
		// Iterating every set of entry in the HashMap
		while (cartItemsIterator.hasNext()) {
			Map.Entry<String, CartItem> entity = cartItemsIterator.next();
			itemsList.add(entity.getValue());
		}
		return itemsList;
	}

	public Set<String> getProductKeys() {
		return cartItems.keySet();
	}

	public void addProduct(CartItem entity) {

		if (cartItems.containsKey(entity.getProductId())) {
			int quantity = entity.getQuantity();

			cartItems.get(entity.getProductId()).setQuantity(quantity);

			System.out.println(cartItems.get(entity.getProductId()).getQuantity());
			cartItems.put(entity.getProductId(), cartItems.get(entity));

		} else {
			cartItems.put(entity.getProductId(), entity);
		}

	}

	public void clearCart() {
		cartItems.clear();
	}

}
