package br.com.vehicle.delivery.endpoint;

import java.net.URI;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.vehicle.delivery.domain.restaurant.InvalidRestaurantException;
import br.com.vehicle.delivery.domain.restaurant.Restaurant;
import br.com.vehicle.delivery.domain.restaurant.RestaurantRepository;

/**
 * Endpoint para manipular os Restaurantes.
 * 
 * @author cvinicius
 * @since 05/07/2018
 * @version 1.0
 */
@RestController
@RequestMapping("/restaurants")
public class RestaurantApi {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	/**
	 * Criação de um novo Restaurant
	 * 
	 * @param restaurant
	 * @return ResponseEntity<Restaurant>
	 * @throws InvalidRestaurantException
	 */
	@PostMapping
	public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant)
	throws InvalidRestaurantException{
		
		if(Objects.isNull(restaurant.getId()) || this.restaurantRepository.existsById(restaurant.getId())) {
			throw new InvalidRestaurantException("ID inválido ou já existente");
		}
		
		Restaurant newObj = this.restaurantRepository.save(restaurant);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
													.path("/{id}")
													.buildAndExpand(newObj.getId())
													.toUri();
		
		return ResponseEntity.created(location).body(restaurant);
	}
	
	/**
	 * Visualização de um Restaurant
	 * 
	 * @param id
	 * @return ResponseEntity<Restaurant>
	 */ 
	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> getClient(@PathVariable("id") Long id){
		
		return this.restaurantRepository.findById(id)
										.map(rest -> ResponseEntity.ok(rest))
										.orElse(ResponseEntity.notFound().build());
	}
	
	/**
	 * Alteração de Restaurants.
	 * 
	 * @param id
	 * @param restaurant
	 * @return ResponseEntity<Restaurant>
	 */ 
	@PutMapping("/{id}")
	public ResponseEntity<Restaurant> update(@PathVariable Long id, @Valid @RequestBody Restaurant restaurant){
		
		if(restaurantRepository.existsById(id)){
			
			restaurant.setId(id);
			
			return ResponseEntity.ok(this.restaurantRepository.save(restaurant));
		}
		else 
			return ResponseEntity.notFound().build();
	}
}