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
		orderList.add(new Order(new Date(2015,12,5), new Date(2015,12,6), 5, "bowl", 12.00, 3));
		orderList.add(new Order(new Date(2015,11,8), new Date(2015,5,9), 5, "bowl2", 15.00, 2));
		for (Order ord: orderList){			
			System.out.println
		(ord);
		}

		write(orderList, "OrderList.csv");
		
		
		List<Order> ordersFromFileRead = read("OrderList.csv");
		// line to check the size of list-delete later
		
		System.out.println("After writing and reading...");
		System.out.println(ordersFromFileRead.size());
		
		for (Order ord: ordersFromFileRead){
			System.out.println(ord);
		}

		}

	// reads a Customer List file
	public static List<Order> read(String fileName) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(fileName));
		ArrayList<Order> ordersFromFile = new ArrayList<Order>();
		String[] currentOrderRow;
		while ((currentOrderRow = reader.readNext()) != null) {
			String orderDate = currentOrderRow[0];
			String dueDate = currentOrderRow[1];
			String orderNum = currentOrderRow[2];
			String description = currentOrderRow[3];
			String price = currentOrderRow[4];
			String stage = currentOrderRow[5];
			ordersFromFile.add(new Order(new Date(orderDate), new Date(dueDate), Integer.parseInt(orderNum), description, Double.parseDouble(price), Integer.parseInt(stage)));
		}
		reader.close();
		return ordersFromFile;
		
	}

	// writes a customer list file
	public static void write(List<Order> orderList, String fileName) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(fileName), ',');
		for (Order order: orderList) {
			String[] currentOrderRow = new String[6];
			currentOrderRow[0] = order.getOrderDate()+"";
			currentOrderRow[1] = order.getOrderDate()+"";
			currentOrderRow[2] = order.getOrderNum()+"";
			currentOrderRow[3] = order.getDescription();
			currentOrderRow[4] = order.getPrice()+"";
			currentOrderRow[5] = order.getStage()+"";
			writer.writeNext(currentOrderRow);
		}
		writer.close();
	}
}