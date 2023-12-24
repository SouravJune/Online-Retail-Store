package com.msd.customer.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "msd_customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private long customerId;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "customer_email")
	private String customerEmail;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
	@JsonManagedReference
	private List<CustomerBillingAddress> customerBillingAddress;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
	@JsonManagedReference
	private List<CustomerShippingAddress> customerShippingAddress;
	
	
}
