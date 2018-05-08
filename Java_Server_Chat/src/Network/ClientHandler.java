package Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import data.Data;
import data.DataType;
import javafx.scene.control.TextArea;

public class ClientHandler extends NetworkUtils implements Runnable {

	private Socket sock;
	private ArrayList<Object> clientOutputStream;
	private HashMap<Object, String> clientHashMap;
	private TextArea messagesAreaUI;

	ObjectInputStream objReader;
	ObjectOutputStream objWriter;
	
	public ClientHandler(Socket clientSocket, ArrayList<Object> clientOutputStream,
			HashMap<Object, String> clientHashMap, TextArea messagesAreaUI) {
		try {
			this.sock = clientSocket;
			this.clientOutputStream = clientOutputStream;
			this.clientHashMap = clientHashMap;
			this.messagesAreaUI = messagesAreaUI;

			objWriter = new ObjectOutputStream(sock.getOutputStream());
			objReader = new ObjectInputStream(sock.getInputStream());
			
			this.clientOutputStream.add(objWriter);
		} catch (IOException e) {
			appendMessage("Unexpected error..... \n");
		}
	}

	@Override
	public void run() {
		String message;
			
		try {
			Data localData = null;
			while( true ) {
				//if ( 0 != objReader.available() ) {
					localData = (Data) objReader.readObject();
					if ( DataType.CONNECT == localData.getDataType() ) {
						message = "New user has connected: " + localData.getUsername();
						this.clientHashMap.put(objWriter, localData.getUsername());
						appendMessage( message );
						appendMessage("--------");
						//sendMessagesToAllClients(message);
						sendMessagesToAllClients(message, clientOutputStream);
					}
					
					if ( DataType.MESSAGE == localData.getDataType() ) {
						message = localData.getUsername() + ": " + localData.getMessage();
						appendMessage( message );
						sendMessagesToAllClients(message, clientOutputStream);
						//sendMessagesToAllClients(message);
					}
					
					if ( DataType.DISCONNECT == localData.getDataType() ) {
						message = "-------\nUser has disconnected: " + localData.getUsername() + "\n-------";
						//clientOutputStream.remove(clientHashMap.get(localData.getUsername()));
						//clientHashMap.remove(localData.getUsername());
						clientHashMap.remove(objWriter);
						clientOutputStream.remove(objWriter);
						appendMessage(message);
						//sendMessagesToAllClients(message);
						sendMessagesToAllClients(message, clientOutputStream);
					}
				}
			//}
			
		} catch (SocketException sockException) {

		} catch (Exception e) {
			appendMessage("No Conditions were met. \n");
			e.printStackTrace();
		}
	}
	
	private void sendMessagesToAllClients(String sendDataToAllMessage) {
		Data newData = new Data();
		newData.setMessage(sendDataToAllMessage);
		
		Iterator<Object> it = clientOutputStream.iterator();
		while( it.hasNext() ) {
			
			
			try {
				ObjectOutputStream localOutWriter = (ObjectOutputStream) it.next();
				localOutWriter.reset();
				localOutWriter.writeObject(newData);
				localOutWriter.flush();
			} catch (Exception e) {
				appendMessage("Message failed to send to all users");
			}
		}
	}
	
	private void appendMessage(String message) {
		messagesAreaUI.appendText(message + "\n");
	}

}
