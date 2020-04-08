package br.com.cvinicius.profile.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cvinicius.profile.service.Message;

@RestController
public class MessageApi{

	@Autowired
	private Message message;
	
	@GetMapping("/")
	public String sendMessage(){
		return message.getMessage();
	}
}
