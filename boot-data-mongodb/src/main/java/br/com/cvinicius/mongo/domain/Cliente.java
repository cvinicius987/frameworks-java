package br.com.cvinicius.mongo.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Cliente {

	@Id
	private String id;
	
	private String razaoSocial;
}