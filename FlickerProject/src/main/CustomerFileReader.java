package main;

//author Kelsey
import java.io.*;
import java.util.*;

import com.opencsv.*;

public class CustomerFileReader {
	// http://opencsv.sourceforge.net/
	// http://www.javapractices.com/topic/TopicAction.do?Id=42
	// http://stackoverflow.com/questions/27324187/java-reading-from-csv-file-and-storing-its-information-into-arraylistclass
	// http://stackoverflow.com/questions/10964975/parsing-csv-file-to-class-getter-methods-with-opencsv


	public static void main(String[] args) throws IOException {
		
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer("Bob", "123 Bobber St", "1234567", "bob@gmail.com"));
		customerList.add(new Customer("Sally", "123 Sally Street", "789789", "sally@gmail.com"));
		for (Customer cust: customerList){			
			System.out.println
		(cust);
		}

		write(customerList, "CustomerList.csv");
		
		
		List<Customer> customersFromFileRead = read("CustomerList.csv");
		// line to check the size of list-delete later
		
		System.out.println("After writing and reading...");
		System.out.println(customersFromFileRead.size());
		
		for (Customer cust: customersFromFileRead){
			System.out.println(cust);
		}

	}

	// reads a Customer List file
	public static List<Customer> read(String fileName) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(fileName));
		ArrayList<Customer> customersFromFile = new ArrayList<Customer>();
		String[] currentCustomerRow;
		while ((currentCustomerRow = reader.readNext()) != null) {
			String name = currentCustomerRow[0];
			String address = currentCustomerRow[1];
			String phoneNum = currentCustomerRow[2];
			String email = currentCustomerRow[3];
			customersFromFile.add(new Customer(name, address, phoneNum, email));
		}
		reader.close();
		return customersFromFile;
		
	}

	// writes a customer list file
	public static void write(List<Customer> customerList, String fileName) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(fileName), ',');
		for (Customer customer: customerList) {
			String[] currentCustomerRow = new String[4];
			currentCustomerRow[0] = customer.getName();
			currentCustomerRow[1] = customer.getAddress();
			currentCustomerRow[2] = customer.getPhoneNum();
			currentCustomerRow[3] = customer.getEmail();
			writer.writeNext(currentCustomerRow);
		}
		writer.close();
	}
}
