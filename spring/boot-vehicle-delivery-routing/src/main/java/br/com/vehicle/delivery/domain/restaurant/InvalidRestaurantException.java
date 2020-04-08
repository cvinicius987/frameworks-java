package br.com.vehicle.delivery.domain.restaurant;

/**
 * Exception que define manipulações sobre os restaurantes.
 * 
 * @author cvinicius
 * @since 11/07/2018
 * @version 1.0
 */
public class InvalidRestaurantException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidRestaurantException(String message) {
		super(message);
	}
	
	public InvalidRestaurantException(String message, Throwable ex) {
		super(message, ex);
	}
}