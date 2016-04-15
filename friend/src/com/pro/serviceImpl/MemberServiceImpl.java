package com.pro.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.pro.exception.ProException;
import com.pro.pojo.InterestBean;
import com.pro.pojo.MemberBean;
import com.pro.service.InterestService;
import com.pro.service.MemberService;
import com.pro.service.UniversityService;
import com.pro.utils.CommonUtils;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	UniversityService universityService;

	@Autowired
	InterestService interestService;

	@Override
	public boolean add(MemberBean memberBean) throws ProException {
		try {
			jdbcTemplate.update("INSERT INTO COM_PRO_MEMBER (USERNAME,USERPASSWORD,UNIVERSITY,PROVINCE,CITY,SUBJECT,YEAR,STUNO) VALUES(?,?,?,?,?,?,?,?)",
							memberBean.getUserName(), memberBean.getUserPassword(), memberBean.getUniversityId(),
							memberBean.getProvince(), memberBean.getCity(), memberBean.getSubject(), memberBean.getYear(), memberBean.getStuNo());
			/*if (memberBean.getInterest().size() > 0) {
				List<Object[]> idList = new ArrayList<Object[]>();
				for (InterestBean bean : memberBean.getInterest()) {
					idList.add(new Object[] { memberBean.getId(), bean.getId() });
				}
				jdbcTemplate.batchUpdate("INSERT INTO COM_PRO_MEMBER_INTEREST (MEMBER_ID,INTEREST_ID) VALUES(?,?)",
						idList);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(MemberBean memberBean) throws ProException {
		try {
			jdbcTemplate
					.update("UPDATE COM_PRO_MEMBER SET USERNAME=?,USERPASSWORD=?,GENDER=?,AGE=?,ADDRESS=?,UNIVERSITY=? WHERE ID =?",
							memberBean.getUserName(), memberBean.getUserPassword(), memberBean.getGender(),
							memberBean.getAge(), memberBean.getAddress(), memberBean.getUniversity().getId(),
							memberBean.getId());
			jdbcTemplate.update("DELETE FROM COM_PRO_MEMBER_INTEREST WHERE MEMBER_ID=?", memberBean.getId());
			if (memberBean.getInterest().size() > 0) {
				List<Object[]> idList = new ArrayList<Object[]>();
				for (InterestBean bean : memberBean.getInterest()) {
					idList.add(new Object[] { memberBean.getId(), bean.getId() });
				}
				jdbcTemplate.batchUpdate("INSERT INTO COM_PRO_MEMBER_INTEREST (MEMBER_ID,INTEREST_ID) VALUES(?,?)",
						idList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(int id) throws ProException {
		try {
			jdbcTemplate.update("DELETE FROM COM_PRO_MEMBER WHERE ID = ?", id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean valid(int id) throws ProException {
		try {
			jdbcTemplate.update("UPDATE COM_PRO_MEMBER SET STATUS = 1 WHERE ID = ?", id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean invalid(int id) throws ProException {
		try {
			jdbcTemplate.update("UPDATE COM_PRO_MEMBER SET STATUS = 0 WHERE ID = ?", id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public MemberBean get(int id) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_MEMBER WHERE ID = ?", id);
		if (!CommonUtils.isEmptyList(rows)) {
			Map<String, Object> row = rows.get(0);
			MemberBean memberBean = new MemberBean();
			memberBean.setId(id);
			memberBean.setId((Integer) row.get("ID"));
			memberBean.setUserName((String) row.get("USERNAME"));
			memberBean.setUserPassword((String) row.get("USERPASSWORD"));
			memberBean.setGender((String) row.get("GENDER"));
			//String ageStr = (String)row.get("AGE");
			//Integer age = StringUtils.isEmpty(ageStr) ? 0 : Integer.valueOf(ageStr); // 暂时在注册时去掉了年龄、性别和地址，下一版本会加上，此处做临时处理
			//memberBean.setAge(age);
			memberBean.setAddress((String) row.get("ADDRESS"));
			memberBean.setUniversity(universityService.get((Integer) row.get("UNIVERSITY")));
			memberBean.setStatus((Integer) row.get("STATUS"));
			return memberBean;
		}
		return null;
	}

	public int login(String userName, String password) throws ProException {
		try {
			return jdbcTemplate.queryForInt("SELECT ID FROM COM_PRO_MEMBER WHERE USERNAME=? AND USERPASSWORD=?", userName, password
					); 
		} catch (Exception e) {
			return -1;
		}
		
	}
	@Override
	public MemberBean get(MemberBean memberBean) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_MEMBER WHERE ID = ?",
				memberBean.getId());
		if (!CommonUtils.isEmptyList(rows)) {
			Map<String, Object> row = rows.get(0);
			memberBean.setId((Integer) row.get("ID"));
			memberBean.setUserName((String) row.get("USERNAME"));
			memberBean.setUserPassword((String) row.get("USERPASSWORD"));
			memberBean.setGender((String) row.get("GENDER"));
			//String ageStr = (String)row.get("AGE");
			//Integer age = StringUtils.isEmpty(ageStr) ? 0 : Integer.valueOf(ageStr); // 暂时在注册时去掉了年龄、性别和地址，下一版本会加上，此处做临时处理
			//memberBean.setAge(age);
			memberBean.setAddress((String) row.get("ADDRESS"));
			memberBean.setUniversity(universityService.get((Integer) row.get("UNIVERSITY")));
			memberBean.setStatus((Integer) row.get("STATUS"));
			return memberBean;
		}
		return null;
	}

	@Override
	public List<MemberBean> list() throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_MEMBER");
		if (!CommonUtils.isEmptyList(rows)) {
			List<MemberBean> beanList = new ArrayList<MemberBean>();
			for (Map<String, Object> row : rows) {
				MemberBean memberBean = new MemberBean();
				memberBean.setId((Integer) row.get("ID"));
				memberBean.setUserName((String) row.get("USERNAME"));
				memberBean.setUserPassword((String) row.get("USERPASSWORD"));
				memberBean.setGender((String) row.get("GENDER"));
				//String ageStr = (String)row.get("AGE");
				//Integer age = StringUtils.isEmpty(ageStr) ? 0 : Integer.valueOf(ageStr); // 暂时在注册时去掉了年龄、性别和地址，下一版本会加上，此处做临时处理
				//memberBean.setAge(age);
				memberBean.setAddress((String) row.get("ADDRESS"));
				memberBean.setUniversity(universityService.get((Integer) row.get("UNIVERSITY")));
				memberBean.setStatus((Integer) row.get("STATUS"));
				beanList.add(memberBean);
			}
			return beanList;
		}
		return null;
	}

	@Override
	public List<MemberBean> list(int start, int limit) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_MEMBER LIMIT ?,?", start,
				limit);
		if (!CommonUtils.isEmptyList(rows)) {
			List<MemberBean> beanList = new ArrayList<MemberBean>();
			for (Map<String, Object> row : rows) {
				MemberBean memberBean = new MemberBean();
				memberBean.setId((Integer) row.get("ID"));
				memberBean.setUserName((String) row.get("USERNAME"));
				memberBean.setUserPassword((String) row.get("USERPASSWORD"));
				memberBean.setGender((String) row.get("GENDER"));
				//String ageStr = (String)row.get("AGE");
				//Integer age = StringUtils.isEmpty(ageStr) ? 0 : Integer.valueOf(ageStr); // 暂时在注册时去掉了年龄、性别和地址，下一版本会加上，此处做临时处理
				//memberBean.setAge(age);
				memberBean.setAddress((String) row.get("ADDRESS"));
				memberBean.setUniversity(universityService.get((Integer) row.get("UNIVERSITY")));
				memberBean.setStatus((Integer) row.get("STATUS"));
				beanList.add(memberBean);
			}
			return beanList;
		}
		return null;
	}

	public int getTotalRecords() throws ProException {
		return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM COM_PRO_MEMBER");
	}

	public List<InterestBean> getInterest(int id) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate
				.queryForList(
						"SELECT A.ID, A.NAME, IF(ISNULL(B.ID), '','checked') AS CHECKED FROM COM_PRO_INTEREST A LEFT JOIN COM_PRO_MEMBER_INTEREST B ON A.ID = B.INTEREST_ID AND B.MEMBER_ID=?",
						id);
		if (!CommonUtils.isEmptyList(rows)) {
			List<InterestBean> beanList = new ArrayList<InterestBean>();
			for (Map<String, Object> row : rows) {
				InterestBean interestBean = new InterestBean();
				interestBean.setId((Integer) row.get("ID"));
				interestBean.setName((String) row.get("NAME"));
				interestBean.setChecked((String) row.get("CHECKED"));
				beanList.add(interestBean);
			}
			return beanList;
		}
		return null;
	}

	@Override
	public MemberBean findByStuNo(String stuNo) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_MEMBER WHERE STUNO = ?", stuNo);
		if (!CommonUtils.isEmptyList(rows)) {
			for (Map<String, Object> row : rows) {
				MemberBean memberBean = new MemberBean();
				memberBean.setId((Integer) row.get("ID"));
				memberBean.setUserName((String) row.get("USERNAME"));
				memberBean.setUserPassword((String) row.get("USERPASSWORD"));
				memberBean.setGender((String) row.get("GENDER"));
				//String ageStr = (String)row.get("AGE");
				//Integer age = StringUtils.isEmpty(ageStr) ? 0 : Integer.valueOf(ageStr); // 暂时在注册时去掉了年龄、性别和地址，下一版本会加上，此处做临时处理
				//memberBean.setAge(age);
				memberBean.setAddress((String) row.get("ADDRESS"));
				memberBean.setUniversity(universityService.get((Integer) row.get("UNIVERSITY")));
				memberBean.setStatus((Integer) row.get("STATUS"));
				return memberBean;
			}
		}
		return null;
	}

	@Override
	public MemberBean findByUserName(String userName) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_MEMBER WHERE USERNAME = ?", userName);
		if (!CommonUtils.isEmptyList(rows)) {
			for (Map<String, Object> row : rows) {
				MemberBean memberBean = new MemberBean();
				memberBean.setId((Integer) row.get("ID"));
				memberBean.setUserName((String) row.get("USERNAME"));
				memberBean.setUserPassword((String) row.get("USERPASSWORD"));
				memberBean.setGender((String) row.get("GENDER"));
				//String ageStr = (String)row.get("AGE");
				//Integer age = StringUtils.isEmpty(ageStr) ? 0 : Integer.valueOf(ageStr); // 暂时在注册时去掉了年龄、性别和地址，下一版本会加上，此处做临时处理
				//memberBean.setAge(age);
				memberBean.setAddress((String) row.get("ADDRESS"));
				memberBean.setUniversity(universityService.get((Integer) row.get("UNIVERSITY")));
				memberBean.setStatus((Integer) row.get("STATUS"));
				return memberBean;
			}
		}
		return null;
	}
	
	@Override
	public boolean addBlackMember(MemberBean memberBean) throws ProException {
		try {
			jdbcTemplate.update("INSERT INTO COM_PRO_BLACK_MEMBER (USERNAME,USERPASSWORD,UNIVERSITY,PROVINCE,CITY,SUBJECT,YEAR,STUNO) VALUES(?,?,?,?,?,?,?,?)",
							memberBean.getUserName(), memberBean.getUserPassword(), memberBean.getUniversityId(),
							memberBean.getProvince(), memberBean.getCity(), memberBean.getSubject(), memberBean.getYear(), memberBean.getStuNo());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public MemberBean getBlackMember(String userName) throws ProException {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_BLACK_MEMBER WHERE USERNAME = ?", userName);
		if (!CommonUtils.isEmptyList(rows)) {
			for (Map<String, Object> row : rows) {
				MemberBean memberBean = new MemberBean();
				memberBean.setId((Integer) row.get("ID"));
				memberBean.setUserName((String) row.get("USERNAME"));
				memberBean.setUserPassword((String) row.get("USERPASSWORD"));
				memberBean.setGender((String) row.get("GENDER"));
				//String ageStr = (String)row.get("AGE");
				//Integer age = StringUtils.isEmpty(ageStr) ? 0 : Integer.valueOf(ageStr); // 暂时在注册时去掉了年龄、性别和地址，下一版本会加上，此处做临时处理
				//memberBean.setAge(age);
				memberBean.setAddress((String) row.get("ADDRESS"));
				memberBean.setUniversity(universityService.get((Integer) row.get("UNIVERSITY")));
				memberBean.setStatus((Integer) row.get("STATUS"));
				memberBean.setCity(((Integer) row.get("CITY")).toString());
				memberBean.setProvince(((Integer) row.get("PROVINCE")).toString());
				memberBean.setStuNo((String) row.get("STUNO"));
				memberBean.setSubject(((Integer) row.get("SUBJECT")).toString());
				memberBean.setUniversityId(((Integer) row.get("UNIVERSITY")).toString());
				memberBean.setYear((Integer) row.get("YEAR"));
				return memberBean;
			}
		}
		return null;
	}
	
}
