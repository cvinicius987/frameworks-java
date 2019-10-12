package br.com.vehicle.delivery.domain.route;

/**
 * Tratamento de erros na logica de processamento
 * 
 * @author Caio
 * @since 07/07/2018
 * @version 1.0
 */
public class RouteException extends Exception{

	private static final long serialVersionUID = 1L;

	public RouteException(String message) {
		super(message);
	}
}