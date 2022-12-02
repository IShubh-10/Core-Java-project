package com.food.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection createConnection()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FUSIONKITCHEN","root","1234");
			
			return con;
		}
		catch(ClassNotFoundException e)
		{
			ErrorMsg.connectionError();
		}
		catch(SQLException e)
		{
			ErrorMsg.connectionError();
		}
		catch(Exception e)
		{
			ErrorMsg.connectionError();
		}
		return con;
	}
}
