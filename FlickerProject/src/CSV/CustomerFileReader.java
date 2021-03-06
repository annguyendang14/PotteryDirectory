package CSV;

//author Kelsey
import java.io.*;
import java.util.*;

import com.opencsv.*;

import data.Customer;

public class CustomerFileReader {
	// http://opencsv.sourceforge.net/
	// http://www.javapractices.com/topic/TopicAction.do?Id=42
	// http://stackoverflow.com/questions/27324187/java-reading-from-csv-file-and-storing-its-information-into-arraylistclass
	// http://stackoverflow.com/questions/10964975/parsing-csv-file-to-class-getter-methods-with-opencsv


	public static void main(String[] args) throws IOException {
		
		List<Customer> customerList = new ArrayList<Customer>();
	
		write(customerList);
		
		read();
		
	}

	// reads a Customer List file
	public static List<Customer> read() throws IOException {
		CSVReader reader = new CSVReader(new FileReader("CustomerList.csv"));
		ArrayList<Customer> customersFromFile = new ArrayList<Customer>();
		String[] currentCustomerRow;
		while ((currentCustomerRow = reader.readNext()) != null) {
			String name = currentCustomerRow[0];
			String address = currentCustomerRow[1];
			String phoneNum = currentCustomerRow[2];
			String email = currentCustomerRow[3];
			//String code = currentCustomerRow[4];
			customersFromFile.add(new Customer(name, address, phoneNum, email));
		}
		reader.close();
		return customersFromFile;
		
	}

	// writes a customer list file
	public static void write(List<Customer> customerList) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter("CustomerList.csv"), ',');
		for (Customer customer: customerList) {
			String[] currentCustomerRow = new String[5];
			currentCustomerRow[0] = customer.getName();
			currentCustomerRow[1] = customer.getAddress();
			currentCustomerRow[2] = customer.getPhoneNum();
			currentCustomerRow[3] = customer.getEmail();
			currentCustomerRow[4] = ""+customer.hashCode();
			writer.writeNext(currentCustomerRow);
		}
		writer.close();
	}
}
