package main;

import java.util.ArrayList;
import java.util.List;

public class AllOrders {
	private static List<Order> orders = new ArrayList<Order>();

	public static List<Order> getOrders() {
		return orders;
	}
	public static List<OrderTable> getOrderTable(){
		
		return OrderTable.toOrderTable(orders);
	}

	
	

}

