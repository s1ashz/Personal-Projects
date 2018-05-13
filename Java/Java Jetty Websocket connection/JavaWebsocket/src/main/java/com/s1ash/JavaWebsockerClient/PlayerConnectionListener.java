package com.s1ash.JavaWebsockerClient;

import org.eclipse.jetty.websocket.api.Session;

public interface PlayerConnectionListener {

	void onClose();
	void onConnect(Session session);
	void onError();
	void sendMessage(String message);
	void onReceive(String message);
	
}
