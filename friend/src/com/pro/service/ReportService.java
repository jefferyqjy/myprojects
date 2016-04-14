package com.pro.service;

import com.pro.exception.ProException;
import com.pro.pojo.ReportBean;

public interface ReportService {

	public boolean add(ReportBean reportBean) throws ProException;

	public Integer countByUserId(String userId) throws ProException;
}
