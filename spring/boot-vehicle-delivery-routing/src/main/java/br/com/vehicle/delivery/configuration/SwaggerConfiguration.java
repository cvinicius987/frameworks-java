package br.com.vehicle.delivery.configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuração da documentação da API.
 * 
 * @author cvinicius
 * @since 06/07/2018
 * @version 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration{

	@Bean
	public Docket api() {
		
		Set<String> consumesProd = new HashSet<>(Arrays.asList("application/json"));
		
		Contact contact = new Contact("Caio Vinicius", "http://www.cvinicius.com", "caio.tecnologia@gmail.com");
		
		return new Docket(DocumentationType.SWAGGER_2)
						.apiInfo(new ApiInfo("Ifood Vehicle Routes", "", "1.0","urn:tos", contact, "", ""))
						.produces(consumesProd)
						.consumes(consumesProd);
	}
}