package br.com.vehicle.delivery.configuration;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

/**
 * Jackson Configuration padronização da logica de geração de Objetos JSON.
 * 
 * @author cvinicius
 * @since 19/12/2017
 * @version 1.0
 */
@Slf4j
@Configuration
public class JacksonConfiguration{
	
	@PostConstruct
	public void prepare(){
		log.info(" Criacao do Jackson Configuration para geração de JSON.");
	}
	
	@Bean
	public Jackson2ObjectMapperBuilder customJacksonMapper() {
		
		log.info(" Customização da geração do JSON.");
		
		Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder = new Jackson2ObjectMapperBuilder();
		
		jackson2ObjectMapperBuilder.featuresToEnable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
								   .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
								   .modules(new Jdk8Module(), new JavaTimeModule());
		
		return jackson2ObjectMapperBuilder;
	}
}