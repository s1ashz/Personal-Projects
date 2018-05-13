package com.s1ash.JavaWebsocketServer;

import java.util.logging.Logger;

import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class ServerCommunicationHandler extends WebSocketHandler{

	private final static Logger LOG = Logger.getLogger(ServerCommunicationHandler.class.getName());
	
	@Override
	public void configure(WebSocketServletFactory factory) {
		
		LOG.info("Configuring...");
		factory.getPolicy().setIdleTimeout(1000*3600);
		factory.setCreator( new WebSocketCreator( ) {
			
			public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
				LOG.info("Instantiating Server App...");
				return new ServerApp();
			}
		});

	}

}
