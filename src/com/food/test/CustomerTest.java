package com.food.test;

import java.util.List;
import java.util.Scanner;

import com.food.dao.CustomerDaoImpl;
import com.food.driver.FoodHomePage;
import com.food.pojo.Customer;
import com.food.pojo.Food;
import com.food.utility.ErrorMsg;

public class CustomerTest {
	
	public void customerMenu(String type,String id,String name)
	{
		Scanner sc = new Scanner(System.in);
		
		Customer customer = new Customer();
		CustomerDaoImpl cimpl = new CustomerDaoImpl();
		
		int custId;
		String custName;
		Long custPhone;
		String custPass;
		String custAddress;
		boolean flag;
		List<Customer>clist = null;
		
		while(true)
		{
			System.out.println("\n CUSTOMER MENU");

			if (type.equalsIgnoreCase("NEWLOGIN")|| type.equalsIgnoreCase("ADMIN"))
			{
				System.out.println("1-REGISTER NEW USER");
			}
			if (type.equalsIgnoreCase("CUSTOMER")|| type.equalsIgnoreCase("ADMIN"))
			{
				System.out.println("2-UPDATE YOUR PROFILE");
				System.out.println("3-DELETE ACCOUNT");
			}
			if (type.equalsIgnoreCase("ADMIN"))
			{
				System.out.println("4-SHOW ALL CUSTOMERS");
				System.out.println("5-SHOW CUSTOMER BY PHONE");
			}
			System.out.println("10-EXIT");
			System.out.println("ENTER CHOICE");
			int choice=sc.nextInt();

			switch (choice)
			{
			case 1: 
				if (type.equalsIgnoreCase("NEWLOGIN")|| type.equalsIgnoreCase("ADMIN"))
				{
					System.out.println("YOU SELECT ADD CUSTOMER");
					System.out.println("ENTER CUSTOMER NAME");
					custName = sc.next();

					System.out.println("ENTER CUSTOMER PHONE");
					custPhone = sc.nextLong();

					System.out.println("ENTER CUSTOMER PASSWORD");
					custPass = sc.next();

					System.out.println("ENTER CUSTOMER ADDRESS");
					custAddress = sc.next();

					customer.setCustName(custName);
					customer.setCustPhone(custPhone);
					customer.setCustPass(custPass);
					customer.setCustAddress(custAddress);

					flag = cimpl.addCustomer(customer);

					if(flag )
					{
						System.out.println(custName + "Your account added SUCCESSFULLY");
						System.out.println("WELCOME TO THE WORLD OF FOOD ");
						
						System.out.println("Redirecting to the Home page");
						FoodHomePage.homeMenu();
					}
					else
					{
						System.out.println("CUSTOMER NOT ADDED");
					}
				}
				break;

			case 2: 
				if(type.equalsIgnoreCase("ADMIN") || ((type.equalsIgnoreCase("CUSTOMER"))&& (id!=null) && (!id.equalsIgnoreCase(""))&& (id.length()>0)))
				{
					System.out.println("YOU SELECT UPDATE CUSTOMER");
					System.out.println("PLEASE ENTER ID OF CUSTOMER");
					custId = sc.nextInt();

					System.out.println("ENTER UPDATED CUSTOMER NAME");
					custName = sc.next();

					System.out.println("ENTER UPDATED PHONE NUMBER");
					custPhone = sc.nextLong();

					System.out.println("ENTER UPDATED PASSWORD");
					custPass = sc.next();

					System.out.println("ENTER UPDATED ADDRESS");
					custAddress = sc.next();

					customer.setCustName(custName);
					customer.setCustPhone(custPhone);
					customer.setCustPass(custPass);
					customer.setCustAddress(custAddress);
					customer.setCustId(custId);

					flag = cimpl.updateCustomer(customer);

					if(flag )
					{
						System.out.println("CUSTOMER UPDATED SUCCESSFULLY");
					}
					else
					{
						System.out.println("CUSTOMER NOT UPDATED");
					}
				}
				break;

			case 3: 
				if((type.equalsIgnoreCase("CUSTOMER"))&& (id!=null) && (!id.equalsIgnoreCase(""))&& (id.length()>0))
				{
					System.out.println("YOU SELECT DELETE CUSTOMER");
					System.out.println("PLEASE ENTER ID OF CUSTOMER");
					custId = sc.nextInt();

					customer.setCustId(custId);

					flag = cimpl.deleteCustomer(customer);

					if(flag )
					{
						System.out.println("CUSTOMER DELETED SUCCESSFULLY");
					}
					else
					{
						System.out.println("CUSTOMER NOT DELETED");
					}
				}
				break;

			case 4: 
				if(type.equalsIgnoreCase("ADMIN"))
				{
					System.out.println("YOU SELECT SHOW ALL CUSTOMERS");
					clist=cimpl.showAllCustomers();

					if( clist == null || clist.isEmpty() == true )
					{
						ErrorMsg.emptyList();
					}
					else
					{
						System.out.println("CUST_ID  CUST_NAME  CUST_PHONE  CUST_PASS  CUST_ADDRESS");
						for(Customer c:clist)
						{
							System.out.println(c.getCustId()+"       "+c.getCustName()+"     "+c.getCustPhone()+"         "+c.getCustPass()+"             "+c.getCustAddress());
						}
					}
				}
				break;

			case 5: 
				if (type.equalsIgnoreCase("ADMIN"))
				{
					System.out.println("YOU SELECT SHOW CUSTOMER BY PHONE");
					System.out.println("PLEASE ENTER PHONE OF CUSTOMER");
					custPhone = sc.nextLong();
					clist = cimpl.showCustomerByPhone(custPhone);

					if( clist == null || clist.isEmpty() == true )
					{
						ErrorMsg.emptyList();
					}
					else
					{
						System.out.println("CUST_ID  CUST_NAME  CUST_PHONE  CUST_PASS  CUST_ADDRESS");
						for(Customer c:clist)
						{
							System.out.println(c.getCustId()+"         "+c.getCustName()+"     "+c.getCustPhone()+"    "+c.getCustPass()+"   "+c.getCustAddress());
						}
					}
				}
				break;

			case 10: 
				ErrorMsg.exitMessage();

			default :
				ErrorMsg.invalidChoice();

			}
		}
	}

}
