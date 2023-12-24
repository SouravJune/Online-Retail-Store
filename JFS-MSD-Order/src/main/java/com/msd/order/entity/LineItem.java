package com.msd.order.entity;

import java.io.Serializable;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class LineItem implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long itemId;
	private long productId;
	private String productName;
	private long quantity;
	private double price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_order_id")
	@JsonIgnore
	private Order order;
}
