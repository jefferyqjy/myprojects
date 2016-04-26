package com.jeefw.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeefw.dao.sys.ActivityDao;
import com.jeefw.dao.sys.VoterDao;
import com.jeefw.model.sys.TVoter;
import com.jeefw.service.sys.VoterService;

import core.service.BaseService;

/**
 * 字典的业务逻辑层的实现
 * administrator
 */
@Service
public class VoterServiceImpl extends BaseService<TVoter> implements VoterService {

	private VoterDao voterdao;

	@Resource
	public void setActivityDao(VoterDao voterdao) {
		this.voterdao = voterdao;
		this.dao = voterdao;
	}

	public List<TVoter> queryDictWithSubList(List<TVoter> resultList) {
		List<TVoter> alist = new ArrayList<TVoter>();
		for (TVoter entity : resultList) {
			TVoter tvoter = new TVoter();
			tvoter.setId(entity.getId());
			tvoter.setName(entity.getName());
			tvoter.setVoterip(entity.getVoterip());
			tvoter.setVotertime(entity.getVotertime());
			alist.add(tvoter);
		}
		return alist;
	}

	

}
