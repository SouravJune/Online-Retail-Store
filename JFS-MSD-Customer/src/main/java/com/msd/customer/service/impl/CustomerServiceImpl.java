package com.msd.customer.service.impl;

import java.util.Optional;

import com.msd.customer.entities.CustomerBillingAddress;
import com.msd.customer.entities.CustomerShippingAddress;
import com.msd.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msd.customer.repository.CustomerRepository;
import com.msd.customer.entities.Customer;
import com.msd.customer.exceptions.NoDeleteException;
import com.msd.customer.exceptions.NoUpdateException;
import com.msd.customer.exceptions.NotRecordFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer addCustomer(Customer customer) {

//		for (CustomerBillingAddress billingAddress : customer.getCustomerBillingAddress()){
//			billingAddress.setCustomer(customer);
//		}
//		for (CustomerShippingAddress shippingAddress : customer.getCustomerShippingAddress()){
//			shippingAddress.setCustomer(customer);
//		}

		return customerRepository.save(customer);
	}

	@Override
	public Boolean deleteCustomer(long id) {

		Optional<Customer> deleteCustomer = customerRepository.findById(id);

		if(deleteCustomer.isPresent()) {
			customerRepository.deleteById(id);
			return true;
		}

		throw new NoDeleteException(id);
	}

	@Override
	public Customer updateCustomer(long id, Customer customer) {

		Optional<Customer> updateCustomer = customerRepository.findById(id);

		if(updateCustomer.isPresent()) {
//
//			if(!customer.getCustomerName().isBlank()) {
//				updateCustomer.get().setCustomerName(customer.getCustomerName());
//			}
//
//			if(!customer.getCustomerEmail().isBlank()) {
//				updateCustomer.get().setCustomerEmail(customer.getCustomerEmail());
//			}
//
//			for (CustomerBillingAddress billingAddress : customer.getCustomerBillingAddress()){
//
//				if(billingAddress.getDoorNo() != 0){
//					billingAddress.getDoorNo()
//				}
//
//
//				billingAddress.setCustomer(customer);
//			}
//			for (CustomerShippingAddress shippingAddress : customer.getCustomerShippingAddress()){
//				shippingAddress.setCustomer(customer);
//			}

		return customerRepository.save(customer);
	}
		throw new NoUpdateException(id);
	}


	@Override
	public Customer serachCustomer(long id) {
		Optional<Customer> searchCustomer = customerRepository.findById(id);
		return searchCustomer.orElseThrow(()-> new NotRecordFoundException(id));
	}

}
