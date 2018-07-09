package br.com.cvinicius.restful.api;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
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

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT) 
@RunWith(SpringRunner.class)
@ContextConfiguration
public class UserApiIntegrationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
    
	@Test
	public void getAllAsJson_200OkTest() 
	throws Exception{
		
	    ResponseEntity<String> response = this.restTemplate.getForEntity("/users", String.class);
		
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getHeaders().getContentType(), equalTo(MediaType.APPLICATION_JSON_UTF8));
	}
	
	@Test
	public void getOneAsJson_200OkTest() 
	throws Exception{
		
		//Parametros
		Map<String, String> params = new HashMap<>();
	    params.put("id", "1");
	    		
	    ResponseEntity<String> response = this.restTemplate.getForEntity("/users/{id}", String.class, params);
		
	    String body = response.getBody();
	    
	    assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getHeaders().getContentType(), equalTo(MediaType.APPLICATION_JSON_UTF8));
		assertThat(body, isJson(withJsonPath("$.name", equalTo("João"))));
	}
	
	@Test
	public void getOneAsJson_404NotFoundTest() 
	throws Exception{
		
		//Parametros
		Map<String, String> params = new HashMap<>();
	    params.put("id", "20");
	    		
	    ResponseEntity<String> response = this.restTemplate.getForEntity("/users/{id}", String.class, params);
		
	    assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}
	
	@Test
	public void save_201CreatedTest() 
	throws Exception{
		
		//Body
		String userJson = "{\"name\":\"Caio\", \"address\":\"Avenida Paulista\"}";
		
		//Headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(userJson, headers);
		
	    ResponseEntity<String> response = this.restTemplate.postForEntity(new URI("/users"), entity, String.class);
		
	    assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
		assertNotNull(response.getHeaders().getLocation());
	}
	
	@Test
	public void update_200OkTest() 
	throws Exception{
		
		//Body
		String userJson = "{\"name\":\"Caio\", \"address\":\"Rua\"}";
		
		//Headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//Parametros
		Map<String, String> params = new HashMap<>();
	    params.put("id", "1");

		HttpEntity<String> entity = new HttpEntity<String>(userJson, headers);
		
		ResponseEntity<String> response = this.restTemplate.exchange("/users/{id}", HttpMethod.PUT, entity, String.class, params);
	    		
		String body = response.getBody();
		
	    assertThat(response.getStatusCode(), is(HttpStatus.OK));
	    assertThat(body, isJson(withJsonPath("$.address", equalTo("Rua"))));
	}
	
	@Test
	public void delete_200Test() 
	throws Exception{
			
		//Parametros
		Map<String, String> params = new HashMap<>();
	    params.put("id", "2");
	
		HttpEntity<String> entity = new HttpEntity<String>(new HttpHeaders());
		
	    ResponseEntity<String> response = this.restTemplate.exchange("/users/{id}", HttpMethod.DELETE, entity, String.class, params);
		
	    assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}
}