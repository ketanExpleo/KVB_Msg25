package com.fss.card.acquirer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class AcquiringServer implements Runnable {

	public static final Logger logger = LoggerFactory.getLogger(AcquiringServer.class);
	
	@Value("${acquiringPort}")
	private Integer acquiringPort;
	
	private ServerSocket ssc;
	
	@Autowired
	private ApplicationContext context;
	
	private final Executor executor = Executors.newCachedThreadPool();
	
	
	@Override
	public void run() {
		try {
			ssc = new ServerSocket(acquiringPort);
			logger.info("Application Server Started");
			while(true) {
				Socket socket = ssc.accept();
				executor.execute(context.getBean(AcquiringTransaction.class, socket));
			}
		} catch (Exception e) {e.printStackTrace();}
	}

}
