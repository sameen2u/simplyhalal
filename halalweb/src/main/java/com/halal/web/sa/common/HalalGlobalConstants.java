package com.halal.web.sa.common;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class HalalGlobalConstants {
	
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
	
	public static String API_HOST = resourceBundle.getString("API_HOST");
	public static String API_ENDPOINT = resourceBundle.getString("API_ENDPOINT");
	
	@Value("${API_LOGIN_ENPOINT}")
	static String loginApiEndpoint;
	
	public static final String COOKIE_PATH ="/";
	
	public static final String QUERY_PARAM_ADDRESS ="address";
	public static final String QUERY_PARAM_KEYWORD ="keyword";
	public static final String QUERY_PARAM_DISTANCE ="distance";
	public static final String QUERY_PARAM_PAGE ="page";
	
	public static final String API_METHOD ="apiMethod";
	
	public static final String API_SERVICE_URL =API_HOST+"/"+API_ENDPOINT;

}
