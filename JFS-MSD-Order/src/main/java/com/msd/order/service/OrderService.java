package com.msd.order.service;

import com.msd.order.entity.Order;

public interface OrderService {

	public Order addOrder(Order order);
	public Order serachOrder(long id);
	public Order updateOrder(long id, Order order);
	public boolean deleteOrder(long id);

}
