package br.com.vehicle.delivery.domain.route.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vehicle.delivery.domain.order.Order;
import br.com.vehicle.delivery.domain.order.OrderRepository;
import br.com.vehicle.delivery.domain.route.OrderFinalize;

@Service
public class OrderFinalizeImpl implements OrderFinalize{

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public void finalizeOrder(Order order) {
				
		this.orderRepository.updateFinished(order.getId());
	}

	@Override
	public void finalizeOrder(Collection<Order> orders) {
				
		orders.forEach(order -> this.finalizeOrder(order));
	}
}