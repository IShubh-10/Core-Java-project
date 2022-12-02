package com.food.dao;

import java.util.List;

import com.food.pojo.Order;

public interface OrderDao 
{
	
	public Order placeOrder(Order order);
	public List<Order> showMyOrderHistory(String custPhone);
	
	boolean checkOrderId(int orderId);
	
	//ADMIN
	public boolean updateStatus(int orderId,String status);
	public List<Order> showAllOrders();

}
