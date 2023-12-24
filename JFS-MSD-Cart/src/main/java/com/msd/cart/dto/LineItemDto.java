package com.msd.cart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "LineItem", description = "Schema to hold Items information")
public class LineItemDto {

    @Schema(description = "Name of the product", example = "Phinx g502")
    @NotEmpty(message = "The product name cannot be null or empty")
    @Size(min = 2, max = 50, message = "The length of the product name should be between 2 and 50")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Only letters (a-z, A-Z) and digits (0-9) are allowed.")
    private String productName;

    @Schema(description = "Quantity of the product", example = "5")
    @NotNull(message = "The quantity name cannot be null or empty")
    @Positive(message = "The quantity must be a positive number")
    private long quantity;

    @Schema(description = "Price of the product", example = "5000.49")
    @NotNull(message = "The price name cannot be null or empty")
    @Positive(message = "The price must be a positive number")
    private double price;
}
