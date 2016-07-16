package com.halal.web.sa.transformation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class BusinessProfileTransformation extends BaseTransformation{

	@Override
	public Object transformDomain(Map<String, Object> paramMap, HttpServletRequest paramHttpServletRequest) {
		//need to implement massage logic 
		return paramMap;
	}

}
