package br.com.vehicle.delivery.domain.route;

import java.util.List;

import br.com.vehicle.delivery.domain.order.Order;

/**
 * Regras para os motoristas das entregas.
 * 
 * @author Caio
 * @since 09/07/2018
 * @version 1.0
 */
public interface DriverProcess {

	/**
	 * Total de Orders permitidos por Route/Driver
	 * 
	 * @return Integer
	 */
	Integer getTotalOrderToDriver();
	
	/**
	 * Verifica se o total de Orders esta dentro do limite
	 * 
	 * @param driver
	 * @return boolean
	 */
	boolean isReachedLimit(Driver driver);
	
	/**
	 * Define o total de motoristas disponiveis, 
	 * com base na quantidade de pedidos.
	 * 
	 * @param orders
	 * @return Driver[]
	 */
	Driver[] driversAvailable(List<Order> orders);
}