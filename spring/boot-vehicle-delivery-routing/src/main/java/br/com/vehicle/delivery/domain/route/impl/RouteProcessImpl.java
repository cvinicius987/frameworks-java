package br.com.vehicle.delivery.domain.route.impl;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vehicle.delivery.domain.order.Order;
import br.com.vehicle.delivery.domain.order.OrderRepository;
import br.com.vehicle.delivery.domain.restaurant.Restaurant;
import br.com.vehicle.delivery.domain.route.Driver;
import br.com.vehicle.delivery.domain.route.RouteException;
import br.com.vehicle.delivery.domain.route.RouteProcess;
import br.com.vehicle.delivery.domain.route.RouteResult;
import br.com.vehicle.delivery.domain.route.OrderFinalize;
import br.com.vehicle.delivery.domain.route.VehicleRoute;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RouteProcessImpl implements RouteProcess{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	@Qualifier("vehicleRouteManualLogic")
	private VehicleRoute vehicleRoute;
	
	@Autowired
	private OrderFinalize orderFinalize;
	
	@Transactional
	@Override
	public Optional<RouteResult> executeProcessLogic()
	throws RouteException {
		
		/*
		 * Lista Geral de Pedidos
		 * 
		 * - Todos os pedidos prontos;
		 * - Ordenados por data de entrega;
		 * - Somente pedidos não roterizados;
		 */
		Optional<List<Order>> opOrders = this.orderRepository.findPickupAndAvailableOrders(LocalDateTime.now());
		
		if(!opOrders.isPresent()){
			
			log.info("Não foram encontrados pedidos para processar");
			
			return Optional.empty();
		}
	
		/*
		 * Separação dos pedidos do mesmo restaurante
		 */
		List<Order> orders = opOrders.get();
		
		log.info(String.format("Encontrado um total de %d", orders.size()));
		 
		Map<Restaurant, List<Order>> map = orders.stream().collect(Collectors.groupingBy(Order::getRestaurant));
		
		/*
		 * Criação da Lista de Pedidos
		 */
		final List<Driver> driverRoutes = new LinkedList<>();
	
		for(Entry<Restaurant, List<Order>> entryOrder : map.entrySet()){
			
			//Restaurante e seus Pedidos
			Restaurant restaurant   = entryOrder.getKey();
			List<Order>	restOrders  = entryOrder.getValue();
			
			log.info(String.format("Processando as rotas do Restaurante %s total de Pedidos %d", restaurant, restOrders.size()));
			
			List<Driver> drivers = this.vehicleRoute.createRouteFromDelivery(restaurant, restOrders);
			 
			driverRoutes.addAll(drivers); 
			
			//Finaliza os pedidos
			this.orderFinalize.finalizeOrder(restOrders);
		}
		
		return Optional.of(new RouteResult(driverRoutes));
	}
}