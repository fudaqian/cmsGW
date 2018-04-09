package com.stylefeng.guns.modular.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.stylefeng.guns.modular.system.dao.PresentDao;
import com.stylefeng.guns.modular.system.service.IPresentService;

@Service
public class PresentServiceImpl implements IPresentService {

	@Resource
	PresentDao presentDao;
	
	@Override
	public List<Map<String, Object>> list(String condition, Integer pagenum, Integer pagesize, String sn, String name, String company, String beginTime, String endTime) {
		// TODO 自动生成的方法存
		if(pagenum != null && pagesize != null){
			pagenum = (pagenum - 1)*pagesize;
		}	
		List<Map<String, Object>> temp =  presentDao.list(condition,pagenum,pagesize,sn,name,company,beginTime,endTime);
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
		return presentDao.countPresents(condition);
	}

}
