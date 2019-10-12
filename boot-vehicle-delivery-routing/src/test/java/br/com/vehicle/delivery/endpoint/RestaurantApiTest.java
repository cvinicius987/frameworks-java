package br.com.vehicle.delivery.endpoint;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT) 
@RunWith(SpringRunner.class)
@ContextConfiguration
public class RestaurantApiTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void restaurant_createTest() 
	throws RestClientException, URISyntaxException {
		
		String json = "{\"id\": 100, \"title\" : \"cantina\", \"lat\" : \"0.5\", \"lng\" : \"0.5\"}";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		
	    ResponseEntity<String> response = this.restTemplate.postForEntity(new URI("/restaurants"), entity, String.class);
		
	    assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
	    assertThat(response.getHeaders().getLocation().getPath(), equalTo("/restaurants/100"));
	}	
	
	@Test
	public void restaurant_getByIdNotFoundTest() 
	throws RestClientException, URISyntaxException {
		
	    ResponseEntity<String> response = this.restTemplate.getForEntity(new URI("/restaurants/500"), String.class);

	    assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}
	
	@Test
	public void restaurant_updateNotFoundTest() 
	throws RestClientException, URISyntaxException {
		
		String json = "{\"title\" : \"cantina\", \"lat\" : \"0.5\", \"lng\" : \"0.5\"}";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		
		ResponseEntity<String> response = this.restTemplate.exchange("/restaurants/501", HttpMethod.PUT, entity, String.class);
		
	    assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}	
	
	@Test
	public void  restaurant_updateResultOkTest() 
	throws RestClientException, URISyntaxException {
		
		String json = "{\"title\": \"cantina\", \"lat\" : \"0.10\", \"lng\" : \"0.10\"}";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		
		ResponseEntity<String> response = this.restTemplate.exchange("/restaurants/100", HttpMethod.PUT, entity, String.class);
		
	    String body = response.getBody();
	    
	    assertThat(response.getStatusCode(), is(HttpStatus.OK));
	    assertThat(body, isJson(withJsonPath("$.id", equalTo(100))));
	}	
}