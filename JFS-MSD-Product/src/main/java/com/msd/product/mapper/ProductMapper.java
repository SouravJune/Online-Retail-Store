package com.msd.product.mapper;

import com.msd.product.dto.ProductDto;
import com.msd.product.entity.Product;

public class ProductMapper {

    public static Product mapToProduct(ProductDto productDto, Product product) {
        product.setProductName(productDto.getProductName());
        product.setProductDescription(productDto.getProductDescription());
        product.setProductCategory(productDto.getProductCategory());
        product.setPrice(productDto.getPrice());
        return product;
    }

    public static ProductDto mapToProductDto(Product product, ProductDto productDto) {
        productDto.setProductName(product.getProductName());
        productDto.setProductDescription(product.getProductDescription());
        productDto.setProductCategory(product.getProductCategory());
        productDto.setPrice(product.getPrice());
        return productDto;
    }
}
