package com.stylefeng.guns.modular.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.common.persistence.dao.LeaveMsgMapper;
import com.stylefeng.guns.common.persistence.model.Columns;
import com.stylefeng.guns.common.persistence.model.LeaveMsg;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.service.ILeaveMsgService;
import com.stylefeng.guns.modular.system.warpper.MenuWarpper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 栏目控制器
 *
 * @author fengshuonan
 * @Date 2017-10-20 16:58:05
 */
@Controller
@RequestMapping("/leaveMsg")
public class LeaveMsgController extends BaseController {

	@Resource
	LeaveMsgMapper leaveMsgMapper;
	@Resource
	ILeaveMsgService leaveMsgService;

	private String PREFIX = "/system/leaveMsg/";

	/**
	 * 跳转到留言首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "leaveMsg.html";
	}

	/**
	 * 跳转到详情页
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Integer id) {
		return PREFIX + "detail.html";
	}

	/**
	 * 获取留言列表
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object list(@ApiParam("关键字，非必填") @RequestParam(value = "condition", required = false) String condition) {
		List<LeaveMsg> leaveMsgs = leaveMsgService.list(condition);
		return leaveMsgs;
	}

	/**
	 * 添加留言
	 */
	@ApiOperation("添加留言")
	@RequestMapping(value = "/add", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object add(@Valid LeaveMsg leaveMsg) {
		leaveMsg.setCreateDate(DateUtil.getTime());
		Integer code = leaveMsgMapper.insert(leaveMsg);
		if (ToolUtil.isNotEmpty(code)) {
			return SUCCESS;
		}
		throw new BussinessException(BizExceptionEnum.REQUEST_DATA_NULL);
	}

	/**
	 * 添加留言
	 */
	@ApiOperation("测试")
	@RequestMapping(value = "/addTest", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object addT(@ApiParam("关键字，非必填") @RequestParam(value = "condition", required = false) String condition) {
		return SUCCESS;
	}
}
