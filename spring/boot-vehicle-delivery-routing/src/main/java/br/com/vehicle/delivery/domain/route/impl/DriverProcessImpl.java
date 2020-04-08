package br.com.vehicle.delivery.domain.route.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import br.com.vehicle.delivery.domain.order.Order;
import br.com.vehicle.delivery.domain.route.Driver;
import br.com.vehicle.delivery.domain.route.DriverProcess;

@Service
public class DriverProcessImpl implements DriverProcess{

	@Override
	public Driver[] driversAvailable(List<Order> orders) {
		
		int size = orders.size();
		
		int total;
		
		if(size <= getTotalOrderToDriver())
			total = 1;
		else
			total = (int) Math.ceil(((double) size / getTotalOrderToDriver()));
		
		List<Driver> drivers = IntStream.range(0, total)
								 	    .mapToObj(i -> new Driver())
								 	    .collect(Collectors.toList());
		
		return drivers.toArray(new Driver[drivers.size()]);
	}

	@Override
	public Integer getTotalOrderToDriver() {
		return 3;
	}

	@Override
	public boolean isReachedLimit(Driver driver) {
		return driver.getOrders().size() == getTotalOrderToDriver();
	}
}