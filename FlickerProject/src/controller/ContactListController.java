package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import CSV.CustomerFileReader;
import CSV.OrderFileReader;
import GUI.EditCustomerGUI;
import GUI.EditOrderGUI;
import controller.OrderViewerControler.MyEventHandler;
import controller.OrderViewerControler.MyStringTableCell;
import data.AllCustomer;
import data.AllOrders;
import data.Customer;
import data.CustomerTable;
import data.Order;
import data.OrderTable;
import data.TempCustomer;
import data.TempOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import searcherAndSorter.Searcher;

public class ContactListController implements Initializable{
	@FXML private TableView<CustomerTable> customerTable;
	@FXML private TableColumn nameCol;
	@FXML private TableColumn addressCol;
	@FXML private TableColumn phoneCol;
	@FXML private TableColumn emailCol;
	ObservableList<CustomerTable> data;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
		data = FXCollections.observableArrayList(AllCustomer.getCustomerTable());
		//http://java-buddy.blogspot.com/2013/05/detect-mouse-click-on-javafx-tableview.html
		 Callback<TableColumn, TableCell> stringCellFactory =
	                new Callback<TableColumn, TableCell>() {
	            @Override
	          //TODO: need to change to double-click
	            public TableCell call(TableColumn p) {
	                MyStringTableCell cell = new MyStringTableCell();
	                cell.setFont(new Font("Arial", 16));
	                cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
	                return cell;
	            }
	        };
	        nameCol.setCellFactory(stringCellFactory);
	        addressCol.setCellFactory(stringCellFactory);
	        phoneCol.setCellFactory(stringCellFactory);
	        emailCol.setCellFactory(stringCellFactory);
	        
	        nameCol.setCellValueFactory(
				    new PropertyValueFactory<CustomerTable,String>("name")
				);
	        addressCol.setCellValueFactory(
				    new PropertyValueFactory<CustomerTable,String>("address")
				);
	        phoneCol.setCellValueFactory(
				    new PropertyValueFactory<CustomerTable,String>("phone")
				);
	        emailCol.setCellValueFactory(
				    new PropertyValueFactory<CustomerTable,String>("email")
				);
		
	}
	
	class MyEventHandler implements EventHandler<MouseEvent> {
		  
        @Override
        public void handle(MouseEvent t) {
        	if (t.getClickCount()%2==0){ //double click
	            TableCell c = (TableCell) t.getSource();
	            int index = c.getIndex();
	            TempCustomer.setTempCustomer(AllCustomer.findCustomer(data.get(index).getCode()));
	            Stage stage = new Stage();
	    		Parent root;
				try {
					root = FXMLLoader.load(EditCustomerGUI.class.getResource("EditCustomer.fxml"));
					stage.setScene(new Scene(root));
		    	    stage.setTitle("View Customer");
		    	    stage.initModality(Modality.APPLICATION_MODAL);
		    	    stage.initOwner(c.getScene().getWindow());
		    	    stage.showAndWait();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
        	}
        }
    }
	
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
