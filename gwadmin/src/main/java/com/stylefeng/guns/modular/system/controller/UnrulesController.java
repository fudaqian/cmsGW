package com.stylefeng.guns.modular.system.controller;

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

import com.stylefeng.guns.common.persistence.dao.UnrulesMapper;
import com.stylefeng.guns.common.persistence.model.Unrules;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.modular.system.service.IUnrulesService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/unrules")
public class UnrulesController extends BaseController{
	
	
	private String PREFIX = "/system/unrules/";

	@Resource
	UnrulesMapper unrulesMapper;
	@Resource
	IUnrulesService unrulesServiceImpl;
	
    /**
     * 跳转到礼品礼金管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "unrules.html";
    }

    /**
     * 跳转到添加礼品礼金公示单
     */
    @RequestMapping("/unrules_add")
    public String presentAdd() {
        return PREFIX + "unrules_add.html";
    }
    
    /**
     * 跳转到修改礼品礼金公示单
     */
    @RequestMapping("/unrules_edit/{unrulesId}")
    public String presentEdit(@PathVariable Integer unrulesId, Model model) {
    	Unrules unrules = unrulesMapper.selectById(unrulesId);
    	unrules.setProcessResult(HtmlUtils.htmlUnescape(unrules.getProcessResult()));
    	model.addAttribute("unrules", unrules);
    	return PREFIX + "unrules_edit.html";
    }
    /**
     * 跳转到修改礼品礼金公示单
     */
    @RequestMapping("/unrules_detail/{unrulesId}")
    public String presentDetail(@PathVariable Integer unrulesId, Model model) {
    	Unrules unrules = unrulesMapper.selectById(unrulesId);
    	unrules.setProcessResult(HtmlUtils.htmlUnescape(unrules.getProcessResult()));
    	model.addAttribute("unrules", unrules);
    	return PREFIX + "unrules_detail.html";
    }
    
    /**
     * 获取所有礼品礼金公示单列表
     */
    @ApiOperation(value = "获取违规列表", notes = "关键字")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Object list(
    		@ApiParam("关键字，非必填") @RequestParam(value = "condition", required = false)String condition,
    		@ApiParam("编号，非必填") @RequestParam(value = "sn", required = false)String sn,
    		@ApiParam("违规公司名称，非必填") @RequestParam(value = "unruleCompany", required = false)String unruleCompany,
    		@ApiParam("所属公司，非必填") @RequestParam(value = "attributeCompany", required = false)String attributeCompany,
    		@ApiParam("礼品礼金分页页码，非必填") @RequestParam(value = "pagenum", required = false)Integer pagenum,
    		@ApiParam("礼品礼金每页展示数量，非必填") @RequestParam(value = "pagesize", required = false)Integer pagesize) {
        return unrulesServiceImpl.list(pagenum,pagesize,condition,sn,unruleCompany,attributeCompany);
    }
    /**
     * 添加礼品礼金公示单
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Valid Unrules unrules) {
    	unrules.setCreatDate(DateUtil.getTime());
    	unrules.setModifyDate(DateUtil.getTime());
    	unrules.setUserName(ShiroKit.getUser().getName());
    	unrules.setModifyUserName(ShiroKit.getUser().getName());
    	unrules.setVersion(1);
    	
    	Integer num = unrulesMapper.insert(unrules);
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
    public Object edit(@Valid Unrules unrules) {
    	unrules.setModifyDate(DateUtil.getTime());
    	unrules.setModifyUserName(ShiroKit.getUser().getName());
    	unrules.setVersion(unrules.getVersion()+1);
    	
    	Integer num = unrulesMapper.updateById(unrules);
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
    public Object remove( Integer unrulesId) {	
    	Integer num = unrulesMapper.deleteById(unrulesId);
    	if(num == 1){
    		return super.SUCCESS_TIP;
    	}
    	return super.ERROR;
    }
    
    /**
     * 获取礼品礼金条数
     */
    @ApiOperation(value = "获取违规条数", notes = "可直接获取")
    @RequestMapping(value = "/count",method = RequestMethod.POST)
    @ResponseBody
    public Object count(@ApiParam("关键字，非必填") @RequestParam(value = "condition", required = false)String condition) { 	
    	Integer num = unrulesServiceImpl.countPresents(condition);
    	if(num != null){
    		return num;
    	}
    	return ERROR;
    }
}
