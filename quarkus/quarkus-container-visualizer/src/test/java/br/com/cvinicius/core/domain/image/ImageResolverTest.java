package br.com.cvinicius.core.domain.image;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class ImageResolverTest {

    @Inject
    private ImageResolver imageResolver;

    @Test
    public void listAllTest(){

        var list = imageResolver.listAll();

        assertNotNull(list);
    }
}