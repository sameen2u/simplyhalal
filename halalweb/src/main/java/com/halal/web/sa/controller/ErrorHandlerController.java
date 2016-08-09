package com.halal.web.sa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.halal.web.sa.core.exception.ResourceNotFoundException;
import com.halal.web.sa.service.BaseService;

@Controller
public class ErrorHandlerController extends BaseController implements ErrorController {
	
	 private static final String PATH = "/error";
	 private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlerController.class);
	    private static final String FORWARD_SERVLET_PATH = "javax.servlet.forward.servlet_path";
	    
	    @RequestMapping(value = PATH)
	    public ModelAndView errorExecute(HttpServletRequest request, Exception e, ModelAndView modelAndView) {   
	    	
	    	modelAndView.setViewName("desktop/mainTemplate");
			modelAndView.addObject("isErrorpage",Boolean.TRUE);
			Map globalMap = new HashMap();
			globalMap.put("error", "This is default error page.");
			String finalHtml = getHtmlTemplate("desktop/errorPage.html", globalMap);
			modelAndView.addObject("finalHTML",finalHtml);
			return modelAndView;
	    }
	    
	    public String getErrorPath() {
	        return PATH;
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
