package com.halal.web.sa.common;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class UrlUtil {
	
	/**
	 * Return the entire request URL including query string from request object
	 * @param request Request object
	 * @return request URL
	 */
	public static String getEntireRequestURL(final ServletRequest request) {

		if (request != null && request instanceof HttpServletRequest) {
			final HttpServletRequest httpRequest = (HttpServletRequest)request;
			final String queryStr = httpRequest.getQueryString();
			final StringBuffer buf = httpRequest.getRequestURL();
			if (StringUtils.isNotBlank(queryStr)) {
				buf.append("?");
				buf.append(queryStr);
			}
			return buf.toString();
		}
		
		return null;
	}	
	
	public static String addUrlParam(String url, String paramName, String paramValue) {
		if (url == null || paramName == null || paramValue == null || url.contains(paramName + "="))
			return url;
		
		StringBuilder outputUrl = new StringBuilder(url);
		if (url.indexOf("?") > -1){
			outputUrl.append("&" + paramName + "=" + paramValue);
		} else {
			outputUrl.append("?" + paramName + "=" + paramValue);
		}
		return outputUrl.toString();
	}
	
	
	//	String fullUrl = ".../?def=true&searchType=text&abc=true";
	//    String exptUrl = ".../?def=true&abc=true";
	//    assertEquals(exptUrl, removeUrlParam(fullUrl, "searchType=text"));
	public static String removeUrlParam(String fullUrl, String paramToRemoveWithValue)
	{
		fullUrl = fullUrl.replaceAll("[&?]"+paramToRemoveWithValue+".*?(?=&|\\?|$)", "");
		if(fullUrl.contains("?")){
			return fullUrl;
		}
		else{
			fullUrl = fullUrl.replaceFirst("&", "?");
		}
		return fullUrl;
	}
	
	
	//Cleanup unwanted characters from Url
	public static String cleanUpUrl(String url) {
		String output = url;
		if(StringUtils.isNotBlank(url)) {
			output = output.replaceAll("#", "");
		}
		
		return output;
	}
}