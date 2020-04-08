package br.com.vehicle.delivery.domain.route;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.vehicle.delivery.domain.order.Order;
import br.com.vehicle.delivery.domain.route.impl.DriverProcessImpl;

public class DriverProcessTest {

	private DriverProcess driverProcess;
	
	@Before
	public void prepare(){
		driverProcess = new DriverProcessImpl();
	}
	
	@Test
	public void driversAvailableTest() {
		
		List<Order> orders = Arrays.asList(new Order(), new Order(), new Order(),
										   new Order(), new Order(), new Order(),
										   new Order(), new Order(), new Order());

		Driver[] drivers = driverProcess.driversAvailable(orders);
		
		Assert.assertSame(3, drivers.length);
	}
	
	@Test
	public void driversAvailableWhenHas7OrdersTest() {
		
		List<Order> orders = Arrays.asList(new Order(), new Order(), new Order(),
										   new Order(), new Order(), new Order(),
										   new Order(), new Order(), new Order(),
										   new Order());

		Driver[] drivers = driverProcess.driversAvailable(orders);
		
		Assert.assertSame(4, drivers.length);
	}
}