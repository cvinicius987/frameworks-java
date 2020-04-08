package br.com.vehicle.delivery.endpoint;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import org.junit.Before;
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
public class OrderApiTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	private LocalDateTime delivery;
	
	@Before
	public void prepare() {
		
		this.delivery = LocalDateTime.now().plusMinutes(30);
	}
	
	@Test
	public void order_createTest() 
	throws RestClientException, URISyntaxException {
		
		String json = "{\r\n" + 
						"    \"id\": 100,\r\n" + 
						"    \"restaurantId\": 1,\r\n" + 
						"    \"clientId\": 1,\r\n" + 
						"    \"pickup\": \"2018-06-05T13:37:00Z\",\r\n" + 
						"    \"delivery\": \""+delivery.toString()+"\"\r\n" + 
						"}";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		
	    ResponseEntity<String> response = this.restTemplate.postForEntity(new URI("/orders"), entity, String.class);
		
	    assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
	    assertThat(response.getHeaders().getLocation().getPath(), equalTo("/orders/100"));
	}	
	
	@Test
	public void order_getByIdNotFoundTest() 
	throws RestClientException, URISyntaxException {
		
	    ResponseEntity<String> response = this.restTemplate.getForEntity(new URI("/orders/3"), String.class);

	    assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}
	
	@Test
	public void order_getByIdTest() 
	throws RestClientException, URISyntaxException {
		
	    ResponseEntity<String> response = this.restTemplate.getForEntity(new URI("/orders/100"), String.class);

	    assertThat(response.getStatusCode(), is(HttpStatus.OK));
	}
	
	@Test
	public void order_getByRestaurantTest() 
	throws RestClientException, URISyntaxException {
		
	    ResponseEntity<String> response = this.restTemplate.getForEntity(new URI("/orders/restaurant/1"), String.class);

	    assertThat(response.getStatusCode(), is(HttpStatus.OK));
	}
	
	@Test
	public void order_getByRestaurantAndTimeTest() 
	throws RestClientException, URISyntaxException {
		
	    ResponseEntity<String> response = this.restTemplate.getForEntity(new URI("/orders/restaurant/1/delivery/"+delivery.toString()), String.class);

	    assertThat(response.getStatusCode(), is(HttpStatus.OK));
	}
}