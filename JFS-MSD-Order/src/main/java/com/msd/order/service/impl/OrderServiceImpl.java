package com.msd.order.service.impl;

import java.util.Optional;

import com.msd.order.exceptions.NoDeleteException;
import com.msd.order.exceptions.NoUpdateException;
import com.msd.order.exceptions.NotRecordFoundException;
import com.msd.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msd.order.entity.Order;
import com.msd.order.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Order addOrder(Order order) {
		int count = order.getItems().size();
		for(int i  = 0 ; i < count ; i++) {
			order.getItems().get(i).setOrder(order);
		}
		return orderRepository.save(order);
	}

	@Override
	public Order serachOrder(long id) {
		Optional<Order> foundOrder = orderRepository.findById(id);
		return foundOrder.orElseThrow(()-> new NotRecordFoundException(id));
	}

	@Override
	public Order updateOrder(long id, Order order) {
		Optional<Order> or = orderRepository.findById(id);
		if(or.isPresent()) {
			int count = order.getItems().size();
			for(int i  = 0 ; i < count ; i++) {
				order.getItems().get(i).setOrder(order);
			}	
		return orderRepository.save(order);
		}
		throw new NoUpdateException(id);
	}
	

	@Override
	public boolean deleteOrder(long id) {
		Optional<Order> or = orderRepository.findById(id);
			if(or.isPresent()) {
				orderRepository.deleteById(id);
				return true;
		}
			throw new NoDeleteException(id);
	}

}
