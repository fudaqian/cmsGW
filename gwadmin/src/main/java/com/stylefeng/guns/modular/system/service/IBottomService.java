package com.stylefeng.guns.modular.system.service;

import java.util.List;
import java.util.Map;

public interface IBottomService {
	List<Map<String, Object>> urlList(Integer bottomUrlId,Integer deptid);
	List<Map<String, Object>> companyList(Integer deptid);
	Integer updateCompany(Integer id, String place, String phone, String email, String copyright_information, String fax);
	Integer bottomUrlRemove(Integer bottomUrlId);
	Integer updateBottomUrl(Integer id, String url, String urlName);
	Integer addBottomUrl(Integer id, String url, String urlName, Integer deptid);
	Integer insertCompany(String place, String phone, String email, String copyright_information, Integer deptid, String fax);
}
