package br.com.cvinicius.infrastructure.engine;

import static java.util.Optional.ofNullable;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class DockerEngineConfig {
	
	@ConfigProperty(name="config.default.url_engine")
	private String defaultDockerHost;
	
	public String getDockerHost() {
		
		return ofNullable(System.getenv(EnvVariables.DOCKER_HOST.getName())).orElse(defaultDockerHost);
	}
}

enum EnvVariables {
	
   DOCKER_HOST("DOCKER_HOST");

   private String name;

   private EnvVariables(String name){
       this.name = name;
   }

   public String getName() {
       return name;
   }
}