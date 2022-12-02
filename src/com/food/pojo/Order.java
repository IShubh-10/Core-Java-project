package com.food.pojo;

public class Order 
{
	private int orderId;
	private long orderCustPhone;
	private String orderDate;
	private String orderStatus;
	private double orderBill;
	private String orderCustAddress;
	
	
	//DISPLAY
	private int foodId;
	private String foodName;
	private int foodQuantity;
	private Customer customer;
	
	public Order()
	{
		//just to create object without any parameter
	}

	public Order(int orderId, long orderCustPhone, String orderDate, String orderStatus, double orderBill,
			String orderCustAddress, int foodId, String foodName, int foodQuantity, Customer customer) {
		super();
		this.orderId = orderId;
		this.orderCustPhone = orderCustPhone;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.orderBill = orderBill;
		this.orderCustAddress = orderCustAddress;
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodQuantity = foodQuantity;
		this.customer = customer;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public long getOrderCustPhone() {
		return orderCustPhone;
	}

	public void setOrderCustPhone(long orderCustPhone) {
		this.orderCustPhone = orderCustPhone;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public double getOrderBill() {
		return orderBill;
	}

	public void setOrderBill(double orderBill) {
		this.orderBill = orderBill;
	}

	public String getOrderCustAddress() {
		return orderCustAddress;
	}

	public void setOrderCustAddress(String orderCustAddress) {
		this.orderCustAddress = orderCustAddress;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getFoodQuantity() {
		return foodQuantity;
	}

	public void setFoodQuantity(int foodQuantity) {
		this.foodQuantity = foodQuantity;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderCustPhone=" + orderCustPhone + ", orderDate=" + orderDate
				+ ", orderStatus=" + orderStatus + ", orderBill=" + orderBill + ", orderCustAddress=" + orderCustAddress
				+ ", foodId=" + foodId + ", foodName=" + foodName + ", foodQuantity=" + foodQuantity + ", customer="
				+ customer + "]";
	}

	
	
	
}
