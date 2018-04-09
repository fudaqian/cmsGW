package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.common.persistence.model.Columns;
import com.stylefeng.guns.core.node.ZTreeNode;

/**
 * 栏目Dao
 *
 * @author fengshuonan
 * @Date 2017-10-20 16:58:06
 */
public interface ColumnDao {

	List<Map<String, Object>> list(@Param("condition")String condition, @Param("istop")Integer istop,@Param("id") Integer id,@Param("deptid") Integer deptid);

	List<ZTreeNode> tree(@Param("deptid") Integer deptid);

	List<Integer> getColumnByDept(@Param("deptid") Integer deptid);
}
