package com.halal.web.sa.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halal.web.sa.common.CommonUtil;
import com.halal.web.sa.common.HalalGlobalConstants;
import com.halal.web.sa.core.exception.ApplicationException;
import com.halal.web.sa.transformation.BusinessProfileTransformation;

@Service
public class BusinessProfileService extends BaseService{
	
	@Autowired
	BusinessProfileTransformation businessProfileTransformation;

	@Override
	protected Map<String, Object> buildRequestParam(HttpServletRequest request) throws ApplicationException {
		if(!StringUtils.isNumeric((String) request.getAttribute("profileId"))){
			throw new ApplicationException("Profile id is not numeric");
		}
		request.setAttribute(HalalGlobalConstants.API_METHOD, "GET");
		Map<String, Object> requestparam = new HashMap<String, Object>();
		requestparam.put("city", request.getAttribute("city"));
		requestparam.put("profileId", request.getAttribute("profileId"));
		return requestparam;
	}

	@Override
	protected String buildServiceUrl(HttpServletRequest request,
			Map<String, Object> requestParam) {
		if(requestParam == null && requestParam.size()<1){
			//# need to Exception handeling
			return null;
		}
		String city = requestParam.get("city").toString();
		String profileId = requestParam.get("profileId").toString();
		String endpointUrl = resourceBundle.getString("API_SEARCH_BUSINESS_PROFILE_ENPOINT")+"/"+city+"/"+profileId;
		return CommonUtil.buildUrl(endpointUrl, requestParam);
	}

	@Override
	protected Object buildInputDataObject(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object processResponse(String jsonString,
			HttpServletRequest request, HttpServletResponse response)
			throws ApplicationException {
		Object domainObject = businessProfileTransformation.buildViewDomainObject(jsonString, request);
		return domainObject;
	}

}
