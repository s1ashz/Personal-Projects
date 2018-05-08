package Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import controller.Controller;
import data.Data;
import javafx.scene.control.TextArea;

public class NetworkConnectionImpl implements NetworkConnection {

	private String address;
	private int port; 
	private TextArea messagesArea;

	private Socket sock;
	private ObjectOutputStream objWriter;
	private ObjectInputStream objReader;
	
	public NetworkConnectionImpl(String address, int port, TextArea messagesArea ) {
		this.address = address;
		this.port = port;
		this.messagesArea = messagesArea;
	}
	
	@Override
	public void connecServer(Data userData) {
		appendMessage("--------");
		appendMessage("Connecting to Server...");

		try {
			sock = new Socket(address, port);
			objWriter = new ObjectOutputStream(sock.getOutputStream());
			objReader = new ObjectInputStream(sock.getInputStream());
			appendMessage("Connected to server...");
			sendMessage(userData);
			listenThread();
			Controller.isConnected = true;
		} catch (Exception e) {
			appendMessage("Cannot connect! Try again latter. \n");
		}
	}

	private void listenThread() {
		Thread incoReader = new Thread( new IncommingReader(objReader, messagesArea) );
		incoReader.start();
	}

	@Override
	public void sendMessage(Data userData) {
		try {
			objWriter.reset();
			objWriter.writeObject(userData);
			objWriter.flush();
		} catch (IOException e) {
			appendMessage("Server failed to send...");
			e.printStackTrace();
		}
	}

	@Override
	public void disconnectServer() {
		try {
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void appendMessage(String message) {
		messagesArea.appendText(message + "\n");
	}

}
