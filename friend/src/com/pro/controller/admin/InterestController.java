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
import com.pro.pojo.InterestBean;
import com.pro.pojo.datatable.InterestDatatable;
import com.pro.service.InterestService;

@Controller
@RequestMapping(value = "/admin/interest")
public class InterestController {

	@Autowired
	InterestService interestService;

	@RequestMapping(value = "/view.spring", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/interest/view";
		// add other logic.
		try {
			InterestBean interestBean = interestService.get(id);
			mav.addObject("interestBean", interestBean);
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/listAll.spring", method = RequestMethod.GET)
	public ModelAndView listAll() throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/interest/list";
		try {
			List<InterestBean> list = interestService.list();
			mav.addObject("INTERESTLIST", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/list.spring", method = RequestMethod.POST)
	public @ResponseBody
	InterestDatatable list(@RequestParam(value = "iDisplayStart", required = false) Integer start,
			@RequestParam(value = "iDisplayLength", required = false) Integer limit,
			@RequestParam(value = "sEcho", required = false) Integer sEcho) throws Exception {
		InterestDatatable data = new InterestDatatable();
		try {
			data.setsEcho(sEcho);
			data.setiDisplayStart(start);
			data.setiDisplayLength(limit);
			int total = interestService.getTotalRecords();
			data.setiTotalDisplayRecords(total);
			data.setiTotalRecords(total);
			data.setAaData(interestService.list(start, limit));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@RequestMapping(value = "/preAdd.spring", method = RequestMethod.GET)
	public ModelAndView preAdd() throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/interest/add";
		// add other logic.
		mav.setViewName(pageUrl);
		mav.addObject("interestBean", new InterestBean());
		return mav;
	}

	@RequestMapping(value = "/add.spring", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute("interestBean") InterestBean interestBean, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/interest/add";
		mav.addObject("interestBean", interestBean);
		try {
			if (interestService.add(interestBean)) {
				mav.addObject("MESSAGE", "Add Interest Successfully");
			} else {
				mav.addObject("MESSAGE", "Add Interest Failed");
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
		String pageUrl = "admin/interest/update";
		// add other logic.
		try {
			InterestBean interestBean = interestService.get(id);
			mav.addObject("interestBean", interestBean);
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/update.spring", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("interestBean") InterestBean interestBean, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/interest/update";
		try {
			if (interestService.update(interestBean)) {
				mav.addObject("MESSAGE", "Update Interest Successfully");
			} else {
				mav.addObject("MESSAGE", "Update Interest Failed");
			}
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.addObject("interestBean", interestBean);
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/delete.spring", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/interest/list";
		try {
			if (interestService.delete(id)) {
				mav.addObject("MESSAGE", "Delete Interest Successfully");
			} else {
				mav.addObject("MESSAGE", "Delete Interest Failed");
			}
			List<InterestBean> list = interestService.list();
			mav.addObject("INTERESTLIST", list);
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}
}
