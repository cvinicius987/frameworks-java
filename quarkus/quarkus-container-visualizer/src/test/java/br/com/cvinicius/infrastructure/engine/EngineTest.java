package br.com.cvinicius.infrastructure.engine;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class EngineTest {

    @Inject
    private Engine engine;

    @Test
    public void getClientTest(){

        var dockerClient = engine.getClient();

        assertNotNull(dockerClient);
    }
}