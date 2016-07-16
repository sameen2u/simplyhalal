package com.halal.web.sa.transformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.halal.web.sa.common.CommonUtil;
import com.halal.web.sa.common.UrlUtil;

@Component
public class SearchTransformation extends BaseTransformation{

	@Override
	public Object transformDomain(Map<String, Object> globalMap, HttpServletRequest request) {
		setBusinessProfileUrl(globalMap, request);
		setFilters(globalMap, request);
		pagination(globalMap, request);
		return globalMap;
	}
	
	/*
	 * Main pagination method 
	 */
	public void pagination(Map<String, Object> globalMap, HttpServletRequest request){
		String searchBaseUrl = buildSearchUrl(request);
		
		Map<String, Object> searchReport = CommonUtil.getJsonMap(globalMap, "searchReport");
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
		globalMap.put("pagination", pagesList);
	}
	
	/*
	 * This method generates the pagination urls for all the pages and returns the list of pages map with url, label and isSelectedPage flag
	 */
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
	
	/*
	 * This method sets businessprofile url in the list of business in global map
	 */
	private void setBusinessProfileUrl(Map<String, Object> globalMap, HttpServletRequest request){
		String baseUrl = request.getAttribute("baseUrl").toString();
		String businessUrl = null;
		List businesses = (List) globalMap.get("searchBusinesses");
		for(int i=0; i< businesses.size(); i++){
			Map map = (Map) businesses.get(i);
			businessUrl = baseUrl+"/b"+map.get("profileUri");
			map.put("businessUrl", businessUrl);
		}
	}
	
	/*
	 * This method construct search urls which are used for paginations
	 */
	private String buildSearchUrl(HttpServletRequest request){
		final String queryStr = request.getQueryString();
		String str = request.getAttribute("baseUrl").toString();
		if (StringUtils.isNotBlank(queryStr)) {
			str = str+"/search?"+queryStr;
		}
		return str;
	}
	
	private void setFilters(Map<String, Object> globalMap, HttpServletRequest request){
		//setting distance filter
		String distanceRadioValue = request.getParameter("distance-filter");
		String distance = request.getParameter("distance");
		String distFilterRadio = null;
		if(StringUtils.isNotBlank(distance)){
			switch (distance) {
			case "5":
				distFilterRadio = "distanceFilter5";
				break;
			case "8":
				distFilterRadio = "distanceFilter8";
				break;
			case "10":
				distFilterRadio = "distanceFilter10";
				break;
			case "12":
				distFilterRadio = "distanceFilter12";
				break;
			case "15":
				distFilterRadio = "distanceFilter15";
				break;	
			default:
				//do nothing
				break;
			}
		}
		else{
			distFilterRadio = "distanceFilter5";
		}
		globalMap.put(distFilterRadio, Boolean.TRUE);
	}

}
