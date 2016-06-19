package com.halal.web.sa.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.halal.web.sa.common.CommonUtil;
import com.halal.web.sa.common.HalalGlobalConstants;

@Service
public class BusinessService extends BaseService{
	
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
//		jsonObject.put("facility", request.getParameter("rest-landmark"));
		jsonObject.put("otherInfo", request.getParameter("rest-other-detail"));
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

}
