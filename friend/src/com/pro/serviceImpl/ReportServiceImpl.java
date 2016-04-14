package com.pro.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.pro.exception.ProException;
import com.pro.pojo.ReportBean;
import com.pro.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean add(ReportBean reportBean) throws ProException {
		try {
			jdbcTemplate.update("INSERT INTO COM_PRO_REPORT (USERID, REASON, REPORTER, REPORTTIME) VALUES(?,?,?,?)", 
					reportBean.getUserId(),reportBean.getReason(), reportBean.getReporter(), reportBean.getReportTime());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Integer countByUserId(String userId) throws ProException {
		Integer count = 0;
		count = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM COM_PRO_REPORT WHERE USERID = ?", userId);
		return count;
	}

}
