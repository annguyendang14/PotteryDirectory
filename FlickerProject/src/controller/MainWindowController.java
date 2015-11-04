package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.ResourceBundle;

import CSV.CustomerFileReader;
import CSV.OrderFileReader;
import GUI.AboutScreenGUI;
import GUI.ContactListGUI;
import GUI.EditOrderGUI;
import GUI.NewCustomerGUI;
import GUI.NewOrderGUI;
import data.AllCustomer;
import data.AllOrders;
import data.Customer;
import data.Order;
import data.OrderTable;
import data.TempOrder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import searcherAndSorter.DefaultSortingComparator;
import searcherAndSorter.Searcher;

public class MainWindowController implements Initializable {
	@FXML
	private TableView<OrderTable> orderTable;
	@FXML
	private TableColumn orderNumCol;
	@FXML
	private TableColumn orderDateCol;
	@FXML
	private TableColumn dueDateCol;
	@FXML
	private TableColumn customerCol;
	@FXML
	private TableColumn descriptionCol;
	@FXML
	private TableColumn priceCol;
	@FXML
	private TableColumn stageCol;
	@FXML
	private Button addOrderButton;
	@FXML
	private Button aboutWindowButton;
	@FXML
	private Button contactButton;
	@FXML
	private TextField searchBox;
	ObservableList<OrderTable> data;
	@FXML
	private ChoiceBox<String> stageBox;
	ObservableList<String> stageList = FXCollections.observableArrayList("All", "Undone", "Done", "Shipped",
			"Completed", "Canceled");

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

		Collections.sort(AllOrders.getOrders(), new DefaultSortingComparator());
		data = FXCollections.observableArrayList(AllOrders.getOrderTable());
		updateTable();

	}
	
	public void openContactList(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(ContactListGUI.class.getResource("ContactList.fxml"));
		stage.setScene(new Scene(root));
	    stage.setTitle("Add New Customer");
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.initOwner(contactButton.getScene().getWindow());
	    stage.showAndWait();
	    
	    Collections.sort(AllOrders.getOrders(), new DefaultSortingComparator());
		data = FXCollections.observableArrayList(AllOrders.getOrderTable());
		updateTable();
	}

	public void callAboutWindow(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(AboutScreenGUI.class.getResource("AboutScreen.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("About window");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(aboutWindowButton.getScene().getWindow());
		stage.showAndWait();
	}

	public void showOrdersInCollum(ActionEvent event) {

	}

	@Override
	/*
	 * This method opens the order window when the row is clicked.
	 */
	public void initialize(URL location, ResourceBundle resources) {
		stageBox.setValue("All");
		stageBox.setItems(stageList);
		stageBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> obsValue, String oldSelectedStage,
					String newSelectedStage) {
				searchForOrdersByStage(newSelectedStage);
			}

		});

		try {
			for (Customer customer : CustomerFileReader.read()) {
				AllCustomer.getCustomers().add(customer);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			for (Order order : OrderFileReader.read(AllCustomer.getCustomers())) {
				AllOrders.getOrders().add(order);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.sort(AllOrders.getOrders(), new DefaultSortingComparator());
		data = FXCollections.observableArrayList(AllOrders.getOrderTable());
		// http://java-buddy.blogspot.com/2013/05/detect-mouse-click-on-javafx-tableview.html
		Callback<TableColumn, TableCell> stringCellFactory = new Callback<TableColumn, TableCell>() {
			@Override
			public TableCell call(TableColumn p) {
				MyStringTableCell cell = new MyStringTableCell();
				cell.setFont(new Font("Arial", 16));
				cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
				return cell;
			}
		};

		Callback<TableColumn, TableCell> integerCellFactory = new Callback<TableColumn, TableCell>() {
			@Override
			public TableCell call(TableColumn p) {
				MyIntegerTableCell cell = new MyIntegerTableCell();
				cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
				return cell;
			}
		};

		Callback<TableColumn, TableCell> stageCellFactory = new Callback<TableColumn, TableCell>() {
			@Override
			public TableCell call(TableColumn p) {
				MyStageTableCell cell = new MyStageTableCell();
				cell.setFont(new Font("Arial", 16));
				cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
				return cell;
			}
		};
		
		Callback<TableColumn, TableCell> doubleCellFactory = new Callback<TableColumn, TableCell>() {
			@Override
			public TableCell call(TableColumn p) {
				MyDoubleTableCell cell = new MyDoubleTableCell();
				cell.setFont(new Font("Arial", 16));
				cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
				return cell;
			}
		};

		Callback<TableColumn, TableCell> dateCellFactory = new Callback<TableColumn, TableCell>() {
			@Override
			public TableCell call(TableColumn p) {
				MyDateTableCell cell = new MyDateTableCell();
				cell.setFont(new Font("Arial", 16));
				cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
				return cell;
			}
		};

		/*
		 * FormattedTableCellFactory cellFac = new FormattedTableCellFactory();
		 * cellFac.setAlignment(TextAlignment.LEFT);
		 */
		orderNumCol.setCellFactory(integerCellFactory);
		orderDateCol.setCellFactory(dateCellFactory);
		dueDateCol.setCellFactory(dateCellFactory);
		customerCol.setCellFactory(stringCellFactory);
		descriptionCol.setCellFactory(stringCellFactory);
		priceCol.setCellFactory(doubleCellFactory);
		stageCol.setCellFactory(stageCellFactory);
		updateTable();
	}

	/*
	 * This method updates edited information to the table
	 */
	public void updateTable() {
		customerCol.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("customerName"));
		orderDateCol.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("orderDate"));
		dueDateCol.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("dueDate"));
		orderNumCol.setCellValueFactory(new PropertyValueFactory<OrderTable, Integer>("orderNum"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("description"));
		priceCol.setCellValueFactory(new PropertyValueFactory<OrderTable, Double>("price"));
		stageCol.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("stage"));
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
	 * 
	 * @param event activates when user presses enter in search box
	 */
	public void searchBoxTypeIn(ActionEvent event) {

		data = FXCollections.observableArrayList(
				OrderTable.toOrderTable(Searcher.searchOrder(AllOrders.getOrders(), searchBox.getText())));
		updateTable();

	}

	/*
	 * This method searches for orders by different stages
	 * 
	 * @param stageName String that contains the stage that the order is currently at
	 */
	private void searchForOrdersByStage(String stageName) {

		if (stageName.equals("All")) {
			data = FXCollections.observableArrayList(AllOrders.getOrderTable());
			updateTable();
		} else {
			data = FXCollections.observableArrayList(OrderTable.toOrderTable(
					Searcher.searchStageOrder(AllOrders.getOrders(), Order.convertStageNameToStageNumber(stageName))));
			updateTable();
		}
	}

	
	// http://java-buddy.blogspot.com/2013/05/detect-mouse-click-on-javafx-tableview.html

	/*
	 * This method lets the user edit an existent order
	 */
	class MyEventHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent t) {
			if (t.getClickCount() % 2 == 0) { // double click
				TableCell c = (TableCell) t.getSource();
				int index = c.getIndex();
				TempOrder.setTempOrder(Searcher.searchForOrder(AllOrders.getOrders(), (data.get(index).getOrderNum())));
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
	}

	// http://java-buddy.blogspot.com/2013/05/detect-mouse-click-on-javafx-tableview.html

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

	class MyIntegerTableCell extends TableCell<OrderTable, Integer> {

		@Override
		public void updateItem(Integer item, boolean empty) {
			super.updateItem(item, empty);
			setText(empty ? null : getString());
			setGraphic(null);
		}

		public String getString() {
			return getItem() == null ? "" : getItem().toString();
		}
	}

	class MyDoubleTableCell extends TableCell<OrderTable, Double> {

		@Override
		public void updateItem(Double item, boolean empty) {
			super.updateItem(item, empty);
			setText(empty ? null : getString());
			setGraphic(null);
		}

		public String getString() {
			String strDouble = String.format("%.2f", getItem());
			return getItem() == null ? "N/a" : strDouble;
		}
	}

	class MyStageTableCell extends MyIntegerTableCell {
		public String getString() {
			return getItem() == null ? "" : Order.convertStageNumberToStageName(getItem());
		}
	}

	class MyDateTableCell extends TableCell<OrderTable, LocalDate> {

		@Override
		public void updateItem(LocalDate item, boolean empty) {
			super.updateItem(item, empty);
			setText(empty ? null : getString());
			setGraphic(null);
		}

		private String getString() {
			LocalDate item = (LocalDate) getItem();
			String dateFormated = null;
			if (item != null) {
				String month;
				if (item.getMonthValue() < 10) {
					month = "0" + item.getMonthValue();
				} else {
					month = "" + item.getMonthValue();
				}
				String day;
				if (item.getDayOfMonth() < 10) {
					day = "0" + item.getDayOfMonth();
				} else {
					day = "" + item.getDayOfMonth();
				}

				dateFormated = "" + month + "/" + day + "/" + item.getYear();
			}
			return dateFormated == null ? "N/a" : dateFormated;
		}
	}

}
