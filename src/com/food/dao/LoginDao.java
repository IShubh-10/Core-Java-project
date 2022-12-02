package com.food.dao;

public interface LoginDao 
{
	String checkUser(String custPhone, String custPass , String userType);
}
