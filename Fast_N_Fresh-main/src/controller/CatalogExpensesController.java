package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import model.DatabaseConnector;
import model.Product;

public class CatalogExpensesController extends ProductBaseController {
	
	@FXML PieChart catalogData;
	
	// Go to the Catalog Page
	@FXML
    void goToCatalog(ActionEvent event) {
		ScreenController.goToCatalogPage(event);
    }
	
	@Override
	public void initialize() {
		
		double fruitShare = 0;
		double vegShare = 0;
		double snackShare = 0;
		double beverageShare = 0;
		double dairyShare = 0;
		double meatShare = 0;
		// Fetch the user order information from the Database and calculate the catalog wise expenditure and present it using Pie chart
		try {
			Connection conn = DatabaseConnector.getInstance();
			Statement st = conn.createStatement();
			String query = "select purchasedPrice, catalog from user_order_history where user_name = '" + userId +"'";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				switch(rs.getString(2)) {
					case "Fruits" :
						 fruitShare += Double.valueOf(rs.getString(1));
						 break;
					case "Vegetables" :
						vegShare += Double.valueOf(rs.getString(1)); 
						break;
					case "Snacks" :
						snackShare += Double.valueOf(rs.getString(1)); 
						break;
					case "Beverages" :
						beverageShare += Double.valueOf(rs.getString(1)); 
						break;
					case "Dairy" :
						dairyShare += Double.valueOf(rs.getString(1));
						break;
					case "Meat" :
						meatShare += Double.valueOf(rs.getString(1));
						break;
				}
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		double totalShare = fruitShare + vegShare + snackShare + beverageShare + dairyShare + meatShare;
		
		ObservableList<PieChart.Data> pieChartData =
	            FXCollections.observableArrayList(
	            new PieChart.Data("Fruits $" + String.format("%.2f", fruitShare), (fruitShare/totalShare) * 100),
	            new PieChart.Data("Vegetables $" + String.format("%.2f", vegShare), (vegShare/totalShare) * 100),
	            new PieChart.Data("Snacks$" + String.format("%.2f", snackShare), (snackShare/totalShare) * 100),
	            new PieChart.Data("Beverages$" + String.format("%.2f", beverageShare), (beverageShare/totalShare) * 100),
	            new PieChart.Data("Dairy$" + String.format("%.2f", dairyShare), (dairyShare/totalShare) * 100),
				new PieChart.Data("Meat$" + String.format("%.2f", meatShare), (meatShare/totalShare) * 100));

		catalogData.setData(pieChartData);
	}

}
