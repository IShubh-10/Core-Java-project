 package com.food.driver;

import java.util.Scanner;

import com.food.test.CustomerTest;
import com.food.test.FoodTest;
import com.food.test.LoginTest;
import com.food.utility.ErrorMsg;

public class FoodHomePage 
{
	static
	{   System.out.println("\n");
	System.out.println("     F U S I O N -------- ");
	System.out.println("   -------- K I T C H E N ");
	System.out.println("\n Speciality -- \n      INDIAN ,CHINESE & SNACKS");
	System.out.println("\n");
	System.out.println("    Add:- Mahakali Andheri \n   " + " Phone :- (+91) 0889385765");
	System.out.println("____________________________________");

	}

	{
		System.out.println();
		System.out.println("     -----MENU-----    ");
		System.out.println("1- VIEW OUR FOODS");
		System.out.println("2- LOGIN TO EXPLORE");
		System.out.println("3- SIGN-UP");
		System.out.println("4- E X I T");
		System.out.println();
		System.out.println("Are you HUNGRY, then you're came to a right place.....");
	}

	public static void homeMenu()
	{
		Scanner sc = new Scanner(System.in);

		while(true) 
		{
			FoodHomePage foodhomepage = new FoodHomePage();

			System.out.println("ENTER YOUR CHOICE");
			int choice = sc.nextInt();

			switch(choice)
			{
			case 1: 
				System.out.println("YOU ENTER VIEW OUR FOODS");
				FoodTest food = new FoodTest();
				food.foodMenu("NEWLOGIN","");
				break;

			case 2: 
				System.out.println("YOU ENTER LOGIN TO EXPLORE");
				LoginTest login = new LoginTest();
				login.loginMenu();
				break;

			case 3: 
				System.out.println("YOU ENTER SIGNIN");
				CustomerTest customer = new CustomerTest();
				customer.customerMenu("NEWLOGIN","","");
				break;

			case 4: 
				ErrorMsg.exitMessage();
				break;

			default: 
				ErrorMsg.invalidChoice();
			}
		}
	}
	
	public static void main(String[] args) 
	{
		FoodHomePage.homeMenu();
	}

}
