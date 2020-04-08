package br.com.vehicle.delivery.domain.route;

import java.util.List;

import lombok.Value;

/**
 * Rotas definidas para cada Pedido.
 * 
 * @author cvinicius
 * @since 05/07/2018
 * @version 1.0
 */
@Value
public class RouteResult{

	private List<Driver> routes;
}