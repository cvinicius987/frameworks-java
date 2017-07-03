package br.com.cvinicius.mongo.api;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cvinicius.mongo.domain.Cliente;
import br.com.cvinicius.mongo.domain.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteAPI {

	@Autowired
	private ClienteRepository repository;
	
	@GetMapping
	public ResponseEntity<?> list(){
		
		List<Cliente> list = repository.findAll();
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(path="/{cnpj}")
	public ResponseEntity<Cliente> view(@PathVariable String cnpj){
		
		Cliente cliente = repository.findOne(cnpj);
		
		if(Objects.isNull(cliente)){
			
			return ResponseEntity.noContent().build();
		}
		else{
			return ResponseEntity.ok(cliente);
		}
	}
}