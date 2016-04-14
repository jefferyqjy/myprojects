package com.pro.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pro.exception.ProException;
import com.pro.pojo.FriendGroup;
import com.pro.pojo.MemberBean;
import com.pro.pojo.Message;
import com.pro.pojo.ReportBean;
import com.pro.pojo.datatable.DatatableReponse;
import com.pro.service.InterestService;
import com.pro.service.MemberService;
import com.pro.service.ReportService;
import com.pro.service.UniversityService;
import com.pro.utils.CommonUtils;
import com.pro.utils.DateUtils;

@Controller
@RequestMapping(value = "/frd")
public class FriendCtl {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	UniversityService universityService;

	@Autowired
	InterestService interestService;
	
	@Autowired
	ReportService reportService; 
	
	@RequestMapping(value = "/preList.spring", method = RequestMethod.GET)
	public ModelAndView preUpdate(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "frd/list";
		Integer id = (Integer)session.getAttribute("ONLINE_MEMBER_ID");
		if (id == null || id < 0) {
			pageUrl = "error";
			mav.addObject("ERROR_MESSAGE", "请先登陆");
		} else {
			List<FriendGroup> groupList = new ArrayList<FriendGroup>();
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_FRIEND_GROUP WHERE USER_ID = ?", id);
			if (!CommonUtils.isEmptyList(rows)) {
				for (Map<String, Object> row : rows) {
					FriendGroup group = new FriendGroup();
					groupList.add(group);
					int groupId = (Integer)row.get("ID");
					group.setId(groupId);
					group.setName((String)row.get("NAME"));
					List<Map<String, Object>> rows2 = jdbcTemplate.queryForList("SELECT B.ID, B.USERNAME FROM COM_PRO_FRIEND_GROUP_USER A, COM_PRO_MEMBER B WHERE A.FRIEND_ID=B.ID AND A.GROUP_ID = ?", groupId);
					if (!CommonUtils.isEmptyList(rows2)) {
						List<MemberBean> mList = new ArrayList<MemberBean>();
						for (Map<String, Object> row2 : rows2) {
							MemberBean mb = new MemberBean();
							mb.setId((Integer)row2.get("ID"));
							mb.setUserName((String)row2.get("USERNAME"));
							mList.add(mb);
						}
						group.setFriends(mList);
					}	
				}
				mav.addObject("GROUPLIST", groupList);
			}
		}
		mav.setViewName(pageUrl);
		
		return mav;
	} 
	
	@RequestMapping(value = "/home.spring", method = RequestMethod.GET)
	public ModelAndView home(HttpSession session,HttpServletRequest request,
			@RequestParam(value = "userId", required = false) Integer userId
			) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "frd/home";
		MemberBean memberBean = memberService.get(userId);
		mav.addObject("memberBean", memberBean);
		mav.addObject("UniversityBeanList", universityService.list());
		mav.addObject("InterestBeanList", memberService.getInterest(userId));
		mav.setViewName(pageUrl);
		
		return mav;
	} 
	
	@RequestMapping(value = "/getMsg.spring", method = RequestMethod.POST)
	public @ResponseBody
	List<Message> getMsg(HttpSession session, 
			@RequestParam(value = "userId", required = false) Integer userId) throws Exception {
		List<Message> list = new ArrayList<Message>();
		Integer id = (Integer)session.getAttribute("ONLINE_MEMBER_ID");
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_MESSAGE WHERE (TO_USER_ID = ? AND FROM_USER_ID = ?) OR (TO_USER_ID = ? AND FROM_USER_ID = ?);", id, userId, userId, id);
		if (!CommonUtils.isEmptyList(rows)) {
			for (Map<String, Object> row : rows) {
				Message msg = new Message();
				int fromUserId = (Integer)row.get("FROM_USER_ID");
				msg.setMessage((String)row.get("MESSAGE"));
				msg.setDate((String)row.get("CRE_DATE"));
				msg.setFromUserId(fromUserId);
				msg.setFromUserName((String)row.get("FROM_USER_NAME"));
				if (fromUserId == userId) {
					msg.setOrder(1);
				} 
				list.add(msg);
			}
		}
		return list;
	}
	
	@RequestMapping(value = "/addMsg.spring", method = RequestMethod.POST)
	public @ResponseBody
	String addMsg(HttpSession session, 
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "userId", required = false) Integer userId) throws Exception {
		String flag = "1";
		Integer id = (Integer)session.getAttribute("ONLINE_MEMBER_ID");
		String name = (String)session.getAttribute("ONLINE_MEMBER");
		try {
			jdbcTemplate.update("INSERT INTO COM_PRO_MESSAGE(FROM_USER_ID, TO_USER_ID, MESSAGE, CRE_DATE, STATUS,FROM_USER_NAME) VALUES (?,?,?,?,?,?)", 
					id, userId, msg, DateUtils.getCurrentDateTime(), 0, name);
		} catch (Exception e) {
			e.printStackTrace();
			flag = "0";
		}
		return flag;
	}
	
	@RequestMapping(value = "/getMessage.spring", method = RequestMethod.POST)
	public @ResponseBody
	String getMessage(HttpSession session) throws Exception {
		StringBuffer sb = new StringBuffer("有新的消息，");
		Integer id = (Integer)session.getAttribute("ONLINE_MEMBER_ID");
		try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT DISTINCT(FROM_USER_NAME) AS USERNAME FROM COM_PRO_MESSAGE WHERE STATUS =0 AND TO_USER_ID = ?", id);
			if (!CommonUtils.isEmptyList(rows)) {
				List<Object[]> para = new ArrayList<Object[]>(); 
				for (Map<String, Object> row : rows) {
					sb.append(row.get("USERNAME")).append(",");
					para.add(new Object[]{id, row.get("USERNAME")});
				}
				sb.append("给你发来了新的消息，请注意查看");
				jdbcTemplate.batchUpdate("UPDATE COM_PRO_MESSAGE SET STATUS = 1 WHERE TO_USER_ID=? AND FROM_USER_NAME=?", para);
			} else {
				return "0";
			}	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	@RequestMapping(value = "/addGroup.spring", method = RequestMethod.POST)
	public @ResponseBody
	String addGroup(@RequestParam(value = "name", required = false) String name,
			HttpSession session) throws Exception {
		String flag = "1";
		Integer id = (Integer)session.getAttribute("ONLINE_MEMBER_ID");
		try {
			jdbcTemplate.update("INSERT INTO COM_PRO_FRIEND_GROUP (NAME,USER_ID,CRE_DATE) VALUES(?,?,NOW())", name, id);
		} catch (Exception e) {
			e.printStackTrace();
			flag = "0";
		}
		return flag;
	}
	
	@RequestMapping(value = "/searchUser.spring", method = RequestMethod.POST)
	public @ResponseBody
	DatatableReponse list(@RequestParam(value = "iDisplayStart", required = false) Integer start,
			@RequestParam(value = "iDisplayLength", required = false) Integer limit,
			@RequestParam(value = "sEcho", required = false) Integer sEcho,
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "userGender", required = false) String userGender,
			HttpSession session) throws Exception {
		DatatableReponse data = new DatatableReponse();
		try {
			userId = java.net.URLDecoder.decode(userId, "UTF-8");
			userName = java.net.URLDecoder.decode(userName, "UTF-8");
			userGender = java.net.URLDecoder.decode(userGender, "UTF-8");
			int id = 0;
			try {
				id = Integer.parseInt(userId);
			} catch (Exception e) {
				
			}
			
			data.setsEcho(sEcho);
			data.setiDisplayStart(start);
			data.setiDisplayLength(limit);
			int total = jdbcTemplate.queryForInt("SELECT COUNT(1) FROM COM_PRO_MEMBER WHERE ID = " +id+" OR (USERNAME LIKE '%" + userName+"%' AND GENDER=?)", userGender) ;
			data.setiTotalDisplayRecords(total);
			data.setiTotalRecords(total);
			List<MemberBean> mList = new ArrayList<MemberBean>();
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_MEMBER WHERE ID = " +id+" OR (USERNAME LIKE '%" + userName+"%' AND GENDER=?)", userGender);
			if (!CommonUtils.isEmptyList(rows)) {
				
				for (Map<String, Object> row : rows) {
					MemberBean mb = new MemberBean();
					mb.setId((Integer)row.get("ID"));
					mb.setUserName((String)row.get("USERNAME"));
					mb.setGender((String)row.get("GENDER"));
					mb.setAge((Integer)row.get("AGE"));
					mList.add(mb);
				}
				
			}	
			data.setAaData(mList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@RequestMapping(value = "/addFriend.spring", method = RequestMethod.POST)
	public @ResponseBody
	String addFriend(
			@RequestParam(value = "userId", required = false) Integer userId,
			HttpSession session) throws Exception {
		String flag = "1";
		Integer id = (Integer)session.getAttribute("ONLINE_MEMBER_ID");
		try {
			int groupId = 0;
			if (id == userId) {
				flag = "-1";
			} else {
				try {
					groupId = jdbcTemplate.queryForInt("SELECT MIN(ID) FROM COM_PRO_FRIEND_GROUP WHERE USER_ID=?", id);
					if (groupId < 1) {
						flag = "-3";
					} else {
						if (jdbcTemplate.queryForInt("SELECT COUNT(1) FROM COM_PRO_FRIEND_GROUP_USER WHERE USER_ID =? AND FRIEND_ID=?", id, userId) >0) {
							flag = "-2";
						} else {
							jdbcTemplate.update("INSERT INTO COM_PRO_FRIEND_GROUP_USER (GROUP_ID,USER_ID, FRIEND_ID) VALUES(?,?,?)", groupId, id, userId);		
						}
					}
					
				} catch (Exception e) {
					flag = "-3";
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = "0";
		}
		return flag;
	}
	
	@RequestMapping(value = "/deleteFriend.spring", method = RequestMethod.POST)
	public @ResponseBody
	String deleteFriend(
			@RequestParam(value = "userId", required = false) Integer userId,
			HttpSession session) throws Exception {
		String flag = "1";
		Integer id = (Integer)session.getAttribute("ONLINE_MEMBER_ID");
		try {
			jdbcTemplate.update("DELETE FROM COM_PRO_FRIEND_GROUP_USER WHERE USER_ID=? AND FRIEND_ID=?", id, userId);
		} catch (Exception e) {
			e.printStackTrace();
			flag = "0";
		}
		return flag;
	}
	
	@RequestMapping(value = "/moveFriend.spring", method = RequestMethod.POST)
	public @ResponseBody
	String moveFriend(@RequestParam(value = "groupId", required = false) Integer groupId,
			@RequestParam(value = "userId", required = false) Integer userId,
			HttpSession session) throws Exception {
		String flag = "1";
		Integer id = (Integer)session.getAttribute("ONLINE_MEMBER_ID");
		try {
			jdbcTemplate.update("UPDATE COM_PRO_FRIEND_GROUP_USER SET GROUP_ID =? WHERE USER_ID=? AND FRIEND_ID=?", groupId, id, userId);
		} catch (Exception e) {
			e.printStackTrace();
			flag = "0";
		}
		return flag;
	}
	
	/**
	 * do report
	 * @param session
	 * @param userId
	 * @param reason
	 * @return
	 */
	@RequestMapping(value = "/addReport.spring", method = RequestMethod.POST)
	@ResponseBody
	public String addReport(HttpSession session,
			@RequestParam(value = "userId", required = false) String userId, 
			@RequestParam(value = "reason", required = false) String reason) {
		String flag = "1";
		Integer id = (Integer) session.getAttribute("ONLINE_MEMBER_ID");
		ReportBean report = new ReportBean();
		report.setReason(reason);
		report.setReporter(id.toString());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		report.setReportTime(df.format(new Date()));
		report.setUserId(userId);
		try {
			reportService.add(report);
			
			Integer count = reportService.countByUserId(userId);
			if(count >= 50) {
				// move member info to black list
				MemberBean member = memberService.get(Integer.valueOf(userId.trim()));
				memberService.addBlackMember(member);
				memberService.delete(member.getId());
			}
		} catch (ProException e) {
			e.printStackTrace();
			flag = "0";
		}
		return flag;
	}
	
	
}
