package com.food.pojo;

public class Food {
	
	private int foodID;
	private String foodName;
	private String foodType;
	private int foodQuantity;
	private double foodPrice;
	
	public Food()
	{
		//just to create object without any parameter
	}
	
	public Food(int foodID, String foodName, String foodType, int foodQuantity, double foodPrice) {
		super();
		this.foodID = foodID;
		this.foodName = foodName;
		this.foodType = foodType;
		this.foodQuantity = foodQuantity;
		this.foodPrice = foodPrice;
	}


	public int getFoodID() {
		return foodID;
	}


	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}


	public String getFoodName() {
		return foodName;
	}


	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}


	public String getFoodType() {
		return foodType;
	}


	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}


	public int getFoodQuantity() {
		return foodQuantity;
	}


	public void setFoodQuantity(int foodQuantity) {
		this.foodQuantity = foodQuantity;
	}


	public double getFoodPrice() {
		return foodPrice;
	}


	public void setFoodPrice(double foodPrice) {
		this.foodPrice = foodPrice;
	}


	@Override
	public String toString() {
		return "Food [foodID=" + foodID + ", foodName=" + foodName + ", foodType=" + foodType + ", foodQuantity="
				+ foodQuantity + ", foodPrice=" + foodPrice + "]";
	}
	
	

}
