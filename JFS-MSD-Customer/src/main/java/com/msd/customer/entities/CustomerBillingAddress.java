package com.msd.customer.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "msd_customer_billing_address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBillingAddress implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private long customerAddressId;

	@Column(name = "door_number")
	private long doorNo;

	@Column(name = "street_name")
	private String streetName;

	private String layout;

	private String city;

	private long pincode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_customer_id", nullable = false)
	@JsonBackReference
	private Customer customer;
}