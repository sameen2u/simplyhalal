package com.halal.web.sa.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		if(!StringUtils.isEmpty(address)){
			requestParam = new LinkedHashMap<String, Object>();
			address = address.replaceAll(" ", "+");
			if(address.contains(",")){
				address = address.replaceAll(",", "");
			}
			requestParam.put(HalalGlobalConstants.QUERY_PARAM_ADDRESS, address);
			
			
		
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
		if(requestParam == null && requestParam.size()<1){
			//need to implement exception handling here
			return null;
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
