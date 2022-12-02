package com.food.utility;

public class ErrorMsg {
	
	public static void invalidChoice()
	{
		System.out.println("Invalid Choice !!!!");
		System.out.println("Please select number from menu");
	}
	
	public static void invalidId()
	{
		System.out.println("Invalid ID !!!");
		System.out.println("Please check your ID & try again");
	}
	
	public static void invalidInput()
	{
		System.out.println("Invalid Input !!!");
		System.out.println("Please check valid input & try again");
	}
	
	public static void invalidLogin()
	{
		System.out.println("Invalid Username or Password!");
		System.out.println("Please Try Again!!");
	}
	
	public static void maxAttemptsLogin()
	{
		System.out.println("Maximum Attempts Reached");
		System.out.println("Session Terminated !");
	}
	
	public static void notAvailable()
	{
		System.out.println("We are sorry for inconvieneince, but we don't find anything you are looking for");
		System.out.println("stay tuned!");
	}
	
	public static void emptyList()
	{
		System.out.println("Wooo! Your requested List is Empty");
	}
	
	public static void outOfStock()
	{
		System.out.println("Sorry!! but you've select some product which is Out of stock currently");
		System.out.println("stay tuned!");
	}
	
	public static void error()
	{
		System.out.println("oops!! something went wrong");
		System.out.println("Please try again later....or contact customer service!");
	}
	
	public static void connectionError()
	{
		System.out.println("Not able to connect with database at the moment");
		System.out.println("Please try again later....or contact customer service!");
	}
	
	public static void exitMessage()
	{
		System.out.println("BY HAVE A NICE DAY!!");
		System.exit(0);
	}

}
