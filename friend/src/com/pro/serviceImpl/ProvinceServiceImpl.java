package com.pro.serviceImpl;

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

}
