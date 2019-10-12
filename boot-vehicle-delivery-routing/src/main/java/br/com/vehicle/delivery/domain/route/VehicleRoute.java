package br.com.vehicle.delivery.domain.route;

import java.util.List;

import br.com.vehicle.delivery.domain.order.Order;
import br.com.vehicle.delivery.domain.restaurant.Restaurant;

/**
 * Contrato para logica de roterização dos pedidos.
 * 
 * @author Caio
 * @since 09/07/2018
 * @version 1.0
 */
public interface VehicleRoute {

	/**
	 * Executa a roterização dos Pedidos.
	 * 
	 * @param restaurant
	 * @param orders
	 * @return List<Driver>
	 */
	List<Driver> createRouteFromDelivery(Restaurant restaurant, List<Order> orders);
}