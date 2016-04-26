package com.jeefw.dao.sys.impl;

import org.springframework.stereotype.Repository;

import com.jeefw.dao.sys.VoterDao;
import com.jeefw.model.sys.TVoter;

import core.dao.BaseDao;

/**
 * 字典的数据持久层的实现类
 * administrator
 */
@Repository
public class VoterDaoImpl extends BaseDao<TVoter> implements VoterDao {

	public VoterDaoImpl() {
		super(TVoter.class);
	}

}
