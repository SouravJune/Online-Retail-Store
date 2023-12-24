package com.msd.inventory.service;

import com.msd.inventory.dto.FilterParameterRequest;
import com.msd.inventory.dto.InventoryPaginationResponse;
import com.msd.inventory.dto.InventoryRequest;
import com.msd.inventory.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {

	void addInventory (InventoryRequest inventoryRequest);
	InventoryResponse searchInventoryByProductName(String productName);
	List<String> getAllProductName();
	List<String> getAllCategory();
	List<String> getAllSupplier();
	InventoryPaginationResponse searchInventoryByPagination(
			int pageNumber, int pageSize, String sortByField,
			String direction, FilterParameterRequest parameterRequest);
	boolean updateInventory (String productName, InventoryRequest inventoryRequest);
	boolean deleteInventory (String productName);

}
