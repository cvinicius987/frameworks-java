package br.com.vehicle.delivery.endpoint;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT) 
@RunWith(SpringRunner.class)
@ContextConfiguration
public class RouteApiTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void routesTest() 
	throws RestClientException, URISyntaxException {
		
	    ResponseEntity<String> response = this.restTemplate.getForEntity(new URI("/routes"), String.class);
	    
	    assertThat(response.getStatusCode(), is(HttpStatus.OK));
	}
}