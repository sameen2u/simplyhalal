package com.halal.web.sa.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

public class CommonUtil {
	
	private static final Logger LOGGER = Logger.getLogger(CommonUtil.class);
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("Environment");
	private static final String HOST_NAME = "API_HOST";
	private static final String PORT_NUM = "API_PORT";
	private static final ObjectMapper mapper = new ObjectMapper();
	private static final ObjectReader reader = mapper.reader(HashMap.class);
	
	public static String buildUrl(String endPointUrl, Map<String, Object> requestProperty){
		String host = resourceBundle.getString(HOST_NAME);
		String port = resourceBundle.getString(PORT_NUM);
		String serviceUrl;
		serviceUrl = host+":"+port+"/"+endPointUrl;
		if(requestProperty !=null && !requestProperty.isEmpty()){
			return serviceUrl+"?"+constructQuery(requestProperty);
		}
		return serviceUrl;
	}
	
	public static String constructQuery(Map<String, Object> requestProperty) {
		StringBuilder query = new StringBuilder();
		for (Map.Entry<String, Object> entry : requestProperty.entrySet()) {
			if (entry.getValue() != null) {
				query.append(entry.getKey());
				query.append("=");
				query.append(entry.getValue());
				query.append("&");
			}
		}
		// Removing unwanted & char at the end of the url String.
		return StringUtils.removeEnd(query.toString(), "&");
	}
	
	/**
	 * Get the JSON List object from Json map object
	 * @param jsonMap
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,Object>> getJsonList(Map<String,Object> jsonMap,String key){
		if(null!= jsonMap && jsonMap.get(key) != null && !jsonMap.get(key).equals("")){
			return (List<Map<String, Object>>)jsonMap.get(key);
		}else{
			return null;
		}
	}
	
	public static Map<String,Object> getJsonMap(Map<String,Object> jsonMap, String key){
		if (jsonMap == null || key == null)
			return null;
		
		try {
			if (key.contains(".")) {
				String newKey = key.substring(0, key.indexOf("."));
				String remainingKey = key.substring(key.indexOf(".") + 1, key.length());
				return getJsonMap(getJsonMap(jsonMap, newKey), remainingKey);
			} else {
				return (Map<String, Object>)jsonMap.get(key);
			}
		} catch (ClassCastException cce) {return null;}
	}
	
	public static Object buildDomainMap(String responseString) {
		Map<String,Object> domainMap = null;
		if(responseString.length() > 0){
			try {
				domainMap = reader.readValue(responseString);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return domainMap;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> buildMapFromObject(Object object){
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(object, Map.class);
	}
	
	/**
	 * This will Convert the String decimal value double value. if any errors, return 0.0 by defaults
	 * @param str
	 * @return
	 */
	public static double convertStringToDecimal(String str){
		double val=0.0;
		if(StringUtils.isBlank(str)){
			return val;
		}
		try{
			val = Double.valueOf(str); 
		}catch(Exception e){
			LOGGER.error("convertStringToDecimal. Error ["+str+"] while converting to Decimal.."+e);
		}
		return val;
	}
	/**
	 * This will Convert the String integer value double value. if any errors, return 0 by defaults
	 * @param str
	 * @return
	 */
	public static int convertStringToInt(String str){
		int val=0;
		if(StringUtils.isBlank(str)){
			return val;
		}
		try{
			val = Integer.valueOf(str); 
		}catch(Exception e){
			LOGGER.error("convertStringToInt. Error ["+str+"] while converting to Integer.."+e);
		}
		return val;
	}
	
	public static int convertStringToInt(Object str){
		int val=0;
		try{
			if(null!=str){
				val = Integer.valueOf(str.toString()); 
			}
		}catch(Exception e){
			LOGGER.error("convertStringToInt. Error ["+str+"] while converting to Integer.."+e);
		}
		return val;
	}
	
	/**
	 * This will Convert the String decimal value double value. if any errors, return 0.0 by defaults
	 * @param str
	 * @return
	 */
	public static double convertStringToDecimal(Object str){
		double val=0.0;
		try{
			if(null!=str){
				val = Double.valueOf(str.toString()); 
			}
		}catch(Exception e){
			LOGGER.error("convertStringToDecimal. Error ["+str+"] while converting to Decimal.."+e);
		}
		return val;
	}
	
	/**
	 * This will Convert the String Log value double value. if any errors, return 0.0 by defaults
	 * @param str
	 * @return
	 */
	public static long convertStringToLong(Object str){
		long val=0;
		try{
			if(null!=str){
				val = Long.valueOf(str.toString()); 
			}
		}catch(Exception e){
			LOGGER.error("convertStringToLong. Error ["+str+"] while converting to Long.."+e);
		}
		return val;
	}
	
	/*	
	public static String encriptString(String inputString){
		byte[]   bytesEncoded = Base64.encodeBase64(inputString.getBytes());
		return new String(bytesEncoded);
	}
	
	public static String decryptString(String inputString){
		byte[] valueDecoded= Base64.decodeBase64(inputString);
		return new String(valueDecoded);
	}
	
	public static boolean isSessionValid(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		String sessionToken= CommonUtil.decryptString(CookieUtil.getCookie(request, "SESSION_TOKEN"));
		if(session!=null){
			if(StringUtils.equals(session.getId(), sessionToken)){
				return true;
			}
		}
//		response.sendRedirect("/Browse/servlet/account/login/loginPageRedirect");
		return false;
	}*/
}
