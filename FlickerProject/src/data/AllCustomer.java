package data;

import java.util.ArrayList;
import java.util.List;

public class AllCustomer {
	private static List<Customer> customers = new ArrayList<Customer>();

	public static List<Customer> getCustomers() {
		return customers;
	}
	
	public static List<CustomerTable> getCustomerTable(){
		
		return CustomerTable.toCustomerTable(customers);
	}
	
	public static Customer findCustomer(int code){
		for(Customer cus: customers){
			if (cus.hashCode()==code){
				return cus;
			}
		}
		return null;
	}


}
