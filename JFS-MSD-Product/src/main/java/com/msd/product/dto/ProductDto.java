package com.msd.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Product", description = "Schema to hold Product information")
public class ProductDto {

    @NotEmpty(message = "The product name cannot be null or empty")
    @Size(min = 2, max = 50, message = "The length of the product name should be between 2 and 50")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Only letters (a-z, A-Z) and digits (0-9) are allowed.")
    @JsonProperty(value = "product_name")
    private String productName;

    @NotEmpty(message = "The product description cannot be null or empty")
    @Size(min = 5, max = 500, message = "The length of the product description should be between 5 and 500")
    @JsonProperty(value = "product_description")
    private String productDescription;

    @NotEmpty(message = "The product description cannot be null or empty")
    @Size(min = 5, max = 100, message = "The length of the product description should be between 5 and 100")
    @JsonProperty(value = "product_category")
    private String productCategory;

    @NotNull(message = "The product price cannot be null or empty")
    @Positive(message = "The product price must be a positive number")
    @JsonProperty(value = "product_price")
    private double price;
}
