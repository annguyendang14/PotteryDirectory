package controller;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import GUI.NewCustomerGUI;
import data.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewOrderController implements Initializable{

	@FXML private Label customerName;
	@FXML private Label dateOrdered;
	@FXML private Label orderNum;
	@FXML private TextField customerNameBar;
	@FXML private DatePicker dateOrderedPicker;
	@FXML private Label orderNumBar;
	@FXML private Button saveOrder;
	@FXML private DatePicker dueDatePicker;
	@FXML private TextArea description;
	@FXML private TextField price;
	//@FXML private Button generateButton;
	@FXML private Button addCustomerButton;
	@FXML private Label warningLabel;



	public void saveNewOrder(ActionEvent event) {
		//INCOMPLETE CONSTRUCTOR CALL. Need to somehow convert DatePicker into Date
		//An: we have toDate method for that (in this class), hope you find it
		
			try {
				Double.parseDouble(price.getText());
				
			} catch(NumberFormatException e) { 
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				
				alert.setContentText("Price must be number!!");

				alert.showAndWait();
		    } 
			
			/*if (orderNumBar.getText().equals("0")){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Null");
				alert.setContentText("Generate Order Number!");
				generateOrderNum(new ActionEvent());
				alert.showAndWait();
			}else {*/
			
				
				Order newOrder;
			
				if(dueDatePicker.getValue() != null){
					if (dueDatePicker.getValue().compareTo(dateOrderedPicker.getValue())<=0){
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Warning Dialog");
						
						alert.setContentText("Please choose due date later than order date!");

						alert.showAndWait();
						return;
						
					}else {
						newOrder = new Order (TempCustomer.getTempCustomer(), toDate(dateOrderedPicker), toDate(dueDatePicker), Integer.parseInt(orderNumBar.getText()), description.getText(), Double.parseDouble(price.getText()));
						System.out.println(newOrder);
					}
			
					
				} else {
					newOrder = new Order (TempCustomer.getTempCustomer(), toDate(dateOrderedPicker),Integer.parseInt(orderNumBar.getText()), description.getText(), Double.parseDouble(price.getText()));
					System.out.println(newOrder);
				}
				//just to print out thing for now
				AllOrders.getOrders().add(newOrder);
				for (Order order: AllOrders.getOrders()){
					System.out.println("all order: "+order+" "+order.hashCode());
					
				}
				Node  source = (Node)  event.getSource(); 
				Stage stage  = (Stage) source.getScene().getWindow();
				stage.close();
			
			
			
		        
		
				


		//Order newOrder = new Order (customerNameBar.getText(), date, Integer.parseInt(orderNumBar.getText()));
		//System.out.println(newOrder);

	}
/*	public void generateOrderNum(ActionEvent event) {
		//orderNumBar.setEditable(true);
		orderNumBar.setText(AllOrders.getOrders().size()+1+"");
		//orderNumBar.setEditable(false);

	}*/
	// http://stackoverflow.com/questions/20446026/get-value-from-date-picker
		public static Date toDate(DatePicker datePick) {
			LocalDate localDate = datePick.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			Date date = Date.from(instant);
			return date;
		}
	
	public void addNewCustomer(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(NewCustomerGUI.class.getResource("NewCustomer.fxml"));
		stage.setScene(new Scene(root));
	    stage.setTitle("Add New Customer");
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.initOwner(addCustomerButton.getScene().getWindow());
	    stage.showAndWait();
	    
	    customerNameBar.setText(TempCustomer.getTempCustomer().getName());
	    warningLabel.setText("");
	    saveOrder.setDisable(false);
	    
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dateOrderedPicker.setValue(LocalDate.now());
		orderNumBar.setText(AllOrders.getOrders().size()+1+"");
		
	}
	
	
}
