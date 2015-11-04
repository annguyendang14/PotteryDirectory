package data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.EditOrderController;
import javafx.beans.property.*;

public class OrderTable {
	
	private final SimpleStringProperty customerName;
	private final SimpleStringProperty description;
	private final SimpleObjectProperty<LocalDate> dueDate;
	private final SimpleObjectProperty<LocalDate> orderDate;
	private final SimpleStringProperty price;
	private final SimpleIntegerProperty stage;
	private final SimpleIntegerProperty orderNum;
	
	public OrderTable(Order order) {
		this.customerName = new SimpleStringProperty(checkNull(order.getCustomer().getName()));
		this.description = new SimpleStringProperty(checkNull(order.getDescription()));
		if (order.getDueDate()!=null){
			this.dueDate = new SimpleObjectProperty<LocalDate>(EditOrderController.toLocalDate(order.getDueDate()));
		} else {
			this.dueDate = null;
		}
		
		this.orderDate = new SimpleObjectProperty<LocalDate>(EditOrderController.toLocalDate(order.getOrderDate()));
		this.price = new SimpleStringProperty(checkNull(""+order.getFinalPrice()));
		this.stage = new SimpleIntegerProperty(order.getStage());
		this.orderNum = new SimpleIntegerProperty(order.getOrderNum());
	}
	public String getCustomerName() {
		return customerName.get();
	}
	public String getDescription() {
		return description.get();
	}
	public LocalDate getDueDate() {
		if (dueDate!=null){
			return dueDate.get();
			
		} else {
			return null;
		}
	}
	public LocalDate getOrderDate() {
		return orderDate.get();
	}
	public String getPrice() {
		return price.get();
	}
	public int getStage() {
		return stage.get();
	}
	public int getOrderNum() {
		return orderNum.get();
	}
	private static String checkNull(String s){
		
	
		if (s.equals("null")){
			return "N/a";
		} else {
			return s.toString();
		}
	}
	public static List<OrderTable> toOrderTable(List<Order> orders){
		List<OrderTable> orderTable = new ArrayList<OrderTable>();
		for (Order order: orders){
			orderTable.add(new OrderTable(order));
		}
		return orderTable;
	}


	

}
