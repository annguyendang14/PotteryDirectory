package data;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CustomerTable {
	private final SimpleStringProperty name;
	private final SimpleStringProperty address;
	private final SimpleStringProperty phoneNum;
	private final SimpleStringProperty email;
	private final SimpleIntegerProperty code;

	public CustomerTable(Customer cus) {
		this.name = new SimpleStringProperty(cus.getName());
		this.address = new SimpleStringProperty(cus.getAddress());
		this.phoneNum = new SimpleStringProperty(cus.getPhoneNum());
		this.email = new SimpleStringProperty(cus.getEmail());
		this.code = new	SimpleIntegerProperty(cus.hashCode());	
	}
	
	public int getCode(){
		return code.get();
	}

	public String getName() {
		return name.get();
	}

	public String getAddress() {
		return address.get();
	}

	public String getPhoneNum() {
		return phoneNum.get();
	}

	public String getEmail() {
		return email.get();
	}
	
	public static List<CustomerTable> toCustomerTable(List<Customer> cuss){
		List<CustomerTable> cusTable = new ArrayList<CustomerTable>();
		for (Customer cus: cuss){
			cusTable.add(new CustomerTable(cus));
		}
		return cusTable;
	}

}
