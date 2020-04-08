package br.com.vehicle.delivery.domain.route;

import java.util.ArrayList;
import java.util.List;

import br.com.vehicle.delivery.domain.order.Order;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * Rotas definidas para cada Pedido.
 * 
 * @author cvinicius
 * @since 05/07/2018
 * @version 1.0
 */
@Getter
public class Driver {
	
	@ApiModelProperty(notes="Lista com os ID dos pedidos de forma sequencial")
	private final List<Long> orders  = new ArrayList<>();
		
	/**
	 * Adiciona um Order para um determinado Motorista
	 * 
	 * @param currentOrder - Order a ser entrege por este motorista
	 */
	public void addOrder(Order currentOrder){
		this.orders.add(currentOrder.getId());
	}
}