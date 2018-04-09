package com.stylefeng.guns.modular.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.stylefeng.guns.modular.system.dao.BottomDao;
import com.stylefeng.guns.modular.system.service.IBottomService;

@Service
public class BottomServiceImpl implements IBottomService {

	@Resource
	BottomDao bottomDao;
	
	@Override
	public List<Map<String, Object>> urlList(Integer bottomUrlId,Integer deptid) {
		// TODO 自动生成的方法存根
		return bottomDao.urlList(bottomUrlId,deptid);
	}

	@Override
	public List<Map<String, Object>> companyList(Integer deptid) {
		// TODO 自动生成的方法存根
		List<Map<String, Object>> temp =  bottomDao.companyList(deptid);
    	List<Map<String, Object>> resultList = new ArrayList<>();
    	for(Map<String, Object> map :temp ){
			String placeTemp = String.valueOf(map.get("copyright_information"));
			placeTemp = HtmlUtils.htmlUnescape(placeTemp);
			map.put("copyright_information", placeTemp);
			resultList.add(map);
    	}
		return resultList;
	}

	@Override
	public Integer updateCompany(Integer id, String place, String phone, String email, String copyright_information, String fax) {
		// TODO 自动生成的方法存根
		return bottomDao.updateCompany(id,place,email,phone,copyright_information,fax);
	}

	@Override
	public Integer bottomUrlRemove(Integer bottomUrlId) {
		// TODO 自动生成的方法存根
		return bottomDao.bottomUrlRemove(bottomUrlId);
	}

	@Override
	public Integer updateBottomUrl(Integer id, String url, String urlName) {
		// TODO 自动生成的方法存根
		return bottomDao.updateBottomUrl(id,url,urlName);
	}

	@Override
	public Integer addBottomUrl(Integer id, String url, String urlName ,Integer deptid) {
		// TODO 自动生成的方法存根
		return bottomDao.addBottomUrl(id,url,urlName,deptid);
	}

	@Override
	public Integer insertCompany(String place, String phone, String email, String copyright_information, Integer deptid, String fax) {
		// TODO 自动生成的方法存根
		return bottomDao.insertCompany(place,phone,email,copyright_information,deptid,fax);
	}

}
