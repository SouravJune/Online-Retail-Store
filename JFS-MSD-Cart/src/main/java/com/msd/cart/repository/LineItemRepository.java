package com.msd.cart.repository;

import com.msd.cart.entity.LineItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM LineItem l WHERE l.productName IN ?1")
    void deleteAllByProductNames(List<String> productNames);
}
