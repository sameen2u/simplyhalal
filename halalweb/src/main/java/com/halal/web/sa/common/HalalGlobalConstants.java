package com.halal.web.sa.common;

import java.util.ResourceBundle;

public class HalalGlobalConstants {
	
	private final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
	
	public static final String COOKIE_PATH ="/";
	
	public static final String QUERY_PARAM_ADDRESS ="address";
	public static final String QUERY_PARAM_KEYWORD ="keyword";
	public static final String QUERY_PARAM_DISTANCE ="distance";
	public static final String QUERY_PARAM_PAGE ="page";
	
	public static final String API_METHOD ="apiMethod";

}
