package com.halal.web.sa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.halal.web.sa.common.HalalGlobalConstants;

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

}
