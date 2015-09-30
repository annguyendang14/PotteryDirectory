package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.*;

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
	
	public Customer save(ActionEvent event){
		Customer cus = new Customer(nameBar.getText(),addressBar.getText(),phoneNumBar.getText(),emailBar.getText());
		System.out.println(cus);
		return cus;
		
	}
}