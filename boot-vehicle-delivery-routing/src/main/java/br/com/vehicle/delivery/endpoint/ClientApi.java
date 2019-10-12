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

import br.com.vehicle.delivery.domain.client.Client;
import br.com.vehicle.delivery.domain.client.InvalidClientException;
import br.com.vehicle.delivery.domain.client.ClientRepository;

/**
 * Endpoint para manipular os clientes.
 * 
 * @author cvinicius
 * @since 05/07/2018
 * @version 1.0
 */
@RestController
@RequestMapping("/clients")
public class ClientApi {

	@Autowired
	private ClientRepository clientRepository;
	
	/**
	 * Criação de um novo Cliente
	 * 
	 * @param client
	 * @return ResponseEntity<Client>
	 * @throws InvalidClientException
	 */
	@PostMapping
	public ResponseEntity<Client> create(@Valid @RequestBody Client client)
	throws InvalidClientException{
		
		if(Objects.isNull(client.getId()) || this.clientRepository.existsById(client.getId()))
			throw new InvalidClientException("ID inválido ou já existente");
		
		Client clientNew = this.clientRepository.save(client);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
													.path("/{id}")
													.buildAndExpand(clientNew.getId())
													.toUri();
		
		return ResponseEntity.created(location).body(client);
	}
	
	/**
	 * Visualização de Clientes
	 * 
	 * @param id - ID do Client
	 * @return ResponseEntity<Client>
	 */ 
	@GetMapping("/{id}")
	public ResponseEntity<Client> getClient(@PathVariable("id") Long id){
		
		return this.clientRepository.findById(id)
									.map(client -> ResponseEntity.ok(client))
									.orElse(ResponseEntity.notFound().build());
	}
	
	/**
	 * Alteração de Clientes.
	 * 
	 * @param id
	 * @param client
	 * @return ResponseEntity<Client>
	 */ 
	@PutMapping("/{id}")
	public ResponseEntity<Client> update(@PathVariable Long id, @Valid @RequestBody Client client){
		
		if(this.clientRepository.existsById(id)){
			
			client.setId(id);
			
			return ResponseEntity.ok(this.clientRepository.save(client));
		}
		else 
			return ResponseEntity.notFound().build();
	}
}