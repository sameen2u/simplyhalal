package com.halal.web.sa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AdviceController {
	
	@ModelAttribute
	public void addGlobalAttributes(Model model, Device device){
		if(device.isMobile()){
			model.addAttribute("channel", "mobile");
		}
		if(device.isNormal()){
			model.addAttribute("channel", "desktop");
		}
	}

}
