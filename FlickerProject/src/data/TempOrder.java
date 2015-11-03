package data;

public class TempOrder {
	private static Order tempOrder;
	
	public static void setTempOrder(Order order){
		tempOrder = order;
	}
	public static Order getTempOrder() {
		
		return tempOrder;
	}

}
