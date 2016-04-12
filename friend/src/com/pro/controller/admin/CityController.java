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
import com.pro.pojo.CityBean;
import com.pro.pojo.datatable.CityDatatable;
import com.pro.service.CityService;

@Controller
@RequestMapping(value = "/admin/city")
public class CityController {

	@Autowired
	CityService cityService;

	@RequestMapping(value = "/view.spring", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/city/view";
		// add other logic.
		try {
			CityBean cityBean = cityService.get(id);
			mav.addObject("cityBean", cityBean);
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/listAll.spring", method = RequestMethod.GET)
	public ModelAndView listAll() throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/city/list";
		try {
			List<CityBean> list = cityService.list();
			mav.addObject("CITYLIST", list);
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
			int total = cityService.getTotalRecords();
			data.setiTotalDisplayRecords(total);
			data.setiTotalRecords(total);
			data.setAaData(cityService.list(start, limit));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@RequestMapping(value = "/preAdd.spring", method = RequestMethod.GET)
	public ModelAndView preAdd() throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/city/add";
		// add other logic.
		mav.setViewName(pageUrl);
		mav.addObject("cityBean", new CityBean());
		return mav;
	}

	@RequestMapping(value = "/add.spring", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute("cityBean") CityBean cityBean, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/city/add";
		mav.addObject("cityBean", cityBean);
		try {
			if (cityService.add(cityBean)) {
				mav.addObject("MESSAGE", "Add City Successfully");
			} else {
				mav.addObject("MESSAGE", "Add City Failed");
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
		String pageUrl = "admin/city/update";
		// add other logic.
		try {
			CityBean cityBean = cityService.get(id);
			mav.addObject("cityBean", cityBean);
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/update.spring", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("cityBean") CityBean cityBean, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/city/update";
		try {
			if (cityService.update(cityBean)) {
				mav.addObject("MESSAGE", "Update City Successfully");
			} else {
				mav.addObject("MESSAGE", "Update City Failed");
			}
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.addObject("cityBean", cityBean);
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/delete.spring", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/city/list";
		try {
			if (cityService.delete(id)) {
				mav.addObject("MESSAGE", "Delete City Successfully");
			} else {
				mav.addObject("MESSAGE", "Delete City Failed");
			}
			List<CityBean> list = cityService.list();
			mav.addObject("CITYLIST", list);
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}
	
	@RequestMapping(value = "/loadCities.spring", method = RequestMethod.GET)
	@ResponseBody
	public List<CityBean> loadCities(@RequestParam(value = "provinceId", required = false) Integer provinceId) throws Exception {
		return cityService.findByProvince(provinceId);
	}
}
