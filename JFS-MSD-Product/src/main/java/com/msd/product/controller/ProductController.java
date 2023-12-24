package com.msd.product.controller;

import com.msd.product.constants.MessageAndStatusConstants;
import com.msd.product.dto.FilterParameterRequest;
import com.msd.product.dto.ProductDto;
import com.msd.product.dto.ProductPaginationResponse;
import com.msd.product.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.msd.product.entity.Product;
import com.msd.product.service.ProductService;

/**
 * @author SouravJune
 */

@Tag(
		name = "CRUD REST APIs for Product in Online-Retail-Store",
		description = "CRUD REST APIs in Online-Retail-Store to CREATE, UPDATE, FETCH AND DELETE Product details"
)
@RestController
@RequestMapping(
		path = "/api/product",
		produces = {MediaType.APPLICATION_JSON_VALUE}
)
@Validated
public class ProductController {

	@Autowired
    private ProductService productService;

	@Operation(
			summary = "Create product with items REST API",
			description = "REST API to create new produ t inside Online-Retail-Store"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "201",
					description = "HTTP Status CREATED",
					content = @Content(
							schema = @Schema(implementation = ResponseDto.class)
					)
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error"
			)
	}
	)
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> addNewProduct(@RequestBody ProductDto productDto) {

		productService.addProduct(productDto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseDto(
						MessageAndStatusConstants.STATUS_201,
						MessageAndStatusConstants.MESSAGE_201
				));
	}

	@GetMapping("/get-by-product-name")
	public ResponseEntity<ProductDto> searchProductByProductName(
			@RequestParam
			@NotEmpty(message = "The product name cannot be null or empty")
			@Size(min = 2, max = 50, message = "The length of the product name should be between 2 and 50")
			@Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Only letters (a-z, A-Z) and digits (0-9) are allowed.")
			String productName){

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(productService.searchInventoryByProductName(productName));
	}

	@GetMapping("/inventory-pagination")
	public ResponseEntity<ProductPaginationResponse> searchInventoryPagination(
			@RequestParam(defaultValue = "1")
			@NotEmpty(message = "The page number cannot be null or empty")
			@Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Should be a positive integer")
			@Min(value = 1, message = "Should be greater than or equal to 1")
			int pageNumber,

			@RequestParam(defaultValue = "1")
			@NotEmpty(message = "The page number cannot be null or empty")
			@Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Should be a positive integer")
			@Min(value = 1, message = "Should be greater than or equal to 1")
			int pageSize,

			@NotEmpty(message = "The sortBy field cannot be null or empty")
			@Size(min = 2, max = 50, message = "The length of the sortBy field should be between 2 and 50")
			@RequestParam String sortByField,

			@Pattern(regexp = "^(ASC|DESC)$", message = "Invalid sorting direction. Must be 'ASC' or 'DESC'")
			@RequestParam(defaultValue = "ASC", required = false) String direction,

			@Valid
			@RequestBody FilterParameterRequest parameterRequest){

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(productService.searchProductByPagination(pageNumber, pageSize, sortByField, direction, parameterRequest));
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") long productId, @RequestBody Product product) {
		Product updateProduct = productService.updateProduct(productId, product);
		 return new ResponseEntity<Product>(updateProduct,HttpStatus.CREATED);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<Boolean> deleteProduct(@PathVariable(value = "id") long productId) {
		boolean delProduct = productService.deleteProduct(productId);
		return new ResponseEntity<Boolean>(delProduct,HttpStatus.ACCEPTED);
	}

}
