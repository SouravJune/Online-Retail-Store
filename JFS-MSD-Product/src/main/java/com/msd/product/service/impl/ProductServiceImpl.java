package com.msd.product.service.impl;

import java.util.Optional;

import com.msd.product.dto.FilterParameterRequest;
import com.msd.product.dto.ProductDto;
import com.msd.product.dto.ProductPaginationResponse;
import com.msd.product.exceptions.ProductAlreadyExistException;
import com.msd.product.exceptions.RecordNotFoundException;
import com.msd.product.mapper.ProductMapper;
import com.msd.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.msd.product.repository.ProductRepository;
import com.msd.product.entity.Product;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void addProduct(ProductDto productDto) {

		productRepository.findByProductName(productDto.getProductName()).orElseThrow(
				() -> new ProductAlreadyExistException("Product is already exist with the given name"));

		Product product = ProductMapper.mapToProduct(productDto, new Product());
		productRepository.save(product);
	}

	@Override
	public ProductDto searchInventoryByProductName(String productName) {
		Product product = productRepository.findByProductName(productName).orElseThrow(
				() -> new RecordNotFoundException(productName));

		return ProductMapper.mapToProductDto(product, new ProductDto());
	}

	@Override
	public ProductPaginationResponse searchProductByPagination(
			int pageNumber, int pageSize, String sortByField,
			String direction, FilterParameterRequest parameterRequest) {

		Pageable paging;
		Sort sort;

		switch (sortByField) {
			case "leastRecentlyCreated":
				paging = PageRequest.of(pageNumber-1, pageSize, Sort.by("createdAt"));
				break;
			case "mostRecentlyCreated":
				paging = PageRequest.of(pageNumber-1, pageSize, Sort.by("createdAt").descending());
				break;
			case "leastRecentlyUpdated":
				paging = PageRequest.of(pageNumber-1, pageSize, Sort.by("updatedAt"));
				break;
			case "mostRecentlyUpdated":
				paging = PageRequest.of(pageNumber-1, pageSize, Sort.by("updatedAt").descending());
				break;
			case "productName":
				sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
						Sort.by("productName").ascending() :
						Sort.by("productName").descending();
				paging = PageRequest.of(pageNumber-1, pageSize, sort);
				break;
			case "category":
				sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
						Sort.by("productCategory").ascending() :
						Sort.by("productCategory").descending();
				paging = PageRequest.of(pageNumber-1, pageSize, sort);
				break;
			case "price":
				sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
						Sort.by("price").ascending() :
						Sort.by("price").descending();
				paging = PageRequest.of(pageNumber-1, pageSize, sort);
				break;
			default:
				throw new IllegalStateException("Invalid");
		}
	}


	@Override
	public Product updateProduct(long productId, Product product) {
		Optional<Product> in = productRepository.findById(productId);
		if(in.isPresent()) {
			return productRepository.save(product);
			}
	
		throw new RecordNotFoundException(productId);
	}

	@Override
	public boolean deleteProduct(long productId) {
		Optional<Product> pro = productRepository.findById(productId);
		if(pro.isPresent()) {
			productRepository.deleteById(productId);
			return true;
		}
		throw new RecordNotFoundException(productId);
	}
	
}


