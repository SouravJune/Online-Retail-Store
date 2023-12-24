package com.msd.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msd.product.entity.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    Optional<Product> findByProductName(String productName);
}
