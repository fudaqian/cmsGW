package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

public class Unrules extends Model<Unrules> {

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
	 * 违规公司
	 */
	private String unruleCompany;
	/**
	 * 所属公司
	 */
	private String attributeCompany;
	/**
	 * 查处时间
	 */
	private String checkTime;
	/**
	 * 合作项目
	 */
	private String concertProject;
	/**
	 * 违规事项
	 */
	private String unruleThing;
	/**
	 * 处理结果
	 */
	private String processResult;
	/**
	 * 创建时间
	 */
	private String creatDate;
	/**
	 * 操作员
	 */
	private String userName;
	/**
	 * 修改人
	 */
	private String modifyUserName;
	/**
	 * 修改时间
	 */
	private String modifyDate;
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
		return "Unrules {id=" + id + ", sn=" + sn + ", unruleCompany=" + unruleCompany + ", attributeCompany="
				+ attributeCompany + ", concertProject=" + concertProject + ", unruleThing=" + unruleThing + ", processResult="
				+ processResult + ", creatDate=" + creatDate + ", userName=" + userName + ", modifyUserName="
				+ modifyUserName + ", modifyDate=" + modifyDate + ", version=" + version + ",checkTime"+ checkTime + "}";
	}	
	
	
	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
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

	public String getUnruleCompany() {
		return unruleCompany;
	}


	public void setUnruleCompany(String unruleCompany) {
		this.unruleCompany = unruleCompany;
	}


	public String getAttributeCompany() {
		return attributeCompany;
	}


	public void setAttributeCompany(String attributeCompany) {
		this.attributeCompany = attributeCompany;
	}


	public String getConcertProject() {
		return concertProject;
	}


	public void setConcertProject(String concertProject) {
		this.concertProject = concertProject;
	}


	public String getUnruleThing() {
		return unruleThing;
	}


	public void setUnruleThing(String unruleThing) {
		this.unruleThing = unruleThing;
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
