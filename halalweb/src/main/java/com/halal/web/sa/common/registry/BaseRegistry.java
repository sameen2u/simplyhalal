package com.halal.web.sa.common.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseRegistry {
	
	protected Map cacheMap=null;
	protected int maxSize=10;
	protected boolean ok=true;
	
	abstract protected String getName();
	
	abstract protected void populate(Map map);
	
	protected Map constructMap(final int maxSize){
		return new ConcurrentHashMap(maxSize);
	}
	
	public synchronized void refresh(){
		final Map map = constructMap(maxSize);
		populate(map);
		if(ok){
			cacheMap=map;
		}
		
	}
	
	public Map getCacheMap(){
		if(cacheMap==null || cacheMap.isEmpty()){
			refresh();
		}
		return cacheMap;
	}

}
