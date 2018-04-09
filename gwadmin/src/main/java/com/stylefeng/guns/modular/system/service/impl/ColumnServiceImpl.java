package com.stylefeng.guns.modular.system.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.stylefeng.guns.common.persistence.model.Columns;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.dao.ColumnDao;
import com.stylefeng.guns.modular.system.service.IColumnService;

/**
 * 栏目Service
 *
 * @author fengshuonan
 * @Date 2017-10-20 16:58:06
 */
@Service
public class ColumnServiceImpl implements IColumnService {

	@Resource
	ColumnDao columnDao;
	
	@Override
	public List<Map<String, Object>> list(String condition,Integer istop,Integer id,Integer deptid) {
		// TODO 自动生成的方法存根
		return columnDao.list(condition,istop,id,deptid);
	}

	@Override
	public List<ZTreeNode> tree(Integer deptid) {
		// TODO 自动生成的方法存根
		return columnDao.tree(deptid);
	}


}
