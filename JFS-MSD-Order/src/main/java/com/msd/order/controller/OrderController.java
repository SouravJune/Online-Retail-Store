package com.msd.order.controller;

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

import com.msd.order.entity.Order;
import com.msd.order.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/order")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		Order savedOrder = orderService.addOrder(order); 
		return new ResponseEntity<Order>(savedOrder , HttpStatus.CREATED);
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<Order> serachOrder(@PathVariable long id) {
		Order getOrder = orderService.serachOrder(id);
		return new ResponseEntity<Order>(getOrder , HttpStatus.OK);
	}
	
	@PutMapping("/order/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable long id ,@RequestBody Order order) {
		Order updateOrder = orderService.updateOrder(id,order);
		return new ResponseEntity<Order>(updateOrder , HttpStatus.OK);
	}
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<Boolean> deleteOrder(@PathVariable long id) {
		boolean deleteOrder = orderService.deleteOrder(id);
		return new ResponseEntity<Boolean>(deleteOrder , HttpStatus.ACCEPTED);
	}
}
