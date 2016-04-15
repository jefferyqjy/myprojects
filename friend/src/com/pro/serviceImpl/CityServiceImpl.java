package com.pro.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.pro.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.pro.exception.ProException;
import com.pro.pojo.CityBean;
import com.pro.pojo.ProvinceBean;
import com.pro.service.CityService;
import com.pro.service.ProvinceService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ProvinceService provinceService;

	@Override
	public boolean add(CityBean cityBean) throws ProException {
		try {
			jdbcTemplate.update("INSERT INTO COM_PRO_CITY (NAME, PINYIN, PROVINCE) VALUES(?, ?, ?)", cityBean.getName(), cityBean.getPinyin(), cityBean.getProvinceId());
			cityBean.setId(jdbcTemplate.queryForInt("SELECT ID FROM COM_PRO_CITY WHERE NAME=? ORDER BY ID DESC", cityBean.getName()));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(CityBean cityBean) throws ProException {
		try {
			jdbcTemplate.update("UPDATE COM_PRO_CITY SET NAME=?, PINYIN=?, PROVINCE=? WHERE ID =?", cityBean.getName(), cityBean.getPinyin(), cityBean.getProvinceId(), cityBean.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(int id) throws ProException {
		try {
			jdbcTemplate.update("DELETE FROM COM_PRO_CITY WHERE ID = ?", id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public CityBean get(int id) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_CITY WHERE ID = ?", id);
		if (!CommonUtils.isEmptyList(rows)) {
			Map<String, Object> row = rows.get(0);
			CityBean cityBean = new CityBean();
			cityBean.setId(id);
			cityBean.setId((Integer) row.get("ID"));
			cityBean.setName((String) row.get("NAME"));
			cityBean.setPinyin((String) row.get("PINYIN"));
			cityBean.setProvinceId((Integer) row.get("PROVINCE"));
			ProvinceBean province = provinceService.get((Integer) row.get("PROVINCE")); 
			cityBean.setProvince(province);
			cityBean.setProvinceName(province == null ? "" : province.getName());
			return cityBean;
		}
		return null;
	}

	@Override
	public CityBean get(CityBean cityBean) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_CITY WHERE ID = ?",
				cityBean.getId());
		if (!CommonUtils.isEmptyList(rows)) {
			Map<String, Object> row = rows.get(0);
			cityBean.setId((Integer) row.get("ID"));
			cityBean.setName((String) row.get("NAME"));
			cityBean.setPinyin((String) row.get("PINYIN"));
			cityBean.setProvinceId((Integer) row.get("PROVINCE"));
			ProvinceBean province = provinceService.get((Integer) row.get("PROVINCE")); 
			cityBean.setProvince(province);
			cityBean.setProvinceName(province == null ? "" : province.getName());
			return cityBean;
		}
		return null;
	}

	@Override
	public List<CityBean> list() throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_CITY");
		if (!CommonUtils.isEmptyList(rows)) {
			List<CityBean> beanList = new ArrayList<CityBean>();
			for (Map<String, Object> row : rows) {
				CityBean cityBean = new CityBean();
				cityBean.setId((Integer) row.get("ID"));
				cityBean.setName((String) row.get("NAME"));
				cityBean.setPinyin((String) row.get("PINYIN"));
				cityBean.setProvinceId((Integer) row.get("PROVINCE"));
				ProvinceBean province = provinceService.get((Integer) row.get("PROVINCE")); 
				cityBean.setProvince(province);
				cityBean.setProvinceName(province == null ? "" : province.getName());
				beanList.add(cityBean);
			}
			return beanList;
		}
		return null;
	}

	@Override
	public List<CityBean> list(int start, int limit) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate
				.queryForList("SELECT * FROM COM_PRO_CITY LIMIT ?,?", start, limit);
		if (!CommonUtils.isEmptyList(rows)) {
			List<CityBean> beanList = new ArrayList<CityBean>();
			for (Map<String, Object> row : rows) {
				CityBean cityBean = new CityBean();
				cityBean.setId((Integer) row.get("ID"));
				cityBean.setName((String) row.get("NAME"));
				cityBean.setPinyin((String) row.get("PINYIN"));
				cityBean.setProvinceId((Integer) row.get("PROVINCE"));
				ProvinceBean province = provinceService.get((Integer) row.get("PROVINCE")); 
				cityBean.setProvince(province);
				cityBean.setProvinceName(province == null ? "" : province.getName());
				beanList.add(cityBean);
			}
			return beanList;
		}
		return null;
	}

	public List<CityBean> findByProvince(Integer provinceId) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_CITY WHERE PROVINCE = ?", provinceId);
		if(!CommonUtils.isEmptyList(rows)) {
			List<CityBean> beanList = new ArrayList<CityBean>();
			for(Map<String, Object> row : rows) {
				CityBean cityBean = new CityBean();
				cityBean.setId((Integer) row.get("ID"));
				cityBean.setName((String) row.get("NAME"));
				cityBean.setPinyin((String) row.get("PINYIN"));
				cityBean.setProvinceId((Integer) row.get("PROVINCE"));
				ProvinceBean province = provinceService.get((Integer) row.get("PROVINCE")); 
				cityBean.setProvince(province);
				cityBean.setProvinceName(province == null ? "" : province.getName());
				beanList.add(cityBean);
			}
			return beanList;
		}
		return null;
	}
	
	public int getTotalRecords() throws ProException {
		return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM COM_PRO_CITY");
	}

}
