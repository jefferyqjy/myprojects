package com.pro.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.pro.exception.ProException;
import com.pro.pojo.ProvinceBean;
import com.pro.service.ProvinceService;
import com.pro.utils.CommonUtils;

@Service
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean add(ProvinceBean provinceBean) throws ProException {
		try {
			jdbcTemplate.update("INSERT INTO COM_PRO_PROVINCE (NAME, PINYIN) VALUES(?, ?)", 
					provinceBean.getName(), provinceBean.getPinyin());
			provinceBean.setId(jdbcTemplate.queryForInt("SELECT ID FROM COM_PRO_PROVINCE WHERE NAME=? ORDER BY ID DESC",
					provinceBean.getName()));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean update(ProvinceBean provinceBean) throws ProException {
		try {
			jdbcTemplate.update("UPDATE COM_PRO_PROVINCE SET NAME=?, PINYIN = ? WHERE ID =?", provinceBean.getName(), provinceBean.getPinyin(), provinceBean.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean delete(int id) throws ProException {
		try {
			jdbcTemplate.update("DELETE FROM COM_PRO_PROVINCE WHERE ID = ?", id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public ProvinceBean get(int id) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_PROVINCE WHERE ID = ?", id);
		if (!CommonUtils.isEmptyList(rows)) {
			Map<String, Object> row = rows.get(0);
			ProvinceBean provinceBean = new ProvinceBean();
			provinceBean.setPinyin((String) row.get("PINYIN"));
			provinceBean.setId((Integer) row.get("ID"));
			provinceBean.setName((String) row.get("NAME"));
			return provinceBean;
		}
		return null;
	}
	
	@Override
	public List<ProvinceBean> list() throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_PROVINCE");
		if (!CommonUtils.isEmptyList(rows)) {
			List<ProvinceBean> beanList = new ArrayList<ProvinceBean>();
			for (Map<String, Object> row : rows) {
				ProvinceBean province = new ProvinceBean();
				province.setId((Integer) row.get("ID"));
				province.setName((String) row.get("NAME"));
				province.setPinyin((String) row.get("PINYIN"));
				beanList.add(province);
			}
			return beanList;
		}
		return null;
	}
	
	@Override
	public List<ProvinceBean> list(int start, int limit) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_PROVINCE LIMIT ?,?", start, limit);
		if (!CommonUtils.isEmptyList(rows)) {
			List<ProvinceBean> beanList = new ArrayList<ProvinceBean>();
			for (Map<String, Object> row : rows) {
				ProvinceBean province = new ProvinceBean();
				province.setId((Integer) row.get("ID"));
				province.setName((String) row.get("NAME"));
				province.setPinyin((String) row.get("PINYIN"));
				beanList.add(province);
			}
			return beanList;
		}
		return null;
	}
	
	public int getTotalRecords() throws ProException {
		return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM COM_PRO_PROVINCE");
	}

}
