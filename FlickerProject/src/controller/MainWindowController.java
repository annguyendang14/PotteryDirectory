package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.*;


/*
 * Very Basic Main Window GUI
 */
public class MainWindowController {
	@FXML private Label title;
	@FXML private Button createNewOrder;
	@FXML private Button createNewCustomer;
	
	// Need to interact with the New Customer and New Order GUIs.
	public void launchNewOrder(ActionEvent event){
		
		
	}
	
	public void launchNewCustomer(ActionEvent event){
		
	}
}