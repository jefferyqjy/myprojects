package com.jeefw.service.sys;

import java.util.List;

import com.jeefw.model.sys.TVoter;

import core.service.Service;

/**
 * 字典的业务逻辑层的接口
 * administrator
 */
public interface VoterService extends Service<TVoter> {

	List<TVoter> queryDictWithSubList(List<TVoter> resultList);

}
