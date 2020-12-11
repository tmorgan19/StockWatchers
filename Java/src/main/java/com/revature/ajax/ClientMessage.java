package com.revature.ajax;

public class ClientMessage {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ClientMessage(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "ClientMessage [message=" + message + "]";
	}
	
	
}
