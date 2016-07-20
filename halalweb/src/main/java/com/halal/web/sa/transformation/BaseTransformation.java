package com.halal.web.sa.transformation;import com.fasterxml.jackson.databind.ObjectMapper;import com.fasterxml.jackson.databind.ObjectReader;import com.halal.web.App;import com.halal.web.sa.core.exception.ApplicationException;import java.io.IOException;import java.util.HashMap;import java.util.Map;import javax.servlet.http.HttpServletRequest;import org.slf4j.Logger;import org.slf4j.LoggerFactory;public abstract class BaseTransformation{  private static final ObjectMapper mapper = new ObjectMapper();  private static final ObjectReader reader = mapper.reader(HashMap.class);    public abstract Object transformDomain(Map<String, Object> paramMap, HttpServletRequest paramHttpServletRequest);    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTransformation.class);    public Object buildViewDomainObject(String responseJSON, HttpServletRequest request)    throws ApplicationException  {    Map<String, Object> domainMap = null;    if (responseJSON.length() > 0) {      try      {        domainMap = (Map)reader.readValue(responseJSON);      }      catch (IOException e)      {        String message = "IOException while converting json to Hashmap";        e.printStackTrace();                throw new ApplicationException(message);      }    }    Object object = transformDomain(domainMap, request);        return object;  }  }