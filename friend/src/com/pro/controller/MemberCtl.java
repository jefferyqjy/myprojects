package com.pro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pro.enums.Gender;
import com.pro.exception.ProException;
import com.pro.pojo.MemberBean;
import com.pro.service.CityService;
import com.pro.service.InterestService;
import com.pro.service.MemberService;
import com.pro.service.ProvinceService;
import com.pro.service.SubjectService;
import com.pro.service.UniversityService;

@Controller
@RequestMapping(value = "/member")
public class MemberCtl {
	

	@Autowired
	MemberService memberService;
	
	@Autowired
	UniversityService universityService;

	@Autowired
	InterestService interestService;
	
	@Autowired
	ProvinceService provinceService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	SubjectService subjectService;
	
	@RequestMapping(value = "/preUpdate.spring", method = RequestMethod.GET)
	public ModelAndView preUpdate(HttpSession session) throws Exception {
		Integer id = (Integer)session.getAttribute("ONLINE_MEMBER_ID");
		ModelAndView mav = new ModelAndView();
		String pageUrl = "userInfo";
		// add other logic.
		try {
			MemberBean memberBean = memberService.get(id);
			mav.addObject("memberBean", memberBean);
			mav.addObject("UniversityBeanList", universityService.list());
			mav.addObject("InterestBeanList", memberService.getInterest(id));
			mav.addObject("ProvinceBeanList", provinceService.list());
			mav.addObject("CityBeanList", cityService.list());
			mav.addObject("SubjectBeanList", subjectService.list());
			mav.addObject("genders", Gender.values());
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
		String pageUrl = "userInfo";
		try {
			String[] values = request.getParameterValues("interestCheckBox");
			if (values != null)
				for (String idValue : values) {
					com.pro.pojo.InterestBean bean = new com.pro.pojo.InterestBean();
					bean.setId(Integer.parseInt(idValue));
					memberBean.getInterest().add(bean);
				}
			if (memberService.update(memberBean)) {
				mav.addObject("MESSAGE", "更新会员信息成功！");
			} else {
				mav.addObject("MESSAGE", "更新会员信息失败！");
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

	@RequestMapping(value = "/preAdd.spring", method = RequestMethod.GET)
	public ModelAndView preAdd() throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "register";
		// add other logic.
		mav.setViewName(pageUrl);
		mav.addObject("memberBean", new MemberBean());
		mav.addObject("UniversityBeanList", universityService.list());
		mav.addObject("InterestBeanList", interestService.list());
		mav.addObject("ProvinceBeanList", provinceService.list());
		mav.addObject("CityBeanList", cityService.list());
		mav.addObject("SubjectBeanList", subjectService.list());
		mav.addObject("AllInterestBeanList", interestService.list());
		return mav;
	}
	
	@RequestMapping(value = "/add.spring", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute("memberBean") MemberBean memberBean, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "register";
		mav.addObject("memberBean", memberBean);
		mav.addObject("UniversityBeanList", universityService.list());
		mav.addObject("InterestBeanList", interestService.list());
		try {
			
			if (memberService.add(memberBean)) {
				mav.addObject("MESSAGE", "注册成功");
			} else {
				mav.addObject("MESSAGE", "注册失败");
			}
		} catch (ProException e) {
			e.printStackTrace();
			mav.addObject("MESSAGE", "注册失败:" + e.getMessage());
		}
		mav.setViewName(pageUrl);
		return mav;
	}
	
	@RequestMapping(value = "/login.spring", method = RequestMethod.POST)
	public 
	String login(HttpServletRequest request) throws Exception {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		MemberBean member = memberService.getBlackMember(userName);
		if(member != null) {
			request.setAttribute("MESSAGE", "帐号已被举报，若举报错误请联系管理员！QQ：775261002");
			request.setAttribute("userName", userName);
			return "home";
		}
		
		int userId = memberService.login(userName, password);
		if (memberService.login(userName, password) > 0) {
			request.getSession().setAttribute("ONLINE_MEMBER", userName);
			request.getSession().setAttribute("ONLINE_MEMBER_ID", userId);
		} else {
			request.setAttribute("MESSAGE", "用户名或者密码错误");
		}
		
		return "home";
	}
	
	@RequestMapping(value = "/logout.spring", method = RequestMethod.POST)
	public 
	String logout(HttpServletRequest request) throws Exception {
		request.getSession().removeAttribute("ONLINE_MEMBER");
		request.getSession().invalidate();
		return "home";
	}
}
