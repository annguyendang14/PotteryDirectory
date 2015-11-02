package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import GUI.EditCustomerGUI;
import GUI.PrintViewGUI;
import data.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditOrderController implements Initializable {
	@FXML
	private Label customerName;
	@FXML
	private Label dateOrdered;
	@FXML
	private Label orderNum;
	@FXML
	private TextField customerNameBar;
	@FXML
	private DatePicker dateOrderedPicker;
	@FXML
	private Label orderNumBar;
	@FXML
	private Button saveOrder;
	@FXML
	private Button printButton;
	@FXML
	private DatePicker dueDatePicker;
	@FXML
	private TextArea description;
	@FXML
	private TextField price;
	// @FXML private Button generateButton;
	@FXML
	private Button viewCustomerButton;
	@FXML
	private Label totalPrice;
	@FXML
	private TextField taxRate;
	@FXML
	private Label priceAfterTax;
	@FXML
	private RadioButton shippingChoice;
	@FXML
	private RadioButton pickup;
	@FXML
	private TextField shippingCost;
	@FXML
	private Label finalPrice;
	@FXML
	private TextArea shippingAddress;
	@FXML
	private Button useCusAddButton;
	@FXML
	private ToggleGroup shippingOption;
	@FXML
	private Button editButton;
	// @FXML private TextField stageBar;
	@FXML
	private ChoiceBox<String> stageBox;
	ObservableList<String> stageList = FXCollections.observableArrayList("Undone", "Done", "Shipped", "Completed",
			"Canceled");
	private Order order = TempOrder.getTempOrder();

	@Override
	/*
	 * This method sets the TextField contents to the values that are currently
	 * stored in that specific order.
	 */
	public void initialize(URL location, ResourceBundle resources) {

		stageBox.setValue(Order.convertStageNumberToStageName(order.getStage()));
		stageBox.setItems(stageList);

		try {
			customerNameBar.setText(order.getCustomer().getName());
			dateOrderedPicker.setValue(toLocalDate(order.getOrderDate()));
			orderNumBar.setText("" + order.getOrderNum());
			description.setText(order.getDescription());
			price.setText("" + order.getPrice());
			// stageBar.setText(""+order.getStage());
			stageBox.setValue("" + order.getStage()); // Don't know if it's
														// correct
			totalPrice.setText("" + order.getPrice());
			taxRate.setText(order.getTaxRate() + "");
			shippingCost.setText(order.getShippingCost() + "");
			shippingAddress.setText(order.getShippingAddress());
			if (order.isNeedShip()) {
				shippingOption.selectToggle(shippingChoice);
			} else {
				shippingOption.selectToggle(pickup);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (order.getDueDate() != null) {
			dueDatePicker.setValue(toLocalDate(order.getDueDate()));
		}

		// https://docs.oracle.com/javafx/2/ui_controls/radio-button.htm
		shippingOption.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
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

		TempCustomer.setTempCustomer(order.getCustomer());

	}

	// This method calculates the cost after tax is applied
	public void calPriceAfterTax(ActionEvent event) {
		String calculatedString = String.format("%.2f",
				Double.parseDouble(totalPrice.getText()) * Double.parseDouble(taxRate.getText()) / 100);
		priceAfterTax.setText(calculatedString);
		finalPrice.setText(calFinalPrice());

	}

	// This method calculates the cost after shipping is applied
	public void calPriceWithShipping(ActionEvent event) {
		finalPrice.setText(calFinalPrice());
	}

	// This method calculates the final price after all taxes and shipping
	// costs.
	public String calFinalPrice() {
		double total = 0;
		double tax = 0;
		double ship = 0;
		try {
			total = Double.parseDouble(totalPrice.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		try {
			tax = Double.parseDouble(priceAfterTax.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		try {
			ship = Double.parseDouble(shippingCost.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return String.format("%.2f", total + tax + ship);
	}

	// http://stackoverflow.com/questions/21242110/convert-java-util-date-to-java-time-localdate
	public static LocalDate toLocalDate(Date date) {
		LocalDate local = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return local;
	}

	public void saveNewOrder(ActionEvent event) {

		if (dueDatePicker.getValue() != null) {
			if (dueDatePicker.getValue().compareTo(dateOrderedPicker.getValue()) <= 0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");

				alert.setContentText("Please choose due date later than order date!");

				alert.showAndWait();
				return;

			} else {

				TempOrder.getTempOrder().setOrder(TempCustomer.getTempCustomer(),
						NewOrderController.toDate(dateOrderedPicker), NewOrderController.toDate(dueDatePicker),
						description.getText(), price.getText(), shippingChoice.selectedProperty().getValue(),
						shippingAddress.getText(), Double.parseDouble(shippingCost.getText()),
						Double.parseDouble(taxRate.getText()),
						Order.convertStageNameToStageNumber((String) stageBox.getValue()));
			}
		} else {
			TempOrder.getTempOrder().setOrder((TempCustomer.getTempCustomer()),
					NewOrderController.toDate(dateOrderedPicker), description.getText(), price.getText(),
					shippingChoice.selectedProperty().getValue(), shippingAddress.getText(),
					Double.parseDouble(shippingCost.getText()), Double.parseDouble(taxRate.getText()),
					Order.convertStageNameToStageNumber((String) stageBox.getValue()));
			System.out.println(order);
		}
		// just to print out thing for now

		for (Order order : AllOrders.getOrders()) {
			System.out.println("all order: " + order + order.getCustomer());
		}
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();

	}

	public void useCusAdd(ActionEvent event) {
		shippingAddress
				.setText(TempCustomer.getTempCustomer().getName() + "\n" + TempCustomer.getTempCustomer().getAddress());
	}

	// sets default editing to false
	public void editOrder(ActionEvent event) {

		dateOrderedPicker.setDisable(false);
		saveOrder.setDisable(false);
		dueDatePicker.setDisable(false);
		description.setDisable(false);
		price.setDisable(false);
		// stageBar.setDisable(false);
		stageBox.setDisable(false);
		taxRate.setDisable(false);
		pickup.setDisable(false);
		shippingChoice.setDisable(false);
		if (shippingOption.getSelectedToggle() == shippingChoice) {
			shippingCost.setDisable(false);
			shippingAddress.setDisable(false);
			useCusAddButton.setDisable(false);
		}

	}

	// this allows user to edit customers
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

	/*
	 * This method loads the PrintViewGUI window for printing the current order.
	 * 
	 * @param event initiates when Print button is clicked on Order window
	 */
	public void print(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(PrintViewGUI.class.getResource("PrintView.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("Print Screen");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(printButton.getScene().getWindow());
		stage.showAndWait();
	}

}
