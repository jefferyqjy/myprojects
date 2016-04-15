package com.pro.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.pro.exception.ProException;
import com.pro.pojo.SubjectBean;
import com.pro.pojo.UniversityBean;
import com.pro.service.SubjectService;
import com.pro.service.UniversityService;
import com.pro.utils.CommonUtils;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UniversityService universityService;
	
	@Override
	public boolean add(SubjectBean subjectBean) throws ProException {
		try {
			jdbcTemplate.update("INSERT INTO COM_PRO_SUBJECT (NAME, UNIVERSITY) VALUES(?, ?)", subjectBean.getName(), subjectBean.getUniversityId());
			subjectBean.setId(jdbcTemplate.queryForInt("SELECT ID FROM COM_PRO_SUBJECT WHERE NAME=? ORDER BY ID DESC", subjectBean.getName()));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean update(SubjectBean subjectBean) throws ProException {
		try {
			jdbcTemplate.update("UPDATE COM_PRO_SUBJECT SET NAME=?, UNIVERSITY=? WHERE ID =?", subjectBean.getName(), subjectBean.getUniversityId(), subjectBean.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean delete(int id) throws ProException {
		try {
			jdbcTemplate.update("DELETE FROM COM_PRO_SUBJECT WHERE ID = ?", id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public SubjectBean get(int id) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_SUBJECT WHERE ID = ?", id);
		if (!CommonUtils.isEmptyList(rows)) {
			Map<String, Object> row = rows.get(0);
			SubjectBean subjectBean = new SubjectBean();
			subjectBean.setId(id);
			subjectBean.setId((Integer) row.get("ID"));
			subjectBean.setName((String) row.get("NAME"));
			UniversityBean university = universityService.get((Integer) row.get("UNIVERSITY")); 
			subjectBean.setUniversity(university);
			subjectBean.setUniversityName(university == null ? "" : university.getName());
			return subjectBean;
		}
		return null;
	}
	
	@Override
	public SubjectBean get(SubjectBean subjectBean) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_SUBJECT WHERE ID = ?", subjectBean.getId());
		if (!CommonUtils.isEmptyList(rows)) {
			Map<String, Object> row = rows.get(0);
			subjectBean.setId((Integer) row.get("ID"));
			subjectBean.setName((String) row.get("NAME"));
			UniversityBean university = universityService.get((Integer) row.get("UNIVERSITY")); 
			subjectBean.setUniversity(university);
			subjectBean.setUniversityName(university == null ? "" : university.getName());
			return subjectBean;
		}
		return null;
	}
	
	@Override
	public List<SubjectBean> list() throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_SUBJECT");
		if (!CommonUtils.isEmptyList(rows)) {
			List<SubjectBean> beanList = new ArrayList<SubjectBean>();
			for (Map<String, Object> row : rows) {
				SubjectBean subjectBean = new SubjectBean();
				subjectBean.setId((Integer) row.get("ID"));
				subjectBean.setName((String) row.get("NAME"));
				UniversityBean university = universityService.get((Integer) row.get("UNIVERSITY")); 
				subjectBean.setUniversity(university);
				subjectBean.setUniversityName(university == null ? "" : university.getName());
				beanList.add(subjectBean);
			}
			return beanList;
		}
		return null;
	}
	
	@Override
	public List<SubjectBean> list(int start, int limit) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_SUBJECT LIMIT ?,?", start, limit);
		if (!CommonUtils.isEmptyList(rows)) {
			List<SubjectBean> beanList = new ArrayList<SubjectBean>();
			for (Map<String, Object> row : rows) {
				SubjectBean subjectBean = new SubjectBean();
				subjectBean.setId((Integer) row.get("ID"));
				subjectBean.setName((String) row.get("NAME"));
				UniversityBean university = universityService.get((Integer) row.get("UNIVERSITY")); 
				subjectBean.setUniversity(university);
				subjectBean.setUniversityName(university == null ? "" : university.getName());
				beanList.add(subjectBean);
			}
			return beanList;
		}
		return null;
	}
	
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

	public int getTotalRecords() throws ProException {
		return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM COM_PRO_SUBJECT");
	}
	
}
