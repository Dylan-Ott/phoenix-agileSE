package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import model.DatabaseConnector;

public class OrderExpensesController extends ProductBaseController {

	@FXML
	BarChart barChart;

	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;

	@FXML
	void goToCatalog(ActionEvent event) {
		ScreenController.goToCatalogPage(event);
	}

	@Override
	public void initialize() {
		// Fetch the user order information from the Database and calculate the purchase
		// value for each ordered date and store in the Hashmap
		Map<String, Double> orderHistory = new HashMap<String, Double>();
		List<String> dateHistory = new ArrayList<String>();
		try {
			Connection conn = DatabaseConnector.getInstance();
			Statement st = conn.createStatement();
			String query = "select purchasedPrice, order_timestamp from user_order_history where user_name = '" + userId
					+ "'";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				if (orderHistory.containsKey(rs.getString(2))) {
					orderHistory.put(rs.getString(2),
							orderHistory.get(rs.getString(2)) + Double.valueOf(rs.getString(1)));
				} else {
					orderHistory.put(rs.getString(2), Double.valueOf(rs.getString(1)));
					dateHistory.add(rs.getString(2));
				}

			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Sort the List as per the date in the ascending order
		Collections.sort(dateHistory, new SortItems());

		XYChart.Series series1 = new XYChart.Series();
		for (String s : dateHistory) {
			series1.getData().add(new XYChart.Data(s, orderHistory.get(s)));
		}

		barChart.getData().addAll(series1);
		barChart.setLegendVisible(false);

	}

	class SortItems implements Comparator<String> {

		// Method of this class
		// @Override
		public int compare(String a, String b) {

			// Returning the value after comparing the objects
			// this will sort the data in Ascending order
			return a.compareTo(b);
		}
	}

}
