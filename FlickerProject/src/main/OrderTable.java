package main;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.beans.property.*;

public class OrderTable {
	
	private final SimpleStringProperty customerName;
	private final SimpleStringProperty description;
	private final SimpleStringProperty dueDate;
	private final SimpleStringProperty orderDate;
	private final SimpleStringProperty price;
	private final SimpleStringProperty stage;
	private final SimpleStringProperty orderNum;
	
	public OrderTable(Order order) {
		
		this.customerName = new SimpleStringProperty(order.getCustomer().getName());
		this.description = new SimpleStringProperty(checkNull(order.getDescription()));
		//this.description = new SimpleStringProperty("dkjdfcbsakjd");
		this.dueDate = new SimpleStringProperty(checkNull(order.getDueDate()));
		//this.dueDate = new SimpleStringProperty("21/11/1996");
		this.orderDate = new SimpleStringProperty(checkNull(order.getOrderDate().toString()));
		this.price = new SimpleStringProperty(checkNull(""+order.getPrice()));
		String stag;
		if (order.getStage()==0){
			stag = "undone";
		} else if (order.getStage()==1){
			stag = "done";
			
		} else if (order.getStage()==2){
			stag = "shipped";
			
		} else {
			stag = "complete";
			
		} 
		this.stage = new SimpleStringProperty(stag);
		this.orderNum = new SimpleStringProperty(""+order.getOrderNum());
	}
	public String getCustomerName() {
		return customerName.get();
	}
	public String getDescription() {
		return description.get();
	}
	public String getDueDate() {
		return dueDate.get();
	}
	public String getOrderDate() {
		return orderDate.get();
	}
	public String getPrice() {
		return price.get();
	}
	public String getStage() {
		return stage.get();
	}
	public String getOrderNum() {
		return orderNum.get();
	}
	private static String checkNull(Object o){
		String s;
		try {
			s =(String)o;
				
		} catch (Exception e){
			s = o.toString();
		} 
		
		if (s == null){
			return "N/a";
		} else {
			return s;
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
