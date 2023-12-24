package com.msd.cart.service;

import com.msd.cart.dto.LineItemDto;
import com.msd.cart.entity.Cart;

import java.util.List;

public interface LineItemService {
    void addItems(List<LineItemDto> lineItemDto, Cart cartId);
    boolean deleteItems(List<String> productName);
}
