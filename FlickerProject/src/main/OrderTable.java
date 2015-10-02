package main;
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
		//this.description = new SimpleStringProperty(""+order.getDescription());
		this.description = new SimpleStringProperty("fekjfnekwf");
		//this.dueDate = new SimpleStringProperty(""+order.getDueDate().toString());
		this.dueDate = new SimpleStringProperty("21/11/1996");
		this.orderDate = new SimpleStringProperty(order.getOrderDate().toString());
		this.price = new SimpleStringProperty(""+order.getPrice());
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
	
	
	

}
