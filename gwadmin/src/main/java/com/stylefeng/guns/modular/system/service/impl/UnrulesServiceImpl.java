package com.stylefeng.guns.modular.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.stylefeng.guns.modular.system.dao.UnrulesDao;
import com.stylefeng.guns.modular.system.service.IUnrulesService;

@Service
public class UnrulesServiceImpl implements IUnrulesService {

	@Resource
	UnrulesDao unrulesDao;
	
	@Override
	public List<Map<String, Object>> list(Integer pagenum, Integer pagesize, String condition, String sn, String unruleCompany,  String attributeCompany) {
		// TODO 自动生成的方法存
		if(pagenum != null && pagesize != null){
			pagenum = (pagenum - 1)*pagesize;
		}	
		List<Map<String, Object>> temp =  unrulesDao.list(pagenum,pagesize, condition, sn, unruleCompany, attributeCompany);
    	List<Map<String, Object>> resultList = new ArrayList<>();
    	for(Map<String, Object> map :temp ){
    		String html = String.valueOf(map.get("process_result"));
    		html = NewsServiceImpl.stripHtml(HtmlUtils.htmlUnescape(html));
    		map.put("process_result", html);

    		resultList.add(map);
    	}    	
    	return resultList;
	}

	@Override
	public Integer countPresents(String condition) {
		// TODO 自动生成的方法存根
		return unrulesDao.countPresents(condition);
	}

}
