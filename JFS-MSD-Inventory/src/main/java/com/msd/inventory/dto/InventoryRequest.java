package com.msd.inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Inventory", description = "Schema to hold Inventory information")
public class InventoryRequest {

    @Schema(description = "Name of the product", example = "Phinx g502")
    @NotEmpty(message = "The product name cannot be null or empty")
    @Size(min = 2, max = 50, message = "The length of the product name should be between 2 and 50")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Only letters (a-z, A-Z) and digits (0-9) are allowed.")
    @JsonProperty(value = "product_name")
    private String productName;

    @Schema(description = "Quantity of the product", example = "5")
    @NotNull(message = "The quantity name cannot be null or empty")
    @Positive(message = "The quantity must be a positive number")
    private long quantity;

    @Schema(description = "Name of the product supplier", example = "ZPhone LTD")
    @NotEmpty(message = "The product supplier cannot be null or empty")
    @Size(min = 2, max = 30, message = "The length of the product supplier should be between 2 and 30")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only letters (a-z, A-Z) are allowed.")
    @JsonProperty(value = "product_supplier")
    private String productSupplier;

}
