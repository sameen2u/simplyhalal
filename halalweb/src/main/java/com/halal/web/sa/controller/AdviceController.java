package com.halal.web.sa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AdviceController {
	
	@ModelAttribute
	public void addGlobalAttributes(HttpServletRequest request, Model model, Device device){
		if(device.isMobile()){
			model.addAttribute("channel", "mobile");
		}
		if(device.isNormal()){
			model.addAttribute("channel", "desktop");
		}
		//to use base url in all the htmls
		String baseUrl = null;
		String contextPath = request.getContextPath();
		String host = request.getServerName();
		if(host.contains("localhost")){
			baseUrl = "http://"+host+":"+request.getServerPort()+contextPath;
		}
		else{
			baseUrl = "http://"+host+contextPath;
		}
		model.addAttribute("baseUrl",baseUrl);
		model.addAttribute("contextPath",contextPath);
	}

}
