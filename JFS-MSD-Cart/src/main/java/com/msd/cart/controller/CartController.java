package com.msd.cart.controller;

import com.msd.cart.constants.MessageAndStatusConstants;
import com.msd.cart.dto.CartDto;
import com.msd.cart.dto.ResponseDto;
import com.msd.cart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author SouravJune
 */

@Tag(
		name = "CRUD REST APIs for Cart in Online-Retail-Store",
		description = "CRUD REST APIs in Online-Retail-Store to CREATE, UPDATE, FETCH AND DELETE Cart details"
)
@RestController
@RequestMapping(
		path = "/api/v1/cart",
		produces = {MediaType.APPLICATION_JSON_VALUE}
)
@Validated
public class CartController {

	@Autowired
	private CartService cartService;

	@Operation(
			summary = "Create cart with items REST API",
			description = "REST API to create new cart & items inside Online-Retail-Store"
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
	public ResponseEntity<ResponseDto> addCart(@Valid @RequestBody CartDto cartDto, @RequestParam String customerId) {

		cartService.addCart(cartDto, customerId);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseDto(
						MessageAndStatusConstants.STATUS_201,
						MessageAndStatusConstants.MESSAGE_201
				));
	}

	@Operation(
			summary = "Get Cart-Items Details REST API",
			description = "REST API to fetch Cart-Items details"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK",
					content = @Content(
							schema = @Schema(implementation = CartDto.class)
					)
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error"
			)
	}
	)
	@GetMapping("/fetch")
	public ResponseEntity<CartDto> searchCart(@RequestParam(value = "id") long cartId){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(cartService.searchCart(cartId));
	}

	@Operation(
			summary = "Update Items Details REST API",
			description = "REST API to update Items details"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK",
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
	public ResponseEntity<ResponseDto> updateCartDetails(@RequestParam String customerId, @Valid @RequestBody CartDto cartDto) {
		boolean isUpdated = cartService.updateCart(customerId, cartDto);
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
			summary = "Delete Cart Details REST API",
			description = "REST API to delete Customer & Items details"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK",
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
	public ResponseEntity<ResponseDto> deleteCart(@RequestParam(value = "id") long cartId) {
		boolean isDeleted = cartService.deleteCart(cartId);

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
