package br.com.vehicle.delivery.domain.route.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vehicle.delivery.domain.order.Order;
import br.com.vehicle.delivery.domain.restaurant.Restaurant;
import br.com.vehicle.delivery.domain.route.Driver;
import br.com.vehicle.delivery.domain.route.DriverProcess;
import br.com.vehicle.delivery.domain.route.UtilsDistance;
import br.com.vehicle.delivery.domain.route.VehicleRoute;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("vehicleRouteManualLogic")
public class VehicleRouteManualLogic implements VehicleRoute{
	
	@Autowired
	private DriverProcess driverProcess;
	
	@Override
	public List<Driver> createRouteFromDelivery(Restaurant restaurant, List<Order> orders){
		
		final List<Driver> listDrivers = new ArrayList<>();
		
		List<Order> ordersRoutes = new ArrayList<>(orders);
		
		/* 
		 * Logicas dos Motoristas
		 */
		Driver[] drivers = this.driverProcess.driversAvailable(ordersRoutes);
		
		/*
		 * Motoristas
		 */
		takeDrivers:for (int i = 0; i < drivers.length; i++){
			
			//Motorista Current
			Driver driver = drivers[i];
			
			log.info(String.format("Routes para o Driver %d ", i)) ;
			
			//Drivers finais
			listDrivers.add(driver);
			
			//Local Corrente do Motorista, sendo o Restaurante o padrão
			Double latNear = restaurant.getLat();
			Double lngNear = restaurant.getLng();
			
			while(ordersRoutes.iterator().hasNext()) {
										
				//Pega o primeiro Pedido
				Order orderNearDriver = findNearestOrder(latNear, lngNear, ordersRoutes);
				
				//Adiciona o Pedido mais proximo
				driver.addOrder(orderNearDriver);
				
				//Seta o local corrente
				latNear = orderNearDriver.getClient().getLat();
				lngNear = orderNearDriver.getClient().getLng();
				
				//Limite do Motorista atingido
				if(this.driverProcess.isReachedLimit(driver))
					continue takeDrivers;
			}
		} 
		
		return listDrivers;
	}
	
	/**
	 * Busca o Pedido mais proximo do Local indicado.
	 * 
	 * @param lat 			- Latitude do ponto de partida
	 * @param lng			- Longitude do ponto de partida
	 * @param ordersRoutes 	- List contendo as Order
	 * @return Order
	 */
	private Order findNearestOrder(Double lat, Double lng, List<Order> ordersRoutes){
		
		Map<Order, Double> distances = new HashMap<>();

		//Pega a distancia entre o pedido e o ponto
		for (Order order : ordersRoutes) {
			
			Double distance = UtilsDistance.distance(lat, lng, order.getClient().getLat(), order.getClient().getLng());
			
			distances.put(order, distance);
		
			log.info(String.format("Ponto [%s, %s] - Order[%d] Distance [%s]", lat, lng, order.getId(), distance));
		}
		
		Entry<Order, Double> entryOrder = distances.entrySet()
												    .stream()
												    .sorted((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()))
												    .findFirst()
												    .get();
		//Order e distancia do ponto
		Order currentOrder 	= entryOrder.getKey();
		Double distance 	= entryOrder.getValue();
		
		log.info(String.format(" %s partida [%s, %s] destino [%s, %s] distancia total %s", currentOrder, lat, lng, currentOrder.getClient().getLat(), currentOrder.getClient().getLng(), distance));
		
		//Remove o Order já distribuido
		ordersRoutes.remove(currentOrder);
		
		return currentOrder;
	}	
}