package br.com.cvinicius.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="cliente")
public class Cliente {

	@Id
	private String id;
	
	private String razaoSocial;
}