package br.com.vehicle.delivery.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Classe para cessar propriedades de configuração.
 * 
 * @author cvinicius
 * @since 06/07/2018
 * @version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix="config")
public class Config {

}