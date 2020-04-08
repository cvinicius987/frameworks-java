package br.com.cvinicius.profile.service.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.cvinicius.profile.service.Message;

@Profile("homolog")
@Component
public class HomologMessage implements Message{

	@Override
	public String getMessage() {
		return "Ambiente de Homologação";
	}
}