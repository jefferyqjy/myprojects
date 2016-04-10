package com.pro.service;

import java.util.List;
import com.pro.exception.ProException;
import com.pro.pojo.CityBean;

public interface CityService {

	public boolean add(CityBean cityBean) throws ProException;

	public boolean update(CityBean cityBean) throws ProException;

	public boolean delete(int id) throws ProException;

	public CityBean get(int id) throws ProException;

	public CityBean get(CityBean cityBean) throws ProException;

	public List<CityBean> list() throws ProException;

	public List<CityBean> list(int start, int limit) throws ProException;

	public int getTotalRecords() throws ProException;

}
