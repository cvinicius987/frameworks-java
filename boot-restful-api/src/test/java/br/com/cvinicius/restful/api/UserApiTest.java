package br.com.cvinicius.restful.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.cvinicius.restful.api.UserApi;
import br.com.cvinicius.restful.domain.User;
import br.com.cvinicius.restful.domain.UserRepository;

public class UserApiTest {

	private MockMvc mockMvc;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserApi userApi;
	
	@Before
	public void init(){
	
		MockitoAnnotations.initMocks(this);
	        
	    mockMvc = MockMvcBuilders.standaloneSetup(userApi).build();
	}
	
	@Test
	public void getAllAsJson_200OkTest() 
	throws Exception{
		
		List<User> list = Arrays.asList(new User(1, "Caio", "Avenida Paulista"));
		
		when(userRepository.findAll()).thenReturn(list);
		
	    mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(jsonPath("$.[0].name",  equalTo("Caio")))
	            .andExpect(jsonPath("$.[0].address", equalTo("Avenida Paulista")));
	}
	
	@Test
	public void getOneAsJson_200OkTest() 
	throws Exception{
		
		User user = new User(1, "Caio", "Avenida Paulista");
		
		when(userRepository.findById(1)).thenReturn(Optional.of(user));
		
	    mockMvc.perform(get("/users/1").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(jsonPath("$.name",  equalTo("Caio")))
	            .andExpect(jsonPath("$.address", equalTo("Avenida Paulista")));
	}
	
	@Test
	public void getOneAsJson_404NotFoundTest() 
	throws Exception{
		
		User user = new User(1, "Caio", "Avenida Paulista");
		
		when(userRepository.findById(1)).thenReturn(Optional.of(user));
		
	    mockMvc.perform(get("/users/2").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(status().isNotFound());
	}
	
	@Test
	public void save_201CreatedTest() 
	throws Exception{
		
		String userJson = "{\"name\":\"Caio\", \"address\":\"Avenida Paulista\"}";
		User userParam  = new User(null, "Caio", "Avenida Paulista");
		
		when(userRepository.save(userParam)).thenReturn(new User(1, "Caio", "Avenida Paulista"));
				
	    mockMvc.perform(post("/users")
	    					.contentType(MediaType.APPLICATION_JSON)
	    					.content(userJson))
	            .andExpect(status().isCreated());
	}
	
	@Test
	public void update_200OkTest() 
	throws Exception{
		
		String userJson = "{\"name\":\"Caio\", \"address\":\"Rua\"}";
		User userParam  = new User(1, "Caio", "Rua");
		
		when(userRepository.save(userParam)).thenReturn(new User(1, "Caio", "Rua"));
				
	    mockMvc.perform(put("/users/1")
	    					.contentType(MediaType.APPLICATION_JSON)
	    					.content(userJson))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void delete_200Test() 
	throws Exception{
				
	    mockMvc.perform(delete("/users/1"))
	            .andExpect(status().isOk());
	}
}