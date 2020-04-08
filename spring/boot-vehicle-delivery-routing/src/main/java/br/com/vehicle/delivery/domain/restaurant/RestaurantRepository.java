package br.com.vehicle.delivery.domain.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Gerenciamento dos Restaurantes.
 * 
 * @author Caio
 * @since 07/07/2018
 * @version 1.0
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

}