package com.food.test;

import java.util.List;
import java.util.Scanner;

import com.food.dao.FoodDaoImpl;
import com.food.pojo.Food;
import com.food.utility.ErrorMsg;

public class FoodTest {

	public void foodMenu(String type, String id)
	{
		Scanner sc = new Scanner(System.in);

		Food food = new Food();
		FoodDaoImpl fimpl = new FoodDaoImpl();

		int foodId;
		String foodName;
		String foodType;
		int foodQuantity;
		double foodPrice;
		boolean flag;
		List<Food>flist = null;

		while(true)
		{
			System.out.println("\n FOOD MENU");
			System.out.println("1-SHOW ALL FOOD");
			System.out.println("2-SEARCH FOOD BY ID");
			System.out.println("3-SEARCH FOOD BY NAME");
			System.out.println("4-SEARCH FOOD BY TYPE");
			System.out.println("5-SEARCH FOOD BY PRICE RANGE");

			if (type.equalsIgnoreCase("ADMIN"))
			{
				System.out.println(" - ADMIN - ");
				System.out.println("6-ADD FOOD");
				System.out.println("7-UPDATE FOOD");
				System.out.println("8-DELETE FOOD");
			}

			System.out.println("9-ADD TO CART");
			System.out.println("10-EXIT");
			System.out.println("ENTER CHOICE");
			int choice=sc.nextInt();

			switch (choice)
			{
			case 1:
				System.out.println("YOU SELECT SHOW ALL FOOD");
				flist=fimpl.showAllFoods();

				if( flist == null || flist.isEmpty() == true )
				{
					ErrorMsg.emptyList();
				}
				else
				{
					System.out.println("FOOD_ID  FOOD_NAME  FOOD_TYPE  FOOD_QUANTITY  FOOD_PRICE");
					for(Food f:flist)
					{
						System.out.println(f.getFoodID()+"       "+f.getFoodName()+"     "+f.getFoodType()+"         "+f.getFoodQuantity()+"             "+f.getFoodPrice());
					}
				}
				break;

			case 2:
				System.out.println("YOU SELECT SEARCH FOOD BY ID ");
				System.out.println("PLEASE ENTER ID OF FOOD");
				foodId = sc.nextInt();
				
				flag = fimpl.checkFoodId(foodId);
				
				if( flag )
				{
					flist = fimpl.showFoodById(foodId);

					if( flist == null || flist.isEmpty() == true )
					{
						ErrorMsg.emptyList();
					}
					else
					{
						System.out.println("FOOD_ID  FOOD_NAME  FOOD_TYPE  FOOD_QUANTITY  FOOD_PRICE");
						for(Food f:flist)
						{
							System.out.println(f.getFoodID()+"       "+f.getFoodName()+"     "+f.getFoodType()+"         "+f.getFoodQuantity()+"             "+f.getFoodPrice());
						}
					}
				}
				else
				{
					ErrorMsg.invalidId();
				}
				break;

			case 3:
				System.out.println("YOU SELECT SEARCH FOOD BY NAME");
				System.out.println("PLEASE ENTER NAME OF FOOD");
				foodName = sc.next();
				
				if( (foodName!=null) && (!foodName.equalsIgnoreCase("")) && (foodName.length()>0) )
				{
					flist = fimpl.showFoodByName(foodName);

					if( flist == null || flist.isEmpty() == true )
					{
						ErrorMsg.emptyList();
					}
					else
					{
						System.out.println("FOOD_ID  FOOD_NAME  FOOD_TYPE  FOOD_QUANTITY  FOOD_PRICE");
						for(Food f:flist)
						{
							System.out.println(f.getFoodID()+"       "+f.getFoodName()+"     "+f.getFoodType()+"         "+f.getFoodQuantity()+"             "+f.getFoodPrice());
						}
					}
				}
				else
				{
					ErrorMsg.invalidInput();
				}
				break;

			case 4:
				System.out.println("YOU SELECT SEARCH FOOD BY TYPE");
				System.out.println("PLEASE ENTER TYPE OF FOOD");
				foodType = sc.next();
				
				if( (foodType!=null) && (!foodType.equalsIgnoreCase("")) && (foodType.length()>0) )
				{
					flist = fimpl.showFoodByType(foodType);

					if( flist == null || flist.isEmpty() == true )
					{
						ErrorMsg.emptyList();
					}
					else
					{
						System.out.println("FOOD_ID  FOOD_NAME  FOOD_TYPE  FOOD_QUANTITY  FOOD_PRICE");
						for(Food f:flist)
						{
							System.out.println(f.getFoodID()+"       "+f.getFoodName()+"     "+f.getFoodType()+"         "+f.getFoodQuantity()+"             "+f.getFoodPrice());
						}
					}
				}
				else
				{
					ErrorMsg.invalidInput();
				}
				break;

			case 5:
				System.out.println("YOU SELECT SEARCH FOOD BY PRICE RANGE");
				System.out.println("ENTER MINIMUM PRICE");
				int min = sc.nextInt();
				System.out.println("ENTER MAXIMUM PRICE");
				int max=sc.nextInt();
				
				if( min<=0 || max <=0 )
				{
					ErrorMsg.invalidInput();
				}
				else
				{
					flist = fimpl.showFoodByPriceRange(min, max);

					if( flist == null || flist.isEmpty() == true )
					{
						ErrorMsg.emptyList();
					}
					else
					{
						System.out.println("FOOD_ID  FOOD_NAME  FOOD_TYPE  FOOD_QUANTITY  FOOD_PRICE");
						for(Food f:flist)
						{
							System.out.println(f.getFoodID()+"       "+f.getFoodName()+"     "+f.getFoodType()+"         "+f.getFoodQuantity()+"             "+f.getFoodPrice());
						}
					}
				}
				break;

			case 6:
				if (type.equalsIgnoreCase("ADMIN"))
				{
					System.out.println("YOU SELECT ADD FOOD");

					System.out.println("ENTER FOOD NAME");
					foodName = sc.next();

					System.out.println("ENTER FOOD TYPE");
					foodType = sc.next();

					System.out.println("ENTER FOOD QUANTITY");
					foodQuantity = sc.nextInt();

					System.out.println("ENTER FOOD PRICE");
					foodPrice = sc.nextDouble();

					food.setFoodName(foodName);
					food.setFoodType(foodType);
					food.setFoodQuantity(foodQuantity);
					food.setFoodPrice(foodPrice);

					flag = fimpl.addFood(food);

					if(flag)
					{
						System.out.println("FOOD ADDED SUCCESSFULLY");
					}
					else
					{
						System.out.println("FOOD NOT ADDED");
					}
				}
				else
				{
					ErrorMsg.invalidChoice();
				}
				break;

			case 7:
				if (type.equalsIgnoreCase("ADMIN"))
				{
					System.out.println("YOU SELECT UPDATE FOOD");

					System.out.println("PLEASE ENTER ID OF FOOD");
					foodId = sc.nextInt();
					
					flag = fimpl.checkFoodId(foodId);
					
					if( flag )
					{
						System.out.println("ENTER UPDATED FOOD NAME");
						foodName = sc.next();

						System.out.println("ENTER UPDATED FOOD TYPE");
						foodType = sc.next();

						System.out.println("ENTER UPDATED FOOD QUANTITY");
						foodQuantity = sc.nextInt();

						System.out.println("ENTER UPDATED FOOD PRICE");
						foodPrice = sc.nextDouble();

						food.setFoodName(foodName);
						food.setFoodType(foodType);
						food.setFoodQuantity(foodQuantity);
						food.setFoodPrice(foodPrice);
						food.setFoodID(foodId);

						flag = fimpl.updateFood(food);

						if(flag )
						{
							System.out.println("FOOD UPDATED SUCCESSFULLY");
						}
						else
						{
							System.out.println("FOOD NOT UPDATED");
						}
					}
					else
					{
						ErrorMsg.invalidId();
					}
				}
				else
				{
					ErrorMsg.invalidChoice();
				}
				break;

			case 8:
				if (type.equalsIgnoreCase("ADMIN"))
				{
					System.out.println("YOU SELECT DELETE FOOD");

					System.out.println("PLEASE ENTER ID OF FOOD");
					foodId = sc.nextInt();
					
					flag = fimpl.checkFoodId(foodId);
					
					if( flag )
					{
						food.setFoodID(foodId);

						flag = fimpl.deleteFood(food);

						if(flag )
						{
							System.out.println("FOOD DELETED SUCCESSFULLY");
						}
						else
						{
							System.out.println("FOOD NOT DELETED");
						}
					}
					else
					{
						ErrorMsg.invalidId();
					}
				}
				else
				{
					ErrorMsg.invalidChoice();
				}
				break;

			case 9:
				if( type.equalsIgnoreCase("NEWLOGIN") )
				{
					System.out.println("Please Login First to add foods");
					
					System.out.println("Press 1 to Login");
					int l = sc.nextInt();
					if( l == 1 )
					{
						LoginTest login = new LoginTest();
						login.loginMenu();
					}
					else
					{
						System.out.println("You select to continue Foods ");
					}
				}
				else
				{
					CartTest cart = new CartTest();
					cart.cartMenu(type, id, "");
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
