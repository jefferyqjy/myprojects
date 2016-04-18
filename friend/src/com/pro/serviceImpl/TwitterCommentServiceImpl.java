package com.pro.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.pro.exception.ProException;
import com.pro.pojo.MemberBean;
import com.pro.pojo.TwitterCommentBean;
import com.pro.service.CityService;
import com.pro.service.MemberService;
import com.pro.service.TwitterCommentService;
import com.pro.service.TwitterService;
import com.pro.utils.CommonUtils;

@Service
public class TwitterCommentServiceImpl implements TwitterCommentService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	CityService cityService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	TwitterService twitterService;

	/*@Override
	public boolean add(UniversityBean universityBean) throws ProException {
		try {
			jdbcTemplate.update("INSERT INTO COM_PRO_UNIVERSITY (NAME,CITY) VALUES(?,?)", universityBean.getName(),
					universityBean.getCity().getId());
			universityBean.setId(jdbcTemplate.queryForInt(
					"SELECT ID FROM COM_PRO_UNIVERSITY WHERE NAME=? AND CITY=? ORDER BY ID DESC",
					universityBean.getName(), universityBean.getCity().getId()));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}*/

	/*@Override
	public boolean update(UniversityBean universityBean) throws ProException {
		try {
			jdbcTemplate.update("UPDATE COM_PRO_UNIVERSITY SET NAME=?,CITY=? WHERE ID =?", universityBean.getName(),
					universityBean.getCity().getId(), universityBean.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
*/
	/*@Override
	public boolean delete(int id) throws ProException {
		try {
			jdbcTemplate.update("DELETE FROM COM_PRO_UNIVERSITY WHERE ID = ?", id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}*/

	/*@Override
	public UniversityBean get(int id) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_UNIVERSITY WHERE ID = ?", id);
		if (!CommonUtils.isEmptyList(rows)) {
			Map<String, Object> row = rows.get(0);
			UniversityBean universityBean = new UniversityBean();
			universityBean.setId(id);
			universityBean.setId((Integer) row.get("ID"));
			universityBean.setName((String) row.get("NAME"));
			universityBean.setCity(cityService.get((Integer) row.get("CITY")));
			return universityBean;
		}
		return null;
	}*/

	/*@Override
	public UniversityBean get(UniversityBean universityBean) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_UNIVERSITY WHERE ID = ?",
				universityBean.getId());
		if (!CommonUtils.isEmptyList(rows)) {
			Map<String, Object> row = rows.get(0);
			universityBean.setId((Integer) row.get("ID"));
			universityBean.setName((String) row.get("NAME"));
			universityBean.setCity(cityService.get((Integer) row.get("CITY")));
			return universityBean;
		}
		return null;
	}*/

	/*@Override
	public List<TwitterBean> list() throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_TWITTER");
		if (!CommonUtils.isEmptyList(rows)) {
			List<TwitterBean> beanList = new ArrayList<TwitterBean>();
			for (Map<String, Object> row : rows) {
				TwitterBean universityBean = new TwitterBean();
				universityBean.setId((Integer) row.get("ID"));
				universityBean.setName((String) row.get("NAME"));
				universityBean.setCity(cityService.get((Integer) row.get("CITY")));
				beanList.add(universityBean);
			}
			return beanList;
		}
		return null;
	}*/

	/*@Override
	public List<UniversityBean> list(int start, int limit) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_UNIVERSITY LIMIT ?,?", start,
				limit);
		if (!CommonUtils.isEmptyList(rows)) {
			List<UniversityBean> beanList = new ArrayList<UniversityBean>();
			for (Map<String, Object> row : rows) {
				UniversityBean universityBean = new UniversityBean();
				universityBean.setId((Integer) row.get("ID"));
				universityBean.setName((String) row.get("NAME"));
				universityBean.setCity(cityService.get((Integer) row.get("CITY")));
				beanList.add(universityBean);
			}
			return beanList;
		}
		return null;
	}*/
	
	public List<TwitterCommentBean> findByTwitterId(Integer twitterId) throws ProException {
		String query = "SELECT T.* FROM com_pro_twitter_comment T WHERE T.TWITTERID = ?";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, twitterId);
		if (!CommonUtils.isEmptyList(rows)) {
			List<TwitterCommentBean> beanList = new ArrayList<TwitterCommentBean>();
			for (Map<String, Object> row : rows) {
				TwitterCommentBean twitterBean = new TwitterCommentBean();
				twitterBean.setId((Integer) row.get("ID"));
				
				Integer fromUserId = (Integer) row.get("FROMUSERID");
				MemberBean fromUser = memberService.get(fromUserId);
				twitterBean.setFromUserId(fromUserId);
				twitterBean.setFromUserName(fromUser == null ? "" : fromUser.getUserName());
				
				Integer toUserId = (Integer) row.get("TOUSERID");
				MemberBean toUser = memberService.get(toUserId);
				twitterBean.setToUserId(toUserId);
				twitterBean.setToUserName(toUser.getUserName());
				
				twitterBean.setTwitterId(twitterId);
				
				Date date = (Date) row.get("CREATETIME");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				twitterBean.setCreateTime(date == null ? df.format(new Date()) : df.format(date));
				twitterBean.setContent((String) row.get("CONTENT"));
				
				beanList.add(twitterBean);
			}
			return beanList;
		}
		return null;
	}

	public int getTotalRecords() throws ProException {
		return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM com_pro_twitter_comment");
	}

}
