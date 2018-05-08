package Network;

import data.Data;

public interface NetworkConnection {

	public void connecServer(Data userData);
	public void sendMessage(Data userData);
	public void disconnectServer();
	
	
}
