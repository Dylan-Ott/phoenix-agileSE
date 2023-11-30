package model;

import java.text.DecimalFormat;


public class PastOrderItem {

	public String productName;
	public String date;
	public String cost;

	DecimalFormat df = new DecimalFormat("0.00");
	
    // CartItem entity holds the product information that is added to the cart by user
	public PastOrderItem(String name, String cost, String date) {
        this.productName = name;
        this.date = date;
        this.cost = cost;
        this.setCost();
    }
	
	public void setCost() {
		this.cost = df.format(Double.parseDouble(this.cost));
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

}