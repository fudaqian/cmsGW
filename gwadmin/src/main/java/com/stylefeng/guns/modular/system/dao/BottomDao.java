package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.common.persistence.model.Columns;
import com.stylefeng.guns.core.node.ZTreeNode;

/**
 * 底部Dao
 *
 * @author fudaqian
 * @Date 2017-10-20 16:58:06
 */
public interface BottomDao {

	List<Map<String, Object>> urlList(@Param("bottomUrlId")Integer bottomUrlId,@Param("deptid")Integer deptid);
	
	List<Map<String, Object>> companyList(@Param("deptid")Integer deptid);

	Integer updateCompany(@Param("id")Integer id, @Param("place")String place, @Param("email")String email, @Param("phone")String phone, @Param("copyright_information")String copyright_information,@Param("fax")  String fax);

	Integer bottomUrlRemove(@Param("id")Integer bottomUrlId);

	Integer updateBottomUrl(@Param("id")Integer id, @Param("url")String url, @Param("urlName")String urlName);

	Integer addBottomUrl(@Param("id")Integer id, @Param("url")String url, @Param("urlName")String urlName,@Param("deptid") Integer deptid);

	Integer insertCompany(@Param("place")String place, @Param("email")String email, @Param("phone")String phone, @Param("copyright_information")String copyright_information,@Param("deptid") Integer deptid,@Param("fax") String fax);
}
