package com.halal.web.sa.common.apicore;


import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import com.halal.web.sa.common.CommonUtil;
import com.halal.web.sa.common.registry.ApiResponseRegistry;
import com.halal.web.sa.core.exception.ApplicationException;

@Component
public class ApiService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiService.class);
	
	@Autowired
	HttpConfig httpConfig;
	
	public String getMethod(String url, boolean isCacheable) throws ApplicationException{
		if(!StringUtils.isBlank(url)){
				try{
					ResponseEntity<String> responseEntity = httpConfig.getRestTemplate().getForEntity(url, String.class);
					if(responseEntity.getStatusCode() == HttpStatus.OK){
						if(isCacheable){
							ApiResponseRegistry.getInstance().putResponse(url, responseEntity.getBody());
						}							
						return responseEntity.getBody();
					}
					else{
						Map responseMap = (Map) CommonUtil.buildDomainMap(responseEntity.getBody());
		            	LOGGER.error("Halal API Request failed for URL: "+ url);
		            	LOGGER.error("Halal API Request failed with error response: "+ responseEntity.getBody());
		            	String errorMsg = "Halal API Request failed with status : "+responseEntity.getStatusCode();
		                throw new ApplicationException(errorMsg, responseMap);
					}	
				}
				catch (HttpClientErrorException e) {
//					LOGGER.error(e.getMessage());
					throw new ApplicationException("An error ocurred during HTTP communication"+e.getMessage());
				} catch (RestClientException e) {
//					LOGGER.error(e.getMessage());
					throw new ApplicationException("Couldn't connect to Api, rest client exception");
				}
			}
			
		//change this with Exception catching logic later(#need to change)
		return null;
	}
	
	/**
	 * This is main post method api call 
	 * @param url
	 * @param isCacheable
	 * @return
	 * @throws ApplicationException
	 */
	public String postMethod(String url, Object jsonObj, MediaType mediaType, boolean isCacheable) throws ApplicationException{
		String responsString = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);
		HttpEntity<String> httpEntity = new HttpEntity<String>((String)jsonObj, headers);
		try {
			ResponseEntity<String> responseEntity = httpConfig.getRestTemplate().exchange(url.toString(), HttpMethod.POST, httpEntity, String.class);
//			responsString = responseEntity.getBody();
			 if ((responseEntity.getStatusCode() == HttpStatus.OK) || (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND)) {
				 responsString = responseEntity.getBody();
	            } else {
	            	Map responseMap = (Map) CommonUtil.buildDomainMap(responseEntity.getBody());
	            	LOGGER.error("Halal API Request failed for URL: "+ url);
	            	LOGGER.error("Halal API Request failed with error response: "+ responseEntity.getBody());
	            	String errorMsg = "Halal API Responded with Error status : "+responseEntity.getStatusCode();
	                throw new ApplicationException(errorMsg, responseMap);
	            }
		}
		catch (HttpClientErrorException e) {
//			LOGGER.error(e.getMessage());
			throw new ApplicationException("An error ocurred during HTTP communication"+e.getMessage());
		} catch (RestClientException e) {
//			LOGGER.error(e.getMessage());
			throw new ApplicationException("Couldn't connect to Api, rest client exception", e.getMessage());
		}
		return responsString;
	}

}
