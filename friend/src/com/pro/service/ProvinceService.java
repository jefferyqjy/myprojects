package com.pro.service;

import java.util.List;

import com.pro.exception.ProException;
import com.pro.pojo.ProvinceBean;

public interface ProvinceService {
	
	public boolean add(ProvinceBean provinceBean) throws ProException;
	
	public boolean update(ProvinceBean provinceBean) throws ProException;
	
	public boolean delete(int id) throws ProException;

	public ProvinceBean get(int id) throws ProException;
	
	public List<ProvinceBean> list() throws ProException;
	
	public List<ProvinceBean> list(int start, int limit) throws ProException;
	
	public int getTotalRecords() throws ProException;
	
}
