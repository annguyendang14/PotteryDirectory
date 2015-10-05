package controller;

import java.net.URL;
import java.util.ResourceBundle;

import data.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditCustomerController implements Initializable {
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
	@FXML private Button editButton;
	private Customer cus = TempCustomer.getTempCustomer();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			nameBar.setText(cus.getName());
			addressBar.setText(cus.getAddress());
			phoneNumBar.setText(cus.getPhoneNum());
			emailBar.setText(cus.getEmail());
		} catch (Exception e){
			e.printStackTrace();
		}
		
		

	}
	public void saveCustomer(ActionEvent event){
		cus = new Customer(nameBar.getText(),addressBar.getText(),phoneNumBar.getText(),emailBar.getText());
		
		
		Node  source = (Node)  event.getSource(); 
		Stage stage  = (Stage) source.getScene().getWindow();
		stage.close();
			  
			  
		
	}
	public void editCustomer(ActionEvent event){
		nameBar.setDisable(false);
		addressBar.setDisable(false);
		phoneNumBar.setDisable(false);
		emailBar.setDisable(false);
		saveOrder.setDisable(false);
	}

}
