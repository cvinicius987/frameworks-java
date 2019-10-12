package br.com.vehicle.delivery.domain.client;

/**
 * Exception que define manipulações sobre os clientes.
 * 
 * @author cvinicius
 * @since 11/07/2018
 * @version 1.0
 */
public class InvalidClientException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidClientException(String message) {
		super(message);
	}
	
	public InvalidClientException(String message, Throwable ex) {
		super(message, ex);
	}
}