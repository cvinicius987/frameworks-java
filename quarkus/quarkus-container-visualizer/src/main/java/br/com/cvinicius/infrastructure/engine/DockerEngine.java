package br.com.cvinicius.infrastructure.engine;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;

@ApplicationScoped
class DockerEngine implements Engine{
    
	@Inject
	private DockerEngineConfig dockerEngineConfig;
	
    private DockerClient dockerClient;

    @Override
    public DockerClient getClient(){
    	
    	if(this.dockerClient == null) {
            
            var config = DefaultDockerClientConfig.createDefaultConfigBuilder()
								                    .withDockerHost(dockerEngineConfig.getDockerHost())
								                .build();
									            
    	    this.dockerClient = DockerClientBuilder.getInstance(config).build();
    	}
    	
        return dockerClient;
    }
}