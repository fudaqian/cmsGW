package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

public class Present extends Model<Present> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 编号
	 */
	private String sn;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 所属公司
	 */
	private String company;
	/**
	 * 礼品礼金
	 */
	private String presentMoney;
	/**
	 * 价值
	 */
	private String worth;
	/**
	 * 礼品厂商
	 */
	private String presentFactory;
	/**
	 * 礼品规格
	 */
	private String presentSpec;
	/**
	 * 处理结果
	 */
	private String processResult;
	/**
	 * 接受时间
	 */
	private String acceptDate;
	/**
	 * 创建时间
	 */
	private String creatDate;
	/**
	 * 修改时间
	 */
	private String modifyDate;
	/**
	 * 操作员
	 */
	private String userName;
	/**
	 * 修改人
	 */
	private String modifyUserName;
	/**
	 * 版本
	 */
	private Integer version;

	@Override
	protected Serializable pkVal() {
		// TODO 自动生成的方法存根
		return null;
	}



	@Override
	public String toString() {
		return "Present {id=" + id + ", sn=" + sn + ", name=" + name + ", company=" + company + ", presentMoney="
				+ presentMoney + ", worth=" + worth + ", presentFactory=" + presentFactory + ", presentSpec="
				+ presentSpec + ", processResult=" + processResult + ", acceptDate=" + acceptDate + ", creatDate="
				+ creatDate + ", modifyDate=" + modifyDate + ", userName=" + userName + ", modifyUserName="
				+ modifyUserName + ", version=" + version + "}";
	}



	public String getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}


	public String getModifyUserName() {
		return modifyUserName;
	}


	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPresentMoney() {
		return presentMoney;
	}

	public void setPresentMoney(String presentMoney) {
		this.presentMoney = presentMoney;
	}

	public String getWorth() {
		return worth;
	}

	public void setWorth(String worth) {
		this.worth = worth;
	}

	public String getPresentFactory() {
		return presentFactory;
	}

	public void setPresentFactory(String presentFactory) {
		this.presentFactory = presentFactory;
	}

	public String getPresentSpec() {
		return presentSpec;
	}

	public void setPresentSpec(String presentSpec) {
		this.presentSpec = presentSpec;
	}

	public String getProcessResult() {
		return processResult;
	}

	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
