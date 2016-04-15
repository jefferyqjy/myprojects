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
import com.pro.pojo.ProvinceBean;
import com.pro.pojo.datatable.CityDatatable;
import com.pro.service.ProvinceService;

@Controller
@RequestMapping(value = "/admin/province")
public class ProvinceController {

	@Autowired
	ProvinceService provinceService;

	@RequestMapping(value = "/view.spring", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/province/view";
		// add other logic.
		try {
			ProvinceBean provinceBean = provinceService.get(id);
			mav.addObject("provinceBean", provinceBean);
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/listAll.spring", method = RequestMethod.GET)
	public ModelAndView listAll() throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/province/list";
		try {
			List<ProvinceBean> list = provinceService.list();
			mav.addObject("PROVINCELIST", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/list.spring", method = RequestMethod.POST)
	public @ResponseBody
	CityDatatable list(@RequestParam(value = "iDisplayStart", required = false) Integer start,
			@RequestParam(value = "iDisplayLength", required = false) Integer limit,
			@RequestParam(value = "sEcho", required = false) Integer sEcho) throws Exception {
		CityDatatable data = new CityDatatable();
		try {
			data.setsEcho(sEcho);
			data.setiDisplayStart(start);
			data.setiDisplayLength(limit);
			int total = provinceService.getTotalRecords();
			data.setiTotalDisplayRecords(total);
			data.setiTotalRecords(total);
			data.setAaData(provinceService.list(start, limit));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@RequestMapping(value = "/preAdd.spring", method = RequestMethod.GET)
	public ModelAndView preAdd() throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/province/add";
		// add other logic.
		mav.setViewName(pageUrl);
		mav.addObject("provinceBean", new ProvinceBean());
		return mav;
	}

	@RequestMapping(value = "/add.spring", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute("provinceBean") ProvinceBean provinceBean, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/province/add";
		mav.addObject("provinceBean", provinceBean);
		try {
			if (provinceService.add(provinceBean)) {
				mav.addObject("MESSAGE", "添加省份成功！");
			} else {
				mav.addObject("MESSAGE", "添加省份失败！");
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
		String pageUrl = "admin/province/update";
		// add other logic.
		try {
			ProvinceBean provinceBean = provinceService.get(id);
			mav.addObject("provinceBean", provinceBean);
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/update.spring", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("provinceBean") ProvinceBean provinceBean, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/province/update";
		try {
			if (provinceService.update(provinceBean)) {
				mav.addObject("MESSAGE", "更新省份成功！");
			} else {
				mav.addObject("MESSAGE", "更新省份失败！");
			}
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.addObject("provinceBean", provinceBean);
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/delete.spring", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/province/list";
		try {
			if (provinceService.delete(id)) {
				mav.addObject("MESSAGE", "删除省份成功！");
			} else {
				mav.addObject("MESSAGE", "删除省份失败！");
			}
			List<ProvinceBean> list = provinceService.list();
			mav.addObject("PROVINCELIST", list);
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}
	
}
