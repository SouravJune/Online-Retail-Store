package com.msd.cart.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "msd_cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends OperationalTrackEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private long cartId;

	@Column(name = "customer_id")
	private String customerId;
	
	@OneToMany(
			mappedBy = "cart",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JsonManagedReference
	private List<LineItem> items;
}
