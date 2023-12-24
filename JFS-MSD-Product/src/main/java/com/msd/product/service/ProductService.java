package com.msd.product.service;

import com.msd.product.dto.FilterParameterRequest;
import com.msd.product.dto.ProductDto;
import com.msd.product.dto.ProductPaginationResponse;
import com.msd.product.entity.Product;

public interface ProductService {

	void addProduct(ProductDto productDto);
	ProductDto searchInventoryByProductName(String productName);
	ProductPaginationResponse searchProductByPagination(
			int pageNumber, int pageSize, String sortByField,
			String direction, FilterParameterRequest parameterRequest);
	Product updateProduct(long productId, Product product);
	boolean deleteProduct(long productId);
	
}
