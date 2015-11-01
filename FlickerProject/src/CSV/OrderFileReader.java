package CSV;
//author Kelsey
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.io.IOException;

import com.opencsv.*;

import data.Customer;
import data.Order;

public class OrderFileReader {
	// http://opencsv.sourceforge.net/
	// http://www.javapractices.com/topic/TopicAction.do?Id=42
	// http://stackoverflow.com/questions/27324187/java-reading-from-csv-file-and-storing-its-information-into-arraylistclass
	// http://stackoverflow.com/questions/10964975/parsing-csv-file-to-class-getter-methods-with-opencsv
	public static void main(String[] args) throws IOException {
		List<Order> orderList = new ArrayList<Order>();
		/*for (Order ord: orderList){			
			System.out.println
		(ord);
		}
		
*/
		
		write(orderList);
		
		
		/*List<Order> ordersFromFileRead =*/ 
		//read();
		// line to check the size of list-delete later
		
		//System.out.println("After writing and reading...");
		//System.out.println(ordersFromFileRead.size());
		
		//for (Order ord: ordersFromFileRead){
			//System.out.println(ord);
		//}

		}

	// reads a Customer List file
	public static List<Order> read(List<Customer> customers) throws IOException {
		CSVReader reader = new CSVReader(new FileReader("OrderList.csv"));
		ArrayList<Order> ordersFromFile = new ArrayList<Order>();
		String[] currentOrderRow;
		while ((currentOrderRow = reader.readNext()) != null) {
			String orderNum = currentOrderRow[0];
			String orderDate = currentOrderRow[1];
			String dueDate = currentOrderRow[2];
			String customerCode = currentOrderRow[3];
			String description = currentOrderRow[4];
			String price = currentOrderRow[5];
			String needShip = currentOrderRow[6];
			String shippingAddress = currentOrderRow[7];
			String shippingCost = currentOrderRow[8];
			String taxRate = currentOrderRow[9];
			String stage = currentOrderRow[10];
			//System.out.print(dueDate);
			if (!dueDate.equals("null")&&!dueDate.equals("N/a")){
				ordersFromFile.add(new Order(findCustomer(customers,customerCode), new Date(orderDate), new Date(dueDate), Integer.parseInt(orderNum), description, price, stringToBoolean(needShip), shippingAddress, Double.parseDouble(shippingCost),Double.parseDouble(taxRate),Integer.parseInt(stage)));
			} else {
				ordersFromFile.add(new Order(findCustomer(customers,customerCode), new Date(orderDate), Integer.parseInt(orderNum), description, price, stringToBoolean(needShip), shippingAddress, Double.parseDouble(shippingCost), Double.parseDouble(taxRate), Integer.parseInt(stage)));

			}
		}
		reader.close();
		return ordersFromFile;
		
	}

	// writes a customer list file
	public static void write(List<Order> orderList) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter("OrderList.csv"), ',');
		for (Order order: orderList) {
			String[] currentOrderRow = new String[11];
			currentOrderRow[0] = order.getOrderNum()+"";
			currentOrderRow[1] = order.getOrderDate()+"";
			currentOrderRow[2] = order.getDueDate()+"";
			currentOrderRow[3] = order.getCustomer().hashCode()+"";
			currentOrderRow[4] = order.getDescription();
			currentOrderRow[5] = order.getPrice();
			currentOrderRow[6] = order.isNeedShip()+"";
			currentOrderRow[7] = order.getShippingAddress();
			currentOrderRow[8] = order.getShippingCost()+"";
			currentOrderRow[9] = order.getTaxRate()+"";
			currentOrderRow[10] = order.getStage()+"";
			
			writer.writeNext(currentOrderRow);
		}
		writer.close();
	}
	public static Customer findCustomer(List<Customer> customers, String customerCode) throws IOException{
		CSVReader reader = new CSVReader(new FileReader("CustomerList.csv"));
		ArrayList<Customer> customersFromFile = new ArrayList<Customer>();
		String[] currentCustomerRow;
		int i=0;
		while ((currentCustomerRow = reader.readNext()) != null) {
			String code = currentCustomerRow[4];
			if (code.equals(customerCode)){
				return customers.get(i);
			}
			i++;
		}
		return null;
	}
	public static boolean stringToBoolean(String s){
		if (s.equals("true")){
			return true;
		} else {
			return false;
		}
	}
}