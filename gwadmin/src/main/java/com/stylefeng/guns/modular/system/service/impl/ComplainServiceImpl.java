package com.stylefeng.guns.modular.system.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.stylefeng.guns.modular.system.dao.ComplainDao;
import com.stylefeng.guns.modular.system.service.IComplainService;

@Service
public class ComplainServiceImpl implements IComplainService {

	@Resource
	ComplainDao complainDao; 
	
	@Override
	public List<Map<String, Object>> list(String acceptName, String acceptType, String ischeck, Integer id) {
		// TODO 自动生成的方法存根
		return complainDao.list( acceptName, acceptType,ischeck,id);
	}

}
