package com.food.pojo;

public class Cart {
	
	private int cartId;
	private String cartCustPhone;
	private int cartFoodId;
	private int cartFoodQuantity;
	private double cartFoodPrice;
	
	private Food food;
	
	public Cart()
	{
		//just to create object without any parameter
	}

	public Cart(int cartId, String cartCustPhone, int cartFoodId, int cartFoodQuantity, double cartFoodPrice,
			Food food) {
		super();
		this.cartId = cartId;
		this.cartCustPhone = cartCustPhone;
		this.cartFoodId = cartFoodId;
		this.cartFoodQuantity = cartFoodQuantity;
		this.cartFoodPrice = cartFoodPrice;
		this.food = food;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getCartCustPhone() {
		return cartCustPhone;
	}

	public void setCartCustPhone(String cartCustPhone) {
		this.cartCustPhone = cartCustPhone;
	}

	public int getCartFoodId() {
		return cartFoodId;
	}

	public void setCartFoodId(int cartFoodId) {
		this.cartFoodId = cartFoodId;
	}

	public int getCartFoodQuantity() {
		return cartFoodQuantity;
	}

	public void setCartFoodQuantity(int cartFoodQuantity) {
		this.cartFoodQuantity = cartFoodQuantity;
	}

	public double getCartFoodPrice() {
		return cartFoodPrice;
	}

	public void setCartFoodPrice(double cartFoodPrice) {
		this.cartFoodPrice = cartFoodPrice;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", cartCustPhone=" + cartCustPhone + ", cartFoodId=" + cartFoodId
				+ ", cartFoodQuantity=" + cartFoodQuantity + ", cartFoodPrice=" + cartFoodPrice + ", food=" + food
				+ "]";
	}

	
	
	
}
