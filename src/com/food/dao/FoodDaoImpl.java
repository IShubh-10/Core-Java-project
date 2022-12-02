package com.food.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.food.pojo.Food;
import com.food.utility.DBConnection;
import com.food.utility.ErrorMsg;

public class FoodDaoImpl implements FoodDao{

	Connection con = null;
	PreparedStatement ps = null;
	String sql = "";
	int row =0;
	ResultSet rs=null;
	List<Food>flist=null;
	Food food = null;

	@Override
	public boolean addFood(Food food) {
		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="INSERT INTO FOOD(FOOD_NAME,FOOD_TYPE,FOOD_QUANTITY,FOOD_PRICE)VALUES(?,?,?,?)";
				ps = con.prepareStatement(sql);

				ps.setString(1, food.getFoodName());
				ps.setString(2, food.getFoodType());
				ps.setInt(3, food.getFoodQuantity());
				ps.setDouble(4, food.getFoodPrice());

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
	public boolean updateFood(Food food) 
	{
		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="UPDATE FOOD SET FOOD_NAME = ?,FOOD_TYPE = ?,FOOD_QUANTITY = ?,FOOD_PRICE = ? WHERE FOOD_ID=?";
				ps = con.prepareStatement(sql);

				ps.setString(1, food.getFoodName());
				ps.setString(2, food.getFoodType());
				ps.setInt(3, food.getFoodQuantity());
				ps.setDouble(4, food.getFoodPrice());
				ps.setInt(5,food.getFoodID());

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
	public boolean deleteFood(Food food) {
		
		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="DELETE FROM FOOD WHERE FOOD_ID=?";
				ps = con.prepareStatement(sql);

				ps.setInt(1,food.getFoodID());

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
	public List<Food> showAllFoods() {
		

		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="SELECT * FROM FOOD";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				
				flist=new ArrayList<Food>();
				while(rs.next()) {
					food = new Food();
					food.setFoodID(rs.getInt("FOOD_ID"));
					food.setFoodName(rs.getString("FOOD_NAME"));
					food.setFoodType(rs.getString("FOOD_TYPE"));
					food.setFoodQuantity(rs.getInt("FOOD_QUANTITY"));
					food.setFoodPrice(rs.getDouble("FOOD_PRICE"));
					flist.add(food);
				}
				return flist;

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
	public List<Food> showFoodById(int foodId) {

		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="SELECT * FROM FOOD WHERE FOOD_ID=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, foodId);
				rs = ps.executeQuery();
				
				flist=new ArrayList<Food>();
				while(rs.next()) {
					food = new Food();
					food.setFoodID(rs.getInt("FOOD_ID"));
					food.setFoodName(rs.getString("FOOD_NAME"));
					food.setFoodType(rs.getString("FOOD_TYPE"));
					food.setFoodQuantity(rs.getInt("FOOD_QUANTITY"));
					food.setFoodPrice(rs.getDouble("FOOD_PRICE"));
					flist.add(food);
				}
				return flist;

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
	public List<Food> showFoodByName(String foodName) {

		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="SELECT * FROM FOOD WHERE FOOD_NAME=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, foodName);
				rs = ps.executeQuery();
				
				flist=new ArrayList<Food>();
				while(rs.next()) {
					food = new Food();
					food.setFoodID(rs.getInt("FOOD_ID"));
					food.setFoodName(rs.getString("FOOD_NAME"));
					food.setFoodType(rs.getString("FOOD_TYPE"));
					food.setFoodQuantity(rs.getInt("FOOD_QUANTITY"));
					food.setFoodPrice(rs.getDouble("FOOD_PRICE"));
					flist.add(food);
				}
				return flist;

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
	public List<Food> showFoodByType(String foodType) {

		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="SELECT * FROM FOOD WHERE FOOD_TYPE=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, foodType);
				rs = ps.executeQuery();
				
				flist=new ArrayList<Food>();
				while(rs.next()) {
					food = new Food();
					food.setFoodID(rs.getInt("FOOD_ID"));
					food.setFoodName(rs.getString("FOOD_NAME"));
					food.setFoodType(rs.getString("FOOD_TYPE"));
					food.setFoodQuantity(rs.getInt("FOOD_QUANTITY"));
					food.setFoodPrice(rs.getDouble("FOOD_PRICE"));
					flist.add(food);
				}
				return flist;

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
	public List<Food> showFoodByPriceRange(int min, int max) {


		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="SELECT * FROM FOOD WHERE FOOD_PRICE BETWEEN "+min+" AND "+max;
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				
				flist=new ArrayList<Food>();
				while(rs.next()) {
					food = new Food();
					food.setFoodID(rs.getInt("FOOD_ID"));
					food.setFoodName(rs.getString("FOOD_NAME"));
					food.setFoodType(rs.getString("FOOD_TYPE"));
					food.setFoodQuantity(rs.getInt("FOOD_QUANTITY"));
					food.setFoodPrice(rs.getDouble("FOOD_PRICE"));
					flist.add(food);
				}
				return flist;

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
	public Food showFoodDetailsById(int foodId) 
	{
		
		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="SELECT * FROM FOOD WHERE FOOD_ID=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, foodId);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					food = new Food();
					food.setFoodID(rs.getInt("FOOD_ID"));
					food.setFoodName(rs.getString("FOOD_NAME"));
					food.setFoodType(rs.getString("FOOD_TYPE"));
					food.setFoodQuantity(rs.getInt("FOOD_QUANTITY"));
					food.setFoodPrice(rs.getDouble("FOOD_PRICE"));
				}
				return food;

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
	public boolean checkFoodId(int foodId) 
	{
		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="SELECT * FROM FOOD WHERE FOOD_ID=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, foodId);
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

	@Override
	public boolean updateFoodDetails(int foodId, int foodQuantity) 
	{
		try
		{
			int prevQty = 0;
			int updatedQty = 0;
			
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="SELECT * FROM FOOD WHERE FOOD_ID=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, foodId);
				rs = ps.executeQuery();
				
				while(rs.next()) 
				{
					prevQty = rs.getInt("FOOD_QUANTITY");
				}
			
				updatedQty = prevQty - foodQuantity;
				
				sql ="UPDATE FOOD SET FOOD_QUANTITY = ? WHERE FOOD_ID=?";
				ps = con.prepareStatement(sql);

				ps.setInt(1, updatedQty);
				ps.setInt(2, foodId);

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
}
