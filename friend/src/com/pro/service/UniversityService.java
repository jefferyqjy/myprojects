package com.pro.service;

import java.util.List;
import com.pro.exception.ProException;
import com.pro.pojo.UniversityBean;

public interface UniversityService {

	public boolean add(UniversityBean universityBean) throws ProException;

	public boolean update(UniversityBean universityBean) throws ProException;

	public boolean delete(int id) throws ProException;

	public UniversityBean get(int id) throws ProException;

	public UniversityBean get(UniversityBean universityBean) throws ProException;

	public List<UniversityBean> list() throws ProException;

	public List<UniversityBean> list(int start, int limit) throws ProException;
	
	public List<UniversityBean> findByCityId(Integer cityId) throws ProException;

	public int getTotalRecords() throws ProException;

}
