    package com.msd.inventory.dto;

    import com.msd.inventory.enumclass.QuantityFlag;
    import com.msd.inventory.validation.annotation.ValidCategoryList;
    import com.msd.inventory.validation.annotation.ValidProductNameList;
    import com.msd.inventory.validation.annotation.ValidQuantityFlag;
    import com.msd.inventory.validation.annotation.ValidSupplierList;
    import io.swagger.v3.oas.annotations.media.Schema;
    import jakarta.annotation.Nullable;
    import jakarta.persistence.EnumType;
    import jakarta.persistence.Enumerated;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.List;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "FilterParameter", description = "Schema to hold Filter Data information")
    public class FilterParameterRequest {

        @Schema(description = "List of product", example = "[\"Phinx g502\", \"Keyfox 82\", \"SkyZ\"]")
        @ValidProductNameList
        private List<String> productNameList;

        @Schema(description = "List of category", example = "[\"Phone\", \"Bag\", \"Mouse\"]")
        @ValidCategoryList
        private List<String> categoryList;

        @Schema(description = "List of supplier", example = "[\"ZPhone\", \"EBag\", \"XBMouse\"]")
        @ValidSupplierList
        private List<String> supplierList;

        @Schema(description = "QuantityFlag to select what kind of search do you want", example = "EXACT_VALUE or IN_RANGE")
        @Enumerated(EnumType.STRING)
        @ValidQuantityFlag
        @Nullable
        private QuantityFlag quantityFlag;

        @Schema(description = "Quantity value")
        private QuantityDto quantity;

        @Schema(description = "in stock value", example = "true or false")
        @Nullable
        private String inStock;
    }
