package com.food.test;

import java.util.Scanner;

import com.food.dao.LoginDaoImpl;
import com.food.driver.FoodHomePage;
import com.food.utility.ErrorMsg;

public class LoginTest 
{
	
	public void loginMenu()
	{
		LoginDaoImpl limpl = new LoginDaoImpl();
		Scanner sc = new Scanner(System.in);
		String login;
		int b=0;

		for (int i=1;i<=3;i++)
		{
			System.out.println(" ***LOGIN PAGE*** ");
			System.out.println("Enter Your Phone");
			String custPhone = sc.next();
			System.out.println("Enter Your Pass");
			String custPass = sc.next();

			login = limpl.checkUser(custPhone.trim() ,custPass.trim(), "NEWLOGIN");

			if(login!= null && (!login.equalsIgnoreCase("")) && (login.length()>0) &&(!login.equalsIgnoreCase("INVALID"))  )
			{
				String type = login.substring(0,login.indexOf("_"));

				String name = login.substring(login.indexOf("_")+1);

				System.out.println("LOGIN SUCCESSFUL");
				System.out.println("HELLO, " + type + " - "+ name.toUpperCase());

				new LoginTest().userMenu(type,custPhone,name);

				b=1;
				break;
			}
			else 
			{
				ErrorMsg.invalidLogin();
			}
		}
		if (b==0)
		{
			ErrorMsg.maxAttemptsLogin();
			
			System.out.println("Not an User ? want to register your self?");
			System.out.println("Please press 1 to Register & any other key to exit");
			int choice = sc.nextInt();
			if(choice == 1)
			{
				CustomerTest customer = new CustomerTest();
				customer.customerMenu("NEWLOGIN","","");
			}
			else
			{
				FoodHomePage.homeMenu();
			}
		}
	}
	
	public void userMenu(String type,String id,String name)
	{
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		if(type.equalsIgnoreCase("CUSTOMER"))
		{
			System.out.println("1-VIEW FOODS");
			System.out.println("2-SHOW MY CART");
			System.out.println("3-BUY MY PRODUCTS/ORDERS");
			System.out.println("4-MY PROFILE(UPDATE/DELETE)");
			System.out.println("10-EXIT");
			choice = sc.nextInt();
			switch(choice)
			{
			case 1: 
				FoodTest food = new FoodTest();
				food.foodMenu(type, id);
				break;
			case 2:
				CartTest cart = new CartTest();
				cart.cartMenu(type,id,name);
				break;
			case 3:
				OrderTest order = new OrderTest();
				order.orderMenu(type,id,name);
			case 4:
				CustomerTest customer = new CustomerTest();
				customer.customerMenu(type,id,name);
				break;
			case 10:
				ErrorMsg.exitMessage();
			default:
				ErrorMsg.invalidChoice();
			}
		}
		
		if(type.equalsIgnoreCase("ADMIN"))
		{
			System.out.println("1-VIEW FOODS");
			System.out.println("2-SHOW/UPDATE ORDER STATUS");
			System.out.println("3-CUSTOMER MENU (UPDATE/DELETE/SHOW)");
			System.out.println("10-EXIT");
			choice = sc.nextInt();
			
			switch(choice)
			{
			case 1: 
				FoodTest food = new FoodTest();
				food.foodMenu(type,id);
				break;
			case 2:
				OrderTest order = new OrderTest();
				order.orderMenu(type,id,name);
			case 3:
				CustomerTest customer = new CustomerTest();
				customer.customerMenu(type,id,name);
				break;
			case 10:
				ErrorMsg.exitMessage();
			default:
				ErrorMsg.invalidChoice();
			}
		}
		
	}

}
