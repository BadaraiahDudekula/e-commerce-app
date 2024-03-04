package org.jsp.ecommerceapp.dto;

public class ResponseStructure<T> {
	private String message;
	private T model;
	private int statusCode;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getModel() {
		return model;
	}
	public void setModel(T model) {
		this.model = model;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
}
