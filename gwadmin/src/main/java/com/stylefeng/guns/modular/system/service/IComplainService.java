package com.stylefeng.guns.modular.system.service;

import java.util.List;
import java.util.Map;

public interface IComplainService {
	List<Map<String, Object>> list(String acceptName, String acceptType, String ischeck, Integer id);
}
