package com.msd.order.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "msd_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
	private List<LineItem> items;
}
