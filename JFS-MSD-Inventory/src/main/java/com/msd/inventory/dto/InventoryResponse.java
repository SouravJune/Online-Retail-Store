package com.msd.inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Inventory", description = "Schema to hold Inventory information")
public class InventoryResponse {

    @JsonProperty(value = "product_id")
    @Schema(description = "Unique identifier for the product")
    private long productId;

    @JsonProperty(value = "product_name")
    @Schema(description = "Name of the product")
    private String productName;

    @JsonProperty(value = "product_description")
    @Schema(description = "Description of the product")
    private String description;

    @JsonProperty(value = "product_quantity")
    @Schema(description = "Quantity of the product")
    private long quantity;

    @JsonProperty(value = "product_category")
    @Schema(description = "Category of the product")
    private String productCategory;

    @JsonProperty(value = "product_supplier")
    @Schema(description = "Supplier of the product")
    private String productSupplier;

    @Schema(description = "Flag indicating whether the product is in stock")
    private boolean inStock;
}
