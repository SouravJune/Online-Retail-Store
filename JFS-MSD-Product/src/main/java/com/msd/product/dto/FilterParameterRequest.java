package com.msd.product.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterParameterRequest {

    @Size(min = 2, max = 30, message = "The length of the category name should be between 2 and 30")
    private String category;

    @Positive(message = "The price must be positive")
    private double price;
}
