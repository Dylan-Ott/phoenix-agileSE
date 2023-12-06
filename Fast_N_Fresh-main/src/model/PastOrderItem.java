package model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class PastOrderItem {

	public String productName;
	public String date;
	public String cost;
	public String status;
	public String number;

	DecimalFormat df = new DecimalFormat("0.00");
	
    // CartItem entity holds the product information that is added to the cart by user
	public PastOrderItem(String name, String cost, String date, String status, String number) {
        this.productName = name;
        this.date = date;
        this.cost = cost;
        this.status = status;
        this.number = number;
        this.setStatus();
        this.setCost();
    }
	
	public void setCost() {
		this.cost = df.format(Double.parseDouble(this.cost));
	}
	
	public void setStatus()
	{
		if (this.status.equals("Cancelled"))
		{
			// Leave it alone, we don't want to change it from Cancelled
		}
		else
		{
			LocalDate now = LocalDate.now();
			LocalDate orderDate = LocalDate.parse(date);
			
			int days = (int) orderDate.until(now, ChronoUnit.DAYS);
			
			switch (days)
			{
				case 0:
				{
					setStatus("Placed");
					break;
				}
				case 1:
				{
					setStatus("Processed");
					break;
				}
				case 2:
				{
					setStatus("Shipped");
					break;
				}
				case 3:
				default:
				{
					setStatus("Delivered");
					break;
				}

			}
		}
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
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
}