package com.food.test;

import java.util.List;
import java.util.Scanner;

import com.food.dao.CartDaoImpl;
import com.food.dao.OrderDaoImpl;
import com.food.pojo.Cart;
import com.food.pojo.Order;
import com.food.utility.ErrorMsg;

public class OrderTest 
{
	public void orderMenu( String type, String id, String name )
	{
		Scanner sc = new Scanner(System.in);
		Order order = null;
		OrderDaoImpl oimpl = new OrderDaoImpl();
		int orderId;
		double totalBill=0;
		boolean flag = false;
		List<Order>olist=null;
		List<Cart>clist = new CartDaoImpl().showMyCart(id);

		if(type.equalsIgnoreCase("CUSTOMER") && (  clist==null || clist.isEmpty()==true ) )  ////////////////////////////
		{
			System.out.println(name + " YOUR CART IS EMPTY !!!! YOU ARE NOT ADDED ANYTHING!");
			
			System.out.println("\n PLEASE SELECT YOUR CHOICE");
			System.out.println("1 - ADD TO CART");
			System.out.println("2- YOUR PREVIOUS ORDER HISTORY");
			System.out.println("10-EXIT");

			int choice = sc.nextInt();

			switch(choice)
			{
			case 1: 
				CartTest cart = new CartTest();
				cart.cartMenu("CUSTOMER", id, name);
				break;

			case 2:
				System.out.println("You select show My Order History");
				olist = oimpl.showMyOrderHistory(id);   
				if( olist == null || olist.isEmpty() == true )
				{
					ErrorMsg.emptyList();
					new OrderTest().orderMenu( type,id,name );
				}
				else
				{
					System.out.println("ORDERID PHONE ADDRESS BILL STATUS DATE");
					for(Order o:olist)
					{
						System.out.println(o.getOrderId()+"    "+o.getOrderCustPhone()+"    "+o.getOrderCustAddress()+"   "+o.getOrderBill()+"   "+o.getOrderStatus()+"    "+o.getOrderDate());
					}
				}
				break;

			case 10: 
				ErrorMsg.exitMessage();

			default: 
				ErrorMsg.invalidChoice();
			}
		}

		if(type.equalsIgnoreCase("CUSTOMER"))   
		{
			System.out.println("******"+name+"'s  ORDERS*******");

			System.out.println("CARTID FOODNAME QUANTITY PRICE TOTALPRICE");

			for(Cart c : clist)
			{
				System.out.println(c.getCartId()+"       "+c.getFood().getFoodName()+"    "+c.getCartFoodQuantity()
				+"    "+c.getFood().getFoodPrice()+"      "+c.getCartFoodPrice());

				totalBill = totalBill +c.getCartFoodPrice();
			}
			System.out.println("\n TOTAL AMOUNT PAYABLE:- "+totalBill+ " Rs.");
		}
		
		while(true) 
		{
			System.out.println("\n --ORDER MENU--");
			
			if(type.equalsIgnoreCase("CUSTOMER"))
			{
				System.out.println("ENTER YOUR CHOICE");
				System.out.println("1-CONFIRM YOUR ORDER");
				System.out.println("2-CANCEL ORDER");
				System.out.println("3-BACK TO CART/UPDATE CART");
				System.out.println("4-Show my Order history");
			}
			if(type.equalsIgnoreCase("ADMIN"))
			{
				System.out.println("5-UPDATE STATUS");
				System.out.println("6-SHOW ALL ORDERS");
			}
			System.out.println("10-EXIT");
			System.out.println("Please enter your choice...");

			int choice = sc.nextInt();
			switch(choice)
			{
			case 1: 
				if(type.equalsIgnoreCase("CUSTOMER"))
				{
					System.out.println("PLACING YOUR ORDER.... PLEAE WAIT!");
					order = new Order();
					order.setOrderCustPhone(Long.valueOf(id));
					order.setOrderBill(totalBill);

					Order placeorder =oimpl.placeOrder(order);
					
					if(placeorder != null)
					{
						System.out.println("YOUR ORDER HAS BEEN PLACED SUCCESSFULLY !");
						System.out.println("YOU WILL GET CONFIRMATION MESSAGE IN 1 HOUR");

						System.out.println("\n ****  YOUR  ORDER SUMMARY  ****");
						System.out.println("NAME :-"+name);
						System.out.println("DATE :-"+placeorder.getOrderDate());
						System.out.println("PHONE :-"+placeorder.getOrderCustPhone());
						System.out.println("ADDRESS :-"+placeorder.getOrderCustAddress());
						System.out.println("BILL :-"+placeorder.getOrderBill());
						System.out.println("STATUS :-"+placeorder.getOrderStatus());
						
						
						System.out.println("\n Continue shooping ? ");
						System.out.println("Press 1 to shopping");
						System.out.println("Press 2 to Cart");
						System.out.println("& any other key to exit");
						int redirect = sc.nextInt();
						if( redirect == 1 )
						{
							FoodTest food = new FoodTest();
							food.foodMenu(type, id);
						}
						else if( redirect == 2 )
						{
							CartTest cart = new CartTest();
							cart.cartMenu("CUSTOMER", id, name);
						}
					}
					else
					{
						ErrorMsg.error();
					}
				}
				break;

			case 2:
				if(type.equalsIgnoreCase("CUSTOMER"))
				{
					System.out.println("CANCEL ORDER");
					System.out.println("PLEASE ENTER YOUR ORDER ID");
					orderId = sc.nextInt();
					
					flag = oimpl.checkOrderId(orderId);

					if(flag)
					{
						flag = oimpl.updateStatus(orderId, "USER_CANCELLED");

						if(flag)
						{
							System.out.println("YOUR ORDER CANCEL SUCCESSFULLY");

							System.out.println("Do you want to go Back to CART");
							System.out.println("Press 1 to Yes and any other key to EXIT");
							int redirect = sc.nextInt();
							if( redirect == 1)
							{
								System.out.println("REDIRECTING TO YOUR CART....");
								CartTest cart = new CartTest();
								cart.cartMenu("CUSTOMER", id, name);
							}
						}
						else
						{
							ErrorMsg.error();
						}
					}
					else
					{
						ErrorMsg.invalidId();
					}
				}
				break;

			case 3:
				if(type.equalsIgnoreCase("CUSTOMER"))
				{
					System.out.println("BACK TO YOUR CART");
					CartTest cart = new CartTest();
					cart.cartMenu("CUSTOMER", id, name);
				}
				break;

			case 4:
				if(type.equalsIgnoreCase("CUSTOMER"))
				{
					System.out.println("You select Order History");
					
					olist = oimpl.showMyOrderHistory( id );
					
					if( olist == null || olist.isEmpty() == true )
					{
						ErrorMsg.emptyList();
					}
					else
					{
						System.out.println("ORDERID PHONE ADDRESS BILL STATUS DATE");
						for(Order o:olist)
						{
							System.out.println(o.getOrderId()+"    "+o.getOrderCustPhone()+"    "
									+o.getOrderCustAddress()+"   "+o.getOrderBill()+"   "
									+o.getOrderStatus()+"    "+o.getOrderDate());
						}
					}
				}
				
			case 5:
				if(type.equalsIgnoreCase("ADMIN"))
				{
					System.out.println("UPDATE STATUS");
					
					System.out.println("PLEASE ENTER YOUR ORDER ID");
					orderId = sc.nextInt();
					
					flag = oimpl.checkOrderId(orderId);

					if(flag)
					{
						System.out.println("PLEASE ENTER YOUR ORDER STATUS");
						String orderStatus = sc.next();

						flag = oimpl.updateStatus(orderId, orderStatus);

						if(flag)
						{
							System.out.println("STATUS UPDATED SUCCESSFULLY");
						}
						else
						{
							System.out.println("NOT UPDATED");
						}
					}
					else
					{
						ErrorMsg.invalidId();
					}
				}
				break;

			case 6:
				if(type.equalsIgnoreCase("ADMIN")) 
				{
					System.out.println("YOU SELECT SHOW MY ORDER");
					olist = oimpl.showAllOrders();

					if( olist == null || olist.isEmpty() == true )
					{
						ErrorMsg.emptyList();
					}
					else
					{
						System.out.println("ORDERID PHONE ADDRESS BILL STATUS DATE");
						for(Order o:olist)
						{
							System.out.println(o.getOrderId()+"    "+o.getOrderCustPhone()+"    "
									+o.getOrderCustAddress()+"   "+o.getOrderBill()+"   "
									+o.getOrderStatus()+"    "+o.getOrderDate());
						}
					}
				}
				break;

			case 10:
				ErrorMsg.exitMessage();
				
			default:
				ErrorMsg.invalidChoice();
			}
		}

	}

}
