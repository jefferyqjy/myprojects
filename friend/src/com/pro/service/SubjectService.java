package com.pro.service;

import java.util.List;

import com.pro.exception.ProException;
import com.pro.pojo.SubjectBean;

public interface SubjectService {

	public boolean add(SubjectBean subjectBean) throws ProException;

	public boolean update(SubjectBean subjectBean) throws ProException;

	public boolean delete(int id) throws ProException;

	public SubjectBean get(int id) throws ProException;

	public SubjectBean get(SubjectBean subjectBean) throws ProException;

	public List<SubjectBean> list() throws ProException;

	public List<SubjectBean> list(int start, int limit) throws ProException;
	
	public List<SubjectBean> findByUniversityId(Integer university) throws ProException;

	public int getTotalRecords() throws ProException;

}
