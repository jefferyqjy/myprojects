package com.jeefw.service.sys.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeefw.dao.sys.ActivityDao;
import com.jeefw.dao.sys.DictDao;
import com.jeefw.model.sys.Dict;
import com.jeefw.model.sys.TActivity;
import com.jeefw.service.sys.ActivityService;
import com.jeefw.service.sys.DictService;

import core.service.BaseService;

/**
 * 字典的业务逻辑层的实现
 * administrator
 */
@Service
public class ActivityServiceImpl extends BaseService<TActivity> implements ActivityService {

	private ActivityDao activityDao;

	@Resource
	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
		this.dao = activityDao;
	}

	public List<TActivity> queryDictWithSubList(List<TActivity> resultList) {
		List<TActivity> alist = new ArrayList<TActivity>();
		for (TActivity entity : resultList) {
			TActivity tactivity = new TActivity();
			tactivity.setId(entity.getId());
			tactivity.setTitle(entity.getTitle());
			tactivity.setPromoter(entity.getPromoter()); 
			tactivity.setReviewed(entity.getReviewed());
			alist.add(tactivity);
		}
		return alist;
	}

	

}
