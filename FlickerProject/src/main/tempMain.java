package main;
import java.util.*;

/** 
 * temporary main class to test search method
 * @author annguyendang14
 *
 */
public class tempMain {

	public static void main(String[] args) {
		List<Order> orders = new ArrayList<Order>();
		List<Customer> customers = new ArrayList<Customer>();
		
		for (int i=1; i<5; i++){
			Date recentDate = new Date();
			Date recent = new Date(recentDate.getYear(),recentDate.getMonth(),recentDate.getDate());
			System.out.println(recent);
			for (int j=1; j<5;j++){
				Order tempOrder = new Order("an"+i,new Date(recentDate.getYear(),recentDate.getMonth()-1,recentDate.getDate()+i),j, 5-j);
				orders.add(tempOrder);
				customers.add(tempOrder.getCustomer());
			}
		}
		for (Order i: orders){
			System.out.println(i);
			
		}
	/*	for (Customer i: customers ){
			System.out.println(i.getName());
		}*/
		
		searchCustomer(customers,"an");
		
		searchOrder(orders, "1");
		
		searchOrder(orders, "an");
		System.out.println();
		
		Collections.sort(orders, new DefaultSortingComparator());
		
		for (Order i: orders){
			System.out.println(i);
		}

	}
	/**
	 * this method search for a customer by their name
	 * @param customers list of customer
	 * @param name the name of the customer user want to search for
	 * @return
	 */
	public static List<Customer> searchCustomer(List<Customer> customers, String name){
		List<Customer> result = new ArrayList<Customer>();
		for (Customer i: customers){
			if (i.getName().contains(name)){
				result.add(i);
			}
			
		}
		for (Customer i: result){
			System.out.println(i.getName());
		}
		return result;
	}
	
	/**
	 * 
	 * this method search for order, auto recognize if the user want to search by OrderNum (by type in a number)
	 * @param orders List of order
	 * @param searchKeyWords a string that customer type in search box
	 * @return List of order contain key words
	 */
	public static List<Order> searchOrder(List<Order> orders, String searchKeyWords){
		List<Order> result = new ArrayList<Order>();
		if (isInteger(searchKeyWords)){
			int searchOrderNum = Integer.parseInt(searchKeyWords);
			for (Order i: orders){
				if (i.getOrderNum()==searchOrderNum){
					result.add(i);
				}
			}
		} else {
			for (Order i: orders){
				if (i.getCustomer().getName().contains(searchKeyWords)){
					result.add(i);
				}
			}
		}
		
		for (Order i: result){
			System.out.println(i);
		}
		
		return result;
	}
	
	
	
	
	/**
	 * http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
	 */
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}

}
