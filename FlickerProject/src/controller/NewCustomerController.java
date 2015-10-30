package controller;

import data.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/*
 * this class saves the textField content to a new customer
 */
public class NewCustomerController {
	@FXML private Label title;
	@FXML private Label name;
	@FXML private Label address;
	@FXML private Label phoneNum;
	@FXML private Label email;
	@FXML private TextField nameBar;
	@FXML private TextField addressBar;
	@FXML private TextField phoneNumBar;
	@FXML private TextField emailBar;
	@FXML private Button saveOrder;
	
	public void saveCustomer(ActionEvent event){
		Customer cus = new Customer(nameBar.getText(),addressBar.getText(),phoneNumBar.getText(),emailBar.getText());
		AllCustomer.getCustomers().add(cus);
		TempCustomer.setTempCustomer(cus);
		Node  source = (Node)  event.getSource(); 
		Stage stage  = (Stage) source.getScene().getWindow();
		stage.close();
			  
			  
		
	}
	
}
	