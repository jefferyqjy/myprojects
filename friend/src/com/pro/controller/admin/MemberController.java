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
import com.pro.pojo.MemberBean;
import com.pro.pojo.datatable.MemberDatatable;
import com.pro.service.MemberService;
import com.pro.service.UniversityService;
import com.pro.service.InterestService;

@Controller
@RequestMapping(value = "/admin/member")
public class MemberController {

	@Autowired
	MemberService memberService;

	@Autowired
	UniversityService universityService;

	@Autowired
	InterestService interestService;

	@RequestMapping(value = "/view.spring", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/member/view";
		// add other logic.
		try {
			MemberBean memberBean = memberService.get(id);
			mav.addObject("memberBean", memberBean);
			mav.addObject("InterestBeanList", memberService.getInterest(id));
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/listAll.spring", method = RequestMethod.GET)
	public ModelAndView listAll() throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/member/list";
		try {
			List<MemberBean> list = memberService.list();
			mav.addObject("MEMBERLIST", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/list.spring", method = RequestMethod.POST)
	public @ResponseBody
	MemberDatatable list(@RequestParam(value = "iDisplayStart", required = false) Integer start,
			@RequestParam(value = "iDisplayLength", required = false) Integer limit,
			@RequestParam(value = "sEcho", required = false) Integer sEcho) throws Exception {
		MemberDatatable data = new MemberDatatable();
		try {
			data.setsEcho(sEcho);
			data.setiDisplayStart(start);
			data.setiDisplayLength(limit);
			int total = memberService.getTotalRecords();
			data.setiTotalDisplayRecords(total);
			data.setiTotalRecords(total);
			data.setAaData(memberService.list(start, limit));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@RequestMapping(value = "/preAdd.spring", method = RequestMethod.GET)
	public ModelAndView preAdd() throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/member/add";
		// add other logic.
		mav.setViewName(pageUrl);
		mav.addObject("memberBean", new MemberBean());
		mav.addObject("UniversityBeanList", universityService.list());
		mav.addObject("InterestBeanList", interestService.list());
		return mav;
	}

	@RequestMapping(value = "/add.spring", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute("memberBean") MemberBean memberBean, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/member/add";
		mav.addObject("memberBean", memberBean);
		mav.addObject("UniversityBeanList", universityService.list());
		mav.addObject("InterestBeanList", interestService.list());
		try {
			String[] values = request.getParameterValues("interestCheckBox");
			for (String idValue : values) {
				com.pro.pojo.InterestBean bean = new com.pro.pojo.InterestBean();
				bean.setId(Integer.parseInt(idValue));
				memberBean.getInterest().add(bean);
			}
			if (memberService.add(memberBean)) {
				mav.addObject("MESSAGE", "Add Member Successfully");
			} else {
				mav.addObject("MESSAGE", "Add Member Failed");
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
		String pageUrl = "admin/member/update";
		// add other logic.
		try {
			MemberBean memberBean = memberService.get(id);
			mav.addObject("memberBean", memberBean);
			mav.addObject("UniversityBeanList", universityService.list());
			mav.addObject("InterestBeanList", memberService.getInterest(id));
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/update.spring", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("memberBean") MemberBean memberBean, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/member/update";
		try {
			String[] values = request.getParameterValues("interestCheckBox");
			for (String idValue : values) {
				com.pro.pojo.InterestBean bean = new com.pro.pojo.InterestBean();
				bean.setId(Integer.parseInt(idValue));
				memberBean.getInterest().add(bean);
			}
			if (memberService.update(memberBean)) {
				mav.addObject("MESSAGE", "Update Member Successfully");
			} else {
				mav.addObject("MESSAGE", "Update Member Failed");
			}
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.addObject("memberBean", memberBean);
		mav.addObject("UniversityBeanList", universityService.list());
		mav.addObject("InterestBeanList", memberService.getInterest(memberBean.getId()));
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/delete.spring", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/member/list";
		try {
			if (memberService.delete(id)) {
				mav.addObject("MESSAGE", "Delete Member Successfully");
			} else {
				mav.addObject("MESSAGE", "Delete Member Failed");
			}
			List<MemberBean> list = memberService.list();
			mav.addObject("MEMBERLIST", list);
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}
	
	@RequestMapping(value = "/valid.spring", method = RequestMethod.GET)
	public ModelAndView valid(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/member/list";
		try {
			if (memberService.valid(id)) {
				mav.addObject("MESSAGE", "Valid Member Successfully");
			} else {
				mav.addObject("MESSAGE", "Valid Member Failed");
			}
			List<MemberBean> list = memberService.list();
			mav.addObject("MEMBERLIST", list);
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}
	
	@RequestMapping(value = "/invalid.spring", method = RequestMethod.GET)
	public ModelAndView invalid(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/member/list";
		try {
			if (memberService.invalid(id)) {
				mav.addObject("MESSAGE", "Invalid Member Successfully");
			} else {
				mav.addObject("MESSAGE", "Invalid Member Failed");
			}
			List<MemberBean> list = memberService.list();
			mav.addObject("MEMBERLIST", list);
		} catch (ProException e) {
			e.printStackTrace();
		}
		mav.setViewName(pageUrl);
		return mav;
	}
}
