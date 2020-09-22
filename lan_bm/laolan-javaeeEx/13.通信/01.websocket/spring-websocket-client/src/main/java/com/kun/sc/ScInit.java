package com.kun.sc;

import com.kun.sc.config.WebSocketJavaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScInit implements CommandLineRunner{

	@Autowired
	private WebSocketJavaClient webSocketJavaClient;
	
	@Override
	public void run(String... args) {
		webSocketJavaClient.clientConnect();
	}
	
}
