package com.pro.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.pro.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.pro.exception.ProException;
import com.pro.pojo.InterestBean;
import com.pro.service.InterestService;

@Service
public class InterestServiceImpl implements InterestService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean add(InterestBean interestBean) throws ProException {
		try {
			jdbcTemplate.update("INSERT INTO COM_PRO_INTEREST (NAME) VALUES(?)", interestBean.getName());
			interestBean.setId(jdbcTemplate.queryForInt(
					"SELECT ID FROM COM_PRO_INTEREST WHERE NAME=? ORDER BY ID DESC", interestBean.getName()));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(InterestBean interestBean) throws ProException {
		try {
			jdbcTemplate.update("UPDATE COM_PRO_INTEREST SET NAME=? WHERE ID =?", interestBean.getName(),
					interestBean.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(int id) throws ProException {
		try {
			jdbcTemplate.update("DELETE FROM COM_PRO_INTEREST WHERE ID = ?", id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public InterestBean get(int id) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_INTEREST WHERE ID = ?", id);
		if (!CommonUtils.isEmptyList(rows)) {
			Map<String, Object> row = rows.get(0);
			InterestBean interestBean = new InterestBean();
			interestBean.setId(id);
			interestBean.setId((Integer) row.get("ID"));
			interestBean.setName((String) row.get("NAME"));
			return interestBean;
		}
		return null;
	}

	@Override
	public InterestBean get(InterestBean interestBean) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_INTEREST WHERE ID = ?",
				interestBean.getId());
		if (!CommonUtils.isEmptyList(rows)) {
			Map<String, Object> row = rows.get(0);
			interestBean.setId((Integer) row.get("ID"));
			interestBean.setName((String) row.get("NAME"));
			return interestBean;
		}
		return null;
	}

	@Override
	public List<InterestBean> list() throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_INTEREST");
		if (!CommonUtils.isEmptyList(rows)) {
			List<InterestBean> beanList = new ArrayList<InterestBean>();
			for (Map<String, Object> row : rows) {
				InterestBean interestBean = new InterestBean();
				interestBean.setId((Integer) row.get("ID"));
				interestBean.setName((String) row.get("NAME"));
				beanList.add(interestBean);
			}
			return beanList;
		}
		return null;
	}

	@Override
	public List<InterestBean> list(int start, int limit) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_INTEREST LIMIT ?,?", start,
				limit);
		if (!CommonUtils.isEmptyList(rows)) {
			List<InterestBean> beanList = new ArrayList<InterestBean>();
			for (Map<String, Object> row : rows) {
				InterestBean interestBean = new InterestBean();
				interestBean.setId((Integer) row.get("ID"));
				interestBean.setName((String) row.get("NAME"));
				beanList.add(interestBean);
			}
			return beanList;
		}
		return null;
	}

	public int getTotalRecords() throws ProException {
		return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM COM_PRO_INTEREST");
	}

}
