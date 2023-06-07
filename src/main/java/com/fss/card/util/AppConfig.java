package com.fss.card.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.fss.card.acquirer.AcquiringServer;

@Configuration
public class AppConfig {

	@Autowired
	private ApplicationContext context;
	
	@EventListener
	public void run(ApplicationReadyEvent event) {
		new Thread(context.getBean(AcquiringServer.class)).start();
	}
}
