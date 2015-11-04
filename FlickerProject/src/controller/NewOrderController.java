package controller;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import GUI.ContactListGUI;
import GUI.NewCustomerGUI;
import data.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;

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
	@FXML private Button addCustomerButton;
	@FXML private Label warningLabel;
	@FXML private Label totalPrice;
	@FXML private TextField taxRate;
	@FXML private Label priceAfterTax;
	@FXML private RadioButton shippingChoice;
	@FXML private TextField shippingCost;
	@FXML private Label finalPrice;
	@FXML private TextArea shippingAddress;
	@FXML private Button useCusAddButton;
	@FXML private ToggleGroup shippingOption;



	/**
	 * this method call when saveOrder button clicked, it save the order and close the window
	 * @param event
	 */
	public void saveNewOrder(ActionEvent event) {
					
				Order newOrder;
			
				if(dueDatePicker.getValue() != null){
					if (dueDatePicker.getValue().compareTo(dateOrderedPicker.getValue())<=0){
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Warning Dialog");
						
						alert.setContentText("Please choose due date later than order date!");

						alert.showAndWait();
						return;
						
					}else {//add new order with dueDate
						newOrder = new Order (TempCustomer.getTempCustomer(), toDate(dateOrderedPicker), toDate(dueDatePicker), Integer.parseInt(orderNumBar.getText()), description.getText(), price.getText(), shippingChoice.selectedProperty().getValue(), shippingAddress.getText(), Double.parseDouble(shippingCost.getText()), Double.parseDouble(taxRate.getText()));
						

					}
			
					
				} else {//add new order w/o dueDate
					newOrder = new Order (TempCustomer.getTempCustomer(), toDate(dateOrderedPicker), Integer.parseInt(orderNumBar.getText()), description.getText(), price.getText(), shippingChoice.selectedProperty().getValue(), shippingAddress.getText(), Double.parseDouble(shippingCost.getText()), Double.parseDouble(taxRate.getText()));
					
				}
				//just to print out thing for now
				AllOrders.getOrders().add(newOrder);
			
				Node  source = (Node)  event.getSource(); 
				Stage stage  = (Stage) source.getScene().getWindow();
				stage.close();
			
		
	}

	// http://stackoverflow.com/questions/20446026/get-value-from-date-picker
	/**
	 * this method convert LocalDate (value of DatePicker) to Date
	 * @param datePick
	 * @return
	 */
		public static Date toDate(DatePicker datePick) {
			LocalDate localDate = datePick.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			Date date = Date.from(instant);
			return date;
		}
	
	/**
	 * this method call when user click on Add Customer button
	 * @param event
	 * @throws IOException
	 */
	public void addNewCustomer(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		
		
		alert.setContentText("Would you want to add an existing customer in Contact List?");

		ButtonType buttonAddFromContact = new ButtonType("Add from Contact List");
		ButtonType buttonAddNewCus = new ButtonType("Add New Customer");
		
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonAddFromContact, buttonAddNewCus, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonAddFromContact){
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(ContactListGUI.class.getResource("ContactList.fxml"));
			stage.setScene(new Scene(root));
		    stage.setTitle("Add New Customer");
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.initOwner(addCustomerButton.getScene().getWindow());
		    stage.showAndWait();
		    
		    try {
				customerNameBar.setText(TempCustomer.getTempCustomer().getName());
				warningLabel.setText("");
				saveOrder.setDisable(false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (result.get() == buttonAddNewCus) {
		    
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
		    
		} else {
		    // ... user chose CANCEL or closed the dialog
		}
		
	}
	

	/**
	 * this call when user clicked on use customer address button, ato coppy customer address to shipping address
	 * @param event
	 */
	public void useCusAdd(ActionEvent event) {
		shippingAddress.setText(TempCustomer.getTempCustomer().getName()+"\n"+TempCustomer.getTempCustomer().getAddress());
	}
	/**
	 * this calculate the price, call when change in price TextField
	 * @param event
	 */
	public void calPrice(ActionEvent event) {
		double calculated=0.0;
		
		try {
			calculated = Calculator.StringCalculator(price.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String calculatedString =  String.format("%.2f", calculated);
		totalPrice.setText(calculatedString);
		calTax(new ActionEvent());
		finalPrice.setText(calFinalPrice());
		
	}
	/**
	 * this method calculate tax, call when change in taxRate TextField
	 * @param event
	 */
	public void calTax(ActionEvent event) {
		String calculatedString =  String.format("%.2f", Double.parseDouble(totalPrice.getText())*Double.parseDouble(taxRate.getText())/100);
		priceAfterTax.setText(calculatedString);
		finalPrice.setText(calFinalPrice());
		
	}
	/**
	 * this cal when enter shipping cost
	 */
	public void calPriceWithShipping(ActionEvent event) {
		finalPrice.setText(calFinalPrice());
	}
	/**
	 * this calculate the final price
	 * @return String for finnalPrice, formated
	 */
	public String calFinalPrice(){
		double total = 0;
		double tax = 0;
		double ship = 0;
		try {
			total = Double.parseDouble(totalPrice.getText());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			tax = Double.parseDouble(priceAfterTax.getText());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ship = Double.parseDouble(shippingCost.getText());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.format("%.2f", total + tax +ship);
	}
	
	@Override
	/**
	 * setup window when it openned, add action listener to the RadioButton
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {
		dateOrderedPicker.setValue(LocalDate.now());
		orderNumBar.setText(AllOrders.getOrders().size()+1+"");
		taxRate.setText("0");
		shippingCost.setText("0.00");
		//https://docs.oracle.com/javafx/2/ui_controls/radio-button.htm
		shippingOption.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		        Toggle old_toggle, Toggle new_toggle) {
		            if (shippingOption.getSelectedToggle() == shippingChoice) {
		            	shippingCost.setDisable(false);
		        		shippingAddress.setDisable(false);
		        		useCusAddButton.setDisable(false);
		        		
		            } else {
		            	shippingCost.setDisable(true);
		        		shippingAddress.setDisable(true);
		        		useCusAddButton.setDisable(true);
		            }
		        }
		});
		
	}
	
	
}
