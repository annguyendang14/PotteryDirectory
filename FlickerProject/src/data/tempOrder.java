package data;

public class tempOrder {
	private static Order tempOrder;
	
	public static void setTempOrder(Order order){
		tempOrder = order;
	}
	public static Order getTempOrder() {
		
		return tempOrder;
	}

}
