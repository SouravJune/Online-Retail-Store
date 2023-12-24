package com.msd.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "msd_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends OperationalTrackEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_description")
	private String productDescription;

	@Column(name = "category")
	private String productCategory;

	private double price;
	
	
}
