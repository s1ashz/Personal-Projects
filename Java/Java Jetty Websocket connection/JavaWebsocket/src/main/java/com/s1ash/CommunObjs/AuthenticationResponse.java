package com.s1ash.CommunObjs;

public class AuthenticationResponse {

	private final String type = "authentication";

	private int id;
	private String name;
	private String requestToken;
	private boolean result;
	
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
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "AuthenticationResponse [type=" + type + ", id=" + id + ", name=" + name + ", requestToken="
				+ requestToken + ", result=" + result + "]";
	}
	
}
