package com.s1ash.JavaWebsockerClient;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;

public class CommunicationSocket implements WebSocketListener {

	private PlayerConnectionListener playerConnectionListener;
	
	public CommunicationSocket(PlayerConnectionListener playerConnectionListener) {
		this.playerConnectionListener = playerConnectionListener;
	}
	
	@Override
	public void onWebSocketClose(int statusCode, String reason) {
		System.out.println("SocketClass: onClose");
		playerConnectionListener.onClose();
	}

	@Override
	public void onWebSocketConnect(Session session) {
		System.out.println("SocketClass: onConnect");
		playerConnectionListener.onConnect(session);
	}

	@Override
	public void onWebSocketError(Throwable cause) {
		System.out.println("SocketClass: onError");
		System.out.println(cause);
		playerConnectionListener.onError();
	}

	@Override
	public void onWebSocketBinary(byte[] payload, int offset, int len) {
		System.out.println("SocketClass: onBinary");
	}

	@Override
	public void onWebSocketText(String message) {
		System.out.println("onText");
		playerConnectionListener.onReceive(message);
	}

}
