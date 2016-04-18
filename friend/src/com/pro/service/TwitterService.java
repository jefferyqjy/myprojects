package com.pro.service;

import java.util.List;

import com.pro.exception.ProException;
import com.pro.pojo.TwitterBean;

public interface TwitterService {

	/*public boolean add(UniversityBean universityBean) throws ProException;

	public boolean update(UniversityBean universityBean) throws ProException;

	public boolean delete(int id) throws ProException;

	public UniversityBean get(int id) throws ProException;

	public UniversityBean get(UniversityBean universityBean) throws ProException;*/

	//public List<TwitterBean> list() throws ProException;

	//public List<UniversityBean> list(int start, int limit) throws ProException;
	
	public List<TwitterBean> findByUserId(Integer userId) throws ProException;

	//public int getTotalRecords() throws ProException;

}
