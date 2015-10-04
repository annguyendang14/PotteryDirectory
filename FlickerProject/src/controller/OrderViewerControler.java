package controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.util.Callback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import main.*;

public class OrderViewerControler implements Initializable{
	@FXML private TableView<OrderTable> orderTable;
	@FXML private TableColumn<OrderTable,String> orderNumCol;
	@FXML private TableColumn<OrderTable,String>  orderDateCol;
	@FXML private TableColumn<OrderTable,String>  dueDateCol;
	@FXML private TableColumn<OrderTable,String>  customerCol;
	@FXML private TableColumn<OrderTable,String>  descriptionCol;
	@FXML private TableColumn<OrderTable,String>  priceCol;
	@FXML private TableColumn<OrderTable,String>  stageCol;
	@FXML private Button addOrderButton;
	@FXML private TextField searchBox;
	ObservableList<OrderTable> data;
	
	public void addNewOrder(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(NewOrderGUI.class.getResource("NewOrder.fxml"));
		stage.setScene(new Scene(root));
	    stage.setTitle("Add New Order");
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.initOwner(addOrderButton.getScene().getWindow());
	    stage.showAndWait();
	    data = FXCollections.observableArrayList(AllOrders.getOrderTable());
	    updateTable();
	    OrderFileReader.write(AllOrders.getOrders());
	}
	
	public void showOrdersInCollum(ActionEvent event){
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*try {
			AllOrders.getOrders().addAll(OrderFileReader.read());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			for (Order order: OrderFileReader.read()){
				AllOrders.getOrders().add(order);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data = FXCollections.observableArrayList(AllOrders.getOrderTable());
		
		/*orderNumCol.setCellFactory(TextFieldTableCell.forTableColumn());
		dueDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
		customerCol.setCellFactory(TextFieldTableCell.forTableColumn());
		descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		priceCol.setCellFactory(TextFieldTableCell.forTableColumn());
		stageCol.setCellFactory(TextFieldTableCell.forTableColumn());*/
		updateTable();
		
		
	}
	
	public void updateTable(){
		customerCol.setCellValueFactory(
			    new PropertyValueFactory<OrderTable,String>("customerName")
			);
		orderDateCol.setCellValueFactory(
			    new PropertyValueFactory<OrderTable,String>("orderDate")
			);
		dueDateCol.setCellValueFactory(
			    new PropertyValueFactory<OrderTable,String>("dueDate")
			);
		orderNumCol.setCellValueFactory(
			    new PropertyValueFactory<OrderTable,String>("orderNum")
			);
		descriptionCol.setCellValueFactory(
			    new PropertyValueFactory<OrderTable,String>("description")
			);
		priceCol.setCellValueFactory(
			    new PropertyValueFactory<OrderTable,String>("price")
			);
		stageCol.setCellValueFactory(
			    new PropertyValueFactory<OrderTable,String>("stage")
			);
		orderTable.setItems(data);
		
	}
	
	public void searchBoxTypeIn(ActionEvent event){
		
		data = FXCollections.observableArrayList(OrderTable.toOrderTable(Searcher.searchOrder(AllOrders.getOrders(), searchBox.getText())));
		updateTable();
		
	}
	public void editTable(ActionEvent event){
		List<Order> orders = new ArrayList<Order>();
		int i=0;
		while (!orderNumCol.getCellData(i).isEmpty()){
			if (!dueDateCol.getCellData(i).equals("null")&&!dueDateCol.getCellData(i).equals("N/a")){
				orders.add(new Order(customerCol.getCellData(i), new Date(orderDateCol.getCellData(i)), new Date(dueDateCol.getCellData(i)), Integer.parseInt(orderNumCol.getCellData(i)), descriptionCol.getCellData(i), Double.parseDouble(priceCol.getCellData(i)), Integer.parseInt(stageCol.getCellData(i))));
			} else {
				orders.add(new Order(customerCol.getCellData(i), new Date(orderDateCol.getCellData(i)),  Integer.parseInt(orderNumCol.getCellData(i)), descriptionCol.getCellData(i), Double.parseDouble(priceCol.getCellData(i)), Integer.parseInt(stageCol.getCellData(i))));

			}
			i++;
		}
		try {
			OrderFileReader.write(orders);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AllOrders.getOrders().clear();
		try {
			for (Order order: OrderFileReader.read()){
				AllOrders.getOrders().add(order);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data = FXCollections.observableArrayList(AllOrders.getOrderTable());
		updateTable();
	}
	
	

}
