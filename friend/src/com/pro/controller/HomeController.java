package com.pro.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pro.utils.CommonUtils;

@Controller
public class HomeController {

	@RequestMapping(value = "/home.spring", method = RequestMethod.GET)
	public ModelAndView home(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "home";
		session.setAttribute("ONLINE_USER", CommonUtils.getCurrentUserName());
		mav.setViewName(pageUrl);
		return mav;
	}

	@RequestMapping(value = "/admin/home.spring", method = RequestMethod.GET)
	public ModelAndView adminHome(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pageUrl = "admin/home";
		session.setAttribute("ONLINE_USER", CommonUtils.getCurrentUserName());
		mav.setViewName(pageUrl);
		return mav;
	}
}
