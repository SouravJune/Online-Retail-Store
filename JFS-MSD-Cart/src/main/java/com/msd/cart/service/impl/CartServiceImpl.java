package com.msd.cart.service.impl;

import com.msd.cart.dto.CartDto;
import com.msd.cart.dto.LineItemDto;
import com.msd.cart.entity.Cart;
import com.msd.cart.entity.LineItem;
import com.msd.cart.exceptions.CartDoesNotExistException;
import com.msd.cart.exceptions.RecordNotFoundException;
import com.msd.cart.mapper.LineItemMapper;
import com.msd.cart.repository.CartRepository;
import com.msd.cart.service.CartService;
import com.msd.cart.service.LineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
1. Call to Product Service: to fetch the product id and calculate the price of the product against the product.
2. After implementing security based on the user session we will fetch the cart we will not fetch the cart based on the cart id.
3. Change cart id to uuid.
4. when I do the order service then do this here     private boolean isPurchased;        // Flag indicating whether the cart has been purchased
 */
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private LineItemService lineItemService;

	@Override
	public void addCart(CartDto cartDto, String customerId) {

		Optional<Cart> existingCart = cartRepository.findByCustomerId(customerId);

		if (existingCart.isPresent()) {
			lineItemService.addItems(cartDto.getItems(), existingCart.get());
		}
		else {
			Cart newCart = new Cart();
			List<LineItem> listOfItems = new ArrayList<>();

			for (LineItemDto item : cartDto.getItems()) {
				LineItem newLineItem = LineItemMapper.mapToLineItem(item, new LineItem());
				newLineItem.setCart(newCart);
				listOfItems.add(newLineItem);
			}

			newCart.setItems(listOfItems);
			newCart.setCustomerId("Sourav"); // It will be replaced by customer service api call or from security context holder.
			cartRepository.save(newCart);
		}

	}

	@Override
	public CartDto searchCart(long cartId) {
		Cart getCart = cartRepository.findById(cartId)
				.orElseThrow(()-> new RecordNotFoundException(cartId));

		List<LineItemDto> listOfLineItems = new ArrayList<>();
		for (LineItem items : getCart.getItems()) {
			LineItemDto newLineItemDto = LineItemMapper.mapToLineItemDto(items, new LineItemDto());
			listOfLineItems.add(newLineItemDto);
		}

		CartDto cart = new CartDto();
		cart.setItems(listOfLineItems);

		return cart;
	}

	@Override
	public boolean updateCart(String customerId, CartDto cartDto) {
		Cart existingCart = cartRepository.findByCustomerId(customerId).orElseThrow(
				() -> new CartDoesNotExistException("Cart does not exist with this customer Id:" + customerId)
		);

		List<LineItem> existingItems = existingCart.getItems();
		List<LineItem> updatedItems = new ArrayList<>();
		List<String> removedItems = new ArrayList<>();

		for (LineItem existingItem : existingItems) {
			LineItemDto matchingDto = findMatchingDto(cartDto.getItems(), existingItem.getProductName());

			if (matchingDto != null) {
				existingItem.setQuantity(matchingDto.getQuantity());
				existingItem.setPrice(matchingDto.getPrice());
				updatedItems.add(existingItem);
			} else {
				removedItems.add(existingItem.getProductName());
			}
		}

		lineItemService.deleteItems(removedItems);

		existingCart.setItems(updatedItems);
		cartRepository.save(existingCart);

		return true;
	}

	private LineItemDto findMatchingDto(List<LineItemDto> items, String productName) {
		return items.stream()
				.filter(itemDto -> itemDto.getProductName().equals(productName))
				.findFirst()
				.orElse(null);
	}

	@Override
	public boolean deleteCart(long cartId) {
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(()-> new RecordNotFoundException(cartId));

		cartRepository.deleteById(cart.getCartId());
		return true;
	}

}
