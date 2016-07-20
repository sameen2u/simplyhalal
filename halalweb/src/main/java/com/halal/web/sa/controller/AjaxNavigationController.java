package com.halal.web.sa.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.metadata.MethodType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.halal.web.App;
import com.halal.web.sa.core.exception.ApplicationException;
import com.halal.web.sa.service.BaseService;
import com.halal.web.sa.service.SearchService;

@Controller
public class AjaxNavigationController extends BaseController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	
	@Autowired
	SearchService searchService;
	
	@RequestMapping(value="/search/ajax", method=RequestMethod.GET, produces={"text/plain"})
	public @ResponseBody String ajaxExecute(HttpServletRequest request, HttpServletResponse response, @RequestParam MultiValueMap<String, String> requestParameters,
			ModelAndView modelAndView, @ModelAttribute("channel") String channel) throws ApplicationException, IOException{
		
		request.setAttribute("channel", channel);
//		modelAndView.addObject("isSearchpage",Boolean.TRUE);
		
		super.performExecute(request, response, modelAndView);
		String finalHtml = modelAndView.getModel().get("finalHTML").toString();
		return finalHtml;
	}

	@Override
	protected BaseService getAPIServiceProvider() {
		return searchService;
	}

	@Override
	protected void processResponse(ModelAndView modelAndView,
			Map<String, Object> globalMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getTemplateName() {
		return "search/result-cell.html";
	}
	
}
