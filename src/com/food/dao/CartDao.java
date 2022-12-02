package com.food.dao;

import java.util.List;

import com.food.pojo.Cart;

public interface CartDao {
	
	boolean addToCart(Cart cart);
	boolean deleteFromCart(Cart cart);
	boolean updateQuantity(Cart cart);
	
	boolean clearCart(String custPhone, String update);
	List<Cart> showMyCart(String custPhone);
	
	int getFoodIdByCartId(int cartId);
	
	boolean checkCartId(int cartId);
	boolean checkFoodAvaibility(int foodId, int quantity);
}
