package controller;

import Network.NetworkConnectionImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

	@FXML
	private Button startServerBtn;
	
	@FXML
	private Button stopServerBtn;
	
	@FXML
	private Button usersBtn;
	
	@FXML
	private Button clearBtn;
	
	@FXML
	private TextArea messagesArea;
	
	@FXML
	private TextField portInput;
	
	NetworkConnectionImpl conn;
	
	@FXML
	public void initialize() {
		setPortInputToNumericalOnly();
	}
	
	public void startServerClick() {
		int port = Integer.valueOf( portInput.getText() );
		conn = new NetworkConnectionImpl(port, messagesArea);
		conn.startServer();
		changeAllFieldsState(true);
	}
	
	public void stopServerClick() {
		conn.stopServer();
		changeAllFieldsState(false);
	}
	
	public void clearClick() {
		messagesArea.clear();
	}
	
	public void usersClick() {
		conn.checkOnlineUsers();
	}
	
	
	public void appendMessage(String message) {
		System.out.println(messagesArea);
		messagesArea.appendText(message + "\n");
	}
	
	private void changeAllFieldsState(boolean state) {
		startServerBtn.setDisable(state);
		stopServerBtn.setDisable(!state);
	}
	
	private void setPortInputToNumericalOnly() {
		portInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	portInput.setText(newValue.replaceAll("[^\\d]", ""));
		        }

		        if (newValue.length() > 5) {
		        	portInput.setText(oldValue);
		        }
		    }
		});
	}
}
