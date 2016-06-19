package com.halal.web.sa.common.apicore;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.halal.web.sa.common.registry.ApiResponseRegistry;
import com.halal.web.sa.core.exception.ApplicationException;

@Component
public class ApiService {
	
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
						throw new ApplicationException("Problem in getting Api response, Error - "+responseEntity.getStatusCode());
					}	
				}
				catch(Exception e){
					String errorMsg = "Failed to get response from API url - "+url;
					if(isCacheable){
						
					}
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
	public String postMethod(String url, Object jsonObj, MediaType mediaType, boolean isCacheable){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);
		HttpEntity<String> httpEntity = new HttpEntity<String>((String)jsonObj, headers);
		ResponseEntity<String> responseEntity = httpConfig.getRestTemplate().exchange(url.toString(), HttpMethod.POST, httpEntity, String.class);
		return responseEntity.getBody();
	}

}
