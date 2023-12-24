package com.msd.customer.repository;

import com.msd.customer.entities.CustomerShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerShippingAddressRepository extends JpaRepository<CustomerShippingAddress, Long> {

}
