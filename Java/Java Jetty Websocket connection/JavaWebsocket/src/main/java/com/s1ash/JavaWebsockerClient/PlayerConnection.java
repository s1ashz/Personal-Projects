package com.s1ash.JavaWebsockerClient;

import java.net.URI;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WriteCallback;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class PlayerConnection implements PlayerConnectionListener {
	
	private Session session;
	public static final int MESSAGE_TIME_OUT_IN_MILLIS = 5000;
	private LinkedBlockingQueue<String> receivedMessages = new LinkedBlockingQueue<String>();
	private boolean isConnected = false;

	public PlayerConnection(String address) {
		createConnection(address);
	}
	
	private void createConnection(String address) {
		try {
			URI uri = new URI(address);
			CommunicationSocket socket = new CommunicationSocket(this);
			WebSocketClient client = new WebSocketClient();
			client.setMaxIdleTimeout(1000*3600);
			client.start();
			client.connect(socket, uri).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendMessage(String message) {
		System.out.println("Listener: sendMessage");
		try {
			session.getRemote().sendString(message, new WriteCallback() {

				@Override
				public void writeFailed(Throwable x) {
					System.out.println("Message failed to sent");
				}

				@Override
				public void writeSuccess() {
					System.out.println("Message successfully sent");
				}
				
			} );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getResponse(String responsType) {
		String response = null;
		try {
			LinkedBlockingQueue<String> receivedMessagesback = new LinkedBlockingQueue<String>();
			do {
				response = receivedMessages.poll( MESSAGE_TIME_OUT_IN_MILLIS, TimeUnit.MILLISECONDS );
				if (null == response) {
					System.out.println("No more messages in query");
					break;
				}
	
				if ( response.contains(responsType) ) {
					System.out.println("received requested response"); 
					break;
				}
				receivedMessagesback.put(response);
			} while ( true );

			if ( receivedMessagesback.size() != 0 ) {
				for (String backedMessage : receivedMessagesback) {
					System.out.println("Restoring string.... ::" + backedMessage);
					receivedMessages.put(backedMessage);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public void onReceive(String message) {
		System.out.println("Listener: onReceive + " + message);
		try {
			if ( null == message ) {
				System.out.println("MESSAGE FROM SERVER WAS NULL...... " + message);
			} else {
				receivedMessages.put(message);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onClose() {
		System.out.println("Listener: onClose");		
		isConnected = false;
	}

	@Override
	public void onConnect(Session session) {
		this.session = session;
		isConnected = true;
		System.out.println("Listener: onConnect");		
	}

	@Override
	public void onError() {
		System.out.println("Listener: onError");
	}
	
	
}
