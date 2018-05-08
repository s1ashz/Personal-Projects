package Network;

import java.io.ObjectInputStream;

import controller.Controller;
import data.Data;
import javafx.scene.control.TextArea;

public class IncommingReader implements Runnable {

	private ObjectInputStream outReader;
	private TextArea messagesArea;
	private Data incommingUserData;
	
	public IncommingReader(ObjectInputStream outReader, TextArea messagesArea) {
		this.outReader = outReader;
		this.messagesArea = messagesArea;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				incommingUserData = (Data) outReader.readObject();
				appendMessage( incommingUserData.getMessage() );
			}
		} catch (Exception e) {
			Controller.isConnected = false;
			Controller.changeAllFieldsState(false);
			appendMessage("Server disconnected");
		}
	}
	
	public void appendMessage(String message) {
		messagesArea.appendText(message + "\n");
	}

}
