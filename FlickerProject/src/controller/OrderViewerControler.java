package controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import CSV.CustomerFileReader;
import CSV.OrderFileReader;
import GUI.EditOrderGUI;
import GUI.NewOrderGUI;
import data.AllCustomer;
import data.AllOrders;
import data.Customer;
import data.Order;
import data.OrderTable;
import data.tempOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import searcherAndSorter.Searcher;

public class OrderViewerControler implements Initializable{
	@FXML private TableView<OrderTable> orderTable;
	@FXML private TableColumn orderNumCol;
	@FXML private TableColumn  orderDateCol;
	@FXML private TableColumn  dueDateCol;
	@FXML private TableColumn customerCol;
	@FXML private TableColumn  descriptionCol;
	@FXML private TableColumn  priceCol;
	@FXML private TableColumn stageCol;
	@FXML private Button addOrderButton;
	@FXML private TextField searchBox;
	ObservableList<OrderTable> data;
	
	/*
	 * This method saves a new order to orders
	 */
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
	    
	}
	
	public void showOrdersInCollum(ActionEvent event){
		
		
	}

	@Override
	/*
	 * This method opens the order window when the row is clicked.
	 */
	public void initialize(URL location, ResourceBundle resources) {
		/*try {
			AllOrders.getOrders().addAll(OrderFileReader.read());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			for (Customer customer: CustomerFileReader.read()){
				AllCustomer.getCustomers().add(customer);
			}
			try {
				for (Order order: OrderFileReader.read(AllCustomer.getCustomers())){
					AllOrders.getOrders().add(order);
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		data = FXCollections.observableArrayList(AllOrders.getOrderTable());
		//http://java-buddy.blogspot.com/2013/05/detect-mouse-click-on-javafx-tableview.html
		 Callback<TableColumn, TableCell> stringCellFactory =
	                new Callback<TableColumn, TableCell>() {
	            @Override
	          //TODO: need to change to double-click
	            public TableCell call(TableColumn p) {
	                MyStringTableCell cell = new MyStringTableCell();
	                cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
	                return cell;
	            }
	        };
		/*FormattedTableCellFactory cellFac = new FormattedTableCellFactory();
		cellFac.setAlignment(TextAlignment.LEFT);*/
		orderNumCol.setCellFactory(stringCellFactory);
		dueDateCol.setCellFactory(stringCellFactory);
		customerCol.setCellFactory(stringCellFactory);
		descriptionCol.setCellFactory(stringCellFactory);
		priceCol.setCellFactory(stringCellFactory);
		stageCol.setCellFactory(stringCellFactory);
		updateTable();
		
		
	}
	/*
	 * This method updates edited information to the table
	 */
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
		
		try {
			CustomerFileReader.write(AllCustomer.getCustomers());
			OrderFileReader.write(AllOrders.getOrders());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*
	 * This method searches for Customer names or order numbers
	 */
	public void searchBoxTypeIn(ActionEvent event){
		
		data = FXCollections.observableArrayList(OrderTable.toOrderTable(Searcher.searchOrder(AllOrders.getOrders(), searchBox.getText())));
		updateTable();
		
	}
	public void editTable(ActionEvent event){
		/*List<Order> orders = new ArrayList<Order>();
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
		updateTable();*/
	}
	//http://java-buddy.blogspot.com/2013/05/detect-mouse-click-on-javafx-tableview.html

	/*
	 * This method lets the user edit an existent order
	 */
	class MyEventHandler implements EventHandler<MouseEvent> {
		  
	        @Override
	        public void handle(MouseEvent t) {
	            TableCell c = (TableCell) t.getSource();
	            int index = c.getIndex();
	            tempOrder.setTempOrder(Searcher.searchForOrder(AllOrders.getOrders(),Integer.parseInt(data.get(index).getOrderNum())));
	            Stage stage = new Stage();
	    		Parent root;
				try {
					root = FXMLLoader.load(EditOrderGUI.class.getResource("EditOrder.fxml"));
					stage.setScene(new Scene(root));
		    	    stage.setTitle("View Customer");
		    	    stage.initModality(Modality.APPLICATION_MODAL);
		    	    stage.initOwner(c.getScene().getWindow());
		    	    stage.showAndWait();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				data = FXCollections.observableArrayList(AllOrders.getOrderTable());
				updateTable();
	    		
	        }
	    }
	
	//http://java-buddy.blogspot.com/2013/05/detect-mouse-click-on-javafx-tableview.html

	/* 
	 * This is actually a new class
	 */
	 class MyStringTableCell extends TableCell<OrderTable, String> {
		 
	        @Override
	        public void updateItem(String item, boolean empty) {
	            super.updateItem(item, empty);
	            setText(empty ? null : getString());
	            setGraphic(null);
	        }
	 
	        private String getString() {
	            return getItem() == null ? "" : getItem().toString();
	        }
	    }
	  
	
	

}
