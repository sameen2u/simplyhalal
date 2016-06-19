package com.halal.web.sa.common.registry;

import java.util.Map;


public class ApiResponseRegistry extends BaseRegistry{

	
	protected String getName() {
		return "apiResponseRegistry";
	}

	
	protected void populate(Map map) {
		// TODO Auto-generated method stub
		
	}
	
	////Mark the constructor always as private to ensure the singleton pattern.
	private ApiResponseRegistry(){}
	
	private static class ApiResponseRegistryHolder{
		private static ApiResponseRegistry INSTANCE;
		static{
			INSTANCE = new ApiResponseRegistry();
		}
	}
	
	//return singleton instance of Apiregistry class
	public static ApiResponseRegistry getInstance(){
		return ApiResponseRegistryHolder.INSTANCE;
	}
	
	@SuppressWarnings("unchecked")
	public void putResponse(String key, String response){
		getCacheMap().put(key, response);
	}
	
	public String getResponse(Object key) {
		if(cacheMap!=null){
			return (String) cacheMap.get(key);
		}
		return null;
	}

}
