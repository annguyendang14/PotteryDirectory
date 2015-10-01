package controller;

import java.time.LocalDate;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.*;

public class NewOrderController {

	@FXML private Label customerName;
	@FXML private Label dateOrdered;
	@FXML private Label orderNum;
	@FXML private TextField customerNameBar;
	@FXML private DatePicker dateOrderedPicker;
	@FXML private TextField orderNumBar;
	@FXML private Button saveOrder;

	public void saveNewOrder(ActionEvent event) {
		//INCOMPLETE CONSTRUCTOR CALL. Need to somehow convert DatePicker into Date
		
		//Order newOrder = new Order (customerNameBar.getText(), date, Integer.parseInt(orderNumBar.getText()));
		//System.out.println(newOrder);
	}



}
