package com.pro.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pro.exception.ProException;
import com.pro.pojo.SubjectBean;
import com.pro.pojo.UniversityBean;
import com.pro.pojo.datatable.SubjectDatatable;
import com.pro.service.SubjectService;
import com.pro.service.UniversityService;

@Controller
@RequestMapping(value = "/admin/subject")
public class SubjectController {

	@Autowired
	SubjectService subjectService;
	
	@Autowired
	UniversityService universityService;
	
	@RequestMapping(value = "/view.spring", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/subject/view";
		// add other logic.
		try {
			SubjectBean subjectBean = subjectService.get(id);
			mav.addObject("subjectBean", subjectBean);
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/listAll.spring", method = RequestMethod.GET)
	public ModelAndView listAll() throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/subject/list";
		try {
			List<SubjectBean> list = subjectService.list();
			mav.addObject("SUBJECTLIST", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/list.spring", method = RequestMethod.POST)
	public @ResponseBody
	SubjectDatatable list(@RequestParam(value = "iDisplayStart", required = false) Integer start,
			@RequestParam(value = "iDisplayLength", required = false) Integer limit,
			@RequestParam(value = "sEcho", required = false) Integer sEcho) throws Exception {
		SubjectDatatable data = new SubjectDatatable();
		try {
			data.setsEcho(sEcho);
			data.setiDisplayStart(start);
			data.setiDisplayLength(limit);
			int total = subjectService.getTotalRecords();
			data.setiTotalDisplayRecords(total);
			data.setiTotalRecords(total);
			data.setAaData(subjectService.list(start, limit));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@RequestMapping(value = "/preAdd.spring", method = RequestMethod.GET)
	public ModelAndView preAdd() throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/subject/add";
		// add other logic.
		mav.setViewName(pageUrl);
		mav.addObject("subjectBean", new SubjectBean());
		
		List<UniversityBean> list = universityService.list();
		mav.addObject("universities", list);
		return mav;
	}

	@RequestMapping(value = "/add.spring", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute("subjectBean") SubjectBean subjectBean, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/subject/add";
		mav.addObject("subjectBean", subjectBean);
		try {
			if (subjectService.add(subjectBean)) {
				mav.addObject("MESSAGE", "添加专业成功！");
			} else {
				mav.addObject("MESSAGE", "添加专业失败！");
			}
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/preUpdate.spring", method = RequestMethod.GET)
	public ModelAndView preUpdate(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/subject/update";
		// add other logic.
		try {
			SubjectBean subjectBean = subjectService.get(id);
			mav.addObject("subjectBean", subjectBean);
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		
		List<UniversityBean> list = universityService.list();
		mav.addObject("universities", list);
		return mav;
	}

	@RequestMapping(value = "/update.spring", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("subjectBean") SubjectBean subjectBean, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/subject/update";
		try {
			if (subjectService.update(subjectBean)) {
				mav.addObject("MESSAGE", "更新专业成功！");
			} else {
				mav.addObject("MESSAGE", "更新专业失败！");
			}
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.addObject("subjectBean", subjectBean);
		List<UniversityBean> list = universityService.list();
		mav.addObject("universities", list);
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/delete.spring", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/subject/list";
		try {
			if (subjectService.delete(id)) {
				mav.addObject("MESSAGE", "删除专业成功！");
			} else {
				mav.addObject("MESSAGE", "删除专业失败！");
			}
			List<SubjectBean> list = subjectService.list();
			mav.addObject("SUBJECTLIST", list);
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}
	
}
