package org.jsp.ecommerceapp.exceptions;

public class IdNotFoundException extends RuntimeException{
	
	@Override
	public String getMessage() {
		return "Invalid Id";
	}

}
