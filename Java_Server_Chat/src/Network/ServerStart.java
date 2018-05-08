package Network;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.control.TextArea;

public class ServerStart implements Runnable {

	private int port;
	private ArrayList<String> users;
	private ArrayList<Socket> socketsArray;
	private ArrayList<Object> clientOutputStream;
	private HashMap<Object, String> clientHashMap;
	private TextArea messagesAreaUI;
	private ServerSocket serverSock;
	
	public ServerStart(int port, ArrayList<String> users, ArrayList<Object> clientOutputStream,
			 HashMap<Object, String> clientHashMap, TextArea messagesAreaUI) {
		this.port = port;
		this.users = users;
		this.clientOutputStream = clientOutputStream;
		this.clientHashMap = clientHashMap;
		this.messagesAreaUI = messagesAreaUI;
	}

	@Override
	public void run() {
		
		try {
			Thread.sleep(1000);
			serverSock = new ServerSocket(port);
			socketsArray = new ArrayList<>();
			appendMessage("Server started at port: " + port + "...");

			while (true) {
				Socket clientSocket = serverSock.accept();
				socketsArray.add(clientSocket);
				Thread listener = new Thread( new ClientHandler(clientSocket, clientOutputStream, clientHashMap, messagesAreaUI) );
				listener.start();
				appendMessage("--------");
				appendMessage("Got a connection...");
			}
			
		} catch (SocketException socketException) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	private void appendMessage(String message) {
		messagesAreaUI.appendText(message + "\n");
	}

	public ServerSocket getServerSock() {
		return serverSock;
	}

	public ArrayList<Socket> getSocketsArray() {
		return socketsArray;
	}
	
	
	
}
