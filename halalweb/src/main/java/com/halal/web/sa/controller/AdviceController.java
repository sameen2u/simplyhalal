package com.halal.web.sa.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mobile.device.Device;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.halal.web.sa.common.HalalGlobalConstants;
import com.halal.web.sa.core.exception.ApplicationException;
import com.halal.web.sa.service.BaseService;

@ControllerAdvice
public class AdviceController extends BaseController{
	
	@ModelAttribute
	public void addGlobalAttributes(HttpServletRequest request, Model model, Device device){
		if(device.isMobile()){
			model.addAttribute("channel", "mobile");
			request.setAttribute("channel", "mobile");
		}
		if(device.isNormal()){
			model.addAttribute("channel", "desktop");
			request.setAttribute("channel", "desktop");
		}
		//to use base url in all the htmls
		String baseUrl = null;
		String protocol = "http://";
		if(request.isSecure()){
			protocol = "https://";
		}
		String contextPath = request.getContextPath();
		String host = request.getServerName();
		if(host.contains("localhost")){
			baseUrl = protocol+host+":"+request.getServerPort()+contextPath;
		}
		else{
			baseUrl = protocol+host+contextPath;
		}
		model.addAttribute("baseUrl",baseUrl);
		request.setAttribute("baseUrl",baseUrl);
		model.addAttribute("contextPath",contextPath);
		request.setAttribute("contextPath",contextPath);
		
	}
	
	@ExceptionHandler({ApplicationException.class, Exception.class})
	public ModelAndView handleException(HttpServletRequest request,	HttpServletResponse response, Exception e){
		System.out.println(e.getLocalizedMessage());
		String channel = request.getAttribute("channel").toString();
		if (StringUtils.isBlank(channel)){
			channel = "desktop";
		}
		ModelAndView modelAndView = new ModelAndView();
		
		//to use base url in all the htmls
		String baseUrl = null;
		String protocol = "http://";
		if(request.isSecure()){
			protocol = "https://";
		}
		String contextPath = request.getContextPath();
		String host = request.getServerName();
		if(host.contains("localhost")){
			baseUrl = protocol+host+":"+request.getServerPort()+contextPath;
		}
		else{
			baseUrl = protocol+host+contextPath;
		}
		modelAndView.addObject("contextPath",contextPath);
		modelAndView.addObject("baseUrl",baseUrl);
		
		modelAndView.setViewName(channel+"/mainTemplate");
		modelAndView.addObject("isErrorpage",Boolean.TRUE);
		Map globalMap = new HashMap();
		globalMap.put("error", e.getMessage());
		if(e instanceof ApplicationException){
			ApplicationException exception = (ApplicationException) e;
			globalMap.put("errorResponse", exception.getResponseBody());
		}
		else{
			globalMap.put("errorResponse", e.getLocalizedMessage()+", "+e.getCause());
		}
		String finalHtml = getHtmlTemplate("desktop/errorPage.html", globalMap);
		modelAndView.addObject("finalHTML",finalHtml);
		return modelAndView;
	}
	
	private void populateModelAndView(HttpServletRequest request, HttpServletResponse response, Exception e, ModelAndView mav ){
		
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
