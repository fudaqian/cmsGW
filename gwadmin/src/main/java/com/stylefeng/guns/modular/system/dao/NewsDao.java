package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.common.persistence.model.Columns;
import com.stylefeng.guns.core.node.ZTreeNode;

/**
 * 新闻Dao
 *
 * @author fudaqian
 * @Date 2017-10-20 16:58:06
 */
public interface NewsDao {

	List<Map<String, Object>> list(@Param("condition") String condition, @Param("cloumnId") Integer cloumnId,
			@Param("id") Integer id, @Param("istop") Integer istop, @Param("pagenum") Integer pagenum,
			@Param("pagesize") Integer pagesize,@Param("cloumns") List<Integer> cloumns);
	
	List<Map<String, Object>> adminList(@Param("condition") String condition, @Param("cloumnId") Integer cloumnId,
			@Param("id") Integer id, @Param("istop") Integer istop, @Param("pagenum") Integer pagenum,
			@Param("pagesize") Integer pagesize,@Param("cloumns") List<Integer> cloumns);

	Integer countNews(@Param("cloumnId")Integer cloumnId,@Param("condition") String condition,@Param("cloumns") List<Integer> cloumns);

}
