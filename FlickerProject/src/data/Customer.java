package data;

//import java.util.*;
public class Customer {
	private String name;
	private String address;
	private String phoneNum;
	private String email;
	/**
	 * @param name
	 * sets the customers name in a constructor
	 */
	public Customer(String name) {
		this.name = name;
	}
	/**
	 * @param name
	 * @param address
	 * sets the customers name in a constructor
	 */
	public Customer(String name, String address) {
		this(name);
		this.address = address;
	}
	/**
	 * @author Kelsey
	 * 
	 * @param name
	 * @param address
	 * @param phoneNum
	 * @param email
	 * initializes the customer's data in a constructor
	 */

	public Customer(String name, String address, String phoneNum, String email) {
		this(name, address);
		this.phoneNum = phoneNum;
		this.email = email;
	}
	
	/**
	 *Gets and returns the data fields
	 */
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
	/**
	 *Sets the data field with the corresponding input data field
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

	/**

	 * @param name
	 * @param address
	 * @param phoneNum
	 * @param email
	 * changes the customer's data using a constructor
	 */
	public void setCustomer(String name, String address, String phoneNum, String email) {
		this.setName(name);
		this.setAddress(address);
		this.setPhoneNum(phoneNum);
		this.setEmail(email);
	}
	
	

	// added for debug purposes, prints out all the data fields to the console
	public String toString() {
		return "The Customer: " + this.getName() + " " + this.getAddress() + " "
				+ this.getPhoneNum() + " " + this.getEmail();
	}

}
