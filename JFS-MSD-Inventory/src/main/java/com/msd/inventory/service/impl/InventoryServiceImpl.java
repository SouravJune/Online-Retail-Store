package com.msd.inventory.service.impl;

import com.msd.inventory.dto.FilterParameterRequest;
import com.msd.inventory.dto.InventoryPaginationResponse;
import com.msd.inventory.dto.InventoryRequest;
import com.msd.inventory.dto.InventoryResponse;
import com.msd.inventory.entity.Inventory;
import com.msd.inventory.enumclass.QuantityFlag;
import com.msd.inventory.exceptions.RecordNotFoundException;
import com.msd.inventory.mapper.InventoryMapper;
import com.msd.inventory.repository.InventoryRepository;
import com.msd.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.msd.inventory.constants.ServiceConstants.*;

@Service
public class InventoryServiceImpl implements InventoryService {

	/**
	 * 1. For update and delete changes @RequestParam from name to id
	 * 2. If you are deleting from here you need to delete from product service as well
	 * 3. in addInventory call product ms to get id, category, description
	 * 4. in update if product name is updating then call to product ms to update that name field
	 */

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private InventoryPaginationResponse inventoryPaginationResponse;
	
	@Override
	public void addInventory(InventoryRequest inventoryRequest) {
		Inventory inventory = InventoryMapper.mapToInventory(inventoryRequest, new Inventory());
		inventoryRepository.save(inventory);
	}

	@Override
	public InventoryResponse searchInventoryByProductName(String productName) {
		Inventory inventory = inventoryRepository.findByProductName(productName).orElseThrow(
				() -> new RecordNotFoundException(productName));

		return InventoryMapper.mapToInventoryResponse(inventory, new InventoryResponse());
	}

	@Override
	public List<String> getAllProductName() {
		return inventoryRepository.getAllProductName().orElseThrow(RecordNotFoundException::new);
	}

	@Override
	public List<String> getAllCategory() {
		return inventoryRepository.getAllCategory().orElseThrow(RecordNotFoundException::new);
	}

	@Override
	public List<String> getAllSupplier() {
		return inventoryRepository.getAllSupplier().orElseThrow(RecordNotFoundException::new);
	}

	@Override
	public InventoryPaginationResponse searchInventoryByPagination(
			int pageNumber, int pageSize, String sortByField,
			String direction, FilterParameterRequest parameterRequest) {

		Pageable paging;
		Sort sort;

		switch (sortByField) {
			case "leastRecentlyCreated":
				paging = PageRequest.of(pageNumber - 1, pageSize, Sort.by(SORT_FIELD_CREATED_AT));
				break;
			case "mostRecentlyCreated":
				paging = PageRequest.of(pageNumber - 1, pageSize, Sort.by(SORT_FIELD_CREATED_AT).descending());
				break;
			case "leastRecentlyUpdated":
				paging = PageRequest.of(pageNumber - 1, pageSize, Sort.by(SORT_FIELD_UPDATED_AT));
				break;
			case "mostRecentlyUpdated":
				paging = PageRequest.of(pageNumber - 1, pageSize, Sort.by(SORT_FIELD_UPDATED_AT).descending());
				break;
			case "productName":
				sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
						Sort.by(SORT_FIELD_PRODUCT_NAME).ascending() :
						Sort.by(SORT_FIELD_PRODUCT_NAME).descending();
				paging = PageRequest.of(pageNumber - 1, pageSize, sort);
				break;
			case "category":
				sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
						Sort.by(SORT_FIELD_PRODUCT_CATEGORY).ascending() :
						Sort.by(SORT_FIELD_PRODUCT_CATEGORY).descending();
				paging = PageRequest.of(pageNumber - 1, pageSize, sort);
				break;
			case "quantity":
				sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
						Sort.by(SORT_FIELD_QUANTITY).ascending() :
						Sort.by(SORT_FIELD_QUANTITY).descending();
				paging = PageRequest.of(pageNumber - 1, pageSize, sort);
				break;
			case "supplier":
				sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
						Sort.by(SORT_FIELD_PRODUCT_SUPPLIER).ascending() :
						Sort.by(SORT_FIELD_PRODUCT_SUPPLIER).descending();
				paging = PageRequest.of(pageNumber - 1, pageSize, sort);
				break;
			case "available":
				sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
						Sort.by(SORT_FIELD_IN_STOCK).ascending() :
						Sort.by(SORT_FIELD_IN_STOCK).descending();
				paging = PageRequest.of(pageNumber - 1, pageSize, sort);
				break;
			default:
				throw new IllegalStateException(INVALID);
		}

		Page<Inventory> pagedResult;

		if (!parameterRequest.getProductNameList().isEmpty()
				&& !parameterRequest.getCategoryList().isEmpty()) {
			pagedResult = inventoryRepository.searchByProductNameOrCategoryList(
					paging, parameterRequest.getProductNameList(), parameterRequest.getCategoryList());
		}
		else if (!parameterRequest.getProductNameList().isEmpty()
				&& !parameterRequest.getSupplierList().isEmpty()) {
			pagedResult = inventoryRepository.searchByProductNameOrSupplierList(
					paging, parameterRequest.getProductNameList(), parameterRequest.getSupplierList());
		}
		else if (!parameterRequest.getCategoryList().isEmpty()
				&& !parameterRequest.getSupplierList().isEmpty()) {
			pagedResult = inventoryRepository.searchByProductCategoryOrSupplierList(
					paging, parameterRequest.getCategoryList(), parameterRequest.getSupplierList());
		}
		else if (!parameterRequest.getProductNameList().isEmpty()
				&& parameterRequest.getInStock() != null) {
			pagedResult = inventoryRepository.searchByProductNameAndInStock(
					paging, parameterRequest.getProductNameList(), Boolean.parseBoolean(parameterRequest.getInStock()));
		}
		else if (!parameterRequest.getCategoryList().isEmpty()
				&& parameterRequest.getInStock() != null) {
			pagedResult = inventoryRepository.searchByProductCategoryAndInStock(
					paging, parameterRequest.getCategoryList(), Boolean.parseBoolean(parameterRequest.getInStock()));
		}
		else if (!parameterRequest.getProductNameList().isEmpty()) {
			pagedResult = inventoryRepository.searchByProductNameList(
					paging, parameterRequest.getProductNameList());
		}
		else if (!parameterRequest.getCategoryList().isEmpty()) {
			pagedResult = inventoryRepository.searchByProductCategoryList(
					paging, parameterRequest.getCategoryList());
		}
		else if (!parameterRequest.getSupplierList().isEmpty()) {
			pagedResult = inventoryRepository.searchByProductSupplierList(
					paging, parameterRequest.getSupplierList());
		}
		else if (QuantityFlag.EXACT_VALUE.equals(parameterRequest.getQuantityFlag())) {
			pagedResult = inventoryRepository.searchByExactProductQuantity(
					paging, parameterRequest.getQuantity().getExactQuantity());
		}
		else if (QuantityFlag.IN_RANGE.equals(parameterRequest.getQuantityFlag())) {
			pagedResult = inventoryRepository.searchByExactProductQuantity(
					paging, parameterRequest.getQuantity().getMinQuantity(),
					parameterRequest.getQuantity().getMaxQuantity());
		}
		else if (parameterRequest.getInStock() != null) {
			pagedResult = inventoryRepository.searchByInStock(
					paging, Boolean.parseBoolean(parameterRequest.getInStock()));
		}
		else {
			pagedResult = inventoryRepository.searchInventory(paging);
		}

		return getInventoryPaginationResponse(pagedResult);

    }

	private InventoryPaginationResponse getInventoryPaginationResponse(Page<Inventory> pagedResult) {

		List<Inventory> inventoryList = pagedResult.getContent();

		if(inventoryList.isEmpty()) {
			throw new RecordNotFoundException("No Record Found");
		}

		List<InventoryResponse> inventoryResponseList =  inventoryList.stream()
				.map(inventory -> InventoryMapper
						.mapToInventoryResponse(inventory, new InventoryResponse()))
				.collect(Collectors.toList());

		inventoryPaginationResponse.setContent(inventoryResponseList);
		inventoryPaginationResponse.setPageNumber(pagedResult.getNumber());
		inventoryPaginationResponse.setPageSize(pagedResult.getSize());
		inventoryPaginationResponse.setTotalElements(pagedResult.getTotalElements());
		inventoryPaginationResponse.setTotalPages(pagedResult.getTotalPages());
		inventoryPaginationResponse.setFirstPage(pagedResult.isFirst());
		inventoryPaginationResponse.setLastPage(pagedResult.isLast());

		return inventoryPaginationResponse;
	}

	@Override
	public boolean updateInventory(String productName, InventoryRequest inventoryRequest) {
		Inventory updateInventory = inventoryRepository.findByProductName(productName).orElseThrow(
				() -> new RecordNotFoundException(productName));

		updateInventory.setProductName(inventoryRequest.getProductName());
		updateInventory.setQuantity(inventoryRequest.getQuantity());
		updateInventory.setProductSupplier(inventoryRequest.getProductSupplier());

		inventoryRepository.save(updateInventory);

		return true;
	}

	@Override
	public boolean deleteInventory(String productName) {
		Inventory deleteInventory = inventoryRepository.findByProductName(productName)
				.orElseThrow(() -> new RecordNotFoundException(productName));

		inventoryRepository.delete(deleteInventory);

		return true;
	}


}


