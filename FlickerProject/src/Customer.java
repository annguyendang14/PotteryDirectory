import java.util.*;
public class Customer {
	private String name;
	private String address;
	private String phoneNum;
	private String email;	

	//why do we have this?-Kelsey
	public Customer(String name) {
		this.name = name;
	}
	
	public Customer(String name, String address){
		this(name);
		this.address = address;
	}
	//author-Kelsey created to be used for file processing
	public Customer(String name, String address, String phoneNum, String email){
		this(name, address);
		this.phoneNum = phoneNum;
		this.email = email;
	}
	//public Order[] getOrder(){
		
	//}
	
	public String getName(){
		return name;
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getPhoneNum(){
		return phoneNum;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void addOrder(Order order){
		
	}
	
	public void setOrder(List<Order> orders){
		
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	
	
	public boolean equals(Object o){
		return false;
	}

//added for debug purposes
	public String toString(){
		return "The Customer: " + this.getName()+ " "+this.getAddress()+ " "+this.getPhoneNum()+ " "+this.getEmail();
	}
	
	
	
	
}
