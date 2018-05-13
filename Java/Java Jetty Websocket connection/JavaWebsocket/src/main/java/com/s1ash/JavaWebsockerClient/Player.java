package com.s1ash.JavaWebsockerClient;

import com.google.gson.Gson;
import com.s1ash.CommunObjs.AuthenticationRequest;
import com.s1ash.CommunObjs.AuthenticationResponse;

public class Player {

	private int id;
	private String name;
	private String requestToken;
	private PlayerConnection playerConn ;
	private AuthenticationResponse authResponse;
	private AuthenticationRequest authRequest;
	
	public Player() {
		this.name = "s1ash";
		this.id = 456678998;
		this.requestToken = "KJHFGKJDHBJHKLG564787234";
	}

	public void connect(String address) {
		this.playerConn = new PlayerConnection(address);
	}
	
	public void sendAuthentication() {
		
		authRequest = new AuthenticationRequest();
		authRequest.setName(getName());
		authRequest.setRequestToken(getRequestToken());
		authRequest.setId(getId());;
		
		String authMessage = serializeObject(authRequest);
		sendMessage(authMessage);
	}
	
	public void receiveAuthentication() {
		String response = playerConn.getResponse("authentication");
		authResponse = new AuthenticationResponse();
		
		if ( null != response )	authResponse = (AuthenticationResponse) deserialize(response, authResponse);
		
		System.out.println(authResponse.getId());
		System.out.println(authResponse.getName());
		System.out.println(authResponse.getRequestToken());
		System.out.println(authResponse.isResult());
	}
	
	
	public void sendMessage(String message) {
		playerConn.sendMessage(message);
	}

	private String serializeObject(Object obj) {
		Gson gson = new Gson();
		String message = gson.toJson(obj);
		return message;
	}
	
	private Object deserialize(String json, Object obj) {
		Gson gson = new Gson();
		obj = gson.fromJson(json, obj.getClass());
		return obj;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRequestToken() {
		return requestToken;
	}

	public void setRequestToken(String requestToken) {
		this.requestToken = requestToken;
	}
	
}
