package br.com.vehicle.delivery.endpoint;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vehicle.delivery.domain.route.RouteException;
import br.com.vehicle.delivery.domain.route.RouteProcess;
import br.com.vehicle.delivery.domain.route.RouteResult;

/**
 * Endpoint para manipular as solicitações de criação e gerenciamento de Rotas de entraga.
 * 
 * @author cvinicius
 * @since 05/07/2018
 * @version 1.0
 */
@RestController
@RequestMapping("/routes")
public class RouteApi {

	@Autowired
	private RouteProcess routeProcess;
	
	/**
	 * Processamento das Routes.
	 * 
	 * @return ResponseEntity<RouteResult>
	 * @throws RouteException
	 */
	@GetMapping
	public ResponseEntity<RouteResult> processRoutes()
	throws RouteException{
		
		Optional<RouteResult> result = this.routeProcess.executeProcessLogic();
		
		return result.map(r -> ResponseEntity.ok(r))
					 .orElse(ResponseEntity.noContent().build());		
	}
}