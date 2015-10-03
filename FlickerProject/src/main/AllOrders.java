package main;

import java.util.ArrayList;
import java.util.List;

public class AllOrders {
	private static List<Order> orders = new ArrayList<Order>();

	public static List<Order> getOrders() {
		return orders;
	}
	public static List<OrderTable> getOrderTable(){
		List<OrderTable> orderTable = new ArrayList<OrderTable>();
		for (Order order: orders){
			orderTable.add(new OrderTable(order));
		}
		return orderTable;
	}

	
	

}

