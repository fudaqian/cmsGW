package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

public class Complain extends Model<Complain> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 *举报人 
	 */
	private String sn;
	/**
	 *投诉编号
	 */
	private String putName;
	/**
	 * 举报人身份
	 */
	private String putStatus;
	/**
	 * 举报人手机号码
	 */
	private String putPhone;
	/**
	 * 举报人邮箱
	 */
	private String putEmail;
	/**
	 * 被举报人姓名
	 */
	private String acceptName;
	/**
	 * 被举报人所在部门
	 */
	private String acceptDepartment;
	/**
	 * 被举报人所在公司
	 */
	private String acceptCompany;
	/**
	 * 被举报类型
	 */
	private String acceptType;
	/**
	 * 举报标题
	 */
	private String title;
	/**
	 * 地点
	 */
	private String place;
	/**
	 * 具体过程
	 */
	private String content;
	/**
	 * 文件名称(服务端)
	 */
	private String file;
	/**
	 * 文件原名称
	 */
	private String fileName;
	/**
	 * 文件类型
	 */
	private String fileType;
	/**
	 * 是否实名
	 */
	private String ischeck;
	/**
	 * 反馈建议
	 */
	private String feedbackIdea;
	private String feedbackIdeaHtml;
	private String creatDate;
	
	@Override
	public String toString() {
		return "Complain {id=" + id + ", sn=" + sn + ", putName=" + putName + ", putStatus=" + putStatus + ", putPhone="
				+ putPhone + ", putEmail=" + putEmail + ", acceptName=" + acceptName + ", acceptDepartment="
				+ acceptDepartment + ", acceptCompany=" + acceptCompany + ", acceptType=" + acceptType + ", title="
				+ title + ", place=" + place + ", content=" + content + ", file=" + file + ", fileName=" + fileName
				+ ", fileType=" + fileType + ", ischeck=" + ischeck + ", feedbackIdea=" + feedbackIdea
				+ ", feedbackIdeaHtml=" + feedbackIdeaHtml + ", creatDate=" + creatDate + "}";
	}


	public String getSn() {
		return sn;
	}


	public void setSn(String sn) {
		this.sn = sn;
	}


	public String getFeedbackIdea() {
		return feedbackIdea;
	}

	public void setFeedbackIdea(String feedbackIdea) {
		this.feedbackIdea = feedbackIdea;
	}

	public String getFeedbackIdeaHtml() {
		return feedbackIdeaHtml;
	}

	public void setFeedbackIdeaHtml(String feedbackIdeaHtml) {
		this.feedbackIdeaHtml = feedbackIdeaHtml;
	}

	public String getIscheck() {
		return ischeck;
	}

	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPutName() {
		return putName;
	}

	public void setPutName(String putName) {
		this.putName = putName;
	}

	public String getPutStatus() {
		return putStatus;
	}

	public void setPutStatus(String putStatus) {
		this.putStatus = putStatus;
	}

	public String getPutPhone() {
		return putPhone;
	}

	public void setPutPhone(String putPhone) {
		this.putPhone = putPhone;
	}

	public String getPutEmail() {
		return putEmail;
	}

	public void setPutEmail(String putEmail) {
		this.putEmail = putEmail;
	}

	public String getAcceptName() {
		return acceptName;
	}

	public void setAcceptName(String acceptName) {
		this.acceptName = acceptName;
	}

	public String getAcceptDepartment() {
		return acceptDepartment;
	}

	public void setAcceptDepartment(String acceptDepartment) {
		this.acceptDepartment = acceptDepartment;
	}

	public String getAcceptCompany() {
		return acceptCompany;
	}

	public void setAcceptCompany(String acceptCompany) {
		this.acceptCompany = acceptCompany;
	}

	public String getAcceptType() {
		return acceptType;
	}

	public void setAcceptType(String acceptType) {
		this.acceptType = acceptType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getFile() {
		return file;
	}


	public void setFile(String file) {
		this.file = file;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}

	@Override
	protected Serializable pkVal() {
		// TODO 自动生成的方法存根
		return null;
	}
}
