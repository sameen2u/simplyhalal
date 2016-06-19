package com.halal.web.sa.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;

//import com.google.common.base.Charsets;
//import com.google.common.io.Files;















import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.halal.web.sa.core.exception.ApplicationException;
import com.halal.web.sa.service.BaseService;
import com.halal.web.sa.service.SearchService;
import com.samskivert.mustache.Mustache;

@Controller
public class SearchController extends BaseController{
	
	@Autowired
	SearchService searchService;
	
	@RequestMapping( value="/",  method=RequestMethod.GET)
	public ModelAndView mainExecute(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws ApplicationException, IOException{
		modelAndView.setViewName("pages/mainTemplate");
		modelAndView.addObject("pageType","homepage");
		
		String finalHtml = getHtmlTemplate("desktop/homepage.html", null);
		modelAndView.addObject("finalHTML",finalHtml);
//		return super.performExecute(request, response, modelAndView);
		return modelAndView;
	}
	
	@RequestMapping( value="/searchBiz",  method=RequestMethod.GET)
	public ModelAndView searchKeyword(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView,
										@RequestParam(value="loc") String address) throws ApplicationException{
		//this is condition will prevent any server calls without address in case FE prevention is compromised
		if(address == null || address.trim().isEmpty()){
			modelAndView.setViewName("index");
			return modelAndView;
		}
		modelAndView.setViewName("search/searchResult");
		return super.performExecute(request, response, modelAndView);
	}

	@Override
	protected BaseService getAPIServiceProvider() {
		return searchService;
	}

	@Override
	protected void processResponse(ModelAndView modelAndView, Map<String, Object> globalMap) {
		modelAndView.addObject("globalMap", globalMap);
	}
}
