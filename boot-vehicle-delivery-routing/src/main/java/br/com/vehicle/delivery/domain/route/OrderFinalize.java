package br.com.vehicle.delivery.domain.route;

import java.util.Collection;

import br.com.vehicle.delivery.domain.order.Order;

/**
 * Logica para centralizar a finalização de pedidos.
 * 
 * @author cvinicius
 * @since 12/07/2018
 * @version 1.0
 */
public interface OrderFinalize {

	/**
	 * Realiza a finalização do pedido
	 * 
	 * @param order - Pedido a ser finalizado
	 */
	void finalizeOrder(Order order);
	
	/**
	 * Realiza a finalização do pedido
	 * 
	 * @param order - Coleção de pedidos a serem finalizados
	 */
	void finalizeOrder(Collection<Order> orders);
}