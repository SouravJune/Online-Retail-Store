package com.msd.inventory.dto;

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
@Schema(name = "Inventory", description = "Schema to hold Inventory information")
public class InventoryPaginationResponse {

    @Schema(description = "Hold Inventory details")
    private List<InventoryResponse> content;

    @Schema(description = "Page number")
    private long pageNumber;

    @Schema(description = "Number of items per page")
    private long pageSize;

    @Schema(description = "Total number of items across all pages")
    private long totalElements;

    @Schema(description = "Total number of pages")
    private long totalPages;

    @Schema(description = "Flag indicating whether it is the first page")
    private boolean firstPage;

    @Schema(description = "Flag indicating whether it is the last page")
    private boolean lastPage;
}
