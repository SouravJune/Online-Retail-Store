package com.msd.inventory.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Quantity", description = "Schema to hold Quantity information")
public class QuantityDto {

    @Positive(message = "Minimum quantity must be a positive value")
    @Schema(description = "lower range")
    private long minQuantity;

    @Positive(message = "Maximum quantity must be a positive value")
    @Schema(description = "higher range")
    private long maxQuantity;

    @Positive(message = "Exact quantity must be a positive value")
    @Schema(description = "search with exact quantity")
    private long exactQuantity;
}
