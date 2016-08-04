package com.halal.web.sa.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.halal.web.sa.core.exception.ApplicationException;
import com.halal.web.sa.service.BaseService;
import com.halal.web.sa.service.SearchService;

@Controller
public class SearchController extends BaseController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	SearchService searchService;
	
	/*
	 * This method is used to bring up the homepage
	 */
	@RequestMapping( value="/",  method=RequestMethod.GET)
	public ModelAndView mainExecute(HttpServletRequest request, HttpServletResponse response, 
			ModelAndView modelAndView, @ModelAttribute("channel") String channel) throws ApplicationException, IOException{
		modelAndView.setViewName(channel+"/mainTemplate");
		
		modelAndView.addObject("ishomepage","homepage");
		String finalHtml = getHtmlTemplate(channel+"/homepage.html", null);
		modelAndView.addObject("finalHTML",finalHtml);
		return modelAndView;
	}
	
	@RequestMapping( value="/search",  method=RequestMethod.GET)
	public ModelAndView searchKeyword(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView,
										@RequestParam(value="loc") String address, @ModelAttribute("channel") String channel) throws ApplicationException{
		//this is condition will prevent any server calls without address in case FE prevention is compromised
//		if(address == null || address.trim().isEmpty()){
//			modelAndView.setViewName("index");
//			return modelAndView;
//		}
		modelAndView.setViewName(channel+"/mainTemplate");
		request.setAttribute("channel", channel);
		modelAndView.addObject("isSearchpage",Boolean.TRUE);
		
		return super.performExecute(request, response, modelAndView);
	}

	@Override
	protected BaseService getAPIServiceProvider() {
		return searchService;
	}
	
	protected String getTemplateName() {
		return "search/searchResult.html";
	}

	@Override
	protected void processResponse(ModelAndView modelAndView, Map<String, Object> globalMap) {
		modelAndView.addObject("globalMap", globalMap);
	}
}
