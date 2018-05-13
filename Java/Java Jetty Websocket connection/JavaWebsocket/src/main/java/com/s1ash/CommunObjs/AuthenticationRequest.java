package com.s1ash.CommunObjs;

public class AuthenticationRequest {

	private final String type = "authentication";
	private int id;
	private String name;
	private String requestToken;
	
	
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
	
	@Override
	public String toString() {
		return "AuthenticationRequest [type=" + type + ", id=" + id + ", name=" + name + ", requestToken="
				+ requestToken + "]";
	}
	
}
