package Network;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import data.Data;

public abstract class NetworkUtils {

	
	protected void sendMessagesToAllClients(String sendDataToAllMessage, ArrayList<Object> clientOutputStream) throws Exception {
		Data newData = new Data();
		newData.setMessage(sendDataToAllMessage);
		
		Iterator<Object> it = clientOutputStream.iterator();
		while( it.hasNext() ) {
			
			
				ObjectOutputStream localOutWriter = (ObjectOutputStream) it.next();
				localOutWriter.reset();
				localOutWriter.writeObject(newData);
				localOutWriter.flush();
				
		}
	}
	
	
	
	
	
}
