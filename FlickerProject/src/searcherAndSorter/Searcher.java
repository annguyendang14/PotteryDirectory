package searcherAndSorter;

import java.util.*;

import data.Customer;
import data.Order;

/**
 * temporary main class to test search method
 * 
 * @author annguyendang14
 *
 */
public class Searcher {

	/*
	 * public static void main(String[] args) { List<Order> orders = new
	 * ArrayList<Order>(); List<Customer> customers = new ArrayList<Customer>();
	 * 
	 * for (int i=1; i<5; i++){ Date recentDate = new Date(); Date recent = new
	 * Date(recentDate.getYear(),recentDate.getMonth(),recentDate.getDate());
	 * System.out.println(recent); for (int j=1; j<5;j++){ Order tempOrder = new
	 * Order("an"+i,new
	 * Date(recentDate.getYear(),recentDate.getMonth()-1,recentDate
	 * .getDate()+i),j, 5-j); orders.add(tempOrder);
	 * customers.add(tempOrder.getCustomer()); } } for (Order i: orders){
	 * System.out.println(i);
	 * 
	 * } for (Customer i: customers ){ System.out.println(i.getName()); }
	 * 
	 * searchCustomer(customers,"an");
	 * 
	 * searchOrder(orders, "1");
	 * 
	 * searchOrder(orders, "an"); System.out.println();
	 * 
	 * Collections.sort(orders, new DefaultSortingComparator());
	 * 
	 * for (Order i: orders){ System.out.println(i); }
	 * 
	 * 
	 * }
	 */
	/**
	 * this method search for a customer by their name
	 * 
	 * @param customers
	 *            list of customer
	 * @param name
	 *            the name of the customer user want to search for
	 * @return list of Customer match the search
	 */
	public static List<Customer> searchCustomer(List<Customer> customers,
			String name) {
		List<Customer> result = new ArrayList<Customer>();
		for (Customer cus : customers) {
			if (cus.getName().contains(name)) {
				result.add(cus);
			}

		}
		for (Customer cus : result) {
			System.out.println(cus.getName());
		}
		return result;
	}

	/**
	 * 
	 * this method search for order, auto recognize if the user want to search
	 * by OrderNum (by type in a number)
	 * 
	 * @param orders
	 *            List of order
	 * @param searchKeyWords
	 *            a string that customer type in search box
	 * @return List of order contain key words
	 */
	public static List<Order> searchOrder(List<Order> orders,
			String searchKeyWords) {
		List<Order> result = new ArrayList<Order>();
		if (isInteger(searchKeyWords)) {
			int searchOrderNum = Integer.parseInt(searchKeyWords);
			for (Order ord : orders) {
				if (ord.getOrderNum() == searchOrderNum) {
					result.add(ord);
				}
			}
		} else {
			for (Order ord : orders) {
				if (ord.getCustomer().getName().contains(searchKeyWords)) {
					result.add(ord);
				}
			}
		}

		/*
		 * for (Order i: result){ System.out.println(i); }
		 */
		return result;
	}

	public static List<Order> searchStageOrder(List<Order> orders, int stageNum) {
		List<Order> result = new ArrayList<Order>();
		for (Order i : orders) {
			if (i.getStage() == stageNum) {
				result.add(i);
			}
		}

		return result;
	}

	/**
	 * http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-
	 * integer-in-java
	 * 
	 */
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	/*
	 * public static List<OrderTable> getListOrderSample(){ List<Order> orders =
	 * new ArrayList<Order>(); List<Customer> customers = new
	 * ArrayList<Customer>();
	 * 
	 * for (int i=1; i<5; i++){ Date recentDate = new Date(); Date recent = new
	 * Date(recentDate.getYear(),recentDate.getMonth(),recentDate.getDate());
	 * System.out.println(recent); for (int j=1; j<5;j++){ Order tempOrder = new
	 * Order("an"+i,new
	 * Date(recentDate.getYear(),recentDate.getMonth()-1,recentDate
	 * .getDate()+i),j, 5-j); orders.add(tempOrder);
	 * customers.add(tempOrder.getCustomer()); } } List<OrderTable> orderTable =
	 * new ArrayList<OrderTable>(); for (Order order: orders){
	 * orderTable.add(new OrderTable(order)); } return orderTable; }
	 */
	public static Order searchForOrder(List<Order> orders, int orderNum) {
		Order returnOrd = null;
		for (Order order : orders) {
			if (order.getOrderNum() == orderNum) {
				returnOrd = order;
			}
		}
		return returnOrd;

	}

}
