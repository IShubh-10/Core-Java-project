package com.food.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.food.pojo.Cart;
import com.food.pojo.Food;
import com.food.utility.DBConnection;
import com.food.utility.ErrorMsg;

public class CartDaoImpl implements CartDao 
{
	Connection con = null;
	PreparedStatement ps = null;
	String sql = "";
	int row =0;
	ResultSet rs=null;
	int cartId;
	List<Cart>clist = null;
	Cart cart = null;

	@Override
	public boolean addToCart(Cart cart) {

		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="INSERT INTO CART(CART_CUST_PHONE,CART_FOOD_ID,CART_FOOD_QUANTITY,CART_FOOD_BILL)VALUES(?,?,?,?)";
				ps = con.prepareStatement(sql);

				ps.setLong(1,Long.valueOf(cart.getCartCustPhone()));
				ps.setInt(2, cart.getCartFoodId());
				ps.setInt(3, cart.getCartFoodQuantity());
				
				
				//TO GET FOOD PRICE FROM FOOD TABLE
				Food food = new FoodDaoImpl().showFoodDetailsById(cart.getCartFoodId());
				double price = food.getFoodPrice();
				int qty = cart.getCartFoodQuantity();
				double bill = price * qty;
				
				ps.setDouble(4, bill);

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
	public boolean updateQuantity(Cart cart) {


		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="UPDATE CART SET CART_FOOD_QUANTITY = ? ,CART_FOOD_BILL = ? WHERE CART_ID=?";
				ps = con.prepareStatement(sql);

				
				ps.setInt(1,cart.getCartFoodQuantity() );
				
				//to get food id from cart id
				
				int foodId = new CartDaoImpl().getFoodIdByCartId(cart.getCartId());
				
				//TO GET FOOD PRICE FROM FOOD TABLE
				Food food = new FoodDaoImpl().showFoodDetailsById(foodId);
				double price = food.getFoodPrice();
				int qty = cart.getCartFoodQuantity();
				double bill = price * qty;
				
				ps.setDouble(2, bill);
				
				ps.setInt(3,cart.getCartId());

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
	public boolean deleteFromCart(Cart cart) {

		
		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="DELETE FROM CART WHERE CART_ID=?";
				ps = con.prepareStatement(sql);

				ps.setInt(1,cart.getCartId());

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
	public boolean clearCart(String custPhone, String update) 
	{
		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				if(update!=null && update.equals("FOOD"))
				{
					try
					{
						sql = "SELECT * FROM CART WHERE CART_CUST_PHONE = ?";

						ps = con.prepareStatement(sql);
						ps.setLong(1, Long.valueOf( custPhone ));

						rs = ps.executeQuery();

						while( rs.next() )
						{
							new FoodDaoImpl().updateFoodDetails(rs.getInt("CART_FOOD_ID"), rs.getInt("CART_FOOD_QUANTITY") );
						}
					}
					catch (Exception e) 
					{
						ErrorMsg.error();
					}
				}
				
				
				sql ="DELETE FROM CART WHERE CART_CUST_PHONE=?";
				ps = con.prepareStatement(sql);

				ps.setString(1,custPhone);

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
	public List<Cart> showMyCart(String custPhone) {

		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="SELECT * FROM CART WHERE CART_CUST_PHONE = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, custPhone);
				rs = ps.executeQuery();
				
				clist=new ArrayList<Cart>();
				while(rs.next()) 
				{
					cart = new Cart();
					cart.setCartId(rs.getInt("CART_ID"));
					cart.setCartCustPhone(rs.getString("CART_CUST_PHONE"));
					cart.setCartFoodId(rs.getInt("CART_FOOD_ID"));
					cart.setCartFoodQuantity(rs.getInt("CART_FOOD_QUANTITY"));
					cart.setCartFoodPrice(rs.getInt("CART_FOOD_BILL"));
					
					Food food = new FoodDaoImpl().showFoodDetailsById(rs.getInt("CART_FOOD_ID"));
					cart.setFood(food);
					
					clist.add(cart);
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
	public int getFoodIdByCartId(int cartId) 
	{
		int foodId = 0;
		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="SELECT CART_FOOD_ID FROM CART WHERE CART_ID = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1,cartId);
				rs = ps.executeQuery();
				
			
				while(rs.next()) {
					
					foodId = rs.getInt("CART_FOOD_ID");
					
				}
				return foodId;

			}
			else
			{
				ErrorMsg.error();
				return foodId;
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
		return foodId;
	}


	@Override
	public boolean checkFoodAvaibility(int foodId, int quantity) 
	{
		int qty=0;
		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="SELECT FOOD_QUANTITY FROM FOOD WHERE FOOD_ID = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1,foodId);
				rs = ps.executeQuery();
				
				while( rs.next() ) 
				{
					qty = rs.getInt("FOOD_QUANTITY");
				}
				
				if( qty >= quantity )
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
	public boolean checkCartId(int cartId) {
		try
		{
			con = DBConnection.createConnection();
			if(con != null)
			{
				sql ="SELECT * FROM CART WHERE CART_ID=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, cartId);
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
