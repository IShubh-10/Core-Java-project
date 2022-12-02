package com.food.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.food.pojo.Customer;
import com.food.pojo.Order;
import com.food.utility.DBConnection;
import com.food.utility.ErrorMsg;
import java.time.LocalDate;

public class OrderDaoImpl implements OrderDao 
{
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs=null;
	String sql = "";
	int row =0;
	
	List<Order> olist =null;
	Order order = null;
	Customer customer =null;

	@Override
	public Order placeOrder(Order order) 
	{
		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="INSERT INTO ORDERCUSTOMER(ORDER_CUST_PHONE,ORDER_CUST_ADDRESS,ORDER_DATE,ORDER_STATUS,ORDER_BILL)VALUES(?,?,?,?,?)";
				
				ps = con.prepareStatement(sql);
				
				customer = new OrderDaoImpl().getCustomerDetailsByPhone( order.getOrderCustPhone() );

				ps.setLong(1, order.getOrderCustPhone());
				ps.setString(2, customer.getCustAddress() );
				ps.setDate(3, Date.valueOf( LocalDate.now() ) ); 
				ps.setString(4,"INITIATED");
				ps.setDouble(5,order.getOrderBill());

				row = ps.executeUpdate();

				if(row>0)
				{
					boolean empty = new CartDaoImpl().clearCart( String.valueOf(order.getOrderCustPhone()) , "FOOD");

					if( ! empty)
					{
						System.out.println("OOPS! error");
						System.out.println("BUT YOUR ORDER PLACED SUCCESSFULLY");
					}

					Order placeOrder = null;

					sql = "SELECT * FROM ORDERCUSTOMER ORDER BY ORDER_ID DESC LIMIT 1";
					ps = con.prepareStatement(sql);
					rs= ps.executeQuery();

					if(rs.next())
					{
						placeOrder = new Order();
						placeOrder.setOrderId(rs.getInt("ORDER_ID"));
						placeOrder.setOrderCustPhone(rs.getLong("ORDER_CUST_PHONE"));
						placeOrder.setOrderCustAddress(rs.getString("ORDER_CUST_ADDRESS"));
						placeOrder.setOrderBill(rs.getDouble("ORDER_BILL"));
						placeOrder.setOrderStatus(rs.getString("ORDER_STATUS"));
						placeOrder.setOrderDate(rs.getString("ORDER_DATE"));

						return placeOrder;
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				con.close();
				ps.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<Order> showMyOrderHistory(String custPhone) 
	{
		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="SELECT * FROM ORDERCUSTOMER WHERE ORDER_CUST_PHONE = ?";
				ps = con.prepareStatement(sql);
				
				ps.setLong(1, Long.valueOf(custPhone));
				rs = ps.executeQuery();
				
				olist=new ArrayList<Order>();
				while(rs.next()) 
				{
					order = new Order();
					order.setOrderId(rs.getInt("ORDER_ID"));
					order.setOrderCustPhone(rs.getLong("ORDER_CUST_PHONE"));
					order.setOrderCustAddress(rs.getString("ORDER_CUST_ADDRESS"));
					order.setOrderDate(rs.getString("ORDER_DATE"));
					order.setOrderStatus(rs.getString("ORDER_STATUS"));
					order.setOrderBill(rs.getDouble("ORDER_BILL"));
					
					olist.add(order);
				}
				return olist;
			}
			else
			{
				ErrorMsg.error();
				return null;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				con.close();
				ps.close();
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	
		return null;
	}

	@Override
	public boolean updateStatus(int orderId, String status) 
	{
		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="UPDATE ORDERCUSTOMER SET ORDER_STATUS = ? WHERE ORDER_ID=?";
				ps = con.prepareStatement(sql);

				ps.setString(1, status);
				ps.setInt(2, orderId);
				
				row = ps.executeUpdate();

				if(row>0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				ErrorMsg.error();
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				con.close();
				ps.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return false;
	
	
	}

	@Override
	public List<Order> showAllOrders() 
	{
		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="SELECT * FROM ORDERCUSTOMER ORDER BY ORDER_ID DESC";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				
				olist=new ArrayList<Order>();
				while(rs.next()) 
				{
					order = new Order();
					order.setOrderId(rs.getInt("ORDER_ID"));
					order.setOrderCustPhone(rs.getLong("ORDER_CUST_PHONE"));
					order.setOrderCustAddress(rs.getString("ORDER_CUST_ADDRESS"));
					order.setOrderDate(rs.getString("ORDER_DATE"));
					order.setOrderStatus(rs.getString("ORDER_STATUS"));
					order.setOrderBill(rs.getDouble("ORDER_BILL"));
					olist.add(order);
				}
				return olist;

			}
			else
			{
				ErrorMsg.error();
				return null;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				con.close();
				ps.close();
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	
		return null;
	}
	
	public Customer getCustomerDetailsByPhone(long custPhone)
	{
		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="SELECT * FROM CUSTOMER WHERE CUST_PHONE=?";
				ps = con.prepareStatement(sql);
				ps.setLong(1, custPhone);
				rs = ps.executeQuery();

				while(rs.next()) 
				{
					customer = new Customer();
					
					customer.setCustId(rs.getInt("CUST_ID"));
					customer.setCustName(rs.getString("CUST_NAME"));
					customer.setCustPass(rs.getString("CUST_PASS"));
					customer.setCustAddress(rs.getString("CUST_ADDRESS"));
					
					return customer;
				}
				return null;
			}
			else
			{
				ErrorMsg.error();
				return null;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				con.close();
				ps.close();
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public boolean checkOrderId(int orderId) 
	{
		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="SELECT * FROM ORDERCUSTOMER WHERE ORDER_ID = ?";
				ps = con.prepareStatement(sql);
				
				ps.setInt(1, orderId);
				rs = ps.executeQuery();
				
				while(rs.next()) 
				{
					return true;
				}
			}
			else
			{
				ErrorMsg.error();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				con.close();
				ps.close();
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	
		return false;
	}
	

}