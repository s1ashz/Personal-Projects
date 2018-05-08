package data;

import java.io.Serializable;

public class Data implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String message;
	private DataType dataType;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public DataType getDataType() {
		return dataType;
	}
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

}
