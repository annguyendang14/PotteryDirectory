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
	private double price;
	private int stage; // 0 = not done, 1 = done, 2 = shipped, 3 = customer received

	/**
	 * 
	 * @param orderDate
	 * @param dueDate
	 * @param orderNum
	 * @param description
	 * @param price
	 * Sets stage to 0 when called
	 * 
	 */
	private Order(Date orderDate, int orderNum, String description, double price) {
		this.orderDate = orderDate;
		this.orderNum = orderNum;
		this.price = price;
		this.description = description;
		this.stage = 0;
	}
	/**
	 * @param customerName from Customer class
	 * @param orderDate
	 * @param dueDate
	 * @param orderNum
	 * @param description
	 * @param price
	 * @param stage
	 * 
	 */
	public Order(String customerName, Date orderDate, int orderNum, String description, double price) {
		this(orderDate, orderNum, description, price);
		this.customer = new Customer(customerName);

	}
	/**
	 * @param customerName from Customer class
	 * @param orderDate
	 * @param dueDate
	 * @param orderNum
	 * @param description
	 * @param price
	 * @param stage
	 * makes stage changeable 
	 * 
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
	 * @param stage
	 * 
	 */
	public Order(String customerName, Date orderDate, Date dueDate, int orderNum,
			String description, double price, int stage) {
		this(customerName, orderDate, orderNum, description, price, stage);
		this.dueDate = dueDate;
	}

	/**
	 * 
	 * @param orderDate
	 * @param dueDate
	 * @param orderNum
	 * @param description
	 * @param price
	 * @param stage
	 * 
	 */
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
/**
 * Various set methods that take in a parameter and sets it to the corresponding data field
 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public void setStage(int stage) { // restricted to reduce stage for now,
										// might change later
		if (stage >= this.stage) {
			this.stage = stage;
		}
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

	public double getPrice() {
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
