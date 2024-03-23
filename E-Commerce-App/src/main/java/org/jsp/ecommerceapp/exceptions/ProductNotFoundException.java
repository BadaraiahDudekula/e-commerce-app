package org.jsp.ecommerceapp.exceptions;

public class ProductNotFoundException  extends RuntimeException{
	
	public ProductNotFoundException(String message) {
		super(message);
	}

}
