package br.com.vehicle.delivery.endpoint;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.vehicle.delivery.domain.order.Order;
import br.com.vehicle.delivery.domain.order.InvalidOrderException;
import br.com.vehicle.delivery.domain.order.OrderRepository;
import br.com.vehicle.delivery.domain.restaurant.Restaurant;
import lombok.extern.slf4j.Slf4j;

/**
 * Endpoint para manipular os pedidos.
 * 
 * @author cvinicius
 * @since 05/07/2018
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderApi {

	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * Criação de um novo Pedido
	 * 
	 * @param order - Pedido a ser gravado
	 * @return ResponseEntity<Order>
	 * @throws InvalidOrderException
	 */
	@PostMapping
	public ResponseEntity<Order> create(@Valid @RequestBody Order order)
	throws InvalidOrderException{
		
		if(this.orderRepository.existsById(order.getId()))
			throw new InvalidOrderException(String.format("O pedido com id [%d] já existe no sistema.", order.getId()));
		
		Order orderNew = orderRepository.save(order);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
													.path("/{id}")
													.buildAndExpand(orderNew.getId())
													.toUri();
		
		return ResponseEntity.created(location).body(orderNew);
	}
	
	/**
	 * Visualização dos Pedidos
	 * 
	 * @param id - ID do Pedido
	 * @return ResponseEntity<Order>
	 */ 
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id){
		
		return this.orderRepository.findById(id)
								   .map(order -> ResponseEntity.ok(order))
								   .orElse(ResponseEntity.notFound().build());
	}
	
	/**
	 * Visualiação de detalhes de pedidos de um restaurante e pro tempo de entrega.
	 * 
	 * @param id 		- ID do Restaurant
	 * @param delivery 	- Tempo previsto para entrega
	 * @return ResponseEntity<List<Order>>
	 */
	@GetMapping(value= {"/restaurant/{id}", 
						"/restaurant/{id}/delivery/{delivery}"})
	public ResponseEntity<List<Order>> getOrderByRestaurant(@PathVariable("id") Long id, 
													  		@PathVariable(name="delivery", required=false) LocalDateTime delivery){
		
		Optional<List<Order>> orders = Optional.empty();
		
		if(Objects.isNull(delivery)) {
			
			log.debug(String.format(" Busca Pedidos por Restaurante [%d] ", id));
			
			orders = orderRepository.findByRestaurant(new Restaurant(id));
		}
		else{
			log.debug(" Busca Pedidos por Restaurante e Data de Entrega");
			
			log.debug(String.format("  Busca Pedidos por Restaurante [%d] e Data de Entrega [%s] ", id, delivery));
			
			orders = orderRepository.findByRestaurantAndDeliveryLessThanEqual(new Restaurant(id), delivery);
		}
				
		return orders.map(order -> ResponseEntity.ok(order))
				   	 .orElse(ResponseEntity.notFound().build());
	}
}