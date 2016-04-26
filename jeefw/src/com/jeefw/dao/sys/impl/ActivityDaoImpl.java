package com.jeefw.dao.sys.impl;

import org.springframework.stereotype.Repository;

import com.jeefw.dao.sys.ActivityDao;
import com.jeefw.dao.sys.DictDao;
import com.jeefw.model.sys.Dict;
import com.jeefw.model.sys.TActivity;

import core.dao.BaseDao;

/**
 * 字典的数据持久层的实现类
 * administrator
 */
@Repository
public class ActivityDaoImpl extends BaseDao<TActivity> implements ActivityDao {

	public ActivityDaoImpl() {
		super(TActivity.class);
	}

}
