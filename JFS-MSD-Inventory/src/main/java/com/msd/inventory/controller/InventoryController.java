package com.msd.inventory.controller;

import com.msd.inventory.constants.MessageAndStatusConstants;
import com.msd.inventory.dto.*;
import com.msd.inventory.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
		path = "/api/v1/inventory",
		produces = {MediaType.APPLICATION_JSON_VALUE}
)
@Validated
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@Operation(
			summary = "Create inventory REST API",
			description = "REST API to create new inventory inside Online-Retail-Store"
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
	public ResponseEntity<ResponseDto> addNewInventory(
			@Valid  @RequestBody InventoryRequest inventoryRequest) {

		inventoryService.addInventory(inventoryRequest);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseDto(
						MessageAndStatusConstants.STATUS_201,
						MessageAndStatusConstants.MESSAGE_201
				));
	}

	@Operation(
			summary = "Get Product by Product Details REST API",
			description = "REST API to fetch inventory product details"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK",
					content = @Content(
							schema = @Schema(implementation = InventoryResponse.class)
					)
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error"
			)
	}
	)
	@GetMapping("/get-inventory-by-product-name")
	public ResponseEntity<InventoryResponse> searchInventoryByProductName(
			@RequestParam
			@NotEmpty(message = "The product name cannot be null or empty")
			@Size(min = 2, max = 50, message = "The length of the product name should be between 2 and 50")
			@Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Only letters (a-z, A-Z) and digits (0-9) are allowed.")
			String productName){

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(inventoryService.searchInventoryByProductName(productName));
	}

	@Operation(
			summary = "Get All Product Details REST API",
			description = "REST API to fetch all product details"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK"
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error"
			)
	}
	)
	@GetMapping("/get-product-name")
	public ResponseEntity<List<String>> getAllProductName(){

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(inventoryService.getAllProductName());
	}

	@Operation(
			summary = "Get All Category Details REST API",
			description = "REST API to fetch all category details"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK"
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error"
			)
	}
	)
	@GetMapping("/get-category")
	public ResponseEntity<List<String>> getAllCategory(){

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(inventoryService.getAllCategory());
	}

	@Operation(
			summary = "Get All Supplier Details REST API",
			description = "REST API to fetch all supplier details"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK"
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error"
			)
	}
	)
	@GetMapping("/get-supplier")
	public ResponseEntity<List<String>> getAllSupplier(){

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(inventoryService.getAllSupplier());
	}

	@Operation(
			summary = "Get Filtered Data REST API",
			description = "REST API to fetch details on filtered data"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK",
					content = @Content(
							schema = @Schema(implementation = InventoryPaginationResponse.class)
					)
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error"
			)
	}
	)
	@GetMapping("/inventory-pagination")
	public ResponseEntity<InventoryPaginationResponse> searchInventoryPagination(
			@RequestParam(defaultValue = "1")
			@NotNull(message = "The page number cannot be null or empty")
			@Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Should be a positive integer")
			@Min(value = 1, message = "Should be greater than or equal to 1")
			int pageNumber,

			@RequestParam(defaultValue = "1")
			@NotNull(message = "The page number cannot be null or empty")
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
				.body(inventoryService.searchInventoryByPagination(pageNumber, pageSize, sortByField, direction, parameterRequest));
	}

	@Operation(
			summary = "Update inventory REST API",
			description = "REST API to update inventory inside Online-Retail-Store"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status CREATED",
					content = @Content(
							schema = @Schema(implementation = ResponseDto.class)
					)
			),
			@ApiResponse(
					responseCode = "417",
					description = "Expectation Failed"
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error"
			)
	}
	)
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateInventory(
			@RequestParam String productName,
			@Valid @RequestBody InventoryRequest inventoryRequest) {

		boolean isUpdated = inventoryService.updateInventory(productName, inventoryRequest);
		if(isUpdated) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseDto(
							MessageAndStatusConstants.STATUS_200,
							MessageAndStatusConstants.MESSAGE_200
					));
		}else{
			return ResponseEntity
					.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(
							MessageAndStatusConstants.STATUS_417,
							MessageAndStatusConstants.MESSAGE_417_UPDATE
					));
		}
	}

	@Operation(
			summary = "Delete inventory REST API",
			description = "REST API to delete inventory details"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status CREATED",
					content = @Content(
							schema = @Schema(implementation = ResponseDto.class)
					)
			),
			@ApiResponse(
					responseCode = "417",
					description = "Expectation Failed"
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error"
			)
	}
	)
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteInventory(
			@RequestParam String productName) {

		 boolean isDeleted = inventoryService.deleteInventory(productName);

		if(isDeleted) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseDto(
							MessageAndStatusConstants.STATUS_200,
							MessageAndStatusConstants.MESSAGE_200
					));
		}else{
			return ResponseEntity
					.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(
							MessageAndStatusConstants.STATUS_417,
							MessageAndStatusConstants.MESSAGE_417_DELETE
					));
		}
	}
}
