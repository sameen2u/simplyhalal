package com.halal.web.sa.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.halal.web.sa.common.CommonUtil;
import com.halal.web.sa.common.HalalGlobalConstants;

@Service
public class BusinessService extends BaseService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessService.class);
	
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

	@Override
	protected String buildServiceUrl(HttpServletRequest httpServletRequest, Map<String, Object> requestParam) {
		String endpointUrl = resourceBundle.getString("API_POST_RESTAURANT_ENPOINT");
		return CommonUtil.buildUrl(endpointUrl, requestParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object buildInputDataObject(HttpServletRequest request) {
		//need to correct this
		String cuisine[] = request.getParameter("rest-cuisine").split(",");
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(Arrays.asList(cuisine));
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", request.getParameter("rest-name"));
		jsonObject.put("description", request.getParameter("rest-desc"));
		jsonObject.put("cuisine", jsonArray);
		Map<String,String> address = new HashMap<String, String>();
		address.put("streetAddress", request.getParameter("rest-st-addr"));
		address.put("city", request.getParameter("rest-city"));
		address.put("pincode", request.getParameter("rest-pincode"));
		address.put("landmark", request.getParameter("rest-landmark"));
		jsonObject.put("address", address);
		jsonObject.put("phone", request.getParameter("rest-phone"));
		jsonObject.put("email", request.getParameter("rest-email"));
		jsonObject.put("ownerEmail", request.getParameter("rest-owner-phone"));
		jsonObject.put("ownerPhone", request.getParameter("rest-owner-email"));
		jsonObject.put("status", request.getParameter("rest-opening-status"));
		jsonObject.put("userEmail", request.getParameter("rest-user-email"));
		jsonObject.put("website", request.getParameter("rest-website"));
		jsonObject.put("facebookPage", request.getParameter("rest-fb-page"));
		jsonObject.put("twitterPage", request.getParameter("rest-tw-page"));
		jsonObject.put("authenticity", request.getParameter("rest-authenticity"));
		jsonObject.put("halalOfferings", request.getParameter("rest-halal-serving"));
		jsonObject.put("facility", buildFeatures(request));
		jsonObject.put("otherInfo", request.getParameter("rest-other-detail"));
		jsonObject.put("workingHours", buildBusinessHours(request));
		return jsonObject.toJSONString();
	}

	@Override
	protected Object processResponse(String jsonString,
			HttpServletRequest request,
			HttpServletResponse response) {
		return CommonUtil.buildDomainMap(jsonString);
	}

	@Override
	protected Map<String, Object> buildRequestParam(
			HttpServletRequest request) {
		request.setAttribute(HalalGlobalConstants.API_METHOD, "POST");
		return null;
	}
	
	/*
	 * this method generates business working hours in string format
	 * e.g "mon=07:00-21:00,tue=07:00-21:00,wed=07:00-21:00,thur=07:00-21:00,fri=07:00-21:00,sat=10:00-17:00,sun=closed" 
	 */
	public String buildBusinessHours(HttpServletRequest request){
		String monHours =  request.getParameter("hours-start-mon")+"-"+ request.getParameter("hours-end-mon");
		String tueHours =  request.getParameter("hours-start-tue")+"-"+ request.getParameter("hours-end-tue");
		String wedHours =  request.getParameter("hours-start-wed")+"-"+ request.getParameter("hours-end-wed");
		String thurHours =  request.getParameter("hours-start-thur")+"-"+ request.getParameter("hours-end-thur");
		String friHours =  request.getParameter("hours-start-fri")+"-"+ request.getParameter("hours-end-fri");
		String satHours =  request.getParameter("hours-start-sat")+"-"+ request.getParameter("hours-end-sat");
		String sunHours =  request.getParameter("hours-start-sun")+"-"+ request.getParameter("hours-end-sun");
		
		if(StringUtils.isNotBlank(request.getParameter("hours-closed-mon")) && request.getParameter("hours-closed-mon").equals("1")){
			monHours = "closed";
		}
		if(StringUtils.isNotBlank(request.getParameter("hours-closed-tue")) && request.getParameter("hours-closed-tue").equals("1")){
			tueHours = "closed";
		}
		if(StringUtils.isNotBlank(request.getParameter("hours-closed-wed")) && request.getParameter("hours-closed-wed").equals("1")){
			wedHours = "closed";
		}
		if(StringUtils.isNotBlank(request.getParameter("hours-closed-thur")) && request.getParameter("hours-closed-thur").equals("1")){
			thurHours = "closed";
		}
		if(StringUtils.isNotBlank(request.getParameter("hours-closed-fri")) && request.getParameter("hours-closed-fri").equals("1")){
			friHours = "closed";
		}
		if(StringUtils.isNotBlank(request.getParameter("hours-closed-sat")) && request.getParameter("hours-closed-sat").equals("1")){
			satHours = "closed";
		}
		if(StringUtils.isNotBlank(request.getParameter("hours-closed-sun")) && request.getParameter("hours-closed-sun").equals("1")){
			sunHours = "closed";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("mon="+monHours+",");
		sb.append("tue="+tueHours+",");
		sb.append("wed="+wedHours+",");
		sb.append("thur="+thurHours+",");
		sb.append("fri="+friHours+",");
		sb.append("sat="+satHours+",");
		sb.append("sun="+sunHours+"");
		
		return sb.toString();
	}
	
	/*
	 * this method generates business facilities in string format
	 * e.g "takeaway,mealCoupon,hooka,juiceCenter,creditcardAccepted,indoorSeating" 
	 */
	public String buildFeatures(HttpServletRequest request){
		StringBuilder sb = new StringBuilder();
		String[] facilities = request.getParameterValues("facilities");
		for (int i = 0; i < facilities.length; i++) {
		    sb.append(facilities[i]);
		    if(i < facilities.length-1){
		    	sb.append(",");
		    }
		}
		return sb.toString();
	}

}
