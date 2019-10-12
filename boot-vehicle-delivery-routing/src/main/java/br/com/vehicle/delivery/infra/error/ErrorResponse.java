package br.com.vehicle.delivery.infra.error;

import lombok.NonNull;
import lombok.Value;

/**
 * Contrato de mensagens de erro.
 * 
 * @author cvinicius
 * @since 05/07/2018
 * @version 1.0
 */
@Value
public class ErrorResponse {

	private String message;
	
	/**
	 * Criação de mensagens de erro no endpoints
	 * 
	 * @param message
	 * @return ErrorResponse
	 */
	public static ErrorResponse createNewMessage(@NonNull String message) {
		return new ErrorResponse(message);
	}
}