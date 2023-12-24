package com.msd.customer.repository;

import com.msd.customer.entities.CustomerBillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerBillingAddressRepository extends JpaRepository<CustomerBillingAddress, Long> {

}
