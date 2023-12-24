package com.msd.cart.mapper;

import com.msd.cart.dto.LineItemDto;
import com.msd.cart.entity.LineItem;

public class LineItemMapper {

    public static LineItem mapToLineItem(LineItemDto lineItemDto, LineItem lineItem) {
        lineItem.setProductName(lineItemDto.getProductName());
        lineItem.setQuantity(lineItemDto.getQuantity());
        lineItem.setPrice(lineItemDto.getPrice());
        return lineItem;
    }

    public static LineItemDto mapToLineItemDto(LineItem lineItem, LineItemDto lineItemDto) {
        lineItemDto.setProductName(lineItem.getProductName());
        lineItemDto.setQuantity(lineItem.getQuantity());
        lineItemDto.setPrice(lineItem.getPrice());
        return lineItemDto;
    }
}
