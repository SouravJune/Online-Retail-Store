package com.msd.cart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Cart", description = "Schema to hold Cart and Items information")
public class CartDto {

    @Valid
    private List<LineItemDto> items;
}
