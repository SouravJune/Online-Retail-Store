package com.msd.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msd.cart.entity.Cart;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

    Optional<Cart> findByCustomerId(String customerId);
}
