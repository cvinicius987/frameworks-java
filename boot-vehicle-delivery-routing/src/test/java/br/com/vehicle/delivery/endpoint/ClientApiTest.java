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
public class ClientApiTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void client_createOkTest() 
	throws RestClientException, URISyntaxException {
		
		String json = "{\"id\": 100, \"name\" : \"caio\", \"lat\" : \"0.0\", \"lng\" : \"0.0\"}";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		
	    ResponseEntity<String> response = this.restTemplate.postForEntity(new URI("/clients"), entity, String.class);
		
	    assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
	    assertThat(response.getHeaders().getLocation().getPath(), equalTo("/clients/100"));
	}	
	
	@Test
	public void client_getByIdNotFoundTest() 
	throws RestClientException, URISyntaxException {
		
	    ResponseEntity<String> response = this.restTemplate.getForEntity(new URI("/clients/101"), String.class);

	    assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}
	
	@Test
	public void client_updateNotFoundTest() 
	throws RestClientException, URISyntaxException {
		
		String json = "{\"name\" : \"caio\", \"lat\" : \"0.5\", \"lng\" : \"0.5\"}";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		
		ResponseEntity<String> response = this.restTemplate.exchange("/clients/500", HttpMethod.PUT, entity, String.class);
		
	    assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}	
	
	@Test
	public void client_updateResultOkTest() 
	throws RestClientException, URISyntaxException {
		
		String json = "{\"name\" : \"caio\", \"lat\" : \"0.5\", \"lng\" : \"0.5\"}";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		
		ResponseEntity<String> response = this.restTemplate.exchange("/clients/100", HttpMethod.PUT, entity, String.class);
		
	    String body = response.getBody();
	    
	    assertThat(response.getStatusCode(), is(HttpStatus.OK));
	    assertThat(body, isJson(withJsonPath("$.id", equalTo(100))));
	}	
}