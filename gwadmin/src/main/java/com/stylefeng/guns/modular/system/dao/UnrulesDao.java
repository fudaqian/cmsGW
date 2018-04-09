package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.common.persistence.model.Columns;
import com.stylefeng.guns.core.node.ZTreeNode;

/**
 * 违规合作Dao
 *
 * @author fengshuonan
 * @Date 2017-10-20 16:58:06
 */
public interface UnrulesDao {

	List<Map<String, Object>> list(@Param("pagenum") Integer pagenum, @Param("pagesize") Integer pagesize,
			@Param("condition") String condition, @Param("sn") String sn, @Param("unruleCompany") String unruleCompany,
			@Param("attributeCompany") String attributeCompany);

	Integer countPresents(@Param("condition") String condition);

}
