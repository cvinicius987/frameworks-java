package br.com.vehicle.delivery.infra.error;

import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.vehicle.delivery.domain.client.InvalidClientException;
import br.com.vehicle.delivery.domain.order.InvalidOrderException;
import br.com.vehicle.delivery.domain.restaurant.InvalidRestaurantException;
import lombok.extern.slf4j.Slf4j;

/**
 * Tratamento de erros customizados.
 * 
 * @author cvinicius
 * @since 05/07/2018
 * @version 1.0
 */
@Slf4j
@Controller
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler{
	
	/**
	 * Tratamento de erros para padronizar a mensagem de erro em caso
	 * de falha nos processo de gravação de dados.
	 * 
	 * @param ex 
	 * @return ResponseEntity<ErrorResponse>
	 */
	@ExceptionHandler(value= {InvalidClientException.class, 
							  InvalidRestaurantException.class,
							  InvalidOrderException.class})
	protected ResponseEntity<ErrorResponse> handleInvalidException(Exception ex) {
			
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							  .body(ErrorResponse.createNewMessage(ex.getMessage()));
	}	
	
	/**
	 * Tratamento de erros para a validação dos dados de entrada
	 * via BeanValidation
	 * 
	 * @param ex - MethodArgumentNotValidException
	 * @return ResponseEntity<ErrorResponse>
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String message = ex.getBindingResult()
							.getAllErrors()
							.stream()
							.map(err -> err.getDefaultMessage())
							.collect(Collectors.joining(" | "));
			
		log.warn(message);
		
		return ResponseEntity.badRequest()
								.body(ErrorResponse.createNewMessage(message));
	}	
}