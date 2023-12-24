package com.msd.inventory.mapper;

import com.msd.inventory.dto.InventoryRequest;
import com.msd.inventory.dto.InventoryResponse;
import com.msd.inventory.entity.Inventory;

public class InventoryMapper {

    public static Inventory mapToInventory(InventoryRequest inventoryRequest, Inventory inventory) {
        inventory.setProductName(inventoryRequest.getProductName());
        inventory.setQuantity(inventoryRequest.getQuantity());
        inventory.setProductSupplier(inventoryRequest.getProductSupplier());
        inventory.setInStock(true);
        return inventory;
    }

    public static InventoryResponse mapToInventoryResponse(Inventory inventory, InventoryResponse inventoryResponse) {
        inventoryResponse.setProductId(inventory.getProductId());
        inventoryResponse.setProductName(inventory.getProductName());
        inventoryResponse.setDescription(inventory.getDescription());
        inventoryResponse.setQuantity(inventory.getQuantity());
        inventoryResponse.setProductCategory(inventory.getProductCategory());
        inventoryResponse.setProductSupplier(inventory.getProductSupplier());
        inventoryResponse.setInStock(inventory.isInStock());
        return inventoryResponse;
    }
}
