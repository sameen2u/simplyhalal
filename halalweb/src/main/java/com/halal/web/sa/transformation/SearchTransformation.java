package com.halal.web.sa.transformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.halal.web.sa.common.CommonUtil;
import com.halal.web.sa.common.UrlUtil;

@Component
public class SearchTransformation extends BaseTransformation{

	@Override
	public Object transformDomain(Map<String, Object> domainMap, HttpServletRequest request) {
		setBusinessProfileUrl(domainMap, request);
		pagination(domainMap, request);
		return domainMap;
	}
	
	public void pagination(Map<String, Object> domainMap, HttpServletRequest request){
		String searchBaseUrl = UrlUtil.getEntireRequestURL(request);
		
		Map<String, Object> searchReport = CommonUtil.getJsonMap(domainMap, "searchReport");
//		boolean isNextPage = (Boolean) searchReport.get("nextPage");
		
		//removing page parameter from the query string
		searchBaseUrl = UrlUtil.removeUrlParam(searchBaseUrl, "page");
		
		int page = (request.getParameter("page") == null) ? 1 : Integer.parseInt(request.getParameter("page"));
		int currentPage = 1;
		
		int totalRecords = CommonUtil.convertStringToInt(searchReport.get("totalRecords"));
		int recordsPerPage = CommonUtil.convertStringToInt(searchReport.get("recordsPerPage"));
		if(page > 1){
			currentPage  = page;
		}
		
		List<Map<String,Object>> pagesList = this.getPages(currentPage, totalRecords, recordsPerPage, searchBaseUrl);
		domainMap.put("pagination", pagesList);
	}
	
	public List<Map<String,Object>> getPages(int currentPage, double totalRecords, double recordsPerPage, String searchBaseUrl){
		Map<String,Object> pageMap = null;
		int lastPage=currentPage;
		int totalPages = (int) Math.ceil(totalRecords/recordsPerPage);
		long page=0;
		List<Map<String,Object>> pageList = new ArrayList<Map<String,Object>>();
//		pageMap.put("label", ObjectUtils.toString((index+1)));
		for(int i=1; i<=totalPages; i++){
			boolean isSelectedPage = false;
			pageMap = new HashMap<String,Object>();
			String url = searchBaseUrl;
			pageMap.put("label", i);
			if(i == currentPage){
				isSelectedPage = Boolean.TRUE;
			}
			pageMap.put("label", i);
			pageMap.put("isSelectedPage", isSelectedPage);
			
			if(i == 1){
				pageMap.put("paginationUrl", searchBaseUrl);
			}
			else{
				pageMap.put("paginationUrl", UrlUtil.addUrlParam(searchBaseUrl, "page", Integer.toString(i)));
			}
			
			pageList.add(pageMap);	
		}
		
		return pageList;
	}
	
	private void setBusinessProfileUrl(Map<String, Object> domainMap, HttpServletRequest request){
		String baseUrl = request.getAttribute("baseUrl").toString();
		String businessUrl = null;
		List businesses = (List) domainMap.get("searchBusinesses");
		for(int i=0; i< businesses.size(); i++){
			Map map = (Map) businesses.get(i);
			businessUrl = baseUrl+"/b"+map.get("profileUri");
			map.put("businessUrl", businessUrl);
		}
	}

}
