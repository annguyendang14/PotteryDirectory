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
		//this.customerName = new SimpleStringProperty("????");
		this.customerName = new SimpleStringProperty(checkNull(order.getCustomer().getName()));
		this.description = new SimpleStringProperty(checkNull(order.getDescription()));
		//this.description = new SimpleStringProperty("dkjdfcbsakjd");
		if (order.getDueDate()!=null){
			this.dueDate = new SimpleObjectProperty<LocalDate>(EditOrderController.toLocalDate(order.getDueDate()));
		} else {
			//this.dueDate = new SimpleObjectProperty<LocalDate>(LocalDate.of(0, 1, 1));
			this.dueDate = null;
		}
		
		this.orderDate = new SimpleObjectProperty<LocalDate>(EditOrderController.toLocalDate(order.getOrderDate()));
		this.price = new SimpleStringProperty(checkNull(""+order.getFinalPrice()));
//		String stag;
//		if (order.getStage()==0){
//			stag = "undone";
//		} else if (order.getStage()==1){
//			stag = "done";
//			
//		} else if (order.getStage()==2){
//			stag = "shipped";
//			
//		} else {
//			stag = "complete";
//			
//		} 
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

	/*public static void main(String[] args){
		Order n = new Order("aa", new Date(2015,3,4),3, 3);
		System.out.print(n.getDueDate());
		System.out.println(n.getDescription());
		Object o = n.getDescription();
		System.out.println((String)o);
		System.out.println(checkNull(n.getDescription()));
	}*/
	

}
