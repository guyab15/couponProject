package spring.rest;

import com.google.common.base.MoreObjects;

public class UrlMessage {

	private String message;

	public UrlMessage() {}
	
	public UrlMessage(String message) {
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
		return MoreObjects.toStringHelper(this)
				.add("message", this.message)
				.toString();
	}
	
	
	
	
	
	
}
