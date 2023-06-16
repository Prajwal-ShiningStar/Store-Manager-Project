package com.store.manager.payload;

public class ResponseDetails {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseDetails(String message) {
		super();
		this.message = message;
	}
	

}
