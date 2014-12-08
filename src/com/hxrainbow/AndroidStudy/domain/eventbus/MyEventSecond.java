package com.hxrainbow.AndroidStudy.domain.eventbus;

public class MyEventSecond {
	
	private String message;

	public MyEventSecond(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
