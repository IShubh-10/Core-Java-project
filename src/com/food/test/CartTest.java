package com.food.test;

import java.util.List;
import java.util.Scanner;

import com.food.dao.CartDaoImpl;
import com.food.dao.FoodDaoImpl;
import com.food.dao.LoginDaoImpl;
import com.food.pojo.Cart;
import com.food.pojo.Food;
import com.food.utility.ErrorMsg;

public class CartTest {	

	public void cartMenu(String type, String custPhone, String name)
	{
		Scanner sc = new Scanner(System.in);
		CartDaoImpl cimpl = new CartDaoImpl();
		FoodDaoImpl fimpl = new FoodDaoImpl();
		Cart cart = new Cart();
		int foodId;
		int foodQuantity;
		boolean flag;
		int cartId;
		List<Cart>clist = null;
		System.out.println( name +" YOUR CART");

		while(true) 
		{
			System.out.println("ENTER YOUR CHOICE");
			System.out.println("1-ADD TO CART");
			System.out.println("2-UPDATE QUANTITY");
			System.out.println("3-DELETE FROM CART");
			System.out.println("4-CLEAR CART");
			System.out.println("5-SHOW MY CART");
			System.out.println("6-GO TO MY ORDERS");
			System.out.println("10-EXIT");

			int choice = sc.nextInt();

			switch(choice)
			{

			case 1: 
				System.out.println("YOU SELECT ADD TO CART");

				System.out.println("ENTER FOOD ID ?");
				foodId = sc.nextInt();
				
				flag = fimpl.checkFoodId(foodId);

				if(flag)
				{
					System.out.println("ENTER FOOD QUANTITY");
					foodQuantity = sc.nextInt();

					flag = cimpl.checkFoodAvaibility(foodId, foodQuantity );

					if(flag)
					{
						cart.setCartFoodId(foodId);
						cart.setCartCustPhone(custPhone);
						cart.setCartFoodQuantity(foodQuantity);

						flag = cimpl.addToCart(cart);

						if(flag)
						{
							System.out.println("ADD TO CART SUCCESSFULLY");
						}
						else
						{
							System.out.println("NOT ADDED");
						}
					}
					else
					{
						ErrorMsg.outOfStock();
					}
				}
				else
				{
					ErrorMsg.invalidId();
				}
				break;

			case 2: 
				System.out.println("YOU SELECT UPDATE QUANTITY");

				System.out.println("ENTER CART ID");
				cartId =sc.nextInt();
				
				flag = cimpl.checkCartId(cartId);
				
				if(flag)
				{
					System.out.println("ENTER UPDATED QUANTITY");
					foodQuantity = sc.nextInt();

					foodId =cimpl.getFoodIdByCartId(cartId);
					flag = cimpl.checkFoodAvaibility(foodId,foodQuantity );

					if(flag)
					{
						cart.setCartId(cartId);
						cart.setCartFoodQuantity(foodQuantity);

						flag = cimpl.updateQuantity(cart);

						if(flag)
						{
							System.out.println("UPDATE CART SUCCESSFULLY");
						}
						else
						{
							System.out.println("NOT UPDATED");
						}
					}
					else
					{
						ErrorMsg.outOfStock();
					}
				}
				else
				{
					ErrorMsg.invalidId();
				}
				break;

			case 3: 
				System.out.println("YOU SELECT DELETE FROM CART");

				System.out.println("PLEASE ENTER ID OF CART");
				cartId = sc.nextInt();

				flag = cimpl.checkCartId(cartId);
				
				if(flag)
				{
					cart.setCartId(cartId);
					
					flag = cimpl.deleteFromCart(cart);

					if(flag )
					{
						System.out.println("CART DELETED SUCCESSFULLY");
					}
					else
					{
						System.out.println("CART NOT DELETED");
					}
				}
				else
				{
					ErrorMsg.invalidId();
				}
				break;

			case 4: 
				System.out.println("YOU SELECT CLEAR CART");

				flag = cimpl.clearCart(custPhone, "CART");

				if(flag )
				{
					System.out.println("CART DELETED SUCCESSFULLY");
				}
				else
				{
					System.out.println(" NOT DELETED");
				}
				break;

			case 5: 
				System.out.println("YOU SELECT SHOW MY CART");

				clist = cimpl.showMyCart(custPhone);
				
				if( clist == null || clist.isEmpty() == true )
				{
					ErrorMsg.emptyList();
				}
				else
				{
					double bill =0;
					System.out.println("CARTID FOODNAME QUANTITY PRICE");

					for(Cart c : clist)
					{
						System.out.println(c.getCartId()+"       "+c.getFood().getFoodName()+"    "+c.getCartFoodQuantity()+"    "+c.getCartFoodPrice());
						bill = bill + c.getCartFoodPrice();
					}
					System.out.println("YOUR TOTAL AMOUNT TO PAY :- "+ bill + " Rs.");
				}
				break;

			case 6:
				System.out.println("YOU SELECT GO TO MY ORDERS");
				OrderTest order = new OrderTest();
				order.orderMenu(type,custPhone,name);
				break;

			case 10: 
					ErrorMsg.exitMessage();

			default: 
				ErrorMsg.invalidChoice();
			}
		}
	}

}
