package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import GUI.EditCustomerGUI;
import data.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
	// @FXML private TextField stageBar;
	@FXML private ChoiceBox<String> stageBox;
	ObservableList<String> stageList = FXCollections.observableArrayList("Undone", "Done", "Shipped", "Completed", "Canceled");
	private Order order = TempOrder.getTempOrder();

	@Override
	/*
	 * This method sets the TextField contents to the values that are currently stored
	 * in that specific order.
	 */
	public void initialize(URL location, ResourceBundle resources) {
		
		stageBox.setValue(Order.convertStageNumberToStageName(order.getStage()));
		stageBox.setItems(stageList);

		try {
			customerNameBar.setText(order.getCustomer().getName());
			dateOrderedPicker.setValue(toLocalDate(order.getOrderDate()));
			orderNumBar.setText(""+order.getOrderNum());
			description.setText(order.getDescription());
			price.setText(""+order.getPrice());
			//stageBar.setText(""+order.getStage());
			stageBox.setValue(""+order.getStage());  // Don't know if it's correct
		} catch (Exception e){
			e.printStackTrace();
		}
		if (order.getDueDate() != null) {
			dueDatePicker.setValue(toLocalDate(order.getDueDate()));
		}		    
	
		TempCustomer.setTempCustomer(order.getCustomer());
		
		   
	
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
			
			/*
			try {
				Integer.parseInt(stageBar.getText());
				
				
			} catch(NumberFormatException e ) { 
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Null");
				alert.setContentText("Stage must be number (from 0 to 4)!!");

				alert.showAndWait();
		    } 
			
			*/
			
			// Why is this if statement here? Is this duplicate code?
			// An: It kind of duplicate, but it needed for now,
			// the try/catch check if the stage is int type or not, if statement
			// check if it from 0 to 3 
			// this will not happen after we change stage choice to drop down box
			
			
			
			/*
			if (!(Integer.parseInt(stageBar.getText())>=0&&Integer.parseInt(stageBar.getText())<5)){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Null");
				alert.setContentText("Stage must be number (from 0 to 4)!!");

				alert.showAndWait();
			} else {
			
			*/
			
			
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
					if (dueDatePicker.getValue().compareTo(dateOrderedPicker.getValue())<=0){
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Warning Dialog");
						
						alert.setContentText("Please choose due date later than order date!");

						alert.showAndWait();
						return;
						
					}else {
			
						TempOrder.getTempOrder().setOrder(TempCustomer.getTempCustomer(), NewOrderController.toDate(dateOrderedPicker), NewOrderController.toDate(dueDatePicker), description.getText(), Double.parseDouble(price.getText()), Order.convertStageNameToStageNumber((String)stageBox.getValue()) );
						// OrderViewerControler.stageToInt(stageBar.getValue())
						// Integer.parseInt(stageBar.getText())
						System.out.println(order);
					}
				} else {
					TempOrder.getTempOrder().setOrder(TempCustomer.getTempCustomer(), NewOrderController.toDate(dateOrderedPicker),description.getText(), Double.parseDouble(price.getText()), Order.convertStageNameToStageNumber((String)stageBox.getValue()));
					System.out.println(order);
				}
				//just to print out thing for now
				
				for (Order order: AllOrders.getOrders()){
					System.out.println("all order: "+order +order.getCustomer());
				}
				Node  source = (Node)  event.getSource(); 
				Stage stage  = (Stage) source.getScene().getWindow();
				stage.close();
		
			
			
		        
		
				


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
		// stageBar.setDisable(false);
		stageBox.setDisable(false);
	}
	
	//this allows user to edit customers
	public void viewCustomer(ActionEvent event) throws IOException {
		
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(EditCustomerGUI.class.getResource("EditCustomer.fxml"));
		stage.setScene(new Scene(root));
	    stage.setTitle("View Customer");
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.initOwner(viewCustomerButton.getScene().getWindow());
	    stage.showAndWait();
	    
	    customerNameBar.setText(TempCustomer.getTempCustomer().getName());
	}
	

}
