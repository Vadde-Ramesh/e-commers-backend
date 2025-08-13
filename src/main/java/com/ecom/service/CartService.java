package com.ecom.service;

import java.util.List;

import com.ecom.entity.Cart;

public interface CartService {

	Cart addCart(Integer userId, List<Integer> productIds);

	Cart getCart(Long cartId);

	List<Cart> getUserCarts(Long userId);

	void deleteCart(Long cartId);

}
