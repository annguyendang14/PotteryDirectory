package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.*;

public class MainWindowControllerv2 {

	@FXML
	private Button createNewCustomer, createNewOrder, saveNewCustomer, saveNewOrder;
	private TextField nameBar, addressBar, phoneNumBar, emailBar;
	
	
	
	public void handleButtonAction(ActionEvent event) throws IOException {
		Stage stage;
		Parent root;
		//System.out.println(event.getSource());
		
		if(event.getSource() == createNewCustomer){
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("NewCustomer.fxml"));
			stage.setScene(new Scene(root));
		    stage.setTitle("My modal window");
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.initOwner(createNewCustomer.getScene().getWindow());
		    stage.showAndWait();
		    
		}
		/*if(event.getSource() == saveNewCustomer){
	    	saveCustomer(event);
		}*/
		else if (event.getSource() == createNewOrder){
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("NewOrder.fxml"));
			stage.setScene(new Scene(root));
		    stage.setTitle("My modal window");
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.initOwner(createNewOrder.getScene().getWindow());
		    stage.showAndWait();
		}
		
		
	}
	
	public Customer saveCustomer(ActionEvent event){
		Customer cus = new Customer(nameBar.getText(),addressBar.getText(),phoneNumBar.getText(),emailBar.getText());
		System.out.println(cus);
		return cus;
		
	}
	/*
	public void saveCustomer(ActionEvent event){
		//Customer cus = new Customer(nameBar.getText(),addressBar.getText(),phoneNumBar.getText(),emailBar.getText());
		System.out.println("is it working?");
		//return cus;
		
	}*/
}
