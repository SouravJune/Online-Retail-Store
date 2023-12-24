package com.msd.customer.service;

import com.msd.customer.entities.Customer;

public interface CustomerService {

	Customer addCustomer(Customer customer);
	Boolean deleteCustomer(long id);
	Customer updateCustomer(long id, Customer customer);
	Customer serachCustomer(long id);

}
