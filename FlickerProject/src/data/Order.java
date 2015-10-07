package data;

/**
 * @author annguyendang14
 */

import java.util.Date;

public class Order {
	private Customer customer;
	// private List<Pottery> potteries;
	private String description;
	private int orderNum; // not sure how to construct this yet
	private Date orderDate;
	private Date dueDate;
	private double price;
	// private String specialNotice;
	private int stage; // 0 = not done, 1 = done, 2 = shipped, 3 = customer
						// received

	/**
	 * private constructor to reduce repetition in other constructors
	 * 
	 * @param orderDate
	 * @param orderNum
	 */
	private Order(Date orderDate, int orderNum, String description, double price) {
		this.orderDate = orderDate;
		this.orderNum = orderNum;
		this.price = price;
		this.description = description;
		this.stage = 0;
	}

	public Order(String customerName, Date orderDate, int orderNum, String description, double price) {
		this(orderDate, orderNum, description, price);
		this.customer = new Customer(customerName);

	}

	/*
	 * public Order(String customerName, Date orderDate, int orderNum, int
	 * stage){ this(orderDate, orderNum); this.customer = new
	 * Customer(customerName); this.stage = stage;
	 * 
	 * }
	 */

	public Order(String customerName, Date orderDate, int orderNum, String description,
			double price, int stage) {
		this(customerName, orderDate, orderNum, description, price);
		this.stage = stage;

	}

	/**
	 * 
	 * @param orderDate
	 * @param dueDate
	 * @param orderNum
	 * @param description
	 * @param price
	 * 
	 */
	public Order(String customerName, Date orderDate, Date dueDate, int orderNum,
			String description, double price, int stage) {
		this(customerName, orderDate, orderNum, description, price, stage);
		this.dueDate = dueDate;
	}

	//file saving
	public Order(Date orderDate, Date dueDate, int orderNum, String description, double price,
			int stage) {
		this.orderDate = orderDate;
		this.orderNum = orderNum;
		this.dueDate = dueDate;
		this.description = description;
		this.price = price;
		this.stage = stage;
	}

	public Order(Customer customer, Date orderDate, int orderNum, String description, double price) {
		this(orderDate, orderNum, description, price);
		this.customer = customer;
	}

	public Order(Customer customer, Date orderDate, Date dueDate, int orderNum, String description,
			double price) {
		this(customer, orderDate, orderNum, description, price);
		this.dueDate = dueDate;
	}

	/*
	 * public int compareTo(Object o){ //need to try later Order other = (Order)
	 * o; if(other.orderDate.equals(this.orderDate)){ return
	 * this.stage-other.stage; } else { return
	 * -this.orderDate.compareTo(other.orderDate); } }
	 */

	/*
	 * public void addPotteries(Pottery pottery){ potteries.add(pottery);
	 * 
	 * }
	 * 
	 * public void setPottery(List<Pottery> potteries){ this.potteries =
	 * potteries; }
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/*
	 * public void setSpecialNotice(String notice){ this.specialNotice = notice;
	 * }
	 */

	public void setStage(int stage) { // restricted to reduce stage for now,
										// might change later
		if (stage >= this.stage) {
			this.stage = stage;
		}
	}

	public Customer getCustomer() {
		return customer;

	}

	/*
	 * public List<Pottery> getPotteries(){ return potteries;
	 * 
	 * }
	 */
	public int getOrderNum() {
		return orderNum;

	}

	public Date getOrderDate() {
		return orderDate;

	}

	public Date getDueDate() {
		return dueDate;

	}

	public double getPrice() {
		return price;

	}

	/*
	 * public String getNotice(){ return specialNotice;
	 * 
	 * }
	 */

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

	public void setOrder(Customer customer, Date orderDate, String description, double price,
			int stage) {
		this.setCustomer(customer);
		this.setOrderDate(orderDate);
		this.setDescription(description);
		this.setPrice(price);
		this.setStage(stage);
	}

	public void setOrder(Customer customer, Date orderDate, Date dueDate, String description,
			double price, int stage) {
		this.setOrder(customer, orderDate, description, price, stage);
		this.setDueDate(dueDate);
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

}
