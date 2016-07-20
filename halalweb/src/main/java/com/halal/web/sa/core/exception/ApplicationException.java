package com.halal.web.sa.core.exception;

public class ApplicationException extends Exception
{
	/**
	 * 
	 */
	private Object responseBody;
	
	private static final long serialVersionUID = 1L;
	
	public ApplicationException(){
		super();
	}
	public ApplicationException(String e) {
		super(e);
	}
	
	public ApplicationException(String e, Object responseBody) {
		super(e);
		this.responseBody = responseBody;
	}
	
	public Object getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}
	
}
