package com.base.pagination.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;


public class QueryConditionSQL {
	
	private StringBuffer sb;
	
	private Map<String, Object> namedParamMap;

	public QueryConditionSQL(StringBuffer sb2) {
		this.sb=sb2;
		this.namedParamMap=new HashMap<String,Object>();
	}

	public Map<String, Object> getNamedParamMap() {
		return namedParamMap;
	}

	public void setParameter(String paramKey, Object paramValue){
		this.namedParamMap.put(paramKey, paramValue);
	}
	public void setLikeParameter(String paramKey, Object paramValue){
		this.namedParamMap.put(paramKey, "%"+paramValue.toString()+"%");
	}
	
	public void parameterToQuery(Query query){
		for(String key:namedParamMap.keySet()){
			Object value=namedParamMap.get(key);
			query.setParameter(key, value);
		}
	}

	public StringBuffer getSQL() {
		return sb;
	}
	
	public String toString(){
		return getSQL().toString();
	}

}

