package br.com.vehicle.delivery.domain.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.vehicle.delivery.domain.restaurant.Restaurant;

/**
 * Gerenciamento dos dados de pedidos.
 * 
 * @author Caio
 * @since 07/07/2018
 * @version 1.0
 */
public interface OrderRepository extends JpaRepository<Order, Long>{

	/**
	 * Busca um Pedido a partir de um Restaurante.
	 * 
	 * @param restaurant
	 * @return 	Optional<List<Order>>
	 */
	Optional<List<Order>> findByRestaurant(Restaurant restaurant);
	
	/**
	 * Busca um Pedido a partir de um Restaurante com Data de Entrega ate limite
	 * 
	 * @param restaurant
	 * @param delivery
	 * @return Optional<List<Order>>
	 */
	Optional<List<Order>> findByRestaurantAndDeliveryLessThanEqual(Restaurant restaurant, LocalDateTime delivery);
	
	/**
	 * Lista de Pedidos disponíveis para roteamento.
	 *  
	 * @param pickup  - Data/Hora dos pedidos prontos
	 * @return Optional<List<Order>>
	 */
	@Query("SELECT o FROM Order o JOIN FETCH o.client c JOIN FETCH o.restaurant r WHERE o.pickup <= :pickup AND o.finished = false ORDER BY o.delivery ")
	Optional<List<Order>> findPickupAndAvailableOrders(@Param("pickup") LocalDateTime pickup);
	
	/**
	 * Marca o pedido como já finalizado.
	 * 
	 * @param id - ID do Pedido a ser finalizado
	 */
	@Modifying
	@Query("UPDATE Order o SET o.finished = true WHERE o.id = :id ")
	void updateFinished(@Param("id") Long id);
}