package com.halal.web.sa.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halal.web.App;
import com.halal.web.sa.common.CommonUtil;
import com.halal.web.sa.common.HalalGlobalConstants;
import com.halal.web.sa.core.exception.ApplicationException;
import com.halal.web.sa.transformation.SearchTransformation;

@Service
public class SearchService extends BaseService{
	
	@Autowired
	SearchTransformation searchTransformation;
	
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
	public static final int DEFAULT_SEARCH_DISTANCE =5;
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

	@Override
	protected Map<String, Object> buildRequestParam(HttpServletRequest request) {
		request.setAttribute(HalalGlobalConstants.API_METHOD, "GET");
		Map<String, Object> requestParam=null;
		String address = request.getParameter("loc");
		String lattitude = request.getParameter("lattitude");
		String longitude = request.getParameter("longitude");
		String keyword = request.getParameter("keyword");
		String distance = request.getParameter("distance");
		String page = request.getParameter("page");
		if(!StringUtils.isEmpty(address) || !StringUtils.isEmpty(lattitude) && !StringUtils.isEmpty(longitude)){
			requestParam = new LinkedHashMap<String, Object>();
			address = address.replaceAll(" ", "+");
			if(address.contains(",")){
				address = address.replaceAll(",", "");
			}
			requestParam.put(HalalGlobalConstants.QUERY_PARAM_ADDRESS, address);
			
//			requestParam.put("lattitude", lattitude);
//			requestParam.put("longitude", longitude);
		
			if(!StringUtils.isEmpty(keyword)){
				requestParam.put(HalalGlobalConstants.QUERY_PARAM_KEYWORD, keyword);
			}
			if(distance != null && Integer.parseInt(distance) >5){
				requestParam.put(HalalGlobalConstants.QUERY_PARAM_DISTANCE, distance);
			}
			else
				requestParam.put(HalalGlobalConstants.QUERY_PARAM_DISTANCE, DEFAULT_SEARCH_DISTANCE);
			if( page != null && Integer.parseInt(page) > 1){
				requestParam.put(HalalGlobalConstants.QUERY_PARAM_PAGE, page);
			}
		}
		return requestParam;
	}

	@Override
	protected String buildServiceUrl(HttpServletRequest request, Map<String, Object> requestParam) {
		//if manually no parameters passed in query param then search for Camp Pune, address by default 
		if(requestParam == null){
			//need to implement exception handling here
			requestParam = new HashMap<String, Object>();
			requestParam.put("lattitude", "18.5122306"); 
			requestParam.put("longitude", "73.88600999999994"); 
		}
		String endpointUrl = resourceBundle.getString("API_SEARCH_BUSINESS_ENPOINT");
		return CommonUtil.buildUrl(endpointUrl, requestParam);
	}

	@Override
	protected Object buildInputDataObject(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object processResponse(String jsonString,	HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
		Object domainObject = searchTransformation.buildViewDomainObject(jsonString, request);
		return domainObject;
	}

	
}
