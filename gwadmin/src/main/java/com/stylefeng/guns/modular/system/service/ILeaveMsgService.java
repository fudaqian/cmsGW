package com.stylefeng.guns.modular.system.service;

import java.util.List;

import com.stylefeng.guns.common.persistence.model.LeaveMsg;

public interface ILeaveMsgService {

	List<LeaveMsg> list(String condition);
}
