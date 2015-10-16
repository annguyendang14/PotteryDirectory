package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import GUI.EditCustomerGUI;
import data.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class EditOrderController implements Initializable {
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
	@FXML private Button viewCustomerButton;
	@FXML private Button editButton;
	@FXML private TextField stageBar;
	private Order order = tempOrder.getTempOrder();

	@Override
	/*
	 * This method sets the TextField contents to the values that are currently stored
	 * in that specific order.
	 */
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			customerNameBar.setText(order.getCustomer().getName());
			dateOrderedPicker.setValue(toLocalDate(order.getOrderDate()));
			orderNumBar.setText(""+order.getOrderNum());
			description.setText(order.getDescription());
			price.setText(""+order.getPrice());
			stageBar.setText(""+order.getStage());
		} catch (Exception e){
			e.printStackTrace();
		}
		try {
		
		dueDatePicker.setValue(toLocalDate(order.getDueDate()));
		
	} catch (Exception e){
		
		System.out.println("This order dont have dueDate!");
	}
	
	
	//Why did we have this extra try/catch?
		//AN: dueDate might not be available (it optional), so if we dont have 
		//separate try/catch, program will fail when it try to read order without dueDate
		
	}
	public static LocalDate toLocalDate(Date date){
		LocalDate local = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return local;
	}
	public void saveNewOrder(ActionEvent event) {
		//INCOMPLETE CONSTRUCTOR CALL. Need to somehow convert DatePicker into Date
		//An: toDate method is a static method in NewOrderController
		
			try {
				Double.parseDouble(price.getText());
				
			} catch(NumberFormatException e) { 
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Null");
				alert.setContentText("Price must be number!!");

				alert.showAndWait();
		    } 
			try {
				Integer.parseInt(stageBar.getText());
				
			} catch(NumberFormatException e ) { 
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Null");
				alert.setContentText("Stage must be number (from 0 to 3)!!");

				alert.showAndWait();
		    } 
			// Why is this if statement here? Is this duplicate code?
			// An: It kind of duplicate, but it needed for now,
			// the try/catch check if the stage is int type or not, if statement
			// check if it from 0 to 3 
			// this will not happen after we change stage choice to drop down box
			if (!(Integer.parseInt(stageBar.getText())>=0&&Integer.parseInt(stageBar.getText())<4)){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Null");
				alert.setContentText("Stage must be number (from 0 to 3)!!");

				alert.showAndWait();
			} else {
			
			
			/*if (orderNumBar.getText().equals("0")){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Null");
				alert.setContentText("Generate Order Number!");
				generateOrderNum(new ActionEvent());
				alert.showAndWait();
			}else {*/
				
				// Sends string data from TextFields to the tempOrder class for storage.
				if (dueDatePicker.getValue() != null){
			
					tempOrder.getTempOrder().setOrder(TempCustomer.getTempCustomer(), NewOrderController.toDate(dateOrderedPicker), NewOrderController.toDate(dueDatePicker), description.getText(), Double.parseDouble(price.getText()), Integer.parseInt(stageBar.getText()));
					System.out.println(order);
				} else {
					tempOrder.getTempOrder().setOrder(TempCustomer.getTempCustomer(), NewOrderController.toDate(dateOrderedPicker),description.getText(), Double.parseDouble(price.getText()), Integer.parseInt(stageBar.getText()));
					System.out.println(order);
				}
				//just to print out thing for now
				
				for (Order order: AllOrders.getOrders()){
					System.out.println("all order: "+order);
				}
				Node  source = (Node)  event.getSource(); 
				Stage stage  = (Stage) source.getScene().getWindow();
				stage.close();
			}
			
			
			
		        
		
				


		//Order newOrder = new Order (customerNameBar.getText(), date, Integer.parseInt(orderNumBar.getText()));
		//System.out.println(newOrder);

	}
	//sets default editing to false
	public void editOrder(ActionEvent event) {
		
		dateOrderedPicker.setDisable(false);
		saveOrder.setDisable(false);
		dueDatePicker.setDisable(false);
		description.setDisable(false);
		price.setDisable(false);
		stageBar.setDisable(false);
	}
	
	//this allows user to edit customers
	public void viewCustomer(ActionEvent event) throws IOException {
		TempCustomer.setTempCustomer(order.getCustomer());
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(EditCustomerGUI.class.getResource("EditCustomer.fxml"));
		stage.setScene(new Scene(root));
	    stage.setTitle("View Customer");
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.initOwner(viewCustomerButton.getScene().getWindow());
	    stage.showAndWait();
	    
	    customerNameBar.setText(TempCustomer.getTempCustomer().getName());
	}
	
	public void callCalculator(ActionEvent event){
		try {
			NewOrderController.callCalculator(event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
