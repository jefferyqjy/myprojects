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
import com.pro.pojo.UniversityBean;
import com.pro.pojo.datatable.UniversityDatatable;
import com.pro.service.UniversityService;
import com.pro.service.CityService;

@Controller
@RequestMapping(value = "/admin/university")
public class UniversityController {

	@Autowired
	UniversityService universityService;

	@Autowired
	CityService cityService;

	@RequestMapping(value = "/view.spring", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/university/view";
		// add other logic.
		try {
			UniversityBean universityBean = universityService.get(id);
			mav.addObject("universityBean", universityBean);
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/listAll.spring", method = RequestMethod.GET)
	public ModelAndView listAll() throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/university/list";
		try {
			List<UniversityBean> list = universityService.list();
			mav.addObject("UNIVERSITYLIST", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/list.spring", method = RequestMethod.POST)
	public @ResponseBody
	UniversityDatatable list(@RequestParam(value = "iDisplayStart", required = false) Integer start,
			@RequestParam(value = "iDisplayLength", required = false) Integer limit,
			@RequestParam(value = "sEcho", required = false) Integer sEcho) throws Exception {
		UniversityDatatable data = new UniversityDatatable();
		try {
			data.setsEcho(sEcho);
			data.setiDisplayStart(start);
			data.setiDisplayLength(limit);
			int total = universityService.getTotalRecords();
			data.setiTotalDisplayRecords(total);
			data.setiTotalRecords(total);
			data.setAaData(universityService.list(start, limit));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@RequestMapping(value = "/preAdd.spring", method = RequestMethod.GET)
	public ModelAndView preAdd() throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/university/add";
		// add other logic.
		mav.setViewName(pageUrl);
		mav.addObject("universityBean", new UniversityBean());
		mav.addObject("CityBeanList", cityService.list());
		return mav;
	}

	@RequestMapping(value = "/add.spring", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute("universityBean") UniversityBean universityBean, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/university/add";
		mav.addObject("universityBean", universityBean);
		mav.addObject("CityBeanList", cityService.list());
		try {
			if (universityService.add(universityBean)) {
				mav.addObject("MESSAGE", "Add University Successfully");
			} else {
				mav.addObject("MESSAGE", "Add University Failed");
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
		String pageUrl = "admin/university/update";
		// add other logic.
		try {
			UniversityBean universityBean = universityService.get(id);
			mav.addObject("universityBean", universityBean);
			mav.addObject("CityBeanList", cityService.list());
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/update.spring", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("universityBean") UniversityBean universityBean,
			HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/university/update";
		try {
			if (universityService.update(universityBean)) {
				mav.addObject("MESSAGE", "Update University Successfully");
			} else {
				mav.addObject("MESSAGE", "Update University Failed");
			}
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.addObject("universityBean", universityBean);
		mav.addObject("CityBeanList", cityService.list());
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/delete.spring", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/university/list";
		try {
			if (universityService.delete(id)) {
				mav.addObject("MESSAGE", "Delete University Successfully");
			} else {
				mav.addObject("MESSAGE", "Delete University Failed");
			}
			List<UniversityBean> list = universityService.list();
			mav.addObject("UNIVERSITYLIST", list);
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}
}
