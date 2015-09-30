package main;
//author Kelsey
import java.io.FileReader;
import java.util.*;
import java.io.IOException;

import com.opencsv.*;

public class CustomerFileReader {
	// http://opencsv.sourceforge.net/
	// http://www.javapractices.com/topic/TopicAction.do?Id=42
	// http://stackoverflow.com/questions/27324187/java-reading-from-csv-file-and-storing-its-information-into-arraylistclass
	// http://stackoverflow.com/questions/10964975/parsing-csv-file-to-class-getter-methods-with-opencsv
	public static void main(String[] args) throws IOException {
		CSVReader reader = new CSVReader(new FileReader("CustomerList.csv"));
		ArrayList<Customer> result = new ArrayList<Customer>();
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			String happy = nextLine[0];
			result.add(new Customer(happy));
		}
		// line to check the size of list-delete later
		System.out.println(result.size());
		reader.close();
	}
}
