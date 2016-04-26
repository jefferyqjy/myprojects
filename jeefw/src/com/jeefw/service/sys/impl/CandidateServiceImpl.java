package com.jeefw.service.sys.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeefw.dao.sys.ActivityDao;
import com.jeefw.dao.sys.CandidateDao;
import com.jeefw.dao.sys.DictDao;
import com.jeefw.model.sys.Dict; 
import com.jeefw.model.sys.TCandidate;
import com.jeefw.service.sys.ActivityService;
import com.jeefw.service.sys.CandidateService;
import com.jeefw.service.sys.DictService;

import core.service.BaseService;

/**
 * 字典的业务逻辑层的实现
 * administrator
 */
@Service
public class CandidateServiceImpl extends BaseService<TCandidate> implements CandidateService {

	private CandidateDao candidatedao;

	@Resource
	public void setCandidateDao(CandidateDao candidatedao) {
		this.candidatedao = candidatedao;
		this.dao = candidatedao;
	}

	public List<TCandidate> queryDictWithSubList(List<TCandidate> resultList) {
		List<TCandidate> alist = new ArrayList<TCandidate>();
		for (TCandidate entity : resultList) {
			TCandidate tactivity = new TCandidate();
			tactivity.setId(entity.getId()); 
			tactivity.setAid(entity.getAid());
			tactivity.setAtitle(entity.getAtitle());
			tactivity.setNumber(entity.getNumber());
			tactivity.setName(entity.getName());
			tactivity.setAge(entity.getAge());
			tactivity.setCandescribe(entity.getCandescribe());
			tactivity.setReviewed(entity.getReviewed());
			
			alist.add(tactivity);
		}
		return alist;
	}

	

}
