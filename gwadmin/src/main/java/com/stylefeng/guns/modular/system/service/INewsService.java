package com.stylefeng.guns.modular.system.service;

import java.util.List;
import java.util.Map;

/**
 * 栏目Service
 *
 * @author fengshuonan
 * @Date 2017-10-20 16:58:06
 */
public interface INewsService {

	List<Map<String, Object>> list(String condition,Integer cloumnId, Integer id, Integer istop, Integer pagenum, Integer pagesize, List<Integer> cloumns);
	
	List<Map<String, Object>> adminList(String condition,Integer cloumnId, Integer id, Integer istop, Integer pagenum, Integer pagesize, List<Integer> cloumns);

	Integer countNews(Integer cloumnId, String condition, List<Integer> cloumns);

}
