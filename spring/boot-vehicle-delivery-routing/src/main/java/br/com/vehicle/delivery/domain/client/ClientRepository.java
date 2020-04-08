package br.com.vehicle.delivery.domain.client;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Gerenciamento dos dados de clientes.
 * 
 * @author Caio
 * @since 07/07/2018
 * @version 1.0
 */
public interface ClientRepository extends JpaRepository<Client, Long>{

}