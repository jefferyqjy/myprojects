package com.pro.service;

import com.pro.exception.ProException;
import com.pro.pojo.ProvinceBean;

public interface ProvinceService {

	public ProvinceBean get(int id) throws ProException;
	
}
