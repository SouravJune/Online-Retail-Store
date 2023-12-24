package com.msd.inventory.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.msd.inventory.entity.Inventory;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{

    Optional<Inventory> findByProductName(String productName);

    @Query("SELECT i.productName FROM Inventory i ORDER BY i.productName ASC")
    Optional<List<String>> getAllProductName();

    @Query("SELECT i.productCategory FROM Inventory i ORDER BY i.productCategory ASC")
    Optional<List<String>> getAllCategory();

    @Query("SELECT i.productSupplier FROM Inventory i ORDER BY i.productSupplier ASC")
    Optional<List<String>> getAllSupplier();

    @Query("SELECT i FROM Inventory i WHERE i.productName IN ?1 OR i.productCategory IN ?2")
    Page<Inventory> searchByProductNameOrCategoryList(Pageable paging, List<String> productNameList, List<String> productCategoryList);

    @Query("SELECT i FROM Inventory i WHERE i.productName IN ?1 OR i.productSupplier IN ?2")
    Page<Inventory> searchByProductNameOrSupplierList(Pageable paging, List<String> productNameList, List<String> productSupplierList);

    @Query("SELECT i FROM Inventory i WHERE i.productCategory IN ?1 OR i.productSupplier IN ?2")
    Page<Inventory> searchByProductCategoryOrSupplierList(Pageable paging, List<String> productCategoryList, List<String> productSupplierList);

    @Query("SELECT i FROM Inventory i WHERE i.productName IN ?1 AND i.inStock=?2")
    Page<Inventory> searchByProductNameAndInStock(Pageable paging, List<String> productNameList, Boolean inStock);

    @Query("SELECT i FROM Inventory i WHERE i.productCategory IN ?1 AND i.inStock=?2")
    Page<Inventory> searchByProductCategoryAndInStock(Pageable paging, List<String> categoryList, Boolean inStock);

    @Query("SELECT i FROM Inventory i WHERE i.productName IN ?1")
    Page<Inventory> searchByProductNameList(Pageable paging, List<String> productName);

    @Query("SELECT i FROM Inventory i WHERE i.productCategory IN ?1")
    Page<Inventory> searchByProductCategoryList(Pageable paging, List<String> productCategory);

    @Query("SELECT i FROM Inventory i WHERE i.productSupplier IN ?1")
    Page<Inventory> searchByProductSupplierList(Pageable paging, List<String> productSupplier);

    @Query("SELECT i FROM Inventory i WHERE i.quantity=?1")
    Page<Inventory> searchByExactProductQuantity(Pageable paging, long quantity);

    @Query("SELECT i FROM Inventory i WHERE i.quantity BETWEEN ?1 AND ?2")
    Page<Inventory> searchByExactProductQuantity(Pageable paging, long minQuantity, long maxQuantity);

    @Query("SELECT i FROM Inventory i WHERE i.inStock=?1")
    Page<Inventory> searchByInStock(Pageable paging, Boolean inStock);

    @Query("SELECT i FROM Inventory i")
    Page<Inventory> searchInventory(Pageable paging);

}
