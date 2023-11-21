package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MaintenanceController extends ProductBaseController {

	@FXML
	private Label lblMaintenance;
    
    @Override
	void initialize() {
    	lblMaintenance.setText("Unfortunately, our store is undergoing\nregular maintenance.\nDon't worry! We'll be back soon to serve you!");
	}

}
