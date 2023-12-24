package com.msd.cart.service.impl;

import com.msd.cart.dto.LineItemDto;
import com.msd.cart.entity.Cart;
import com.msd.cart.entity.LineItem;
import com.msd.cart.mapper.LineItemMapper;
import com.msd.cart.repository.LineItemRepository;
import com.msd.cart.service.LineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LineItemServiceImpl implements LineItemService {

    @Autowired
    private LineItemRepository lineItemRepository;

    @Override
    public void addItems(List<LineItemDto> lineItemDto, Cart existingCart) {
        List<LineItem> listOfItems = new ArrayList<>();

        for (LineItemDto item : lineItemDto) {
            LineItem newLineItem = LineItemMapper.mapToLineItem(item, new LineItem());
            newLineItem.setCart(existingCart);
            listOfItems.add(newLineItem);
        }

        lineItemRepository.saveAll(listOfItems);
    }

    @Override
    public boolean deleteItems(List<String> productName) {
        lineItemRepository.deleteAllByProductNames(productName);
        return true;
    }
}
