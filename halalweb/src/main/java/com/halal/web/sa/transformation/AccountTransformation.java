package com.halal.web.sa.transformation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class AccountTransformation extends BaseTransformation{

	
	public Object transformDomain(Map<String, Object> domainObject,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return domainObject;
	}
}
