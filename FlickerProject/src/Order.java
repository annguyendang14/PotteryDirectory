import java.util.Date;
import java.util.List;


public class Order {
	private Customer customer;
	private List<Pottery> potteries;
	private int orderNum;
	private Date orderDate;
	private Date dueDate;
	private double price;
	private String specialNotice;
	private int stage; // 0 = not done, 1 = done, 2 = shipped, 3 = customer received
	
	public Order(String customerName, Date orderDate){
		this.customer = new Customer(customerName);
		this.orderDate = orderDate;
		
		
	}
	
	public Order(Customer customer, Date orderDate){
		this.customer = customer;
		this.orderDate = orderDate;
	}
	
	public int compareTo(Object o){ //need to try later
		Order other = (Order) o;
		if(other.orderDate.equals(this.orderDate)){
			return this.stage-other.stage;
		} else {
			return -this.orderDate.compareTo(other.orderDate);
		}
	}
	
	public void addPotteries(Pottery pottery){
		potteries.add(pottery);
		
	}
	
	public void setPottery(List<Pottery> potteries){
		this.potteries = potteries;
	}
	
	public void setDueDate(Date dueDate){
		this.dueDate = dueDate;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public void setSpecialNotice(String notice){
		this.specialNotice = notice;
	}
	
	public void setStage(int stage){ //restricted to reduce stage for now, might change later
		if (stage >= this.stage){
			this.stage = stage;
		} 
	}
	
	public Customer getCustomer(){
		return customer;
		
	}
	
	public List<Pottery> getPotteries(){
		return potteries;
		
	}
	
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
	
	public String getNotice(){
		return specialNotice;
		
	}
	
	public int getStage(){
		return stage;
		
	}
	
	public void printShippingInvoice(){
//	add code after get the Customer	class
	}

}
