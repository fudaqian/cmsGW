package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.common.persistence.dao.ColumnMapper;
import com.stylefeng.guns.common.persistence.model.Columns;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.service.IColumnService;
import com.stylefeng.guns.modular.system.warpper.MenuWarpper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 栏目控制器
 *
 * @author fengshuonan
 * @Date 2017-10-20 16:58:05
 */
@Controller
@RequestMapping("/column")
public class ColumnController extends BaseController {

	@Resource
	ColumnMapper columnMapper;
	@Resource
	IColumnService columnServiceImpl;
	
    private String PREFIX = "/system/column/";

    /**
     * 跳转到栏目首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "column.html";
    }

    /**
     * 跳转到添加栏目
     */
    @RequestMapping("/column_add")
    public String columnAdd() {
        return PREFIX + "column_add.html";
    }

    /**
     * 跳转到修改栏目
     */
    @RequestMapping("/column_update/{columnId}")
    public String columnUpdate(@PathVariable Integer columnId, Model model) {
    	Columns column = columnMapper.selectById(columnId);
//    	Columns columnP = columnMapper.selectById(Integer.valueOf(column.getPid()));
//    	if(columnP != null){
        	model.addAttribute("pcodeName", column.getName());
//    	}else{
//    		model.addAttribute("pcodeName", "顶级");
//    	}
    	model.addAttribute("column", column);
        return PREFIX + "column_edit.html";
    }

    /**
     * 获取栏目列表
     */
    @ApiOperation("获取栏目列表")
    @RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object list(
    		@ApiParam("名称，非必填") @RequestParam(value = "condition", required = false)String condition,
    		@ApiParam("是否置顶，非必填") @RequestParam(value = "istop", required = false)Integer istop,
    		@ApiParam("栏目id，非必填") @RequestParam(value = "id", required = false)Integer id,
    		@ApiParam("部门id，非必填") @RequestParam(value = "deptid", required = false)Integer deptid
    	) {
    	List<Map<String, Object>> columns = new ArrayList<>();
    	if(ToolUtil.isNotEmpty(ShiroKit.getUser()) && ShiroKit.isAdmin()){
    		columns = columnServiceImpl.list(null,null,null,null);
    	}else{
    		if(ToolUtil.isEmpty(deptid)){
        		deptid = ShiroKit.getUser().getDeptId();
        	}
    		columns = columnServiceImpl.list(condition,istop,id,deptid);
    	}
        
        return super.warpObject(new MenuWarpper(columns));
    }

    /**
     * 获取栏目列表树接口
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> Tree(String condition) {
    	List<ZTreeNode> roleTreeList = new ArrayList<>();
    	if(ShiroKit.isAdmin()){
    		roleTreeList = columnServiceImpl.tree(null);
    	}else{
    		roleTreeList = columnServiceImpl.tree(ShiroKit.getUser().getDeptId());
    	}
    	roleTreeList.add(ZTreeNode.createParent());
        return roleTreeList;
    }
    
    /**
     * 新增栏目
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Valid Columns column) {
//    	if(column.getPid() == 0){
//    		column.setPids("[0],");
//    		column.setLevels(1);
//    	}else{
//    		Columns pColumns = columnMapper.selectById(column.getPid());
//    		column.setPids(pColumns.getPids()+"["+pColumns.getId()+"],");
//    		column.setLevels(pColumns.getLevels()+1);
//    	}
    	column.setDeptid(ShiroKit.getUser().getDeptId());
    	column.setUrl("articlelist");
    	Integer num = columnMapper.insert(column);
    	if(num == 1){
    		return super.SUCCESS_TIP;
    	}
    	return super.ERROR;
    }

    /**
     * 删除栏目
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(Integer columnId) {
    	Integer num = columnMapper.deleteById(columnId);
    	if(num == 1){
    		return super.SUCCESS_TIP;
    	}
    	return super.ERROR;
    }


    /**
     * 修改栏目
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Valid Columns column) {
//    	if(column.getPid() == 0){
//    		column.setPids("[0],");
//    		column.setLevels(1);
//    	}else{
//    		Columns pColumns = columnMapper.selectById(column.getPid());
//    		column.setPids(pColumns.getPids()+"["+pColumns.getId()+"],");
//    		column.setLevels(pColumns.getLevels()+1);
//    	}
    	column.setUrl("articlelist");
    	Integer num = columnMapper.updateById(column);
    	if(num == 1){
    		return super.SUCCESS_TIP;
    	}
    	return super.ERROR;
    }
}
