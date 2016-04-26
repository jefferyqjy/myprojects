package com.jeefw.service.sys;

import java.util.List;

import com.jeefw.model.sys.Dict;
import com.jeefw.model.sys.TActivity;
import com.jeefw.model.sys.TCandidate;

import core.service.Service;

/**
 * 字典的业务逻辑层的接口
 * administrator
 */
public interface CandidateService extends Service<TCandidate> {

	List<TCandidate> queryDictWithSubList(List<TCandidate> resultList);

}
