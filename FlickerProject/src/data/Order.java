package data;

/**
 * @author annguyendang14
 * @param orderNum
	@param orderDate;
	@param dueDate;
	@param price;
	@param stage;
 * 
 */

import java.util.Date;

public class Order {
	private Customer customer;
	private String description;
	private int orderNum; // not sure how to construct this yet
	private Date orderDate;
	private Date dueDate;
	private String price;
	private int stage; // 0 = not done, 1 = done, 2 = shipped, 3 = completed, 4 = canceled
	private boolean needShip;
	private String shippingAddress;
	private double shippingCost;
	private double taxRate;

	/**
	 * 
	 * @param orderDate
	 * @param dueDate
	 * @param orderNum
	 * @param description
	 * @param price
	 * @param needShip (boolean)
	 * @param shippingAddress
	 * Sets stage to 0 when called
	 * 
	 */
	private Order(Date orderDate, int orderNum, String description, String price, boolean needShip, String shippingAddress, double shippingCost,double taxRate) {
		this.orderDate = orderDate;
		this.orderNum = orderNum;
		this.price = price;
		this.description = description;
		this.stage = 0;
		this.needShip = needShip;
		this.shippingAddress = shippingAddress;
		this.shippingCost = shippingCost;
		this.taxRate = taxRate;
	}



	public Order(Customer customer, Date orderDate, int orderNum, String description, String price,  boolean needShip, String shippingAddress,double shippingCost, double taxRate) {
		this(orderDate, orderNum, description, price, needShip, shippingAddress, shippingCost,taxRate);
		this.customer = customer;
		
	}

	public Order(Customer customer, Date orderDate, Date dueDate, int orderNum, String description,
			String price,  boolean needShip, String shippingAddress,double shippingCost, double taxRate) {
		this(orderDate, orderNum, description, price, needShip, shippingAddress, shippingCost, taxRate);
		this.dueDate = dueDate;
	}

	public Order(Customer customer,Date orderDate, int orderNum, String description , String price,   boolean needShip, String shippingAddress,double shippingCost, double taxRate, int stage){
		this(orderDate, orderNum, description, price, needShip, shippingAddress, shippingCost, taxRate);
		this.stage = stage;
	}
	public Order(Customer customer, Date orderDate, Date dueDate, int orderNum,  String description , String price,  boolean needShip, String shippingAddress, double shippingCost,double taxRate,int stage){
		this(customer, orderDate, orderNum, description, price, needShip, shippingAddress, shippingCost,taxRate, stage);
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
	

/**
 * Various set methods that take in a parameter and sets it to the corresponding data field
 */
	public void setDueDate(Date dueDate) {

		this.dueDate = dueDate;
	}

	public void setPrice(String price) {
		this.price = price;
	}


	public void setStage(int stage) { 
		this.stage = stage;
	}
	
	public void setStageByName(String stageName) {
		
	}
	
	/**
	 * Gets and returns the parameters
	 */
	public Customer getCustomer() {
		return this.customer;

	}

	public int getOrderNum() {
		return this.orderNum;

	}

	public Date getOrderDate() {
		return this.orderDate;

	}

	public Date getDueDate() {
		return this.dueDate;

	}

	public String getPrice() {
		return this.price;

	}

	public int getStage() {
		return stage;

	}

	/**
	 * temp toString method, need revision later
	 */
	public String toString() {
		if (dueDate != null) {
			return orderNum + " " + orderDate.toString() + " " + dueDate.toString() + " " + stage
					+ " " + description;
		} else {
			return orderNum + " " + orderDate.toString() + " " + stage + " " + description;

		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setOrder(Customer customer, Date orderDate, String description, String price, boolean needShip, String shippingAddress,double shippingCost, double taxRate,int stage) {
		this.setCustomer(customer);
		this.setOrderDate(orderDate);
		this.setDescription(description);
		this.setPrice(price);
		this.setStage(stage);
		this.setNeedShip(needShip);
		this.setShippingAddress(shippingAddress);
		this.setShippingCost(shippingCost);
		this.taxRate = taxRate;
	}

	public void setOrder(Customer customer, Date orderDate, Date dueDate, String description,
			String price, boolean needShip, String shippingAddress,double shippingCost,double taxRate, int stage) {
		this.setOrder(customer, orderDate, description, price, needShip, shippingAddress, shippingCost,taxRate,stage );
		this.setDueDate(dueDate);
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public static int convertStageNameToStageNumber(String stageName){ 
		if (stageName.equals("Undone")){
			return 0;
		} else if (stageName.equals("Done")){
			return 1;
		} else if (stageName.equals("Shipped")){
			return 2;
		} else if (stageName.equals("Completed")){
			return 3;
		} else { // for canceled
			return 4;
		}
	}
	
	public static String convertStageNumberToStageName(int stageNum){
		if (stageNum==0){
			return "Undone";
		} else if (stageNum == 1){
			return "Done";
		} else if (stageNum == 2){
			return "Shipped";
		}  else if (stageNum == 3){
			return "Completed";
		}  else { // for stageNum == 4
			return "Canceled"; 
		}
	}



	public boolean isNeedShip() {
		return needShip;
	}



	public void setNeedShip(boolean needShip) {
		this.needShip = needShip;
	}



	public String getShippingAddress() {
		return shippingAddress;
	}



	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}



	public double getTaxRate() {
		return taxRate;
	}



	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}



	public double getShippingCost() {
		return shippingCost;
	}



	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}
	
	public double getFinalPrice(){
		double total = Calculator.StringCalculator(price);
		double tax = total*taxRate/100;
		return total +tax + shippingCost;
	}

	
}
