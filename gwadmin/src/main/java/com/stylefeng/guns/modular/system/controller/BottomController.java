package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.common.annotion.Permission;
import com.stylefeng.guns.common.constant.Const;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.template.config.ContextConfig;
import com.stylefeng.guns.core.template.engine.SimpleTemplateEngine;
import com.stylefeng.guns.core.template.engine.base.GunsTemplateEngine;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.service.IBottomService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 底部栏目控制器
 *
 * @author fudaqian
 * @Date 2017-05-23 18:52:34
 */
@Controller
@RequestMapping("/bottom")
public class BottomController extends BaseController {

	private String PREFIX = "/system/bottom/";

	@Resource
	IBottomService bottomServiceImpl;

	/**
	 * 跳转相关链接配置页
	 */
	@RequestMapping("url")
	public String urlIndex(Model model) {
		if (ShiroKit.isAdmin()) {
			model.addAttribute("urlList", bottomServiceImpl.urlList(null, null));
		} else {
			model.addAttribute("urlList", bottomServiceImpl.urlList(null, ShiroKit.getUser().deptId));
		}
		return PREFIX + "bottom_url.html";
	}

	/**
	 * 跳转企业信息配置页
	 */
	@RequestMapping("company")
	public String companyIndex(Model model) {
		if(ToolUtil.isEmpty(bottomServiceImpl.companyList(ShiroKit.getUser().getDeptId()))){
			return PREFIX + "bottom_company.html";
		}	
		model.addAttribute("company", bottomServiceImpl.companyList(ShiroKit.getUser().getDeptId()).get(0));
		return PREFIX + "bottom_company.html";
	}

	/**
	 * 跳转相关链接添加页
	 */
	@RequestMapping("bottomUrl_add")
	public String bottomUrlAdd(Model model) {
		return PREFIX + "bottomUrl_add.html";
	}

	/**
	 * 跳转相关链接修改页
	 */
	@RequestMapping("bottomUrl_edit/{bottomUrlId}")
	public String bottomUrlEdit(@PathVariable Integer bottomUrlId, Model model) {
		model.addAttribute("url", bottomServiceImpl.urlList(bottomUrlId, null).get(0));
		return PREFIX + "bottomUrl_edit.html";
	}

	/**
	 * 删除相关链接
	 */
	@RequestMapping("bottomUrl_remove")
	@ResponseBody
	public Object bottomUrlRemove(Integer bottomUrlId, Model model) {
		Integer num = bottomServiceImpl.bottomUrlRemove(bottomUrlId);
		if (num == 1) {
			return SUCCESS_TIP;
		}
		return ERROR;
	}

	/**
	 * 获取底部公司信息列表
	 */
	@ApiOperation("获取底部公司信息")
	@RequestMapping(value = "/companyList", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object companyList(@ApiParam("部门id，必填") @RequestParam(value = "deptid", required = false) Integer deptid) {
		return bottomServiceImpl.companyList(deptid).get(0);
	}

	/**
	 * 获取相关链接列表
	 */
	@ApiOperation("获取相关链接")
	@RequestMapping(value = "/urlList", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object urlList(@ApiParam("部门id，必填") @RequestParam(value = "deptid", required = false) Integer deptid) {
		if(ToolUtil.isEmpty(ShiroKit.getUser())){
			return bottomServiceImpl.urlList(null, deptid);
		}else{
			return bottomServiceImpl.urlList(null, ShiroKit.getUser().getDeptId());
		}
		
	}

	/**
	 * 修改相关链接.
	 */
	@RequestMapping(value = "/updateUrl", method = RequestMethod.POST)
	@ResponseBody
	public Object urlUpdate(Integer id, String urlName, String url) {
		Integer num = 0;
		if (id != null) {
			num = bottomServiceImpl.updateBottomUrl(id, url, urlName);
		} else {
			num = bottomServiceImpl.addBottomUrl(null, url, urlName, ShiroKit.getUser().getDeptId());
		}
		if (num == 1) {
			return SUCCESS_TIP;
		}
		return ERROR;
	}

	/**
	 * 修改公司信息
	 */
	@RequestMapping(value = "/updateCompany", method = RequestMethod.POST)
	@ResponseBody
	public Object companyUpdate(Integer id, String place, String email, String phone, String copyright_information,String fax) {
		Integer num = 0;
		if(id != null){
			num = bottomServiceImpl.updateCompany(id, place, phone, email, copyright_information,fax);
		}else{
			num = bottomServiceImpl.insertCompany(place, phone, email, copyright_information,ShiroKit.getUser().getDeptId(),fax);
		}
		if (num == 1) {
			return SUCCESS_TIP;
		}
		return ERROR;
	}

}
