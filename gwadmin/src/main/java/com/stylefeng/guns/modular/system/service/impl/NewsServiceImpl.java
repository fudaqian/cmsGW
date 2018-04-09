package com.stylefeng.guns.modular.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.stylefeng.guns.modular.system.dao.NewsDao;
import com.stylefeng.guns.modular.system.dao.PresentDao;
import com.stylefeng.guns.modular.system.service.INewsService;
import com.stylefeng.guns.modular.system.service.IPresentService;

@Service
public class NewsServiceImpl implements INewsService {

	@Resource
	NewsDao newsDao;
	
	@Override
	public List<Map<String, Object>> list(String condition,Integer cloumnId,Integer id, Integer istop,Integer pagenum, Integer pagesize,List<Integer> cloumns) {
		// TODO 自动生成的方法存根
		if(pagenum != null && pagesize != null){
			pagenum = (pagenum - 1)*pagesize;
		}	
    	List<Map<String, Object>> temp =  newsDao.list(condition,cloumnId,id,istop,pagenum,pagesize,cloumns);
    	List<Map<String, Object>> resultList = new ArrayList<>();
    	for(Map<String, Object> map :temp ){
    		String image = "/kaptcha/"+map.get("image");
    		String html = String.valueOf(map.get("html_content"));
    		String subtitle = String.valueOf(map.get("subtitle"));
    		String titleTemp = String.valueOf(map.get("title"));
    		
    		html = HtmlUtils.htmlUnescape(html); 		
    		titleTemp = HtmlUtils.htmlUnescape(titleTemp);    		
    		subtitle = HtmlUtils.htmlUnescape(subtitle);
    		
    		String content = stripHtml(html);
    		map.put("image", image);
    		map.put("html_content", html);
    		map.put("content", content);
    		map.put("title", titleTemp);
    		map.put("subtitle", subtitle);
    		resultList.add(map);
    	}    	
    	return resultList;
	}
	
	@Override
	public List<Map<String, Object>> adminList(String condition,Integer cloumnId,Integer id, Integer istop,Integer pagenum, Integer pagesize,List<Integer> cloumns) {
		// TODO 自动生成的方法存根
		
		List<Map<String, Object>> temp =  newsDao.list(condition,cloumnId,id,istop,pagenum,pagesize,cloumns);
		List<Map<String, Object>> resultList = new ArrayList<>();
		for(Map<String, Object> map :temp ){
			String image = "/kaptcha/"+map.get("image");
			String html = String.valueOf(map.get("html_content"));
			String subtitle = String.valueOf(map.get("subtitle"));
			String titleTemp = String.valueOf(map.get("title"));
			
			html = HtmlUtils.htmlUnescape(html); 		
			titleTemp = HtmlUtils.htmlUnescape(titleTemp);    		
			subtitle = HtmlUtils.htmlUnescape(subtitle);
			
			String content = stripHtml(html);
			map.put("image", image);
			map.put("html_content", html);
			map.put("content", content);
			map.put("title", titleTemp);
			map.put("subtitle", subtitle);
			resultList.add(map);
		}    	
		return resultList;
	}
	
	public static String stripHtml(String content) { 
		// <p>段落替换为换行 
		content = content.replaceAll("<p .*?>", ""); 
		// <br><br/>替换为换行 
		content = content.replaceAll("<br\\s*/?>", ""); 
		// 去掉其它的<>之间的东西 
		content = content.replaceAll("\\<.*?>", ""); 
		// 还原HTML 
		// content = HTMLDecoder.decode(content); 
		return content; 
	}

	@Override
	public Integer countNews(Integer cloumnId,String condition, List<Integer> cloumns) {
		// TODO 自动生成的方法存根
		return newsDao.countNews(cloumnId,condition,cloumns);
	}
}
