package com.pro.controller.resource;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.pro.pojo.CityBean;
import com.pro.pojo.MemberBean;
import com.pro.pojo.SubjectBean;
import com.pro.pojo.UniversityBean;
import com.pro.service.CityService;
import com.pro.service.MemberService;
import com.pro.service.SubjectService;
import com.pro.service.UniversityService;

@Controller
@RequestMapping(value = "/resource")
public class ResourceController {

	@Autowired
	CityService cityService;
	
	@Autowired
	UniversityService universityService;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	MemberService memberService;

	/**
	 * @param provinceId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loadCities.spring", method = RequestMethod.GET)
	@ResponseBody
	public List<CityBean> loadCities(@RequestParam(value = "provinceId", required = false) Integer provinceId) throws Exception {
		return cityService.findByProvince(provinceId);
	}
	
	/**
	 * @param cityId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loadUniversities.spring", method = RequestMethod.GET)
	@ResponseBody
	public List<UniversityBean> loadUniverties(@RequestParam(value = "cityId", required = false) Integer cityId) throws Exception {
		return universityService.findByCityId(cityId);
	}
	
	/**
	 * @param universityId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loadSubjects.spring", method = RequestMethod.GET)
	@ResponseBody
	public List<SubjectBean> loadSubjects(@RequestParam(value = "universityId", required = false) Integer universityId) throws Exception {
		return subjectService.findByUniversityId(universityId);
	}
	
	@RequestMapping(value = "/ajaxValidate.spring", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> ajaxValidate(@RequestParam(value = "userName", required = false) String userName,  
			@RequestParam(value = "stuNo", required = false) String stuNo) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		String message = "";
		MemberBean member = memberService.findByStuNo(stuNo);
		if(member != null) {
			message += "学号已存在！\n";
		}
		
		member = memberService.findByUserName(userName);
		if(member != null) {
			message += "用户名已存在！\n";
		}
		map.put("message", message);
		return map;
	}
}
