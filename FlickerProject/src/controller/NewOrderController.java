package controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.*;

public class NewOrderController{

	@FXML private Label customerName;
	@FXML private Label dateOrdered;
	@FXML private Label orderNum;
	@FXML private TextField customerNameBar;
	@FXML private DatePicker dateOrderedPicker;
	@FXML private TextField orderNumBar;
	@FXML private Button saveOrder;
	@FXML private DatePicker dueDatePicker;
	@FXML private TextArea description;
	@FXML private TextField price;
	@FXML private Button generateButton;



	public void saveNewOrder(ActionEvent event) {
		//INCOMPLETE CONSTRUCTOR CALL. Need to somehow convert DatePicker into Date

		
			try {
				Double.parseDouble(price.getText());
				
			} catch(NumberFormatException e) { 
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Null");
				alert.setContentText("Price must be number!!");

				alert.showAndWait();
		    } 
			if (orderNumBar.getText().equals("0")){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Null");
				alert.setContentText("Please generate Order Number!");
				alert.showAndWait();
			} else if (dueDatePicker.getValue() != null){
				Order newOrder = new Order (customerNameBar.getText(), toDate(dateOrderedPicker), toDate(dueDatePicker), Integer.parseInt(orderNumBar.getText()), description.getText(), Double.parseDouble(price.getText()));
				System.out.println(newOrder);
			} else {
				Order newOrder = new Order (customerNameBar.getText(), toDate(dateOrderedPicker),Integer.parseInt(orderNumBar.getText()), description.getText(), Double.parseDouble(price.getText()));
				System.out.println(newOrder);
			}
			
		        
		
				


		//Order newOrder = new Order (customerNameBar.getText(), date, Integer.parseInt(orderNumBar.getText()));
		//System.out.println(newOrder);

	}
	public void generateOrderNum(ActionEvent event) {
		//orderNumBar.setEditable(true);
		orderNumBar.setText("10");
		//orderNumBar.setEditable(false);

	}
	// http://stackoverflow.com/questions/20446026/get-value-from-date-picker
		public static Date toDate(DatePicker datePick) {
			LocalDate localDate = datePick.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			Date date = Date.from(instant);
			return date;
		}





}
