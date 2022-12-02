package com.food.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.food.utility.DBConnection;

public class LoginDaoImpl implements LoginDao {

	Connection con = null;
	PreparedStatement ps = null;
	String sql = "";
	int row =0;
	ResultSet rs=null;

	@Override
	public String checkUser(String custPhone, String custPass , String userType) 
	{
		String name ="INVALID";
		try
		{
			if( custPhone!=null && custPass!=null  )
			{
				con = DBConnection.createConnection();

				if(con != null)
				{
					if(userType.equalsIgnoreCase("CUSTOMER")  || userType.equalsIgnoreCase("NEWLOGIN"))
					{
						sql = "SELECT * FROM CUSTOMER WHERE CUST_PHONE=? AND CUST_PASS=?";
						ps = con.prepareStatement(sql);
						ps.setString(1, custPhone);
						ps.setString(2, custPass);

						rs = ps.executeQuery();
						while (rs.next())
						{
							name=rs.getString("CUST_NAME");
							return "CUSTOMER_"+name;
						}
					}

					if(userType.equalsIgnoreCase("ADMIN")|| userType.equalsIgnoreCase("NEWLOGIN"))
					{
						sql = "SELECT * FROM ADMIN WHERE ADMIN_UID=? AND ADMIN_PASS=?";
						ps = con.prepareStatement(sql);
						ps.setString(1, custPhone);
						ps.setString(2, custPass);

						rs = ps.executeQuery();
						while (rs.next())
						{
							name=rs.getString("ADMIN_UID");
							return "ADMIN_"+name;
						}
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
		return name; //////////////////////////
	}
}
