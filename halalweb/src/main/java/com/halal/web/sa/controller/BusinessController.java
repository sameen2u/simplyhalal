package com.halal.web.sa.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.halal.web.sa.core.exception.ApplicationException;
import com.halal.web.sa.service.BaseService;
import com.halal.web.sa.service.BusinessService;

@Controller
public class BusinessController extends BaseController{
	
	@Autowired
	BusinessService businessService;
	
	/*
	 * Method forward the request to the add business page
	 */
	@RequestMapping(value="/addBusiness", method=RequestMethod.GET)
	public ModelAndView loginPageService(HttpServletRequest request, ModelAndView modelAndView){
		modelAndView.setViewName("pages/mainTemplate");
		modelAndView.addObject("pageType","businesspage");
		String finalHtml = getHtmlTemplate("desktop/business/addBusiness.html", null);
		modelAndView.addObject("businessHTML",finalHtml);
		return modelAndView;
	}
	
	@RequestMapping(value="/biz/add", method=RequestMethod.POST)
	public ModelAndView addRestaurant(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws ApplicationException{
		modelAndView.setViewName("pages/mainTemplate");
		modelAndView.addObject("pageType","businesspage");
		return super.performExecute(request, response, modelAndView);
	}
	
	public BaseService getAPIServiceProvider(){
		return businessService;
	}	
	
	@Override
	protected void processResponse(ModelAndView modelAndView,
			Map<String, Object> globalMap) {
		modelAndView.addObject("globalMap", globalMap);
		
	}
}
