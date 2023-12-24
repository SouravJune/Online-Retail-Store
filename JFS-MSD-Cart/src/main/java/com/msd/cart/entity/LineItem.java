package com.msd.cart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "msd_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LineItem extends OperationalTrackEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private long itemId;

	@Column(name = "product_id")
	private long productId;

	@Column(name = "product_name")
	private String productName;

	private long quantity;
	private double price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_cart_id")
	@JsonBackReference
	private Cart cart;
}
