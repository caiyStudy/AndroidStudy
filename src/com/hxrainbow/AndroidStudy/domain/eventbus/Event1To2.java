package com.hxrainbow.AndroidStudy.domain.eventbus;

public class Event1To2 {
	
	private String message;

	public Event1To2(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Event1To2 [message=" + message + "]";
	}
}
