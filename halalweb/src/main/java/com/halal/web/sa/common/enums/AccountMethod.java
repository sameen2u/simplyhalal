package com.halal.web.sa.common.enums;

public enum AccountMethod {

	LOGIN("login"),
	SIGNUP("signup"),
	LOGOUT("logout");
	
	private String name;
	
	private AccountMethod(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
