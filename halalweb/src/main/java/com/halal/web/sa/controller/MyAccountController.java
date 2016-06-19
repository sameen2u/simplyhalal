package com.halal.web.sa.controller;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.halal.web.sa.service.BaseService;

@Controller
public class MyAccountController extends BaseController{
	
	/*
	 * Method forward the request to the loginpage
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginPageService(HttpServletRequest request, ModelAndView modelAndView){
		modelAndView.setViewName("pages/mainTemplate");
		modelAndView.addObject("pageType","loginpage");
		String finalHtml = getHtmlTemplate("desktop/account/loginpage.html", null);
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
	
}
