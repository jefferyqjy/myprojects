package com.pro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pro.pojo.BokeBean;
import com.pro.pojo.BokeMessageBean;
import com.pro.pojo.datatable.DatatableReponse;
import com.pro.utils.CommonUtils;

@Controller
@RequestMapping(value = "/boke")
public class BokeCtl {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "/view.spring", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "boke/view";
		// add other logic.
		try {
			BokeBean bokeBean = new BokeBean();
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_BOKE WHERE ID = ?", id);
			if (!CommonUtils.isEmptyList(rows)) {
				Map<String, Object> row = rows.get(0);
				bokeBean.setId(id);
				bokeBean.setTitle((String)row.get("TITLE"));
				bokeBean.setContent((String)row.get("CONTENT"));
			}
			mav.addObject("bokeBean", bokeBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}
	
	@RequestMapping(value = "/viewBoke.spring", method = RequestMethod.GET)
	public ModelAndView viewBoke(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "frd/boke";
		// add other logic.
		try {
			BokeBean bokeBean = new BokeBean();
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_BOKE WHERE ID = ?", id);
			if (!CommonUtils.isEmptyList(rows)) {
				Map<String, Object> row = rows.get(0);
				bokeBean.setId(id);
				bokeBean.setTitle((String)row.get("TITLE"));
				bokeBean.setContent((String)row.get("CONTENT"));
			}
			mav.addObject("bokeBean", bokeBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}
	
	@RequestMapping(value = "/preUpdate.spring", method = RequestMethod.GET)
	public ModelAndView preUpdate(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "boke/update";
		// add other logic.
		try {
			BokeBean bokeBean = new BokeBean();
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_BOKE WHERE ID = ?", id);
			if (!CommonUtils.isEmptyList(rows)) {
				Map<String, Object> row = rows.get(0);
				bokeBean.setId(id);
				bokeBean.setTitle((String)row.get("TITLE"));
				bokeBean.setContent((String)row.get("CONTENT"));
			}
			mav.addObject("bokeBean", bokeBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}
	
	@RequestMapping(value = "/delete.spring", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "boke/list";
		try {
			if (jdbcTemplate.update("DELETE FROM COM_PRO_BOKE WHERE ID = ?", id) > 0) {
				mav.addObject("MESSAGE", "Delete Member Successfully");
			} else {
				mav.addObject("MESSAGE", "Delete Member Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}
	
	
	@RequestMapping(value = "/preList.spring", method = RequestMethod.GET)
	public ModelAndView preUpdate(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "boke/list";
		Integer id = (Integer)session.getAttribute("ONLINE_MEMBER_ID");
		if (id == null || id < 0) {
			pageUrl = "error";
			mav.addObject("ERROR_MESSAGE", "ÇëÏÈµÇÂ½");
		}
	
		mav.setViewName(pageUrl);
		return mav;
	} 
	
	@RequestMapping(value = "/update.spring", method = RequestMethod.POST)
	public @ResponseBody
	String update(
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "content", required = false) String content,
			HttpSession session) throws Exception {
		String flag = "1";
		try {
			jdbcTemplate.update("UPDATE COM_PRO_BOKE SET TITLE =? ,CONTENT=? WHERE ID=?", title, content, id);
		} catch (Exception e) {
			e.printStackTrace();
			flag = "0";
		}
		return flag;
	}
	
	@RequestMapping(value = "/add.spring", method = RequestMethod.POST)
	public @ResponseBody
	String add(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "content", required = false) String content,
			HttpSession session) throws Exception {
		String flag = "1";
		Integer id = (Integer)session.getAttribute("ONLINE_MEMBER_ID");
		try {
			jdbcTemplate.update("INSERT INTO COM_PRO_BOKE (TITLE,CONTENT,USER_ID,CRE_DATE) VALUES(?,?,?,NOW())", title, content, id);
		} catch (Exception e) {
			e.printStackTrace();
			flag = "0";
		}
		return flag;
	}
	
	@RequestMapping(value = "/addMessage.spring", method = RequestMethod.POST)
	public @ResponseBody
	String addMessage(
			@RequestParam(value = "message", required = false) String message,
			@RequestParam(value = "id", required = false) Integer id,
			HttpSession session) throws Exception {
		String flag = "1";
		Integer userId = (Integer)session.getAttribute("ONLINE_MEMBER_ID");
		String userName = (String)session.getAttribute("ONLINE_MEMBER");
		try {
			jdbcTemplate.update("INSERT INTO COM_PRO_BOKE_MESSAGE (MESSAGE, USERID, USERNAME,BOKE_ID, CRE_DATE) VALUES(?,?,?,?,NOW())", message, userId, userName, id);
		} catch (Exception e) {
			e.printStackTrace();
			flag = "0";
		}
		return flag;
	}
	

	@RequestMapping(value = "/list.spring", method = RequestMethod.POST)
	public @ResponseBody
	DatatableReponse list(@RequestParam(value = "iDisplayStart", required = false) Integer start,
			@RequestParam(value = "iDisplayLength", required = false) Integer limit,
			@RequestParam(value = "sEcho", required = false) Integer sEcho,
			HttpSession session) throws Exception {
		DatatableReponse data = new DatatableReponse();
		try {
			Integer id = (Integer)session.getAttribute("ONLINE_MEMBER_ID");
			data.setsEcho(sEcho);
			data.setiDisplayStart(start);
			data.setiDisplayLength(limit);
			int total = jdbcTemplate.queryForInt("SELECT COUNT(1) FROM COM_PRO_BOKE WHERE USER_ID =?", id ) ;
			data.setiTotalDisplayRecords(total);
			data.setiTotalRecords(total);
			
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_BOKE WHERE USER_ID =? ORDER BY ID DESC LIMIT ?,?", id, start,
					limit);
			if (!CommonUtils.isEmptyList(rows)) {
				List<BokeBean> beanList = new ArrayList<BokeBean>();
				for (Map<String, Object> row : rows) {
					BokeBean bean = new BokeBean();
					bean.setId((Integer) row.get("ID"));
					bean.setTitle((String)row.get("TITLE"));
					bean.setContent((String)row.get("CONTENT"));
//					bean.setCreDateTime((String)row.get("CRE_DATE"));
					beanList.add(bean);
				}
				data.setAaData(beanList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	@RequestMapping(value = "/listByUserId.spring", method = RequestMethod.POST)
	public @ResponseBody
	DatatableReponse listU(@RequestParam(value = "iDisplayStart", required = false) Integer start,
			@RequestParam(value = "iDisplayLength", required = false) Integer limit,
			@RequestParam(value = "sEcho", required = false) Integer sEcho,
			@RequestParam(value = "userId", required = false) Integer userId,
			HttpSession session) throws Exception {
		DatatableReponse data = new DatatableReponse();
		try {
			data.setsEcho(sEcho);
			data.setiDisplayStart(start);
			data.setiDisplayLength(limit);
			int total = jdbcTemplate.queryForInt("SELECT COUNT(1) FROM COM_PRO_BOKE WHERE USER_ID =?", userId ) ;
			data.setiTotalDisplayRecords(total);
			data.setiTotalRecords(total);
			
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_BOKE WHERE USER_ID =? ORDER BY ID DESC LIMIT ?,?", userId, start,
					limit);
			if (!CommonUtils.isEmptyList(rows)) {
				List<BokeBean> beanList = new ArrayList<BokeBean>();
				for (Map<String, Object> row : rows) {
					BokeBean bean = new BokeBean();
					bean.setId((Integer) row.get("ID"));
					bean.setTitle((String)row.get("TITLE"));
					bean.setContent((String)row.get("CONTENT"));
//					bean.setCreDateTime((String)row.get("CRE_DATE"));
					beanList.add(bean);
				}
				data.setAaData(beanList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	@RequestMapping(value = "/listMessage.spring", method = RequestMethod.GET)
	public @ResponseBody
	DatatableReponse listMessage(@RequestParam(value = "iDisplayStart", required = false) Integer start,
			@RequestParam(value = "iDisplayLength", required = false) Integer limit,
			@RequestParam(value = "sEcho", required = false) Integer sEcho,
			@RequestParam(value = "id", required = false) Integer id,
			HttpSession session) throws Exception {
		DatatableReponse data = new DatatableReponse();
		try {
			data.setsEcho(sEcho);
			data.setiDisplayStart(start);
			data.setiDisplayLength(limit);
			int total = jdbcTemplate.queryForInt("SELECT COUNT(1) FROM COM_PRO_BOKE_MESSAGE WHERE BOKE_ID =?", id ) ;
			data.setiTotalDisplayRecords(total);
			data.setiTotalRecords(total);
			
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_BOKE_MESSAGE WHERE BOKE_ID =? ORDER BY ID DESC LIMIT ?,?", id, start,
					limit);
			if (!CommonUtils.isEmptyList(rows)) {
				List<BokeMessageBean> beanList = new ArrayList<BokeMessageBean>();
				for (Map<String, Object> row : rows) {
					BokeMessageBean bean = new BokeMessageBean();
					bean.setId((Integer) row.get("ID"));
					bean.setMessage((String)row.get("MESSAGE"));
					bean.setUserName((String)row.get("USERNAME"));
					bean.setUserId((Integer)row.get("USERID"));
//					bean.setCreDateTime((String)row.get("CRE_DATE"));
					beanList.add(bean);
				}
				data.setAaData(beanList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
