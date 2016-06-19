package com.halal.web.sa.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * This class holds all Cookie Util 
 * @author Sameen
 */
public class CookieUtil {
	
	public static final String COOKIE_SB_USERNAME ="SB_USERNAME";
	public static final String COOKIE_SESSION_TOKEN ="SESSION_TOKEN";
		
	// Check for cookie existence
	public static boolean checkCookie(HttpServletRequest request, String cookieName, String cookieValue) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)	&& cookie.getValue().toUpperCase().equals(cookieValue)) {
					return true;
			 	}
			}
		}
		return false;
	}
	
	// Get Cookie Value
	public static String getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					return cookie.getValue();
				}
			}
		}
		return "";
	}
	
	/**
	 * Set cookie 
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param expire
	 */
	public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, int expire) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setPath(HalalGlobalConstants.COOKIE_PATH);
		cookie.setMaxAge(expire);
		response.addCookie(cookie);
	}
}
