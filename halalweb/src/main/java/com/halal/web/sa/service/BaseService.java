package com.halal.web.sa.service;

import com.halal.web.sa.common.CommonUtil;
import com.halal.web.sa.common.HalalGlobalConstants;
import com.halal.web.sa.common.apicore.ApiService;
import com.halal.web.sa.common.registry.ApiResponseRegistry;
import com.halal.web.sa.core.exception.ApplicationException;

import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.apache.commons.lang3.StringUtils;

public abstract class BaseService
{
  protected static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
 
  @Autowired
  ApiService apiService;
  
  public Object executeService(HttpServletRequest request, HttpServletResponse response)  throws ApplicationException {
	  Map<String, Object> requestParam = buildRequestParam(request);
	  String serviceURL = buildServiceUrl(request, requestParam);
	  String responseJson = null;
	  Object object = null;
	  if(serviceURL !=null){
		  
	  }
	  if(StringUtils.equals((String) request.getAttribute(HalalGlobalConstants.API_METHOD), "GET")){
		  Map cacheMap = ApiResponseRegistry.getInstance().getCacheMap();
	      if (cacheMap !=null && cacheMap.containsKey(serviceURL)) {
	    	  String jsonString = (String) cacheMap.get(serviceURL);
	    	  object = processResponse(responseJson, request, response);//CommonUtil.buildDomainMap(jsonString);
	    	  return object;
	      }
	      responseJson = apiService.getMethod(serviceURL, isCacheable());
	      if (StringUtils.isEmpty(responseJson)) {
	    	  throw new ApplicationException("problem here- Empty JSON response String");
	      }
	      object = processResponse(responseJson, request, response); //CommonUtil.buildDomainMap(responseJson);
//	      return object; 
	  }
	  else if (StringUtils.equals((String) request.getAttribute(HalalGlobalConstants.API_METHOD), "POST")){
	      responseJson = apiService.postMethod(serviceURL, buildInputDataObject(request), MediaType.APPLICATION_JSON, isCacheable());
	      if(!StringUtils.isEmpty(responseJson)){
	    	  object = processResponse(responseJson, request, response);
	      }
	  }
	  else
		  throw new ApplicationException("Calling incorect Method, call GET or POST method");
	return object;
  }
  
  /**
   * This method builds query parameters for the api endpoints url
   */
  protected abstract Map<String, Object> buildRequestParam(HttpServletRequest request);
  
  protected abstract String buildServiceUrl(HttpServletRequest request, Map<String, Object> requestParam);
  
  protected abstract Object buildInputDataObject(HttpServletRequest request);
  
  protected abstract Object processResponse(String paramString, HttpServletRequest request, HttpServletResponse response) throws ApplicationException;
  
  /*protected String invokeService(String serviceURL, boolean isCacheable)
    throws ApplicationException
  {
    return this.apiService.callApiService(serviceURL, isCacheable);
  }
  
  protected String invokePostService(String serviceURL, Object jsonObj, MediaType mediaType, boolean isCacheable)
  {
    return this.apiService.callApiPostService(serviceURL, jsonObj, mediaType, isCacheable);
  }
  */
  protected boolean isCacheable()
  {
    return false;
  }
}
