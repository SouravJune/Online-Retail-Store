package com.msd.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msd.customer.entities.Customer;
import com.msd.customer.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customer));
	}
	
	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<Boolean> deleteCustomer(@PathVariable(value = "customerId") long id){
		return ResponseEntity.status(HttpStatus.OK).body(customerService.deleteCustomer(id));
	}
	
	@PutMapping("/customer/{customerId}")
	public ResponseEntity<Customer> updateCustomer(
			@PathVariable(value = "customerId") long id ,
			@RequestBody Customer customer){
		return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(id , customer));
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> serachCustomer(@PathVariable(value = "customerId") long id){
		return ResponseEntity.status(HttpStatus.OK).body(customerService.serachCustomer(id));
	}
}
