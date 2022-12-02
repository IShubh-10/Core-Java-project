package com.food.dao;

import java.util.List;

import com.food.pojo.Customer;

public interface CustomerDao {
	
	boolean addCustomer(Customer Customer);
	boolean updateCustomer(Customer Customer);
	boolean deleteCustomer(Customer Customer);
	
	List<Customer> showAllCustomers();
	List<Customer> showCustomerByPhone(long custPhone);

}
