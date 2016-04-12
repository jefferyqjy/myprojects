package com.pro.controller.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pro.pojo.CityBean;
import com.pro.pojo.SubjectBean;
import com.pro.pojo.UniversityBean;
import com.pro.service.CityService;
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

	/**
	 * 加载城市列表
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
	 * 加载大学列表
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
	 * 加载专业列表
	 * @param universityId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loadSubjects.spring", method = RequestMethod.GET)
	@ResponseBody
	public List<SubjectBean> loadSubjects(@RequestParam(value = "universityId", required = false) Integer universityId) throws Exception {
		return subjectService.findByUniversityId(universityId);
	}
}
