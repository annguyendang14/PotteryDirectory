package main;
//author Kelsey
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.io.IOException;

import com.opencsv.*;

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
		//write(orderList);
		
		
		/*List<Order> ordersFromFileRead =*/ 
		read();
		// line to check the size of list-delete later
		
		//System.out.println("After writing and reading...");
		//System.out.println(ordersFromFileRead.size());
		
		//for (Order ord: ordersFromFileRead){
			//System.out.println(ord);
		//}

		}

	// reads a Customer List file
	public static List<Order> read() throws IOException {
		CSVReader reader = new CSVReader(new FileReader("OrderList.csv"));
		ArrayList<Order> ordersFromFile = new ArrayList<Order>();
		String[] currentOrderRow;
		while ((currentOrderRow = reader.readNext()) != null) {
			String customername = currentOrderRow[0];
			String orderDate = currentOrderRow[1];
			String dueDate = currentOrderRow[2];
			String orderNum = currentOrderRow[3];
			String description = currentOrderRow[4];
			String price = currentOrderRow[5];
			String stage = currentOrderRow[6];
			//System.out.print(dueDate);
			if (!dueDate.equals("null")){
				ordersFromFile.add(new Order(customername, new Date(orderDate), new Date(dueDate), Integer.parseInt(orderNum), description, Double.parseDouble(price), Integer.parseInt(stage)));
			} else {
				ordersFromFile.add(new Order(customername, new Date(orderDate), Integer.parseInt(orderNum), description, Double.parseDouble(price), Integer.parseInt(stage)));
			}
		}
		reader.close();
		return ordersFromFile;
		
	}

	// writes a customer list file
	public static void write(List<Order> orderList) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter("OrderList.csv"), ',');
		for (Order order: orderList) {
			String[] currentOrderRow = new String[7];
			currentOrderRow[0] = order.getCustomer().getName();
			currentOrderRow[1] = order.getOrderDate()+"";
			currentOrderRow[2] = order.getDueDate()+"";
			currentOrderRow[3] = order.getOrderNum()+"";
			currentOrderRow[4] = order.getDescription();
			currentOrderRow[5] = order.getPrice()+"";
			currentOrderRow[6] = order.getStage()+"";
			writer.writeNext(currentOrderRow);
		}
		writer.close();
	}
}