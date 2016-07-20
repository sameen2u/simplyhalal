package com.halal.web.sa.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.halal.web.sa.common.HalalGlobalConstants;
import com.halal.web.sa.service.BaseService;

@Controller
public class MyAccountController extends BaseController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyAccountController.class);
	
	/*
	 * Method forward the request to the loginpage
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginPageService(HttpServletRequest request, ModelAndView modelAndView,
					@ModelAttribute("channel") String channel){
		String loginApiUrl = HalalGlobalConstants.API_SERVICE_URL;
		modelAndView.addObject("loginApiUrl", loginApiUrl);
		modelAndView.setViewName(channel+"/mainTemplate");
		modelAndView.addObject("pageType","loginpage");
		String finalHtml = getHtmlTemplate(channel+"/account/loginpage.html", modelAndView.getModel());
		modelAndView.addObject("loginHTML",finalHtml);
		return modelAndView;
	}

	@Override
	protected BaseService getAPIServiceProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void processResponse(ModelAndView modelAndView,
			Map<String, Object> globalMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getTemplateName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
