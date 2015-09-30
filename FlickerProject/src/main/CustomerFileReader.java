package main;
//author Kelsey
import java.io.*;
import java.util.*;


import com.opencsv.*;

public class CustomerFileReader{
	// http://opencsv.sourceforge.net/
	// http://www.javapractices.com/topic/TopicAction.do?Id=42
	// http://stackoverflow.com/questions/27324187/java-reading-from-csv-file-and-storing-its-information-into-arraylistclass
	// http://stackoverflow.com/questions/10964975/parsing-csv-file-to-class-getter-methods-with-opencsv
	public static void main(String[] args) throws IOException {
		read();
		//write(result);
	}
	//reads a Customer List file
public static void read()throws IOException {
	CSVReader reader = new CSVReader(new FileReader("CustomerList.csv"));
	ArrayList<Customer> result = new ArrayList<Customer>();
	String[] nextLine;
	while ((nextLine = reader.readNext()) != null) {
		String input = nextLine[0];
		result.add(new Customer(input));
	}
	// line to check the size of list-delete later
	System.out.println(result.size());
	reader.close();
}
	//writes a customer list file
	//public static void write(ArrayList<Customer> result)throws IOException {
	//CSVWriter writer = new CSVWriter(new FileWriter("CustomerList.csv"), ',');
    //for(i = 0; )
	//String[] entries = "first,second#third".split(",");
    //writer.writeNext();
	 //writer.close();
	//}
}
