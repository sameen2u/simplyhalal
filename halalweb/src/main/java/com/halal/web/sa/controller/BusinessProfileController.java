package com.halal.web.sa.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.halal.web.sa.core.exception.ApplicationException;
import com.halal.web.sa.service.BaseService;
import com.halal.web.sa.service.BusinessProfileService;
import com.halal.web.sa.service.BusinessService;

@Controller
@RequestMapping("/b")
public class BusinessProfileController extends BaseController{
	
	@Autowired
	BusinessProfileService businessProfileService;
	
	@RequestMapping( value="/{city}/{bizNameCode}/{profileId}",  method=RequestMethod.GET)
	public ModelAndView searchKeyword(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView,
										@ModelAttribute("channel") String channel, 
										@PathVariable("city") String city,
										@PathVariable("profileId") String profileId) throws ApplicationException{
		modelAndView.setViewName(channel+"/mainTemplate");
		request.setAttribute("channel", channel);
		modelAndView.addObject("isBizProfilePage",Boolean.TRUE);
		request.setAttribute("city", city);
		request.setAttribute("profileId", profileId);
//		modelAndView.setViewName("business/profileBusiness");
		return super.performExecute(request, response, modelAndView);
	}

	@Override
	protected BaseService getAPIServiceProvider() {
		return businessProfileService;
	}

	@Override
	protected void processResponse(ModelAndView modelAndView,
			Map<String, Object> globalMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getTemplateName() {
		return "business/profileBusiness.html";
	}

}
