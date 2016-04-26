package com.jeefw.dao.sys.impl;

import org.springframework.stereotype.Repository;

import com.jeefw.dao.sys.ActivityDao;
import com.jeefw.dao.sys.CandidateDao;
import com.jeefw.dao.sys.DictDao;
import com.jeefw.model.sys.Dict;
import com.jeefw.model.sys.TActivity;
import com.jeefw.model.sys.TCandidate;

import core.dao.BaseDao;

/**
 * 字典的数据持久层的实现类
 * administrator
 */
@Repository
public class CandidateDaoImpl extends BaseDao<TCandidate> implements CandidateDao {

	public CandidateDaoImpl() {
		super(TCandidate.class);
	}

}
