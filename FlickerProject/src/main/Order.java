package main;
/**
 * @author annguyendang14
 */

import java.util.Date;
import java.util.List;


public class Order {
	private Customer customer;
	//private List<Pottery> potteries;
	private String description;
	private int orderNum; //not sure how to construct this yet
	private Date orderDate;
	private Date dueDate;
	private double price;
	//private String specialNotice;
	private int stage; // 0 = not done, 1 = done, 2 = shipped, 3 = customer received
	
	
	/**
	 * private constructor to reduce repetition in other constructor
	 * @param orderDate
	 * @param orderNum
	 */
	private Order(Date orderDate, int orderNum){
		this.orderDate = orderDate;
		this.orderNum = orderNum;
	}
	public Order(String customerName, Date orderDate, int orderNum){
		this(orderDate, orderNum);
		this.customer = new Customer(customerName);
		this.stage = 0;
				
	}
	public Order(String customerName, Date orderDate, int orderNum, int stage){
		this(orderDate, orderNum);
		this.customer = new Customer(customerName);
		this.stage = stage;
				
	}
	
				
	public Order(String customerName, Date orderDate, int orderNum,  String description , double price){
		this(customerName, orderDate, orderNum);
		this.price = price;
		this.description = description;
		
	}
	
	//added price, stage, and special order by Kelsey **fixed to match new field
	/** 
	 * 
	 * @param orderDate
	 * @param dueDate
	 * @param orderNum
	 * @param description
	 * @param price
	 * 
	 */
	public Order(String customerName, Date orderDate, Date dueDate, int orderNum,  String description , double price){
		this(customerName,orderDate, orderNum, description , price);
		this.dueDate = dueDate;
		
		
	}
	
/*	public int compareTo(Object o){ //need to try later
		Order other = (Order) o;
		if(other.orderDate.equals(this.orderDate)){
			return this.stage-other.stage;
		} else {
			return -this.orderDate.compareTo(other.orderDate);
		}
	}*/
	
/*	public void addPotteries(Pottery pottery){
		potteries.add(pottery);
		
	}
	
	public void setPottery(List<Pottery> potteries){
		this.potteries = potteries;
	}
	*/
	public void setDueDate(Date dueDate){
		this.dueDate = dueDate;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	/*public void setSpecialNotice(String notice){
		this.specialNotice = notice;
	}*/
	
	public void setStage(int stage){ //restricted to reduce stage for now, might change later
		if (stage >= this.stage){
			this.stage = stage;
		} 
	}
	
	public Customer getCustomer(){
		return customer;
		
	}
	
/*	public List<Pottery> getPotteries(){
		return potteries;
		
	}
	*/
	public int getOrderNum(){
		return orderNum;
		
	}
	
	public Date getOrderDate(){
		return orderDate;
		
	}
	
	public Date getDueDate(){
		return dueDate;
		
	}
	
	public double getPrice(){
		return price;
		
	}
	
/*	public String getNotice(){
		return specialNotice;
		
	}*/
	
	public int getStage(){
		return stage;
		
	}
	/**
	 * temp toString method, need revision later
	 */
	public String toString(){
		if (dueDate != null){
			return customer.getName()+" "+ orderNum+" "+ orderDate.toString() +" "+dueDate.toString()+" "+stage;
		} else {
			return customer.getName()+" "+ orderNum+" "+ orderDate.toString() +" "+stage;

		}
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
