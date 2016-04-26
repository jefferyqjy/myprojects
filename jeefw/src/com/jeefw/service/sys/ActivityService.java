package com.jeefw.service.sys;

import java.util.List;

import com.jeefw.model.sys.Dict;
import com.jeefw.model.sys.TActivity;

import core.service.Service;

/**
 * 字典的业务逻辑层的接口
 * administrator
 */
public interface ActivityService extends Service<TActivity> {

	List<TActivity> queryDictWithSubList(List<TActivity> resultList);

}
