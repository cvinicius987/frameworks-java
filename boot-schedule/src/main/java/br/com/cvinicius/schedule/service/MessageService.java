package br.com.cvinicius.schedule.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageService {

	@Scheduled(fixedDelay=1000)
	public void message(){
		log.info(" Aqui.....");
	}
}