package com.s1ash.JavaWebsocketServer;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import com.google.gson.Gson;
import com.s1ash.CommunObjs.AuthenticationRequest;
import com.s1ash.CommunObjs.AuthenticationResponse;

public class ServerApp extends WebSocketAdapter {
	
	private boolean isConnected = false;
	private Session session;
	
    public static void main( String[] args ) {

    	try {
            Server server = new Server( new InetSocketAddress(8181) );
            
            ServerCommunicationHandler serverWSHandler = new ServerCommunicationHandler();
            
            server.setHandler(serverWSHandler);
            server.setStopTimeout(0);
            server.start();
            server.join();
            
        } catch (Throwable e) {
            e.printStackTrace();
        }
    	
    }

	@Override
	public boolean isConnected() {
		System.out.println("SERVER: isConnected");
		return super.isConnected();
	}

	@Override
	public void onWebSocketBinary(byte[] payload, int offset, int len) {
		System.out.println("SERVER: binary");
		super.onWebSocketBinary(payload, offset, len);
	}

	@Override
	public void onWebSocketClose(int statusCode, String reason) {
		isConnected = false;
		System.out.println("SERVER: onCLose");
		super.onWebSocketClose(statusCode, reason);
	}

	@Override
	public void onWebSocketConnect(Session sess) {
		System.out.println("SERVER: onConnect");
		super.onWebSocketConnect(sess);
		session = sess;
		isConnected = true;
		
		//keepAlive();
	}

	@Override
	public void onWebSocketError(Throwable cause) {
		System.out.println("SERVER: onError");
		super.onWebSocketError(cause);
	}

	@Override
	public void onWebSocketText(String message) {
		super.onWebSocketText(message);
		
		System.out.println(message);
		
		if ( message.contains("authentication") ) {
			
			AuthenticationRequest request = new AuthenticationRequest();
			request = (AuthenticationRequest) deserialize( message, request );
			
			System.out.println(request.toString());
			
			AuthenticationResponse response = new AuthenticationResponse();
			response.setId(request.getId());
			response.setName(request.getName());
			response.setRequestToken(request.getRequestToken());
			response.setResult(true);
			System.out.println("acepted request.....sending status true");
			
			if ( isConnected ) {
				try {
					String authResponse = serializeObject(response);
					//sending some trash messages to get sure client can just decode the auth response
					session.getRemote().sendString("one");
					session.getRemote().sendString("two");
					session.getRemote().sendString("three");
					session.getRemote().sendString(authResponse);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private Object deserialize(String json, Object obj) {
		Gson gson = new Gson();
		obj = gson.fromJson(json, obj.getClass());
		return obj;
	}

	private String serializeObject(Object obj) {
		Gson gson = new Gson();
		String message = gson.toJson(obj);
		return message;
	}
	
	private void keepAlive() {
		if ( null != session ) {
			while( isConnected ) {
				ByteBuffer bbuf = ByteBuffer.allocate(10);
				try {
					session.getRemote().sendString("Pinging connection....");
					Thread.sleep(3000);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
    
}
