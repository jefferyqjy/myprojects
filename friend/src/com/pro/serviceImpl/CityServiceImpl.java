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
import com.pro.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean add(CityBean cityBean) throws ProException {
		try {
			jdbcTemplate.update("INSERT INTO COM_PRO_CITY (NAME) VALUES(?)", cityBean.getName());
			cityBean.setId(jdbcTemplate.queryForInt("SELECT ID FROM COM_PRO_CITY WHERE NAME=? ORDER BY ID DESC",
					cityBean.getName()));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(CityBean cityBean) throws ProException {
		try {
			jdbcTemplate.update("UPDATE COM_PRO_CITY SET NAME=? WHERE ID =?", cityBean.getName(), cityBean.getId());
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
