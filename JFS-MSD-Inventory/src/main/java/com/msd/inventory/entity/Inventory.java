package com.msd.inventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "msd_inventory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory extends OperationalTrackEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inventory_id")
	private long inventoryId;

	@Column(name = "product_id")
	private long productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_description")
	private String description;

	@Column(name = "quantity")
	private long quantity;

	@Column(name = "category")
	private String productCategory;

	@Column(name = "supplier")
	private String productSupplier;

	private boolean inStock;


}
