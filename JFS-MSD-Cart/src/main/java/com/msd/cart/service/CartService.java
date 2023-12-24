package com.msd.cart.service;

import com.msd.cart.dto.CartDto;

public interface CartService {

	void addCart(CartDto cartDto, String customerId);
	CartDto searchCart(long cartId);
	boolean updateCart(String customerId,CartDto cartDto);
	boolean deleteCart(long cartId);

}
