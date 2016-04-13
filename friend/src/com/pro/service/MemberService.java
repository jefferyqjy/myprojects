package com.pro.service;

import java.util.List;
import com.pro.exception.ProException;
import com.pro.pojo.MemberBean;
import com.pro.pojo.InterestBean;

public interface MemberService {

	public boolean add(MemberBean memberBean) throws ProException;

	public boolean update(MemberBean memberBean) throws ProException;

	public boolean delete(int id) throws ProException;
	
	public boolean valid(int id) throws ProException;
	
	public boolean invalid(int id) throws ProException;

	public MemberBean get(int id) throws ProException;

	public MemberBean get(MemberBean memberBean) throws ProException;

	public List<MemberBean> list() throws ProException;

	public List<MemberBean> list(int start, int limit) throws ProException;

	public int getTotalRecords() throws ProException;

	public List<InterestBean> getInterest(int id) throws ProException;
	
	public int login(String userName, String password) throws ProException;
	
	public MemberBean findByUserName(String userName) throws ProException;
	
	public MemberBean findByStuNo(String stuNo) throws ProException;
}
