package main;

public class TempCustomer {
	private static Customer tempCustomer;
	
	public static void setTempCustomer(Customer cus){
		tempCustomer = cus;
	}
	public static Customer getTempCustomer() {
		Customer returnCus = tempCustomer;
		//tempCustomer = null;
		return returnCus;
	}
	
}
