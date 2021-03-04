package br.com.cvinicius.infrastructure.engine;

import com.github.dockerjava.api.DockerClient;

/**
 * Contrato de comunicação com o Engine Docker
 * 
 * @author cvinicius
 * @since 11/09/2020
 * @version 1.0
 */
public interface Engine {

	/**
	 * Retorna o Docker 
	 * 
	 * @return DockerClient
	 */
    DockerClient getClient();
}