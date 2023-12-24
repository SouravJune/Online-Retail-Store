package com.msd.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Product", description = "Schema to hold Product information")
public class ProductPaginationResponse {

    private List<ProductDto> content;
    private long pageNumber;
    private long pageSize;
    private long totalElements;
    private long totalPages;
    private boolean firstPage;
    private boolean lastPage;
}
