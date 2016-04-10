package com.pro.service;

import java.util.List;
import com.pro.exception.ProException;
import com.pro.pojo.InterestBean;

public interface InterestService {

	public boolean add(InterestBean interestBean) throws ProException;

	public boolean update(InterestBean interestBean) throws ProException;

	public boolean delete(int id) throws ProException;

	public InterestBean get(int id) throws ProException;

	public InterestBean get(InterestBean interestBean) throws ProException;

	public List<InterestBean> list() throws ProException;

	public List<InterestBean> list(int start, int limit) throws ProException;

	public int getTotalRecords() throws ProException;

}
