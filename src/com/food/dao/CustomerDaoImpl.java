package com.food.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.food.pojo.Customer;
import com.food.pojo.Food;
import com.food.utility.DBConnection;
import com.food.utility.ErrorMsg;

public class CustomerDaoImpl implements CustomerDao {

	Connection con = null;
	PreparedStatement ps = null;
	String sql = "";
	int row =0;
	ResultSet rs=null;
	Customer customer= null;
	List<Customer>clist=null;

	@Override
	public boolean addCustomer(Customer customer) {

		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="INSERT INTO CUSTOMER(CUST_NAME,CUST_PHONE,CUST_PASS,CUST_ADDRESS)VALUES(?,?,?,?)";
				ps = con.prepareStatement(sql);

				ps.setString(1, customer.getCustName());
				ps.setLong(2, customer.getCustPhone());
				ps.setString(3, customer.getCustPass());
				ps.setString(4, customer.getCustAddress());

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
	public boolean updateCustomer(Customer customer) {


		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="UPDATE CUSTOMER SET CUST_NAME = ?,CUST_PHONE = ?,CUST_PASS = ?,CUST_ADDRESS = ? WHERE CUST_ID=?";
				ps = con.prepareStatement(sql);

				ps.setString(1,customer.getCustName());
				ps.setLong(2, customer.getCustPhone());
				ps.setString(3, customer.getCustPass());
				ps.setString(4, customer.getCustAddress());
				ps.setInt(5,customer.getCustId());

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
	public boolean deleteCustomer(Customer customer) {



		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="DELETE FROM CUSTOMER WHERE CUST_ID=?";
				ps = con.prepareStatement(sql);

				ps.setInt(1,customer.getCustId());

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
	public List<Customer> showAllCustomers() {

		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="SELECT * FROM CUSTOMER";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();

				clist=new ArrayList<Customer>();
				while(rs.next()) {
					customer = new Customer();
					customer.setCustId(rs.getInt("CUST_ID"));
					customer.setCustName(rs.getString("CUST_NAME"));
					customer.setCustPhone(rs.getLong("CUST_PHONE"));
					customer.setCustPass(rs.getString("CUST_PASS"));
					customer.setCustAddress(rs.getString("CUST_ADDRESS"));

					clist.add(customer);
				}
				return clist;

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
	public List<Customer> showCustomerByPhone(long custPhone) {


		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="SELECT * FROM CUSTOMER WHERE CUST_PHONE=?";
				ps = con.prepareStatement(sql);
				ps.setLong(1, custPhone);
				rs = ps.executeQuery();

				clist=new ArrayList<Customer>();
				while(rs.next()) {
					customer = new Customer();
					customer.setCustId(rs.getInt("CUST_ID"));
					customer.setCustName(rs.getString("CUST_NAME"));
					customer.setCustPhone(rs.getLong("CUST_PHONE"));
					customer.setCustPass(rs.getString("CUST_PASS"));
					customer.setCustAddress(rs.getString("CUST_ADDRESS"));
					clist.add(customer);
				}
				return clist;


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

}
