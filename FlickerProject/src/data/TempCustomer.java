package data;

public class TempCustomer {
	private static Customer tempCustomer;
	
	public static void setTempCustomer(Customer cus){
		tempCustomer = cus;
	}
	public static Customer getTempCustomer() {
		
		return tempCustomer;
	}
	
}
