package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.common.persistence.model.Columns;
import com.stylefeng.guns.core.node.ZTreeNode;

/**
 * 礼品Dao
 *
 * @author fengshuonan
 * @Date 2017-10-20 16:58:06
 */
public interface PresentDao {

	List<Map<String, Object>> list(@Param("condition") String condition, @Param("pagenum") Integer pagenum,
			@Param("pagesize") Integer pagesize, @Param("sn") String sn, @Param("name") String name,
			@Param("company") String company, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

	Integer countPresents(@Param("condition") String condition);

}
