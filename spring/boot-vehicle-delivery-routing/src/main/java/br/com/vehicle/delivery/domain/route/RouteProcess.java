package br.com.vehicle.delivery.domain.route;

import java.util.Optional;

/**
 * Contrato para processamento das rotas
 * 
 * @author cvinicius
 * @since 06/07/2018
 * @version 1.0
 */
public interface RouteProcess{

	/**
	 * Realiza o processamento das rotas do pedido.
	 * 
	 * @return Optional<RouteResult> 
	 * @throws RouteException
	 */
	Optional<RouteResult> executeProcessLogic() throws RouteException;
}