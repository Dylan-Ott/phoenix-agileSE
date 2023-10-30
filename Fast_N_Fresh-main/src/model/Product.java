package model;

public class Product {
	
	private int quantity;
	private double price;
	private String productName;
	private String productId;
	private String catalog;

	// Product entity is used to store the product information from the Inventory Database store.
	public Product(String productId, String productName, int quantity, double price, String catalogId){
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.catalog = catalogId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Override
	public String toString() {
		return "Product{" + "productId=" + productId + "productName=" + productName + "quantity=" + quantity + "price=" + price +'}';
		
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	
}
