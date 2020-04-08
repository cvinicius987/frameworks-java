package br.com.vehicle.delivery.domain.order;

/**
 * Exception que define que um pedido já existe na base.
 * 
 * @author cvinicius
 * @since 11/07/2018
 * @version 1.0
 */
public class InvalidOrderException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidOrderException(String message) {
		super(message);
	}
	
	public InvalidOrderException(String message, Throwable ex) {
		super(message, ex);
	}
}