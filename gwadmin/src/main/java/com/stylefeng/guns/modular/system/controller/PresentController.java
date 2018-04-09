package com.stylefeng.guns.modular.system.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.stylefeng.guns.common.annotion.Permission;
import com.stylefeng.guns.common.persistence.dao.DeptMapper;
import com.stylefeng.guns.common.persistence.dao.PresentMapper;
import com.stylefeng.guns.common.persistence.model.Present;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.modular.system.dao.DeptDao;
import com.stylefeng.guns.modular.system.service.IDeptService;
import com.stylefeng.guns.modular.system.service.IPresentService;
import com.stylefeng.guns.modular.system.service.impl.NewsServiceImpl;
import com.stylefeng.guns.modular.system.warpper.DeptWarpper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/present")
public class PresentController extends BaseController{
	
	
	private String PREFIX = "/system/present/";

	@Resource
	PresentMapper presentMapper;
	@Resource
	IPresentService presentServiceImpl;
	
    /**
     * 跳转到礼品礼金管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "present.html";
    }

    /**
     * 跳转到添加礼品礼金公示单
     */
    @RequestMapping("/present_add")
    public String presentAdd() {
        return PREFIX + "present_add.html";
    }
    
    /**
     * 跳转到修改礼品礼金公示单
     */
    @RequestMapping("/present_edit/{presentId}")
    public String presentEdit(@PathVariable Integer presentId, Model model) {
    	Present present = presentMapper.selectById(presentId);
    	present.setProcessResult(HtmlUtils.htmlUnescape(present.getProcessResult()));
    	model.addAttribute("present", present);
    	return PREFIX + "present_edit.html";
    }
    /**
     * 跳转到修改礼品礼金公示单
     */
    @RequestMapping("/present_detail/{presentId}")
    public String presentDetail(@PathVariable Integer presentId, Model model) {
    	Present present = presentMapper.selectById(presentId);
    	present.setProcessResult(HtmlUtils.htmlUnescape(present.getProcessResult()));
    	model.addAttribute("present", present);
    	return PREFIX + "present_detail.html";
    }
    
    /**
     * 获取所有礼品礼金公示单列表
     */
    @ApiOperation(value = "获取礼品礼金列表", notes = "关键字")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Object list(
    		@ApiParam("关键字，非必填") @RequestParam(value = "condition", required = false)String condition,
    		@ApiParam("礼品礼金分页页码，非必填") @RequestParam(value = "pagenum", required = false)Integer pagenum,
    		@ApiParam("礼品礼金每页展示数量，非必填") @RequestParam(value = "pagesize", required = false)Integer pagesize,
    		@ApiParam("收单编号，非必填") @RequestParam(value = "sn", required = false)String sn,
    		@ApiParam("姓名，非必填") @RequestParam(value = "name", required = false)String name,
    		@ApiParam("所属公司，非必填") @RequestParam(value = "company", required = false)String company,
    		@ApiParam("开始时间，非必填") @RequestParam(value = "beginTime", required = false)String beginTime,
    		@ApiParam("结束时间，非必填") @RequestParam(value = "endTime", required = false)String endTime) {
        return presentServiceImpl.list(condition,pagenum,pagesize,sn,name,company,beginTime,endTime);
    }
    /**
     * 添加礼品礼金公示单
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Valid Present present) {
    	present.setCreatDate(DateUtil.getTime());
    	present.setModifyDate(DateUtil.getTime());
    	present.setUserName(ShiroKit.getUser().getName());
    	present.setModifyUserName(ShiroKit.getUser().getName());
    	present.setVersion(1);
    	
    	Integer num = presentMapper.insert(present);
    	if(num == 1){
    		return super.SUCCESS_TIP;
    	}
    	return super.ERROR;
    }
    /**
     * 修改礼品礼金公示单
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object edit(@Valid Present present) {
    	present.setModifyDate(DateUtil.getTime());
    	present.setModifyUserName(ShiroKit.getUser().getName());
    	present.setVersion(present.getVersion()+1);
    	
    	Integer num = presentMapper.updateById(present);
    	if(num == 1){
    		return super.SUCCESS_TIP;
    	}
    	return super.ERROR;
    }
    /**
     * 删除礼品礼金公示单
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object remove( Integer presentId) {	
    	Integer num = presentMapper.deleteById(presentId);
    	if(num == 1){
    		return super.SUCCESS_TIP;
    	}
    	return super.ERROR;
    }
    
    /**
     * 获取礼品礼金条数
     */
    @ApiOperation(value = "获取礼品礼金条数", notes = "可直接获取")
    @RequestMapping(value = "/count",method = RequestMethod.POST)
    @ResponseBody
    public Object count(@ApiParam("关键字，非必填") @RequestParam(value = "condition", required = false)String condition) { 	
    	Integer num = presentServiceImpl.countPresents(condition);
    	if(num != null){
    		return num;
    	}
    	return ERROR;
    }
}
