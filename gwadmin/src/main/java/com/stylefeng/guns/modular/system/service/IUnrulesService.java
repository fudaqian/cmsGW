package com.stylefeng.guns.modular.system.service;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.common.persistence.model.Columns;
import com.stylefeng.guns.core.node.ZTreeNode;

/**
 * 礼品Service
 *
 * @author fengshuonan
 * @Date 2017-10-20 16:58:06
 */
public interface IUnrulesService {

	List<Map<String, Object>> list(Integer pagenum, Integer pagesize, String condition, String sn, String unruleCompany,  String attributeCompany);

	Integer countPresents(String condition);

}
