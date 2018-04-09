package com.stylefeng.guns.modular.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.persistence.dao.LeaveMsgMapper;
import com.stylefeng.guns.common.persistence.model.LeaveMsg;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.service.ILeaveMsgService;

@Service
public class LeaveMsgServiceImpl implements ILeaveMsgService {

	@Resource
	LeaveMsgMapper leaveMsgMapper;

	@Override
	public List<LeaveMsg> list(String condition) {
		// TODO 自动生成的方法存根
		Wrapper<LeaveMsg> wrapper = new EntityWrapper<>();
		if (ToolUtil.isNotEmpty(condition)) {
			wrapper = wrapper.like("name", "%[" + condition + "]%");
			wrapper = wrapper.like("phone", "%[" + condition + "]%");
			wrapper = wrapper.like("content", "%[" + condition + "]%");
			wrapper = wrapper.like("email", "%[" + condition + "]%");
		}
		wrapper = wrapper.orderBy("createDate", false);
		List<LeaveMsg> subDepts = leaveMsgMapper.selectList(wrapper);
		return subDepts;
	}

}
