package controller;

import Network.NetworkConnectionImpl;
import data.Data;
import data.DataType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

	@FXML
	private TextField addressInput;
	
	@FXML
	private TextField portInput;
	
	@FXML
	private TextField usernameInput;
	
	@FXML
	private TextArea messagesArea;
	
	@FXML
	private TextField messageInput;

	@FXML
	private Button connectButton;
	
	@FXML
	private Button disconnectButton;
	
	@FXML
	private Button clearBtn;
	
	private static TextField addressInputStatic;
	private static TextField portInputStatic;
	private static TextField usernameInputStatic;
	private static TextField messageInputStatic;
	private static Button connectButtonStatic;
	private static Button disconnectButtonStatic;
	
	private NetworkConnectionImpl conn;
	private Data userData;
	
	private String address;
	private String username;
	private int port;
	private String messageInputString;
	public static boolean isConnected;
	
	public void initialize( ) {
		isConnected = false;
		userData = new Data();
		createStaticFields();
		setPortInputToNumericalOnly();
	}
	
	private void createStaticFields() {
		addressInputStatic= this.addressInput;
		portInputStatic= this.portInput;
		usernameInputStatic = this.usernameInput;
		messageInputStatic = this.messageInput;
		connectButtonStatic = this.connectButton;
		disconnectButtonStatic = this.disconnectButton;
	}

	public void connectBtn() {
		updateFields();
		updateData(DataType.CONNECT);
		conn = new NetworkConnectionImpl(address, port, messagesArea);
		conn.connecServer(userData);
		if ( isConnected ) {
			changeAllFieldsState(true);
		}
	}

	public void disconnectBtn() {
		changeAllFieldsState(false);
		updateData(DataType.DISCONNECT);
		conn.sendMessage(userData);
		conn.disconnectServer();
	}
	
	public void clearBtnClick() {
		messagesArea.clear();
	}
	
	public void sendBtn() {
		if ( !messageInput.getText().trim().isEmpty() ) {
			sendMessageToServer();
		}
	}

	public void sendMessage() {
		if ( !messageInput.getText().trim().isEmpty() ) {
			sendMessageToServer();
		}
	}
	
	public void appendMessage(String message) {
		messagesArea.appendText(message + "\n");
	}
	
	private void sendMessageToServer() {
		updateData( DataType.MESSAGE );
		try {
			conn.sendMessage(userData);
			messageInput.clear();
		} catch (Exception e) {
			appendMessage("Message failed to send....");
		}
	}
	
	private void updateData(DataType sendDatatype) {
		userData.setUsername(username);
		userData.setMessage(messageInput.getText());
		userData.setDataType(sendDatatype);
	}

	private void updateFields() {
		address = addressInput.getText();
		username = usernameInput.getText();
		messageInputString = null;
		
		//ADICIONAR VALIDATION -------------------------------------
		port = Integer.valueOf( portInput.getText() );		
	}
	
	public static void changeAllFieldsState(boolean state) {
		addressInputStatic.setDisable(state);
		portInputStatic.setDisable(state);
		usernameInputStatic.setDisable(state);
		connectButtonStatic.setDisable(state);
		disconnectButtonStatic.setDisable(!state);
	}
	
	private void setPortInputToNumericalOnly() {
		portInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	portInput.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	}

}
