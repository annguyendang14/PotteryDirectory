package data;

//import java.util.*;
public class Customer {
	private String name;
	private String address;
	private String phoneNum;
	private String email;

	public Customer(String name) {
		this.name = name;
	}

	public Customer(String name, String address) {
		this(name);
		this.address = address;
	}

	// author-Kelsey created to be used for file processing
	public Customer(String name, String address, String phoneNum, String email) {
		this(name, address);
		this.phoneNum = phoneNum;
		this.email = email;
	}

	// to be used if needed
	// public Order[] getOrder(){

	// }

	public String getName() {
		return this.name;
	}

	public String getAddress() {
		return this.address;
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public String getEmail() {
		return this.email;
	}

	/*
	 * to be used if needed public void addOrder(Order order){
	 * 
	 * }
	 * 
	 * public void setOrder(List<Order> orders){
	 * 
	 * }
	 */

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCustomer(String name, String address, String phoneNum, String email) {
		this.setName(name);
		this.setAddress(address);
		this.setPhoneNum(phoneNum);
		this.setEmail(email);
	}

	public boolean equals(Object o) {
		return false;
	}

	// added for debug purposes, prints out all the data fields to the console
	public String toString() {
		return "The Customer: " + this.getName() + " " + this.getAddress() + " "
				+ this.getPhoneNum() + " " + this.getEmail();
	}

}
