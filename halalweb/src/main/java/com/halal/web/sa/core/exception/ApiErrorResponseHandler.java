package com.halal.web.sa.core.exception;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class ApiErrorResponseHandler implements ResponseErrorHandler{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiErrorResponseHandler.class);

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		LOGGER.info(response.getStatusText());
		return false;
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		// TODO Auto-generated method stub
		LOGGER.info(response.getStatusText());
	}

}
