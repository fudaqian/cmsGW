package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.common.persistence.model.Columns;
import com.stylefeng.guns.core.node.ZTreeNode;

/**
 * 投诉Dao
 *
 * @author fengshuonan
 * @Date 2017-10-20 16:58:06
 */
public interface ComplainDao {

	List<Map<String, Object>> list(@Param("acceptName")String acceptName, @Param("acceptType")String acceptType, @Param("ischeck")String ischeck,@Param("id") Integer id);

}
