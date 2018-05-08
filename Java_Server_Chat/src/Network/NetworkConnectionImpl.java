package Network;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.scene.control.TextArea;

public class NetworkConnectionImpl extends NetworkUtils implements NetworkConnection {

	private int port;
	private TextArea messagesAreaUI;
	ServerStart serverStart;
	
	private ArrayList<Object> clientOutputStream = new ArrayList<>();
	private HashMap<Object, String> clientHashMap = new HashMap<>();
	private ArrayList<String> users = new ArrayList<>();
	
	
	public NetworkConnectionImpl(int port, TextArea messagesAreaUI) {
		this.port = port;
		this.messagesAreaUI = messagesAreaUI;
	}
	
	@Override
	public void startServer() {
		Thread server = new Thread( serverStart = new ServerStart(port, users, clientOutputStream, clientHashMap, messagesAreaUI) );
		server.start();
	}

	@Override
	public void checkOnlineUsers() {
		
		Iterator mapIT = clientHashMap.entrySet().iterator();
		
		appendMessage("----");
		appendMessage("Users: " + clientHashMap.size());
		while ( mapIT.hasNext() ) {
			
			String user = mapIT.next().toString();
			String[] users = user.split("=");
			appendMessage(users[1]);
		}
		appendMessage("----");
	}

	@Override
	public void stopServer() {
		
		try {
			String message = "Server is Disconnecting";
			sendMessagesToAllClients(message, clientOutputStream);
			
            Thread.sleep(5000);
            for ( Socket sock : serverStart.getSocketsArray() ) {
            	sock.close();
            }
            serverStart.getServerSock().close();
            appendMessage("server stopped");
        } catch(InterruptedException ex) {
        	Thread.currentThread().interrupt();
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (Exception e) {
        	appendMessage("Message failed to send to all users");
		}
	}
	
	private void appendMessage(String message) {
		messagesAreaUI.appendText(message + "\n");
	}

}
