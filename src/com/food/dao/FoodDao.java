package com.food.dao;

import java.util.List;

import com.food.pojo.Food;

public interface FoodDao 
{
	
	boolean addFood(Food food);
	boolean updateFood(Food food);
	boolean deleteFood(Food food);
	
	List<Food> showAllFoods();
	List<Food> showFoodById(int foodId);
	List<Food> showFoodByName(String foodName);
	List<Food> showFoodByType(String foodType);
	List<Food> showFoodByPriceRange(int min,int max);
	
	boolean checkFoodId(int foodId);
	Food showFoodDetailsById(int foodId);
	
	boolean updateFoodDetails(int foodId, int foodQuantity);

}
