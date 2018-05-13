package com.s1ash.JavaWebsockerClient;

public class ClientApp {
	
	public static void main(String[] args) throws Exception {
	
		System.out.println("New Player-------");
		String address = "ws://localhost:8181";
		
		Player player = new Player();
		
		player.connect(address);
		player.sendAuthentication();
		player.receiveAuthentication();
	}
	
}
