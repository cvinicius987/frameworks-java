package br.com.cvinicius.mongo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}