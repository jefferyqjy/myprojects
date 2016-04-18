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
import com.pro.pojo.TwitterBean;
import com.pro.service.CityService;
import com.pro.service.MemberService;
import com.pro.service.TwitterService;
import com.pro.utils.CommonUtils;

@Service
public class TwitterServiceImpl implements TwitterService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	CityService cityService;
	
	@Autowired
	MemberService memberService;

	@Override
	public boolean add(TwitterBean twitterBean) throws ProException {
		try {
			jdbcTemplate.update("INSERT INTO COM_PRO_TWITTER (CONTENT, CREATETIME, USERID) VALUES(?, ?, ?)", 
					twitterBean.getContent(),
					twitterBean.getCreateTime(),
					twitterBean.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean like(Integer twitterId) throws ProException {
		try {
			TwitterBean t = this.get(twitterId);
			Integer like = t.getLike();
			like++;
			jdbcTemplate.update("UPDATE COM_PRO_TWITTER SET LIKE = ?", like);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

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

	@Override
	public TwitterBean get(int id) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_TWITTER WHERE ID = ?", id);
		if (!CommonUtils.isEmptyList(rows)) {
			Map<String, Object> row = rows.get(0);
			TwitterBean twitterBean = new TwitterBean();
			twitterBean.setId((Integer) row.get("ID"));
			twitterBean.setContent((String) row.get("CONTENT"));
			Date date = (Date)row.get("CREATETIME");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			twitterBean.setCreateTime(date == null ? df.format(new Date()) : df.format(date));
			Integer userId1 = (Integer) row.get("USERID");
			twitterBean.setUserId(userId1);
			MemberBean member = memberService.get(userId1);
			twitterBean.setUserName(member == null ? "" : member.getUserName());
			twitterBean.setLike((Integer) row.get("LIKE"));
			return twitterBean;
		}
		return null;
	}

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
	
	public List<TwitterBean> findByUserId(Integer userId) throws ProException {
		String query = " SELECT T.* FROM com_pro_twitter T, com_pro_friend_group_user U WHERE T.USERID = U.FRIEND_ID and U.USER_ID = ?"
					 + " UNION ALL" 
					 + " SELECT T2.* FROM com_pro_twitter T2 WHERE T2.USERID = ?";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, userId, userId);
		if (!CommonUtils.isEmptyList(rows)) {
			List<TwitterBean> beanList = new ArrayList<TwitterBean>();
			for (Map<String, Object> row : rows) {
				TwitterBean twitterBean = new TwitterBean();
				twitterBean.setId((Integer) row.get("ID"));
				twitterBean.setContent((String) row.get("CONTENT"));
				Date date = (Date)row.get("CREATETIME");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				twitterBean.setCreateTime(date == null ? df.format(new Date()) : df.format(date));
				Integer userId1 = (Integer) row.get("USERID");
				twitterBean.setUserId(userId1);
				MemberBean member = memberService.get(userId);
				twitterBean.setUserName(member == null ? "" : member.getUserName());
				twitterBean.setLike((Integer) row.get("LIKE"));
				beanList.add(twitterBean);
			}
			return beanList;
		}
		return null;
	}

	public int getTotalRecords() throws ProException {
		return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM com_pro_twitter");
	}

}
