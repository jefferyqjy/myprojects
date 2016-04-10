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

import com.pro.pojo.BokeMessageBean;
import com.pro.pojo.PhotoBean;
import com.pro.pojo.PhotoBoxBean;
import com.pro.pojo.datatable.DatatableReponse;
import com.pro.utils.CommonUtils;

@Controller
@RequestMapping(value = "/photoBox")
public class PhotoBoxCtl {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "/view.spring", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "photoBox/view";
		// add other logic.
		try {
			PhotoBoxBean bean = new PhotoBoxBean();
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_PHOTOBOX WHERE ID = ?", id);
			if (!CommonUtils.isEmptyList(rows)) {
				Map<String, Object> row = rows.get(0);
				bean.setId(id);
				bean.setName((String)row.get("NAME"));
			}
			mav.addObject("photoBoxBean", bean);
			
			rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_PHOTO WHERE PHOTOBOX_ID = ?", id);
			List<PhotoBean> photoList = new ArrayList<PhotoBean>();
			if (!CommonUtils.isEmptyList(rows)) {
				for(Map<String, Object> row:rows) {
					PhotoBean b = new PhotoBean();
					b.setId((Integer)row.get("ID"));
					b.setPath((String)row.get("PATH"));
					b.setPhotoBoxId(id);
					b.setName((String)row.get("NAME"));
					photoList.add(b);
				}
			}
			
			mav.addObject("photoList", photoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}
	
	@RequestMapping(value = "/viewPhotoBox.spring", method = RequestMethod.GET)
	public ModelAndView viewPhotoBox(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "frd/photoBox";
		// add other logic.
		try {
			PhotoBoxBean bean = new PhotoBoxBean();
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_PHOTOBOX WHERE ID = ?", id);
			if (!CommonUtils.isEmptyList(rows)) {
				Map<String, Object> row = rows.get(0);
				bean.setId(id);
				bean.setName((String)row.get("NAME"));
			}
			mav.addObject("photoBoxBean", bean);
			
			rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_PHOTO WHERE PHOTOBOX_ID = ?", id);
			List<PhotoBean> photoList = new ArrayList<PhotoBean>();
			if (!CommonUtils.isEmptyList(rows)) {
				for(Map<String, Object> row:rows) {
					PhotoBean b = new PhotoBean();
					b.setId((Integer)row.get("ID"));
					b.setPath((String)row.get("PATH"));
					b.setPhotoBoxId(id);
					b.setName((String)row.get("NAME"));
					photoList.add(b);
				}
			}
			
			mav.addObject("photoList", photoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}
	
	@RequestMapping(value = "/preList.spring", method = RequestMethod.GET)
	public ModelAndView preUpdate(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "photoBox/list";
		Integer id = (Integer)session.getAttribute("ONLINE_MEMBER_ID");
		if (id == null || id < 0) {
			pageUrl = "error";
			mav.addObject("ERROR_MESSAGE", "ÇëÏÈµÇÂ½");
		}
	
		mav.setViewName(pageUrl);
		return mav;
	} 
	
	@RequestMapping(value = "/add.spring", method = RequestMethod.POST)
	public @ResponseBody
	String add(
			@RequestParam(value = "name", required = false) String name,
			HttpSession session) throws Exception {
		String flag = "1";
		Integer id = (Integer)session.getAttribute("ONLINE_MEMBER_ID");
		try {
			jdbcTemplate.update("INSERT INTO COM_PRO_PHOTOBOX (NAME,USER_ID,CRE_DATE) VALUES(?,?,NOW())",name, id);
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
			jdbcTemplate.update("INSERT INTO COM_PRO_PHOTOBOX_MESSAGE (MESSAGE, USERID, USERNAME,PHOTOBOX_ID, CRE_DATE) VALUES(?,?,?,?,NOW())", message, userId, userName, id);
		} catch (Exception e) {
			e.printStackTrace();
			flag = "0";
		}
		return flag;
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
			int total = jdbcTemplate.queryForInt("SELECT COUNT(1) FROM COM_PRO_PHOTOBOX_MESSAGE WHERE PHOTOBOX_ID =?", id ) ;
			data.setiTotalDisplayRecords(total);
			data.setiTotalRecords(total);
			
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_PHOTOBOX_MESSAGE WHERE PHOTOBOX_ID =? ORDER BY ID DESC LIMIT ?,?", id, start,
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
	@RequestMapping(value = "/list.spring", method = RequestMethod.POST)
	public @ResponseBody
	DatatableReponse list(@RequestParam(value = "iDisplayStart", required = false) Integer start,
			@RequestParam(value = "iDisplayLength", required = false) Integer limit,
			@RequestParam(value = "sEcho", required = false) Integer sEcho,
			@RequestParam(value = "userId", required = false) Integer userId,
			HttpSession session) throws Exception {
		DatatableReponse data = new DatatableReponse();
		try {
			if (userId == null) {
				userId = (Integer)session.getAttribute("ONLINE_MEMBER_ID");
			}
			data.setsEcho(sEcho);
			data.setiDisplayStart(start);
			data.setiDisplayLength(limit);
			int total = jdbcTemplate.queryForInt("SELECT COUNT(1) FROM COM_PRO_PHOTOBOX WHERE USER_ID =?", userId ) ;
			data.setiTotalDisplayRecords(total);
			data.setiTotalRecords(total);
			
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM COM_PRO_PHOTOBOX WHERE USER_ID =? ORDER BY ID DESC LIMIT ?,?", userId, start,
					limit);
			if (!CommonUtils.isEmptyList(rows)) {
				List<PhotoBoxBean> beanList = new ArrayList<PhotoBoxBean>();
				for (Map<String, Object> row : rows) {
					PhotoBoxBean bean = new PhotoBoxBean();
					bean.setId((Integer) row.get("ID"));
					bean.setName((String)row.get("NAME"));
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
