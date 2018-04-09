package com.stylefeng.guns.modular.system.service;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.common.persistence.model.Columns;
import com.stylefeng.guns.core.node.ZTreeNode;

/**
 * 栏目Service
 *
 * @author fengshuonan
 * @Date 2017-10-20 16:58:06
 */

public interface IColumnService {

	List<Map<String, Object>> list(String condition, Integer istop, Integer id, Integer deptid);

	List<ZTreeNode> tree(Integer deptid);

}
