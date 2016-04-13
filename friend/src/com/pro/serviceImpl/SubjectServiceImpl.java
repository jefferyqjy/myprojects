package com.pro.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.pro.exception.ProException;
import com.pro.pojo.SubjectBean;
import com.pro.service.SubjectService;
import com.pro.service.UniversityService;
import com.pro.utils.CommonUtils;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UniversityService universityService;
	
	public List<SubjectBean> findByUniversityId(Integer universityId) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_SUBJECT WHERE UNIVERSITY = ?", universityId);
		if (!CommonUtils.isEmptyList(rows)) {
			List<SubjectBean> beanList = new ArrayList<SubjectBean>();
			for (Map<String, Object> row : rows) {
				SubjectBean SubjectBean = new SubjectBean();
				SubjectBean.setId((Integer) row.get("ID"));
				SubjectBean.setName((String) row.get("NAME"));
				SubjectBean.setUniversityId((Integer) row.get("UNIVERSITY"));
				SubjectBean.setUniversity(universityService.get((Integer) row.get("UNIVERSITY")));
				beanList.add(SubjectBean);
			}
			return beanList;
		}
		return null;
	}
	
}
