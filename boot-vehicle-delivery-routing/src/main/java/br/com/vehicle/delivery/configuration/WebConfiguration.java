package br.com.vehicle.delivery.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuração da camada Web (Endpoints da Aplicação)
 * 
 * @author Caio
 * @since 09/07/2018
 * @version 1.0
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer{

	@Override
    public void addFormatters(FormatterRegistry registry) {
		
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(registry);
    }
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	    
		configurer.defaultContentType(MediaType.APPLICATION_JSON); 
	}
}